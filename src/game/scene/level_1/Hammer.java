package game.scene.level_1;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import lombok.Getter;
import lombok.Setter;

/**
 * Dos hammers:
 * [x: 71, y: 308, width: 32, height: 31]
 * [x: 681, y: 599, width: 32, height: 31]
 * @author marco
 *
 */
public class Hammer {
	
	@Getter @Setter private int x;
	@Getter @Setter private int y;
	@Getter @Setter private int width;
	@Getter @Setter private int height;
	private final ImageIcon hammer = new ImageIcon("img\\hammer\\hammer_red_up.png");
	@Getter @Setter private boolean visible = true;
	
	
	public Hammer(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 32;
		this.height = 31;
	}
	
	public void paint(Graphics2D g) {
		if (visible) g.drawImage(hammer.getImage(), x, y, width, height, null);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
	
}
