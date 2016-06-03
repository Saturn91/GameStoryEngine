package GameEventEngine.Actions.Action;

import java.util.ArrayList;

import GameEventEngine.StoryManager;
import GameEventEngine.Events.Event.Event;
import GameEventEngine.Events.Event.Event_Status;

public abstract class Action {
	protected String name;
	private String description;
	private Event activater;
	private Event_Status status;
	private long id;
	private boolean isExecuted = false;
	private static ArrayList<Action> actions = new ArrayList<>();
	
	/**
	 * Default Constructor, the Action will be executed when the activater Event is ACTIVE
	 * @param name nameTag of Action
	 * @param activater Event wich leads to execution if its status is ACTIVE
	 * @param description short Text wich discripts the Action
	 */
	public Action(String name, Event activater, String description) {
		this.name = name;
		this.activater = activater;
		this.status = Event_Status.ACTIVE;
		this.description = description;
		id = actions.size();
		actions.add(this);
	}
	
	/**
	 * Advanced Constructor where you can define wich status the activator Event must have to execute the Action
	 * @param name nameTag of the action
	 * @param activater Event wich sets Action to executionmode if Evfent.status is the same as specified in status
	 * @param status INACTIV/ACTIVE/DONE
	 * @param description short text to describe the action
	 */
	public Action(String name, Event activater, Event_Status status, String description) {
		this.name = name;
		this.activater = activater;
		this.status = status;
		this.description = description;
		id = actions.size();
		actions.add(this);
	}
	
	public void update(){
		if(Event.isStatus(activater.getName(), status) &! isExecuted){
			if(StoryManager.testing){
				System.out.println("Action : [" + description + "] gets executed!");
			}
			execute();
			isExecuted = true;
		}
	}
	
	public void print(){
		System.out.println("Action: " + name + ": " + description + " already Executed?: " + isExecuted);
	}
	
	public void reInit(){
		isExecuted = false;
	}
	
	public Action getActionByName(String name){
		for(Action a: actions){
			if(a.name.equals(name)){
				return a;
			}
		}
		System.err.println("No action \"" + name + "\" found!");
		return null;
	}
	
	public Action getActionByID(long id){
		if(id >= 0 && id < actions.size()){
			return actions.get((int) id); 
		}
		System.err.println("No action" + id + " found! MaxId = " + (actions.size()-1));
		return null;
	}
	
	public abstract void execute();
	
	
	
	
	
	
}
