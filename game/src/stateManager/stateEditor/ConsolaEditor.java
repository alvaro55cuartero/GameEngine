package stateManager.stateEditor;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.joml.Vector3f;

import objeto.entities.Entity3DPlane;

public class ConsolaEditor implements Runnable, ActionListener {

	private JFrame frame;
	private JPanel panel;
	private JTextField fieldid;
	private JTextField fieldx;
	private JTextField fieldy;
	private JTextField fieldz;
	private JTextField fieldDimx;
	private JTextField fieldDimy;
	private JTextField fieldRotx;
	private JTextField fieldRoty;
	private JTextField fieldRotz;
	private Thread thread;
	public Entity3DPlane entity;

	public ConsolaEditor() {
		thread = new Thread(this);
		thread.start();
	}

	public void run() {
		frame = new JFrame("Consola Editor");
		panel = new JPanel();
		fieldid = new JTextField("0", 10);
		fieldx = new JTextField("0", 10);
		fieldy = new JTextField("0", 10);
		fieldz = new JTextField("0", 10);
		fieldDimx = new JTextField("1", 10);
		fieldDimy = new JTextField("1", 10);
		fieldRotx = new JTextField("0", 10);
		fieldRoty = new JTextField("0", 10);
		fieldRotz = new JTextField("0", 10);

		fieldid.addActionListener(this);
		fieldx.addActionListener(this);
		fieldy.addActionListener(this);
		fieldz.addActionListener(this);
		fieldDimx.addActionListener(this);
		fieldDimy.addActionListener(this);
		fieldRotx.addActionListener(this);
		fieldRoty.addActionListener(this);
		fieldRotz.addActionListener(this);

		panel.add(fieldid);
		panel.add(fieldx);
		panel.add(fieldy);
		panel.add(fieldz);
		panel.add(fieldDimx);
		panel.add(fieldDimy);
		panel.add(fieldRotx);
		panel.add(fieldRoty);
		panel.add(fieldRotz);
		frame.add(panel);
		frame.setSize(150, 400);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				exitProcedure();
			}
		});

		panel.setBackground(Color.BLACK);
		frame.setVisible(true);
		fillEntity();
	}

	private void exitProcedure() {
		frame.dispose();

	}

	public void actionPerformed(ActionEvent e) {
		fillEntity();
	}

	private void fillEntity() {
		entity = new Entity3DPlane(Integer.parseInt(fieldid.getText()),
				new Vector3f(Float.parseFloat(fieldx.getText()), Float.parseFloat(fieldy.getText()),
						Float.parseFloat(fieldz.getText())),
				Float.parseFloat(fieldRotx.getText()), Float.parseFloat(fieldRoty.getText()),
				Float.parseFloat(fieldRotz.getText()), Float.parseFloat(fieldDimy.getText()),
				Float.parseFloat(fieldDimy.getText()));
	}

}
