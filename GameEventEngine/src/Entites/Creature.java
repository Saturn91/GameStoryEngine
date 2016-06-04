package Entites;

public class Creature extends Entity{
	public Creature(String name, int Level) {
		super(name);
		
		//add the variables for creatures:
		status.addInteger("Level", Level);
		
	}

}
