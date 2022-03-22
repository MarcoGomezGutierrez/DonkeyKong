package game.scene.level_1;

import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;

import game.character.Mario;
import lombok.Setter;

public class Points {
	@Setter private int x;
	@Setter private int y;
	private int width = 30;
	private int height = 30;
	private final ImageIcon bonus_100 = new ImageIcon("img\\points\\bonus_100.png");
	private final ImageIcon bonus_300 = new ImageIcon("img\\points\\bonus_300.png");
	private final ImageIcon bonus_800 = new ImageIcon("img\\points\\bonus_800.png");
	@Setter private boolean drawPoints = false;
	private boolean draw = true;
	private int points = 0;
	private Clip bonusSound;
	public static List<Integer> listHash = new ArrayList<Integer>();
	
	public Points() {
		try {
			bonusSound = AudioSystem.getClip();
		} catch (LineUnavailableException e) {}
	}
	
	public void paint(Graphics2D g) {
		if (drawPoints) {
			points++;
			new ImagePoints(points).start();
			drawPoints = false;
		} else if (draw){
			if (points == 1) {
				g.drawImage(bonus_100.getImage(), x, y, width, height, null);
			} else if (points == 2){
				g.drawImage(bonus_300.getImage(), x, y, width, height, null);
			} else if (points >= 3){
				g.drawImage(bonus_800.getImage(), x, y, width, height, null);
			}
		}
		
	}
	
	private class ImagePoints extends Thread{
		
		private int point = 0;
		
		public ImagePoints(int point) {
			this.point = point;
		}
		
		@Override
		public void run() {
			try { Thread.sleep(500); } catch (InterruptedException e) { }
			if (points == point) {
				try {
					if (!bonusSound.isRunning()) {
						bonusSound = AudioSystem.getClip();
						bonusSound.open(AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/audio/wav/bonusPoint.wav")));
						bonusSound.start();
					}
				} 
				catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {}
				if (points == 1) {
					Mario.setPoints(Mario.getPoints() + 100);
				} else if (points == 2) {
					Mario.setPoints(Mario.getPoints() + 300);
				} else if (points >= 3) {
					Mario.setPoints(Mario.getPoints() + 800);
				}
				draw = true;
				try { Thread.sleep(250); } catch (InterruptedException e) { }
				draw = false;
				listHash.clear();
				points = 0;
			}
		}
	}
	
}
