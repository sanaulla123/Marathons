package net.javabeat;

public class Location {
	
	public Location(String name){
		this.name = name;
	}
	
	String name;
	double longitude;
	double latitude;
	
	
	@Override
	public String toString(){
		return name+" "+latitude+" Lgn: "+longitude;
	}

}
