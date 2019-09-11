package engine.platform.opengl;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import engine.graphics.VertexArray;
import engine.graphics.buffers.Buffer.ShaderDataType;
import engine.graphics.buffers.BufferElement;
import engine.graphics.buffers.BufferLayout;
import engine.graphics.buffers.IndexBuffer;
import engine.graphics.buffers.VertexBuffer;

public class OpenGLVertexArray extends VertexArray {
	private int rendererID;
	private int vertexBufferIndex;
	private ArrayList<VertexBuffer> vertexBuffers = new ArrayList<VertexBuffer>();
	private IndexBuffer indexBuffer;

	public OpenGLVertexArray() {
		rendererID = GL30.glGenVertexArrays();
	}

	public void bind() {
		GL30.glBindVertexArray(rendererID);
	}

	public void unbind() {
		GL30.glBindVertexArray(0);
	}

	public void addVertexBuffer(VertexBuffer vertexBuffer) {
		// HZ_CORE_ASSERT(vertexBuffer->GetLayout().GetElements().size(), "Vertex Buffer
		// has no layout!");
		GL30.glBindVertexArray(rendererID);
		vertexBuffer.bind();
		BufferLayout layout = vertexBuffer.getLayout();

		for (int i = layout.begin(); i < layout.end(); i++) {
			BufferElement element = layout.getElements().get(i);
			GL20.glEnableVertexAttribArray(vertexBufferIndex);
			GL20.glVertexAttribPointer(vertexBufferIndex, element.getComponentCount(),
					ShaderDataTypeToOpenGLBaseType(element.type), element.normalized, layout.getStride(),
					element.offset);
			vertexBufferIndex++;
		}

		vertexBuffers.add(vertexBuffer);
	}

	public void setIndexBuffer() {

	}

	public void dispose() {
		GL30.glDeleteVertexArrays(rendererID);
	}

	public ArrayList<VertexBuffer> getVertexBuffers() {
		return vertexBuffers;
	}

	public IndexBuffer getIndexBuffer() {
		return indexBuffer;
	}

	static int ShaderDataTypeToOpenGLBaseType(ShaderDataType type) {
		switch (type) {
		case FLOAT:
			return GL11.GL_FLOAT;
		case FLOAT2:
			return GL11.GL_FLOAT;
		case FLOAT3:
			return GL11.GL_FLOAT;
		case FLOAT4:
			return GL11.GL_FLOAT;
		case MAT3:
			return GL11.GL_FLOAT;
		case MAT4:
			return GL11.GL_FLOAT;
		case INT:
			return GL11.GL_INT;
		case INT2:
			return GL11.GL_INT;
		case INT3:
			return GL11.GL_INT;
		case INT4:
			return GL11.GL_INT;
		case BOOL:
			return GL20.GL_BOOL;
		}

		// HZ_CORE_ASSERT(false, "Unknown ShaderDataType!");
		return 0;
	}

	public void setIndexBuffer(IndexBuffer indexBuffer) {
		GL30.glBindVertexArray(rendererID);
		indexBuffer.bind();

		this.indexBuffer = indexBuffer;
	}
}
