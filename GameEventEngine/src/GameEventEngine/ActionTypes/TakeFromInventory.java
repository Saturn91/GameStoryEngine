package GameEventEngine.ActionTypes;

import Entites.EntityTypes.Creature;
import Entites.EntityTypes.Thing;
import Entity.Main.EntityManager;
import GameEventEngine.StoryManager;
import GameEventEngine.Actions.Action.Action;
import GameEventEngine.Events.Event.Event;
import GameEventEngine.Events.Event.Event_Status;

public class TakeFromInventory extends Action{
	private Creature creature;
	private Thing thing;
	public TakeFromInventory(String creatureName, String thingName, Event activater) {
		super("take " + thingName, activater, "take " + thingName + " from " + creatureName + " if " + activater.getName() + " is DONE");
		try {
			this.creature = (Creature) EntityManager.getEntity(creatureName);
		} catch (Exception e) {
			System.err.println("TakeFromInventory: <" + creatureName + "> is no valid CreatureName");
		}
		try {
			this.thing = (Thing) EntityManager.getEntity(thingName);
		} catch (Exception e) {
			System.err.println("TakeFromInventory: <" + thingName + "> is no valid Thing");
		}
	}

	public TakeFromInventory(String creatureName, String thingName, Event_Status status, Event activater) {
		super("take " + thingName, activater, status, "take " + thingName + " from " + creatureName + " if " + activater.getName() + " is " + status.toString());
		try {
			this.creature = (Creature) EntityManager.getEntity(creatureName);
		} catch (Exception e) {
			System.err.println("TakeFromInventory: <" + creatureName + "> is no valid CreatureName");
		}
		try {
			this.thing = (Thing) EntityManager.getEntity(thingName);
		} catch (Exception e) {
			System.err.println("TakeFromInventory: <" + thingName + "> is no valid Thing");
		}
	}

	@Override
	public void execute() {
		creature.takefromInventory(thing.getID());
	}

}
