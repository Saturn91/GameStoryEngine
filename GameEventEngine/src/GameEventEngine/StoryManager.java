package GameEventEngine;

import java.util.ArrayList;

import GameEventEngine.EventEntities.Event;
import GameEventEngine.EventEntities.Event_Status;
import GameEventEngine.RPG_Events.GetToPosition;
import GameEventEngine.RPG_Events.StartEvent;

public class StoryManager {
	ArrayList<Event> eventList = new ArrayList<>();
	
	public StoryManager() {
		//create the First Event to initialize the Story 
		eventList.add(new StartEvent());
	}
	
	public void addGetToPosition(String name, int x, int y){
		eventList.add(new GetToPosition(name, x, y));
	}
	
	public void addEventBefore(String name, Event event){
		Event.getEventWithName(name).addEventBefore(event);
	}
	
	public void update(){
		for(Event e: eventList){
			e.update();
		}
	}
	
	public void printStatus(){
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
}
