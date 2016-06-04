package Entites;

import java.util.ArrayList;

import GameEventEngine.Dialogs.Dialog.Dialog;

public class Creature extends Entity{
	private ArrayList<Dialog> dialogList = new ArrayList<>();
	private ArrayList<String> dialogNames = new ArrayList<>();
	public Creature(String name, int Level) {
		super(name);
		//add the variables for creatures:
		status.addInteger("Level", Level);
		status.addInteger("Infamie", 50);
	}
	
	public void addDialog(String name){
		if(!dialogNames.contains(name)){
			dialogList.add(new Dialog(this, name));
			dialogNames.add(name);
		}else{
			System.err.println("Creature: <" + name + "> is already used as Dialogname in " + getName());
		}
		
	}
	
	public Dialog getDialog(String name){
		if(dialogNames.contains(name)){
			return dialogList.get(dialogNames.indexOf(name));
		}else{
			System.err.println("Creature: no Dialog: <" + name + "> for "+ getName());
			return null;
		}
	}

}
