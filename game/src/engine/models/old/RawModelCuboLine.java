package engine.models.old;

public class RawModelCuboLine extends RawModel {

	private static float[] vertices = {

			0f, 0f, 0f, 0f, 0f, 1f, 0f, 1f, 0f, 0f, 1f, 1f, 1f, 0f, 0f, 1f, 0f, 1f, 1f, 1f, 0f, 1f, 1f, 1f

	};

	private static float[] textureCoords = {

			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0

	};

	private static int[] indices = { 0, 1, 5, 0, 4, 5, 7, 1, 3, 7, 4, 6, 0, 3, 2, 7, 6, 2, 0

	};

	public RawModelCuboLine() {
		super(vertices, indices, textureCoords);
	}

}
