package screen;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import mech.Constants;
import mech.Logic;

public class Screen implements Constants {
	public Logic lgc;
	private MainFrame fr;
	private JPanel mp;
	private JLabel antRulesLbl;
	public JLabel stepNumberLbl;
	public AntField antField;
	public MyButton startBtn, stopBtn, pauseBtn;
	public MyButton addL1Btn, addR1Btn;
	public MyButton addL2Btn, addR2Btn;
	public MyButton addNBtn, addBBtn;
	public MyButton clearAllBtn, clearLastBtn;
	public MyButton clearAntFieldBtn;
	public JCheckBox randomChBox;
	
	public Screen() {
		lgc = new Logic(this);
		fr = new MainFrame("Langton Ant", this);
		createMainPanel();
		createAllLabels();
		createAllButtons();
		createRandomCheckBoss();
		Constants.initAntPoints();
		mp.repaint();
	}

	private void createMainPanel() {
		mp = new JPanel();
		mp.setLayout(null);
		mp.setBackground(Color.WHITE);
		fr.setContentPane(mp);
	}

	public void createAntField() {
		if (antField != null)
			mp.remove(antField);
		antField = new AntField();
		mp.add(antField);
		antField.repaint();
	}
	
	private void createAllLabels() {
		antRulesLbl = new MyLabel("R1L1B", ADD_BUTTON_WIDTH*8, LABEL_WIDTH);
		mp.add(antRulesLbl);
		
		stepNumberLbl = new MyLabel("0", 0, 400);
		stepNumberLbl.setVisible(false);
		stepNumberLbl.setName("Button");
		mp.add(stepNumberLbl);
	}
	
	public void setTextOnRLLabel(String s){
		antRulesLbl.setText(s);
		if (s.equals("")){
			startBtn.setEnabled(false);
			clearLastBtn.setEnabled(false);
			clearAllBtn.setEnabled(false);
		}else{
			startBtn.setEnabled(true);
			clearLastBtn.setEnabled(true);
			clearAllBtn.setEnabled(true);
		}
		
		if (calculateDirections(s) > COLORS.length)
			setTextOnRLLabel(s.substring(0, s.length()-1));
	}
	
		private int calculateDirections(String s) {
			int k = 0;
			for (char c : s.toCharArray()) {
				if (c != '1' && c != '2')
					k++;
			}
		return k;
	}

		public String getTextFromRLLabel(){
			return antRulesLbl.getText();
		}

	private void createAllButtons() {
		addL2Btn = new MyButton("+L2", ADD_BUTTON_WIDTH*0, 0, ADD_BUTTON_WIDTH, BUTTON_HEIGHT, lgc);
		mp.add(addL2Btn);
		
		addL1Btn = new MyButton("+L1", ADD_BUTTON_WIDTH*1, 0, ADD_BUTTON_WIDTH, BUTTON_HEIGHT, lgc);
		mp.add(addL1Btn);
		
		addBBtn = new MyButton("+B", ADD_BUTTON_WIDTH*2, 0, ADD_BUTTON_WIDTH, BUTTON_HEIGHT, lgc);
		mp.add(addBBtn);
		
		addNBtn = new MyButton("+N", ADD_BUTTON_WIDTH*3, 0, ADD_BUTTON_WIDTH, BUTTON_HEIGHT, lgc);
		mp.add(addNBtn);
		
		addR1Btn = new MyButton("+R1", ADD_BUTTON_WIDTH*4, 0, ADD_BUTTON_WIDTH, BUTTON_HEIGHT, lgc);
		mp.add(addR1Btn);
		
		addR2Btn = new MyButton("+R2", ADD_BUTTON_WIDTH*5, 0, ADD_BUTTON_WIDTH, BUTTON_HEIGHT, lgc);
		mp.add(addR2Btn);
		
		clearLastBtn = new MyButton("-LAST", ADD_BUTTON_WIDTH*6, 0, ADD_BUTTON_WIDTH, BUTTON_HEIGHT, lgc);
		mp.add(clearLastBtn);
		
		clearAllBtn = new MyButton("-ALL", ADD_BUTTON_WIDTH*7, 0, ADD_BUTTON_WIDTH, BUTTON_HEIGHT, lgc);
		mp.add(clearAllBtn);
		
		startBtn = new MyButton("START", WIN_WIDTH-100, 0, BUTTON_WIDTH, BUTTON_HEIGHT, lgc);
		mp.add(startBtn);
		
		stopBtn = new MyButton("STOP", WIN_WIDTH-100, 0, BUTTON_WIDTH, BUTTON_HEIGHT, lgc);
		stopBtn.setVisible(false);
		mp.add(stopBtn);
		
		pauseBtn = new MyButton("PAUSE", WIN_WIDTH-200, 0, BUTTON_WIDTH, BUTTON_HEIGHT, lgc);
		pauseBtn.setVisible(false);
		mp.add(pauseBtn);
		
		clearAntFieldBtn = new MyButton("CLEAR", WIN_WIDTH-200, 0, BUTTON_WIDTH, BUTTON_HEIGHT, lgc);
		clearAntFieldBtn.setVisible(false);
		mp.add(clearAntFieldBtn);
	}

	private void createRandomCheckBoss() {
		randomChBox = new JCheckBox("Do random snapshots");
		randomChBox.setBounds(clearAntFieldBtn.getX()-BUTTON_WIDTH*2, 0, BUTTON_WIDTH*2, BUTTON_HEIGHT);
		randomChBox.setBackground(Color.WHITE);
		randomChBox.setFocusable(false);
		randomChBox.setName("CheckBox");
		mp.add(randomChBox);
	}
	
	public void setTextOnStepNumberLabel(String s){
		stepNumberLbl.setText(s);
	}
	
	public void switchButtonsOnStart(){
		for (Component c: mp.getComponents()) {
			String name = c.getName();
			if (name != null){
				if (c.isVisible())
					c.setVisible(false);
				else
					c.setVisible(true);	
			}
		}
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Screen scr = new Screen();
	}
}
