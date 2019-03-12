package objeto;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import controls.Input;
import graphics.MasterRenderer;
import level.Level;
import main.Camera;

public class Jugador extends EntityPlane {

	public float speed = 1f;
	public boolean onGround;
	public Vector2f vel = new Vector2f(0, 0);
	public float friccion = 1f;
	public Vector3f respawnPoint;

	public Jugador(int texturedModelId, Vector3f position, float sx, float sy) {
		super(texturedModelId, position, sx, sy, false);
	}

	public Jugador(int texturedModelId) {
		this(texturedModelId, new Vector3f(0, 4, 2), 3f, 3f);
	}

	public void render(Camera camera) {
		MasterRenderer.processEntity3D(this);
		MasterRenderer.processEntity3D(new EntityPlane(15, this.position, this.sx, this.sy, false));
	}

	public void tick() {

	}

	public void onGround(Level level) {
		if (this.position.y < -7.5) {
			onGround = true;

		}
	}

	public void move(Level level) {

		position.x += vel.x * 0.02;
		position.y += vel.y;

		if (vel.x > 0) {
			vel.x -= friccion;
		} else if (vel.x < 0) {
			vel.x += friccion;
		}

		if (Input.get(GLFW.GLFW_KEY_D) && vel.x < 10) {
			vel.x += 10;
		}
		if (Input.get(GLFW.GLFW_KEY_A) && vel.x > -10) {
			vel.x -= 10;
		}
		if (onGround && Input.get(GLFW.GLFW_KEY_SPACE) && vel.y < 50) {
			vel.y += 1.1f;
		} else if (!onGround) {
			vel.y -= 0.05;
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
		position = new Vector3f(10, 15, 2);

		vel = new Vector2f(0, 0);
	}

}
