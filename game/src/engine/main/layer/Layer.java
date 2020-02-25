package engine.main.layer;

import engine.main.Timestep;
import engine.main.events.Event;

public abstract class Layer {

	private String name;

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
