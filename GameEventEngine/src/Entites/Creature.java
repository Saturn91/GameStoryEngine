package Entites;

import java.util.ArrayList;

import GameEventEngine.Dialogs.Dialog.Dialog;

public class Creature extends Entity{
	private Dialog dialog;
	public Creature(String name, int Level) {
		super(name);
		//add the variables for creatures:
		status.addInteger("Level", Level);
		status.addInteger("Infamie", 50);
	}
	
	public void addDialog(){
		dialog = new Dialog();		
	}
	
	public Dialog getDialog(){
		if(dialog != null){
			return dialog;
		}else{
			System.err.println("Creature: no Dialog for "+ getName() + " yet");
			return null;
		}
	}

}
