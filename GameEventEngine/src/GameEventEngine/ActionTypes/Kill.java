package GameEventEngine.ActionTypes;

import Entites.EntityTypes.Creature;
import Entity.Main.EntityManager;
import GameEventEngine.StoryManager;
import GameEventEngine.Actions.Action.Action;
import GameEventEngine.Events.Event.Event;
import GameEventEngine.Events.Event.Event_Status;

public class Kill extends Action{
	private Creature creature;
	public Kill(String creatureName, Event activater) {
		super("Kill " + creatureName, activater, "kill " + creatureName + " if " + activater.getName() + " is ACTIVE");
		try {
			this.creature = (Creature) EntityManager.getEntity(creatureName);
		} catch (Exception e) {
			System.err.println("Kill: <" + creatureName + "> is no valid CreatureName");
		}
		
	}

	public Kill(String creatureName, Event_Status status, Event activater) {
		super("Kill " + creatureName , activater, status, "kill " + creatureName + " if " + activater.getName() + " is " + status.toString());
		try {
			this.creature = (Creature) EntityManager.getEntity(creatureName);
		} catch (Exception e) {
			System.err.println("Kill: <" + creatureName + "> is no valid CreatureName");
		}
	}

	@Override
	public void execute() {
		creature.kill();
	}

}
