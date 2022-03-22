package game.character;

import static java.awt.event.KeyEvent.VK_UP;

import java.awt.Color;
import java.awt.Font;

import static java.awt.event.KeyEvent.VK_DOWN;
import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_RIGHT;
import static java.awt.event.KeyEvent.VK_SPACE;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;

import game.scene.Scene;
import game.scene.level_1.Game_1;
import game.scene.level_1.Hammer;
import game.scene.level_1.PointsAnimation;
import game.scene.level_1.collision.Collision;
import lombok.*;


/**
 * Out of map:
 * [x: 0, y: 680, width: 2, height: 97] -> 1
 * [x: 881, y: 684, width: 2, height: 71] -> 2
 * [x: 839, y: 618, width: 1, height: 71] -> 3
 * [x: 0, y: 553, width: 2, height: 97] -> 4
 * [x: 63, y: 489, width: 1, height: 97] -> 5
 * [x: 882, y: 451, width: 1, height: 97] -> 6
 * [x: 838, y: 411, width: 1, height: 71] -> 7--
 * [x: 0, y: 346, width: 1, height: 97] -> 8
 * [x: 64, y: 290, width: 1, height: 90] -> 9
 * [x: 881, y: 249, width: 1, height: 92] -> 10
 * [x: 837, y: 185, width: 1, height: 90] -> 11
 * 
 * @author marco
 *
 */
public class Mario {
	
	@Getter @Setter private static int x = 180;
	@Getter @Setter private static int y = 730;
	private final static int width = 40;
	private final static int height = 50;
	private final static int widthHammerUp = 38;//Hammer-up
	private final static int heightHammerUp = 80;//Hammer-up
	
