package GameEventEngine.RPG_Events;

import DummyGameEntites.Entity;
import DummyGameEntites.Player;
import GameEventEngine.EventEntities.Event;
import GameEventEngine.EventEntities.Event_Status;
import GameEventEngine.EventEntities.Transistion;

public class EntityGetsToPosition extends Event{
	private int x;
	private int y;
	private Transistion transistion;
	private Entity entity;
	
	public EntityGetsToPosition(String name, int x, int y) {
		super(name , "try to find this Position on the Map!");
		this.x = x;
		this.y = y;
		transistion = new Transistion(this);
	}
	
	@Override
	public void addEventBefore(Event event){
		transistion.addEventBefore(event);
	}
	
	@Override
	public void update(){
		if(entity == null){
			System.err.println("please set an entity -> GetToPosition");
			System.exit(0);
		}
		if(isActive() && entity.xPos == x && entity.yPos == y){
			EntityGetsToPosition.setEventStatus(name, Event_Status.DONE);
		}
		transistion.update();
	}
	
	public void setEntity(Entity entity){
		this.entity = entity;
	}
	
	

}
