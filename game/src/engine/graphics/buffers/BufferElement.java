package engine.graphics.buffers;

import engine.graphics.buffers.Buffer.ShaderDataType;

public class BufferElement {

	public String name;
	public ShaderDataType type;
	public int size;
	public int offset;
	public boolean normalized;

	public BufferElement(ShaderDataType type, String name, boolean normalized) {
		this.name = name;
		this.type = type;
		this.size = Buffer.ShaderDataTypeSize(type);
		this.offset = 0;
		this.normalized = normalized;
	}

	public BufferElement(ShaderDataType type, String name) {
		this(type, name, false);
	}

	public int getComponentCount() {
		switch (type) {
		case FLOAT:
			return 1;
		case FLOAT2:
			return 2;
		case FLOAT3:
			return 3;
		case FLOAT4:
			return 4;
		case MAT3:
			return 3 * 3;
		case MAT4:
			return 4 * 4;
		case INT:
			return 1;
		case INT2:
			return 2;
		case INT3:
			return 3;
		case INT4:
			return 4;
		case BOOL:
			return 1;
		case NONE:
			break;
		default:
			break;
		}
		// HZ_CORE_ASSERT(false, "Unknown ShaderDataType!");
		return 0;
	}

}
