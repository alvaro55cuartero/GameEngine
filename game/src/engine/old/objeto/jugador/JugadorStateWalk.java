package engine.old.objeto.jugador;

import engine.old.objeto.jugador.Jugador.State;

public class JugadorStateWalk extends JugadorState {

	public int index = 0;
	public int count = 0;

	public JugadorStateWalk(Jugador jugador) {
		super(jugador);
		System.out.println("walk");
	}

	public void input() {
		if (!jugador.isWalk()) {
			jugador.setState(State.State_Idle);
		}
		if (jugador.isJump()) {
			jugador.setState(State.State_Jump);
		}
		if (jugador.isRun()) {
			jugador.setState(State.State_Run);
		}
	}

	public void tick() {
		if (jugador.isCanDown()) {
			jugador.setState(State.State_On_Air);
		}
		if (jugador.isGoRight() && jugador.isCanRight()) {
			jugador.getVel().x = 0.05f;
		}
		if (jugador.isGoLeft() && jugador.isCanLeft()) {
			jugador.getVel().x = -0.05f;
		}

	}

	public void render() {
		if (index == 9) {
			index = 1;
		}
		// jugador.setTextureIndex(index);
		if (count == 3) {
			index++;
			count = 0;
		}
		count++;

	}

	public void reset() {

	}
}
