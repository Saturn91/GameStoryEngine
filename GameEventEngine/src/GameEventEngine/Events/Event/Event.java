package GameEventEngine.Events.Event;

import java.util.ArrayList;

public abstract class Event {
	protected Transistion transistion;
	public static final String START_TAG = "[START]";
	private static ArrayList<Long> usedIds = new ArrayList<>();
	private static ArrayList<Event> events = new ArrayList<>();
	private Event_Status status = Event_Status.INACTIVE;
	private long id;
	protected String name;
	private String description;
	
	public Event(String name, String description){
		id = usedIds.size();
		usedIds.add(id);
		this.name = name;
		this.description = description;
		events.add(this);
		transistion = new Transistion(this);
	}
	
	private Event_Status getStatus(){
		return status;
	}
	
	private void setStatus(Event_Status status){
		this.status = status;
	}
	
	public String getName(){
		return name;
	}
	
	public String getDescription(){
		return description;
	}
	
	public static long getMaxID(){
		return (long) usedIds.size()-1;
	}
	
	public static Event getEventByID(long id){
		return events.get((int) id);
	}
	
	public static Event getEventByName(String name){
		for(Event e: events){
			if(e.getName().equals(name)){
				return e;
			}
		}
		System.err.println("Event: no Event called: " + name );
		return null;
	}
	
	public void clearIDs(){
		usedIds.clear();
	}
	
	public long getID(){
		return id;
	}
	
	public static boolean setEventStatus(String eventName, Event_Status status){
		for(Event e: events){
			if(e.getName().equals(eventName)){
				e.setStatus(status);
				return true;
			}
		}
		System.err.println("no Event named: " + eventName);
		return false;
	}
	
	public static boolean isStatus(String eventname, Event_Status status){
		for(Event e: events){
			if(e.getName().equals(eventname) && e.getStatus().equals(status)){
				return true;
			}
		}
		
		return false;
	}
	
	protected boolean isActive(){
		return status.equals(Event_Status.ACTIVE);
	}
	
	public void addEventBefore(Event event){
		transistion.addEventBefore(event);
	}
	
	public void addEventBeforeOR(Event event, int branch){
		transistion.addEventBeforeOR(event, branch);
	}
	
	public boolean isStatus(Event_Status status){
		return status.equals(status);
	}
	
	abstract public void update();
	
	
}
