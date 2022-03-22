package creator.map;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Random;

import static java.awt.event.KeyEvent.VK_1;
import static java.awt.event.KeyEvent.VK_2;
import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_RIGHT;
import static java.awt.event.KeyEvent.VK_UP;
import static java.awt.event.KeyEvent.VK_DOWN;
import static java.awt.event.KeyEvent.VK_SPACE;
import static java.awt.event.KeyEvent.VK_C;
import static java.awt.event.KeyEvent.VK_V;
import static java.awt.event.KeyEvent.VK_B;
import static java.awt.event.KeyEvent.VK_F;
import static java.awt.event.KeyEvent.VK_A;
import static java.awt.event.KeyEvent.VK_S;
import static java.awt.event.KeyEvent.VK_D;
import static java.awt.event.KeyEvent.VK_W;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import game.character.DonkeyKong;
import game.character.Peach;
import game.scene.level_1.BarrelFlame;
import game.scene.level_1.DonkeyBarrel;
import game.scene.level_1.Flame;
import game.scene.level_1.Hammer;
import game.scene.level_2.Cintas;
import main.Program;

@SuppressWarnings("unused")
public class CreatorMap extends JPanel {
	
	private final int x = 0;
	private final int y = 0;
	private final int width = Program.WIDTH;
	private final int height = Program.HEIGHT - 50;
	private Cinta cinta = new Cinta();
	private BlueBarrel barrel = new BlueBarrel();
	private int xRec = 1;
	private int yRec = 1;
	private int widthRec = 20;
	private int heightRec = 20;
	private static final long serialVersionUID = 2278588087180717438L;
	//private final DonkeyKong donkey = new DonkeyKong();
//	private final DonkeyBarrel barrel = new DonkeyBarrel();
	private final ImageIcon part_8 = new ImageIcon("img\\background\\level_2\\level_2.png");
	public static int speed = 1;
//	private Clip clip;
	//private Flame flame = new Flame();
//	private BarrelFlame barrelFlame = new BarrelFlame();
	private boolean out = true;
	
	public CreatorMap(int width, int height) {
		this.addKeyListener( new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
//				barrel.keyPressed(e);
				if (e.getKeyCode() == VK_1) speed++;
				if (e.getKeyCode() == VK_2) speed--;
				if (e.getKeyCode() == VK_LEFT) {
					cinta.setX(cinta.getX() - speed);
				}
				if (e.getKeyCode() == VK_RIGHT) {
					cinta.setX(cinta.getX() + speed);
				}
				if (e.getKeyCode() == VK_UP) {
					cinta.setY(cinta.getY() - speed);
				}
				if (e.getKeyCode() == VK_DOWN) {
					cinta.setY(cinta.getY() + speed);
				}
				if (e.getKeyCode() == VK_SPACE) {
					barrel.setWidth(barrel.getWidth() + 1);
				}
				if (e.getKeyCode() == VK_C) {
					barrel.setWidth(barrel.getWidth() - 1);
				}
				if (e.getKeyCode() == VK_V) {
					barrel.setHeight(barrel.getHeight() + 1);
				}
				if (e.getKeyCode() == VK_B) {
					barrel.setHeight(barrel.getHeight() - 1);
				}
				if (e.getKeyCode() == VK_W) {
					barrel.setY(barrel.getY() - speed);
				}
				if (e.getKeyCode() == VK_A) {
					barrel.setX(barrel.getX() - speed);
				}
				if (e.getKeyCode() == VK_S) {
					barrel.setY(barrel.getY() + speed);
				}
				if (e.getKeyCode() == VK_D) {
					barrel.setX(barrel.getX() + speed);
				}
			}
			
		});
		
		
		
		this.setFocusable(true);
		this.setSize(width, height);
		this.setBackground(Color.BLACK);
		this.setVisible(true);
		
	}
	
	public Rectangle getMouse(int x, int y) {
		return new Rectangle(x, y, 1, 1);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//System.out.println("[x: " + xRec + ", y: " + yRec + ", width: " + widthRec + ", height: " + heightRec + ", xB: " + barrel.getX() + ", yB: " + barrel.getY() +"]");
		System.out.println("Barrel: [x: " + barrel.getX() + ", y: " + barrel.getY() + ", width: " + barrel.getWidth() + ", height: " + barrel.getHeight() +  "]");
		System.out.println("Cinta: [x: " + cinta.getX() + ", y: " + cinta.getY() + "]");
		//System.out.println(new Random().nextInt((4 - 1) + 1) + 1);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(part_8.getImage(), x, y, width, height, null);
		g2d.setColor(Color.WHITE);
		g2d.setFont(new Font("Verdana", Font.BOLD, 30));
		g2d.drawString(String.valueOf(speed), 50, 50);
		cinta.paint(g2d);
		barrel.paint(g2d);
		//g2d.fillRect(xRec, yRec, widthRec, heightRec);
//		System.out.println(new Random().nextInt((2 - 2) + 1) + 2);
//		barrel.paint(g2d);
		//flame.paint(g2d);
		g2d.drawRect(xRec, yRec, widthRec, heightRec);
		this.repaint();
	}
	
	
}
