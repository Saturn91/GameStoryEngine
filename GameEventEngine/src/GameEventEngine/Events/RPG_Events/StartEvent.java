package GameEventEngine.Events.RPG_Events;

import GameEventEngine.Events.EventTypes.Event;
import GameEventEngine.Events.EventTypes.Event_Status;

public class StartEvent extends Event{

	public StartEvent() {
		super("START", "Initialization");
		this.setEventStatus("START", Event_Status.DONE);
	}

	@Override
	public void addEventBefore(Event event) {
		//unused
		
	}

	@Override
	public void update() {
		//unused
		
	}
	
}
