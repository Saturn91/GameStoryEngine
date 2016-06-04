package Entites;

import java.util.ArrayList;

public class Status {
	private String entityName;
	private ArrayList<Integer> integers = new ArrayList<>();
	private ArrayList<String>  integerNames = new ArrayList<>();
	private ArrayList<Boolean> booleans = new ArrayList<>();
	private ArrayList<String>  booleanNames = new ArrayList<>();
	private ArrayList<String>  strings = new ArrayList<>();
	private ArrayList<String>  stringNames = new ArrayList<>();
	
	public Status(String entityName){
		this.entityName = entityName;
	}
	
	public void addInteger(String name, int value){
		if(!integerNames.contains(name)){
			integers.add(value);
			integerNames.add(name);
		}else{
			System.err.println("Status: there is already a Integer named: " + name);
		}
	}
	
	public void addBoolean(String name, boolean value){
		if(!booleanNames.contains(name)){
			booleans.add(value);
			booleanNames.add(name);
		}else{
			System.err.println("Status: there is already a Boolean named: " + name);
		}
	}
	
	public void addString(String name, String value){
		if(!stringNames.contains(name)){
			strings.add(value);
			stringNames.add(name);
		}else{
			System.err.println("Status: there is already a String named: " + name);
		}
	}
	
	public void set(String name, int value){
		if(integerNames.contains(name)){
			integers.set(integerNames.indexOf(name), value);
		}else{
			System.err.println("Status: <" + name + "> isnot a valid integer name!" );
		}
	}
	
	public void set(String name, boolean value){
		if(booleanNames.contains(name)){
			booleans.set(booleanNames.indexOf(name), value);
		}else{
			System.err.println("Status: <" + name + "> isnot a valid boolean name!" );
		}
	}
	
	public void set(String name, String value){
		if(stringNames.contains(name)){
			strings.set(stringNames.indexOf(name), value);
		}else{
			System.err.println("Status: <" + name + "> isnot a valid String name!" );
		}
	}
	
	public int getI(String name){
		if(integerNames.contains(name)){
			return integers.get(integerNames.indexOf(name));
		}else{
			System.err.println("Status: <" + name + "> isnot a valid integer name!" );
			System.err.println("returned -99999" );
			return -99999;
		}
	}
	
	public boolean getB(String name){
		if(booleanNames.contains(name)){
			return booleans.get(booleanNames.indexOf(name));
		}else{
			System.err.println("Status: <" + name + "> isnot a valid boolean name!" );
			System.err.println("returned false" );
			return false;
		}
	}
	
	public String getS(String name){
		if(stringNames.contains(name)){
			return strings.get(stringNames.indexOf(name));
		}else{
			System.err.println("Status: <" + name + "> isnot a valid string name!" );
			System.err.println("returned null" );
			return null;
		}
	}
	
	public enum Type{
		BOOL, INT, STRING;
	}
	
	public void debug(){
		System.out.println("============================Debug: " + entityName + "============================");
		System.out.println("=========Integers=========");
		for(int i = 0; i < integers.size(); i++){
			System.out.println(integerNames.get(i) + ":   " + integers.get(i));
		}
		
		System.out.println("=========Booleans=========");
		for(int i = 0; i < booleans.size(); i++){
			System.out.println(booleanNames.get(i) + ":   " + booleans.get(i));
		}
		
		System.out.println("=========Strings=========");
		for(int i = 0; i < booleans.size(); i++){
			System.out.println(stringNames.get(i) + ":   " + strings.get(i));
		}
		System.out.println("============================Debug: " + entityName + "/END========================");
		
	}
}
