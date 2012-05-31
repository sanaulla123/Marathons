package net.javabeat;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.zkoss.gmaps.Gmaps;
import org.zkoss.gmaps.Gmarker;
import org.zkoss.gmaps.MapModel;
import org.zkoss.gmaps.MapModelList;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Div;

public class MarathonHomeComposer extends GenericForwardComposer{
	
	MapModel mapModel;
	Gmaps eventsMap;
	Div contentDiv;
	List<Gmarker> marathonEvents;
	
	public MarathonHomeComposer() 
			throws MalformedURLException, IOException{
		String marathonEventScheduleSource = "http://runinfinity.com/calendar/india-marathon-calendar";
		MarathonEventsSource marathonEventsSource = new MarathonEventsSource();
		marathonEvents = marathonEventsSource.getMarathonEvents();	
		mapModel = new MapModelList(marathonEvents);
		
	}
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);		
		eventsMap.setModel(mapModel);
		eventsMap.setZoom(5);
		setTheDefaultLocationOfMap();
	}
	
	/**
	 * Setting the default location of the map.
	 * Note: Ideally this should get the user's location i.e IP address
	 * and get the corresponding Latitude and Longitude values. 
	 * For now defaulting to setting the location to the location of the first event.
	 */
	public void setTheDefaultLocationOfMap(){
		if ( marathonEvents.size() > 0){
			Gmarker firstEvent = marathonEvents.get(0);
			eventsMap.setLat(firstEvent.getLat());
			eventsMap.setLng(firstEvent.getLng());
		}
	}
}
