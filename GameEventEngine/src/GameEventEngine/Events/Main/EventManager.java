package GameEventEngine.Events.Main;

import java.util.ArrayList;

import Entites.EntityTypes.Creature;
import Entites.EntityTypes.Entity;
import GameEventEngine.Events.Event.Event;
import GameEventEngine.Events.Event.Event_Status;
import GameEventEngine.Events.EventTypes.DeathOfEntity;
import GameEventEngine.Events.EventTypes.EntityGetsToPosition;
import GameEventEngine.Events.EventTypes.EntityHasInInvetory;
import GameEventEngine.Events.EventTypes.EntityStatusIs;
import GameEventEngine.Events.EventTypes.StartEvent;
import GameEventEngine.Events.EventTypes.TextSeenByPlayer;

public class EventManager {
	ArrayList<Event> eventList = new ArrayList<>();
	
	public EventManager() {
		//create the First Event to initialize the Story 
		eventList.add(new StartEvent());
	}
	
	public void addEntityGetsToPosition(String name, String entityName, int x, int y){
		EntityGetsToPosition gtp = new EntityGetsToPosition(name, entityName, x, y);
		eventList.add(gtp);
	}
	
	public void addDeathOfEntity(String name, String entityName){
		eventList.add(new DeathOfEntity(name, entityName));
	}
	
	public void addHasInInventory(String name, String entityName, String inInventory){
		eventList.add(new EntityHasInInvetory(name, entityName, inInventory));
	}
	
	public void addIsStatus(String name, String entityName, String varName, int value){
		eventList.add(new EntityStatusIs(name, entityName, varName, value));
	}
	
	public void addTextRead(String name, String textName, String creatureName){
		eventList.add(new TextSeenByPlayer(name, textName, creatureName));
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
