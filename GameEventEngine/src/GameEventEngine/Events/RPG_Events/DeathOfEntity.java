package GameEventEngine.Events.RPG_Events;

import DummyGameEntites.Entity;
import GameEventEngine.Events.EventTypes.Event;
import GameEventEngine.Events.EventTypes.Event_Status;
import GameEventEngine.Events.EventTypes.Transistion;

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
