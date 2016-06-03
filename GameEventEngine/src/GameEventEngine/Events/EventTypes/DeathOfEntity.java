package GameEventEngine.Events.EventTypes;

import DummyGameEntites.Entity;
import GameEventEngine.Events.Event.Event;
import GameEventEngine.Events.Event.Event_Status;
import GameEventEngine.Events.Event.Transistion;

public class DeathOfEntity extends Event{
	private Entity entity;
	
	public DeathOfEntity(String name, Entity entity) {
		super(name , "gets true if " + name + " gets killed");
		this.entity = entity;
		transistion = new Transistion(this);
	}
	
	@Override
	public void update(){
		if(isActive() &! entity.isAlive()){
			DeathOfEntity.setEventStatus(name, Event_Status.DONE);
		}
		transistion.update();
	}
	
	

}
