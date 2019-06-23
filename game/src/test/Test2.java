package test;

import org.joml.AABBf;
import org.joml.Intersectionf;
import org.joml.LineSegmentf;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Test2 {

	static AABBf a = new AABBf(0, 0, 0, 10, 1, 2);
	static Vector3f pos = new Vector3f(1, 1f, 1);
	static Vector3f vel = new Vector3f(0, 0f, 0);

	public static void main(String[] args) {
		while (true) {
			Vector3f pos2 = new Vector3f(pos).add(vel);
			LineSegmentf line = new LineSegmentf(pos, pos2);
			Vector2f r = new Vector2f();
			if (Intersectionf.intersectLineSegmentAab(line, a, r) == Intersectionf.ONE_INTERSECTION) {
				pos.y += ((pos2.y - pos.y) * Math.min(r.x, r.y));
				System.out.println("one " + r);
			} else if (Intersectionf.intersectLineSegmentAab(line, a, r) == Intersectionf.TWO_INTERSECTION) {
				pos.y += ((pos2.y - pos.y) * Math.min(r.x, r.y));
				System.out.println("two" + r);
			} else if (Intersectionf.intersectLineSegmentAab(line, a, r) == Intersectionf.INSIDE) {
				System.out.println("in " + r);
			} else if (Intersectionf.intersectLineSegmentAab(line, a, r) == Intersectionf.OUTSIDE) {
				System.out.println("out");
				pos.add(vel);
			}
			System.out.println(pos.y);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
