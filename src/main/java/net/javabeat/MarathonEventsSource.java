package net.javabeat;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.ArrayList;

import org.zkoss.gmaps.Gmarker;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

public class MarathonEventsSource {
	
	public List<Gmarker> getMarathonEvents()
			throws MalformedURLException, IOException {
		List<Gmarker> marathonEvents = new ArrayList<Gmarker>();
		
		InputStream marathonEvenJsonSource = getClass().getResourceAsStream("marathon_events.json");
		Gson gson = new Gson();
		JsonReader jsonReader = new JsonReader(new InputStreamReader(marathonEvenJsonSource));
		JsonParser jsonParser = new JsonParser();
		JsonArray jsonArray = jsonParser.parse(jsonReader).getAsJsonArray();
		for ( JsonElement element : jsonArray){
			MarathonEvent marathonEvent = gson.fromJson(element, MarathonEvent.class);
			marathonEvent.geoLocation = getGeoCode(marathonEvent.location);
			Gmarker gmarker = new Gmarker(marathonEvent.location,
					marathonEvent.geoLocation.latitude,
					marathonEvent.geoLocation.longitude);
			gmarker.setContent(marathonEvent.toString());
			
			marathonEvents.add(gmarker);
		}
		return marathonEvents;
		
	}
	
	public Location getGeoCode(String location) 
			throws MalformedURLException, IOException{
		String url = "http://maps.googleapis.com/maps/api/geocode/json?address="+URLEncoder.encode(location, "UTF-8")+"&sensor=false";
		Location geoLocation = new Location(location);
		Gson gson = new Gson();
		InputStream in = new URL(url).openStream();
		JsonReader reader = new JsonReader(new InputStreamReader(in));
		JsonParser parser = new JsonParser();
		JsonObject jsonObject = parser.parse(reader).getAsJsonObject();
		JsonArray resultsArray = jsonObject.getAsJsonArray("results");
		if ( resultsArray.size() > 0 ){
			JsonObject geoInformation = resultsArray.get(0).getAsJsonObject();
			String formattedAddress = geoInformation.get("formatted_address").getAsString();
			System.out.println("Formatted Address: "+formattedAddress);
			JsonObject locationJsonObj = geoInformation.get("geometry").getAsJsonObject().get("location").getAsJsonObject();
			String latitude = locationJsonObj.get("lat").getAsString();
			String longitude = locationJsonObj.get("lng").getAsString();
			System.out.println("latitude : "+latitude+" longitude: "+ longitude);
			geoLocation.latitude = Double.parseDouble(latitude);
			geoLocation.longitude = Double.parseDouble(longitude);
			geoLocation.name = formattedAddress;
		}
		return geoLocation;
	}

}
