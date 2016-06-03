package GameEventEngine;

import java.util.ArrayList;

import DummyGameEntites.Entity;
import DummyGameEntites.Player;
import GameEventEngine.Events.EventTypes.Event;
import GameEventEngine.Events.EventTypes.Event_Status;
import GameEventEngine.Events.Main.EventManager;
import GameEventEngine.Events.RPG_Events.DeathOfEntity;
import GameEventEngine.Events.RPG_Events.EntityGetsToPosition;
import GameEventEngine.Events.RPG_Events.EntityHasInInvetory;
import GameEventEngine.Events.RPG_Events.StartEvent;

public class StoryManager {
	private EventManager evma;
	
	public StoryManager() {
		evma = new EventManager();
	}
	
	public EventManager getEventManager(){
		return evma;
	}
}
