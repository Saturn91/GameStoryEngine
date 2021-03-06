package Entites.EntityTypes;

import java.util.ArrayList;

public class Entity {
	private long id;
	private int xPos;
	private int yPos;
	private String name;
	private boolean alive;
	private ArrayList<Entity> inventory = new ArrayList<>();
	private static ArrayList<Entity> entitys = new ArrayList<>();
	protected Status status;
	
	public Entity(String name){
		status = new Status(name);
		this.name = name;
		alive = true;
		id = entitys.size();		
		entitys.add(this);
	}
	
	public void addToInventory(String name){
		inventory.add(entitys.get((int) getIdOff(name)));
	}
	
	public Entity takefromInventory(String name){
		inventory.remove(entitys.get((int) getIdOff(name)));
		return entitys.get((int) getIdOff(name));
	}
	
	public boolean hasinInventory(String name){
		if((int) getIdOff(name) < entitys.size() && (int) getIdOff(name) >= 0){
			for(Entity e: inventory){
				if(inventory.contains(entitys.get((int) getIdOff(name)))){
					return true;
				}
			}
			return false;
		}else{
			System.err.println("Entity: unvalid id!");
			return false;
		}
		
	}
	
	public void kill(){
		alive = false;
	}
	
	public void resurect(){
		alive = true;
	}
	
	public boolean isAlive(){
		return alive;
	}
	
	public String getName(){
		return name;
	}
	
	public int getX(){
		return xPos;
	}
	
	public int getY(){
		return yPos;
	}
	
	public void setPosition(int x, int y){
		xPos = x;
		yPos = y;
	}
	
	public static long getIdOff(String name){
		for(Entity e: entitys){
			if(e.name.equals(name)){
				return e.id;
			}
		}
		System.err.println("Entity: no <" + name + "> found in entities!");
		return -9;
	}
	
	public long getID(){
		return id;
	}
	
	public Status status(){
		return status;
	}
}
