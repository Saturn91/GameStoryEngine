package StoryController;

import Entites.EntityTypes.Entity;
import Entity.Main.EntityManager;
import GameEventEngine.StoryManager;
import Interpreter.StoryInterpreter;

public class StoryController {
	private StoryInterpreter stin;
	private StoryManager syma;
	
	public StoryController(String filePath) {
		stin = new StoryInterpreter(filePath);
		syma = stin.compile();
	}
	
	public Entity getEntity(String name){
		return EntityManager.getEntity(name);
	}
	
	public void debug(){
		syma.printStatus();
		syma.printStatusActiveEvents();
	}
	
	private long lastTime = 0;
	private long nowTime;
	private long updateTime = 500;
	
	public void tick(){
		if(lastTime == 0){
			nowTime = System.currentTimeMillis();
			lastTime = System.currentTimeMillis();
		}else{
			nowTime = System.currentTimeMillis();
		}
		
		if(nowTime - lastTime >= updateTime){
			syma.update();
		}		
	}
	
	public void openDialog(String creatureName){
		syma.getDialogManager().openDialog(creatureName);
	}
	
	public void getNextDialogText(){
		syma.getDialogManager().getNextText();
	}
	
	public void chooseDialogOption(String option){
		syma.getDialogManager().choosOption(option);
	}
	
	public void closeDialog(){
		syma.getDialogManager().closeDialog();
	}
	
	public boolean isDialogOpen(){
		return syma.getDialogManager().isDialogOpen();
	}
	
	public String getOpenDialogText(){
		return syma.getDialogManager().getOpenDialogText();
	}
	
	public String[] getOptions(){
		return syma.getDialogManager().getOptions();
	}
	
	public void setUpdateTime(long updateTime){
		this.updateTime = updateTime;
	}	
}
