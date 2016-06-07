package GameEventEngine.ActionTypes;

import Entites.EntityTypes.Creature;
import Entites.EntityTypes.Thing;
import GameEventEngine.StoryManager;
import GameEventEngine.Actions.Action.Action;
import GameEventEngine.Events.Event.Event;
import GameEventEngine.Events.Event.Event_Status;

public class giveToInventory extends Action{
	private Creature creature;
	private Thing thing;
	public giveToInventory(Creature creature, Thing thing, Event activater) {
		super("give " + thing.getName(), activater, "give " + thing.getName() + " to " + creature.getName() + " if " + activater.getName() + " is DONE");
		this.creature = creature;
		this.thing = thing;
	}

	public giveToInventory(Creature creature, Thing thing, Event_Status status, Event activater) {
		super("give " + thing.getName(), activater, status, "give " + thing.getName() + " to " + creature.getName() + " if " + activater.getName() + " is " + status.toString());
		this.creature = creature;
		this.thing = thing;
	}

	@Override
	public void execute() {
		creature.addToInventory(thing.getID());
	}

}
