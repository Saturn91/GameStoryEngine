package GameEventEngine.EventEntities;

import java.util.ArrayList;

public class Transistion {
	private Event mainEvent;
	private ArrayList<Event> eventsBefore = new ArrayList<>();
	private boolean isOver = false;
	
	public Transistion(Event event){
		this.mainEvent = event;
	}
	
	public void addEventBefore(Event event){
		eventsBefore.add(event);
	}
	
	public void update(){
		if(!isOver){
			int counter = 0;
			for(Event e: eventsBefore){
				if(Event.isStatus(e.getName(), Event_Status.DONE)){
					counter++;
				}
			}
			
			if(counter == eventsBefore.size()){
				isOver = true;
				Event.setEventStatus(mainEvent.getName(), Event_Status.ACTIVE);
			}
		}
	}
}
