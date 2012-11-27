package Builders;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class ButtonBuilder extends JButton {
	private int location;

	public ButtonBuilder(int location) {
		assert (location >= 0);
		assert (location <= 15);
		this.location = location;
	}

	public int getLoc() {
		return location;
	}

}
