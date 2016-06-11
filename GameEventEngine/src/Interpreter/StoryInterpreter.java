package Interpreter;

import Entites.EntityTypes.Creature;
import Entites.EntityTypes.Thing;
import GameEventEngine.StoryManager;
import SaveSystem.SaveSystem;

public class StoryInterpreter {
	
	private String pathFile;
	private StoryManager syma;
	private SaveSystem reader;
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
	}

	private void buildEntities(){
		int[] lines = reader.getPrefixLinePositions(InterpretorPrefixes.addCreature + ":");
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
		
		lines = reader.getPrefixLinePositions(InterpretorPrefixes.addThing + ":");
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
		int[] lines = reader.getPrefixLinePositions(InterpretorPrefixes.addEvent + ":");
		String[] args;
		for(int i = 0; i < lines.length; i++){			
			args = reader.loadLine(lines[i]).split(" |;");
			if(!addEvent(args)){
				errorCounter ++;
				System.err.println("StoryInterpreter: Error in Line: " + (lines[i]+1) + " Invalid Argument");
			}
					
		}
		
		System.out.println("StoryInterpreter: tried to create " + lines.length + " Events - failed: " + errorCounter);
	}
	
	private boolean addEvent(String[] args){
		try {
			switch(args[1]){
				case "inInventory": {
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
