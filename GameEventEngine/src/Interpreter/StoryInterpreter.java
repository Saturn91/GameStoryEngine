package Interpreter;

import Entites.EntityTypes.Creature;
import Entites.EntityTypes.Thing;
import Entity.Main.EntityManager;
import GameEventEngine.StoryManager;
import GameEventEngine.Events.Event.Event;
import GameEventEngine.Events.Main.EventManager;
import SaveSystem.SaveSystem;

public class StoryInterpreter {
	
	private String pathFile;
	private StoryManager syma;
	private SaveSystem reader;
	private DialogInterpreter diin;
	private int errorCounter;
	
	public StoryInterpreter(String filePath) {
		this.pathFile = filePath;
		this.syma = new StoryManager();
		this.reader = new SaveSystem(filePath, "story");
	}
	
	public StoryManager compile(){
		buildStoryManager();
		return syma;
	}

	private void buildStoryManager() {
		errorCounter = 0;
		reader.readFile(pathFile);
		buildEntities();
		buildEvents();
		buildActions();
		buildDialog();
	}

	private void buildEntities(){
		int[] lines = reader.getPrefixLinePositions(InterpreterPrefixes.addCreature + ":");
		String[] args;
		for(int i = 0; i < lines.length; i++){			
			args = reader.loadLine(lines[i]).split(" |;");
			try {
				syma.getEntityManager().addEntity(new Creature(args[1], Integer.parseInt(args[2])));
			} catch (Exception e) {
				errorCounter ++;
				System.err.println("StoryInterpreter: Error in Line: " + (lines[i]+1) + ": <" + args[2] + "> must be a Number");
			}			
		}
		
		System.out.println("StoryInterpreter: tried to create " + lines.length + " Creatures - failed: " + errorCounter);
		
		lines = reader.getPrefixLinePositions(InterpreterPrefixes.addThing + ":");
		for(int i = 0; i < lines.length; i++){
			args = reader.loadLine(lines[i]).split(" |;");
			try {
				syma.getEntityManager().addEntity(new Thing(args[1]));
			} catch (Exception e) {
				errorCounter ++;
				System.err.println("StoryInterpreter: Error in Line: " + (lines[i]+1) + " Expected an argument!");
			}
		}
		
		System.out.println("StoryInterpreter: tried to create " + lines.length + " Things - failed: " + errorCounter);
	}
	
	private void buildEvents() {
		int[] lines = reader.getPrefixLinePositions(InterpreterPrefixes.addEvent + ":");
		String[] args;
		for(int i = 0; i < lines.length; i++){			
			args = reader.loadLine(lines[i]).split(" |;");
			if(!addEvent(args)){
				errorCounter ++;
				System.err.println("StoryInterpreter: Error in Line: " + (lines[i]+1) + " Invalid Argument");
			}
					
		}
		
		System.out.println("StoryInterpreter: tried to create " + lines.length + " Events - failed: " + errorCounter);
		
		lines = reader.getPrefixLinePositions(InterpreterPrefixes.addEventBefore + ":");
		for(int i = 0; i < lines.length; i++){			
			args = reader.loadLine(lines[i]).split(" |;");
			if(Event.getEventByName(args[1]) != null && Event.getEventByName(args[2]) != null){
				syma.getEventManager().addEventBefore(args[1], Event.getEventByName(args[2]));
			}else{
				errorCounter ++;
				System.err.println("StoryInterpreter: Error in Line: " + (lines[i]+1) + " undefined Events");
			}					
		}
		
		System.out.println("StoryInterpreter: tried to create " + lines.length + " Eventtransistions - failed: " + errorCounter);
	
		lines = reader.getPrefixLinePositions(InterpreterPrefixes.addEventBeforeOR + ":");
		for(int i = 0; i < lines.length; i++){			
			args = reader.loadLine(lines[i]).split(" |;");
			if(Event.getEventByName(args[1]) != null && Event.getEventByName(args[2]) != null){
				try {
					syma.getEventManager().addEventBeforeOR(args[1], Event.getEventByName(args[2]), Integer.parseInt(args[3]));
				} catch (Exception e) {
					errorCounter ++;
					System.err.println("StoryInterpreter: Error in Line: " + (lines[i]+1) +" : <"+ args[3] + "> has to be an Integer!" );
				}
			}else{
				errorCounter ++;
				System.err.println("StoryInterpreter: Error in Line: " + (lines[i]+1) + " undefined Events");
			}					
		}
		
		System.out.println("StoryInterpreter: tried to create " + lines.length + " Event-OR-transistions - failed: " + errorCounter);
	
	}
	
