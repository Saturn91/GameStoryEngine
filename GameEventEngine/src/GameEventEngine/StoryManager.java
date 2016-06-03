package GameEventEngine;

import java.util.ArrayList;

import DummyGameEntites.Entity;
import DummyGameEntites.Player;
import GameEventEngine.Actions.Main.ActionManager;
import GameEventEngine.Events.Event.Event;
import GameEventEngine.Events.Event.Event_Status;
import GameEventEngine.Events.EventTypes.DeathOfEntity;
import GameEventEngine.Events.EventTypes.EntityGetsToPosition;
import GameEventEngine.Events.EventTypes.EntityHasInInvetory;
import GameEventEngine.Events.EventTypes.StartEvent;
import GameEventEngine.Events.Main.EventManager;

public class StoryManager {
	private EventManager evma;
	private ActionManager acma;
	
	public StoryManager() {
		evma = new EventManager();
		acma = new ActionManager();
	}
	
	public EventManager getEventManager(){
		return evma;
	}
	
	public ActionManager getActionManager(){
		return acma;
	}
}
