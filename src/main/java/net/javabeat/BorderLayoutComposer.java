package net.javabeat;

import java.util.List;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Div;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

public class BorderLayoutComposer extends GenericForwardComposer{
	
	ListModelList<MarathonEvent> eventsModel;
	Listbox eventsListBox;
	MenuNodeSelectListener listener = new MenuNodeSelectListener();
	MenuNodeItemRenderer renderer = new MenuNodeItemRenderer();
	Div contentDiv;
	
	public BorderLayoutComposer(){
		String marathonEventScheduleSource = "http://runinfinity.com/calendar/india-marathon-calendar";
		MarathonEventsSource marathonEventsSource = new MarathonEventsSource();
		List<MarathonEvent> marathonEvents = marathonEventsSource.getMarathonEvents(); 
		eventsModel = new ListModelList<MarathonEvent>(marathonEvents);
		
	}
	
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		eventsListBox.setModel(eventsModel);
		
	}

	class MenuNode {
		String label;
		String link;
		public MenuNode(String label,String link){
			this.label = label;
			this.link = link;
		}
		public String getLabel() {
			return label;
		}
		public void setLabel(String label) {
			this.label = label;
		}
		public String getLink() {
			return link;
		}
		public void setLink(String link) {
			this.link = link;
		}
	}
	
	class MenuNodeItemRenderer implements ListitemRenderer{

		public void render(Listitem item, Object data, int index) throws Exception {
			
			MenuNode node = (MenuNode)data;
			item.setImage("icon-24x24.png");
			item.setLabel(node.getLabel());
			item.setValue(node);
		}
	}
	
	class MenuNodeSelectListener implements EventListener{
		public void onEvent(Event event) throws Exception {
//			Listitem item = menuListbox.getSelectedItem();
//			contentDiv.getChildren().clear();
//			if(item!=null){
//				MenuNode node = (MenuNode)item.getValue();
//				Executions.createComponents(node.getLink(),contentDiv,null);
//			}
		}		
	}
	
}
