package objeto;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import controls.Input;
import graphics.MasterRenderer;
import level.Level;
import main.Camera;

public class Jugador extends PlaneEntity {

	public float speed = 1f;
	public boolean onGround;
	public Vector2f vel = new Vector2f(0, 0);
	public float friccion = 1f;
	public Vector3f respawnPoint;

	public Jugador(int texturedModelId, Vector3f position, float sx, float sy) {
		super(texturedModelId, position, sx, sy);
	}

	public Jugador(int texturedModelId) {
		this(texturedModelId, new Vector3f(1, 4, -4), 3f, 3f);
	}

	public void render(Camera camera) {
		MasterRenderer.processEntity3D(this);
	}

	public void onGround(Level level) {
		if (this.position.y < -7.5) {
			onGround = true;

		}
	}

	public void move(Level level) {
		onGround(level);
		position.x += vel.x * 0.01;
		position.y += vel.y * 0.01;

		if (vel.x > 0) {
			vel.x -= friccion;
		} else if (vel.x < 0) {
			vel.x += friccion;
		}

		if (Input.get(GLFW.GLFW_KEY_D) && vel.x < 10) {
			vel.x += 5;
		}
		if (Input.get(GLFW.GLFW_KEY_A) && vel.x > -10) {
			vel.x -= 5;
		}
		if (onGround && Input.get(GLFW.GLFW_KEY_SPACE) && vel.y < 50) {
			vel.y += 6;
		} else if (!onGround) {
			vel.y -= 2;
		} else {
			vel.y = 0;
		}
		if (position.y < -50) {
			dead();
		}

		onGround = false;
	}

	public void dead() {

		respawn();
	}

	public void respawn() {
		position = new Vector3f(1, 4, -5);

		vel = new Vector2f(0, 0);
	}

}
