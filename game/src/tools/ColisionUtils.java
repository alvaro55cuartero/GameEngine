package tools;

import models.Plane;

public class ColisionUtils {

	public boolean betwen(int a, int b, int c) {
		boolean r = false;
		if (a > b && b > c) {
			r = true;
		}
		return r;
	}

	public static boolean ground(Plane e, Plane f) {
		if (e.getPosition().x > f.getPosition().x && e.getPosition().getX() < f.getPosition().getX() + f.getSx()
				|| e.getPosition().getX() + e.getSx() > f.getPosition().x
						&& e.getPosition().getX() + e.getSx() < f.getPosition().getX() + f.getSx()) {

			if (e.getPosition().y <= f.getPosition().y + f.getSy()
					&& e.getPosition().getY() + e.getSy() > f.getPosition().getY()) {
				if (e.getPosition().getZ() == f.getPosition().getZ()) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean wall() {
		return false;
	}

	public static boolean ceillin() {
		return false;
	}

}
