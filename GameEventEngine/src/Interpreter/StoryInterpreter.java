package Interpreter;

import Entites.EntityTypes.Creature;
import Entites.EntityTypes.Thing;
import GameEventEngine.StoryManager;
import SaveSystem.SaveSystem;

public class StoryInterpreter {
	
	private String pathFile;
	private StoryManager syma;
	private SaveSystem reader;
	
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
				System.err.println("StoryInterpreter: Error in Line: " + lines[i] + ": <" + args[2] + "> must be a Number");
			}
		}
		
		System.out.println("StoryInterpreter: created " + lines.length + " Creatures");
		
		lines = reader.getPrefixLinePositions(InterpretorPrefixes.addThing + ":");
		for(int i = 0; i < lines.length; i++){
			args = reader.loadLine(lines[i]).split(" |;");
			try {
				syma.getEntityManager().addEntity(new Thing(args[1]));
			} catch (Exception e) {
				System.err.println("StoryInterpreter: Error in Line: " + lines[i] + " Expected an argument!");
			}
		}
		
		System.out.println("StoryInterpreter: created " + lines.length + " Things");
	}
	
	private void buildEvents() {
		
	}
}
