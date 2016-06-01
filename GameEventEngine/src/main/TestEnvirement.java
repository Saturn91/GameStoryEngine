package main;

import GameEventEngine.StoryManager;
import GameEventEngine.EventEntities.Event;

public class TestEnvirement {
	/**
	 * aplication Launcher
	 * @param args
	 */
	public static void main(String[] args){
		StoryManager syma = new StoryManager();
		syma.addGetToPosition("GetOutOfTheHole", 100, 200);
		syma.addGetToPosition("X1", 100, 200);
		
		syma.addEventBefore("GetOutOfTheHole", Event.getEventWithName("START"));
		syma.addEventBefore("X1", Event.getEventWithName("GetOutOfTheHole"));
		syma.printStatus();
		
		syma.update();
		
		syma.printStatus();
		
		
		
		
	}
}
