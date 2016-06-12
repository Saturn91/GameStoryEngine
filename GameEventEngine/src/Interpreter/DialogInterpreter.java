package Interpreter;

import GameEventEngine.StoryManager;
import SaveSystem.SaveSystem;

public class DialogInterpreter {
	private SaveSystem reader;
	private String creature;
	private String filePath;
	private StoryManager syma;
	private int errorCounter;
	private int countLines;
	
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
		
		System.out.println("DialogInterpreter: tried to create " + countLines + " Texts for " + creature + " - failed: " + errorCounter);		
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
}