	private final static int widthHammerDown = 83;//Hammer-down
	private final static int heightHammerDown = 47;//Hammer-down
	private static int steps = 0;
	private final int sleep = 100;
	// 1-left | 2-right
	@Getter @Setter private int direccion;
	//Var Jump
	@Getter private boolean jump = false;
	private boolean jumpCondition = false;
	@Getter private boolean upStrs = false;
	@Getter private boolean downStrs = false;
	private boolean animationStairs = false;
	@Getter private boolean strs = false;
	public static boolean resetMario = false;
	public static boolean marioDeath = false; //When Mario Death
	private int marioDeathSteps = 1; //Animation Mario Death
	private int yi; //Calcular el inicio del salto
	private int lives = 3;//Lives Mario 3
	//Imagenes
	private final ImageIcon marioLeft = new ImageIcon("img\\mario\\rojo\\Mario_Parado_Left.png");
	private final ImageIcon marioAndaLeft_1 = new ImageIcon("img\\mario\\rojo\\Mario_Anda_Left_1.png");
	private final ImageIcon marioAndaLeft_2 = new ImageIcon("img\\mario\\rojo\\Mario_Anda_Left_2.png");
	private final ImageIcon marioRight = new ImageIcon("img\\mario\\rojo\\Mario_Parado_Right.png");
	private final ImageIcon marioAndaRight_1 = new ImageIcon("img\\mario\\rojo\\Mario_Anda_Right_1.png");
	private final ImageIcon marioAndaRight_2 = new ImageIcon("img\\mario\\rojo\\Mario_Anda_Right_2.png");
	private final ImageIcon marioEscaleras_1 = new ImageIcon("img\\mario\\rojo\\Mario_Escaleras_1.png");
	private final ImageIcon marioEscaleras_2 = new ImageIcon("img\\mario\\rojo\\Mario_Escaleras_2.png");
	private final ImageIcon marioEscaleras_3 = new ImageIcon("img\\mario\\rojo\\Mario_Escaleras_3.png");
	private final ImageIcon marioEscaleras_4 = new ImageIcon("img\\mario\\rojo\\Mario_Escaleras_4.png");
	private final ImageIcon marioEscaleras_5 = new ImageIcon("img\\mario\\rojo\\Mario_Escaleras_5.png");
	private final ImageIcon marioJump_left = new ImageIcon("img\\mario\\rojo\\mario_jump_left.png");
	private final ImageIcon marioJump_right = new ImageIcon("img\\mario\\rojo\\mario_jump_right.png");
	private final ImageIcon marioDeath_1 = new ImageIcon("img\\mario\\rojo\\Mario_Death_1.png");
	private final ImageIcon marioDeath_2 = new ImageIcon("img\\mario\\rojo\\Mario_Death_2.png");
	private final ImageIcon marioDeath_3 = new ImageIcon("img\\mario\\rojo\\Mario_Death_3.png");
	private final ImageIcon marioDeath_4 = new ImageIcon("img\\mario\\rojo\\Mario_Death_4.png");
	private final ImageIcon mario_death = new ImageIcon("img\\mario\\rojo\\Mario_Death.png");
	private final ImageIcon mario_left_hammer = new ImageIcon("img\\mario\\rojo\\hammer\\mario_with_hammer\\left\\mario_hammer.png");//Hammer
	private final ImageIcon mario_right_hammer = new ImageIcon("img\\mario\\rojo\\hammer\\mario_with_hammer\\right\\mario_hammer.png");
	private final ImageIcon mario_left_hammer_up_run_1 = new ImageIcon("img\\mario\\rojo\\hammer\\mario_with_hammer\\left\\mario_hammer_up_2.png");
	private final ImageIcon mario_left_hammer_up_run_1_special = new ImageIcon("img\\mario\\rojo\\hammer\\mario_with_hammer\\left\\mario_hammer_up_2_special.png");
	private final ImageIcon mario_left_hammer_up_run_2 = new ImageIcon("img\\mario\\rojo\\hammer\\mario_with_hammer\\left\\mario_hammer_up_3.png");
	private final ImageIcon mario_left_hammer_up_run_2_special = new ImageIcon("img\\mario\\rojo\\hammer\\mario_with_hammer\\left\\mario_hammer_up_3_special.png");
	private final ImageIcon mario_left_hammer_down_run_1 = new ImageIcon("img\\mario\\rojo\\hammer\\mario_with_hammer\\left\\mario_hammer_down_2.png");
	private final ImageIcon mario_left_hammer_down_run_1_special = new ImageIcon("img\\mario\\rojo\\hammer\\mario_with_hammer\\left\\mario_hammer_down_2_special.png");
	private final ImageIcon mario_left_hammer_down_run_2 = new ImageIcon("img\\mario\\rojo\\hammer\\mario_with_hammer\\left\\mario_hammer_down_3.png");
	private final ImageIcon mario_left_hammer_down_run_2_special = new ImageIcon("img\\mario\\rojo\\hammer\\mario_with_hammer\\left\\mario_hammer_down_3_special.png");
	private final ImageIcon mario_right_hammer_up_run_1 = new ImageIcon("img\\mario\\rojo\\hammer\\mario_with_hammer\\right\\mario_hammer_up_2.png");
	private final ImageIcon mario_right_hammer_up_run_1_special = new ImageIcon("img\\mario\\rojo\\hammer\\mario_with_hammer\\right\\mario_hammer_up_2_special.png");
	private final ImageIcon mario_right_hammer_up_run_2 = new ImageIcon("img\\mario\\rojo\\hammer\\mario_with_hammer\\right\\mario_hammer_up_3.png");
	private final ImageIcon mario_right_hammer_up_run_2_special = new ImageIcon("img\\mario\\rojo\\hammer\\mario_with_hammer\\right\\mario_hammer_up_3_special.png");
	private final ImageIcon mario_right_hammer_down_run_1 = new ImageIcon("img\\mario\\rojo\\hammer\\mario_with_hammer\\right\\mario_hammer_down_2.png");
	private final ImageIcon mario_right_hammer_down_run_1_special = new ImageIcon("img\\mario\\rojo\\hammer\\mario_with_hammer\\right\\mario_hammer_down_2_special.png");
	private final ImageIcon mario_right_hammer_down_run_2 = new ImageIcon("img\\mario\\rojo\\hammer\\mario_with_hammer\\right\\mario_hammer_down_3.png");
	private final ImageIcon mario_right_hammer_down_run_2_special = new ImageIcon("img\\mario\\rojo\\hammer\\mario_with_hammer\\right\\mario_hammer_down_3_special.png");
	private final ImageIcon mario_left_hammer_up_stop = new ImageIcon("img\\mario\\rojo\\hammer\\mario_with_hammer\\left\\mario_hammer_up_1_stop.png");
	private final ImageIcon mario_left_hammer_up_stop_special = new ImageIcon("img\\mario\\rojo\\hammer\\mario_with_hammer\\left\\mario_hammer_up_1_stop_special.png");
	private final ImageIcon mario_left_hammer_down_stop = new ImageIcon("img\\mario\\rojo\\hammer\\mario_with_hammer\\left\\mario_hammer_down_1_stop.png");
	private final ImageIcon mario_left_hammer_down_stop_special = new ImageIcon("img\\mario\\rojo\\hammer\\mario_with_hammer\\left\\mario_hammer_down_1_stop_special.png");
	private final ImageIcon mario_right_hammer_up_stop = new ImageIcon("img\\mario\\rojo\\hammer\\mario_with_hammer\\right\\mario_hammer_up_1_stop.png");
	private final ImageIcon mario_right_hammer_up_stop_special = new ImageIcon("img\\mario\\rojo\\hammer\\mario_with_hammer\\right\\mario_hammer_up_1_stop_special.png");
	private final ImageIcon mario_right_hammer_down_stop = new ImageIcon("img\\mario\\rojo\\hammer\\mario_with_hammer\\right\\mario_hammer_down_1_stop.png");
	private final ImageIcon mario_right_hammer_down_stop_special = new ImageIcon("img\\mario\\rojo\\hammer\\mario_with_hammer\\right\\mario_hammer_down_1_stop_special.png");
	private final ImageIcon mario_drop_left = new ImageIcon("img\\mario\\rojo\\Mario_Drop_Left.png");
	private final ImageIcon mario_drop_right = new ImageIcon("img\\mario\\rojo\\Mario_Drop_Right.png");
	private Clip soundMarioWalk;
	private Clip soundMarioHammer;
	private Clip soundMarioJump;
	private boolean sound = true;
	private boolean down = false;
	private boolean strsUp = false;
	private boolean strsDown = false;
	@Getter private boolean marioDrop = false;
	private boolean marioDeathDrop = false;
	@Getter @Setter private static boolean marioHammer = false;
	@Getter @Setter private static int points = 0;
	private boolean ambar = false;
	private Hammer hammer1 = new Hammer(71, 300);
	private Hammer hammer2 = new Hammer(681, 599);
	
