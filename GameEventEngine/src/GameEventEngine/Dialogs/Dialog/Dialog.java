package GameEventEngine.Dialogs.Dialog;

import java.util.ArrayList;

import Entites.Creature;
import GameEventEngine.Events.Event.Event;

public class Dialog {
	private static final String startTag = "[UNDEFIND]";
	private String reEntryText;
	private Creature creature;
	private String name;
	private ArrayList<Text> textList = new ArrayList<>();
	private ArrayList<String> textNameList = new ArrayList<>();
	private ArrayList<Option> optionList = new ArrayList<>();
	private ArrayList<TextTransitionTracker> textTranistionTrackers = new ArrayList<>();
	private String actualText = null;
	
	public Dialog(Creature creature, String name) {
		this.creature = creature;
		this.name = name;
		actualText = startTag;
	}
	
	public void addTransitionTracker(String originText, String defaultText){
		if(textNameList.contains(originText)){
			if(textNameList.contains(defaultText)){
				if(!getText(originText).hasTransitionTracker()){
					textTranistionTrackers.add(new TextTransitionTracker(originText, defaultText));
					getText(originText).addTransitionTracker();
				}else{
					System.err.println("Dialog: <" + originText + "> has already a TransitionTracker!");
				}
			}else{
				System.err.println("Dialog: <" + defaultText + "> is no valid textname!");
			}
		}else{
			System.err.println("Dialog: <" + originText + "> is no valid textname!");
		}
	}
	
	public void addTransition(String originText, Event event, String textName){
		getTransitionTrackerOf(originText).addTransition(event, textName);
	}
	
	public void addText(String name, String text){
		if(!textNameList.contains(name)){
			textList.add(new Text(name, text));
			textNameList.add(name);
		}else{
			System.err.println("Dialog: <" + name + "> is already defined as a text!");
		}
	}
	
	public void addOption(String optionText, String OriginTextName, String DestinationTextName){
		optionList.add(new Option(optionText, textList.get(textNameList.indexOf(OriginTextName)), 
				textList.get(textNameList.indexOf(DestinationTextName))));
		textList.get(textNameList.indexOf(OriginTextName)).addOptions();
	}
	
	public void setReEntryPointIF(String reEntryText, Event event){
		
	}
	
	private Text getText(String name){
		if(textNameList.contains(name)){
			return textList.get(textNameList.indexOf(name));
		}else{
			System.err.println("Dialog: <" + name + "> is not a known text for " + name);
			return null;
		}
	}
	
	public void open(){
		if(reEntryText != null){
			actualText = reEntryText;
		}else{
			System.err.println("Dialog: no reEntryText has been choosen!");
		}		
	}
	
	public void close(){
		actualText = reEntryText;
	}
	
	public void setReEntryText(String name){
		if(textNameList.contains(name)){
			reEntryText = name;
		}else{
			System.err.println("Dialog: Text:<" + name + "> does not exists -> not able to change reentrypoint! ");
		}
	}

	public String getTextString() {
		return textList.get(textNameList.indexOf(actualText)).open();
	}
	
	public String[] getOptionString() {
		String[] options = new String[textList.get(textNameList.indexOf(actualText)).getOptionNum()];
		int counter = 0;
		for(Option o: optionList){
			if(o.isOptionOf(actualText)){
				options[counter] = o.getName();
				counter++;
			}
		}
		return options;
	}
	
	public void getNextText(){
		actualText = getTransitionTrackerOf(actualText).nextText();
	}
	
	public boolean actualHasOptions(){
		return textList.get(textNameList.indexOf(actualText)).hasOption();
	}
	
	public void chooseOption(String optionName){
		for(Option o: optionList){
			if(o.getName().equals(optionName)){
				actualText = o.choose();
			}
		}
		//actualText = optionList.get(optionName.indexOf(optionName)).choose();
	}
	
	public TextTransitionTracker getTransitionTrackerOf(String originText){
		for(TextTransitionTracker t: textTranistionTrackers){
			if(t.isOriginText(originText)){
				return t;
			}
		}
		System.err.println("Dialog: no transitionTracker ");
		return null;
	}
}
