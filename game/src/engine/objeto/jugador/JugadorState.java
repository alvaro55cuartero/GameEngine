package engine.objeto.jugador;

public abstract class JugadorState {

	protected Jugador jugador;

	public JugadorState(Jugador jugador) {
		this.jugador = jugador;
	}

	public abstract void input();

	public abstract void tick();

	public abstract void render();

	public abstract void reset();

}
