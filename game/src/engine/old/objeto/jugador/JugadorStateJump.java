package engine.old.objeto.jugador;

import engine.old.objeto.jugador.Jugador.State;

public class JugadorStateJump extends JugadorState {

	public JugadorStateJump(Jugador jugador) {
		super(jugador);
	}

	public void input() {
		jugador.setState(State.State_On_Air);
	}

	public void tick() {
		jugador.getVel().y += 1.6f;
	}

	public void render() {

	}

	public void reset() {

	}
}
