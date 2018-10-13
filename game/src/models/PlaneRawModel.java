package models;

import graphics.Loader;

public class PlaneRawModel extends RawModel {

	static float[] vertices = { 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f };
	static float[] textureCoords = { 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f };
	static int[] indices = { 0, 1, 3, 3, 1, 2 };

	public PlaneRawModel() {
		RawModel temp = Loader.loadToVao(vertices, indices, textureCoords);
		setId(temp.getId());
		setCount(temp.getCount());

	}
}
