package creator.map;

import java.awt.Graphics2D;

import javax.swing.ImageIcon;

import lombok.Getter;
import lombok.Setter;

public class Cinta {
	
	@Getter @Setter private int x = 500;
	@Getter @Setter private int y = 500;
	private int width = 30;
	private int height = 30;
	private final ImageIcon cinta_left_1 = new ImageIcon("img\\cinta\\cinta_left_1.png");
	private final ImageIcon cinta_right_1 = new ImageIcon("img\\cinta\\cinta_right_1.png");
	
	public void paint(Graphics2D g) {
		g.drawImage(cinta_right_1.getImage(), x, y, width, height, null);
	}
	
}
