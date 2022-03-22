package creator.map;

import java.awt.Graphics2D;

import javax.swing.ImageIcon;

import lombok.Getter;
import lombok.Setter;

/**
 * Hammer-up [x: 178, y: 698, width: 38, height: 80]
 * Hammer-left-down-stop [x: 135, y: 731, width: 83, height: 47]
 * Hammer-right-down-stop [x: 186, y: 731, width: 83, height: 47]
 * @author marco
 *
 */

public class Mario_Hammer_Prueba {
	@Getter @Setter private int x = 178;//
	@Getter @Setter private int y = 698;
	@Getter @Setter private int xNormal = 180;
	@Getter @Setter private int yNormal = 730;
	@Getter private static int width = 40;
	@Getter private static int height = 50;
	@Getter @Setter private int widthHammer = 38;
	@Getter @Setter private int heightHammer = 80;
	private final ImageIcon mario = new ImageIcon("img\\mario\\rojo\\Mario_Parado_Left.png");
	@Setter private int steps = 0;
	
	public void paint(Graphics2D g) {
		g.drawImage(mario.getImage(), x, y, width, height, null);
	}
	
}
