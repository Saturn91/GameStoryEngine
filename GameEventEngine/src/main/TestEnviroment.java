package main;

import Entites.EntityTypes.Creature;
import Entites.EntityTypes.Thing;
import Entity.Main.EntityManager;
import GameEventEngine.StoryManager;
import GameEventEngine.Dialogs.Main.DialogManager;
import GameEventEngine.Events.Event.Event;
import GameEventEngine.Events.EventTypes.DeathOfEntity;
import Interpreter.StoryInterpreter;

public class TestEnviroment {
	private static StoryManager syma;
	private static StoryInterpreter stin;
	public static void main(String[] args) {
		stin = new StoryInterpreter("./res/Story");
		syma = stin.compile();
		
		//Options
		syma.getDialogManager().getDialog("Lady").addOption("Yes", "T3", "T4");
		syma.getDialogManager().getDialog("Lady").addOption("No", "T3", "T5");
		syma.getDialogManager().getDialog("Lady").setTextBye("T4", true);
		
		//EntryPoint
		syma.getDialogManager().getDialog("Lady").setReEntryText("T1");
		
	//RUN
		syma.getEventManager().printStatus();
		updatePrint();
		EntityManager.getEntity("Player").addToInventory("Dagger");
		updatePrint();
		EntityManager.getEntity("Player").addToInventory("Key");
		updatePrint();
		EntityManager.getEntity("Watchman").kill();
		updatePrint();
		System.out.println("-----------------player has Sword in inventory: " + EntityManager.getEntity("Player").hasinInventory("Sword"));
		EntityManager.getEntity("Player").setPosition(10, 10);
		updatePrint();
		System.out.println("-----------------player has Sword in inventory: " + EntityManager.getEntity("Player").hasinInventory("Sword"));
		syma.getDialogManager().openDialog("Lady");
		print();
		syma.getDialogManager().getNextText();
		print();
		syma.getDialogManager().choosOption("No");
		print();
		syma.getDialogManager().closeDialog();
		System.out.println("-----------------player has Ring in inventory: " + EntityManager.getEntity("Player").hasinInventory("Ring"));
		syma.getDialogManager().openDialog("Lady");
		print();
		syma.getDialogManager().getNextText();
		print();
		syma.getDialogManager().choosOption("Yes");
		print();
		syma.getDialogManager().getNextText();
		print();
		updatePrint();
		System.out.println("-----------------player has Ring in inventory: " + EntityManager.getEntity("Player").hasinInventory("Ring"));
	}
	
	public static void print(){
		if(syma.getDialogManager().isDialogOpen()){
			System.out.println("==================Dialog: =============================");
			System.out.println(syma.getDialogManager().getOpenDialogText());
			if(syma.getDialogManager().getOptions().length > 0){
				System.out.println("==================Option: =============================");
				for(int i = 0; i < syma.getDialogManager().getOptions().length; i++){
					System.out.println(i + ": " + syma.getDialogManager().getOptions()[i]);
				}
			}
			System.out.println("=======================================================");
		}else{
			System.out.println("no open Dialog!");
		}
	}
	
	public static void updatePrint(){
		syma.update();
		syma.printStatus();
	}
}
