package pl.sebcel.timecircuits;

import pl.sebcel.timecircuits.gui.MainFrame;

public class Bootstrap {

	public static void main(String[] args) {
		System.out.println("Time Circuits");
		new Bootstrap().run();
	}
	
	public void run() {
		MainFrame mainFrame = new MainFrame();
		mainFrame.setBounds(100, 100, 800, 600);
		mainFrame.setVisible(true);
		mainFrame.setTitle("Time Circuits");
	}
}