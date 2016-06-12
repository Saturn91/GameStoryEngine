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
		
		//setTransitions
		syma.getEventManager().addEventBefore("E1", Event.getEventByName(Event.START_TAG));
		syma.getEventManager().addEventBefore("E2", Event.getEventByName(Event.START_TAG));
		syma.getEventManager().addEventBefore("E3", Event.getEventByName("E1"));
		syma.getEventManager().addEventBefore("E4", Event.getEventByName("E3"));
		syma.getEventManager().addEventBefore("E4", Event.getEventByName("E2"));
		syma.getEventManager().addEventBefore("E5", Event.getEventByName("E4"));
		
		//Dialog
		syma.getDialogManager().addDialog("Lady");
		syma.getDialogManager().getDialog("Lady").addText("T1", "Hi How are you?");
		syma.getDialogManager().getDialog("Lady").addText("T2", "Kill the watchman!");
		syma.getDialogManager().getDialog("Lady").addText("T3", "Oh thank you for killing the Watchman, want my Ring?");
		syma.getDialogManager().getDialog("Lady").addText("T4", "Here you go");
		syma.getDialogManager().getDialog("Lady").addText("T5", "well then farewell!");
		
		//Transitions
		syma.getDialogManager().getDialog("Lady").addTransitionTracker("T1", "T2");
		syma.getDialogManager().getDialog("Lady").addTransition("T1", new DeathOfEntity("E6", "Watchman"), "T3");
		
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
