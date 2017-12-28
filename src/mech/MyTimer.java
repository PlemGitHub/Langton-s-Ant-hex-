package mech;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.filechooser.FileSystemView;

import entity.Ant;
import screen.Screen;

public class MyTimer extends javax.swing.Timer implements Constants, ActionListener{
	private static final long serialVersionUID = 1L;

	public Screen scr;
	private JPanel antField;
	private Graphics g;
	private Ant ant;
	public String directionChars;
	public ArrayList<String> directionCharsSeparated;
	public String newDirectionString;
	public int colorPos;
	public BufferedImage buffImg;
	public int stepNumber;
	public boolean stopped;
	private SavePNG savePNG = new SavePNG();
	/**
	 * 0 - white <br> 1 - black
	 */
	public Color[][] antColors = new Color[K_WIDTH][K_HEIGHT];

	public MyTimer(int delay, ActionListener listener, Screen scr) {
		super(delay, listener);
		this.scr = scr;
		antField = scr.antField;
		if (scr.randomChBox.isSelected()){
			createRandomDirectionChars();
			scr.setTextOnRLLabel(directionChars);
		}
		else
			directionChars = scr.getTextFromRLLabel();
			separateDirectionChars();
		
		ant = new Ant(K_WIDTH/2, K_HEIGHT/2);
		initAntColors();
		buffImg = new BufferedImage(ANT_FIELD_WIDTH, ANT_FIELD_HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = buffImg.getGraphics();
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, ANT_FIELD_WIDTH, ANT_FIELD_HEIGHT);
			g.setColor(Color.BLACK);
			g.drawRect(0, 0, ANT_FIELD_WIDTH-1, ANT_FIELD_HEIGHT-1);
	}

	private void separateDirectionChars() {
		directionCharsSeparated  = new ArrayList<>();
		String str;
		for (int i = 0; i < directionChars.length(); i++) {
			char c = directionChars.charAt(i);
			if (c == 'R' || c == 'L'){
				str = directionChars.substring(i, i+2);
				i++;
			}
			else
				str = directionChars.substring(i, i+1);
		directionCharsSeparated.add(str);
		}
	}

	private void createRandomDirectionChars() {
		directionChars = "";
		int length = COLORS.length-3;	// 10
		Random rnd = new Random();
		int dCharsLength = rnd.nextInt(length)+3;	// 3..13
		for (int i = 0; i < dCharsLength; i++) {	// 0..(3..13)
			rnd = new Random();
			int k = rnd.nextInt(6);
				switch (k) {
					case 0: directionChars = directionChars+"L2"; break;
					case 1:	directionChars = directionChars+"L1"; break;
					case 2: directionChars = directionChars+"B"; break;
					case 3:	directionChars = directionChars+"N"; break;
					case 4: directionChars = directionChars+"R1"; break;
					case 5:	directionChars = directionChars+"R2"; break;
				}
		}
		String home = FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath()+"/LangtonAntPNG/"+directionChars;
		File f = new File(home);
		if (f.exists())
			createRandomDirectionChars();
	}

	private void initAntColors() {
		for (int i = 0; i < K_WIDTH; i++)
			for (int j = 0; j < K_HEIGHT; j++)
				antColors[i][j] = COLORS[0];
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Color newColor = defineNewColorUnderAnt();

		ant.defineDirection(newDirectionString);
		
		drawStep(newColor);
		increaseStepNumber();
	}

	private Color defineNewColorUnderAnt() {
		Color underAnt = antColors[ant.i][ant.j];
		colorPos = findColor(underAnt);
		newDirectionString = directionCharsSeparated.get(colorPos);
		increaseColorPos();
		return COLORS[colorPos];
	}

	private int findColor(Color underAnt) {
		int k=0;
		for (Color color : COLORS) {
			if (underAnt == color)
				return k;
			k++;		
		}
		return 0;
	}

	private void increaseColorPos() {
		colorPos++;
		if (colorPos > directionCharsSeparated.size()-1)
			colorPos = 0;
	}

	private void drawStep(Color newColor) {
		g.setColor(newColor);
		g.fillPolygon(definePolygonX(ant.x), definePolygonY(ant.y), 6);
		antColors[ant.i][ant.j] = newColor;
		ant.doMove();
		g.setColor(Color.RED);
		g.fillPolygon(definePolygonX(ant.x), definePolygonY(ant.y), 6);
		antField.getGraphics().drawImage(buffImg, 0, 0, antField);
	}

	private int[] definePolygonX(int x){
		int[] xx = new int[6];
			xx[0] = x+HEX_SIZE/4;
			xx[1] = x+HEX_SIZE*3/4;
			xx[2] = x+HEX_SIZE;
			xx[3] = x+HEX_SIZE*3/4;
			xx[4] = x+HEX_SIZE/4;
			xx[5] = x;
		return xx;
	}
	//int HEX_SIZE = SIZE_K*4;
	private int[] definePolygonY(int y){
		int[] yy = new int[6];
			yy[0] = y;
			yy[1] = y;
			yy[2] = y+HEX_SIZE/2;
			yy[3] = y+HEX_SIZE;
			yy[4] = y+HEX_SIZE;
			yy[5] = y+HEX_SIZE/2;
		return yy;
	}
	
	private void increaseStepNumber() {
		stepNumber++;
		scr.setTextOnStepNumberLabel(Integer.toString(stepNumber));
		if (scr.randomChBox.isSelected()
			&& (int) stepNumber%SNAPSHOT_FREQUENCY == 0){
			savePNG.save(buffImg, directionChars, stepNumber);
		}
	}
}
