package Interpreter.Room;

import GameEventEngine.StoryManager;
import GameEventEngine.Events.Event.Event;
import Interpreter.InterpreterPrefixes;
import Interpreter.Dialog.EventInterpreter;
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
						if(!args[1].equals(roomName)){
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
		for(int i = 0; i < lines.length; i++){
			countLines++;
			args = reader.loadLine(lines[i]).split(" |;");			
			if(args.length >= 3){
					if(!syma.getRoomManager().addDescription(roomName, args[1], args[2])){
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
			Event event = EventInterpreter.addEvent(args, 3);
			if(event == null){
				errorCounter ++;
				System.err.println("DialogInterpreter: Error in Line: " + (lines[i]+1) + " Invalid Argument");
			}else{
				boolean error1 = false;
				boolean error2 = false;
				if(args[2].equals("true")){
					if(!syma.getRoomManager().addDescriptionWatchEventIF(roomName, args[1], event, true)){
						error1 = true;
					}else{
						error2 = true;
					}
				}else{
					if(args[2].equals("false")){
						if(!syma.getRoomManager().addDescriptionWatchEventIF(roomName, args[1], event, false)){
							error1 = true;
						}else{
							error2 = true;
						}
					}
						
				}
				
				if(error1){
					errorCounter ++;
					System.err.println("DialogInterpreter: Error in Line: " + (lines[i]+1) + " Invalid Argument <" + args[2] + "> must be true or false");
				}
				
				if(error2){
					errorCounter ++;
					System.err.println("DialogInterpreter: Error in Line: " + (lines[i]+1) + " Invalid Argument <" + args[2] + "> wrong argument");
				}
				
			}
					
		}
		System.out.println("DialogInterpreter: tried to create " + lines.length + " Events - failed: " + errorCounter);
	}

	private void addDescriptionStoryEventIF() {
		// TODO Auto-generated method stub
		
	}
}