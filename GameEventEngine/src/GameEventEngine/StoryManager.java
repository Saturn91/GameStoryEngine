package GameEventEngine;

import java.util.ArrayList;

import Entites.Entity;
import Entites.Player;
import GameEventEngine.Actions.Main.ActionManager;
import GameEventEngine.Events.Event.Event;
import GameEventEngine.Events.Event.Event_Status;
import GameEventEngine.Events.EventTypes.DeathOfEntity;
import GameEventEngine.Events.EventTypes.EntityGetsToPosition;
import GameEventEngine.Events.EventTypes.EntityHasInInvetory;
import GameEventEngine.Events.EventTypes.StartEvent;
import GameEventEngine.Events.Main.EventManager;

public class StoryManager {
	public static boolean testing = false;
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
	
	public void update(){
		//update status (Events)
		evma.update();
		
		//modify gameWorld with actions
		acma.update();
	}
	
	public void printStatus(){
		evma.printStatus();
		acma.printStatus();
	}
	
	public void printStatusActiveEvents(){
		evma.printActiveEvents();
		acma.printStatus();
	}
	
	public static void setTesting(boolean ttesting){
		testing = ttesting;
	}
}
