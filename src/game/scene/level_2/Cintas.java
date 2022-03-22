package game.scene.level_2;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 * Cinta: [x: 19, y: 658, direccion = false]
 * Cinta: [x: 853, y: 652, direccion = true]
 * Cinta: [x: 487, y: 403]
 * Barrel: [x: 417, y: 355, width: 69, height: 103]
 * Cinta: [x: 386, y: 403]
 * @author marco
 *
 */
public class Cintas {
	
	private final int x1;
	private final int y1;
	private final int x2;
	private final int y2;
	private final int width = 30;
	private final int height = 30;
	private int steps = 0;
	private boolean direccion; //False -> left  True -> Right
	private int zona;
	private final int yTarta;
	private boolean addCake = false;
	private List<Cake> list = new ArrayList<Cake>();
	private final ImageIcon cinta_left_1 = new ImageIcon("img\\cinta\\cinta_left_1.png");
	private final ImageIcon cinta_left_2 = new ImageIcon("img\\cinta\\cinta_left_2.png");
	private final ImageIcon cinta_left_3 = new ImageIcon("img\\cinta\\cinta_left_3.png");
	private final ImageIcon cinta_right_1 = new ImageIcon("img\\cinta\\cinta_right_1.png");
	private final ImageIcon cinta_right_2 = new ImageIcon("img\\cinta\\cinta_right_2.png");
	private final ImageIcon cinta_right_3 = new ImageIcon("img\\cinta\\cinta_right_3.png");
	private Random rand = new Random();
	private boolean cinta_left = false;
	private boolean cinta_right = false;
	
	public Cintas(int x1, int y1, int x2, int y2, int zona) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.zona = zona;
		direccion = rand.nextBoolean();
		if (zona == 0) this.yTarta = 624;
		else if (zona == 1) this.yTarta = 376;
		else this.yTarta = 0;
		new MoveCinta_1().start();
	}
	
	public void paint(Graphics2D g) {
		printCintaLeft(g);
		printCintaRight(g);
		if (addCake) {
			if (zona == 0) list.add(new Cake(direccion ? 0 : 900, yTarta, direccion));
			else if (zona == 1) {
				if (cinta_left) list.add(new Cake(0, yTarta, true));
				if (cinta_right) list.add(new Cake(900, yTarta, false));
			}
			cinta_left = false;
			cinta_right = false;
			addCake = false;
		}
		for (Cake cake : list) {
			if (zona == 0) {
				cake.setDireccion(direccion);
				if (cake.getX() < - 50 || cake.getX() > 950) {
					list.remove(cake);
					break;
				}
			} else {
				if (cake.getX() > 425 && cake.getX() < 440) {
					list.remove(cake);
					break;
				}
			}
			cake.paint(g);
		}
	}

	private void printCintaLeft(Graphics2D g) {
		if (zona == 0) {
			if (steps == 2) g.drawImage(cinta_left_1.getImage(), x1, y1, width, height, null);
			else if (steps == 1) g.drawImage(cinta_left_2.getImage(), x1, y1, width, height, null);
			else if (steps == 0) g.drawImage(cinta_left_3.getImage(), x1, y1, width, height, null);
		} else {
			if (steps == 0) g.drawImage(cinta_left_1.getImage(), x1, y1, width, height, null);
			else if (steps == 1) g.drawImage(cinta_left_2.getImage(), x1, y1, width, height, null);
			else if (steps == 2) g.drawImage(cinta_left_3.getImage(), x1, y1, width, height, null);
		}
	}

	private void printCintaRight(Graphics2D g) {
		if (steps == 0) g.drawImage(cinta_right_1.getImage(), x2, y2, width, height, null);
		else if (steps == 1) g.drawImage(cinta_right_2.getImage(), x2, y2, width, height, null);
		else if (steps == 2) g.drawImage(cinta_right_3.getImage(), x2, y2, width, height, null);
	}
	
	private class MoveCinta_1 extends Thread {
		
		private int contador1 = zona == 0 ? rand.nextInt((15 - 8) + 1) + 8 : rand.nextInt((25 - 15) + 1) + 15;
		private int contador2 = rand.nextInt((25 - 15) + 1) + 15;
		
		@Override
		public void run() {
			while (true) {
				try { Thread.sleep(500); } catch (InterruptedException e) {}
				if (direccion) printDireccionRight();
				else printDireccionLeft();
				if (zona == 0) {
					if (rand.nextInt((20 - 1) + 1) + 1 == 5) {
						if (direccion) direccion = false;
						else direccion = true;
					}
					if (contador1 == 0) {
						cinta_right = true;
						addCake = true;
						contador1 = rand.nextInt((15 - 8) + 1) + 8;
					} else contador1--;
				} else if (zona == 1) {
					if (contador1 == 0) {
						cinta_right = true;
						addCake = true;
						contador1 = rand.nextInt((25 - 15) + 1) + 15;
					} else contador1--;
					if (contador2 == 0) {
						cinta_left = true;
						addCake = true;
						contador2 = rand.nextInt((25 - 15) + 1) + 15;
					} else contador2--;
				}
			}
		}

		private void printDireccionRight() {
			if (steps == 0) steps = 2;
			else if (steps == 1) steps--;
			else if (steps == 2) steps--;
		}

		private void printDireccionLeft() {
			if (steps == 0) steps++;
			else if (steps == 1) steps++;
			else if (steps == 2) steps = 0;
		}
		
	}
	
}
