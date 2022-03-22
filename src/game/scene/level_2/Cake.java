package game.scene.level_2;

import java.awt.Graphics2D;

import javax.swing.ImageIcon;

import lombok.Getter;
import lombok.Setter;

public class Cake {
	
	@Getter @Setter private int x;
	private final int y;
	private final int width = 50;
	private final int height = 30;
	@Setter private boolean direccion;
	private final ImageIcon cake = new ImageIcon("img\\tarta\\tarta.png");
	
	public Cake(int x, int y, boolean direccion) {
		this.x = x;
		this.y = y;
		this.direccion = direccion;
		new MoveCake().start();
	}
	
	public void paint(Graphics2D g) {
		g.drawImage(cake.getImage(), x, y, width, height, null);
	}
	
	private class MoveCake extends Thread {
		
		@Override 
		public void run() {
			while (true) {
				try { Thread.sleep(20); } catch (InterruptedException e) {}
				if (direccion) x += 2;
				else x -= 2;
			}
		}
		
	}
	
}
