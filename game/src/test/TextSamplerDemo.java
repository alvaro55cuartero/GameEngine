package test;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class TextSamplerDemo extends JPanel {
	protected static final String textFieldString = "JTextField";
	protected static final String passwordFieldString = "JPasswordField";
	protected static final String ftfString = "JFormattedTextField";
	protected static final String buttonString = "JButton";

	public TextSamplerDemo() {

		JTextField textField = new JTextField(10);
		textField.setActionCommand(textFieldString);

		JPasswordField passwordField = new JPasswordField(10);
		passwordField.setActionCommand(passwordFieldString);

		JFormattedTextField ftf = new JFormattedTextField(java.util.Calendar.getInstance().getTime());
		ftf.setActionCommand(textFieldString);

		JLabel textFieldLabel = new JLabel(textFieldString + ": ");
		textFieldLabel.setLabelFor(textField);
		JLabel passwordFieldLabel = new JLabel(passwordFieldString + ": ");
		passwordFieldLabel.setLabelFor(passwordField);
		JLabel ftfLabel = new JLabel(ftfString + ": ");
		ftfLabel.setLabelFor(ftf);

		// Lay out the text controls and the labels.
		JPanel textControlsPane = new JPanel();
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();

		textControlsPane.setLayout(gridbag);

		JLabel[] labels = { textFieldLabel, passwordFieldLabel, ftfLabel };
		JTextField[] textFields = { textField, passwordField, ftf };
		addLabelTextRows(labels, textFields, gridbag, textControlsPane);

		c.gridwidth = GridBagConstraints.REMAINDER; // last
		c.anchor = GridBagConstraints.WEST;
		c.weightx = 1.0;
		textControlsPane.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Text Fields"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		add(textControlsPane);

	}

	private void addLabelTextRows(JLabel[] labels, JTextField[] textFields, GridBagLayout gridbag,
			Container container) {
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.EAST;
		int numLabels = labels.length;

		for (int i = 0; i < numLabels; i++) {
			c.gridwidth = GridBagConstraints.RELATIVE; // next-to-last
			c.fill = GridBagConstraints.NONE; // reset to default
			c.weightx = 0.0; // reset to default
			container.add(labels[i], c);

			c.gridwidth = GridBagConstraints.REMAINDER; // end row
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 1.0;
			container.add(textFields[i], c);
		}
	}

	private static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("TextSamplerDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add content to the window.
		frame.add(new TextSamplerDemo());

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		// Schedule a job for the event dispatching thread:
		// creating and showing this application's GUI.
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// Turn off metal's use of bold fonts
				UIManager.put("swing.boldMetal", Boolean.FALSE);
				createAndShowGUI();
			}
		});
	}
}
