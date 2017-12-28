package screen;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import javax.swing.JFrame;

import mech.Constants;
import mech.MyTimer;

public class MainFrame extends JFrame implements Constants, KeyListener, WindowStateListener{
	private static final long serialVersionUID = 1L;
	
	private Screen scr;
	
	public MainFrame(String title, Screen scr) {
		this.scr = scr;
		setTitle(title);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addKeyListener(this);
		addWindowStateListener(this);
	}

	@Override
	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode()==27)
			System.exit(0);
	}

	@Override
	public void windowStateChanged(WindowEvent e) {
		MyTimer myTimer = scr.lgc.myTimer;
		
		if (e.getNewState() == 0){	// SMALL-SIZED
			setBounds(WIN_WIDTH/4, WIN_HEIGHT/4, WIN_WIDTH/2, WIN_HEIGHT/2);
		}
			
		if (e.getNewState() == 7)	// ICONIFIED
			if (myTimer != null){
				if (myTimer.isRunning())
					myTimer.stop();
			}
		
		if (e.getNewState() == 6){	// DEICONIFIED
			if (myTimer != null){
				if (!myTimer.isRunning()
					&& !myTimer.stopped){
					myTimer.start();
				}
			}
		}
		
	}
}
