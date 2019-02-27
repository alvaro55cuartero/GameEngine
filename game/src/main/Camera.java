package main;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import controls.Input;
import controls.InputCursor;
import objeto.Jugador;

public class Camera {

	private Vector3f position = new Vector3f(0, 0, 8);
	private float pitch;
	private float yaw;
	private float roll;
	private float speed = 0.60f;
	private float speedcam = 2.1f;

	private int count;

	public Camera() {
	}

	public void moveJuego(Jugador jugador) {
		roll = 5;
		float dx = jugador.getPosition().x - this.getPosition().x;
		float dy = jugador.getPosition().y - 1 - this.getPosition().y;

		this.position.x += dx * speed;
		this.position.y += dy * speed + 2;

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
			this.position.x += 0.4f * Math.sin(Math.toRadians(pitch));
			this.position.y -= 0.4f * Math.sin(Math.toRadians(roll));
			this.position.z -= 0.4f * Math.cos(Math.toRadians(pitch));
		}
		if (Input.get(GLFW.GLFW_KEY_A)) {
			this.position.x -= 0.4f * Math.cos(Math.toRadians(pitch));
			this.position.z -= 0.4f * Math.sin(Math.toRadians(pitch));
		}
		if (Input.get(GLFW.GLFW_KEY_S)) {
			this.position.x -= 0.4f * Math.sin(Math.toRadians(pitch));
			this.position.y += 0.4f * Math.sin(Math.toRadians(roll));
			this.position.z += 0.4f * Math.cos(Math.toRadians(pitch));
		}
		if (Input.get(GLFW.GLFW_KEY_D)) {

			this.position.x += 0.4f * Math.cos(Math.toRadians(pitch));
			this.position.z += 0.4f * Math.sin(Math.toRadians(pitch));
		}
		if (Input.get(GLFW.GLFW_KEY_SPACE)) {

			this.position.y += 0.5f;
		}
		if (roll > -90) {
			if (Input.get(GLFW.GLFW_KEY_UP)) {
				this.roll -= 2.5f;
			} else if (InputCursor.getDy() > 0) {
				this.roll += InputCursor.getDy() * speed;
			}
		}
		if (roll < 90) {
			if (Input.get(GLFW.GLFW_KEY_DOWN)) {
				this.roll += 2.5f;
			} else if (InputCursor.getDy() < 0) {
				this.roll -= -InputCursor.getDy() * speed;
			}
		}

		if (Input.get(GLFW.GLFW_KEY_LEFT)) {
			this.pitch -= 2.5f;
		} else if (InputCursor.getDx() < 0) {
			this.pitch -= -InputCursor.getDx() * speed;
		}

		if (Input.get(GLFW.GLFW_KEY_RIGHT)) {
			this.pitch += 2.5f;
		}
		if (InputCursor.getDx() > 0) {
			this.pitch += InputCursor.getDx() * speed;
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

	public void setPositionZ(float z) {
		position.z = z;
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

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public void setDir(Vector3f dir) {
		this.pitch = dir.x;
		this.roll = dir.y;
		this.yaw = dir.z;
	}

	public void setPitch(float pitch) {
		this.pitch = pitch;
	}

	public void setYaw(float yaw) {
		this.yaw = yaw;
	}

	public void setRoll(float roll) {
		this.roll = roll;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public void setSpeedcam(float speedcam) {
		this.speedcam = speedcam;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
