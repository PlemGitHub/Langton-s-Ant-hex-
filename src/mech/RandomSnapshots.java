package mech;

import screen.Screen;

public class RandomSnapshots extends Thread implements Constants{
	private Screen scr;
	private Logic lgc;
	public MyTimer myTimer;
	
	public RandomSnapshots(Screen scr, Logic lgc) {
		this.scr = scr;
		this.lgc = lgc;
		initStart(lgc);
	}
	
	private void initStart(Logic lgc) {
		myTimer = lgc.createMyTimerAndAntField();
		scr.switchButtonsOnStart();
		scr.clearAntFieldBtn.setVisible(false);
	}

	@Override
	public void run() {
		do {
			while (myTimer.stepNumber < STEP_LIMIT){
				if (myTimer.stopped)
					interrupt();
				Thread.yield();
			}
		myTimer.stop();
		myTimer = lgc.createMyTimerAndAntField();
		
		} while (!myTimer.stopped);
	}
}