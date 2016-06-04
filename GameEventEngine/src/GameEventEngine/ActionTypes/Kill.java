package GameEventEngine.ActionTypes;

import Entites.Creature;
import GameEventEngine.StoryManager;
import GameEventEngine.Actions.Action.Action;
import GameEventEngine.Events.Event.Event;
import GameEventEngine.Events.Event.Event_Status;

public class Kill extends Action{
	private Creature creature;
	public Kill(Creature creature, Event activater) {
		super("Kill " + creature.getName() , activater, "kill " + creature.getName() + " if " + activater.getName() + " is ACTIVE");
		this.creature = creature;
	}

	public Kill(Creature creature, Event_Status status, Event activater) {
		super("Kill " + creature.getName() , activater, status, "kill " + creature.getName() + " if " + activater.getName() + " is " + status.toString());
		this.creature = creature;
	}

	@Override
	public void execute() {
		creature.kill();
	}

}
