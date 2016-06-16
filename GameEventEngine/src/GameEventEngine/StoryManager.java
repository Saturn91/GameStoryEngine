package GameEventEngine;

import java.util.ArrayList;

import Entites.EntityTypes.Entity;
import Entity.Main.EntityManager;
import GameEventEngine.Actions.Main.ActionManager;
import GameEventEngine.Dialogs.Main.DialogManager;
import GameEventEngine.Events.Event.Event;
import GameEventEngine.Events.Event.Event_Status;
import GameEventEngine.Events.EventTypes.DeathOfEntity;
import GameEventEngine.Events.EventTypes.EntityGetsToPosition;
import GameEventEngine.Events.EventTypes.EntityHasInInvetory;
import GameEventEngine.Events.EventTypes.StartEvent;
import GameEventEngine.Events.Main.EventManager;
import RoomEngine.Main.RoomManager;

public class StoryManager {
	public static boolean testing = false;
	private EventManager evma;
	private ActionManager acma;
	private DialogManager dima;
	private EntityManager enma;
	private RoomManager roma;
	
	public StoryManager() {
		evma = new EventManager();
		acma = new ActionManager();
		dima = new DialogManager();
		enma = new EntityManager();
		roma = new RoomManager();
	}
	
	public EventManager getEventManager(){
		return evma;
	}
	
	public RoomManager getRoomManager(){
		return roma;
	}
	
	public ActionManager getActionManager(){
		return acma;
	}
	
	public DialogManager getDialogManager(){
		return dima;
	}
	
	public EntityManager getEntityManager(){
		return enma;
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
