package engine.graphics.buffers;

import engine.graphics.buffers.Buffer.ShaderDataType;

public class BufferElement {

	public String name;
	public ShaderDataType type;
	public int size;
	public int offset;
	public boolean normalized;

	public BufferElement(String name, ShaderDataType type, boolean normalized) {
		this.name = name;
		this.type = type;
		this.size = Buffer.ShaderDataTypeSize(type);
		this.offset = 0;
		this.normalized = normalized;
	}

	public BufferElement(String name, ShaderDataType type) {
		this(name, type, false);
	}

}
