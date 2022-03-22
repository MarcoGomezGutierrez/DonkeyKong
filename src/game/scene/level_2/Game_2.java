package game.scene.level_2;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;

import game.character.DonkeyKong;
import main.Program;

public class Game_2 {
	
	private final int x = 0;
	private final int y = 0;
	private final int width = Program.WIDTH;
	private final int height = Program.HEIGHT - 50;
	private final ImageIcon scene_2 = new ImageIcon("img\\background\\scene_2.png");
	private final ImageIcon level_2 = new ImageIcon("img\\background\\level_2\\level_2.png");
	public static boolean startGame_2 = false;
	private Clip howhighSound;
	//Fona 1
	private final Cintas cinta_1 = new Cintas(5, 652, 853, 652, 0);
	private final Cintas cinta_2 = new Cintas(487, 403, 386, 403, 1);
	private BarrelFlame barrel = new BarrelFlame();
	//Fona 2
//	private final Cintas cinta_3 = new Cintas(50, 50, false);
	
	
	public Game_2() {
		try { 
			howhighSound = AudioSystem.getClip();
		} catch (LineUnavailableException e) {}
		new Scene_2Sound().start();
	}
	
	public void print(Graphics2D g) {
		if (DonkeyKong.isGame_1_finish() && !startGame_2) {
			g.drawImage(scene_2.getImage(), x, y, width, height, null);
		} else {
			g.drawImage(level_2.getImage(), x, y, width, height, null);
			cinta_1.paint(g);
			cinta_2.paint(g);
			barrel.paint(g);
		}
	}
	
	private class Scene_2Sound extends Thread {
		
		@Override
		public void run() {
			while (!DonkeyKong.isGame_1_finish());
			try {
				howhighSound = AudioSystem.getClip();
				howhighSound.open(AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/audio/wav/howhigh.wav")));
				howhighSound.start();
			} 
			catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {}
			try { Thread.sleep(2000); } catch (InterruptedException e) {}
			startGame_2 = true;
		}
		
	}
	
}
