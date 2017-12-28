package mech;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;

public interface Constants {
	int WIN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
	int WIN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
	
	int BUTTON_WIDTH = 100;
	int ADD_BUTTON_WIDTH = 80;
	int BUTTON_HEIGHT = 50;
	String BUTTON_NAME = "Button";
	Font BUTTON_FONT = new Font ("TimesRoman", Font.BOLD, 15);
	
	int SIZE_K = 2;
	int HEX_SIZE = SIZE_K*4;
	int HEX_SIZE_CELL = HEX_SIZE*3/4;
	
	int K_WIDTH = (WIN_WIDTH - (BUTTON_WIDTH + HEX_SIZE_CELL)*2)/HEX_SIZE_CELL;
	int K_HEIGHT = (WIN_HEIGHT - (BUTTON_HEIGHT + HEX_SIZE_CELL)*2)/HEX_SIZE;
//	int K_WIDTH = 333;
//	int K_HEIGHT = 300;
	
	Point[][] ANT_POINTS = new Point[K_WIDTH][K_HEIGHT];
	
	int ANT_FIELD_WIDTH = HEX_SIZE_CELL*K_WIDTH + HEX_SIZE*1/4 + 2;
	int ANT_FIELD_HEIGHT = HEX_SIZE*K_HEIGHT - HEX_SIZE/2 + 2;
	
	int DELAY = 0;
	
	Font LABEL_FONT = new Font ("TimesRoman", Font.BOLD, 20);
	int LABEL_WIDTH = WIN_WIDTH-ADD_BUTTON_WIDTH*8-BUTTON_WIDTH*4;
	char[] DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
	
	int DX = HEX_SIZE/4*3;
	int DY = HEX_SIZE/2;
	
	Point[] DIRECTIONS_LEFT_0 = {new Point(0, -1), new Point(-1, 0), new Point(-1, 1),
									new Point(0, 1), new Point(1, 1), new Point(1, 0)};
	Point[] DIRECTIONS_RIGHT_0 = {new Point(0, -1), new Point(1, 0), new Point(1, 1),
									new Point(0, 1), new Point(-1, 1), new Point(-1, 0)};
	
	Point[] DIRECTIONS_LEFT_1 = {new Point(0, -1), new Point(-1, -1), new Point(-1, 0),
									new Point(0, 1), new Point(1, 0), new Point(1, -1)};
	Point[] DIRECTIONS_RIGHT_1 = {new Point(0, -1), new Point(1, -1), new Point(1, 0),
									new Point(0, 1), new Point(-1, 0), new Point(-1, -1)};
	
	Point[][] DIRECTION_PAIRS_0 = {{new Point(1, 0), new Point(1, -1)},
									{new Point(1, 1), new Point(1, 0)},
									{new Point(-1, 1), new Point(-1, 0)},
									{new Point(-1, 0), new Point(-1, -1)}};
	
	Color[] COLORS = {Color.YELLOW, Color.BLACK, Color.BLUE, Color.DARK_GRAY, Color.GREEN, Color.MAGENTA, Color.LIGHT_GRAY, 
						Color.ORANGE, Color.GRAY, Color.PINK, Color.RED, Color.CYAN, Color.WHITE};
	int COLORS_LAST_INDEX = COLORS.length-1;
	
	int STEP_LIMIT = 200100;
	int SNAPSHOT_FREQUENCY = 10000;

	public static void initAntPoints() {
		Point p;
		for (int i = 0; i < K_WIDTH; i++)
			for (int j = 0; j < K_HEIGHT; j++){
				int x = i*HEX_SIZE_CELL;
				if (i%2 == 0)
					p = new Point(x+1, -HEX_SIZE/2+j*HEX_SIZE+1);
				else
					p = new Point(x+1, -HEX_SIZE+j*HEX_SIZE+1);
				
			ANT_POINTS[i][j] = p;
			}
	}
}
