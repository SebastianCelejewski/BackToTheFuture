package pl.sebcel.timecircuits.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JComponent;

public class DigitDisplay extends JComponent {

	private Set<Integer> createSet(int... values) {
		Set<Integer> result = new HashSet<Integer>();
		return result;
	}

	private static enum Segment {
		TOP(0, 2, 3, 5, 6, 7, 8, 9), MIDDLE(2, 3, 4, 5, 6, 8, 9), BOTTOM(0, 2, 3, 5, 6, 8, 9), TOPLEFT(0, 4, 5, 6, 8, 9), TOPRIGHT(0, 1, 2, 3, 4, 7, 8, 9), BOTTOMLEFT(0, 2, 6, 8), BOTTOMRIGHT(0, 1, 3, 4, 5, 6, 7, 8, 9);
		Set<Integer> acceptedValues = new HashSet<>();

		Segment(int... values) {
			for (int value : values) {
				acceptedValues.add(value);
			}
		}

		public boolean isShown(int value) {
			return acceptedValues.contains(value);
		}

	};

	private static final long serialVersionUID = 1L;

	private boolean enabled;
	private int value;
	private Color digitsColour;

	public void setValue(int value) {
		this.value = value;

	}

	public DigitDisplay(Color digitsColour) {
		this.digitsColour = digitsColour;
		this.setSize(20, 40);
		this.setMinimumSize(new Dimension(20, 40));
		this.setPreferredSize(new Dimension(20, 40));
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(LookAndFeel.DIGITS_DISPLAY_BACKGROUND_COLOUR);
		g.fillRect(0, 0, 20, 40);

		if (enabled) {
			g.setColor(digitsColour);

			for (Segment segment : Segment.values()) {
				if (segment.isShown(value)) {
					showSegment(segment, g);
				}
			}
		}
	}

	private void showSegment(Segment segment, Graphics g) {
		switch (segment) {
		case TOP:
			g.drawLine(2, 1, 16, 1);
			g.drawLine(2, 2, 16, 2);
			g.drawLine(2, 3, 16, 3);
			break;
		case MIDDLE:
			g.drawLine(2, 19, 16, 19);
			g.drawLine(2, 20, 16, 20);
			g.drawLine(2, 21, 16, 21);
			break;
		case BOTTOM:
			g.drawLine(2, 36, 16, 36);
			g.drawLine(2, 37, 16, 37);
			g.drawLine(2, 38, 16, 38);
			break;
		case TOPLEFT:
			g.drawLine(1, 3, 1, 18);
			g.drawLine(2, 3, 2, 18);
			g.drawLine(3, 3, 3, 18);
			break;
		case TOPRIGHT:
			g.drawLine(15, 3, 15, 18);
			g.drawLine(16, 3, 16, 18);
			g.drawLine(17, 3, 17, 18);
			break;
		case BOTTOMLEFT:
			g.drawLine(1, 23, 1, 36);
			g.drawLine(2, 23, 2, 36);
			g.drawLine(3, 23, 3, 36);
			break;
		case BOTTOMRIGHT:
			g.drawLine(15, 23, 15, 36);
			g.drawLine(16, 23, 16, 36);
			g.drawLine(17, 23, 17, 36);
		}
	}

	public void off() {
		enabled = false;
		this.repaint();
	}

	public void on() {
		enabled = true;
		this.repaint();
	}
}