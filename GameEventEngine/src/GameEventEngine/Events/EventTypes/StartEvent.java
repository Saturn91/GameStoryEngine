package GameEventEngine.Events.EventTypes;

import GameEventEngine.Events.Event.Event;
import GameEventEngine.Events.Event.Event_Status;

public class StartEvent extends Event{

	public StartEvent() {
		super(Event.START_TAG, "Initialization");
		this.setEventStatus(Event.START_TAG, Event_Status.DONE);
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
