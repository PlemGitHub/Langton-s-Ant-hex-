package screen;

import java.awt.Color;
import javax.swing.JLabel;
import mech.Constants;

public class MyLabel extends JLabel implements Constants{
	private static final long serialVersionUID = 1L;

	public MyLabel(String name, int x, int width) {
		setText(name);
		setBounds(x, 0, width, 50);
		setHorizontalAlignment(JLabel.CENTER);
		setVerticalAlignment(JLabel.CENTER);
		setForeground(Color.RED);
		setFont(LABEL_FONT);
	}
}
