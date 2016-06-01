package main;

import DummyGameEntites.Player;
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
		syma.addGetToPosition("X1", 100, 400);
		
		syma.addEventBefore("GetOutOfTheHole", Event.getEventWithName("START"));
		syma.addEventBefore("X1", Event.getEventWithName("GetOutOfTheHole"));
		//Start
		syma.printStatus();
		syma.update();
		
		//check
		syma.printStatus();
		
		//Player moved
		Player.xPos = 100;
		Player.yPos = 200;
		
		syma.update();
		syma.printStatus();
		
		//Player moved
		Player.xPos = 100;
		Player.yPos = 400;
		
		syma.update();
		syma.printStatus();
		
		
		
	}
}
