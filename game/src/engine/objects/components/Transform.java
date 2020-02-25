package engine.objects.components;

import org.joml.Vector3f;

import engine.objects.Component;

public class Transform extends Component {

	public Vector3f position = new Vector3f();
	public Vector3f rotation = new Vector3f();
	public Vector3f scale = new Vector3f();

	public Transform(String name) {
		super(name);
	}

	public void init() {

	}

	public void tick() {

	}

	public void render() {

	}

	public void close() {

	}

}
