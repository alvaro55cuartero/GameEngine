package engine.graphics.buffers;

import java.util.ArrayList;

public class BufferLayout {

	private int stride = 0;
	private ArrayList<BufferElement> elements;

	public BufferLayout(ArrayList<BufferElement> elements) {
		this.elements = elements;
		calculateOffsetsAndStride();
	}

	private void calculateOffsetsAndStride() {
		int offset = 0;
		stride = 0;
		for (int i = 0; i < elements.size(); i++) {
			BufferElement element = elements.get(i);
			element.offset = offset;
			offset += element.size;
			stride += element.size;
		}
	}

	public ArrayList<BufferElement> getElements() {
		return elements;
	}

	public int size() {
		return elements.size();
	}

	public int getStride() {
		return this.stride;
	}

}
