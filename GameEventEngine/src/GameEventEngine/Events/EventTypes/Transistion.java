package GameEventEngine.Events.EventTypes;

import java.util.ArrayList;

public class Transistion {
	private Event mainEvent;
	private Branch branches[] = new Branch[10];
	private boolean isOver = false;
	
	public Transistion(Event event){
		this.mainEvent = event;
		for(int i = 0; i < 10; i++){
			branches[i] = new Branch();
		}
	}
	
	public void addEventBefore(Event event){
		branches[0].add(event);
	}
	
	public void addEventBeforeOR(Event event, int branch){
		branches[branch].add(event);
	}
	
	public void update(){
		if(!isOver){
			for(int i = 0; i < 10; i++){
				branches[i].update();
				if(isOver){
					break;
				}
			}
		}
	}
	
	private class Branch{
		private ArrayList<Event> eventsBefore = new ArrayList<>();
		public void add(Event event){
			eventsBefore.add(event);
		}
		
		public void update(){
			int counter = 0;
			for(Event e: eventsBefore){
				if(Event.isStatus(e.getName(), Event_Status.DONE)){
					counter++;
				}
			}
			
			if(counter == eventsBefore.size() && counter > 0){
				Event.setEventStatus(mainEvent.getName(), Event_Status.ACTIVE);
				isOver = true;
			}
		}
		
	}
}
