package GameEventEngine.Events.EventTypes;

import Entites.EntityTypes.Entity;
import Entity.Main.EntityManager;
import GameEventEngine.Events.Event.Event;
import GameEventEngine.Events.Event.Event_Status;
import GameEventEngine.Events.Event.Transistion;

public class EntityHasInInvetory extends Event{
	private Entity entity;
	private Entity inInventory;
	
	public EntityHasInInvetory(String name, String entityName, String inInventory) {
		super(name , "gets true if " + inInventory + " is in " + entityName + "'s Inventory");
		this.entity = EntityManager.getEntity(entityName);
		this.inInventory = EntityManager.getEntity(inInventory);
		transistion = new Transistion(this);
	}
	
	@Override
	public void update(){
		if(isActive() && entity.hasinInventory(inInventory.getName())){
			EntityHasInInvetory.setEventStatus(name, Event_Status.DONE);
		}
		transistion.update();
	}
	
	

}
