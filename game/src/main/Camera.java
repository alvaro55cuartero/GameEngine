package main;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import objeto.Jugador;

public class Camera {

	private Vector3f position = new Vector3f(0, 0, 10);
	private float pitch = 0;
	private float yaw;
	private float roll;
	private float speed = 0.15f;

	private int count;

	public Camera() {
	}

	public void moveJuego(Jugador jugador) {
		float dx = jugador.getPosition().x - this.getPosition().x;
		float dy = jugador.getPosition().y - 1 - this.getPosition().y;

		this.position.x += dx * speed;
		this.position.y += dy * speed;

	}

	public void moveEditor() {
		if (count > 0) {
			count--;
		}
		if (Input.get(GLFW.GLFW_KEY_W) && count == 0) {
			this.position.y += 1f;
			count = 20;
		}
		if (Input.get(GLFW.GLFW_KEY_A) && count == 0) {
			this.position.x -= 1f;
			count = 20;
		}
		if (Input.get(GLFW.GLFW_KEY_S) && count == 0) {
			this.position.y -= 1f;
			count = 20;
		}
		if (Input.get(GLFW.GLFW_KEY_D) && count == 0) {
			this.position.x += 1f;
			count = 20;
		}

	}

	public void moveFree() {
		if (Input.get(GLFW.GLFW_KEY_W)) {
			this.position.z -= 1f;
		}
		if (Input.get(GLFW.GLFW_KEY_A)) {
			this.position.x -= 0.1f;
		}
		if (Input.get(GLFW.GLFW_KEY_S)) {
			this.position.z += 1f;
		}
		if (Input.get(GLFW.GLFW_KEY_D)) {
			this.position.x += 0.1f;
		}
		if (Input.get(GLFW.GLFW_KEY_UP)) {
			this.roll += 1f;
		}
		if (Input.get(GLFW.GLFW_KEY_DOWN)) {
			this.roll -= 1f;
		}
		if (Input.get(GLFW.GLFW_KEY_LEFT)) {
			this.pitch -= 1f;
		}
		if (Input.get(GLFW.GLFW_KEY_RIGHT)) {
			this.pitch += 1f;
		}
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPositionX(float x) {
		position.x = x;
	}

	public void setPositionY(float y) {
		position.y = y;
	}

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public float getRoll() {
		return roll;
	}

}
