package engine.old.fontMeshCreator;

/**
 * Stores the vertex data for all the quads on which a text will be rendered.
 * 
 * @author Karl
 *
 */
public class TextMeshData {

	private float[] vertexPositions;
	private float[] textureCoords;
	private int[] indices;

	protected TextMeshData(int[] indices, float[] vertexPositions, float[] textureCoords) {
		this.indices = indices;
		this.vertexPositions = vertexPositions;
		this.textureCoords = textureCoords;
	}

	public int[] getIndices() {
		return indices;
	}

	public float[] getVertexPositions() {
		return vertexPositions;
	}

	public float[] getTextureCoords() {
		return textureCoords;
	}

	public int getVertexCount() {
		return vertexPositions.length / 2;
	}

}
