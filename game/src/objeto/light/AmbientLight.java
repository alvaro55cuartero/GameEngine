package objeto.light;

import org.joml.Vector3f;

import graphics.MasterRenderer;
import objeto.EntityDebug;

public class AmbientLight {

	private Vector3f color;
	private Vector3f position;

	int count = 0;

	public AmbientLight(Vector3f color, Vector3f position) {
		super();
		this.color = color;
		this.position = position;
	}

	public void render() {

		position.x = (float) (10 + (2 * Math.sin((count) * Math.PI / 180)));
		// System.out.println((20 * Math.sin((count / 360) * Math.PI / 180)));
		MasterRenderer.processEntityDebug(new EntityDebug(position, 0, 0, 0, 1, 1, 1));
		count++;
		if (count > 360) {
			count = 0;
		}
	}

	public Vector3f getColor() {
		return color;
	}

	public void setColor(Vector3f color) {
		this.color = color;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

}
