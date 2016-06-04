package main;

import Entites.Creature;
import Entites.Entity;
import Entites.Player;
import Entites.Thing;
import GameEventEngine.StoryManager;
import GameEventEngine.Dialogs.Main.DialogManager;
import GameEventEngine.Events.Event.Event;

public class TestEnvirement {
	private static Player player;
	private static Creature creature;
	private static Thing thing;
	/**
	 * aplication Launcher
	 * @param args
	 */
	public static void main(String[] args){
		String t1 = "asdlökfjlöskdafjlksdafjsdafjlöskdafjsdalökfjlskadfjlsadkjfklsdajflskdafjlskdafjlksdafjlk \nsakdfhjlskdjafhskdjahfskjahfkjsdafhksdjahf";
		String t2 = "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb";
		String t3 = "ccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc";
		
		Creature creature = new Creature("Crea1", 10);
		StoryManager syma = new StoryManager();
		
		syma.getDialogManager().addDialog(creature, "Dia1");
		
		//add Texts
		syma.getDialogManager().getDialog(creature, "Dia1").addText("T1", t1);
		syma.getDialogManager().getDialog(creature, "Dia1").addText("T2", t2);
		syma.getDialogManager().getDialog(creature, "Dia1").addText("T3", t3);
		syma.getDialogManager().getDialog(creature, "Dia1").setReEntryText("T1");
		
		//addOptions
		syma.getDialogManager().getDialog(creature, "Dia1").addOption("Option b", "T1", "T2");
		syma.getDialogManager().getDialog(creature, "Dia1").addOption("Option c", "T1", "T3");
		
		//test
		syma.getDialogManager().openDialog(creature, "Dia1");
		print(syma.getDialogManager());
		syma.getDialogManager().choosOption("Option b");
		print(syma.getDialogManager());
		syma.getDialogManager().closeDialog();
		syma.getDialogManager().openDialog(creature, "Dia1");
		print(syma.getDialogManager());
	}
	
	//TODO delete Test Method!!!
	private static void print(DialogManager dm){
		System.out.println(dm.getOpenDialogText());
		for(int i = 0; i < dm.getOptions().length; i++){
			System.out.println("Option" + i + ":" + dm.getOptions()[i]);
		}
	}
}
