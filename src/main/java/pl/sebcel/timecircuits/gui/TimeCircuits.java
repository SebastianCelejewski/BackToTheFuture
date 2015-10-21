package pl.sebcel.timecircuits.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

import pl.sebcel.timecircuits.model.TimeSetting;

public class TimeCircuits extends JPanel {

	private static final long serialVersionUID = 1L;

	private TimePanel[] timePanels = new TimePanel[3];
	private TimePanel destinationTimePanel = new TimePanel("DESTINATION TIME", LookAndFeel.DESTINATION_TIME_DIGITS_COLOUR, TimeSetting.now());
	private TimePanel presentTimePanel = new TimePanel("PRESENT TIME", LookAndFeel.PRESENT_TIME_DIGITS_COLOUR, TimeSetting.now());
	private TimePanel lastTimeDepartedPanel = new TimePanel("LAST TIME DEPARTED", LookAndFeel.LAST_TIME_DEPARTED_DIGITS_COLOUR, null);

	private int selectedTimePanel = 0;
	private int selectedDigitsSection = 0;

	private Timer currentTimeRefreshingTimer;

	public TimeCircuits() {
		this.setLayout(new GridBagLayout());
		timePanels[0] = destinationTimePanel;
		timePanels[1] = presentTimePanel;
		timePanels[2] = lastTimeDepartedPanel;

		this.add(destinationTimePanel, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0));
		this.add(presentTimePanel, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0));
		this.add(lastTimeDepartedPanel, new GridBagConstraints(0, 2, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0));

		currentTimeRefreshingTimer = new Timer();
		currentTimeRefreshingTimer.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
//				presentTimePanel.updateTime(TimeSetting.now());

			}
		}, 0, 500);
	}

	public void up() {
		if (selectedTimePanel > 0) {
			timePanels[selectedTimePanel].deactivate();
			selectedTimePanel--;
			timePanels[selectedTimePanel].activate(selectedDigitsSection);
		}
	}

	public void down() {
		if (selectedTimePanel < 2) {
			timePanels[selectedTimePanel].deactivate();
			selectedTimePanel++;
			timePanels[selectedTimePanel].activate(selectedDigitsSection);
		}
	}

	public void left() {
		if (selectedDigitsSection > 0) {
			timePanels[selectedTimePanel].deactivate();
			selectedDigitsSection--;
			timePanels[selectedTimePanel].activate(selectedDigitsSection);
		}
	}

	public void right() {
		if (selectedDigitsSection < 4) {
			timePanels[selectedTimePanel].deactivate();
			selectedDigitsSection++;
			timePanels[selectedTimePanel].activate(selectedDigitsSection);
		}
	}

	public void plus() {
		timePanels[selectedTimePanel].increaseValue();
	}

	public void minus() {
		timePanels[selectedTimePanel].decreaseValue();
	}

	public void on() {
		for (TimePanel panel : timePanels) {
			panel.on();
		}
	}

	public void off() {
		for (TimePanel panel : timePanels) {
			panel.off();
		}
	}

	private Timer timer;
	private TimeSetting lastPresentTime;

	public void startTravel() {
		timer = new Timer();
		lastPresentTime = presentTimePanel.getTime();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				presentTimePanel.setTime(new TimeSetting((int) (2000 * Math.random()), (int) (12 * Math.random()), (int) (31 * Math.random()), (int) (24 * Math.random()), (int) (60 * Math.random())));
			}
		}, 0, 50);
	}

	public void completeTravel() {
		timer.cancel();
		lastTimeDepartedPanel.setTime(lastPresentTime);
		presentTimePanel.setTime(destinationTimePanel.getTime());
	}
}