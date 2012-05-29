package net.javabeat

import org.htmlcleaner.*;

class HtmlUtility {
	
	def List<MarathonEvent> getMarathonEvents(String sourceUrl){
		// Clean any messy HTML
		def cleaner = new HtmlCleaner()
		def node = cleaner.clean(sourceUrl.toURL())
		 
		// Convert from HTML to XML
		def props = cleaner.getProperties()
		def serializer = new SimpleXmlSerializer(props)
		def xml = serializer.getAsString(node)
		 
		// Parse the XML into a document we can work with
		def page = new XmlSlurper(false,false).parseText(xml)
		def eventList = new ArrayList<MarathonEvent>()
		page.body.div.div.div[4].div.div.div[2].div.table.tbody.each{
			eventRow -> eventRow.each{
				eventColumn -> println(eventColumn)
			}
		}
		
		return eventList;
	}

}
