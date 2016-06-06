package GameEventEngine.Dialogs.Main;

import java.util.ArrayList;

import Entites.Creature;
import GameEventEngine.Dialogs.Dialog.Dialog;

public class DialogManager {
	private Dialog openDialog;
	
	public void addDialog(Creature creature){
		creature.addDialog();
	}
	
	public Dialog getDialog(Creature creature){
		return creature.getDialog();
	}
	
	
	
//**********************************Handle Dialog Methodes *************************************
	public void openDialog(Creature creature){
		if(creature.isAlive()){
			openDialog = creature.getDialog();
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
