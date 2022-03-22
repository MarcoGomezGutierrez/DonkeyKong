package main;

import java.awt.EventQueue;
import javax.swing.JFrame;

import creator.map.CreatorMap;
import game.scene.Scene;

/**
 * Clase Main
 * @author marco
 *
 */
@SuppressWarnings("unused")
public class Program extends JFrame {
	
	private static final long serialVersionUID = 6220972751175106372L;
	private JFrame fr;
	private Scene scene;
	private CreatorMap creat;
	public static final int WIDTH = 900;
	public static final int HEIGHT = 850;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Program frame = new Program();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Program() {
		fr = new JFrame();
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fr.getContentPane().setLayout(null);
		fr.setResizable(false);
		fr.setSize(WIDTH, HEIGHT);
		fr.setLocationRelativeTo(null);
		fr.setVisible(true);
		scene = new Scene(WIDTH, HEIGHT);
		fr.getContentPane().add(scene);
//		creat = new CreatorMap(WIDTH, HEIGHT);
//		fr.getContentPane().add(creat);
	}
}
