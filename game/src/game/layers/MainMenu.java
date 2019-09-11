package game.layers;

import org.joml.Vector3f;

import engine.events.Event;
import engine.layer.Layer;
import engine.main.Timestep;
import engine.models.PlaneTexturedModel;
import engine.objeto.OrthographicCamera;
import engine.objeto.entities.Entity3DPlane;

public class MainMenu extends Layer {

	Entity3DPlane plane = new Entity3DPlane(0, new Vector3f(0, 0, 0), 0, 0, 0, 1, 1);
	PlaneTexturedModel model = new PlaneTexturedModel("balcon");

	public OrthographicCamera camera;

	public MainMenu(String name) {
		super(name);

	}

	public void onAttach() {

	}

	public void onDetach() {

	}

	public void onUpdate(Timestep timestep) {

	}

	public void onEvent(Event event) {

	}
}
