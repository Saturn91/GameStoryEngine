package main;

import Entites.Creature;
import GameEventEngine.StoryManager;
import GameEventEngine.Dialogs.Main.DialogManager;
import GameEventEngine.Events.EventTypes.DeathOfEntity;

public class TestEnvirement {
	public static void main(String[] args) {
		Creature msTipten = new Creature("Creature", 10);
		Creature mrButler = new Creature("Mr. Butler", 10);
		StoryManager syma = new StoryManager();
		syma.getDialogManager().addDialog(msTipten, "Dia1");
		syma.getDialogManager().getDialog(msTipten, "Dia1").addText("START", "Hi Mr. Bond nice to see you here, are you enjoing the Party?");
		syma.getDialogManager().getDialog(msTipten, "Dia1").addText("O1", "Oh, what a pitty i didn't know that!");
		syma.getDialogManager().getDialog(msTipten, "Dia1").addText("O2", "Yeah! Thats true, its fantastic here!");
		syma.getDialogManager().getDialog(msTipten, "Dia1").addText("T2", "I will never Speak with you again!");
		syma.getDialogManager().getDialog(msTipten, "Dia1").addText("T3", "I Think Mr. Butler is a very Handful Man, what about you?");
		syma.getDialogManager().getDialog(msTipten, "Dia1").setReEntryText("START");
		
		syma.getDialogManager().getDialog(msTipten, "Dia1").addOption("No, i'm quite ill!", "START", "O1");
		syma.getDialogManager().getDialog(msTipten, "Dia1").addOption("Yes", "START", "O2");
		
		syma.getDialogManager().getDialog(msTipten, "Dia1").addTransitionTracker("O2", "T3");
		syma.getDialogManager().getDialog(msTipten, "Dia1").addTransition("O2", new DeathOfEntity("killed Mr. Bond", mrButler), "T2");
		
	//open Dialog
		syma.getDialogManager().openDialog(msTipten, "Dia1");
		print(syma.getDialogManager());
		
		syma.getDialogManager().choosOption("Yes");
		
		print(syma.getDialogManager());
		
		mrButler.kill();
		syma.getDialogManager().getNextText();
		
		print(syma.getDialogManager());
	}
	
	public static void print(DialogManager dima){
		System.out.println(dima.getOpenDialogText());
		if(dima.getOptions().length > 0){
			for(int i = 0; i < dima.getOptions().length; i++){
				System.out.println(i + ": " + dima.getOptions()[i]);
			}
		}
	}
}
