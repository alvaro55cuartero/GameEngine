package main;

import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import controls.Input;
import controls.InputCursor;
import objeto.Jugador;

public class Camera {

	private Vector3f position = new Vector3f(0, 0, 20);
	private Quaternionf rotation = new Quaternionf();
	private float speed = 0.6f;
	private float speedcam = 0.01f;

	public Camera() {
	}

	public void moveJuego(Jugador jugador) {

		float dx = jugador.getPosition().x - this.getPosition().x;
		float dy = jugador.getPosition().y - 1 - this.getPosition().y;

		this.position.x += dx * speed;
		this.position.y += dy * speed + 2;

	}

	public void moveEditor() {
		if (Input.get(GLFW.GLFW_KEY_W)) {
			this.position.x += 0.4f * Math.sin(Math.toRadians(getPitch()));
			this.position.y -= 0.4f * Math.sin(Math.toRadians(getRoll()));
			this.position.z -= 0.4f * Math.cos(Math.toRadians(getPitch()));
		}
		if (Input.get(GLFW.GLFW_KEY_A)) {
			this.position.x -= 0.4f * Math.cos(Math.toRadians(getPitch()));
			this.position.z -= 0.4f * Math.sin(Math.toRadians(getPitch()));
		}
		if (Input.get(GLFW.GLFW_KEY_S)) {
			this.position.x -= 0.4f * Math.sin(Math.toRadians(getPitch()));
			this.position.y += 0.4f * Math.sin(Math.toRadians(getRoll()));
			this.position.z += 0.4f * Math.cos(Math.toRadians(getPitch()));
		}
		if (Input.get(GLFW.GLFW_KEY_D)) {

			this.position.x += 0.4f * Math.cos(Math.toRadians(getPitch()));
			this.position.z += 0.4f * Math.sin(Math.toRadians(getPitch()));
		}
		if (Input.get(GLFW.GLFW_KEY_SPACE)) {
			this.position.y += 0.5f;
		}

		if (Input.get(GLFW.GLFW_KEY_UP) && getRoll() > -80) {
			rotation.rotateY(-0.04f);
		}
		if (Input.get(GLFW.GLFW_KEY_DOWN) && getRoll() < 80) {
			rotation.rotateY(0.04f);
		}

		if (Input.get(GLFW.GLFW_KEY_LEFT)) {
			rotation.rotateLocalX(-0.04f);
		}

		if (Input.get(GLFW.GLFW_KEY_RIGHT)) {
			rotation.rotateLocalX(0.04f);
		}

	}

	public void moveFree() {
		if (Input.get(GLFW.GLFW_KEY_W)) {
			this.position.x += 0.4f * Math.sin(Math.toRadians(getPitch()));
			this.position.y -= 0.4f * Math.sin(Math.toRadians(getRoll()));
			this.position.z -= 0.4f * Math.cos(Math.toRadians(getPitch()));
		}
		if (Input.get(GLFW.GLFW_KEY_A)) {
			this.position.x -= 0.4f * Math.cos(Math.toRadians(getPitch()));
			this.position.z -= 0.4f * Math.sin(Math.toRadians(getPitch()));
		}
		if (Input.get(GLFW.GLFW_KEY_S)) {
			this.position.x -= 0.4f * Math.sin(Math.toRadians(getPitch()));
			this.position.y += 0.4f * Math.sin(Math.toRadians(getRoll()));
			this.position.z += 0.4f * Math.cos(Math.toRadians(getPitch()));
		}
		if (Input.get(GLFW.GLFW_KEY_D)) {

			this.position.x += 0.4f * Math.cos(Math.toRadians(getPitch()));
			this.position.z += 0.4f * Math.sin(Math.toRadians(getPitch()));
		}
		if (Input.get(GLFW.GLFW_KEY_SPACE)) {

			this.position.y += 0.5f;
		}
		if (getRoll() > -80) {
			if (Input.get(GLFW.GLFW_KEY_UP)) {
				rotation.rotateY(-0.04f);
			} else if (InputCursor.getDy() > 0) {
				rotation.rotateY(InputCursor.getDy() * speedcam);
			}
		}
		if (getRoll() < 80) {
			if (Input.get(GLFW.GLFW_KEY_DOWN)) {
				rotation.rotateY(0.04f);
			} else if (InputCursor.getDy() < 0) {
				rotation.rotateY(InputCursor.getDy() * speedcam);
			}
		}

		if (Input.get(GLFW.GLFW_KEY_LEFT)) {
			rotation.rotateLocalX(-0.04f);
		} else if (InputCursor.getDx() < 0) {
			rotation.rotateLocalX(InputCursor.getDx() * speedcam);
		}

		if (Input.get(GLFW.GLFW_KEY_RIGHT)) {
			rotation.rotateLocalX(0.04f);
		} else if (InputCursor.getDx() > 0) {
			rotation.rotateLocalX(InputCursor.getDx() * speedcam);
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
		return (float) Math.toDegrees(getEuler().x);
	}

	public float getYaw() {
		return (float) Math.toDegrees(getEuler().z);
	}

	public float getRoll() {
		return (float) Math.toDegrees(getEuler().y);
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public void setSpeedcam(float speedcam) {
		this.speedcam = speedcam;
	}

	public Vector3f getEuler() {
		return rotation.getEulerAnglesXYZ(new Vector3f());
	}

}
