package layers;

public abstract class Layer {

	private final String name;

	public Layer(String name) {
		this.name = name;
	}

	public abstract void OnAttach();

	public abstract void OnDetach();

	public abstract void OnUpdate();

	public abstract void OnEvent();

	public String getName() {
		return name;
	}
}
