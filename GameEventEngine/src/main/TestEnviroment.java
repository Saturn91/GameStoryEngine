package main;

import Entites.Creature;
import Entites.Thing;
import GameEventEngine.StoryManager;
import GameEventEngine.Dialogs.Main.DialogManager;
import GameEventEngine.Events.Event.Event;
import GameEventEngine.Events.EventTypes.DeathOfEntity;

public class TestEnviroment {
	private static StoryManager syma;
	public static void main(String[] args) {
		syma = new StoryManager();
		
		//Creatures
		Creature player = new Creature("Player", 10);
		Creature watchMan = new Creature("WatchMan", 15);
		Creature lady = new Creature("Lady", 5);
		
		//Things
		Thing dagger = new Thing("Dagger");
		Thing key = new Thing("Key");
		Thing sword = new Thing("Sword");
		Thing ring = new Thing("Ring");
				
		//Events
		syma.getEventManager().addHasInInventory("E1", player, dagger);
		syma.getEventManager().addHasInInventory("E2", player, key);
		syma.getEventManager().addDeathOfEntity("E3", watchMan);
		syma.getEventManager().addEntityGetsToPosition("E4", player, 10, 10);
		syma.getEventManager().addTextRead("E5", "gr", lady);
		
		//Actions
		syma.getActionManager().addGive(player, sword, Event.getEventByName("E3"));
		syma.getActionManager().addTake(player, sword, Event.getEventByName("E4"));
		syma.getActionManager().addGive(player, ring, Event.getEventByName("E5"));
		
		//setTransitions
		syma.getEventManager().addEventBefore("E1", Event.getStartEvent());
		syma.getEventManager().addEventBefore("E2", Event.getStartEvent());
		syma.getEventManager().addEventBefore("E3", Event.getEventByName("E1"));
		syma.getEventManager().addEventBefore("E4", Event.getEventByName("E3"));
		syma.getEventManager().addEventBefore("E4", Event.getEventByName("E2"));
		syma.getEventManager().addEventBefore("E5", Event.getEventByName("E4"));
		
		//Dialog
		syma.getDialogManager().addDialog(lady);
		syma.getDialogManager().getDialog(lady).addText("T1", "Hi How are you?");
		syma.getDialogManager().getDialog(lady).addText("T2", "Kill the watchman!");
		syma.getDialogManager().getDialog(lady).addText("T3", "Oh thank you for killing the Watchman, want my Ring?");
		syma.getDialogManager().getDialog(lady).addText("T4", "Here you go");
		syma.getDialogManager().getDialog(lady).addText("T5", "well then farewell!");
		
		//Transitions
		syma.getDialogManager().getDialog(lady).addTransitionTracker("T1", "T2");
		//syma.getDialogManager().getDialog(lady).addTransition("T1", Event.getEventByName("E3"), "T3");
		
		//Options
		syma.getDialogManager().getDialog(lady).addOption("Yes", "T3", "T4");
		syma.getDialogManager().getDialog(lady).addOption("No", "T3", "T5");
		syma.getDialogManager().getDialog(lady).setTextBye("T4", true);
		
		syma.getEventManager().printStatus();
		syma.update();
		syma.getEventManager().printStatus();
		
	}
	
	public static void print(){
		if(syma.getDialogManager().isDialogOpen()){
			System.out.println(syma.getDialogManager().getOpenDialogText());
			if(syma.getDialogManager().getOptions().length > 0){
				for(int i = 0; i < syma.getDialogManager().getOptions().length; i++){
					System.out.println(i + ": " + syma.getDialogManager().getOptions()[i]);
				}
			}
		}		
	}
}
