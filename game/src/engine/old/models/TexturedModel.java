package engine.old.models;

import engine.old.graphics.old.textures.ModelTexture;

public class TexturedModel {

	private RawModel rawModel;
	private ModelTexture modelTexture;
	private String name;

	public TexturedModel(RawModel rawModel, ModelTexture modelTexture, String name) {
		this.name = name;
		this.rawModel = rawModel;
		this.modelTexture = modelTexture;
	}

	public RawModel getRawModel() {
		return rawModel;
	}

	public ModelTexture getModelTexture() {
		return modelTexture;
	}

	public String getName() {
		return name;
	}

}
