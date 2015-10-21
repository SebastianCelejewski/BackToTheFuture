package pl.sebcel.timecircuits.gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TimePanelSection extends JPanel {

	private static final long serialVersionUID = 1L;

	private JPanel labelPanel = new JPanel();
	private JLabel label = new JLabel();
	private JPanel digitDisplaysPanel = new JPanel();

	private int numberOfDigits = 0;
	private DigitDisplay[] digitDisplays;

	private int value;

	public TimePanelSection(int numberOfDigits, Color digitsColour, String label) {
		this.numberOfDigits = numberOfDigits;
		digitDisplaysPanel.add(new JLabel(" "));
		digitDisplays = new DigitDisplay[numberOfDigits];
		for (int i = 0; i < numberOfDigits; i++) {
			digitDisplays[i] = new DigitDisplay(digitsColour);
			digitDisplaysPanel.add(digitDisplays[i]);
		}

		this.setLayout(new BorderLayout());
		this.add(digitDisplaysPanel, BorderLayout.CENTER);
		digitDisplaysPanel.setBackground(LookAndFeel.DIGITS_DISPLAY_BACKGROUND_COLOUR);
		this.setBackground(LookAndFeel.PANEL_BACKGROUND_COLOUR);
		this.setBorder(BorderFactory.createLoweredBevelBorder());

		if (label != null) {
			labelPanel.add(this.label);
			this.add(labelPanel, BorderLayout.NORTH);
			this.label.setHorizontalAlignment(0);
			labelPanel.setBackground(LookAndFeel.DIGITS_SECTION_LABEL_BACKGROUND_COLOUR);
			this.label.setForeground(LookAndFeel.LABEL_TEXT_COLOUR);
			this.label.setText(label);
		}
	}

	public void setValue(int value) {
		this.value = value;
		this.repaint();
	}

	@Override
	public void repaint() {
		if (value < 0) {
			throw new IllegalArgumentException("Value " + value + " is out of range.");
		}

		if (value >= Math.pow(10, numberOfDigits)) {
			throw new IllegalArgumentException("Value " + value + " is out of range.");
		}

		if (numberOfDigits == 2) {
			int decimals = value / 10;
			digitDisplays[0].setValue(decimals);

			int units = value % 10;
			digitDisplays[1].setValue(units);
		}

		int temp = value;

		if (numberOfDigits == 4) {
			int thousands = temp / 1000;
			digitDisplays[0].setValue(thousands);

			temp = temp - thousands * 1000;
			int houndreds = temp / 100;
			digitDisplays[1].setValue(houndreds);

			temp = temp - houndreds * 100;
			int decimals = temp / 10;
			digitDisplays[2].setValue(decimals);

			temp = temp - decimals * 10;
			int units = temp / 1;
			digitDisplays[3].setValue(units);
		}

		super.repaint();
	}

	protected void addDigitDisplay(DigitDisplay display) {
		this.digitDisplaysPanel.add(display);
	}

	public void increaseValue() {
		value++;
		this.repaint();
	}

	public void decreaseValue() {
		value--;
		this.repaint();
	}

	public void on() {
		for (DigitDisplay display : digitDisplays) {
			display.on();
		}
	}

	public void off() {
		for (DigitDisplay display : digitDisplays) {
			display.off();
		}
	}
}