package game.scene.level_1;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;

import game.character.Mario;
import lombok.Getter;
import lombok.Setter;

public class PointsAnimation {
	
	@Setter private static int x = 0;
	@Setter private static int y = 0;
	private final int width = 30;
	private final int height = 30;
	@Getter @Setter private static boolean animationPoints = false;
	@Setter private static boolean special = false;
	private int steps = 0;
	private final ImageIcon hammer_hit_1 = new ImageIcon("img\\points\\hammer_hit_1.png");
	private final ImageIcon hammer_hit_2 = new ImageIcon("img\\points\\hammer_hit_2.png");
	private final ImageIcon hammer_hit_3 = new ImageIcon("img\\points\\hammer_hit_3.png");
	private final ImageIcon hammer_hit_4 = new ImageIcon("img\\points\\hammer_hit_4.png");
	private final ImageIcon bonus_300 = new ImageIcon("img\\points\\bonus_300.png");
	private final ImageIcon bonus_500 = new ImageIcon("img\\points\\bonus_500.png");
	private Clip hammerPoint;
	private Clip bonusSound;
	
	public PointsAnimation() {
		try { 
			hammerPoint = AudioSystem.getClip(); 
			bonusSound = AudioSystem.getClip();
		} catch (LineUnavailableException e) {}
		new ThreadAnimation().start();
	}
	
	public void paint(Graphics2D g) {
		if (animationPoints) {//Si hay animacion pintarlo
			audio();
			if (steps == 0) g.drawImage(hammer_hit_1.getImage(), x, y, width, height, null);
			else if (steps == 1) g.drawImage(hammer_hit_2.getImage(), x, y, width, height, null);
			else if (steps == 2) g.drawImage(hammer_hit_3.getImage(), x, y, width, height, null);
			else if (steps == 3) g.drawImage(hammer_hit_4.getImage(), x, y, width, height, null);
			else if (steps == 4) {
				if (special) g.drawImage(bonus_500.getImage(), x, y, width, height, null);
				else g.drawImage(bonus_300.getImage(), x, y, width, height, null);
			}
		}
	}
	
	private void audio() {
		try {
			if (!hammerPoint.isRunning()) {
				hammerPoint = AudioSystem.getClip();
				hammerPoint.open(AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/audio/wav/hammer_hit.wav")));
				hammerPoint.start();
			}
		} 
		catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {}
	}
	
	private class ThreadAnimation extends Thread {
		
		@Override
		public void run() {
			while (true) {
				try { Thread.sleep(1); } catch (InterruptedException e) {}
				if (Game_1.winMario) break;
				if (animationPoints) {
					try { Thread.sleep(250); } catch (InterruptedException e) {}
					if (steps == 0) {
						steps++;
					} else if (steps == 1) {
						steps++;
					} else if (steps == 2) {
						steps++;
					} else if (steps == 3) {
						steps++;
					} else if (steps == 4) {
						steps = 0;
						try {
							bonusSound = AudioSystem.getClip();
							bonusSound.open(AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/audio/wav/bonusPoint.wav")));
							bonusSound.start();
						} 
						catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {}
						if (special) {
							Mario.setPoints(Mario.getPoints() + 500);
							special = false;
						} else {
							Mario.setPoints(Mario.getPoints() + 300);
						}
						animationPoints = false;
					}
				}
			}
		}
		
	}
}
