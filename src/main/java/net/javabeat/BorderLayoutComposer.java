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

public class BorderLayoutComposer extends GenericForwardComposer{
	
	MapModel mapModel;
	Gmaps eventsMap;
	Div contentDiv;
	
	public BorderLayoutComposer() throws MalformedURLException, IOException{
		String marathonEventScheduleSource = "http://runinfinity.com/calendar/india-marathon-calendar";
		MarathonEventsSource marathonEventsSource = new MarathonEventsSource();
		List<Gmarker> marathonEvents = marathonEventsSource.getMarathonEvents();	
		mapModel = new MapModelList(marathonEvents);
		
	}
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);		
		eventsMap.setModel(mapModel);
		eventsMap.setZoom(9);
	}
}
