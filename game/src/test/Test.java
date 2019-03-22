package test;

import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import controls.Input;

public class Test {

	Quaternionf rotation = new Quaternionf();
	boolean loop = true;

	public static void main(String[] args) {
		Test test = new Test();
		while (test.loop) {
			test.tick();
			System.out.println(test.getEuler());
		}

	}

	public void tick() {
		if (Input.get(GLFW.GLFW_KEY_UP) && getRoll() > -90) {
			rotation.rotateY(0.1f);
		}
		if (Input.get(GLFW.GLFW_KEY_DOWN) && getRoll() < 90) {
			rotation.rotateY(-0.1f);
		}

		if (Input.get(GLFW.GLFW_KEY_LEFT)) {
			rotation.rotateX(0.1f);
		}

		if (Input.get(GLFW.GLFW_KEY_RIGHT)) {
			rotation.rotateX(-0.1f);
		}
		if (Input.get(GLFW.GLFW_KEY_ESCAPE)) {
			loop = false;
		}
	}

	public float getPitch() {
		return getEuler().x;
	}

	public float getYaw() {
		return getEuler().y;
	}

	public float getRoll() {
		return getEuler().z;
	}

	public Vector3f getEuler() {
		return rotation.getEulerAnglesXYZ(new Vector3f());
	}
}
