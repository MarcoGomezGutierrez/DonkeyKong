package game.character;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import game.scene.animation.level_1.Level_1;
import game.scene.level_1.BlueBarrel;
import game.scene.level_1.DonkeyBarrel;
import game.scene.level_1.Floor;
import game.scene.level_1.Game_1;
import game.scene.level_1.Points;
import game.scene.level_1.PointsAnimation;
import lombok.Getter;
import lombok.Setter;

public class DonkeyKong {
	
	@Getter @Setter private int x;
	@Getter @Setter  private int y;
	@Setter private int width;
	@Setter private int height;
	private int steps;
	@Setter private static boolean gameEnd;
	private final ImageIcon donkey = new ImageIcon("img\\donKingKong\\DonKingKong.png");
	private final ImageIcon donkey_barrel = new ImageIcon("img\\donKingKong\\DonKingKong_Barril.png");
	private final ImageIcon donkey_barrel_blue = new ImageIcon("img\\donKingKong\\DonKingKong_Barril_Blue.png");
	private final ImageIcon donkey_catch = new ImageIcon("img\\donKingKong\\DonKingKong_Recoge.png");
	private final ImageIcon donkey_throw = new ImageIcon("img\\donKingKong\\DonKingKong_Lanza.png");
	private final ImageIcon donkey_stairs_1 = new ImageIcon("img\\donKingKong\\donkey_climb_1.png");
	private final ImageIcon donkey_stairs_2 = new ImageIcon("img\\donKingKong\\donkey_climb_2.png");
	private final ImageIcon donkey_stairs_princess_1 = new ImageIcon("img\\donKingKong\\donkey_climb_princess_1.png");
	private final ImageIcon donkey_stairs_princess_2 = new ImageIcon("img\\donKingKong\\donkey_climb_princess_2.png");
	private List<DonkeyBarrel> listBarrels = new ArrayList<DonkeyBarrel>();
	private List<Floor> listFloor1;
	private List<Floor> listFloor2;
	private List<Floor> listFloor3;
	private List<Floor> listFloor4;
	private List<Floor> listFloor5;
	private List<Floor> listFloor6;
	public static BlueBarrel blueBarrel;
	@Getter private static int numberOfFlames;
	private boolean firstBarrel;
	@Setter private static boolean newFlame = false;
	private PointsAnimation pointsAnimation = new PointsAnimation();
	private boolean animationMarioWinLevel1 = false;
	@Getter private static boolean game_1_finish = false; //Change map, level_1 finish //TODO Poner a false para que funcione el juego bien
	public static Points points = new Points();
	
	public DonkeyKong() {
		x = 85;
		y = 160;
		width = 170;
		height = 105;
		steps = 1;
		gameEnd = false;
		listBarrels = new ArrayList<DonkeyBarrel>();
		listFloor1 = new ArrayList<Floor>();
		listFloor2 = new ArrayList<Floor>();
		listFloor3 = new ArrayList<Floor>();
		listFloor4 = new ArrayList<Floor>();
		listFloor5 = new ArrayList<Floor>();
		listFloor6 = new ArrayList<Floor>();
		numberOfFlames = 0;
		game_1_finish = false;
		firstBarrel = true;
		newFlame = false;
		buildFloor1();
		buildFloor2();
		buildFloor3();
		buildFloor4();
		buildFloor5();
		buildFloor6();
		new SynchronizedActionDonkey().start();
		new SynchronizedBarrel().start();
	}
	
