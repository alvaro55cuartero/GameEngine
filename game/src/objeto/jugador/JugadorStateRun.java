package objeto.jugador;

import objeto.jugador.Jugador.State;

public class JugadorStateRun extends JugadorState {

	public JugadorStateRun(Jugador jugador) {
		super(jugador);
		System.out.println("Run");
	}

	public void input() {
		if (!jugador.isRun()) {
			jugador.setState(State.State_Idle);
		}
		if (jugador.isJump()) {
			jugador.setState(State.State_Jump);
		}

	}

	public void tick() {
		if (jugador.isCanDown()) {
			jugador.setState(State.State_On_Air);
		}
		if (jugador.isGoRight() && jugador.isCanRight()) {
			jugador.getVel().x = 0.5f;
		}
		if (jugador.isGoLeft() && jugador.isCanLeft()) {
			jugador.getVel().x = -0.5f;
		}
		if (jugador.isDash()) {
			jugador.setState(State.State_Dash);
		}
	}

	public void render() {

	}

	public void reset() {

	}

}
