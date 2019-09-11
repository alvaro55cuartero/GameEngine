package engine.debug;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class DebugConsole implements Runnable {
	JFrame frame;
	JPanel plane;
	Thread thread;
	public Grafica grafica;

	boolean loop = true;

	public DebugConsole() {

		thread = new Thread(this);
		thread.start();
		grafica = new Grafica();
	}

	public void run() {
		frame = new JFrame();
		plane = new JPanel() {
			public void paint(Graphics g) {
				render(g);
			}
		};

		frame.setSize(600, 400);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				exitProcedure();
			}
		});

		plane.setBackground(Color.BLACK);
		frame.add(plane);
		frame.setVisible(true);

		while (loop) {
			plane.repaint();
		}
	}

	public void tick() {

	}

	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 600, 400);
		g.setColor(Color.green);

		grafica.render(g);

	}

	public void exitProcedure() {
		loop = false;
		frame.dispose();
	}

}
