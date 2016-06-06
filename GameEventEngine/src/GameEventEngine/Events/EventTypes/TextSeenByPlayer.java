package GameEventEngine.Events.EventTypes;

import Entites.Creature;
import GameEventEngine.Events.Event.Event;
import GameEventEngine.Events.Event.Event_Status;
import GameEventEngine.Events.Event.Transistion;

public class TextSeenByPlayer extends Event{
	private Creature creature;
	private String textName;
	public TextSeenByPlayer(String name, String textName ,Creature creature) {
		super(name , "gets true if " + name + " gets killed");
		this.creature = creature;
		this.textName = textName;
		transistion = new Transistion(this);
	}
	
	@Override
	public void update(){
		if(isActive() && creature.getDialog().getText(textName).alreadyRead()){
			TextSeenByPlayer.setEventStatus(name, Event_Status.DONE);
		}
		transistion.update();
	}
	
	

}
