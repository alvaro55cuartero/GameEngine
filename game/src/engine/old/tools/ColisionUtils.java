package engine.old.tools;

public class ColisionUtils {

	public static boolean betwen(int a, int b, int c) {
		boolean r = false;
		if (a > b && b > c) {
			r = true;
		}
		return r;
	}

	public static boolean inside(int x1, int y1, int xp, int yp, int x2, int y2) {
		boolean r = false;
		if (betwen(x1, xp, x2) && betwen(y1, yp, y2)) {
			r = true;
		}
		return r;
	}

	public static boolean wall() {
		return false;
	}

	public static boolean ceillin() {
		return false;
	}

}
