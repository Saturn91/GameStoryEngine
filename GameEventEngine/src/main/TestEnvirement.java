package main;

import DummyGameEntites.Creature;
import DummyGameEntites.Entity;
import DummyGameEntites.Player;
import DummyGameEntites.Thing;
import GameEventEngine.StoryManager;
import GameEventEngine.Events.EventTypes.Event;

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
		syma.getEventManager().addEntityGetsToPosition("GetOutOfTheHole", player, 100, 200);
		syma.getEventManager().addEntityGetsToPosition("X1", player, 100, 400);
		syma.getEventManager().addDeathOfEntity("you have to kill the creature", creature);
		syma.getEventManager().addHasInInventory("GetTheDagger", player, thing);
		syma.getEventManager().addEntityGetsToPosition("X2", creature, 100, 100);
		
		//all transitions
		syma.getEventManager().addEventBefore("GetOutOfTheHole", Event.getEventByName("START"));
		syma.getEventManager().addEventBefore("X1", Event.getEventByName("GetOutOfTheHole"));
		syma.getEventManager().addEventBefore("you have to kill the creature", Event.getEventByName("X1"));
		syma.getEventManager().addEventBefore("GetTheDagger", Event.getEventByName("you have to kill the creature"));
		syma.getEventManager().addEventBeforeOR("GetTheDagger", Event.getEventByName("X2"), 1);
		syma.getEventManager().addEventBefore("X2", Event.getEventByName("START"));
		
		//Start
		syma.getEventManager().printStatus();
		syma.getEventManager().update();
		
		syma.getEventManager().printActiveEvents();
		
		//Player moved
		player.setPosition(100, 200);
		
		syma.getEventManager().update();
		syma.getEventManager().printActiveEvents();
		
		//Player moved
		player.setPosition(100, 400);
		
		syma.getEventManager().update();
		syma.getEventManager().printActiveEvents();
		
		//setPos creature
		creature.setPosition(100, 100);
		
		syma.getEventManager().update();
		syma.getEventManager().printActiveEvents();
		
		//don't kill creature
		//creature.kill();
		
		syma.getEventManager().update();
		syma.getEventManager().printActiveEvents();
		
		//take dagger from creature
		player.addToInventory(creature.takefromInventory(thing.getID()).getID());
		
		syma.getEventManager().update();
		syma.getEventManager().printStatus();
				
	}
}
