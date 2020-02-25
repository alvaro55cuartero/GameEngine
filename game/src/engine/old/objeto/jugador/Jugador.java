package engine.old.objeto.jugador;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import engine.old.controls.old.Input;
import engine.old.controls.old.InputControler;
import engine.old.graphics.old.MasterRenderer;
import engine.old.objeto.entities.Entity3DPlane;
import engine.old.objeto.entities.EntityDebug;

public class Jugador extends Entity3DPlane {

	private int dashCounter = 0;
	private int dashMax = 30;

	private float speed = 1f;
	private float friccion = 1f;

	private boolean canUp = true;
	private boolean canDown = true;
	private boolean canLeft = true;
	private boolean canRight = true;

	private boolean goLeft = false;
	private boolean goRight = false;

	private boolean dashReady = true;
	private boolean walk = false;
	private boolean run = false;
	private boolean dash = false;
	private boolean jump = false;
	private boolean change = false;
	private boolean onGround = false;

	private Vector3f vel = new Vector3f(0, 0, 0);
	private Vector3f respawnPoint;

	private JugadorState jugadorState;
	private State currentState = State.State_Idle;

	public enum State {
		State_Idle, State_Walk, State_Run, State_On_Air, State_Jump, State_Dash
	};

	public Jugador(int texturedModelId, Vector3f position, float sx, float sy) {
		super(texturedModelId, position, sx, sy);
		setState(State.State_Idle);
		respawn();
	}

	public Jugador(int texturedModelId) {
		this(texturedModelId, new Vector3f(0, 4, 2), 3f, 3f);
	}

	public void input() {
		change();
		estandarInput();
		jugadorState.input();
	}

	public void tick() {
		jugadorState.tick();
		if (position.y < -50) {
			respawn();
		}

		position.add(vel);
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
		jugadorState.render();

	}

	public void reset() {
		jugadorState.reset();
		setCanUp(true);
		setCanDown(true);
		setCanLeft(true);
		setCanRight(true);
		setGoLeft(false);
		setGoRight(false);
		setRun(false);
		setWalk(false);
		setJump(false);
		setDash(false);
	}

	public void setState(State state) {
		currentState = state;
		change = true;
	}

	public void change() {
		if (change) {
			switch (currentState) {
			case State_Idle:
				jugadorState = new JugadorStateIdle(this);
				break;
			case State_Walk:
				jugadorState = new JugadorStateWalk(this);
				break;
			case State_Run:
				jugadorState = new JugadorStateRun(this);
				break;
			case State_Jump:
				jugadorState = new JugadorStateJump(this);
				break;
			case State_On_Air:
				jugadorState = new JugadorStateOnAir(this);
				break;
			case State_Dash:
				jugadorState = new JugadorStateDash(this);
				break;
			default:
				jugadorState = new JugadorStateIdle(this);
				break;
			}
			change = false;
		}
	}

	public void estandarInput() {
		if (Input.get(GLFW.GLFW_KEY_D) || InputControler.axis(0, 0.5f, 0.1f)) {
			setGoRight(true);
			setWalk(true);
		}
		if (Input.get(GLFW.GLFW_KEY_A) || InputControler.axis(0, -0.1f, -0.5f)) {
			setGoLeft(true);
			setWalk(true);
		}
		if ((Input.get(GLFW.GLFW_KEY_D) && Input.get(GLFW.GLFW_KEY_LEFT_SHIFT)) || InputControler.axis(0, 1, 0.5f)) {
			setGoRight(true);
			setRun(true);
		}
		if ((Input.get(GLFW.GLFW_KEY_A) && Input.get(GLFW.GLFW_KEY_LEFT_SHIFT)) || InputControler.axis(0, -0.5f, -1)) {
			setGoLeft(true);
			setRun(true);
		}
		if (Input.get(GLFW.GLFW_KEY_SPACE) || InputControler.press(0)) {
			setJump(true);
		}

		if ((Input.get(GLFW.GLFW_KEY_S) || InputControler.press(3)) && isDashReady()) {
			setDash(true);
			setDashReady(false);
		}

		if (!isDashReady()) {
			dashCounter++;
			if (dashCounter == dashMax) {
				setDashReady(true);
				dashCounter = 0;
			}
		}
	}

