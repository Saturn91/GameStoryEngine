package main;

import DummyGameEntites.Creature;
import DummyGameEntites.Entity;
import DummyGameEntites.Player;
import DummyGameEntites.Thing;
import GameEventEngine.StoryManager;
import GameEventEngine.Events.Event.Event;

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
		syma.getEventManager().addEntityGetsToPosition("X1", player, 100, 100);
		syma.getEventManager().addDeathOfEntity("X2", creature);
		syma.getEventManager().addEntityGetsToPosition("X3", player, 200, 200);
		
		//all transitions
		syma.getEventManager().addEventBefore("X1", Event.getEventByName("START"));
		syma.getEventManager().addEventBefore("X2", Event.getEventByName("X1"));
		syma.getEventManager().addEventBefore("X3", Event.getEventByName("X2"));
		
		//all actions
		syma.getActionManager().addKill(creature, Event.getEventByName("X2"));
		
	//Start
		syma.printStatus();
		//X1 gets active
		syma.update();
		
		syma.printStatusActiveEvents();
		
	//Player moved
		player.setPosition(100, 100);
		//X1 -> done, X2 -> active, Creature dies (because X2 is active)
		syma.update();		
		
		syma.printStatusActiveEvents();
		
	//Creature is Dead!
		//X2 -> done, X3 -> active
		syma.update();
		
		syma.printStatusActiveEvents();
		
	//Player moves
		player.setPosition(200, 200);
		//X3 -> done
		syma.update();
		
		syma.printStatus();
				
	}
}
