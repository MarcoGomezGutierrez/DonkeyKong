package game.scene.level_1;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;
import javax.swing.ImageIcon;
import game.character.DonkeyKong;
import game.character.Mario;
import lombok.*;


/**
 * Left-Center --> (  x   , y + 18)
 * Center -------> (x + 18, y + 18)
 * Right-Center--> (x + 36, y + 18)
 * Top-Center----> (x + 18,   y   )
 * Buttom-Center-> (x + 18, y + 32)
 * @author marco
 *
 */
public class DonkeyBarrel {
	
	@Getter @Setter private int x = 262;
	@Getter @Setter private int y = 226;
	private int width = 40;
	private int height = 40;
	private int widthFront = 40 + 15;
	private int heightFront = 40;
	private int steps = 4;
	@Getter private boolean end = false;
	private final ImageIcon barrel_1 = new ImageIcon("img\\barrel\\barrel_1.png");
	private final ImageIcon barrel_2 = new ImageIcon("img\\barrel\\barrel_2.png");
	private final ImageIcon barrel_3 = new ImageIcon("img\\barrel\\barrel_3.png");
	private final ImageIcon barrel_4 = new ImageIcon("img\\barrel\\barrel_4.png");
	private final ImageIcon barrel_front_1 = new ImageIcon("img\\barrel\\barrel_front_1.png");
	private final ImageIcon barrel_front_2 = new ImageIcon("img\\barrel\\barrel_front_2.png");
	private final ImageIcon barrel_special_1 = new ImageIcon("img\\blue_barrel\\blue_barrel_1.png");
	private final ImageIcon barrel_special_2 = new ImageIcon("img\\blue_barrel\\blue_barrel_2.png");
	private final ImageIcon barrel_special_3 = new ImageIcon("img\\blue_barrel\\blue_barrel_3.png");
	private final ImageIcon barrel_special_4 = new ImageIcon("img\\blue_barrel\\blue_barrel_4.png");
	private final ImageIcon barrel_special_front_1 = new ImageIcon("img\\blue_barrel\\blue_barrel_front_1.png");
	private final ImageIcon barrel_special_front_2 = new ImageIcon("img\\blue_barrel\\blue_barrel_front_2.png");
	@Getter @Setter private  int floor = 6;
	private Random rand = new Random();
	private boolean downStair = false;
	@Getter private boolean down = false;
	@Getter private final boolean specialBarrel;
	
	public DonkeyBarrel(boolean special) {
		this.specialBarrel = special;
		new SynchronizedImage().start();
		new SynchronizedMovement().start();
	}
	
	public void paint(Graphics2D g) {
		if (specialBarrel) paintSpecialBarrel(g);
		else paintNormalBarrel(g);
//		g.setColor(Color.WHITE);
//		g.drawRect(x + 17, y - 22, 5, 20);
	}
	
	private void paintSpecialBarrel(Graphics2D g) {
		if (downStair) {
			if (steps == 1 || steps == 3) g.drawImage(barrel_special_front_1.getImage(), x, y, widthFront, heightFront, null);
			else if (steps == 2 || steps == 4) g.drawImage(barrel_special_front_2.getImage(), x, y, widthFront, heightFront, null);
		} else {
			if (steps == 1) g.drawImage(barrel_special_1.getImage(), x, y, width, height, null);
			else if (steps == 2) g.drawImage(barrel_special_2.getImage(), x, y, width, height, null);
			else if (steps == 3) g.drawImage(barrel_special_3.getImage(), x, y, width, height, null);
			else if (steps == 4) g.drawImage(barrel_special_4.getImage(), x, y, width, height, null);
		}
	}
		
	private void paintNormalBarrel(Graphics2D g) {
		if (downStair) {
			if (steps == 1 || steps == 3) g.drawImage(barrel_front_1.getImage(), x, y, widthFront, heightFront, null);
			else if (steps == 2 || steps == 4) g.drawImage(barrel_front_2.getImage(), x, y, widthFront, heightFront, null);
		} else {
			if (steps == 1) g.drawImage(barrel_1.getImage(), x, y, width, height, null);
			else if (steps == 2) g.drawImage(barrel_2.getImage(), x, y, width, height, null);
			else if (steps == 3) g.drawImage(barrel_3.getImage(), x, y, width, height, null);
			else if (steps == 4) g.drawImage(barrel_4.getImage(), x, y, width, height, null);
		}
	}
		
