package GameEventEngine.ActionTypes;

import Entites.EntityTypes.Creature;
import Entites.EntityTypes.Thing;
import GameEventEngine.StoryManager;
import GameEventEngine.Actions.Action.Action;
import GameEventEngine.Events.Event.Event;
import GameEventEngine.Events.Event.Event_Status;

public class TakeFromInventory extends Action{
	private Creature creature;
	private Thing thing;
	public TakeFromInventory(Creature creature, Thing thing, Event activater) {
		super("take " + thing.getName(), activater, "take " + thing.getName() + " from " + creature.getName() + " if " + activater.getName() + " is DONE");
		this.creature = creature;
		this.thing = thing;
	}

	public TakeFromInventory(Creature creature, Thing thing, Event_Status status, Event activater) {
		super("take " + thing.getName(), activater, status, "take " + thing.getName() + " from " + creature.getName() + " if " + activater.getName() + " is " + status.toString());
		this.creature = creature;
		this.thing = thing;
	}

	@Override
	public void execute() {
		creature.takefromInventory(thing.getID());
	}

}
