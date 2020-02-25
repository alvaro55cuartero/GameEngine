package pressets.models;

import java.util.ArrayList;

import engine.old.graphics.VertexArray;
import engine.old.graphics.buffers.BufferElement;
import engine.old.graphics.buffers.BufferLayout;
import engine.old.graphics.buffers.IndexBuffer;
import engine.old.graphics.buffers.VertexBuffer;
import engine.old.graphics.buffers.Buffer.ShaderDataType;

public class Cube {
	public VertexArray vertexArray;

	private float[] vertices = { -0.5f, 0.5f, 0.0f, 0.0f, 1.0f, 0.5f, 0.5f, 0.0f, 1.0f, 1.0f, 0.5f, -0.5f, 0.0f, 1.0f,
			0.0f, -0.5f, -0.5f, 0.0f, 0.0f, 0.0f };

	private int[] index = { 0, 1, 2, 0, 2, 3 };

	public Cube() {
		vertexArray = VertexArray.create();

		VertexBuffer position = VertexBuffer.create(vertices);
		ArrayList<BufferElement> b = new ArrayList<BufferElement>();
		b.add(new BufferElement(ShaderDataType.FLOAT3, "position"));
		b.add(new BufferElement(ShaderDataType.FLOAT2, "texcoord"));
		BufferLayout layout1 = new BufferLayout(b);
		position.setLayout(layout1);
		vertexArray.addVertexBuffer(position);

		IndexBuffer indices = IndexBuffer.create(index);
		vertexArray.setIndexBuffer(indices);

	}

}