	public Mario(int direccion) {
		this.direccion = direccion;
		try { 
			soundMarioWalk = AudioSystem.getClip(); 
			soundMarioHammer = AudioSystem.getClip(); 
			soundMarioJump = AudioSystem.getClip();
		} catch (LineUnavailableException e) {}
		new MovementMario().start();
		new TimeStepsMario().start();
	}


	/**
	 * 1- Move Mario Left
	 * 2- Move Mario Right
	 * 3- Stop Mario Left
	 * 4- Stop Mario Left
	 * @param g
	 */
	public void paint(Graphics2D g) {
		if (marioDrop) {
			if (marioDeath) printMarioDeath(g);
			else {
				if (marioDeathDrop) {
					if (direccion == 1 || direccion == 3) g.drawImage(mario_drop_left.getImage(), x, y, width + 5, height - 5, null);
					else if (direccion == 2 || direccion == 4) g.drawImage(mario_drop_right.getImage(), x, y, width + 5, height - 5, null);
				} else {
					if (direccion == 1 || direccion == 3) g.drawImage(marioLeft.getImage(), x, y, width, height, null);
					else if (direccion == 2 || direccion == 4) g.drawImage(marioRight.getImage(), x, y, width, height, null);
				}
			}
		} else if (marioHammer) paintMarioHammer(g);
		else paintMarioNormal(g);
		g.setColor(Color.white);
		g.setFont(new Font("Verdana", Font.BOLD, 30));
		g.drawString(String.valueOf(lives), 750, 97);
		g.drawString(String.valueOf(points), 50, 50);
//		g.drawRect(x + 9, y + 5, width - 19, height - 16);
//		System.out.println("x: " + x + " y: " + y);
	}
	
