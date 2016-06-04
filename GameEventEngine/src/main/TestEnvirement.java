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
		String t1 = "asdlökfjlöskdafjlksdafjsdafjlöskdafjsdalökfjlskadfjlsadkjfklsdajflskdafjlskdafjlksdafjlk \n sakdfhjlskdjafhskdjahfskjahfkjsdafhksdjahf";
		String t2 = "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb";
		String t3 = "ccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc";
		
		Creature creature = new Creature("Crea1", 10);
		DialogManager dm = new DialogManager();
		
		dm.addDialog(creature, "Dia1");
		
		//add Texts
		dm.getDialog(creature, "Dia1").addText("T1", t1);
		dm.getDialog(creature, "Dia1").addText("T2", t2);
		dm.getDialog(creature, "Dia1").addText("T3", t3);
		dm.getDialog(creature, "Dia1").setReEntryText("T1");
		
		//addOptions
		dm.getDialog(creature, "Dia1").addOption("Option b", "T1", "T2");
		dm.getDialog(creature, "Dia1").addOption("Option c", "T1", "T3");
		
		//test
		dm.openDialog(creature, "Dia1");
		print(dm);
		dm.choosOption("Option b");
		print(dm);
		dm.closeDialog();
		dm.openDialog(creature, "Dia1");
		print(dm);
	}
	
	//TODO delete Test Method!!!
	private static void print(DialogManager dm){
		System.out.println(dm.getOpenDialogText());
		for(int i = 0; i < dm.getOptions().length; i++){
			System.out.println("Option" + i + ":" + dm.getOptions()[i]);
		}
	}
}
