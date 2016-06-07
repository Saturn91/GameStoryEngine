package Entity.Main;

import java.util.ArrayList;

import Entites.EntityTypes.Entity;

public class EntityManager {
	private static ArrayList<Entity> entities;
	
	public EntityManager(){
		entities = new ArrayList<>();
	}
	
	public void addEntity(Entity entity){
		for(Entity e: entities){
			if(e.getName().equals(entity.getName())){
				System.err.println("EntityManager: Entity <" + entity.getName() + "> does already exists");
			}
		}
		entities.add(entity);
	}
	
	public static Entity getEntity(String name){
		for(Entity e: entities){
			if(e.getName().equals(name)){
				return e;
			}
		}
		System.err.println("EntityManager: no entity <" + name + ">");
		return null;
	}
}
