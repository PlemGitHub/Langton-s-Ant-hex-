package entity;

import java.awt.Point;
import mech.Constants;

public class Ant implements Constants {
	public int x, y;
	public int i, j;
	private Point directionD = DIRECTIONS_LEFT_0[0];
	
	public Ant(int i, int j) {
		this.i = i;
		this.j = j;
		updateXY();
	}

	public void defineDirection(String str){
		switch (str) {
			case "N": break;
			case "B": defineDirectionBack();
			case "L1": defineDirectionPoint(chooseDirectionSideN('L', i), 1); break;
			case "R1": defineDirectionPoint(chooseDirectionSideN('R', i), 1); break;
			case "L2": defineDirectionPoint(chooseDirectionSideN('L', i), 2); break;
			case "R2": defineDirectionPoint(chooseDirectionSideN('R', i), 2); break;
		}
	}
	
	private void defineDirectionBack() {
		directionD = new Point(-directionD.x, -directionD.y);
	}

	private void defineDirectionPoint(Point[] directionSide, int dK) {
		directionD = translateDirectionPoint(i, directionD);
		int k = 0;
		for (Point p : directionSide) {
			if (p.equals(directionD)){
				k=k+dK;
				if (k < directionSide.length){
					directionD = directionSide[k];
					return;
				}else{
					directionD = directionSide[k-directionSide.length];
					return;
				}
			}
			k++;	
		}
	}

	public void doMove() {
		i = i+directionD.x;
			if (i < 0)
				i = K_WIDTH-1;
			if (i > K_WIDTH-1)
				i = 0;
		j = j+directionD.y;
			if (j < 1)
				j = K_HEIGHT-1;
			if (j > K_HEIGHT-1)
				j = 1;
		updateXY();
	}
	
	private void updateXY(){
		x = ANT_POINTS[i][j].x;
		y = ANT_POINTS[i][j].y;
	}
	
	public Point[] chooseDirectionSideN(char c, int i){
		switch (c) {
		case 'L': 	if (i%2 == 0)
						return DIRECTIONS_LEFT_0;
					else
						return DIRECTIONS_LEFT_1;
		case 'R':	if (i%2 == 0)
						return DIRECTIONS_RIGHT_0;
					else
						return DIRECTIONS_RIGHT_1;
		}
		return null;
	}
	
	public Point translateDirectionPoint(int i, Point p){
		for (Point[] pair : DIRECTION_PAIRS_0) {
			if (i%2 == 1 && p.equals(pair[0])){
				p = pair[1];
				break;
			}
			if (i%2 == 0 && p.equals(pair[1])){
				p = pair[0];
				break;
			}
		}
		return p;
	}
}
