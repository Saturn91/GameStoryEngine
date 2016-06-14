package Entites.EntityTypes;

public class Thing extends Entity{
	boolean isContainer = false;
	public Thing(String name) {
		super(name);
	}
	
	public Thing(String name, boolean isContaier) {
		super(name);
	}
	
	@Override
	public void addToInventory(String name) {
		if(isContainer){
			super.addToInventory(name);
		}else{
			System.out.println("you can not store that in NonContainer: " + super.getName());
		}
	}
	
	public Entity takeFromInventory(String name) {
		if(isContainer){
			return super.takefromInventory(name);
		}else{
			System.out.println("you can not get that from NonContainer: " + super.getName());
			return null;
		}
	}
	
	public void setToContainer(boolean isContainer){
		this.isContainer = isContainer;
	}
	
	@Override
	public void setPosition(int x, int y) {
		System.out.println("not able to set position of a Thing!");
	}

}
