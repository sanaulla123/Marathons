package net.javabeat;

import com.google.gson.annotations.SerializedName;

public class MarathonEvent {
	
	@SerializedName("even_url")
	String url;
	
	String name;
	String date;
	String location;
	
	@Override
	public String toString(){
		return date+": "+name+" @ "+location;
	}

}