	public Rectangle getBounds() {
		return new Rectangle(x + 9, y + 9, width - 17, height - 17);
	}
	
	public Rectangle getBoundsMarioJump() {
		return new Rectangle(x + 17, y - 22, 5, 20);
	}
	
	
	private class SynchronizedMovement extends Thread {
		
		private int speed = 4;
		
		@Override
		public void run() {
			while (true) {
				try { Thread.sleep(20); } catch (InterruptedException e) {}
				if (Game_1.winMario) break;
				if (!PointsAnimation.isAnimationPoints()) {//Si hay puntos que no se mueva
					if (Mario.marioDeath) break;
					if (floor == 6) floor6();
					else if (floor == 5) floor5();
					else if (floor == 4) floor4();
					else if (floor == 3) floor3();
					else if (floor == 2) floor2();
					else if (floor == 1) {
						if (x >= 112 && x <= 148 && y >= 734) {
							if (specialBarrel) { //Si es un barril especial, le digo a DonkeyKong que añada uno
								DonkeyKong.setNewFlame(true);
							}
							break;
						} else {
							if (Game_1.floor > floor && x >= 830 && x <= 1000 && y >= 713 && !specialBarrel) {
								x += speed;
							} else {
								x -= speed;
							}
						}
					}
				}
			}
			end = true;
		}

		/**
		 * Todos estos métodos se dedican a hacer las acciones de mover el barril
		 * y bajar las escaleras
		 */
		private void floor6() {
			if (x >= 335 && x <= 364 && y >= 226 && ((Game_1.floor < floor && Mario.getX() < x) ? rand.nextInt(3) : rand.nextInt(10)) == 0 && floor > Game_1.floor) { //Stair 1
				downStair = true;
				down = true;
				while (!(x >= 335 && x <= 364 && y >= 301)) {
					if (!PointsAnimation.isAnimationPoints()) {
						x = 342;
						y += 7;
					}
					try { Thread.sleep(50); } catch (InterruptedException e) {}
				}
				floor--;
				downStair = false;
				down = false;
			} else if (x >= 722 && x <= 750 && y >= 233 && ((Game_1.floor < floor && Mario.getX() < x) ? rand.nextInt(3) : rand.nextInt(10)) == 0 && floor > Game_1.floor) { //Stair 2
				downStair = true;
				down = true;
				while (!(x >= 722 && x <= 750 && y >= 305)) {
					if (!PointsAnimation.isAnimationPoints()) {
						x = 729;
						y += 7;
					}
					try { Thread.sleep(50); } catch (InterruptedException e) {}
				}
				floor--;
				downStair = false;
				down = false;
			} else if (x >= 830 && x <= 871 && y >= 238) { 
				down = true;
				while (!(x >= 830 && x <= 871 && y >= 301)) {
					if (!PointsAnimation.isAnimationPoints()) {
						y += 7;
					}
					try { Thread.sleep(20); } catch (InterruptedException e) {}
				}
				floor--;
				down = false;
			} else {
				x += speed;
			}
		}

