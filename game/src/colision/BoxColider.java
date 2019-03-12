package colision;

import org.joml.Rectanglef;

public class BoxColider extends Colider {

	private Rectanglef rect;
	private float capa;

	public BoxColider(float minX, float minY, float maxX, float maxY, float capa) {
		rect = new Rectanglef(minX, minY, maxX, maxY);
		this.setCapa(capa);
	}

	public Rectanglef getRect() {
		return rect;
	}

	public void setRect(Rectanglef rect) {
		this.rect = rect;
	}

	public float getCapa() {
		return capa;
	}

	public void setCapa(float capa) {
		this.capa = capa;
	}

}
