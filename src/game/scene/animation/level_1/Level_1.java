package game.scene.animation.level_1;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import lombok.Getter;
import main.Program;

//Animacion Level_1
public class Level_1 {
	
	private final int x = 0;
	private final int y = 0;
	private final int width = Program.WIDTH;
	private final int height = Program.HEIGHT - 50;
	private int steps = 1;
	@Getter private static boolean startGame = false; //TODO ponerlo a false para que donkey no empieze
	private final ImageIcon part_1 = new ImageIcon("img\\background\\level_1\\part_1.png");
	private final ImageIcon part_2 = new ImageIcon("img\\background\\level_1\\part_2.png");
	private final ImageIcon part_3 = new ImageIcon("img\\background\\level_1\\part_3.png");
	private final ImageIcon part_4 = new ImageIcon("img\\background\\level_1\\part_4.png");
	private final ImageIcon part_5 = new ImageIcon("img\\background\\level_1\\part_5.png");
	private final ImageIcon part_6 = new ImageIcon("img\\background\\level_1\\part_6.png");
	private final ImageIcon part_7 = new ImageIcon("img\\background\\level_1\\part_7.png");
	private final ImageIcon scene_1 = new ImageIcon("img\\background\\scene_1.png");

	private final DonkeyKongAnimation donkey;
	private Clip clip;
	
	public Level_1() {
		try {
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/audio/wav/animation_level_1.wav")));
			clip.start();
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {}
		donkey = new DonkeyKongAnimation();
		new Synchronized().start();
	}
	
	/**
	 * Animation start game
	 * @param g
	 */
	public void printAnimation(Graphics2D g) {
		if (steps == 1) g.drawImage(part_1.getImage(), x, y, width, height, null);
		else if (steps == 2) g.drawImage(part_2.getImage(), x, y, width, height, null);
		else if (steps == 3) g.drawImage(part_3.getImage(), x, y, width, height, null);
		else if (steps == 4) g.drawImage(part_4.getImage(), x, y, width, height, null);
		else if (steps == 5) g.drawImage(part_5.getImage(), x, y, width, height, null);
		else if (steps == 6) g.drawImage(part_6.getImage(), x, y, width, height, null);
		else if (steps == 7) g.drawImage(part_7.getImage(), x, y, width, height, null);
		else if (steps == 8) {
			g.drawImage(scene_1.getImage(), x, y, width, height, null);
		}
		if (steps != 8 && steps != 9) donkey.printAnimation(g);
	}
	
	private class Synchronized extends Thread {
		
		private boolean firstJump = true;
		private Clip clip;
		private boolean sound = true;
		
		@Override
		public void run() {
			while(true) {
				try { Thread.sleep(1); } catch (InterruptedException e1) {}
				if (donkey.isDonkeyUp()) {
					if (steps != 8) { 
						if (firstJump) {
							firstJump = false;
							try { Thread.sleep(300); } catch (InterruptedException e) {}
						} else {
							try { Thread.sleep(550); } catch (InterruptedException e) {}
						}
					}
					if (steps == 1) steps++;
					else if (steps == 2) steps++;
					else if (steps == 3) steps++;
					else if (steps == 4) steps++;
					else if (steps == 5) steps++;
					else if (steps == 6) steps++;
					else if (steps == 7) {
						try { Thread.sleep(3000); } catch (InterruptedException e) {}
						steps++;
					}
					else if (steps == 8) {
						if (sound) soundDeath();
						try { Thread.sleep(2000); } catch (InterruptedException e) {}
						steps++;
					}
					else if (steps == 9) {
						startGame = true;
						break;
					}
				}
			}
		}

		private void soundDeath() {
			try {
				clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/audio/wav/howhigh.wav")));
				clip.start();
			} 
			catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {}
			finally {
				sound = false;
			}
		}
	}
	
}
