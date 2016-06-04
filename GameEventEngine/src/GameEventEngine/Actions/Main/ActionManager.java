package GameEventEngine.Actions.Main;

import java.util.ArrayList;

import Entites.Creature;
import GameEventEngine.ActionTypes.Kill;
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
