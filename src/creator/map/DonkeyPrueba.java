package creator.map;

import java.awt.Graphics2D;

import javax.swing.ImageIcon;

import lombok.Getter;
import lombok.Setter;
/**
 * [x: 227, y: 150, width: 190, height: 115]
 * @author marco
 *
 */
public class DonkeyPrueba {
	@Getter @Setter private int x = 85;
	@Getter @Setter private int y = 160;
	@Getter private int width = 190;
	@Getter private int height = 115;
	@Getter @Setter private int steps = 0;
	private final ImageIcon donkey_stairs_princess_1 = new ImageIcon("src\\img\\donKingKong\\donkey_climb_princess_1.png");
	private final ImageIcon donkey_stairs_princess_2 = new ImageIcon("src\\img\\donKingKong\\donkey_climb_princess_2.png");
	
	public void paint(Graphics2D g) {
		if (steps == 0) g.drawImage(donkey_stairs_princess_1.getImage(), x, y, width, height, null);
		else if (steps == 1) g.drawImage(donkey_stairs_princess_2.getImage(), x, y, width, height, null);
	}
}
