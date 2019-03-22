package colision;

import java.util.List;

import org.joml.AABBf;
import org.joml.Intersectionf;

import objeto.Jugador;

public class MasterColisions {

	public void testJugado(Jugador jugador, List<BoxColider> coliders) {
		for (int i = 0; i < coliders.size(); i++) {
			test(jugador, coliders.get(i).getColider(), jugador.getColider());
		}
	}

	public void test(Jugador jugador, AABBf a, BoxColider[] b) {
		if (Intersectionf.testAabAab(a, b[0].getColider())) {
			jugador.down = false;
		}
		if (Intersectionf.testAabAab(a, b[1].getColider())) {
			jugador.up = false;
		}
		if (Intersectionf.testAabAab(a, b[2].getColider())) {
			jugador.left = false;
		}
		if (Intersectionf.testAabAab(a, b[3].getColider())) {
			jugador.right = false;
		}
		return;

	}

	public void render() {

	}

}
