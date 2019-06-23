package debug;

import java.awt.Graphics;
import java.util.ArrayList;

import org.joml.Vector2f;

public class Grafica {

	Vector2f pos = new Vector2f(10, 10);
	Vector2f dim = new Vector2f(410, 210);

	int cap = 8;
	int count = 0;
	long sum = 0;

	ArrayList<Long> time = new ArrayList<Long>();
	Long temp = System.nanoTime();

	public void render(Graphics g) {
		g.drawRect((int) pos.x, (int) pos.y, (int) dim.x, (int) dim.y);
		if (time.size() > 4) {
			for (int i = 0; i < time.size() - 1; i++) {
				g.drawLine((int) (pos.x + i), (int) (pos.y + Math.toIntExact(time.get(i))), (int) (pos.x + i + 1),
						(int) (pos.y + Math.toIntExact(time.get(i + 1))));

			}
		}
	}

	public synchronized void add() {
		long a = System.nanoTime();
		if (count == cap) {
			if (!(time.size() < 410)) {
				time.remove(0);
			}

			count = 0;
			time.add((long) ((sum / cap) / 1000000));
			sum = 0;
		} else {
			sum += a - temp;
		}
		count++;
		temp = a;
	}
}
