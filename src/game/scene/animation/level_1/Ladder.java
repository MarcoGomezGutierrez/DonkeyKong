package game.scene.animation.level_1;

import java.awt.Graphics2D;

import javax.swing.ImageIcon;

import lombok.Data;
import lombok.Getter;

public @Data class Ladder {
	
	@Getter private final int x;
	@Getter private final int y;
	private final int width = 35;
	private final int height = 29;
	private final ImageIcon ladder = new ImageIcon("img\\background\\level_1\\ladder.png");
	
	public void paint(Graphics2D g) {
		g.drawImage(ladder.getImage(), x, y, width, height, null);
	}
	
}
