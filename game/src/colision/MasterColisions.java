package colision;

import java.util.List;

import org.joml.Intersectionf;
import org.joml.Rectanglef;
import org.joml.Vector2f;
import org.joml.Vector3f;

import objeto.Entity3D;
import objeto.Jugador;

public class MasterColisions {

	public MasterColisions() {

	}

	public void testJugador(Jugador jugador, List<Entity3D> coliders) {
		for (int i = 0; i < coliders.size(); i++) {
			if (coliders.get(i).isSolid()) {
				if (test(coliders.get(i).getRectf(), jugador.getRectf())) {

					jugador.onGround = true;
					jugador.setPosition(new Vector3f(jugador.getPosition().x, jugador.getPosition().y - jugador.vel.y,
							jugador.getPosition().z));
					jugador.vel = new Vector2f(jugador.vel.x, 0);

				}
			}
		}
	}

	public boolean test(Rectanglef a, Rectanglef b) {
		return Intersectionf.testAarAar(a.minX, a.minY, a.maxX, a.maxY, b.minX, b.minY, b.maxX, b.maxY);
	}

	public void render() {

	}
}
