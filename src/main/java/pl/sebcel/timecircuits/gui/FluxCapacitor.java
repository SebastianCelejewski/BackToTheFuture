package pl.sebcel.timecircuits.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class FluxCapacitor extends JComponent {

	private static final long serialVersionUID = 1L;

	public FluxCapacitor() {
		this.setPreferredSize(new Dimension(200, 200));
		this.setBackground(Color.BLACK);
	}

	@Override
	public void paint(Graphics g) {

		g.setColor(Color.gray);
		g.fillOval(50, 50, 20, 20);
		g.fillOval(150, 50, 20, 20);
		g.fillOval(100, 120, 20, 20);

		g.setColor(Color.WHITE);
		g.fillOval(106, 100, 7, 7);

		g.setColor(new Color(200, 200, 200));
		g.fillOval(55, 55, 10, 10);
		g.fillOval(155, 55, 10, 10);
		g.fillOval(105, 125, 10, 10);
		g.fillRect(55, 60, 10, 10);
		g.fillRect(155, 60, 10, 10);
		g.fillRect(105, 130, 10, 10);
		g.fillOval(55, 65, 10, 10);
		g.fillOval(155, 65, 10, 10);
		g.fillOval(105, 135, 10, 10);

		g.setColor(new Color(200, 0, 0));
		g.fillOval(57, 67, 6, 6);
		g.fillOval(157, 67, 6, 6);
		g.fillOval(107, 157, 6, 6);
		g.fillRect(57, 70, 6, 20);
		g.fillRect(157, 70, 6, 20);
		g.fillRect(107, 150, 6, 20);
		g.fillOval(57, 87, 6, 6);
		g.fillOval(157, 87, 6, 6);
		g.fillOval(107, 177, 6, 6);

		g.setColor(new Color(200, 200, 255));
		g.drawLine(60, 60, 110, 102);
		g.drawLine(145, 60, 110, 102);
		g.drawLine(110, 102, 110, 145);

	}

}
