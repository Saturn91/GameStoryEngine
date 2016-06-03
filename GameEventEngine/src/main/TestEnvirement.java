package main;

import DummyGameEntites.Entity;
import DummyGameEntites.Player;
import GameEventEngine.StoryManager;
import GameEventEngine.EventEntities.Event;

public class TestEnvirement {
	private static Player player;
	private static Entity creature;
	/**
	 * aplication Launcher
	 * @param args
	 */
	public static void main(String[] args){
		player = new Player();
		creature = new Entity();
		StoryManager syma = new StoryManager();
		//all Events
		syma.addEntityGetsToPosition("GetOutOfTheHole", player, 100, 200);
		syma.addEntityGetsToPosition("X1", player, 100, 400);
		syma.addDeathOfEntity("you have to kill the creature", creature);
		
		//all transitions
		syma.addEventBefore("GetOutOfTheHole", Event.getEventByName("START"));
		syma.addEventBefore("X1", Event.getEventByName("GetOutOfTheHole"));
		syma.addEventBefore("you have to kill the creature", Event.getEventByName("X1"));
		
		//Start
		syma.printStatus();
		syma.update();
		
		//check
		syma.printActiveEvents();
		
		//Player moved
		player.xPos = 100;
		player.yPos = 200;
		
		syma.update();
		syma.printActiveEvents();
		
		//Player moved
		player.xPos = 100;
		player.yPos = 400;
		
		syma.update();
		syma.printActiveEvents();
		
		//kill creature
		creature.alive = false;
		
		syma.update();
		syma.printStatus();
				
	}
}
