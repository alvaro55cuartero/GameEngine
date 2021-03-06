package engine.old.models;

import engine.old.graphics.old.Loader;

public class RawModel {

	protected int id;
	protected int count;

	public RawModel(int id, int count) {
		this.id = id;
		this.count = count;
	}

	public RawModel(float[] positions, int[] indices, float[] textureCoords) {
		RawModel model = Loader.loadToVao(positions, indices, textureCoords);
		this.id = model.id;
		this.count = model.count;
	}

	public RawModel(int[] indices, float[] positions, float[] textureCoords, float[] normals) {
		RawModel model = Loader.loadToVao(indices, positions, textureCoords, normals);
		this.id = model.id;
		this.count = model.count;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getCount() {
		return count;
	}

}
