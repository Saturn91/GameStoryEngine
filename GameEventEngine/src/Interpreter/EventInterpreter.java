package Interpreter;

import GameEventEngine.Events.Event.Event;
import GameEventEngine.Events.EventTypes.DeathOfEntity;
import GameEventEngine.Events.EventTypes.EntityGetsToPosition;
import GameEventEngine.Events.EventTypes.EntityHasInInvetory;
import GameEventEngine.Events.EventTypes.TextSeenByPlayer;

public class EventInterpreter {
	public static Event addEvent(String[] args){
		try {
			switch(args[1]){
				case "InInventory": {
					return new EntityHasInInvetory(args[2], args[3], args[4]);
				}
				
				case "DeathOf": {
					return new DeathOfEntity(args[2], args[3]);
				}
				
				case "Pos": {
					return new EntityGetsToPosition(args[2], args[3], Integer.parseInt(args[4]), Integer.parseInt(args[5]));
				}
				
				case "Read": {
					return new TextSeenByPlayer(args[2], args[3], args[4]);
				}				
				
				default: {
					return null;
				}
			}
		} catch (Exception e) {
			System.err.println("EventInterpreter: unexpected argument!");
			return null;
		}
	}
	
	public static Event addEvent(String[] args, int pushArgumentCounterBy){
		try {
			switch(args[1+pushArgumentCounterBy]){
				case "InInventory": {
					return new EntityHasInInvetory(args[2+pushArgumentCounterBy], args[3+pushArgumentCounterBy], args[4+pushArgumentCounterBy]);
				}
				
				case "DeathOf": {
					return new DeathOfEntity(args[2+pushArgumentCounterBy], args[3+pushArgumentCounterBy]);
				}
				
				case "Pos": {
					return new EntityGetsToPosition(args[2+pushArgumentCounterBy], args[3+pushArgumentCounterBy], Integer.parseInt(args[4+pushArgumentCounterBy]), Integer.parseInt(args[5+pushArgumentCounterBy]));
				}
				
				case "Read": {
					return new TextSeenByPlayer(args[2+pushArgumentCounterBy], args[3+pushArgumentCounterBy], args[4+pushArgumentCounterBy]);
				}				
				
				default: {
					return null;
				}
			}
		} catch (Exception e) {
			System.err.println("EventInterpreter: unexpected argument!");
			return null;
		}
	}
}
