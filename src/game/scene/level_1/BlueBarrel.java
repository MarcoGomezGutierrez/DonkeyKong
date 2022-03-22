package game.scene.level_1;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import lombok.Getter;
import lombok.Setter;

public class BlueBarrel {
	
	private int x = 137;
	private int y = 217;
	private final int widthFront;
	private final int heightFront;
	private final int width;
	private final int height;
	private final int limit;
	private final ImageIcon blue_barrel_front_1 = new ImageIcon("img\\blue_barrel\\blue_barrel_front_1.png");
	private final ImageIcon blue_barrel_front_2 = new ImageIcon("img\\blue_barrel\\blue_barrel_front_2.png");
	private final ImageIcon blue_barrel_1 = new ImageIcon("img\\blue_barrel\\blue_barrel_1.png");
	private final ImageIcon blue_barrel_2 = new ImageIcon("img\\blue_barrel\\blue_barrel_2.png");
	private final ImageIcon blue_barrel_3 = new ImageIcon("img\\blue_barrel\\blue_barrel_3.png");
	private final ImageIcon blue_barrel_4 = new ImageIcon("img\\blue_barrel\\blue_barrel_4.png");
	
	private int steps;
	@Getter @Setter private static boolean alive;
	
	public BlueBarrel() {
		this.x = 137;
		this.y = 217;
		this.widthFront = 65;
		this.heightFront = 35;
		this.width = 40;
		this.height = 40;
		this.limit = 730;
		steps = 0;
		alive = true;
		new SynchronizedImages().start();
		new SynchronizedMovement().start();
	}
	
	public void paint(Graphics2D g) {
		if (y >= limit) {
			if (steps == 0) g.drawImage(blue_barrel_1.getImage(), x, y, width, height, null);
			else if (steps == 1) g.drawImage(blue_barrel_2.getImage(), x, y, width, height, null);
			else if (steps == 2) g.drawImage(blue_barrel_3.getImage(), x, y, width, height, null);
			else if (steps == 3) g.drawImage(blue_barrel_4.getImage(), x, y, width, height, null);
		} else {
			if (steps == 0) g.drawImage(blue_barrel_front_1.getImage(), x, y, widthFront, heightFront, null);
			else g.drawImage(blue_barrel_front_2.getImage(), x, y, widthFront, heightFront, null);
		}
	}
	
	public Rectangle getBounds() {
		if (y >= limit) return new Rectangle(x, y, widthFront, heightFront);
		else return new Rectangle(x, y, width, height);
	}
	

	
	private class SynchronizedMovement extends Thread {
		
		@Override
		public void run() {
			while(alive) {
				try { Thread.sleep(50); } catch (InterruptedException e) {}
				if (y >= limit) {
					x -= 7;
					if (x <= 60) alive = false;
				}
				else y += 10;
			}
		}
		
	}
	
	private class SynchronizedImages extends Thread {
		
		@Override
		public void run() {
			while(alive) {
				try { Thread.sleep(20); } catch (InterruptedException e) {}
				if (y >= limit) {
					if (steps == 0) steps++;
					else if (steps == 1) steps++;
					else if (steps == 2) steps++;
					else if (steps == 3) steps = 0;
				} else {
					if (steps == 0) steps++;
					else steps--;
				}
			}
		}
		
	}
	
}
