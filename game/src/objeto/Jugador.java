package objeto;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import colision.BoxColider;
import controls.Input;
import graphics.MasterRenderer;
import level.Level;

public class Jugador extends EntityPlane {

	public float speed = 1f;
	public boolean onGround;
	public boolean up = true;
	public boolean down = true;
	public boolean left = true;
	public boolean right = true;
	public Vector2f vel = new Vector2f(0, 0);
	public Vector2f prevel = new Vector2f(0, 0);
	public float friccion = 1f;
	public Vector3f respawnPoint;
	public EntityPlane entity;

	public Jugador(int texturedModelId, Vector3f position, float sx, float sy) {
		super(texturedModelId, position, sx, sy);
		respawn();
	}

	public Jugador(int texturedModelId) {
		this(texturedModelId, new Vector3f(0, 4, 2), 3f, 3f);
	}

	public void tick() {
	}

	public void move(Level level) {

		if (down) {
			vel.y -= 0.06f;
		} else {
			position.y -= vel.y;
			if (vel.y < 0) {
				vel.y = 0;

			}

		}
		if (!down && Input.get(GLFW.GLFW_KEY_SPACE) && vel.y < 50) {
			vel.y += 1.4f;
		}
		if (up) {

		} else {
			if (vel.y > 0) {
				vel.y = 0;

			}
		}
		if (right) {
			if (Input.get(GLFW.GLFW_KEY_D) && vel.x < 10) {
				vel.x += 10;
			}
		} else {
			if (vel.x > 0) {
				vel.x = 0;
			}
		}
		if (left) {
			if (Input.get(GLFW.GLFW_KEY_A) && vel.x > -10) {
				vel.x -= 10;
			}
		} else {
			if (vel.x < 0) {
				vel.x = 0;
			}
		}

		if (vel.x > 0) {
			vel.x -= friccion;
		} else if (vel.x < 0) {
			vel.x += friccion;
		}
		if (position.y < -50) {
			dead();
		}

		this.position.x += vel.x * 0.02;
		position.y += vel.y;

		up = true;
		down = true;
		left = true;
		right = true;
		prevel = vel;
	}

	public void dead() {

		respawn();
	}

	public void respawn() {
		position = new Vector3f(10, 15, 2);

		vel = new Vector2f(0, 0);
	}

	public void render() {
		MasterRenderer.processEntity3D(this);
		MasterRenderer.processEntityDebug(new EntityDebug(
				new Vector3f(position.x + (sx / 2) - 0.1f, position.y - 0.1f, position.z), 0, 0, 0, 0.2f, 0.2f, 0.1f));
		MasterRenderer.processEntityDebug(
				new EntityDebug(new Vector3f(position.x + (sx / 2) - 0.1f, position.y + sy - 0.1f, position.z), 0, 0, 0,
						0.2f, 0.2f, 0.1f));
		MasterRenderer.processEntityDebug(new EntityDebug(
				new Vector3f(position.x - 0.1f, position.y + (sy / 2) - 0.1f, position.z), 0, 0, 0, 0.2f, 0.2f, 0.1f));
		MasterRenderer.processEntityDebug(
				new EntityDebug(new Vector3f(position.x + sx - 0.1f, position.y + (sy / 2) - 0.1f, position.z), 0, 0, 0,
						0.2f, 0.2f, 0.1f));

	}

	public BoxColider[] getColider() {
		BoxColider[] a = new BoxColider[4];
		a[0] = new BoxColider(position.x + sx / 2 - 0.1f, position.y - 0.1f, position.z, 0.2f, 0.2f, 0.1f);
		a[1] = new BoxColider(position.x + sx / 2 - 0.1f, position.y + sy - 0.1f, position.z, 0.2f, 0.2f, 0.1f);
		a[2] = new BoxColider(position.x - 0.1f, position.y + (sx / 2) - 0.1f, position.z, 0.2f, 0.2f, 0.1f);
		a[3] = new BoxColider(position.x + sx - 0.1f, position.y + (sx / 2) - 0.1f, position.z, 0.2f, 0.2f, 0.1f);
		return a;
	}

}
