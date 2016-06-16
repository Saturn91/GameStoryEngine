package Interpreter.Dialog;

import java.util.ArrayList;

import GameEventEngine.StoryManager;
import GameEventEngine.Events.Event.Event;
import GameEventEngine.Events.EventTypes.DeathOfEntity;
import GameEventEngine.Events.EventTypes.EntityGetsToPosition;
import GameEventEngine.Events.EventTypes.EntityHasInInvetory;
import GameEventEngine.Events.EventTypes.TextSeenByPlayer;
import Interpreter.InterpreterPrefixes;
import SaveSystem.SaveSystem;

public class DialogInterpreter {
	private SaveSystem reader;
	private String creature;
	private String filePath;
	private StoryManager syma;
	private int errorCounter;
	private int countLines;
	private ArrayList<Event> watchEvents = new ArrayList<>();
	
	public DialogInterpreter(String creatureName, String filePath) {
		reader = new SaveSystem(filePath, "dia");
		this.filePath = filePath;
		this.creature = creatureName;
	}
	
	public boolean isValidPath(){
		return reader.readFile(filePath);
	}
	
	public StoryManager compileTo(StoryManager syma){
		this.syma = syma;
		countLines = 0;
		errorCounter = 0;
		addText();
		addEvents();
		addTransitions();
		addOptions();
		setParameters();
		System.out.println("DialogInterpreter: tried to compile " + countLines + " Commands for " + creature + " - failed: " + errorCounter);		
		return this.syma;
	}

	private void addText() {
		int[] lines = reader.getPrefixLinePositions(DialogPrefixes.addText + ":");
		String[] args;
		String[] splitArgsAndText;
		for(int i = 0; i < lines.length; i++){
			countLines++;
			splitArgsAndText = reader.loadLine(lines[i]).split("\"");			
			if(splitArgsAndText.length > 1){
				args = splitArgsAndText[0].split(" ");
				if(!syma.getDialogManager().getDialog(creature).addText(args[1], splitArgsAndText[1])){
					errorCounter ++;
					System.err.println("DialogInterpreter: Error in Line: " + (lines[i]+1) + " of " + filePath + " unvalid TextName");
				}
			}else{
				errorCounter ++;
				System.err.println("DialogInterpreter: Error in Line: " + (lines[i]+1) + " of " + filePath + " no '\"' in line found! ");
			}
		}
	}
	
	private void addEvents() {
		int[] lines = reader.getPrefixLinePositions(InterpreterPrefixes.addEvent + ":");
		String[] args;
		for(int i = 0; i < lines.length; i++){	
			countLines++;
			args = reader.loadLine(lines[i]).split(" |;");
			if(!addEvent(args)){
				errorCounter ++;
				System.err.println("DialogInterpreter: Error in Line: " + (lines[i]+1) + " Invalid Argument");
			}
					
		}
		System.out.println("DialogInterpreter: tried to create " + lines.length + " Events - failed: " + errorCounter);
	}
	
	private void addTransitions() {
		int[] lines = reader.getPrefixLinePositions(DialogPrefixes.addTransitionTracker + ":");
		String[] args;
		for(int i = 0; i < lines.length; i++){
			countLines++;
			args = reader.loadLine(lines[i]).split(" |;");	
			if(args.length >= 3){
				if(!syma.getDialogManager().getDialog(creature).addTransitionTracker(args[1], args[2])){
					errorCounter ++;
					System.err.println("DialogInterpreter: Error in Line: " + (lines[i]+1) + " of " + filePath + " unvalid TextName");
				}
			}else{
				errorCounter ++;
				System.err.println("DialogInterpreter: Error in Line: " + (lines[i]+1) + " of " + filePath + " not enough arguments");
			}
		}	
		
		lines = reader.getPrefixLinePositions(DialogPrefixes.addTransition + ":");
		for(int i = 0; i < lines.length; i++){
			countLines++;
			args = reader.loadLine(lines[i]).split(" |;");	
			if(args.length >= 4){
				if(!syma.getDialogManager().getDialog(creature).addTransition(args[1], getEvent(args[2]), args[3])){
					errorCounter ++;
					System.err.println("DialogInterpreter: Error in Line: " + (lines[i]+1) + " of " + filePath + " unvalid TextName");
				}
			}else{
				errorCounter ++;
				System.err.println("DialogInterpreter: Error in Line: " + (lines[i]+1) + " of " + filePath + " not enough arguments");
			}
		}
	}
	
	private void addOptions(){
		int[] lines = reader.getPrefixLinePositions(DialogPrefixes.addOption + ":");
		String[] args; 
		for(int i = 0; i < lines.length; i++){
			countLines++;
			String[] parts = reader.loadLine(lines[i]).split("\"");
			args = parts[2].split(" |;");
			if(args.length >= 2){
				if(!syma.getDialogManager().getDialog(creature).addOption(parts[1], args[1], args[2])){
					errorCounter ++;
					System.err.println("DialogInterpreter: Error in Line: " + (lines[i]+1) + " of " + filePath + " unvalid arguments");
				}
			}else{
				errorCounter ++;
				System.err.println("DialogInterpreter: Error in Line: " + (lines[i]+1) + " of " + filePath + " not enough arguments");
			}
		}
	}
	
	private void setParameters() {
		int[] lines = reader.getPrefixLinePositions(DialogPrefixes.setBye + ":");
		String[] args; 
		for(int i = 0; i < lines.length; i++){
			countLines++;
			args = reader.loadLine(lines[i]).split(" |;");
			if(args.length >= 2){
				if(!syma.getDialogManager().getDialog(creature).setTextBye(args[1], true)){
					errorCounter ++;
					System.err.println("DialogInterpreter: Error in Line: " + (lines[i]+1) + " of " + filePath + " unknown Text");
				}
			}else{
				errorCounter ++;
				System.err.println("DialogInterpreter: Error in Line: " + (lines[i]+1) + " of " + filePath + " not enough arguments");
			}
		}	
		
		lines = reader.getPrefixLinePositions(DialogPrefixes.setDialogEntry + ":");
		for(int i = 0; i < lines.length; i++){
			countLines++;
			args = reader.loadLine(lines[i]).split(" |;");
			if(args.length >= 2){
				if(!syma.getDialogManager().getDialog(creature).setReEntryText(args[1])){
					errorCounter ++;
					System.err.println("DialogInterpreter: Error in Line: " + (lines[i]+1) + " of " + filePath + " unknown Text");
				}
			}else{
				errorCounter ++;
				System.err.println("DialogInterpreter: Error in Line: " + (lines[i]+1) + " of " + filePath + " not enough arguments");
			}
		}	
	}
	
	private Event getEvent(String name){
		for(int i = 0; i < watchEvents.size(); i++){
			if(watchEvents.get(i).getName().equals(name)){
				return watchEvents.get(i);
			}
		}
		
		return Event.getEventByName(name);
	}
	
	private boolean addEvent(String[] args){
		try {
			switch(args[1]){
				case "InInventory": {
					watchEvents.add(new EntityHasInInvetory(args[2], args[3], args[4]));
					break;
				}
				
				case "DeathOf": {
					watchEvents.add(new DeathOfEntity(args[2], args[3]));
					break;
				}
				
				case "Pos": {
					watchEvents.add(new EntityGetsToPosition(args[2], args[3], Integer.parseInt(args[4]), Integer.parseInt(args[5])));
					break;
				}
				
				case "Read": {
					watchEvents.add(new TextSeenByPlayer(args[2], args[3], args[4]));
					break;
				}				
				
				default: {
					return false;
				}
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
