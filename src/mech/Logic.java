package mech;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import screen.Screen;

public class Logic implements ActionListener, Constants {
	private Screen scr;
	public MyTimer myTimer;
	private RandomSnapshots randomSnapshots;
	
	public Logic(Screen scr) {
		this.scr = scr;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(scr.addL2Btn))
			scr.setTextOnRLLabel(scr.getTextFromRLLabel()+"L2");
		
		if (e.getSource().equals(scr.addL1Btn))
			scr.setTextOnRLLabel(scr.getTextFromRLLabel()+"L1");
		
		if (e.getSource().equals(scr.addBBtn))
			scr.setTextOnRLLabel(scr.getTextFromRLLabel()+"B");
		
		if (e.getSource().equals(scr.addNBtn))
			scr.setTextOnRLLabel(scr.getTextFromRLLabel()+"N");
		
		if (e.getSource().equals(scr.addR1Btn))
			scr.setTextOnRLLabel(scr.getTextFromRLLabel()+"R1");
		
		if (e.getSource().equals(scr.addR2Btn))
			scr.setTextOnRLLabel(scr.getTextFromRLLabel()+"R2");
		
		if (e.getSource().equals(scr.clearLastBtn)){
			int del = 1;
			String str = scr.getTextFromRLLabel();
				int length = str.length();
			String lastDirection = str.substring(length-1);
			if (lastDirection.equals("1") || lastDirection.equals("2"))
				del = 2;
			scr.setTextOnRLLabel(str.substring(0, length-del));			
		}
		
		if (e.getSource().equals(scr.clearAllBtn))
			scr.setTextOnRLLabel("");
		
		if (e.getSource().equals(scr.startBtn)){
			if (scr.randomChBox.isSelected()){
				randomSnapshots = new RandomSnapshots(scr, this);
				randomSnapshots.start();
			}else{
				createMyTimerAndAntField();
				scr.switchButtonsOnStart();
				scr.clearAntFieldBtn.setVisible(false);
			}
		}
		
		if (e.getSource().equals(scr.stopBtn)){
			if (randomSnapshots != null
					&& randomSnapshots.isAlive()){
				randomSnapshots.interrupt();
				randomSnapshots.myTimer.stop();
			}else
				myTimer.stop();
			myTimer.stopped = true;
			scr.switchButtonsOnStart();
			scr.stepNumberLbl.setText("0");
		}
		
		if (e.getSource().equals(scr.pauseBtn)){
			if (myTimer.isRunning()){
				myTimer.stop();
			}else{
				myTimer.start();
			}
		}
		
		if (e.getSource().equals(scr.clearAntFieldBtn)){
			scr.createAntField();
			scr.clearAntFieldBtn.setVisible(false);
		}
	}	
	
	public MyTimer createMyTimerAndAntField(){
		scr.createAntField();
		myTimer = new MyTimer(DELAY, null, scr);
		myTimer.addActionListener(myTimer);
		myTimer.start();
		return myTimer;
	}
}
