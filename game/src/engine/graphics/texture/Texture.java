package engine.graphics.texture;

public abstract class Texture {
	public abstract void dispose();

	public abstract int getWidth();

	public abstract int getHeight();

	public void bind() {
		this.bind(0);
	}

	public abstract void bind(int slot);
}
