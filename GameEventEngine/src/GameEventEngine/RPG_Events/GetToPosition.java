package GameEventEngine.RPG_Events;

import DummyGameEntites.Player;
import GameEventEngine.EventEntities.Event;
import GameEventEngine.EventEntities.Event_Status;
import GameEventEngine.EventEntities.Transistion;

public class GetToPosition extends Event{
	private int x;
	private int y;
	private Transistion transistion;
	
	public GetToPosition(String name, int x, int y) {
		super(name , "try to find this Position on the Map!");
		transistion = new Transistion(this);
	}
	
	@Override
	public void addEventBefore(Event event){
		transistion.addEventBefore(event);
	}
	
	@Override
	public void update(){
		if(isActive() && Player.xPos == x && Player.yPos == y){
			GetToPosition.setEventStatus(name, Event_Status.DONE);
		}
		transistion.update();
	}
	
	

}