	/**
	 * Imagenes de DonkeyKong
	 * @param g
	 */
	public synchronized void paint(Graphics2D g) {
		if (animationMarioWinLevel1) {// Animation Mario win level_1 [x: 165, y: -13, width: 276, height: 115] Posicion de inicio
			if (steps == 0) {
				g.drawImage(donkey_stairs_1.getImage(), x, y, width, height, null);
			} else if (steps == 1) {
				g.drawImage(donkey_stairs_2.getImage(), x, y, width, height, null);
			} else if (steps == 2) {
				g.drawImage(donkey_stairs_princess_1.getImage(), x, y, width, height, null);
			} else if (steps == 3) {
				g.drawImage(donkey_stairs_princess_2.getImage(), x, y, width, height, null);
			}
			g.setColor(Color.BLACK);
			g.fillRect(165, -13, 276, 115);
		} else {
			if (steps == 1) g.drawImage(donkey.getImage(), x, y, width, height, null);
			else if (steps == 2) g.drawImage(donkey_catch.getImage(), x, y, width, height, null);
			else if (steps == 3) g.drawImage(donkey_barrel.getImage(), x, y, width, height, null);
			else if (steps == 4) g.drawImage(donkey_throw.getImage(), x, y, width, height, null);
			else if (steps == 5) g.drawImage(donkey_barrel_blue.getImage(), x, y, width, height, null);
			for (int i = 0; i < listBarrels.size(); i++) {
				listBarrels.get(i).paint(g);
			}
			if (!firstBarrel) {
				if (BlueBarrel.isAlive()) { //Colision del primer barril con Mario
					getCollisionWithMario();
					blueBarrel.paint(g);
				} else if (numberOfFlames == 0) {
					numberOfFlames = 1;
				} else if (newFlame) {
					numberOfFlames++;
					newFlame = false;
				}
			} 
			pointsAnimation.paint(g);
		}
	}
	
	/**
	 * Collision first barrel with Mario
	 */
	private void getCollisionWithMario() {
		if (Mario.getBounds().intersects(blueBarrel.getBounds())) {
			firstBarrel = true;
			numberOfFlames = 0;
			gameEnd = true; 
		}
	}
	
	/**
	 * Spanish: Sincronización de las acciones de DonkeyKong
	 * 			lanzar barriles normales
	 * English: Synchronized action barrel of DonkeyKong
	 * @author marco
	 *
	 */
	private class SynchronizedActionDonkey extends Thread {
		
		private Random rand = new Random();
		private boolean barrelSpecial = false;
		private int numberOfBarrels = 1;
		private int numberStartBarrelSpecial = rand.nextInt((8 - 7) + 1) + 7;//Número de barriles hasta el especial
		
		@Override
		public void run() {
			while (!Level_1.isStartGame()) {
				try { Thread.sleep(1); } catch (InterruptedException e) {}
				if (Level_1.isStartGame()) break;
			}
			while(true) {
				if (gameEnd) {
					break;
				}
				/**
				 * x: 524 y: 628 // x: 792 y: 642 
				 * x: 57 y: 543 // x: 225 y: 533
				 * x: 57 y: 336 // x: 246 y: 324
				 */
				try { Thread.sleep(1); } catch (InterruptedException e1) {}
				if (!PointsAnimation.isAnimationPoints()) { //Si esta activo los puntos que no se mueva
					if (!Game_1.winMario) { //Change Random throw barrel, and special barrel
						if (!firstBarrel) {
							if (steps == 1) { // ----> Donkey Normal
								try { Thread.sleep(300);//Thread.sleep(rand.nextInt((2000 - 200) + 1) + 200); 
									} catch (InterruptedException e) {}
								steps = 2;
							} else if (steps == 2) { //----> Donkey Catch
								try { Thread.sleep(500); } catch (InterruptedException e) {}
								if (barrelSpecial) {
									steps = 5;
								} else steps = 3;
							} else if (steps == 3 || steps == 5) {  //----> Donkey with Barrel normal or blue
								try { Thread.sleep(500); } catch (InterruptedException e) {}
								steps = 4;
							} else if (steps == 4) { //----> Donkey throw barrel
								if (!barrelSpecial) {
									listBarrels.add(new DonkeyBarrel(false));//Barril Normal
								} else {
									listBarrels.add(new DonkeyBarrel(true));//Barril Special
									barrelSpecial = false;
								}
								numberOfBarrels++;
								if (numberOfBarrels >= numberStartBarrelSpecial) { //Numero de barriles hasta el especial
									barrelSpecial = true;
									numberOfBarrels = 1;
									numberStartBarrelSpecial = rand.nextInt((9 - 7) + 1) + 7;
								}
								try { Thread.sleep(500); } catch (InterruptedException e) {}
								steps = 1;
							}
						} else {
							try { Thread.sleep(500); } catch (InterruptedException e) {}
							firstBarrel();
						}
					} else {
						animationMarioWinLevel1();
						game_1_finish = true;
						break;
					}
				}
			}
		}
		
