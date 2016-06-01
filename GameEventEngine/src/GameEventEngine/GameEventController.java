package GameEventEngine;

import java.util.ArrayList;

import GameEventEngine.EventEntities.Event;
import GameEventEngine.EventEntities.Event_Status;
import GameEventEngine.EventEntities.Transistion;

public class GameEventController {
	
	private ArrayList<Transistion> transisitionList = new ArrayList<>();
	
	public GameEventController(){
		
	}
	
	public long createNewEvent(String name, String description){
		new Event(name, description);
		return Event.getMaxID();
	}
	
	public void createNewTransistion(long eventID, long[] idsAfter){
		transisitionList.add(new Transistion(Event.getEventWithID(eventID)));
		for(int i = 0; i < idsAfter.length; i++){
			transisitionList.get(transisitionList.size()-1).addEventBefore(Event.getEventWithID(idsAfter[i]));
		}		
	}
	
	public void createNewTransistion(Event event, Event[] eventsAfter){
		transisitionList.add(new Transistion(event));
		for(int i = 0; i < eventsAfter.length; i++){
			transisitionList.get(transisitionList.size()-1).addEventBefore(eventsAfter[i]);
		}		
	}
	
	public void setEventDone(Event event){
		if(Event.isStatus(event.getName(), Event_Status.ACTIVE)){
			Event.setEventStatus(event.getName(), Event_Status.DONE);
			for(Transistion t: transisitionList){
				t.update();
			}
		}else{
			System.err.println("GameEventControler: Event " + event.getName() + " is'nt active!");
		}
	}
	
	public void printActives(){
		update();
		System.out.println("find: " + Event.getMaxID() + " Events");
		for(int id = 0; id < Event.getMaxID()+1; id++){
			if(Event.isStatus(Event.getEventWithID(id).getName(), Event_Status.ACTIVE))
			System.out.println(Event.getEventWithID(id).getName() + ":  " + Event.getEventWithID(id).getDescription());
		}
	}
	
	/*public void printStatus(){
		update();
		System.out.println("find: " + Event.getMaxID() + " Events");
		for(int id = 0; id < Event.getMaxID()+1; id++){
			System.out.println(Event.getEventWithID(id).getName() + ":  " + Event.getEventWithID(id).getDescription() + "| is actual: " + Event.getEventWithID(id).getStatus().toString());
		}
	}*/
	
	public void update(){
		for(Transistion t: transisitionList){
			t.update();
		}
	}
	
	public void defineInitEvent(Event event){
		Event.setEventStatus(event.getName(), Event_Status.DONE);
	}
}
