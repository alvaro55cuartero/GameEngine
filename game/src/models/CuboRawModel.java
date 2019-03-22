package models;

public class CuboRawModel extends RawModel {

	private static float[] vertices = { 0f, 1f, 0f, 0f, 0f, 0f, 1f, 0f, 0f, 1f, 1f, 0f,

			0f, 1f, 1f, 0f, 0f, 1f, 1f, 0f, 1f, 1f, 1f, 1f,

			1f, 1f, 0f, 1f, 0f, 0f, 1f, 0f, 1f, 1f, 1f, 1f,

			0f, 1f, 0f, 0f, 0f, 0f, 0f, 0f, 1f, 0f, 1f, 1f,

			0f, 1f, 1f, 0f, 1f, 0f, 1f, 1f, 0f, 1f, 1f, 1f,

			0f, 0f, 1f, 0f, 0f, 0f, 1f, 0f, 0f, 1f, 0f, 1f

	};

	private static float[] textureCoords = {

			0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1,
			1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0

	};

	private static int[] indices = { 0, 1, 3, 3, 1, 2, 4, 5, 7, 7, 5, 6, 8, 9, 11, 11, 9, 10, 12, 13, 15, 15, 13, 14,
			16, 17, 19, 19, 17, 18, 20, 21, 23, 23, 21, 22

	};

	public CuboRawModel() {
		super(vertices, indices, textureCoords);
	}

}
