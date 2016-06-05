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
	private String actualText = null;
	
	public Dialog(Creature creature, String name) {
		this.creature = creature;
		this.name = name;
		actualText = startTag;
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
	
	public void update(){
		//TODO update the dialogs
		//set actualText! acoording to World events!
	}
	
	public void open(){
		actualText = reEntryText;
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
		update();
		return textList.get(textNameList.indexOf(actualText)).open();
	}
	
	public String[] getOptionString() {
		update();
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
	
	public boolean actualHasOptions(){
		return textList.get(textNameList.indexOf(actualText)).hasOption();
	}
	
	public void chooseOption(String optionName){
		actualText = optionList.get(optionName.indexOf(optionName)).choose();
	}
}
