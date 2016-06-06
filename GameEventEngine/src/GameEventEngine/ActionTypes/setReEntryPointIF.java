package GameEventEngine.ActionTypes;

import Entites.Creature;
import GameEventEngine.StoryManager;
import GameEventEngine.Actions.Action.Action;
import GameEventEngine.Events.Event.Event;
import GameEventEngine.Events.Event.Event_Status;

public class setReEntryPointIF extends Action{
	private Creature creature;
	private String textName;
	public setReEntryPointIF(Creature creature, String textName, Event activater) {
		super("Chanche Dialog Entrypoint to: " + textName, activater, "kill " + creature.getName() + " if " + activater.getName() + " is ACTIVE");
		this.creature = creature;
		this.textName = textName;
	}

	public setReEntryPointIF(Creature creature, String textName, Event_Status status, Event activater) {
		super("Chanche Dialog Entrypoint to: " + textName, activater, status, "kill " + creature.getName() + " if " + activater.getName() + " is " + status.toString());
		this.creature = creature;
		this.textName = textName;
	}

	@Override
	public void execute() {
		creature.getDialog().setReEntryText(textName);
	}

}
