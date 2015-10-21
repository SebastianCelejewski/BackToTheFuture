package pl.sebcel.timecircuits.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import pl.sebcel.timecircuits.events.TimeTravelListener;

public class MainFrame extends JFrame implements TimeTravelListener {

	private static final long serialVersionUID = 1L;

	private TimeCircuits timeCircuits = new TimeCircuits();
	private Speedometer speedometer = new Speedometer(this);
	private FluxCapacitor fluxCapacitor = new FluxCapacitor();
	private JPanel bottomPane = new JPanel();

	public MainFrame() {
		this.setLayout(new BorderLayout());
		this.add(timeCircuits, BorderLayout.CENTER);
		this.add(bottomPane, BorderLayout.SOUTH);
		
		bottomPane.add(speedometer);
		bottomPane.add(fluxCapacitor);
		bottomPane.setBackground(Color.BLACK);

		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				System.exit(0);
			}
		});

		this.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case 107:
					timeCircuits.plus();
					break;
				case 109:
					timeCircuits.minus();
					break;
				case KeyEvent.VK_UP:
					timeCircuits.up();
					break;
				case KeyEvent.VK_DOWN:
					timeCircuits.down();
					break;
				case KeyEvent.VK_LEFT:
					timeCircuits.left();
					break;
				case KeyEvent.VK_RIGHT:
					timeCircuits.right();
					break;
				case KeyEvent.VK_2:
					timeCircuits.off();
					break;
				case KeyEvent.VK_1:
					timeCircuits.on();
					break;
				case KeyEvent.VK_8:
					speedometer.start();
					break;
				case KeyEvent.VK_9:
					speedometer.cancel();
					break;
				}
			}
		});
	}

	@Override
	public void timeTravelStarted() {
		timeCircuits.startTravel();

	}

	@Override
	public void timeTravelComplete() {
		timeCircuits.completeTravel();
	}
}