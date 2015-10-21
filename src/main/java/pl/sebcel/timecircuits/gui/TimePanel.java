package pl.sebcel.timecircuits.gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import pl.sebcel.timecircuits.model.TimeSetting;

public class TimePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private TimeSetting timeSetting = null;
	int selectedDigitsSection = 0;

	private JLabel label = new JLabel();

	private JPanel sections = new JPanel();
	private JPanel labelSection = new JPanel();
	private TimePanelSection monthSection;
	private TimePanelSection daySection;
	private TimePanelSection yearSection;

	private TimePanelSection hourSection;
	private TimePanelSection minuteSection;

	private TimePanelSection[] timePanelSections = new TimePanelSection[5];

	public void updateTime(TimeSetting time) {
		this.timeSetting = time;
		yearSection.setValue(time.getYear());
		monthSection.setValue(time.getMonth());
		daySection.setValue(time.getDay());
		hourSection.setValue(time.getHour());
		minuteSection.setValue(time.getMinute());
	}
	
	public void setTime(TimeSetting time) {
		this.timeSetting = time;
		if (time != null) {
			yearSection.setValue(time.getYear());
			monthSection.setValue(time.getMonth());
			daySection.setValue(time.getDay());
			hourSection.setValue(time.getHour());
			minuteSection.setValue(time.getMinute());
			on();
			this.repaint();
		} else {
			off();
		}
	}

	public TimePanel(String label, Color digitsColour, TimeSetting initialTimeSetting) {
		monthSection = new TimePanelSection(2, digitsColour, "MONTH");
		daySection = new TimePanelSection(2, digitsColour, "DAY");
		yearSection = new TimePanelSection(4, digitsColour, "YEAR");
		hourSection = new TimePanelSection(2, digitsColour, "HOUR");
		minuteSection = new TimePanelSection(2, digitsColour, "MINUTE");

		timePanelSections[0] = monthSection;
		timePanelSections[1] = daySection;
		timePanelSections[2] = yearSection;
		timePanelSections[3] = hourSection;
		timePanelSections[4] = minuteSection;

		this.setLayout(new BorderLayout());
		this.setBorder(new EtchedBorder(Color.BLACK, Color.BLACK));
		this.setBackground(LookAndFeel.PANEL_BACKGROUND_COLOUR);
		sections.setBackground(LookAndFeel.PANEL_BACKGROUND_COLOUR);
		this.add(labelSection, BorderLayout.SOUTH);
		labelSection.add(this.label);
		this.label.setForeground(Color.WHITE);
		this.label.setText(label);
		labelSection.setBackground(LookAndFeel.PANEL_SECTION_LABEL_BACKGROUND_COLOUR);

		this.add(sections, BorderLayout.CENTER);
		sections.add(monthSection);
		sections.add(new JLabel("    "));
		sections.add(daySection);
		sections.add(new JLabel("    "));
		sections.add(yearSection);
		sections.add(new JLabel("                     "));
		sections.add(hourSection);
		sections.add(new JLabel("    "));
		sections.add(minuteSection);

		setTime(initialTimeSetting);
		deactivate();
		off();
	}

	public void deactivate() {
		this.timePanelSections[selectedDigitsSection].setBorder(BorderFactory.createLoweredBevelBorder());
	}

	public void activate(int selectedDigitsSection) {
		this.selectedDigitsSection = selectedDigitsSection;
		this.setBorder(BorderFactory.createRaisedBevelBorder());
		this.timePanelSections[selectedDigitsSection].setBorder(BorderFactory.createRaisedBevelBorder());
	}

	public void increaseValue() {
		timeSetting.increase(selectedDigitsSection);
		setTime(timeSetting);
	}

	public void decreaseValue() {
		timeSetting.decrease(selectedDigitsSection);
		setTime(timeSetting);
	}

	public void on() {
		if (timeSetting != null) {
			for (TimePanelSection section : timePanelSections) {
				section.on();
			}
		}
	}

	public void off() {
		for (TimePanelSection section : timePanelSections) {
			section.off();
		}
	}

	public TimeSetting getTime() {
		return timeSetting;
	}
}