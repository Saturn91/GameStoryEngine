package GameEventEngine.Events.Main;

import java.util.ArrayList;

import DummyGameEntites.Entity;
import DummyGameEntites.Player;
import GameEventEngine.Events.EventTypes.Event;
import GameEventEngine.Events.EventTypes.Event_Status;
import GameEventEngine.Events.RPG_Events.DeathOfEntity;
import GameEventEngine.Events.RPG_Events.EntityGetsToPosition;
import GameEventEngine.Events.RPG_Events.EntityHasInInvetory;
import GameEventEngine.Events.RPG_Events.StartEvent;

public class EventManager {
	ArrayList<Event> eventList = new ArrayList<>();
	
	public EventManager() {
		//create the First Event to initialize the Story 
		eventList.add(new StartEvent());
	}
	
	public void addEntityGetsToPosition(String name, Entity entity, int x, int y){
		EntityGetsToPosition gtp = new EntityGetsToPosition(name, entity, x, y);
		eventList.add(gtp);
	}
	
	public void addDeathOfEntity(String name, Entity entity){
		eventList.add(new DeathOfEntity(name, entity));
	}
	
	public void addHasInInventory(String name, Entity entity, Entity inInventory){
		eventList.add(new EntityHasInInvetory(name, entity, inInventory));
	}
	
	public void addEventBefore(String name, Event event){
		Event.getEventByName(name).addEventBefore(event);
	}
	
	public void addEventBeforeOR(String name, Event event, int branch){
		Event.getEventByName(name).addEventBeforeOR(event, branch);
	}
	
	public void update(){
		for(Event e: eventList){
			e.update();
		}
	}
	
	public void printStatus(){
		System.out.println("********************************************************");
		for(Event e: eventList){
			if(e.isStatus(e.getName(), Event_Status.ACTIVE)){
				System.out.println(e.getName() + " is active");
			}
			if(e.isStatus(e.getName(), Event_Status.DONE)){
				System.out.println(e.getName() + " is Done");
			}
			if(e.isStatus(e.getName(), Event_Status.INACTIVE)){
				System.out.println(e.getName() + " is inactive");
			}
		}
	}
	
	public void printActiveEvents(){
		System.out.println("********************************************************");
		for(Event e: eventList){
			if(e.isStatus(e.getName(), Event_Status.ACTIVE)){
				System.out.println(e.getName() + " is active");
			}
		}
	}
}