	/**
	 * Mario with hammer
	 * @param g
	 */
	private void paintMarioHammer(Graphics2D g) {
		if (jump) {
			if (direccion == 1 || direccion == 3) {
				g.drawImage(mario_left_hammer.getImage(), x - 2, y - 32, widthHammerUp + 8, heightHammerUp, null);
			} else if (direccion == 2 || direccion == 4) {
				g.drawImage(mario_right_hammer.getImage(), x - 2, y - 32, widthHammerUp + 8, heightHammerUp, null);
			}
		} else {
			if (direccion == 1) {//Mario Run Left
				if (steps == 0) {
					if (ambar) g.drawImage(mario_left_hammer_up_run_1_special.getImage(), x - 2, y - 32, widthHammerUp, heightHammerUp, null);
					else g.drawImage(mario_left_hammer_up_run_1.getImage(), x - 2, y - 32, widthHammerUp, heightHammerUp, null);
				} else if (steps == 1) {
					if (ambar) g.drawImage(mario_left_hammer_up_run_2_special.getImage(), x - 2, y - 32, widthHammerUp, heightHammerUp, null);
					else g.drawImage(mario_left_hammer_up_run_2.getImage(), x - 2, y - 32, widthHammerUp, heightHammerUp, null);
				} else if (steps == 2) {
					if (ambar) g.drawImage(mario_left_hammer_down_run_1_special.getImage(), x - 45, y + 1, widthHammerDown, heightHammerDown, null);
					else g.drawImage(mario_left_hammer_down_run_1.getImage(), x - 45, y + 1, widthHammerDown, heightHammerDown, null);
				} else if (steps == 3) {
					if (ambar) g.drawImage(mario_left_hammer_down_run_2_special.getImage(), x - 45, y + 1, widthHammerDown, heightHammerDown, null);
					else g.drawImage(mario_left_hammer_down_run_2.getImage(), x - 45, y + 1, widthHammerDown, heightHammerDown, null);
				}
			} else if (direccion == 2) {
				if (steps == 0) {
					if (ambar) g.drawImage(mario_right_hammer_up_run_1_special.getImage(), x - 2, y - 32, widthHammerUp, heightHammerUp, null);
					else g.drawImage(mario_right_hammer_up_run_1.getImage(), x - 2, y - 32, widthHammerUp, heightHammerUp, null);
				} else if (steps == 1) {
					if (ambar) g.drawImage(mario_right_hammer_up_run_2_special.getImage(), x - 2, y - 32, widthHammerUp, heightHammerUp, null);
					else g.drawImage(mario_right_hammer_up_run_2.getImage(), x - 2, y - 32, widthHammerUp, heightHammerUp, null);
				} else if (steps == 2) {
					if (ambar) g.drawImage(mario_right_hammer_down_run_1_special.getImage(), x + 6, y + 1, widthHammerDown, heightHammerDown, null);
					else g.drawImage(mario_right_hammer_down_run_1.getImage(), x + 6, y + 1, widthHammerDown, heightHammerDown, null);
				} else if (steps == 3) {
					if (ambar) g.drawImage(mario_right_hammer_down_run_2_special.getImage(), x + 6, y + 1, widthHammerDown, heightHammerDown, null);
					else g.drawImage(mario_right_hammer_down_run_2.getImage(), x + 6, y + 1, widthHammerDown, heightHammerDown, null);
				}
			} else if (direccion == 3) {
				if (steps == 0 || steps == 2) {
					if (ambar) g.drawImage(mario_left_hammer_up_stop_special.getImage(), x - 2, y - 32, widthHammerUp, heightHammerUp, null);
					else g.drawImage(mario_left_hammer_up_stop.getImage(), x - 2, y - 32, widthHammerUp, heightHammerUp, null);
				} else if (steps == 1 || steps == 3) {
					if (ambar) g.drawImage(mario_left_hammer_down_stop_special.getImage(), x - 45, y + 1, widthHammerDown, heightHammerDown, null);
					else g.drawImage(mario_left_hammer_down_stop.getImage(), x - 45, y + 1, widthHammerDown, heightHammerDown, null);
				}
			} else if (direccion == 4) {
				if (steps == 0 || steps == 2) {
					if (ambar) g.drawImage(mario_right_hammer_up_stop_special.getImage(), x - 2, y - 32, widthHammerUp, heightHammerUp, null);
					else g.drawImage(mario_right_hammer_up_stop.getImage(), x - 2, y - 32, widthHammerUp, heightHammerUp, null);
				} else if (steps == 1 || steps == 3) {
					if (ambar) g.drawImage(mario_right_hammer_down_stop_special.getImage(), x + 6, y + 1, widthHammerDown, heightHammerDown, null);
					else g.drawImage(mario_right_hammer_down_stop.getImage(), x + 6, y + 1, widthHammerDown, heightHammerDown, null);
				}
			}
		}
	}

	/**
	 * Mario not hammer
	 * @param g
	 */
	private void paintMarioNormal(Graphics2D g) {
		if (!marioDeath) {
			if (direccion == 1) paintMarioRunLeft(g); //Left Run
			else if (direccion == 2) paintMarioRunRight(g); //Right Run
			else if (direccion == 3) {
				if (jump) g.drawImage(marioJump_left.getImage(), x, y, width, height, null); //If jump left
				else g.drawImage(marioLeft.getImage(), x, y, width, height, null); //LeftStop
			}
			else if (direccion == 4) {
				if (jump) g.drawImage(marioJump_right.getImage(), x, y, width, height, null); //If jump right
				else g.drawImage(marioRight.getImage(), x, y, width, height, null); //Rightstop
			}
			else if (direccion == 5 || direccion == 6 || direccion == 7) paintMarioStairs(g); //Stairs
		} else {
			printMarioDeath(g);
		}
		hammer1.paint(g);
		hammer2.paint(g);
	}


	/**
	 * Spanish: Este método es el que va a restarle una vida a Mario, colocarle en el inicio
	 * 			y avisar al resto de clases que ya se ha reseteado y que comienze otra vez
	 * 
	 * English: This method is the one that will subtract a life from Mario, place him at the 
	 * 			beginning and notify the rest of the classes that it has already been reset and to start again 
	 */
	public void resetMarioDeath() {
		if (lives != 0) {
			Scene.listLives.remove(lives - 1);
			lives--;
			Game_1.floor = 1;
			direccion = 4;
			jump = false;
			setX(125);
			setY(730);
			sound = true;
			marioDeathSteps = 1;
			marioDeath = false;
			resetMario = true;
			marioDrop = false;
			marioDeathDrop = false;
		} else {
			Scene.gameOver = true;
		}
	}
	
