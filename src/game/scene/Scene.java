package game.scene;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JPanel;
import game.character.DonkeyKong;
import game.character.Mario;
import game.scene.animation.level_1.Level_1;
import game.scene.level_1.BarrelFlame;
import game.scene.level_1.Game_1;
import game.scene.level_2.Game_2;
import lombok.Getter;
import main.Program;

@SuppressWarnings("unused")
public class Scene extends JPanel {

	private static final long serialVersionUID = 2278588087180717438L;
	public static Mario mario;
	private final Level_1 level_1; //Animation
	private final Game_1 game_1; //Start Game
//	private final Game_2 game_2; //Start Game
	public static boolean gameOver = false;
	public static List<Lives> listLives = new ArrayList<Lives>();
	private int xLife = 50;
	private int yLife = 60;
	
	public Scene(int width, int height) {
		mario = new Mario(4);
		level_1 = new Level_1();
		game_1 = new Game_1();
//		game_2 = new Game_2();
		listLives.addAll(Arrays.asList(new Lives(xLife, yLife), new Lives(xLife + 30, yLife), new Lives(xLife + 60, yLife)));
		this.addKeyListener( new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (!DonkeyKong.isGame_1_finish())
					if (!Game_1.winMario && !Mario.marioDeath && Level_1.isStartGame()) 
						mario.keyPressed(e);
			//	else 
					
			}
			@Override
			public void keyReleased(KeyEvent e) {
				if (!DonkeyKong.isGame_1_finish())
					if (!Game_1.winMario && !Mario.marioDeath && Level_1.isStartGame()) 
						mario.keyReleased(e);
//				else 
					
			}
			
		});
		
		this.setFocusable(true);
		this.setSize(width, height);
		this.setBackground(Color.BLACK);
		this.setVisible(true);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		if (!gameOver) {
			 if (Level_1.isStartGame()) { //TODO Para que funcione tiene que estar en False en la clase Level_1
				if (!DonkeyKong.isGame_1_finish()) printLevel_1(g2d); //TODO Ponerlo a false clase DonkeyKong cuando juego terminado
				//else 
				//	game_2.print(g2d);
			 } else {
				level_1.printAnimation(g2d); //Animation game
			 }
		} else {
			g2d.setBackground(Color.BLACK);
			g2d.setColor(Color.WHITE);
			g2d.setFont(new Font("Impact", Font.ITALIC, 100));//Buxton Sketch//Frank Ruehl CLM//Impact//MV Boli//Monotype Corsiva
			g2d.drawString("GAME OVER", Program.WIDTH / 2 - 250, Program.HEIGHT / 2);
		}
		this.repaint();
	}

	private void printLevel_1(Graphics2D g2d) {
		game_1.print(g2d); //Start Game
		if (!Mario.resetMario) {
			if (!Mario.marioDeath) {
				for (Lives life : listLives) {
					life.paint(g2d);
				}
			}
			mario.paint(g2d); //Animation movement and collision Mario 
			DonkeyKong.points.paint(g2d);
		}
	}
	
}
