package GameEventEngine.RPG_Events;

import GameEventEngine.EventEntities.Event;
import GameEventEngine.EventEntities.Event_Status;

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
