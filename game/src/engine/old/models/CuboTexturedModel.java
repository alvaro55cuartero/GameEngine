package engine.old.models;

import engine.old.graphics.old.Loader;
import engine.old.graphics.old.textures.ModelTexture;

public class CuboTexturedModel extends TexturedModel {

	public CuboTexturedModel(ModelTexture modelTexture, String name) {
		super(new CuboRawModel(), modelTexture, name);
	}

	public CuboTexturedModel(String name) {
		this(new ModelTexture(Loader.loadTexture("res/png/" + name + ".png")), name);
	}

}