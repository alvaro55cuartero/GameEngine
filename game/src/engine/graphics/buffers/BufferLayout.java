package engine.graphics.buffers;

import java.util.ArrayList;

public class BufferLayout extends ArrayList<BufferElement> {

	private int stride = 0;

	public BufferLayout(ArrayList<BufferElement> elements) {
		super(elements);
		calculateOffsetsAndStride();
	}

	public int getStride() {
		return stride;
	}

	public ArrayList<BufferElement> getElements() {
		return this;
	}

	public int begin() {
		return 0;
	}

	public int end() {
		return this.size();
	}

	private void calculateOffsetsAndStride() {
		int offset = 0;
		stride = 0;
		for (int i = 0; i < this.size(); i++) {
			this.get(i).offset = offset;
			offset += this.get(i).size;
			stride += this.get(i).size;
		}
	}
}
