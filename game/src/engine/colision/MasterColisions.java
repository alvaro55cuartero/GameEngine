package engine.colision;

import java.util.ArrayList;

import org.joml.AABBf;
import org.joml.Intersectionf;
import org.joml.LineSegmentf;
import org.joml.Vector2f;
import org.joml.Vector3f;

import engine.level.Chunk;
import engine.level.MasterChunk;
import engine.objeto.jugador.Jugador;

public class MasterColisions {

	public void testJugado(Jugador jugador, MasterChunk masterChunk) {
		Chunk[] chunks = masterChunk.getActiveChunks();
		for (Chunk chunk : chunks) {
			testChunk(jugador, chunk);
		}
	}

	private void testChunk(Jugador jugador, Chunk chunk) {
		ArrayList<BoxColider> colidersX = chunk.getColidersX();
		ArrayList<BoxColider> colidersY = chunk.getColidersY();
		ArrayList<BoxColider> colidersGrab = chunk.getColidersGrab();
		for (int i = 0; i < colidersX.size(); i++) {
			testX(jugador, colidersX.get(i).getColider());
		}
		for (int i = 0; i < colidersY.size(); i++) {
			testY(jugador, colidersY.get(i).getColider(), jugador.getColider());
		}
		for (int i = 0; i < colidersGrab.size(); i++) {
			testGrab(jugador, colidersGrab.get(i).getColider());
		}
	}

	private void testGrab(Jugador jugador, AABBf a) {

	}

	private void testX(Jugador jugador, AABBf a) {
		Vector3f pos = jugador.getColider()[3];
		Vector3f pos2 = new Vector3f(pos).add(jugador.getVel());
		LineSegmentf line = new LineSegmentf(pos, pos2);

		Vector2f r = new Vector2f();
		if (Intersectionf.intersectLineSegmentAab(line, a, r) == Intersectionf.ONE_INTERSECTION) {
			jugador.getVel().x = ((pos2.x - pos.x) * Math.min(r.x, r.y));
			jugador.setCanRight(false);
		} else if (Intersectionf.intersectLineSegmentAab(line, a, r) == Intersectionf.TWO_INTERSECTION) {
			jugador.getVel().x = ((pos2.x - pos.x) * Math.min(r.x, r.y));
			jugador.setCanRight(false);
		} else if (Intersectionf.intersectLineSegmentAab(line, a, r) == Intersectionf.INSIDE) {
			jugador.setCanRight(false);
		}
	}

	private void testY(Jugador jugador, AABBf a, Vector3f[] b) {
		Vector3f pos = b[0];
		Vector3f pos2 = new Vector3f(pos).add(jugador.getVel());
		LineSegmentf line = new LineSegmentf(pos, pos2);

		Vector2f r = new Vector2f();
		if (Intersectionf.intersectLineSegmentAab(line, a, r) == Intersectionf.ONE_INTERSECTION) {
			jugador.getVel().y = ((pos2.y - pos.y) * Math.min(r.x, r.y));
			if (jugador.getVel().y > 0) {
				jugador.setCanUp(false);
			} else {
				jugador.setCanDown(false);
			}
		} else if (Intersectionf.intersectLineSegmentAab(line, a, r) == Intersectionf.TWO_INTERSECTION) {
			jugador.getVel().y = ((pos2.y - pos.y) * Math.min(r.x, r.y));
			jugador.setCanDown(false);
		} else if (Intersectionf.intersectLineSegmentAab(line, a, r) == Intersectionf.INSIDE) {
			jugador.setCanDown(false);
		}
	}

}
