package GameEventEngine.Dialogs.Main;

import java.util.ArrayList;

import Entites.Creature;
import GameEventEngine.Dialogs.Dialog.Dialog;

public class DialogManager {
	private Dialog openDialog;
	
	public void addDialog(Creature creature, String name){
		creature.addDialog(name);
	}
	
	public Dialog getDialog(Creature creature, String name){
		return creature.getDialog(name);
	}
	
	
	
//**********************************Handle Dialog Methodes *************************************
	public void openDialog(Creature creature, String name){
		if(creature.isAlive()){
			openDialog = creature.getDialog(name);
			openDialog.update();
			openDialog.open();
		}else{
			System.err.println("DialogManager : can not speak to dead " + creature.getName());
		}
	}
	
	public void closeDialog(){
		openDialog = null;
	}
	
	public String getOpenDialogText(){
		if(openDialog != null){
			//print...
			openDialog.update();
			return openDialog.getTextString();
		}else{
			System.err.println("no open dialog!");
		}
		return "...";
	}
	
	public String[] getOptions(){
		if(openDialog != null){
			return openDialog.getOptionString();
		}else{
			return new String[0];
		}
	}
	
	public void choosOption(String optionName){
		openDialog.chooseOption(optionName);
	}	
}
