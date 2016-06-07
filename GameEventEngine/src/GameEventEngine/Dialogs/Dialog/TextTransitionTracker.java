package GameEventEngine.Dialogs.Dialog;

import java.util.ArrayList;

import GameEventEngine.Events.Event.Event;
import GameEventEngine.Events.Event.Event_Status;

public class TextTransitionTracker {
	private String originText;
	private String defaultText;
	private ArrayList<String> destinationTexts = new ArrayList<>();
	private ArrayList<Event> eventsList = new ArrayList<>();
	
	public TextTransitionTracker(String originText, String defaultText) {
		this.defaultText = defaultText;
		this.originText = originText;
	}
	
	public void addTransition(Event event, String textName){
		Event.setEventStatus(event.getName(), Event_Status.ACTIVE);
		destinationTexts.add(textName);
		eventsList.add(event);
	}
	
	public String nextText(){
		update();
		for(Event e: eventsList){
			if(Event.isStatus(e.getName(), Event_Status.DONE)){
				return destinationTexts.get(eventsList.indexOf(e));
			}
		}
		return defaultText;
	}
	
	private void update() {
		for(Event e: eventsList){
			e.update();
		}
	}
	
	public boolean isOriginText(String name){
		return originText.equals(name);
	}
}
