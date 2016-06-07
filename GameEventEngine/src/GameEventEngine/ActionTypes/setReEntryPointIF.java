package GameEventEngine.ActionTypes;

import Entites.EntityTypes.Creature;
import Entity.Main.EntityManager;
import GameEventEngine.StoryManager;
import GameEventEngine.Actions.Action.Action;
import GameEventEngine.Events.Event.Event;
import GameEventEngine.Events.Event.Event_Status;

public class setReEntryPointIF extends Action{
	private Creature creature;
	private String textName;
	public setReEntryPointIF(String creatureName, String textName, Event activater) {
		super("Chanche Dialog Entrypoint to: " + textName, activater, "kill " + creatureName + " if " + activater.getName() + " is ACTIVE");
		try {
			this.creature = (Creature) EntityManager.getEntity(creatureName);
		} catch (Exception e) {
			System.err.println("setRentryPointIF: <" + creatureName + "> is no valid CreatureName");
		}
		this.textName = textName;
	}

	public setReEntryPointIF(String creatureName, String textName, Event_Status status, Event activater) {
		super("Chanche Dialog Entrypoint to: " + textName, activater, status, "kill " + creatureName + " if " + activater.getName() + " is " + status.toString());
		try {
			this.creature = (Creature) EntityManager.getEntity(creatureName);
		} catch (Exception e) {
			System.err.println("setRentryPointIF: <" + creatureName + "> is no valid CreatureName");
		}
		this.textName = textName;
		this.textName = textName;
	}

	@Override
	public void execute() {
		creature.getDialog().setReEntryText(textName);
	}

}
