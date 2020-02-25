package engine.objects;

public abstract class Component {

	public String name;

	public Component(String name) {
		this.name = name;
	}

	public abstract void init();

	public abstract void tick();

	public abstract void render();

	public abstract void close();
}