		private void floor5() {
			if (x >= 111 && x <= 141 && y >= 330 && ((Game_1.floor < floor && Mario.getX() > x) ? rand.nextInt(3) : rand.nextInt(10)) == 0 && floor > Game_1.floor) { //Stair 3
				downStair = true;
				down = true;
				while (!(x >= 111 && x <= 141 && y >= 400)) {
					if (!PointsAnimation.isAnimationPoints()) {
						x = 119;
						y += 7;
					}
					try { Thread.sleep(50); } catch (InterruptedException e) {}
				}
				floor--;
				downStair = false;
				down = false;
			} else if (x >= 271 && x <= 301 && y >= 327 && ((Game_1.floor < floor && Mario.getX() > x) ? rand.nextInt(3) : rand.nextInt(10)) == 0 && floor > Game_1.floor) { //Stair 4
				downStair = true;
				down = true;
				while (!(x >= 271 && x <= 301 && y >= 410)) {
					if (!PointsAnimation.isAnimationPoints()) {
						x = 277;
						y += 7;
					}
					try { Thread.sleep(50); } catch (InterruptedException e) {}
				}
				floor--;
				downStair = false;
				down = false;
			} else if (x >= 656 && x <= 680 && y >= 310 && ((Game_1.floor < floor && Mario.getX() > x) ? rand.nextInt(3) : rand.nextInt(10)) == 0 && floor > Game_1.floor) { //Stair 5
				downStair = true;
				down = true;
				while (!(x >= 656 && x <= 680 && y >= 418)) {
					if (!PointsAnimation.isAnimationPoints()) {
						x = 665;
						y += 7;
					}
					try { Thread.sleep(50); } catch (InterruptedException e) {}
				}
				floor--;
				downStair = false;
				down = false;
			} else if (x >= -15 && x <= 30 && y >= 346) {
				down = true;
				while (!(x >= -15 && x <= 30 && y >= 401)) {
					if (!PointsAnimation.isAnimationPoints()) {
						y += 7;
					}
					try { Thread.sleep(20); } catch (InterruptedException e) {}
				}
				floor--;
				down = false;
			} else {
				if (Game_1.floor > floor && x >= 830 && x <= 1000 && y >= 301 && !specialBarrel) {
					x += speed;
				} else {
					x -= speed;
				}
			}
		}
		

		private void floor4() {
			if (x >= 245 && x <= 269 && y >= 414 && ((Game_1.floor < floor && Mario.getX() < x) ? rand.nextInt(3) : rand.nextInt(10)) == 0 && floor > Game_1.floor) { //Stair 6
				downStair = true;
				down = true;
				while (!(x >= 245 && x <= 269 && y >= 520)) {
					if (!PointsAnimation.isAnimationPoints()) {
						x = 246;
						y += 7;
					}
					try { Thread.sleep(50); } catch (InterruptedException e) {}
				}
				floor--;
				downStair = false;
				down = false;
			} else if (x >= 437 && x <= 461 && y >= 420 && ((Game_1.floor < floor && Mario.getX() < x) ? rand.nextInt(3) : rand.nextInt(10)) == 0 && floor > Game_1.floor) { //Stair 7
				downStair = true;
				down = true;
				while (!(x >= 437 && x <= 461 && y >= 509)) {
					if (!PointsAnimation.isAnimationPoints()) {
						x = 441;
						y += 7;
					}
					try { Thread.sleep(50); } catch (InterruptedException e) {}
				}
				floor--;
				downStair = false;
				down = false;
			} else if (x >= 723 && x <= 749 && y >= 440 && ((Game_1.floor < floor && Mario.getX() < x) ? rand.nextInt(3) : rand.nextInt(10)) == 0 && floor > Game_1.floor) { //Stair 8
				downStair = true;
				down = true;
				while (!(x >= 723 && x <= 749 && y >= 498)) {
					if (!PointsAnimation.isAnimationPoints()) {
						x = 727;
						y += 7;
					}
					try { Thread.sleep(50); } catch (InterruptedException e) {}
				}
				floor--;
				downStair = false;
				down = false;
			} else if (x >= 830 && x <= 860 && y >= 437) {
				down = true;
				while (!(x >= 830 && x <= 860 && y >= 506)) {
					if (!PointsAnimation.isAnimationPoints()) {
						y += 7;
					}
					try { Thread.sleep(20); } catch (InterruptedException e) {}
				}
				floor--;
				down = false;
			} else {
				if (Game_1.floor > floor && x >= -100 && x <= 30 && y >= 401 && !specialBarrel) {
					x -= speed;
				} else {
					x += speed;
				}
			}
			
		}

