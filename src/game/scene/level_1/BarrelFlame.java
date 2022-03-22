package game.scene.level_1;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import game.character.DonkeyKong;
import game.character.Mario;

public class BarrelFlame {
	
	private final int x;
	private final int y;
	private final int width;
	private final int height;
	private final ImageIcon barrel_flame = new ImageIcon("img\\barrel_flame\\barrel_flame_1.png");
	private final ImageIcon barrel_flame_fire_1 = new ImageIcon("img\\barrel_flame\\barrel_flame_fire_1.png");
	private final ImageIcon barrel_flame_fire_2 = new ImageIcon("img\\barrel_flame\\barrel_flame_fire_2.png");
	private int steps = 0;
	private int numberOfFlames;
	private List<Flame> listFlames;
	private boolean addNewFlame = false;
	
	public BarrelFlame() {
		x = 60;
		y = 688;
		width = 60;
		height = 90;
		steps = 0;
		numberOfFlames = 0;
		listFlames = new ArrayList<Flame>();
		new ChangeImage().start();
	}
	
	public synchronized void paint(Graphics2D g) {
		if (Game_1.winMario) {
			listFlames.clear();
		} else {
			if (DonkeyKong.getNumberOfFlames() == 0) g.drawImage(barrel_flame.getImage(), x, y, width, height, null);
			else {
				if (steps == 0) g.drawImage(barrel_flame_fire_1.getImage(), x, y, width, height, null);
				else g.drawImage(barrel_flame_fire_2.getImage(), x, y, width, height, null);
				if (Mario.marioDeath) { //Si Mario muere limpio la lista
					listFlames.clear();
				} else { //Si no esta muerto sigo pintandolo, si no puede dar CurrentModificationException
					for (Flame flame : listFlames) {
						flame.paint(g);
						if (Mario.getBounds().intersects(flame.getBounds())) {
							if (Mario.isMarioHammer()) {
								PointsAnimation.setX(flame.getX());
								PointsAnimation.setY(flame.getY());
								PointsAnimation.setSpecial(true);
								PointsAnimation.setAnimationPoints(true);
								listFlames.remove(flame);
								break;
							}
							else DonkeyKong.setGameEnd(true);
						}
					}
					if (addNewFlame) {
						addNewFlame = false;
						listFlames.add(new Flame());
					}
				}
			}
		}
	}
	
	private class ChangeImage extends Thread {
		
		private Random ran = new Random();
		
		@Override
		public void run() {
			while (true) {
				try { Thread.sleep(1); } catch (InterruptedException e) {}
				if (!PointsAnimation.isAnimationPoints()) {
					try { Thread.sleep(ran.nextInt((500 - 200) + 1) + 200); } catch (InterruptedException e) {}
					if (steps == 0) steps++;
					else steps--;
					getNumberOfFlames();
					if (Mario.marioDeath) break;
				}
				if (Game_1.winMario) break;
			}
		}

		private synchronized void getNumberOfFlames() { //BlueBarrel Collision with BarrelFlame and create flame
			if (numberOfFlames < DonkeyKong.getNumberOfFlames()) {
				numberOfFlames = DonkeyKong.getNumberOfFlames();
				addNewFlame = true;
			}
		}
		
	}
}
