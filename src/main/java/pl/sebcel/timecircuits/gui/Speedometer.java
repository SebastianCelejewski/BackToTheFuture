package pl.sebcel.timecircuits.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import pl.sebcel.timecircuits.events.TimeTravelListener;

public class Speedometer extends JPanel {

	private enum Mode {
		NONE, ACCELERATION, TIME_TRAVEL, DECELERATION
	};

	private static final long serialVersionUID = 1L;

	private int speed = 0;
	private int timeTravelCounter = 0;
	private Mode mode = Mode.NONE;
	private Timer timer = new Timer();
	private TimeTravelListener listener;
	private TimePanelSection section = new TimePanelSection(2, LookAndFeel.SPEEDOMETER_FONT_COLOUR, null);

	public Speedometer(TimeTravelListener listener) {
		this.listener = listener;
		this.setPreferredSize(new Dimension(200, 100));
		this.add(section);
		this.setBackground(LookAndFeel.DIGITS_DISPLAY_BACKGROUND_COLOUR);
		this.setBorder(BorderFactory.createEtchedBorder(Color.GRAY, Color.WHITE));
		section.on();
	}

	public void start() {
		mode = Mode.ACCELERATION;
		timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				tick();

			}
		}, 0, 200);
	}

	public void cancel() {
		mode = Mode.DECELERATION;
	}

	public void tick() {
		switch (mode) {
		case ACCELERATION:
			speed++;
			if (speed >= 88) {
				mode = Mode.TIME_TRAVEL;
				timeTravelCounter = 20;
				listener.timeTravelStarted();
			}
			break;
		case TIME_TRAVEL:
			if (timeTravelCounter-- <= 0) {
				listener.timeTravelComplete();
				mode = Mode.DECELERATION;
			}
			break;
		case DECELERATION:
			speed = speed - 5;
			if (speed <= 0) {
				speed = 0;
				mode = Mode.NONE;
			}
			break;
		}

		section.setValue(speed);
		this.repaint();
	}
}