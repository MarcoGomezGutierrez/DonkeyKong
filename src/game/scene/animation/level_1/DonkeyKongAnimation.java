package game.scene.animation.level_1;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import lombok.*;

public class DonkeyKongAnimation {
	
	@Getter private int x = 400;
	@Getter private int y = 620;
	private int width = 190;
	private int height = 115;
	private boolean steps = true;
	@Getter private boolean donkeyUp = false;
	private boolean donkeyJump = false;
	private boolean finishAnimation = false;
	private final ImageIcon donkey_1 = new ImageIcon("img\\donKingKong\\donkey_climb_princess_1.png");
	private final ImageIcon donkey_2 = new ImageIcon("img\\donKingKong\\donkey_climb_princess_2.png");
	private final ImageIcon donkey = new ImageIcon("img\\donKingKong\\DonKingKong.png");
	private final ImageIcon princess = new ImageIcon("img\\peach\\Peach_Saltando.png");
	private final ImageIcon donkey_angry = new ImageIcon("img\\donKingKong\\DonKingKong_Enfadado.png");
	private List<Ladder> listLadder1 = new ArrayList<Ladder>();
	private List<Ladder> listLadder2 = new ArrayList<Ladder>();
	
	/**
	 * Spanish: Rellenando las listas de Esacaleras
	 * 
	 * English: Filling in the ladders lists
	 */
	public DonkeyKongAnimation() {
		final int step = 29;
		int y = this.y - 330;
		for (int i = 0; i < 16; i++) {
			listLadder1.add(new Ladder(this.x + 85 + 30, y));
			listLadder2.add(new Ladder(this.x + 20, y));
			y += step;
		}
		new SynchronizedMovement().start();
		new SynchronizedImages().start();
	}
	
	/**
	 * Animation of Donkey and Ladders
	 * @param g
	 */
	public synchronized void printAnimation(Graphics2D g) {
		if (!donkeyUp) {
			for (int i = 0; i < listLadder1.size(); i++) {
				listLadder1.get(i).paint(g);
				listLadder2.get(i).paint(g);
			}
			printDonkeyKongClimb(g);
		} else if (!finishAnimation){
			g.drawImage(princess.getImage(), 360, 117, 50, 60, null);
			g.drawImage(donkey.getImage(), x, y, width - 20, height - 10, null); //Donking Kong
		} else {
			g.drawImage(princess.getImage(), 360, 117, 50, 60, null);
			g.drawImage(donkey_angry.getImage(), x, y, width - 20, height - 10, null); //Donking Kong
		}
	}
	
	/**
	 * Donkey Climb
	 * @param g
	 */
	private void printDonkeyKongClimb(Graphics2D g) {
		if (steps) g.drawImage(donkey_1.getImage(), x, y, width, height, null);
		else g.drawImage(donkey_2.getImage(), x, y, width, height, null);
	}
	
	/**
	 * Move donkey Animation
	 * @author marco
	 *
	 */
	private class SynchronizedMovement extends Thread {
		
		private boolean firstJump = true;
		
		@Override
		public synchronized void run() {
			while (true) {
				if (!donkeyUp) {
					if (!donkeyJump) {
						try { Thread.sleep(115); } catch (InterruptedException e) {}
						y -= 8;
						if (y <= listLadder1.get(listLadder1.size() - 1).getY() - 180) { //Delete ladder below Donkey
							listLadder1.remove(listLadder1.size() - 1);
							if (y <= 300) donkeyJump = true;
						}
					} else {
						try { Thread.sleep(40); } catch (InterruptedException e) {} //Jump after ladders
						y -= 10;
						if (y <= 150) {
							y = 160;
							donkeyUp = true;
						}
					}
				} else {
					if (!finishAnimation) {
						if (firstJump) {
							try { Thread.sleep(500); } catch (InterruptedException e) {} //Longest First Jump 
							firstJump = false;
						}
						try { Thread.sleep(230); } catch (InterruptedException e) {}
						x -= 29;
						if (steps) {
							y += 25;
							steps = false;
						} else {
							y -= 25;
							steps = true;
						}
						if (x <= 55) {
							x = 85;
							y = 160;
							finishAnimation = true;
						}
					} else {
						break;
					}
				}
			}
		}
	}
	
	/**
	 * Changing Donkey images 
	 * @author marco
	 *
	 */
	private class SynchronizedImages extends Thread {
		
		@Override
		public synchronized void run() {
			while (true) {
				if (!donkeyUp) {
					if (!donkeyJump) {
						try { Thread.sleep(170); } catch (InterruptedException e) {}
						if (steps) steps = false;
						else steps = true;
					}
				} else if (finishAnimation){
					break;
				}
			}
		}
	}
	
}
