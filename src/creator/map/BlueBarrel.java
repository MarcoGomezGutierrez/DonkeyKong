package creator.map;

import java.awt.Graphics2D;

import javax.swing.ImageIcon;

import lombok.Getter;
import lombok.Setter;

public class BlueBarrel {
	
	@Getter @Setter private int x = 417;
	@Getter @Setter private int y = 355;
	@Getter @Setter private int width = 69;
	@Getter @Setter private int height = 103;
	private final ImageIcon blueBarrel = new ImageIcon("img\\barrel_flame\\barrel_flame_fire_1.png");
	
	public void paint(Graphics2D g) {
		g.drawImage(blueBarrel.getImage(), x, y, width, height, null);
	}
	
}
