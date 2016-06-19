

import Entity.Main.EntityManager;
import StoryController.StoryController;

public class TestEnviroment {
	private static StoryController sC;
	public static void main(String[] args) {
		sC = new StoryController("./res/Story");
		sC.setUpdateTime(0);
	//RUN
		sC.debug();
		debugStateMachine();
		EntityManager.getEntity("Player").addToInventory("Dagger");
		debugStateMachine();
		EntityManager.getEntity("Player").addToInventory("Key");
		debugStateMachine();
		EntityManager.getEntity("Watchman").kill();
		debugStateMachine();
		System.out.println("-----------------player has Sword in inventory: " + EntityManager.getEntity("Player").hasinInventory("Sword"));
		EntityManager.getEntity("Player").setPosition(10, 10);
		debugStateMachine();
		System.out.println("-----------------player has Sword in inventory: " + EntityManager.getEntity("Player").hasinInventory("Sword"));
		sC.openDialog("Lady");
		debugDialog();
		sC.getNextDialogText();
		debugDialog();
		sC.chooseDialogOption("No");
		debugDialog();
		sC.closeDialog(); 
		System.out.println("-----------------player has Ring in inventory: " + EntityManager.getEntity("Player").hasinInventory("Ring"));
		sC.openDialog("Lady");
		debugDialog();
		sC.getNextDialogText();
		debugDialog();
		sC.chooseDialogOption("Yes");
		debugDialog();
		sC.getNextDialogText();
		debugDialog();
		debugStateMachine();
		System.out.println("-----------------player has Ring in inventory: " + EntityManager.getEntity("Player").hasinInventory("Ring"));
	}
	
	public static void debugDialog(){
		if(sC.isDialogOpen()){
			System.out.println("==================Dialog: =============================");
			System.out.println(sC.getOpenDialogText());	//--
			if(sC.getOptions().length > 0){
				System.out.println("==================Option: =============================");
				for(int i = 0; i < sC.getOptions().length; i++){
					System.out.println(i + ": " + sC.getOptions()[i]);
				}
			}
			System.out.println("=======================================================");
		}else{
			System.out.println("no open Dialog!");
		}
	}
	
	public static void debugStateMachine(){
		sC.tick();
		sC.debug();
	}
}
