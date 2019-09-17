package engine.models;

import java.util.ArrayList;

import engine.graphics.VertexArray;
import engine.graphics.buffers.Buffer.ShaderDataType;
import engine.graphics.buffers.BufferElement;
import engine.graphics.buffers.BufferLayout;
import engine.graphics.buffers.IndexBuffer;
import engine.graphics.buffers.VertexBuffer;

public class RawModel {

	public VertexArray vertexArray;

	public IndexBuffer index;

	protected int id;
	protected int count;

	public RawModel(int id, int count) {
		this.id = id;
		this.count = count;
	}

	public RawModel(int[] indices, float[] positions, float[] textureCoords) {
		vertexArray = VertexArray.create();

		VertexBuffer position = VertexBuffer.create(positions);
		ArrayList<BufferElement> a = new ArrayList<BufferElement>();
		a.add(new BufferElement(ShaderDataType.FLOAT3, "position"));
		position.setLayout(new BufferLayout(a));
		vertexArray.addVertexBuffer(position);

		VertexBuffer textCoord = VertexBuffer.create(textureCoords);
		ArrayList<BufferElement> b = new ArrayList<BufferElement>();
		b.add(new BufferElement(ShaderDataType.FLOAT2, "textCoord"));
		textCoord.setLayout(new BufferLayout(b));
		vertexArray.addVertexBuffer(textCoord);

		index = IndexBuffer.create(indices);
		vertexArray.setIndexBuffer(index);

	}

	public RawModel(int[] indices, float[] positions, float[] textureCoords, float[] normals) {
		this(indices, positions, textureCoords);
		VertexBuffer normal = VertexBuffer.create(normals);
		ArrayList<BufferElement> a = new ArrayList<BufferElement>();
		a.add(new BufferElement(ShaderDataType.FLOAT3, "normals"));
		normal.setLayout(new BufferLayout(a));
		vertexArray.addVertexBuffer(normal);
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
