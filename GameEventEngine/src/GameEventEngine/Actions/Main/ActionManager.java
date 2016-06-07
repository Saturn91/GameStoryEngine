package GameEventEngine.Actions.Main;

import java.util.ArrayList;

import Entites.EntityTypes.Creature;
import Entites.EntityTypes.Thing;
import GameEventEngine.ActionTypes.Kill;
import GameEventEngine.ActionTypes.TakeFromInventory;
import GameEventEngine.ActionTypes.giveToInventory;
import GameEventEngine.ActionTypes.setReEntryPointIF;
import GameEventEngine.Actions.Action.Action;
import GameEventEngine.Events.Event.Event;
import GameEventEngine.Events.Event.Event_Status;

public class ActionManager {
	ArrayList<Action> actionList = new ArrayList<>();
	
	public void addKill( String creatureName, Event activater){
		actionList.add(new Kill(creatureName, activater));
	}
	
	public void addKill( String creatureName, Event_Status status, Event activater){
		actionList.add(new Kill(creatureName, status, activater));
	}
	
	public void addSetReyEntryPointIf( String creatureName, String textName, Event activater){
		actionList.add(new setReEntryPointIF(creatureName, textName, activater));
	}
	
	public void addSetReyEntryPointIf( String creatureName, String textName, Event_Status status, Event activater){
		actionList.add(new setReEntryPointIF(creatureName, textName, status, activater));
	}
	
	public void addGive( String creatureName, String thingName, Event activater){
		actionList.add(new giveToInventory(creatureName, thingName, activater));
	}
	
	public void addGive( String creatureName, String thingName, Event_Status status, Event activater){
		actionList.add(new giveToInventory(creatureName, thingName, status, activater));
	}
	
	public void addTake( String creatureName, String thingName, Event activater){
		actionList.add(new TakeFromInventory(creatureName, thingName, activater));
	}
	
	public void addTake( String creatureName, String thingName, Event_Status status, Event activater){
		actionList.add(new TakeFromInventory(creatureName, thingName, status, activater));
	}
	
	public void update(){
		for(Action a: actionList){
			a.update();
		}
	}
	
	public void printStatus(){
		for(Action a: actionList){
			a.print();
		}
	}
}
