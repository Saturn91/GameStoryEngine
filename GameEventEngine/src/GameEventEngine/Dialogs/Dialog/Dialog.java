package GameEventEngine.Dialogs.Dialog;

import java.util.ArrayList;

import GameEventEngine.Events.Event.Event;

public class Dialog {
	private static final String startTag = "[UNDEFIND]";
	private String reEntryText;
	private ArrayList<Text> textList = new ArrayList<>();
	private ArrayList<String> textNameList = new ArrayList<>();
	private ArrayList<Option> optionList = new ArrayList<>();
	private ArrayList<TextTransitionTracker> textTranistionTrackers = new ArrayList<>();
	private String actualText = null;
	
	public Dialog() {
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
		if(textNameList.contains(originText)){
			if(textNameList.contains(textName)){
				getTransitionTrackerOf(originText).addTransition(event, textName);
			}else{
				System.err.println("Dialog: <" + textName + "> no valid Text!");
			}
		}else{
			System.err.println("Dialog: <" + originText + "> no valid Text!");
		}
	}
	
	public boolean addText(String name, String text){
		if(!textNameList.contains(name)){
			textList.add(new Text(name, text));
			textNameList.add(name);
			return true;
		}else{
			System.err.println("Dialog: <" + name + "> is already defined as a text!");
			return false;
		}
	}
	
	public void addOption(String optionText, String originTextName, String destinationTextName){
		if(textNameList.contains(originTextName)){
			if(textNameList.contains(destinationTextName)){
				optionList.add(new Option(optionText, textList.get(textNameList.indexOf(originTextName)), 
						textList.get(textNameList.indexOf(destinationTextName))));
				textList.get(textNameList.indexOf(originTextName)).addOptions();
			}else{
				System.err.println("Dialog: <" + destinationTextName + "> is no valid TextName!");
			}
		}else{
			System.err.println("Dialog: <" + originTextName + "> is no valid TextName!");
		}
	}
	
	public Text getText(String name){
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
		if(getText(actualText).hasTransitionTracker()){
			actualText = getTransitionTrackerOf(actualText).nextText();
		}else{
			System.err.println("Dialog: no Transitiontracker for: " + actualText);
		}
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
	}
	
	public boolean actualTextIsBye(){
		return getText(actualText).isBye();
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
	
	public void setTextBye(String name, boolean bye){
		getText(name).setBye(bye);
	}
}
