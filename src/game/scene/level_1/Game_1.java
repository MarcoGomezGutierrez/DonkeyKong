package game.scene.level_1;

import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import game.character.DonkeyKong;
import game.character.Mario;
import game.character.Peach;
import game.scene.Scene;
import game.scene.animation.level_1.Level_1;
import lombok.Getter;
import main.Program;

public class Game_1 {
	
	private final int x = 0;
	private final int y = 0;
	private final int width = Program.WIDTH;
	private final int height = Program.HEIGHT - 50;
	private int xBarr = 0;
	private int yBarr = 168;
	private final int widthBarr = 75;
	private final int heightBarr = 97;
	private final ImageIcon part_8 = new ImageIcon("img\\background\\level_1\\part_8.png");
	private final ImageIcon barrel_donkey = new ImageIcon("img\\background\\barrel_donkey.png");
	private final ImageIcon scene_1 = new ImageIcon("img\\background\\scene_1.png");
	private DonkeyKong donkeyKong;
	private BarrelFlame barrelFlame;
	private final Peach peach = new Peach();
	private List<Floor> listFloor1 = new ArrayList<Floor>();
	private List<Floor> listFloor2 = new ArrayList<Floor>();
	private List<Floor> listFloor3 = new ArrayList<Floor>();
	private List<Floor> listFloor4 = new ArrayList<Floor>();
	private List<Floor> listFloor5 = new ArrayList<Floor>();
	private List<Floor> listFloor6 = new ArrayList<Floor>();
	public static int floor = 1;
	public static boolean winMario = false;
	public static boolean reset = false;
	private Clip music_level1;
	private Clip music_scene1;
	private boolean soundHowHigh = true; //Reproducir una vez HowHigh
	@Getter private static int timer = 0;
	
	public Game_1() {
		buildFloor1();
		buildFloor2();
		buildFloor3();
		buildFloor4();
		buildFloor5();
		buildFloor6();
		barrelFlame = new BarrelFlame();
		try {
			music_level1 = AudioSystem.getClip(); 
			music_scene1 = AudioSystem.getClip(); 
		} catch (LineUnavailableException e) {}
		donkeyKong = new DonkeyKong();
		new Timer().start();
		new FloorMario().start();
	}
	
	public void print(Graphics2D g) {
		if (!Mario.resetMario) {
			drawStaticObjects(g);
			donkeyKong.paint(g);
			barrelFlame.paint(g);
			peach.paint(g);
			soundMusicLevel_1();
		} else {
			music_level1.stop();
			g.drawImage(scene_1.getImage(), x, y, width, height, null);
			timer = 0;
			if (soundHowHigh) soundHowHigh();
		}
	}
	
	private void soundHowHigh() {
		try {
			if (!music_scene1.isRunning()) {
				music_scene1 = AudioSystem.getClip();
				music_scene1.open(AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/audio/wav/howhigh.wav")));
				music_scene1.start();
				soundHowHigh = false;
			}
			
		} 
		catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {}
	}
	
	private void soundMusicLevel_1() {
		try {
			if (!music_level1.isRunning() && !Mario.marioDeath && !winMario) {
				music_level1 = AudioSystem.getClip();
				music_level1.open(AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/audio/wav/music_level_1.wav")));
				music_level1.start();
			} 
			if (winMario) {
				music_level1.stop();
			}
			
		} 
		catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {}
	}
	
	private void drawStaticObjects(Graphics2D g) {
			g.drawImage(part_8.getImage(), x, y, width, height, null);
			g.drawImage(barrel_donkey.getImage(), xBarr, yBarr, widthBarr, heightBarr, null);
		
	}
	
	private class FloorMario extends Thread {
		
		private int xM;
		private int xFi;
		private int xFf;
		@Override
		public synchronized void run() {
			while (true) {
				try { Thread.sleep(1); } catch (InterruptedException e) {}
				if (Mario.marioDeath) {
					reset = false;
					try { Thread.sleep(1); } catch (InterruptedException e) {}
					while (!Mario.resetMario) {
						try { Thread.sleep(1); } catch (InterruptedException e) {}
						if (Mario.resetMario) {
							try { Thread.sleep(3000); } catch (InterruptedException e) {}
							Mario.resetMario = false;
							reset = true;
							donkeyKong = new DonkeyKong();
							barrelFlame = new BarrelFlame();
							soundHowHigh = true;
							break;
						}
					}
				}
				if (!Scene.mario.isJump() && !Scene.mario.isStrs() && !Scene.mario.isMarioDrop()) { //If Mario is not Jump and Up or Down Stairs
					if (floor == 1) {
						for (Floor floor : listFloor1) {
							xM = Mario.getX();
							xFi = floor.getX();
							xFf = xFi + floor.getWidth();
							if (xFi >= xM && xM <= xFf) {
								Mario.setY(floor.getY() - 45);
								break;
							}
						}
					} else if (floor == 2) {
						for (Floor floor : listFloor2) {
							xM = Mario.getX();
							xFi = floor.getX();
							xFf = xFi + floor.getWidth();
							if (xFi <= xM && xM >= xFf) {
								Mario.setY(floor.getY() - 45);
								break;
							}
						}
					} else if (floor == 3) {
						for (Floor floor : listFloor3) { 
							xM = Mario.getX();
							xFi = floor.getX();
							xFf = xFi + floor.getWidth();
							if (xFi >= xM && xM <= xFf) {
								Mario.setY(floor.getY() - 45);
								break;
							}
						}
					} else if (floor == 4) {
						for (Floor floor : listFloor4) {
							xM = Mario.getX();
							xFi = floor.getX();
							xFf = xFi + floor.getWidth();
							if (xFi <= xM && xM >= xFf) {
								Mario.setY(floor.getY() - 45);
								break;
							}
						}
					} else if (floor == 5) {
						for (Floor floor : listFloor5) {
							xM = Mario.getX();
							xFi = floor.getX();
							xFf = xFi + floor.getWidth();
							if (xFi >= xM && xM <= xFf) {
								Mario.setY(floor.getY() - 45);
								break;
							}
						}
					} else if (floor == 6) {
						for (Floor floor : listFloor6) {
							xM = Mario.getX();
							xFi = floor.getX();
							xFf = xFi + floor.getWidth();
							if (xFi <= xM && xM >= xFf) {
								Mario.setY(floor.getY() - 45);
								break;
							} else if (listFloor6.indexOf(floor) == 4) {
								Mario.setY(floor.getY() - 45);
								break;
							}
						}
					} else if (winMario) {
						Mario.setX(510);
						Mario.setY(130);
						break;
					}
				}
			}
		}
	}
	
	private class Timer extends Thread {
		
		@Override
		public synchronized void run() {
			while (true) {
				try { Thread.sleep(1); } catch (InterruptedException e) {}
				if (Game_1.winMario) break;
				if (Level_1.isStartGame()) {
					try { Thread.sleep(999); } catch (InterruptedException e) {}
					timer++;
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
//				new Floor(518, 265) // 64
				new Floor(353, 266, 227, 22) //65
		));
	}

}
