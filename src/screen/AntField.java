package screen;

import java.awt.Color;
import javax.swing.JPanel;
import mech.Constants;

public class AntField extends JPanel implements Constants{
	private static final long serialVersionUID = 1L;
		
	public AntField() {
		setBounds(HEX_SIZE_CELL, BUTTON_HEIGHT+HEX_SIZE_CELL, ANT_FIELD_WIDTH, ANT_FIELD_HEIGHT);
		setBackground(Color.WHITE);
	}
}
