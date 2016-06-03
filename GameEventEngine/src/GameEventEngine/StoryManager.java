package GameEventEngine;

import java.util.ArrayList;

import DummyGameEntites.Entity;
import DummyGameEntites.Player;
import GameEventEngine.EventEntities.Event;
import GameEventEngine.EventEntities.Event_Status;
import GameEventEngine.RPG_Events.DeathOfEntity;
import GameEventEngine.RPG_Events.EntityGetsToPosition;
import GameEventEngine.RPG_Events.EntityHasInInvetory;
import GameEventEngine.RPG_Events.StartEvent;

public class StoryManager {
	ArrayList<Event> eventList = new ArrayList<>();
	
	public StoryManager() {
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
