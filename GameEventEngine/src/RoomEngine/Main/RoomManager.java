package RoomEngine.Main;

import java.util.ArrayList;

import GameEventEngine.Events.Event.Event;

public class RoomManager {
	private ArrayList<Room> rooms;
	private ArrayList<String> roomNames;
	private String activRoom;
	
	//
	//************************** Init-Methodes *********************************************
	//
	public RoomManager(){
		rooms = new ArrayList<>();
		roomNames = new ArrayList<>();
	}
	
	public boolean addRoom(String name){
		if(!roomNames.contains(name)){
			rooms.add(new Room(name));
			roomNames.add(name);
			return true;
		}else{
			System.err.println("RoomManager: <" + name + "> is already defined as a Room!");
			return false;
		}
	}
	
	public boolean setStartRoom(String roomName){
		if(getRoomByName(roomName)!= null){
			activRoom = roomName;
			return true;
		}else{
			return false;
		}
	}
	
	public boolean addExit(String roomName, String targetRoom, int direction){
		if(getRoomByName(roomName) != null && getRoomByName(targetRoom) != null){
			if(getRoomByName(roomName).setExit(targetRoom, direction)){
				return true;
			}else{
				System.err.println("RoomManager: <" + targetRoom + "> wrong argument");
				return false;
			}
		}else{
			return false;
		}
	}
	
	//
	//********************** Descriptions ********************************************
	//
	public boolean addDescription(String roomName, String descriptionName, String description){
		if(getRoomByName(roomName) != null){
			if(getRoomByName(roomName).addDescription(descriptionName, description)){
				return true;
			}else{
				System.err.println("RoomManager: <" + descriptionName + "> wrong argument!");
				return false;
			}
			
		}else{
			return false;
		}
	}
	
	public boolean addDescriptionStoryEventIF(String roomName, String descriptionName, String eventName, boolean flag){
		if(getRoomByName(roomName) != null){
			if(getRoomByName(roomName).setDescriptionStoryEventIF(descriptionName, eventName, flag)){
				return true;
			}else{
				System.err.println("RoomManager: <" + descriptionName +"> can not get a Status change - wrong Argument!");
				return false;
			}			
		}else{
			return false;
		}
	}
	
	public boolean addDescriptionWatchEventIF(String roomName, String descriptionName, Event event, boolean flag){
		if(getRoomByName(roomName) != null){
			if(getRoomByName(roomName).setDescriptionWatchEventIF(descriptionName, event, flag)){
				return true;
			}else{
				System.err.println("RoomManager: <" + descriptionName +"> can not get a Status change - wrong Argument!");
				return false;
			}			
		}else{
			return false;
		}
	}
	
	//
	//************************* Description Texts *************************
	//
	public String getRoomName(){
		return activRoom;
	}
	public String getRoomDescription(){
		if(getRoomByName(activRoom)!= null){
			return getRoomByName(activRoom).getActiveDescriptions();
		}else{
			return null;
		}
	}
	
	public String[] getExits(){
		if(getRoomByName(activRoom)!= null){
			return getRoomByName(activRoom).getExits();
		}else{
			return null;
		}
	}
	
	//
	//************************* Actions for Room *****************************
	//
	public void addToInventory(String roomName, String thingName){
		if(getRoomByName(roomName)!= null){
			getRoomByName(roomName).addToInventory(thingName);
		}
	}
	
	public void takeFromInventory(String roomName, String thingName){
		if(getRoomByName(roomName)!= null){
			getRoomByName(roomName).takefromInventory(thingName);
		}
	}
	
	//
	//************************* GoTo *******************************************
	//
	public boolean goTo(int direction){
		if(getRoomByName(activRoom).getExit(direction) != null){
			activRoom = getRoomByName(activRoom).getExit(direction);
			return true;
		}else{
			return false;
		}
		
	}
	
	private Room getRoomByName(String roomName){
		for(Room r: rooms){
			if(r.getName().equals(roomName)){
				return r;
			}
		}
		System.err.println("RoomManager: <" + roomName + "> is not a valuable RoomName");
		return null;
	}
}
