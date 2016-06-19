package Interpreter.Room;

import GameEventEngine.StoryManager;
import GameEventEngine.Events.Event.Event;
import Interpreter.EventInterpreter;
import SaveSystem.SaveSystem;

public class RoomInterpreter {
	private SaveSystem reader;
	private String roomName;
	private String filePath;
	private StoryManager syma;
	private int errorCounter;
	private int countLines;
	
	public RoomInterpreter(String roomName, String filePath) {
		reader = new SaveSystem(filePath, "rm");
		this.filePath = filePath;
		this.roomName = roomName;
	}
	
	public boolean isValidPath(){
		return reader.readFile(filePath);
	}
	
	public StoryManager compileTo(StoryManager syma){
		this.syma = syma;
		countLines = 0;
		errorCounter = 0;
		reader.readFile(filePath);
		addExit();
		addDescriptions();
		addDescriptionStoryEventIF();
		addDescriptionWatchEventIF();
		System.out.println("DialogInterpreter: tried to compile " + countLines + " Commands for " + roomName + " - failed: " + errorCounter);		
		return this.syma;
	}

	private void addExit() {
		int[] lines = reader.getPrefixLinePositions(RoomPrefixes.addExit + ":");
		String[] args;
		for(int i = 0; i < lines.length; i++){
			countLines++;
			args = reader.loadLine(lines[i]).split(" |;");			
			if(args.length >= 3){
				try {
					if(!syma.getRoomManager().addExit(roomName, args[1], Integer.parseInt(args[2]))){
						errorCounter ++;
						System.err.println("RoomInterpreter: Error in Line: " + (lines[i]+1) + " of " + filePath + " unvalid RoomName <" + args[1] + ">");
					}else{
						if(args[1].equals(roomName)){
							System.out.println("RoomInterpreter: [Warning] in Line: " + (lines[i]+1) + " of " + filePath + " room exit should not point back at it self");
						}
					}
					
				} catch (Exception e) {
					errorCounter ++;
					System.err.println("RoomInterpreter: Error in Line: " + (lines[i]+1) + " of " + filePath + " args[2] <" + args[2] + "> might not be a number!");
				}
				
			}else{
				errorCounter ++;
				System.err.println("RoomInterpreter: Error in Line: " + (lines[i]+1) + " need at least 2 argument");
			}
		}
	}
	
	private void addDescriptions() {
		int[] lines = reader.getPrefixLinePositions(RoomPrefixes.addDescription + ":");
		String[] args;
		String[] cmds;
		for(int i = 0; i < lines.length; i++){
			countLines++;
			cmds = reader.loadLine(lines[i]).split("\"|;");	
			args = cmds[0].split(" ");
			if(args.length >= 2){
					if(!syma.getRoomManager().addDescription(roomName, args[1], cmds[1])){
						errorCounter ++;
						System.err.println("RoomInterpreter: Error in Line: " + (lines[i]+1) + " of " + filePath + " see details in RoomManager");
					}				
			}else{
				errorCounter ++;
				System.err.println("RoomInterpreter: Error in Line: " + (lines[i]+1) + " need at least 2 argument");
			}
		}		
	}	
	
	private void addDescriptionWatchEventIF() {
		int[] lines = reader.getPrefixLinePositions(RoomPrefixes.addDescriptionWatchEventIF + ":");
		String[] args;
		for(int i = 0; i < lines.length; i++){	
			countLines++;
			args = reader.loadLine(lines[i]).split(" |;");
			Event event = EventInterpreter.addEvent(args, 2);
			if(event == null){
				errorCounter ++;
				System.err.println("RoomInterpreter: Error in Line: " + (lines[i]+1) + " Invalid Argument");
			}else{
				boolean error1 = false;
				if(args[2].equals("true")){
					if(!syma.getRoomManager().addDescriptionWatchEventIF(roomName, args[1], event, true)){
						error1 = true;
					}
				}else{
					if(args[2].equals("false")){
						if(!syma.getRoomManager().addDescriptionWatchEventIF(roomName, args[1], event, false)){
							error1 = true;
						}
					}
						
				}
				
				if(error1){
					errorCounter ++;
					System.err.println("RoomInterpreter: Error in Line: " + (lines[i]+1) + " Invalid Argument <" + args[2] + "> must be true or false");
				}
				
			}
					
		}
		System.out.println("DialogInterpreter: tried to create " + lines.length + " Events - failed: " + errorCounter);
	}

	private void addDescriptionStoryEventIF() {
		int[] lines = reader.getPrefixLinePositions(RoomPrefixes.addDescriptionStoryEventIF + ":");
		String[] args;
		for(int i = 0; i < lines.length; i++){
			countLines++;
			args = reader.loadLine(lines[i]).split(" |;");			
			if(args.length >= 3){
				if(args[3].equals("true")){
					if(!syma.getRoomManager().addDescriptionStoryEventIF(roomName, args[1], args[2], true)){
						errorCounter ++;
						System.err.println("RoomInterpreter: Error in Line: " + (lines[i]+1) + " of " + filePath + " see details in RoomManager");
					}
				}else{
					if(args[3].equals("false")){
						if(!syma.getRoomManager().addDescriptionStoryEventIF(roomName, args[1], args[2], false)){
							errorCounter ++;
							System.err.println("RoomInterpreter: Error in Line: " + (lines[i]+1) + " of " + filePath + " see details in RoomManager");
						}
					}else{
						errorCounter ++;
						System.err.println("RoomInterpreter: Error in Line: " + (lines[i]+1) + " of " + filePath + " argument 3 <" + args[3]+ "> must be true/false");
					}
				}
									
			}else{
				errorCounter ++;
				System.err.println("RoomInterpreter: Error in Line: " + (lines[i]+1) + " need at least 2 argument");
			}
		}		
	}
}