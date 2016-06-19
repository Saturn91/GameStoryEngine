package GameEventEngine.Rooms.Main;

import java.util.ArrayList;

import Entites.EntityTypes.Entity;
import Entites.EntityTypes.Thing;
import GameEventEngine.Events.Event.Event;
import GameEventEngine.Events.Event.Event_Status;

public class Room extends Thing{
	private String[] exits = new String[4];
	private String roomName;
	private ArrayList<Description> descriptions = new ArrayList<>();
	private static ArrayList<String> descriptionNames = new ArrayList<>();
	private StringBuilder sb = new StringBuilder();
	private ArrayList<Event> watchevents = new ArrayList<>();
	
	public Room(String name){
		super("Room: " + name);
		super.setToContainer(true);
		this.roomName = name;
	}
	
	public boolean addDescription(String name, String text){
		descriptions.add(new Description(this.roomName, name, text));
		if(descriptions.get(descriptions.size()-1).getText() != null){
			return true;
		}else{
			descriptions.remove(descriptions.size()-1);
			return false;
		}
	}
	
	/**
	 * 
	 * @param roomName = name of the room the exit leeds to
	 * @param direction = 0 for nord, 1 for east, 2 for south, 3 for west
	 * @return true if well right implemented and false if a failure happend
	 */
	public boolean setExit(String roomName, int direction){
		if(direction < 4 && direction >= 0){
			exits[direction] = roomName;
			return true;
		}else{
			System.err.println("Rooms: <" + direction + "> has to be between 0 and 3");
			return false;
		}
	}
	
	public String getActiveDescriptions(){
		sb.setLength(0);
		for(Description d: descriptions){
			if(d.isActive){
				sb.append(d.getText() + " ");
			}
		}
		return sb.toString();
	}
	
	public String[] getExits(){
		return exits;
	}
	
	public String getExit(int exit){
		if(exit < 4 && exit >= 0){
			if(exits[exit] != null){
				return exits[exit];
			}else{
				System.err.println("Room: <" + exit + "> is not a valuable Exit for " + roomName);
				return null;
			}
		}else{
			System.err.println("Room: <" + exit + "> must be < 4 and >= 0!");
			return null;
		}
	}
	
	public boolean setDescriptionStoryEventIF(String descriptionName, String eventName, boolean flag){
		if(descriptionNames.contains(descriptionName)){
			descriptions.get(descriptionNames.indexOf(descriptionName)).setEvent(Event.getEventByName(eventName), !flag);
			return true;
		}else{
			System.err.println("Rooms: <" + descriptionName + "> is not a valuable Description-Name!");
			return false;
		}
		
	}
	
	public boolean setDescriptionWatchEventIF(String descriptionName, Event event, boolean flag){
		if(descriptionNames.contains(descriptionName)){
			Event.setEventStatus(event.getName(), Event_Status.ACTIVE);
			watchevents.add(event);
			descriptions.get(descriptionNames.indexOf(descriptionName)).setEvent(event, !flag);
			return true;
		}else{
			System.err.println("Rooms: <" + descriptionName + "> is not a valuable Description-Name!");
			return false;
		}
		
	}
	
	@Override
	public void kill(){
		System.err.println("Room: you can't kill a Room!");
	}
	
	public void resurect() {
		System.err.println("Room: you can't ressourect a Room!");
	}
	
	@Override
	public String getName(){
		return this.roomName;
	}
	
	public void update(){
		for(Event e: watchevents){
			e.update();
		}
	}
	
	private class Description{
		private Event event;
		private boolean isActive = true;
		private boolean listenTo;
		private String text;
		
		public Description(String textName, String name, String text){
			if(!descriptionNames.contains(name)){
				descriptionNames.add(name);
				this.text = text;
			}else{
				System.err.println("Room: <" + name + "> is already a description of <" + textName + ">!");
			}			
		}
				
		public String getText(){
			return text;
		}
		
		public boolean isActive(){
			if(event != null){
				if(event.isStatus(Event_Status.DONE)){
					return listenTo;
				}else{
					return !listenTo;
				}
			}else{
				return isActive;
			}			
		}
		
		public void setEvent(Event event, boolean listenTo){
			this.event = event;
			Event.setEventStatus(event.getName(), Event_Status.ACTIVE);
			this.listenTo = listenTo;
		}
	}
	
	
	
	
	
	
}