		private void floor3() {
			if (x >= 113 && x <= 137 && y >= 540 && ((Game_1.floor < floor && Mario.getX() > x) ? rand.nextInt(3) : rand.nextInt(10)) == 0 && floor > Game_1.floor) { //Stair 9
				downStair = true;
				down = true;
				while (!(x >= 113 && x <= 137 && y >= 606)) {
					if (!PointsAnimation.isAnimationPoints()) {
						x = 118;
						y += 7;
					}
					try { Thread.sleep(50); } catch (InterruptedException e) {}
				}
				floor--;
				downStair = false;
				down = false;
			} else if (x >= 365 && x <= 395 && y >= 528 && ((Game_1.floor < floor && Mario.getX() > x) ? rand.nextInt(3) : rand.nextInt(10)) == 0 && floor > Game_1.floor) { //Stair 10
				downStair = true;
				down = true;
				while (!(x >= 365 && x <= 395 && y >= 618)) {
					if (!PointsAnimation.isAnimationPoints()) {
						x = 373;
						y += 7;
					}
					try { Thread.sleep(50); } catch (InterruptedException e) {}
				}
				floor--;
				downStair = false;
				down = false;
			} else if (x >= -15 && x <= 30 && y >= 542) {
				down = true;
				while (!(x >= -15 && x <= 30 && y >= 610)) {
					if (!PointsAnimation.isAnimationPoints()) {
						y += 7;
					}
					try { Thread.sleep(20); } catch (InterruptedException e) {}
				}
				floor--;
				down = false;
			} else {
				if (Game_1.floor > floor && x >= 830 && x <= 1000 && y >= 500 && !specialBarrel) {
					x += speed;
				} else {
					x -= speed;
				}
			}
			
		}

		private void floor2() {
			if (x >= 305 && x <= 329 && y >= 624 && ((Game_1.floor < floor && Mario.getX() < x) ? rand.nextInt(3) : rand.nextInt(10)) == 0 && floor > Game_1.floor) { //Stair 11
				downStair = true;
				down = true;
				while (!(x >= 305 && x <= 329 && y >= 730)) {
					if (!PointsAnimation.isAnimationPoints()) {
						x = 309;
						y += 7;
					}
					try { Thread.sleep(50); } catch (InterruptedException e) {}
				}
				floor--;
				downStair = false;
				down = false;
			} else if (x >= 725 && x <= 749 && y >= 642 && ((Game_1.floor < floor && Mario.getX() < x) ? rand.nextInt(3) : rand.nextInt(10)) == 0 && floor > Game_1.floor) { //Stair 12
				downStair = true;
				down = true;
				while (!(x >= 725 && x <= 749 && y >= 708)) {
					if (!PointsAnimation.isAnimationPoints()) {
						x = 728;
						y += 7;
					}
					try { Thread.sleep(50); } catch (InterruptedException e) {}
				}
				floor--;
				downStair = false;
				down = false;
			} else if (x >= 830 && x <= 860 && y >= 650) {
				down = true;
				while (!(x >= 830 && x <= 860 && y >= 713)) {
					if (!PointsAnimation.isAnimationPoints()) {
						y += 7;
					}
					try { Thread.sleep(20); } catch (InterruptedException e) {}
				}
				floor--;
				down = false;
			} else {
				if (Game_1.floor > floor && x >= -100 && x <= 30 && y >= 610 && !specialBarrel) {
					x -= speed;
				} else {
					x += speed;
				}
			}
		}
		
	}
	
	private class SynchronizedImage extends Thread {
		
		
		@Override
		public void run() { //Change image
			while (true) {
				try { Thread.sleep(90); } catch (InterruptedException e) {}
				if (Game_1.winMario) break;
				if (!PointsAnimation.isAnimationPoints()) {
				if (floor == 5 || floor == 3 || floor == 1) {
						if (steps == 1) steps++;
						else if (steps == 2) steps++;
						else if (steps == 3) steps++;
						else if (steps == 4) steps = 1;
					} else {
						if (steps == 1) steps = 4;
						else if (steps == 2) steps--;
						else if (steps == 3) steps--;
						else if (steps == 4) steps--;
					}
				}
			}
		}
		
	}
	
}