	/**
	 * Rectangulo de la colisión
	 * @return
	 */
	public static Rectangle getBounds() {
		if (marioHammer) {
			if (steps == 0 || steps == 1) {
				return new Rectangle(x - 2 , y - 32, widthHammerUp, heightHammerUp);
			} else {
				return new Rectangle(x + 6 , y - 1, widthHammerDown, heightHammerDown);
			}
		}
		else return new Rectangle(x + 9, y + 5, width - 19, height - 16);
	}
	
	/**
	 * Spanish: Pinto las transiciones de Mario(Izquierda) manejadas por TimeStepsMario
	 * 
	 * English: I paint Mario(Left) transitions handled by TimeStepsMario
	 * @param g
	 */
	public synchronized void paintMarioRunLeft(Graphics2D g) {
		if (!jump) {
			if (steps == 0) g.drawImage(marioAndaLeft_1.getImage(), x, y, width, height, null);
			else if (steps == 1) g.drawImage(marioAndaLeft_2.getImage(), x, y, width, height, null);
		} else {
			g.drawImage(marioJump_left.getImage(), x, y, width, height, null); //If jump left
		}
	}
	
	/**
	 * Spanish: Pinto las transiciones de Mario(Derecha) manejadas por TimeStepsMario
	 * 
	 * English: I paint Mario(Right) transitions handled by TimeStepsMario
	 * @param g
	 */
	public synchronized void paintMarioRunRight(Graphics2D g) {
		if (!jump) {
			if (steps == 0) g.drawImage(marioAndaRight_1.getImage(), x, y, width, height, null);
			else if (steps == 1) g.drawImage(marioAndaRight_2.getImage(), x, y, width, height, null);
		} else {
			g.drawImage(marioJump_right.getImage(), x, y, width, height, null); //If jump right
		}
	}
	
	private synchronized void paintMarioStairs(Graphics2D g) {
		if (animationStairs) {
			if (steps == 0) g.drawImage(marioEscaleras_3.getImage(), x, y, width, height, null);
			else if (steps == 1) g.drawImage(marioEscaleras_4.getImage(), x, y, width, height, null);
			else if (steps == 2) g.drawImage(marioEscaleras_5.getImage(), x, y, width, height, null);
		} else {
			if (steps == 0) g.drawImage(marioEscaleras_1.getImage(), x, y, width, height, null);
			else if (steps == 1) g.drawImage(marioEscaleras_2.getImage(), x, y, width, height, null);
		}
	}
	
	/**
	 * Print Animation Mario Death
	 * @param g
	 */
	private void printMarioDeath(Graphics2D g) {
		if (marioDeathSteps == 1) {
			g.drawImage(marioDeath_1.getImage(), x, y, width, height, null);
		} else if (marioDeathSteps == 2) {
			g.drawImage(marioDeath_2.getImage(), x, y, width, height, null);
		} else if (marioDeathSteps == 3) {
			g.drawImage(marioDeath_3.getImage(), x, y, width, height, null);
		} else if (marioDeathSteps == 4) {
			g.drawImage(marioDeath_4.getImage(), x, y, width, height, null);
		} else if (marioDeathSteps == 5) {
			g.drawImage(mario_death.getImage(), x, y, width, height, null);
		} 
	}
	
