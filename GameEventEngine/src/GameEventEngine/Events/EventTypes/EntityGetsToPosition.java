package GameEventEngine.Events.EventTypes;

import Entites.Entity;
import Entites.Player;
import GameEventEngine.Events.Event.Event;
import GameEventEngine.Events.Event.Event_Status;
import GameEventEngine.Events.Event.Transistion;

public class EntityGetsToPosition extends Event{
	private int x;
	private int y;
	private Entity entity;
	
	public EntityGetsToPosition(String name, Entity entity, int x, int y) {
		super(name , "try to find this Position on the Map!");
		this.x = x;
		this.y = y;
		this.entity = entity;
		transistion = new Transistion(this);
	}
	
	@Override
	public void update(){
		if(entity == null){
			System.err.println("please set an entity -> GetToPosition");
			System.exit(0);
		}
		if(isActive() && entity.getX() == x && entity.getY() == y){
			EntityGetsToPosition.setEventStatus(name, Event_Status.DONE);
		}
		transistion.update();
	}
}
