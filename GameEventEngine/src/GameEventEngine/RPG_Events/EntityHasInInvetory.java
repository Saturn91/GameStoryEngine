package GameEventEngine.RPG_Events;

import DummyGameEntites.Entity;
import GameEventEngine.EventEntities.Event;
import GameEventEngine.EventEntities.Event_Status;
import GameEventEngine.EventEntities.Transistion;

public class EntityHasInInvetory extends Event{
	private Transistion transistion;
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
