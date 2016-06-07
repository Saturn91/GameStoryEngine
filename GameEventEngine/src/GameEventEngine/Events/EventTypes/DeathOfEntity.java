package GameEventEngine.Events.EventTypes;

import Entites.EntityTypes.Entity;
import Entity.Main.EntityManager;
import GameEventEngine.Events.Event.Event;
import GameEventEngine.Events.Event.Event_Status;
import GameEventEngine.Events.Event.Transistion;

public class DeathOfEntity extends Event{
	private Entity entity;
	
	public DeathOfEntity(String name, String entityName) {
		super(name , "gets true if " + entityName + " gets killed");
		this.entity = EntityManager.getEntity(entityName);
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
