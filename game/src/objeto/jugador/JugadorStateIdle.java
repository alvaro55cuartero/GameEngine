package objeto.jugador;

import objeto.jugador.Jugador.State;

public class JugadorStateIdle extends JugadorState {

	public JugadorStateIdle(Jugador jugador) {
		super(jugador);
		// jugador.setTextureIndex(0);
		System.out.println("idle");
	}

	public void input() {
		if (jugador.isWalk()) {
			jugador.setState(State.State_Walk);
		}
		if (jugador.isRun()) {
			jugador.setState(State.State_Run);
		}
		if (jugador.isJump()) {
			jugador.setState(State.State_Jump);
		}
		if (jugador.isDash()) {
			jugador.setState(State.State_Dash);
		}
	}

	public void tick() {
		if (jugador.isCanDown()) {
			jugador.setState(State.State_On_Air);
		}
		jugador.getVel().x = 0;
		jugador.getVel().y = 0;

		if (jugador.getPosition().y < -50) {
			jugador.dead();
		}
	}

	public void render() {

	}

	public void reset() {

	}

}
