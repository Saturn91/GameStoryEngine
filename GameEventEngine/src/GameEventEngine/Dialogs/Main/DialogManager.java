package GameEventEngine.Dialogs.Main;

import java.util.ArrayList;

import Entites.EntityTypes.Creature;
import Entity.Main.EntityManager;
import GameEventEngine.Dialogs.Dialog.Dialog;

public class DialogManager {
	private Dialog openDialog;
	public void addDialog(String creatureName){
		try {
			((Creature) EntityManager.getEntity(creatureName)).addDialog();
		} catch (Exception e) {
			System.err.println("DialogManager: <" + creatureName + "> is no valid Creaturename");
		}
	}
	
	public Dialog getDialog(String creatureName){
		try {
			return ((Creature) EntityManager.getEntity(creatureName)).getDialog();
		} catch (Exception e) {
			System.err.println("DialogManager: <" + creatureName + "> is no valid Creaturename");
		}
		return 	null;	
	}
	
	
	
//**********************************Handle Dialog Methodes *************************************
	public void openDialog(String creatureName){
		try {
			Creature creature = ((Creature) EntityManager.getEntity(creatureName));
			if(creature.isAlive()){
				openDialog = creature.getDialog();
				openDialog.open();
			}else{
				System.err.println("DialogManager : can not speak to dead " + creature.getName());
			}
		} catch (Exception e) {
			System.err.println("DialogManager: <" + creatureName + "> is no valid Creaturename");
		}
	}
	
	public void closeDialog(){
		openDialog = null;
	}
	
	public String getOpenDialogText(){
		if(openDialog != null){
			//print...
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
		if(isDialogOpen()){
			openDialog.chooseOption(optionName);
		}		
	}
	
	public boolean isDialogOpen(){
		return openDialog !=null;
	}
	
	public void getNextText(){
		if(isDialogOpen() &! openDialog.actualTextIsBye()){
			openDialog.getNextText();
		}else{
			closeDialog();
		}
	}
}
