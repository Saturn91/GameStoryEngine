package Interpreter.Room;

import GameEventEngine.StoryManager;
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
		setStartRoom();
		System.out.println("DialogInterpreter: tried to compile " + countLines + " Commands for " + roomName + " - failed: " + errorCounter);		
		return this.syma;
	}
	
	private void setStartRoom() {
		int[] lines = reader.getPrefixLinePositions(RoomPrefixes.setStartRoom + ":");
		String[] args;
		for(int i = 0; i < lines.length; i++){
			countLines++;
			args = reader.loadLine(lines[i]).split(" |;");			
			if(args.length >= 2){
				if(!syma.getRoomManager().setStartRoom(args[2])){
					errorCounter ++;
					System.err.println("RoomInterpreter: Error in Line: " + (lines[i]+1) + " of " + filePath + " unvalid RoomName");
				}
			}else{
				errorCounter ++;
				System.err.println("RoomInterpreter: Error in Line: " + (lines[i]+1) + " need at least 1 argument");
			}
		}
	}
	
}