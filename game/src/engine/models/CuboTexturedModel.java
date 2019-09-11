package engine.models;

import engine.graphics.old.Loader;
import engine.graphics.old.textures.ModelTexture;

public class CuboTexturedModel extends TexturedModel {

	public CuboTexturedModel(ModelTexture modelTexture, String name) {
		super(new CuboRawModel(), modelTexture, name);
	}

	public CuboTexturedModel(String name) {
		this(new ModelTexture(Loader.loadTexture("res/png/" + name + ".png")), name);
	}

}