package GameEventEngine.ActionTypes;

import Entites.EntityTypes.Creature;
import Entites.EntityTypes.Thing;
import Entity.Main.EntityManager;
import GameEventEngine.StoryManager;
import GameEventEngine.Actions.Action.Action;
import GameEventEngine.Events.Event.Event;
import GameEventEngine.Events.Event.Event_Status;

public class giveToInventory extends Action{
	private Creature creature;
	private Thing thing;
	public giveToInventory(String creatureName, String thingName, Event activater) {
		super("give " + thingName, activater, "give " + thingName + " to " + creatureName + " if " + activater.getName() + " is DONE");
		try {
			this.creature = (Creature) EntityManager.getEntity(creatureName);
		} catch (Exception e) {
			System.err.println("giveToInventory: <" + creatureName + "> is no valid CreatureName");
		}
		try {
			this.thing = (Thing) EntityManager.getEntity(thingName);
		} catch (Exception e) {
			System.err.println("giveToInventory: <" + thingName + "> is no valid Thing");
		}		
	}

	public giveToInventory(String creatureName, String thingName, Event_Status status, Event activater) {
		super("give " + thingName, activater, status, "give " + thingName + " to " + creatureName + " if " + activater.getName() + " is " + status.toString());
		try {
			this.creature = (Creature) EntityManager.getEntity(creatureName);
		} catch (Exception e) {
			System.err.println("giveToInventory: <" + creatureName + "> is no valid CreatureName");
		}
		try {
			this.thing = (Thing) EntityManager.getEntity(thingName);
		} catch (Exception e) {
			System.err.println("giveToInventory: <" + thingName + "> is no valid Thing");
		}
	}

	@Override
	public void execute() {
		creature.addToInventory(thing.getName());
	}

}