	/**
	 * Listening if Mario stops
	 * @param e
	 */
	public void keyReleased(KeyEvent e) {
		if (!marioDeath) {
			if (e.getKeyCode() == VK_SPACE && !jump && !marioHammer && !(direccion == 5 || direccion == 6 || direccion == 7)) {
				yi = y;
				jump = true;
				jumpCondition = true;
				try {
					if (!soundMarioJump.isRunning()) {
						soundMarioJump = AudioSystem.getClip();
						soundMarioJump.open(AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/audio/wav/mario_jump.wav")));
						soundMarioJump.start();
					}
					
				} 
				catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {}
			}
			else if (direccion == 1) {
				soundMarioWalk.stop(); //Stop Mario Walking
				direccion = 3;
			}
			else if (direccion == 2) {
				soundMarioWalk.stop(); //Stop Mario Walking
				direccion = 4;
			}
			else if (direccion == 5 || direccion == 6) direccion = 7;
		}
	}
	
	/**
	 * Listening the direction of Mario
	 * @param e
	 */
	public void keyPressed(KeyEvent e) {
		if (!marioDeath && !resetMario) {
			if (e.getKeyCode() == VK_LEFT && (!upStrs && !downStrs)) {
				if (direccion != 1) direccion = 1;
				soundMarioWalk(); //Sound Mario Walking
			} else if (e.getKeyCode() == VK_RIGHT && (!upStrs && !downStrs)) {
				if (direccion != 2) direccion = 2;
				soundMarioWalk();
			} else if (e.getKeyCode() == VK_UP && !jump && strs && strsUp && !marioHammer) { //Stair and not Jump
				upStrs = true;
				downStrs = true;
				strsDown = true;
				if (direccion != 5) direccion = 5;
			} else if (e.getKeyCode() == VK_DOWN && !jump && strs && strsDown && !marioHammer) {
				upStrs = false;
				downStrs = true;
				strsUp = true;
				if (direccion != 6) direccion = 6;
			}
		}
	}
	
	/**
	 * This is the sound Mario walking
	 */
	private void soundMarioWalk() {
		try {
			if (!soundMarioWalk.isRunning() && !Mario.marioDeath && !PointsAnimation.isAnimationPoints()) {
				soundMarioWalk = AudioSystem.getClip();
				soundMarioWalk.open(AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/audio/wav/mario_walking.wav")));
				soundMarioWalk.start();
			}
			if (soundMarioJump.isRunning()) {
				soundMarioWalk.stop();
			}
			
		} 
		catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {}
	}
	
	/**
	 * Spanish: Este hilo se dedica a cambiar las transiciones de las imagenes
	 * 			para no dormir el hilo principal y que pare el resto de objetos
	 * 			entonces manejo un flag.
	 * 
	 * English: This thread is dedicated to change the transitions of the images
	 * 		 	so as not to put the main thread to sleep and stop the rest of 
	 * 			the objects then I handle a flag.
	 * @author marco
	 *
	 */
	private class TimeStepsMario extends Thread {
		
		private int marioEnd = 0; //Cuando Mario muere
		private Clip clip;
		
		@Override
		public synchronized void run() {
			while(true) {
				/**
				 * Cambio entre correr(izquierda y derecha), y subir escaleras
				 */
				try { Thread.sleep(sleep); } catch (InterruptedException e) {}
				if (Game_1.winMario) break;
				if (!PointsAnimation.isAnimationPoints()) {//Si hay animacion de puntos no moverse
					if (!marioDeath) {
						if (marioHammer) {
							try {
								if (!soundMarioHammer.isRunning()) {
									soundMarioHammer = AudioSystem.getClip();
									soundMarioHammer.open(AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/audio/wav/hammer.wav")));
									soundMarioHammer.start();
								}
								
							} 
							catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {}
							if (!PointsAnimation.isAnimationPoints()) {
								if (steps == 0) steps++;
								else if (steps == 1) steps++;
								else if (steps == 2) steps++;
								else if (steps == 3) steps = 0;
							}
						} else {
							if (direccion == 1 || direccion == 2 || direccion == 5 || direccion == 6) { //Mario no death
								if (!jump) {
									if (steps == 0) steps = 1;
									else if (steps == 1) steps = 0;
								}
							} 
							else if (direccion != 7) steps = 0;
						}
					} else { //Mario Death
						if (sound) {
							soundDeath(); //Reproducir Sonido y detener a Mario
							try { Thread.sleep(500); } catch (InterruptedException e) {}
						}
						if (marioDeathSteps == 1) {
							marioDeathSteps++;
						} else if (marioDeathSteps == 2) {
							marioDeathSteps++;
						} else if (marioDeathSteps == 3) {
							marioDeathSteps++;
						} else if (marioDeathSteps == 4) {//Cuando Mario Muere
							if (marioEnd == 5) { //Vueltas para mario death
								marioDeathSteps++;
								marioEnd = 0;
								try { Thread.sleep(1500); } catch (InterruptedException e) {}
								resetMarioDeath();
							} else {
								marioEnd++;
								marioDeathSteps = 1;
							}
						}
					}
				}
			}
		}

		private void soundDeath() {
			try {
				clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/audio/wav/mario_death.wav")));
				clip.start();
			} 
			catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {}
			finally {
				sound = false;
			}
			
		}
	}
	
	/**
	 * Spanish: Este hilo se dedica a mover al personaje. Y hace la comprobación de las escaleras
	 * 
	 * English: This thread is dedicated to moving the character
	 * @author marco
	 *
	 */
	private class MovementMario extends Thread {
		
		private final int pasos = 3;
		private int ymax = 45;
		private float V = 3; //Speed
		private int collision;

		@Override
		public synchronized void run() {
			while (true) {
				try { Thread.sleep(20); } catch (InterruptedException e) {}
				if (Game_1.winMario) break;
				if (!PointsAnimation.isAnimationPoints()) {
					if (marioDeath) {
						while (!Mario.resetMario) {
							try { Thread.sleep(1); } catch (InterruptedException e) {}
							direccion = 0;
							hammer1.setVisible(true);
							hammer2.setVisible(true);
							if (Mario.resetMario) {
								break;
							}
						}
					}
					if (jump) {
						if ((yi - ymax) < y && jumpCondition) {
							down = false;
							y -= V;
						}
						else if (y <= yi && !jumpCondition && down) {
							y += V;
						}
						else {
							if (!jumpCondition) {
								try { Thread.sleep(20); } catch (InterruptedException e) {}
								down = true;
							} else {
								try { Thread.sleep(20); } catch (InterruptedException e) {}
								jumpCondition = false;
							}
							
						}
						
						if (y >= yi && !jumpCondition) {
							y = yi;
							jump = false;
						}
					}
					/**
					 * Spanish: Compruebo si Mario esta en alguna de las escaleras, 
					 * 			tanto de bajada como de subida.
					 * English: I check if Mario is on any of the stairs, both going down and up. 
					 */
					if (Game_1.floor == 1) floor1(); 	  //------->Floor 1
					else if (Game_1.floor == 2) floor2(); //------->Floor 2
					else if (Game_1.floor == 3) floor3(); //------->Floor 3
					else if (Game_1.floor == 4) floor4(); //------->Floor 4
					else if (Game_1.floor == 5) floor5(); //------->Floor 5
					else if (Game_1.floor == 6) floor6(); //------->Floor 6
					change(); ///Move Mario
					if ((collision = Collision.getCollisionMarioOutOfMap(getBounds(), pasos)) != 0) x = x + collision;//Limit map
					if (Collision.getCollisionMarioOutOfMap(getBounds())) { // Mario muerto por caida
						marioDrop = true;
						for (int i = 0; i < 32; i++) {
							y += 2;
							if (i == 30) marioDeathDrop = true;
							try { Thread.sleep(20); } catch (InterruptedException e) {}
						}
						try { Thread.sleep(150); } catch (InterruptedException e) {}
						DonkeyKong.setGameEnd(true);
					}
					if (getBounds().intersects(hammer1.getBounds()) && hammer1.isVisible()) {
						timerHammer();
						marioHammer = true;
						hammer1.setVisible(false);// Implementar las acciones de los martillos
					}
					else if (getBounds().intersects(hammer2.getBounds()) && hammer2.isVisible()) {
						timerHammer();
						marioHammer = true;
						hammer2.setVisible(false);
					}
				}
			}
		}
		
		private synchronized void timerHammer() {
			Thread timer = new Thread() {
				@Override
				public void run() {
					for (int i = 0; i <= 100; i++) {
						if (PointsAnimation.isAnimationPoints()) i--;
						try { Thread.sleep(50); } catch (InterruptedException e) {}
					}
					for (int i = 0; i < 100; i++) {
						if (PointsAnimation.isAnimationPoints()) i--;
						else {
							if (ambar) ambar = false;
							else ambar = true;
						}
						try { Thread.sleep(50); } catch (InterruptedException e) {}
					}
					marioHammer = false;
					steps = 0;
				}
			};
			timer.start();
		}

		private void floor1() {
			if (x >= 730 && x <= 752 && y <= 716) { //Up stair
				if (x >= 730 && x <= 752 && y <= 655) { //Top of the stair
					animationStair();
					Game_1.floor = 2;
					strsUp = false;
					strsDown = false;
					direccion = 3;
				} else {
					strsUp = true;
					strs = true;
				}
			} else notStairs();
		}
			
		private void floor2() {
			if (x >= 730 && x <= 752 && y >= 640) { //Down stair x: 738 y: 640
				strs = true;
				strsDown = true;
				if (downStrs) {
					animationStairDown();
					Game_1.floor = 1;
				}
			} else if (x >= 368 && x <= 401 && y <= 624) { //Up stair 1
				if (x >= 368 && x <= 401 && y == 534) {
					animationStair();
					Game_1.floor = 3;
					strsUp = false;
					strsDown = false;
					direccion = 4;
				} else {
					strsUp = true;
					strs = true;
				}
			} else if (x >= 113 && x <= 137 && (y <= 608 || y <= 612)) { //Up stair 2
				if (x >= 113 && x <= 137 && y <= 549) {
					animationStair();
					Game_1.floor = 3;
					strsUp = false;
					strsDown = false;
					direccion = 4;
				} else {
					strsUp = true;
					strs = true;
				}
			} else notStairs();
		}
		
		private void floor3() {
			if (x >= 368 && x <= 401 && y >= 527) { //Down Stair 1
				strs = true;
				strsDown = true;
				if (downStrs) {
					animationStairDown();
					Game_1.floor = 2;
				}
			} else if (x >= 113 && x <= 137 && y >= 539) { //Down Stair 2 x: 381 y: 527
				strs = true;
				strsDown = true;
				if (downStrs) {
					animationStairDown();
					Game_1.floor = 2;
				}
			} else if (x >= 434 && x <= 464 && (y <= 521 || y <= 524)) { //Up stair 1
				if (x >= 434 && x <= 464 && y <= 432) {
					animationStair();
					Game_1.floor = 4;
					strsUp = false;
					strsDown = false;
					direccion = 3;
				} else {
					strsUp = true;
					strs = true;
				}
			}  else if (x >= 719 && x <= 752 && y <= 507) { //Up stair 2
				if (x >= 719 && x <= 752 && y <= 445) {
					animationStair();
					Game_1.floor = 4;
					strsUp = false;
					strsDown = false;
					direccion = 3;
				} else {
					strsUp = true;
					strs = true;
				}
			} else notStairs();
		}
			
		private void floor4() {
			if (x >= 434 && x <= 464 && y >= 416) { //Down Stair 1
				strs = true;
				strsDown = true;
				if (downStrs) {
					animationStairDown();
					Game_1.floor = 3;
				}
			} else if (x >= 719 && x <= 752 && y >= 433) { //Down Stair 2
				strs = true;
				strsDown = true;
				if (downStrs) {
					animationStairDown();
					Game_1.floor = 3;
				}
			} else if (x >= 272 && x <= 305 && y <= 410) { //Up stair 1
				if (x >= 272 && x <= 305 && y <= 334) {
					animationStair();
					Game_1.floor = 5;
					strsUp = false;
					strsDown = false;
					direccion = 4;
				} else {
					strsUp = true;
					strs = true;
				}
			} else if (x >= 113 && x <= 140 && (y <= 400 || y <= 405)) { //Up stair 2
				if (x >= 113 && x <= 140 && y <= 341) {
					animationStair();
					Game_1.floor = 5;
					strsUp = false;
					strsDown = false;
					direccion = 4;
				} else {
					strsUp = true;
					strs = true;
				}
			} else notStairs();
		}
		
		private void floor5() {
			if (x >= 272 && x <= 305 && y >= 322) { //Down Stair 1
				strs = true;
				strsDown = true;
				if (downStrs) {
					animationStairDown();
					Game_1.floor = 4;
				}
			} else if (x >= 113 && x <= 140 && (y >= 330 || y >= 326)) { //Down Stair 2
				strs = true;
				strsDown = true;
				if (downStrs) {
					animationStairDown();
					Game_1.floor = 4;
				}
			} else if (x >= 722 && x <= 745 && y <= 300) { //Up stair 1
				if (x >= 722 && x <= 745 && y <= 234) {
					animationStair();
					Game_1.floor = 6;
					strsUp = false;
					strsDown = false;
					direccion = 3;
				} else {
					strsUp = true;
					strs = true;
				}
			} else notStairs();
		}
		
		private void floor6() {
			if (x >= 722 && x <= 745 && y >= 224) { //Down Stair 1x: 735 y: 224
				strs = true;
				strsDown = true;
				if (downStrs) {
					animationStairDown();
					Game_1.floor = 5;
				}
			} else if (x >= 497 && x <= 527 && y <= 225) { //Up stair 1
				if (x >= 497 && x <= 527 && y <= 146) {
					animationStair();
					Game_1.winMario = true;
					Game_1.floor = 0;
					strsUp = false;
					strsDown = false;
					direccion = 3;
				} else {
					strsUp = true;
					strs = true;
				}
			} else notStairs();
		}
		
		private void animationStairDown() {
			animationStairs = true;
			steps = 0;
			try { Thread.sleep(100); } catch (InterruptedException e) {}
			y += 5;
			steps = 2;
			try { Thread.sleep(100); } catch (InterruptedException e) {}
			y += 5;
			steps = 1;
			try { Thread.sleep(150); } catch (InterruptedException e) {}
			y += 5;
			steps = 0;
			animationStairs = false;
		}
		
		private void animationStair() {
			animationStairs = true;
			steps = 0;
			try { Thread.sleep(100); } catch (InterruptedException e) {}
			y -= 2;
			steps = 1;
			try { Thread.sleep(100); } catch (InterruptedException e) {}
			y -= 2;
			steps = 2;
			try { Thread.sleep(150); } catch (InterruptedException e) {}
			steps = 0;
			animationStairs = false;
			strs = false;
			downStrs = false;
			upStrs = false;
			
			
		}
		
		private void notStairs() {
			strsUp = false;
			strsDown = false;
			downStrs = false;
			upStrs = false;
			strs = false;
		}
		
		private void change() {
			if (!marioDeath) {
				if (direccion == 1) {
					if (jump) x -= pasos + 1;
					else x -= pasos; //Run Left
				}
				else if (direccion == 2) {
					if (jump) x += pasos + 1;
					else x += pasos; //Run Right
				}
				else if (direccion == 5) y -= 2; //Stairs Up
				else if (direccion == 6) y += 2; //Stairs Down
			}
		}
	}
	
}
