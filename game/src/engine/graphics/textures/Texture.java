package engine.graphics.textures;

public abstract class Texture {

	public abstract void bind(int slot);

	public void bind() {
		this.bind(0);
	}

	public abstract int getWidth();

	public abstract int getHeight();

	public abstract void dispose();

}
