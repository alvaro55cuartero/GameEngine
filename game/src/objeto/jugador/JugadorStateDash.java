package objeto.jugador;

import objeto.jugador.Jugador.State;

public class JugadorStateDash extends JugadorState {

	public JugadorStateDash(Jugador jugador) {
		super(jugador);
		System.out.println("DASH");
		jugador.getVel().x += 2f;
	}

	public void input() {
		if (jugador.getVel().x == 0) {
			jugador.setState(State.State_Idle);
		}
		if (!jugador.isCanRight()) {
			jugador.setState(State.State_Idle);
		}
		if (!jugador.isCanLeft()) {
			jugador.setState(State.State_Idle);
		}

	}

	public void tick() {
		if (jugador.isCanDown()) {
			jugador.setState(State.State_On_Air);
		}
	}

	public void render() {

	}

	public void reset() {

	}
}
