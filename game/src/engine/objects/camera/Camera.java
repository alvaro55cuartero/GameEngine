package engine.objects.camera;

import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import engine.controls.Input;
import engine.controls.KeyCode;
import engine.old.controls.old.InputCursor;
import engine.old.objeto.jugador.Jugador;

public abstract class Camera {

	protected float speed = 0.01f;
	protected float speedcam = 0.01f;

	public Vector3f position;
	public Quaternionf rotation;

	protected Matrix4f projectionMatrix;
	protected Matrix4f viewMatrix;
	protected Matrix4f viewProjectionMatrix;

	public Camera() {
		position = new Vector3f();
		rotation = new Quaternionf();
		viewProjectionMatrix = new Matrix4f();
	}

	protected void recalculateViewMatrix() {
		Matrix4f transform = new Matrix4f().translate(position).rotate(rotation);
		viewMatrix = transform.invert();
		projectionMatrix.mul(viewMatrix, viewProjectionMatrix);
	}

	public static void move(Camera camera) {

	}

	public static void moveJuego(Jugador jugador, Camera camera) {

		float dx = jugador.getPosition().x - camera.getPosition().x;
		float dy = jugador.getPosition().y - 1 - camera.getPosition().y;

		camera.position.x += dx * camera.speed;
		camera.position.y += dy * camera.speed + 2;

	}

	public static void moveEditor(Camera camera) {
		if (Input.isKeyPressed(KeyCode.PRO_KEY_W)) {
			camera.position.x += camera.speed * Math.sin(Math.toRadians(camera.getPitch()));
			camera.position.y -= camera.speed * Math.sin(Math.toRadians(camera.getRoll()));
			camera.position.z -= camera.speed * Math.cos(Math.toRadians(camera.getPitch()));
		}
		if (Input.isKeyPressed(KeyCode.PRO_KEY_A)) {
			camera.position.x -= camera.speed * Math.cos(Math.toRadians(camera.getPitch()));
			camera.position.z -= camera.speed * Math.sin(Math.toRadians(camera.getPitch()));
		}
		if (Input.isKeyPressed(KeyCode.PRO_KEY_S)) {
			camera.position.x -= camera.speed * Math.sin(Math.toRadians(camera.getPitch()));
			camera.position.y += camera.speed * Math.sin(Math.toRadians(camera.getRoll()));
			camera.position.z += camera.speed * Math.cos(Math.toRadians(camera.getPitch()));
		}
		if (Input.isKeyPressed(KeyCode.PRO_KEY_D)) {

			camera.position.x += camera.speed * Math.cos(Math.toRadians(camera.getPitch()));
			camera.position.z += camera.speed * Math.sin(Math.toRadians(camera.getPitch()));
		}
		if (Input.isKeyPressed(KeyCode.PRO_KEY_SPACE)) {
			camera.position.y += 0.5f;
		}

		if (Input.isKeyPressed(KeyCode.PRO_KEY_UP) && camera.getRoll() > -80) {
			camera.rotation.rotateLocalX(-0.04f);
		}
		if (Input.isKeyPressed(KeyCode.PRO_KEY_DOWN) && camera.getRoll() < 80) {
			camera.rotation.rotateLocalX(0.04f);
		}

		if (Input.isKeyPressed(KeyCode.PRO_KEY_LEFT)) {
			camera.rotation.rotateY(-0.04f);
		}

		if (Input.isKeyPressed(KeyCode.PRO_KEY_RIGHT)) {
			camera.rotation.rotateY(0.04f);
		}
		camera.recalculateViewMatrix();
	}

	public static void moveFree(Camera camera) {

		Vector3f v = camera.rotation.transform(new Vector3f(0, 0, 1));
		if (Input.isKeyPressed(KeyCode.PRO_KEY_W)) {

			camera.position.x += 0.4f * v.x;
			camera.position.y += 0.4f * v.y;
			camera.position.z -= 0.4f * v.z;

		}
		if (Input.isKeyPressed(KeyCode.PRO_KEY_A)) {
			camera.position.x -= 0.4f * v.z;
			camera.position.z -= 0.4f * v.x;
		}
		if (Input.isKeyPressed(KeyCode.PRO_KEY_S)) {
			camera.position.x -= 0.4f * v.x;
			camera.position.y -= 0.4f * v.y;
			camera.position.z += 0.4f * v.z;
		}
		if (Input.isKeyPressed(KeyCode.PRO_KEY_D)) {

			camera.position.x += 0.4f * v.z;
			camera.position.z += 0.4f * v.x;
		}
		if (Input.isKeyPressed(KeyCode.PRO_KEY_SPACE)) {

			camera.position.y += 0.5f;
		}
		if (camera.getRoll() > -80) {
			if (Input.isKeyPressed(KeyCode.PRO_KEY_UP)) {
				camera.rotation.rotateLocalX(-0.04f);
			} else if (InputCursor.getDy() > 0) {
				camera.rotation.rotateLocalX(InputCursor.getDy() * camera.speedcam);
			}
		}
		if (camera.getRoll() < 80) {
			if (Input.isKeyPressed(KeyCode.PRO_KEY_DOWN)) {
				camera.rotation.rotateLocalX(0.04f);
			} else if (InputCursor.getDy() < 0) {
				camera.rotation.rotateLocalX(InputCursor.getDy() * camera.speedcam);
			}
		}

		if (Input.isKeyPressed(KeyCode.PRO_KEY_LEFT)) {
			camera.rotation.rotateY(-0.04f);
		} else if (InputCursor.getDx() < 0) {
			camera.rotation.rotateY(InputCursor.getDx() * camera.speedcam);
		}

		if (Input.isKeyPressed(KeyCode.PRO_KEY_RIGHT)) {
			camera.rotation.rotateY(0.04f);
		} else if (InputCursor.getDx() > 0) {
			camera.rotation.rotateY(InputCursor.getDx() * camera.speedcam);
		}
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

	public Matrix4f getViewProjectionMatrix() {
		this.recalculateViewMatrix();
		return viewProjectionMatrix;
	}

}
