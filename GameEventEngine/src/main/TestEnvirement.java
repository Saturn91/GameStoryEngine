package main;

import GameEventEngine.GameEventController;
import GameEventEngine.EventEntities.Event;

public class TestEnvirement {
	/**
	 * aplication Launcher
	 * @param args
	 */
	public static void main(String[] args){
		//initialisisze
		GameEventController gEC = new GameEventController();
		
		//	build EventTree
		//
		//
		//			first the Events
		//
		gEC.createNewEvent("Start", "Initialisiation");
		gEC.defineInitEvent(Event.getEventWithName("Start"));
		gEC.createNewEvent("getDagger", "We have to find a Dagger to kill the Watchman at the door!");
		gEC.createNewEvent("getKey", "I have to find the Key for the Door!");
		gEC.createNewEvent("KillWatchman", "I have to kill the Watchman if i want to escape this mansion!");
		gEC.createNewEvent("openDoor", "I have to get out of here!");
		gEC.createNewEvent("talkToX", "I wonder what X knows about this place...");
		gEC.createNewEvent("TalkToB", "And what does B know?");
		
		//
		//			second the trasistions
		//
		Event[] eventsBefore = new Event[1];
		//from start on
		eventsBefore[0] = Event.getEventWithName("Start");
		gEC.createNewTransistion(Event.getEventWithName("getDagger"), eventsBefore);
		
		gEC.createNewTransistion(Event.getEventWithName("getKey"), eventsBefore);
		
		gEC.createNewTransistion(Event.getEventWithName("talkToX"), eventsBefore);
		
		//from getDagger
		eventsBefore[0] = Event.getEventWithName("getDagger");
		gEC.createNewTransistion(Event.getEventWithName("KillWatchman"), eventsBefore);
		
		//from KillWatchman && getKey to openDoor
		eventsBefore = new Event[2];
		eventsBefore[0] = Event.getEventWithName("KillWatchman");
		eventsBefore[1] = Event.getEventWithName("getKey");
		gEC.createNewTransistion(Event.getEventWithName("openDoor"), eventsBefore);
		
		//from openDoor && talkToX to TalkToB
		eventsBefore[0] = Event.getEventWithName("openDoor");
		eventsBefore[1] = Event.getEventWithName("talkToX");
		gEC.createNewTransistion(Event.getEventWithName("TalkToB"), eventsBefore);
		
		
		
		//*******************let the story beginn*************************************
		gEC.printActives();
		System.out.println("*************************************");
		gEC.setEventDone(Event.getEventWithName("getDagger"));
		gEC.printActives();
		System.out.println("*************************************");
		gEC.setEventDone(Event.getEventWithName("talkToX"));
		gEC.printActives();
		System.out.println("*************************************");
		gEC.setEventDone(Event.getEventWithName("KillWatchman"));
		gEC.printActives();
		System.out.println("*************************************");
		gEC.setEventDone(Event.getEventWithName("getKey"));
		gEC.printActives();
		System.out.println("*************************************");
		gEC.setEventDone(Event.getEventWithName("openDoor"));
		gEC.printActives();
		
		
		
		
		
		
	}
}
