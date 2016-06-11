package Interpreter;

import GameEventEngine.StoryManager;
import SaveSystem.SaveSystem;

public class StoryInterpreter {
	
	private String pathFile;
	private StoryManager syma;
	private SaveSystem reader;
	
	public StoryInterpreter(String filePath) {
		this.pathFile = filePath;
		this.syma = new StoryManager();
		this.reader = new SaveSystem(filePath, ".story");
	}
	
	public StoryManager compile(){
		buildStoryManager();
		return null;
	}

	private void buildStoryManager() {
		//TODO build story out of Textfile
		
	}
}