		private void animationMarioWinLevel1() {//Animation Mario Win [x: 238, y: 7, width: 190, height: 115]
			try {
				Clip clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/audio/wav/mario_win.wav")));
				clip.start();
			}
			catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {}
			Thread thread = new Thread() {
				@Override
				public void run() {
					while (y >= -10) {
						try { Thread.sleep(100); } catch (InterruptedException e) { }
						y -= 10;//Mover a DonkeyKong
					}
				}
			};
			try { Thread.sleep(1000); } catch (InterruptedException e1) {}
			steps = 0;
			animationMarioWinLevel1 = true;
			x = 227;
			y = 150;
			width = 190;
			height = 115;
			try {
				Clip clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/audio/wav/donkey_enfadado.wav")));
				clip.start();
			}
		catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {}
			thread.start();
			while (y >= -10) { //Cambio de imagen hasta subir las escaleras
				try { Thread.sleep(170); } catch (InterruptedException e) { }
				if (steps == 0) {
					steps++;
				} else if (steps == 1) {
					Peach.setPeachVisible(false);//DonkeyKong coge a peach
					steps++;
				} else if (steps == 2) {
					steps = 3;
				} else if (steps == 3) {
					steps = 2;
				}
			}
		}

		private void firstBarrel() {
			if (steps == 1) steps++;//Animacion de coger barril
			else if (steps == 2) steps = 5;
			else if (steps == 5) {
				blueBarrel = new BlueBarrel();//Lanzar el barril, ya esta en el juego
				firstBarrel = false;
				steps = 1;
			}
		}
	}
	
	private class SynchronizedBarrel extends Thread {
		
		private int xB;
		private int xFi;
		private int xFf;
		private boolean deleteBarrel = false;
		private int index;
		
		@Override
		public synchronized void run() {
			while (!Level_1.isStartGame()) {
				try { Thread.sleep(1); } catch (InterruptedException e) {}
				if (Level_1.isStartGame()) break;
			}
			while (true) {
				try { Thread.sleep(1); } catch (InterruptedException e) {}
				if (!PointsAnimation.isAnimationPoints()) {
					for (DonkeyBarrel barrel : listBarrels) {
						if (barrel.getX() < -100 || barrel.getX() > 1000) {
							listBarrels.remove(barrel);
							break;
						}
						if (Mario.getBounds().intersects(barrel.getBounds())) { //Collision gameEnd
							if (Mario.isMarioHammer()) { //Mario collision hammer
								if (barrel.isSpecialBarrel()) {
									PointsAnimation.setSpecial(true);
								}
								PointsAnimation.setX(barrel.getX());
								PointsAnimation.setY(barrel.getY());
								PointsAnimation.setAnimationPoints(true);
								
								listBarrels.remove(barrel);
								break;
							}
							else {
								gameEnd = true;
								break;
							}
						} else if (Mario.getBounds().intersects(barrel.getBoundsMarioJump())) {
							if (!Points.listHash.contains(barrel.hashCode())) {
								Points.listHash.add(barrel.hashCode());
								points.setX(barrel.getX());
								points.setY(barrel.getY());
								points.setDrawPoints(true);
							}
						}
						if (!barrel.isDown()) {
							if (barrel.getFloor() == 6) {
								for (Floor floor : listFloor6) {
									xB = barrel.getX();
									xFi = floor.getX();
									xFf = xFi + floor.getWidth();
									if (xFi <= xB && xB >= xFf) {
										barrel.setY(floor.getY() - 35);
										break;
									}
									
								}
							}
							else if (barrel.getFloor() == 5) {
								for (Floor floor : listFloor5) {
									xB = barrel.getX();
									xFi = floor.getX();
									xFf = xFi + floor.getWidth();
									if (xFi >= xB && xB <= xFf) {
										barrel.setY(floor.getY() - 35);
										break;
									}
								}
							}
							else if (barrel.getFloor() == 4) {
								for (Floor floor : listFloor4) {
									xB = barrel.getX();
									xFi = floor.getX();
									xFf = xFi + floor.getWidth();
									if (xFi <= xB && xB >= xFf) {
										barrel.setY(floor.getY() - 35);
										break;
									}
								}
							}
							else if (barrel.getFloor() == 3) {
								for (Floor floor : listFloor3) {
									xB = barrel.getX();
									xFi = floor.getX();
									xFf = xFi + floor.getWidth();
									if (xFi >= xB && xB <= xFf) {
										barrel.setY(floor.getY() - 35);
										break;
									}
								}
							}
							else if (barrel.getFloor() == 2) {
								for (Floor floor : listFloor2) {
									xB = barrel.getX();
									xFi = floor.getX();
									xFf = xFi + floor.getWidth();
									if (xFi <= xB && xB >= xFf) {
										barrel.setY(floor.getY() - 35);
										break;
									}
								}
							}
							else if (barrel.getFloor() == 1) {
								for (Floor floor : listFloor1) {
									xB = barrel.getX();
									xFi = floor.getX();
									xFf = xFi + floor.getWidth();
									if (xFi >= xB && xB <= xFf) {
										barrel.setY(floor.getY() - 35);
										break;
									}
								}
							}
							if (barrel.isEnd()) {
								index = listBarrels.indexOf(barrel);
								deleteBarrel = true;
							}
						}
					}
					if (deleteBarrel) {
						deleteBarrel = false;
						listBarrels.remove(index);
					}
					if (Game_1.winMario) {
						listBarrels.clear();
						break;
					}else if (gameEnd) { //Reset DonkeyKong when Mario death
						Mario.marioDeath = true;
						listBarrels.clear();
						break;
					}
				}
			}
		}
		
	}
	
