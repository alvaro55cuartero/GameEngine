package colision;

public class Colider {

	public static boolean intersects(BoxColider a, BoxColider b) {
		if ((a.getX() - b.getX()) > (a.getHeight() + b.getHeight()))
			return false;
		if ((a.getY() - b.getY()) > (a.getWidth() + b.getWidth()))
			return false;

		return true;
	}
}
