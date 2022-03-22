package game.scene;

import java.awt.Graphics2D;

import javax.swing.ImageIcon;

public class Lives {
	
	private final int x;
	private final int y;
	private final int width = 25;
	private final int height = 25;
	private final ImageIcon life = new ImageIcon("img\\lives\\life.png");
	
	public Lives (int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void paint(Graphics2D g) {
		g.drawImage(life.getImage(), x, y, width, height, null);
	}
	
}
