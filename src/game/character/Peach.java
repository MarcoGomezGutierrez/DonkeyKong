package game.character;

import java.awt.Graphics2D;

import javax.swing.ImageIcon;

import game.scene.level_1.Game_1;
import game.scene.level_1.PointsAnimation;
import lombok.Getter;
import lombok.Setter;

public class Peach {
	@Getter @Setter private int x = 358;
	@Getter @Setter private int y = 117;
	private final int width = 48;
	private final int height = 60;
	@Getter @Setter private int xHelp = 358;
	@Getter @Setter private int yHelp = 112;
	@Getter @Setter private int widthHelp = 129;
	@Getter @Setter private int heightHelp = 65;
	@Setter private int steps = 0;
	private final ImageIcon peach_help_1 = new ImageIcon("img\\peach\\Peach_Help_1.png");
	private final ImageIcon peach_help_2 = new ImageIcon("img\\peach\\Peach_Help_2.png");
	private final ImageIcon peach_mario_death = new ImageIcon("img\\peach\\Peach_Mario_Muerto.png");
	private final ImageIcon peach_jump = new ImageIcon("img\\peach\\Peach_Saltando.png");
	private final ImageIcon peach_win = new ImageIcon("img\\peach\\Peach_Win.png");
	private final ImageIcon heart_broken = new ImageIcon("img\\peach\\heart_broken.png");
	@Setter private static boolean peachVisible = true;
	
	public Peach() {
		new AnimationPeach().start();
	}
	
	public void paint(Graphics2D g) {
		if (peachVisible) {
			if (!Game_1.winMario) {
				if (steps == 0) {
					g.drawImage(peach_jump.getImage(), x, y, width, height, null);
				} else if (steps == 1) {
					g.drawImage(peach_help_1.getImage(), xHelp, yHelp, widthHelp - 2, heightHelp, null);
				} else if (steps == 2) {
					g.drawImage(peach_help_2.getImage(), xHelp, yHelp - 2, widthHelp + 2, heightHelp + 2, null);
				} else if (steps == 3) {
					g.drawImage(peach_win.getImage(), x, y, width, height, null);
				} else if (steps == 4) {
					g.drawImage(peach_mario_death.getImage(), x, y, width, height, null);
				}
			} else {
				g.drawImage(peach_win.getImage(), xHelp, yHelp, widthHelp, heightHelp, null);
			}
		} else {
			g.drawImage(heart_broken.getImage(), xHelp, yHelp, widthHelp, heightHelp, null);
		}
	}
	
	private class AnimationPeach extends Thread {
		
		@Override
		public void run() {
			while (true) {
				try { Thread.sleep(5000); } catch (InterruptedException e) {}
				if (!PointsAnimation.isAnimationPoints()) {
					if (steps == 0) {
						for (int i = 0; i < 5; i++) {
							steps = 1;
							try { Thread.sleep(500); } catch (InterruptedException e) {}
							steps = 2;
							try { Thread.sleep(500); } catch (InterruptedException e) {}
						}
						steps = 0;
					}
					if (!peachVisible) break;
				}
			}
		}
		
	}
	
}
