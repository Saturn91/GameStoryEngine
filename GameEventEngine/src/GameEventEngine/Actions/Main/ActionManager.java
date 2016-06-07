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
	
	public void addKill(Creature creature, Event activater){
		actionList.add(new Kill(creature, activater));
	}
	
	public void addKill(Creature creature, Event_Status status, Event activater){
		actionList.add(new Kill(creature, status, activater));
	}
	
	public void addSetReyEntryPointIf(Creature creature, String textName, Event activater){
		actionList.add(new setReEntryPointIF(creature, textName, activater));
	}
	
	public void addSetReyEntryPointIf(Creature creature, String textName, Event_Status status, Event activater){
		actionList.add(new setReEntryPointIF(creature, textName, status, activater));
	}
	
	public void addGive(Creature creature, Thing thing, Event activater){
		actionList.add(new giveToInventory(creature, thing, activater));
	}
	
	public void addGive(Creature creature, Thing thing, Event_Status status, Event activater){
		actionList.add(new giveToInventory(creature, thing, status, activater));
	}
	
	public void addTake(Creature creature, Thing thing, Event activater){
		actionList.add(new TakeFromInventory(creature, thing, activater));
	}
	
	public void addTake(Creature creature, Thing thing, Event_Status status, Event activater){
		actionList.add(new TakeFromInventory(creature, thing, status, activater));
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