	public void dead() {
		respawn();
	}

	public void respawn() {
		position = new Vector3f(10, 15, 2);
		setVel(new Vector3f(0, 0, 0));
	}

	public Vector3f[] getColider() {
		Vector3f[] a = new Vector3f[4];
		// BoxColider[] a = new BoxColider[4];
		// a[0] = new BoxColider(position.x + sx / 2 - 0.1f, position.y - 0.1f,
		// position.z, 0.2f, 0.2f, 0.1f);
		// a[1] = new BoxColider(position.x + sx / 2 - 0.1f, position.y + sy - 0.1f,
		// position.z, 0.2f, 0.2f, 0.1f);
		// a[2] = new BoxColider(position.x - 0.1f, position.y + (sx / 2) - 0.1f,
		// position.z, 0.2f, 0.2f, 0.1f);
		// a[3] = new BoxColider(position.x + sx - 0.1f, position.y + (sx / 2) - 0.1f,
		// position.z, 0.2f, 0.2f, 0.1f);
		a[0] = new Vector3f(position.x + sx / 2, position.y, position.z);
		a[1] = new Vector3f(position.x + sx / 2, position.y + sy, position.z);
		a[2] = new Vector3f(position.x, position.y + (sx / 2), position.z);
		a[3] = new Vector3f(position.x + sx, position.y + (sx / 2), position.z);
		return a;
	}

	public Vector3f getVel() {
		return vel;
	}

	public void setVel(Vector3f vel) {
		this.vel = vel;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getFriccion() {
		return friccion;
	}

	public void setFriccion(float friccion) {
		this.friccion = friccion;
	}

	public boolean isCanUp() {
		return canUp;
	}

	public void setCanUp(boolean canUp) {
		this.canUp = canUp;
	}

	public boolean isCanDown() {
		return canDown;
	}

	public void setCanDown(boolean canDown) {
		this.canDown = canDown;
	}

	public boolean isCanLeft() {
		return canLeft;
	}

	public void setCanLeft(boolean canLeft) {
		this.canLeft = canLeft;
	}

	public boolean isCanRight() {
		return canRight;
	}

	public void setCanRight(boolean canRight) {
		this.canRight = canRight;
	}

	public boolean isChange() {
		return change;
	}

	public void setChange(boolean change) {
		this.change = change;
	}

	public boolean isOnGround() {
		return onGround;
	}

	public void setOnGround(boolean onGround) {
		this.onGround = onGround;
	}

	public Vector3f getRespawnPoint() {
		return respawnPoint;
	}

	public void setRespawnPoint(Vector3f respawnPoint) {
		this.respawnPoint = respawnPoint;
	}

	public JugadorState getJugadorState() {
		return jugadorState;
	}

	public void setJugadorState(JugadorState jugadorState) {
		this.jugadorState = jugadorState;
	}

	public State getCurrentState() {
		return currentState;
	}

	public void setCurrentState(State currentState) {
		this.currentState = currentState;
	}

	public boolean isGoLeft() {
		return goLeft;
	}

	public void setGoLeft(boolean goLeft) {
		this.goLeft = goLeft;
	}

	public boolean isGoRight() {
		return goRight;
	}

	public void setGoRight(boolean goRight) {
		this.goRight = goRight;
	}

	public boolean isJump() {
		return jump;
	}

	public void setJump(boolean jump) {
		this.jump = jump;
	}

	public boolean isDash() {
		return dash;
	}

	public void setDash(boolean dash) {
		this.dash = dash;
	}

	public boolean isDashReady() {
		return dashReady;
	}

	public void setDashReady(boolean dashReady) {
		this.dashReady = dashReady;
	}

	public boolean isWalk() {
		return walk;
	}

	public void setWalk(boolean walk) {
		this.walk = walk;
	}

	public boolean isRun() {
		return run;
	}

	public void setRun(boolean run) {
		this.run = run;
	}

}
