package events;

import java.util.concurrent.Callable;

public class EventDispatcher {

	private Event event;

	public EventDispatcher(Event event) {
		this.event = event;
	}

	public boolean dispatch(Event event) {
		if (this.event.getEventType() == event.getEventType()) {
			event.handled =
			return true; 
		}
		return false;
	}
}
