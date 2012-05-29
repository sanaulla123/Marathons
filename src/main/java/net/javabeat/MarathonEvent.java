package net.javabeat;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;

public class MarathonEvent {
	
	@SerializedName("event_url")
	String url;
	
	String name;
	String date;
	String location;
	Location geoLocation;
	String formattedContent;
	
	
	@Override
	public String toString(){
		if ( formattedContent == null ){
			StringBuilder builder = new StringBuilder();
			builder.append("Date: "+date);
			builder.append("<br/>");
			builder.append("<a href=\""+url+"\">"+name+"</a>");
			builder.append("<br/>");
			builder.append(geoLocation.name);
			formattedContent = builder.toString();
			
		}
		return formattedContent;
	}

}
