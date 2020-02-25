package pressets.models;

import java.util.ArrayList;

import org.joml.Vector2f;

import engine.old.graphics.VertexArray;
import engine.old.graphics.buffers.BufferElement;
import engine.old.graphics.buffers.BufferLayout;
import engine.old.graphics.buffers.IndexBuffer;
import engine.old.graphics.buffers.VertexBuffer;
import engine.old.graphics.buffers.Buffer.ShaderDataType;

public class Plane {
	public VertexArray vertexArray;
	private Vector2f pos;
	private Vector2f dim;

	private float[] vertices = { -0.5f, 0.5f, 0.0f, 0.0f, 1.0f, 0.5f, 0.5f, 0.0f, 1.0f, 1.0f, 0.5f, -0.5f, 0.0f, 1.0f,
			0.0f, -0.5f, -0.5f, 0.0f, 0.0f, 0.0f };

	private int[] index = { 0, 1, 2, 0, 2, 3 };

	public Plane(float x, float y, float width, float height) {
		this(new Vector2f(x, y), new Vector2f(width, height));
	}

	public Plane(Vector2f pos, Vector2f dim) {
		this.pos = pos;
		this.dim = dim;
	}

	public Plane() {
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
