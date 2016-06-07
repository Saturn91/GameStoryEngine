package GameEventEngine.Events.EventTypes;

import Entites.EntityTypes.Creature;
import Entity.Main.EntityManager;
import GameEventEngine.Events.Event.Event;
import GameEventEngine.Events.Event.Event_Status;
import GameEventEngine.Events.Event.Transistion;
import GameEventEngine.Events.Main.EventManager;

public class TextSeenByPlayer extends Event{
	private Creature creature;
	private String textName;
	public TextSeenByPlayer(String name, String textName, String creatureName) {
		super(name , "gets true if " + creatureName + " gets killed");
		try {
			this.creature = (Creature) EntityManager.getEntity(creatureName);
			this.textName = textName;
			transistion = new Transistion(this);
		} catch (Exception e) {
			System.err.println("TextseenByPlayer: <" + creatureName + "> is not a creature!");
		}		
	}
	
	@Override
	public void update(){
		if(isActive() && creature.getDialog().getText(textName).alreadyRead()){
			TextSeenByPlayer.setEventStatus(name, Event_Status.DONE);
		}
		transistion.update();
	}
	
	

}
