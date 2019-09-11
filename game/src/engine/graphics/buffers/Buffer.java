package engine.graphics.buffers;

public class Buffer {

	public enum ShaderDataType {
		NONE, FLOAT, FLOAT2, FLOAT3, FLOAT4, MAT3, MAT4, INT, INT2, INT3, INT4, BOOL
	};

	static int ShaderDataTypeSize(ShaderDataType type) {
		switch (type) {
		case FLOAT:
			return 4;
		case FLOAT2:
			return 4 * 2;
		case FLOAT3:
			return 4 * 3;
		case FLOAT4:
			return 4 * 4;
		case MAT3:
			return 4 * 3 * 3;
		case MAT4:
			return 4 * 4 * 4;
		case INT:
			return 4;
		case INT2:
			return 4 * 2;
		case INT3:
			return 4 * 3;
		case INT4:
			return 4 * 4;
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
