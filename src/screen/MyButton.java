package screen;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;

import mech.Constants;
import mech.Logic;

public class MyButton extends JButton implements Constants{
	private static final long serialVersionUID = 1L;

	public MyButton(String name, int x, int y, int width, int height, Logic lgc) {
		setName(BUTTON_NAME);
		setText(name);
		setBounds(x, y, width, height);
		setHorizontalAlignment(JLabel.CENTER);
		setVerticalAlignment(JLabel.CENTER);
		setForeground(Color.RED);
		setFont(BUTTON_FONT);
		setFocusable(false);
		addActionListener(lgc);
	}
}
