package GameEventEngine.Events.EventTypes;

import DummyGameEntites.Entity;
import DummyGameEntites.Status;
import GameEventEngine.Events.Event.Event;
import GameEventEngine.Events.Event.Event_Status;
import GameEventEngine.Events.Event.Transistion;

public class EntityStatusIs extends Event{
	private Entity entity;
	private String varName;
	private int iValue = 0;
	private boolean bValue = false;
	private String sValue = null;
	private Status.Type type = null;
	
	public EntityStatusIs(String name, Entity entity, String varName, int value) {
		super(name , "gets true if " + entity.getName() + "." + varName + "=" + value);
		this.varName = varName;
		this.iValue = value;
		type = Status.Type.INT;
		this.entity = entity;
		transistion = new Transistion(this);
	}
	
	public EntityStatusIs(String name, Entity entity, String varName, boolean value) {
		super(name , "gets true if " + entity.getName() + "." + varName + "=" + value);
		this.bValue = value;
		type = Status.Type.BOOL;
		this.entity = entity;
		transistion = new Transistion(this);
	}
	
	public EntityStatusIs(String name, Entity entity, String varName, String value) {
		super(name , "gets true if " + entity.getName() + "." + varName + "=" + value);
		this.sValue = value;
		type = Status.Type.STRING;
		this.entity = entity;
		transistion = new Transistion(this);
	}
	
	@Override
	public void update(){
		if(isActive()){
			boolean check = false;
			if(type == Status.Type.INT && entity.status().getI(varName) == iValue){
				check =  true;
			}
			
			if(type == Status.Type.BOOL && entity.status().getB(varName) == bValue){
				check =  true;
			}
			
			if(type == Status.Type.STRING && entity.status().getS(varName) == sValue){
				check =  true;
			}
						
			if(check){
				EntityStatusIs.setEventStatus(name, Event_Status.DONE);
			}
		}
		transistion.update();
	}
	
	

}
