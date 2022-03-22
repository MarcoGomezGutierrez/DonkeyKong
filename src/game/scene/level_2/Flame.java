package game.scene.level_2;

import java.awt.Graphics2D;

import javax.swing.ImageIcon;

import game.character.Mario;
import lombok.Getter;
import lombok.Setter;

public class Flame  {
	
	@Getter @Setter private int x; 
	@Getter @Setter private int y; 
	private int width;
	private int height;
	private final ImageIcon flame_left_1 = new ImageIcon("img\\flame\\flame_left_1.png");
	private final ImageIcon flame_left_2 = new ImageIcon("img\\flame\\flame_left_2.png");
	private final ImageIcon flame_right_1 = new ImageIcon("img\\flame\\flame_right_1.png");
	private final ImageIcon flame_right_2 = new ImageIcon("img\\flame\\flame_right_2.png");
	private final ImageIcon flame_blue_left_1 = new ImageIcon("img\\flame\\flame_blue_left_1.png");
	private final ImageIcon flame_blue_left_2 = new ImageIcon("img\\flame\\flame_blue_left_2.png");
	private final ImageIcon flame_blue_right_1 = new ImageIcon("img\\flame\\flame_blue_right_1.png");
	private final ImageIcon flame_blue_right_2 = new ImageIcon("img\\flame\\flame_blue_right_2.png");
	private int steps;
	private int direccion;
	
	public Flame() {
		this.x = 72; 
		this.y = 689;
		this.width = 40;
		this.height = 40;
		this.steps = 0;
		this.direccion = 0;
	}
	
	public void paint(Graphics2D g) {
		if (direccion == 0) {
			if (steps == 0) {
				if (Mario.isMarioHammer()) g.drawImage(flame_blue_right_1.getImage(), x, y, width, height, null);
				else g.drawImage(flame_right_1.getImage(), x, y, width, height, null);
			}
			else if (steps == 1) {
				if (Mario.isMarioHammer()) g.drawImage(flame_blue_right_2.getImage(), x, y, width, height, null);
				else g.drawImage(flame_right_2.getImage(), x, y, width, height, null);
			}
		} else if (direccion == 1){
			if (steps == 0) {
				if (Mario.isMarioHammer()) g.drawImage(flame_blue_left_1.getImage(), x, y, width, height, null);
				else g.drawImage(flame_left_1.getImage(), x, y, width, height, null);
			}
			else if (steps == 1) {
				if (Mario.isMarioHammer()) g.drawImage(flame_blue_left_2.getImage(), x, y, width, height, null);
				else g.drawImage(flame_left_2.getImage(), x, y, width, height, null);
			}
		}
	}
	
	private class ChangeImage extends Thread {
		
		@Override
		public void run() {
			while (true) {
				try { Thread.sleep(200); } catch (InterruptedException e) {}
				if (steps == 0) {
					steps++;
				} else if (steps == 1) {
					steps = 0;
				}
			}
		}
		
	}

}
