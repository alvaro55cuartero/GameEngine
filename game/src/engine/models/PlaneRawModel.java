package engine.models;

public class PlaneRawModel extends RawModel {

	private static int[] indices = { 0, 1, 3, 3, 1, 2 };
	private static float[] vertices = { 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f };
	private static float[] textureCoords = { 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f };
	private static float[] normals = { 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f };

	public PlaneRawModel() {
		super(indices, vertices, textureCoords, normals);
	}
}
