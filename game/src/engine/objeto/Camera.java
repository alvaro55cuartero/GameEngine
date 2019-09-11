package engine.objeto;

import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import engine.controls.old.Input;
import engine.controls.old.InputCursor;
import engine.objeto.jugador.Jugador;

public abstract class Camera {

	protected float speed = 0.6f;
	protected float speedcam = 0.01f;
	protected Vector3f position = new Vector3f(0, 0, 20);
	protected Quaternionf rotation = new Quaternionf();
	protected Matrix4f projectionMatrix;
	protected Matrix4f viewMatrix;
	protected Matrix4f viewProjectionMatrix = new Matrix4f().identity();

	public Camera() {
	}

	protected void recalculateViewMatrix() {
		Matrix4f transform = new Matrix4f().translate(position).rotate(rotation, new Vector3f(0, 0, 1));
		viewMatrix = transform.invert();
		projectionMatrix.mul(viewMatrix, viewProjectionMatrix);
	};

	public void setPositionX(float x) {
		position.x = x;
	}

	public void setPositionY(float y) {
		position.y = y;
	}

	public void setPositionZ(float z) {
		position.z = z;
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

	public void setRotation(Quaternionf rotation) {
		this.rotation = rotation;
	}

	public Vector3f getPosition() {
		return position;
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

	public Vector3f getEuler() {
		return rotation.getEulerAnglesXYZ(new Vector3f());
	}

	public Quaternionf getRotation() {
		return rotation;
	}

	public float getSpeed() {
		return speed;
	}

	public float getSpeedcam() {
		return speedcam;
	}

	public static void moveJuego(Jugador jugador, Camera camera) {

		float dx = jugador.getPosition().x - camera.getPosition().x;
		float dy = jugador.getPosition().y - 1 - camera.getPosition().y;

		camera.position.x += dx * camera.speed;
		camera.position.y += dy * camera.speed + 2;

	}

	public static void moveEditor(Camera camera) {
		if (Input.get(GLFW.GLFW_KEY_W)) {
			camera.position.x += 0.4f * Math.sin(Math.toRadians(camera.getPitch()));
			camera.position.y -= 0.4f * Math.sin(Math.toRadians(camera.getRoll()));
			camera.position.z -= 0.4f * Math.cos(Math.toRadians(camera.getPitch()));
		}
		if (Input.get(GLFW.GLFW_KEY_A)) {
			camera.position.x -= 0.4f * Math.cos(Math.toRadians(camera.getPitch()));
			camera.position.z -= 0.4f * Math.sin(Math.toRadians(camera.getPitch()));
		}
		if (Input.get(GLFW.GLFW_KEY_S)) {
			camera.position.x -= 0.4f * Math.sin(Math.toRadians(camera.getPitch()));
			camera.position.y += 0.4f * Math.sin(Math.toRadians(camera.getRoll()));
			camera.position.z += 0.4f * Math.cos(Math.toRadians(camera.getPitch()));
		}
		if (Input.get(GLFW.GLFW_KEY_D)) {

			camera.position.x += 0.4f * Math.cos(Math.toRadians(camera.getPitch()));
			camera.position.z += 0.4f * Math.sin(Math.toRadians(camera.getPitch()));
		}
		if (Input.get(GLFW.GLFW_KEY_SPACE)) {
			camera.position.y += 0.5f;
		}

		if (Input.get(GLFW.GLFW_KEY_UP) && camera.getRoll() > -80) {
			camera.rotation.rotateLocalX(-0.04f);
		}
		if (Input.get(GLFW.GLFW_KEY_DOWN) && camera.getRoll() < 80) {
			camera.rotation.rotateLocalX(0.04f);
		}

		if (Input.get(GLFW.GLFW_KEY_LEFT)) {
			camera.rotation.rotateY(-0.04f);
		}

		if (Input.get(GLFW.GLFW_KEY_RIGHT)) {
			camera.rotation.rotateY(0.04f);
		}

	}

	public static void moveFree(Camera camera) {

		Vector3f v = camera.rotation.transform(new Vector3f(0, 0, 1));
		if (Input.get(GLFW.GLFW_KEY_W)) {

			camera.position.x += 0.4f * v.x;
			camera.position.y += 0.4f * v.y;
			camera.position.z -= 0.4f * v.z;

		}
		if (Input.get(GLFW.GLFW_KEY_A)) {
			camera.position.x -= 0.4f * v.z;
			camera.position.z -= 0.4f * v.x;
		}
		if (Input.get(GLFW.GLFW_KEY_S)) {
			camera.position.x -= 0.4f * v.x;
			camera.position.y -= 0.4f * v.y;
			camera.position.z += 0.4f * v.z;
		}
		if (Input.get(GLFW.GLFW_KEY_D)) {

			camera.position.x += 0.4f * v.z;
			camera.position.z += 0.4f * v.x;
		}
		if (Input.get(GLFW.GLFW_KEY_SPACE)) {

			camera.position.y += 0.5f;
		}
		if (camera.getRoll() > -80) {
			if (Input.get(GLFW.GLFW_KEY_UP)) {
				camera.rotation.rotateLocalX(-0.04f);
			} else if (InputCursor.getDy() > 0) {
				camera.rotation.rotateLocalX(InputCursor.getDy() * camera.speedcam);
			}
		}
		if (camera.getRoll() < 80) {
			if (Input.get(GLFW.GLFW_KEY_DOWN)) {
				camera.rotation.rotateLocalX(0.04f);
			} else if (InputCursor.getDy() < 0) {
				camera.rotation.rotateLocalX(InputCursor.getDy() * camera.speedcam);
			}
		}

		if (Input.get(GLFW.GLFW_KEY_LEFT)) {
			camera.rotation.rotateY(-0.04f);
		} else if (InputCursor.getDx() < 0) {
			camera.rotation.rotateY(InputCursor.getDx() * camera.speedcam);
		}

		if (Input.get(GLFW.GLFW_KEY_RIGHT)) {
			camera.rotation.rotateY(0.04f);
		} else if (InputCursor.getDx() > 0) {
			camera.rotation.rotateY(InputCursor.getDx() * camera.speedcam);
		}
	}

}
