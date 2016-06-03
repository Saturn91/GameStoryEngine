package GameEventEngine.Events.RPG_Events;

import DummyGameEntites.Entity;
import GameEventEngine.Events.EventTypes.Event;
import GameEventEngine.Events.EventTypes.Event_Status;
import GameEventEngine.Events.EventTypes.Transistion;

public class EntityHasInInvetory extends Event{
	private Entity entity;
	private Entity inInventory;
	
	public EntityHasInInvetory(String name, Entity entity, Entity inInventory) {
		super(name , "gets true if " + inInventory.getName() + " is in " + entity.getName() + "'s Inventory");
		this.entity = entity;
		this.inInventory = inInventory;
		transistion = new Transistion(this);
	}
	
	@Override
	public void update(){
		if(isActive() && entity.hasinInventory(inInventory.getID())){
			EntityHasInInvetory.setEventStatus(name, Event_Status.DONE);
		}
		transistion.update();
	}
	
	

}
