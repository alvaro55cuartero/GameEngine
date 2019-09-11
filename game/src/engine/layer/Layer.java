package engine.layer;

import engine.events.Event;
import engine.main.Timestep;

public abstract class Layer {

	private final String name;

	public Layer(String name) {
		this.name = name;
	}

	public abstract void onAttach();

	public abstract void onDetach();

	public abstract void onUpdate(Timestep timestep);

	public abstract void onEvent(Event event);

	public String getName() {
		return name;
	}
}