	private void buildFloor1() {
		listFloor1.addAll(Arrays.asList(
				new Floor(455, 776), // 1
				new Floor(517, 772), // 2
				new Floor(581, 768), // 3
				new Floor(645, 765), // 4
				new Floor(710, 761), // 5
				new Floor(778, 759), // 6
				new Floor(841, 757)  // 7
		));
	}
	
	private void buildFloor2() {
		listFloor2.addAll(Arrays.asList(
				new Floor(774, 690), // 8
				new Floor(708, 687), // 9
				new Floor(645, 685), // 10
				new Floor(580, 681), // 11
				new Floor(517, 679), // 12
				new Floor(453, 673), // 13
				new Floor(388, 671), // 14
				new Floor(321, 669), // 15
				new Floor(258, 665), // 16
				new Floor(194, 663), // 17
				new Floor(129, 659), // 18
				new Floor(65, 657), // 19
				new Floor(-1, 653) // 20
		));
	}
	
	private void buildFloor3() {
		listFloor3.addAll(Arrays.asList(
				new Floor(65, 588), // 8
				new Floor(131, 584), // 9
				new Floor(195, 580), // 10
				new Floor(258, 578), // 11
				new Floor(322, 574), // 12
				new Floor(389, 572), // 13
				new Floor(453, 569), // 14
				new Floor(518, 566), // 15
				new Floor(582, 564), // 16
				new Floor(646, 558), // 17
				new Floor(709, 554), // 18
				new Floor(774, 552), // 19
				new Floor(840, 550) // 20
		));
	}
	
	private void buildFloor4() {
		listFloor4.addAll(Arrays.asList(
				new Floor(775, 484), // 34
				new Floor(709, 481), // 35
				new Floor(646, 478), // 36
				new Floor(580, 475), // 37
				new Floor(517, 473), // 38
				new Floor(454, 467), // 39
				new Floor(389, 463), // 40
				new Floor(321, 461), // 41
				new Floor(259, 458), // 42
				new Floor(194, 455), // 43
				new Floor(131, 453), // 44
				new Floor(65, 450),  // 45
				new Floor(0, 445) 	 // 46
		));
	}
	
	private void buildFloor5() {
		listFloor5.addAll(Arrays.asList(
				new Floor(64, 381),  // 47
				new Floor(130, 375), // 48
				new Floor(196, 371), // 49
				new Floor(258, 369), // 50
				new Floor(322, 367), // 51
				new Floor(390, 365), // 52
				new Floor(454, 361), // 53
				new Floor(516, 359), // 54
				new Floor(582, 357), // 55
				new Floor(646, 351), // 56
				new Floor(710, 347), // 57
				new Floor(774, 345), // 58
				new Floor(840, 343)  // 59
		));
	}
	
	private void buildFloor6() {
		listFloor6.addAll(Arrays.asList(
				new Floor(774, 275), // 60
				new Floor(708, 273), // 61
				new Floor(644, 269), // 62
				new Floor(581, 267), // 63
				new Floor(518, 265) // 64
		));
	}

	
	
}
