/**
 * 
 */
package net.will.javatest.thkij4ed.ch10.controller;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Will
 * @version 2012-10-12
 */
public class Controller {
	private List<Event> eventList = new ArrayList<Event>();
	
	public void addEvent(Event c) { eventList.add(c); }
	
	public void run() {
		while (eventList.size() > 0) {
			// Make a copy so you’re not modifying the list
			// while you’re selecting the elements in it:
			for(Event e : new ArrayList<Event>(eventList)) {
				if (e.ready()) {
					System.out.println(e);
					e.action();
					eventList.remove(e);
				}
			}  // end FOR
		}  // end WHILE
	}
	
}
