package net.javabeat;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

public class MarathonEventsSource {
	
	public List<MarathonEvent> getMarathonEvents(){
		List<MarathonEvent> marathonEvents = new ArrayList<MarathonEvent>();
		InputStream marathonEvenJsonSource = getClass().getResourceAsStream("marathon_events.json");
		Gson gson = new Gson();
		JsonReader jsonReader = new JsonReader(new InputStreamReader(marathonEvenJsonSource));
		JsonParser jsonParser = new JsonParser();
		JsonArray jsonArray = jsonParser.parse(jsonReader).getAsJsonArray();
		for ( JsonElement element : jsonArray){
			MarathonEvent marathonEvent = gson.fromJson(element, MarathonEvent.class);
			System.out.println(marathonEvent);
			marathonEvents.add(marathonEvent);
			
		}
		return marathonEvents;
		
	}

}