	private void buildActions() {
		int[] lines = reader.getPrefixLinePositions(InterpreterPrefixes.addAction + ":");
		String[] args;
		for(int i = 0; i < lines.length; i++){			
			args = reader.loadLine(lines[i]).split(" |;");
			if(!addAction(args)){
				errorCounter ++;
				System.err.println("StoryInterpreter: Error in Line: " + (lines[i]+1) + " Invalid Argument");
			}
					
		}
		
		System.out.println("StoryInterpreter: tried to create " + lines.length + " Actions - failed: " + errorCounter);
	}
	
	private void buildDialog(){
		int[] lines = reader.getPrefixLinePositions(InterpreterPrefixes.addDialog + ":");
		String[] args;
		for(int i = 0; i < lines.length; i++){			
			args = reader.loadLine(lines[i]).split(" |;");
			if(EntityManager.getEntity(args[1]) != null){
				try {
					Creature creature = (Creature) EntityManager.getEntity(args[1]);
					diin = new DialogInterpreter(args[1], args[2]);
					if(diin.isValidPath()){
						creature.addDialog();
						dialogInterpreter();
					}else{
						errorCounter ++;
						System.err.println("StoryInterpreter: Error in Line: " + (lines[i]+1) + " unvalid Path");
					}					
				} catch (Exception e) {
					errorCounter ++;
					System.err.println("StoryInterpreter: Error in Line: " + (lines[i]+1) + " Error while compiling Dialog for " + args[1]);
				}
				
			}else{
				errorCounter ++;
				System.err.println("StoryInterpreter: Error in Line: " + (lines[i]+1) + " unknown Entity");
			}
					
		}
		
		System.out.println("StoryInterpreter: tried to create " + lines.length + " Dialogs - failed: " + errorCounter);
	}
	
	private void dialogInterpreter() {
		syma = diin.compileTo(syma);		
	}

	private boolean addEvent(String[] args){
		try {
			switch(args[1]){
				case "InInventory": {
					syma.getEventManager().addHasInInventory(args[2], args[3], args[4]);
					break;
				}
				
				case "DeathOf": {
					syma.getEventManager().addDeathOfEntity(args[2], args[3]);
					break;
				}
				
				case "Pos": {
					syma.getEventManager().addEntityGetsToPosition(args[2], args[3], Integer.parseInt(args[4]), Integer.parseInt(args[5]));
					break;
				}
				
				case "Read": {
					syma.getEventManager().addTextRead(args[2], args[3], args[4]);
					break;
				}
				
				case "isStatusI":{
					try {
						Integer.parseInt(args[5]);
						syma.getEventManager().addIsStatus(args[2], args[3], args[4], Integer.parseInt(args[5]));
					} catch (Exception e) {
						System.err.println("StoryInterpreter: args[5]: <" + args[5] + "> has to be an Integer");
					}
				}
				
				case "isStatusS":{
					syma.getEventManager().addIsStatus(args[2], args[3], args[4], args[5]);
				}
				
				case "isStatusB":{
					if(args[5].equals("true")){
						syma.getEventManager().addIsStatus(args[2], args[3], args[4], true);
					}else{
						if(args[5].equals("false")){
							syma.getEventManager().addIsStatus(args[2], args[3], args[4], false);
						}else{
							System.err.println("StoryInterpreter: args[5]: <" + args[5] + "> has to be a Boolean (true / false");
						}
					}					
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
	
	private boolean addAction(String[] args){
		try {
			switch(args[1]){
				case "Give": {
					syma.getActionManager().addGive(args[2], args[3], Event.getEventByName(args[4]));
					break;
				}
				
				case "Take": {
					syma.getActionManager().addTake(args[2], args[3], Event.getEventByName(args[4]));
					break;
				}
				
				case "Kill": {
					syma.getActionManager().addKill(args[2], Event.getEventByName(args[3]));
					break;
				}
				
				case "setReEntry": {
					syma.getActionManager().addSetReyEntryPointIf(args[2], args[3], Event.getEventByName(args[4]));
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
