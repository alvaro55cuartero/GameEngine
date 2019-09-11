package engine.objeto.jugador;

import engine.objeto.jugador.Jugador.State;

public class JugadorStateOnAir extends JugadorState {

	public JugadorStateOnAir(Jugador jugador) {
		super(jugador);
		System.out.println("onAir");

	}

	public void input() {
		jugador.getVel().y -= 0.1;
	}

	public void tick() {
		if (jugador.getVel().x > 0) {
			jugador.getVel().x -= 0.1f;
		} else if (jugador.getVel().x < 0) {
			jugador.getVel().x += 0.1f;
		}
		if (jugador.isGoRight() && jugador.isWalk() && jugador.isCanRight()) {
			jugador.getVel().x = 0.2f;
		}
		if (jugador.isGoLeft() && jugador.isWalk() && jugador.isCanLeft()) {
			jugador.getVel().x = -0.2f;
		}
		if (jugador.isRun() && jugador.isGoRight() && jugador.isCanRight()) {
			jugador.getVel().x = 0.4f;
		}
		if (jugador.isRun() && jugador.isGoLeft() && jugador.isCanLeft()) {
			jugador.getVel().x = -0.4f;
		}
		if (jugador.isDash()) {
			jugador.setState(State.State_Dash);
		}
		if (!jugador.isCanDown()) {
			jugador.setState(State.State_Idle);
			if (jugador.isRun()) {
				jugador.setState(State.State_Run);
			}
			if (jugador.isWalk()) {
				jugador.setState(State.State_Walk);
			}
		}
		if (jugador.getVel().x > 1) {
			jugador.getVel().x = 1;
		}
		if (jugador.getVel().x < -1) {
			jugador.getVel().x = -1;
		}
	}

	public void render() {

	}

	public void reset() {

	}

}
