package game.scene.level_2;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;

// Barrel: [x: 417, y: 355, width: 69, height: 103]
public class BarrelFlame {
	
	private final int x = 417;
	private final int y = 355;
	private final int width = 69;
	private final int height = 103;
	private final ImageIcon barrel_1 = new ImageIcon("img\\barrel_flame\\barrel_1.png");
	private final ImageIcon barrel_2 = new ImageIcon("img\\barrel_flame\\barrel_2.png");
	private final ImageIcon barrel_3 = new ImageIcon("img\\barrel_flame\\barrel_3.png");
	private final ImageIcon barrel_4 = new ImageIcon("img\\barrel_flame\\barrel_4.png");
	private int steps = 0;
	private List<Flame> listFlame = new ArrayList<Flame>();
	
	public BarrelFlame() {
		for (int i = 0; i < 5; i++) {
			listFlame.add(new Flame());
		}
		new ChangeImage().start();
	}
	
	public void paint(Graphics2D g) {
		if (steps == 0) g.drawImage(barrel_1.getImage(), x, y, width, height, null);
		else if (steps == 1) g.drawImage(barrel_2.getImage(), x, y, width, height, null);
		else if (steps == 2) g.drawImage(barrel_3.getImage(), x, y, width, height, null);
		else if (steps == 3) g.drawImage(barrel_4.getImage(), x, y, width, height, null);
		for (Flame flame : listFlame) {
			flame.paint(g);
		}
	}
	
	private class ChangeImage extends Thread {
		
		private final Random rand = new Random();
		
		@Override
		public void run() {
			while (!Game_2.startGame_2) {
				try { Thread.sleep(1); } catch (InterruptedException e) {}
			}
			while (true) {
				try { Thread.sleep(rand.nextInt((500 - 200) + 1) + 200); } catch (InterruptedException e) {}
				steps = rand.nextInt((3) + 1);
			}
		}
		
	}
	
}
