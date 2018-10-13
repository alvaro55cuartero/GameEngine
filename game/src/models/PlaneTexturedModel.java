package models;

import graphics.Loader;
import textures.ModelTexture;

public class PlaneTexturedModel extends TexturedModel {

	public PlaneTexturedModel(ModelTexture modelTexture, String name) {
		super(new PlaneRawModel(), modelTexture, name);
	}

	public PlaneTexturedModel(String name) {
		this(new ModelTexture(Loader.loadTexture("res/png/" + name + ".png")), name);
	}

}
