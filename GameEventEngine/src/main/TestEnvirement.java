package main;

import DummyGameEntites.Creature;
import DummyGameEntites.Entity;
import DummyGameEntites.Player;
import DummyGameEntites.Thing;
import GameEventEngine.StoryManager;
import GameEventEngine.EventEntities.Event;

public class TestEnvirement {
	private static Player player;
	private static Creature creature;
	private static Thing thing;
	/**
	 * aplication Launcher
	 * @param args
	 */
	public static void main(String[] args){
		player = new Player();
		creature = new Creature("Creature");
		thing = new Thing("Dagger");
		creature.addToInventory(thing.getID());
		
		StoryManager syma = new StoryManager();
		//all Events
		syma.addEntityGetsToPosition("GetOutOfTheHole", player, 100, 200);
		syma.addEntityGetsToPosition("X1", player, 100, 400);
		syma.addDeathOfEntity("you have to kill the creature", creature);
		syma.addHasInInventory("GetTheDagger", player, thing);
		syma.addEntityGetsToPosition("X2", creature, 100, 100);
		
		//all transitions
		syma.addEventBefore("GetOutOfTheHole", Event.getEventByName("START"));
		syma.addEventBefore("X1", Event.getEventByName("GetOutOfTheHole"));
		syma.addEventBefore("you have to kill the creature", Event.getEventByName("X1"));
		syma.addEventBefore("GetTheDagger", Event.getEventByName("you have to kill the creature"));
		syma.addEventBeforeOR("GetTheDagger", Event.getEventByName("X2"), 1);
		syma.addEventBefore("X2", Event.getEventByName("START"));
		
		//Start
		syma.printStatus();
		syma.update();
		
		syma.printActiveEvents();
		
		//Player moved
		player.setPosition(100, 200);
		
		syma.update();
		syma.printActiveEvents();
		
		//Player moved
		player.setPosition(100, 400);
		
		syma.update();
		syma.printActiveEvents();
		
		//setPos creature
		creature.setPosition(100, 100);
		
		syma.update();
		syma.printActiveEvents();
		
		//don't kill creature
		//creature.kill();
		
		syma.update();
		syma.printActiveEvents();
		
		//take dagger from creature
		player.addToInventory(creature.takefromInventory(thing.getID()).getID());
		
		syma.update();
		syma.printStatus();
				
	}
}
