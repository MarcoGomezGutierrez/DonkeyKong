package game.scene.level_1.collision;

import java.awt.Rectangle;

public class Collision {
	
	public static int getCollisionFlameOutOfMap(Rectangle rectangle, int speed) {
		if (rectangle.intersects(out1())) return speed;
		else if (rectangle.intersects(out2())) return -speed;
		else if (rectangle.intersects(out3())) return -speed;
		else if (rectangle.intersects(out4())) return speed;
		else if (rectangle.intersects(out5())) return speed;
		else if (rectangle.intersects(out6())) return -speed;
		else if (rectangle.intersects(out7())) return -speed;
		else if (rectangle.intersects(out8())) return speed;
		else if (rectangle.intersects(out9())) return speed;
		else if (rectangle.intersects(out10())) return -speed;
		else if (rectangle.intersects(out11())) return -speed;
		else return 0;
	}
	
	public static boolean getCollisionMarioOutOfMap(Rectangle rectangle) {
		if (rectangle.intersects(outMario1())) return true;
		else if (rectangle.intersects(outMario2())) return true;
		else if (rectangle.intersects(outMario3())) return true;
		else if (rectangle.intersects(outMario4())) return true;
		else if (rectangle.intersects(outMario5())) return true;
		else return false;
	}
	
	public static int getCollisionMarioOutOfMap(Rectangle rectangle, int speed) {
		if (rectangle.intersects(out1())) return speed;
		else if (rectangle.intersects(out2())) return -speed;
		else if (rectangle.intersects(out4())) return speed;
		else if (rectangle.intersects(out6())) return -speed;
		else if (rectangle.intersects(out8())) return speed;
		else if (rectangle.intersects(out10())) return -speed;
		else return 0;
	}
	
	/**
	 * Mario: [x: 827, y: 641]
	 * Rectangle: [x: 861, y: 632, width: 0, height: 57]
	 * @return
	 */
	private static Rectangle outMario1() {
		return new Rectangle(861, 632, 1, 57);
	}
	
	/**
	 * Mario: [x: 34, y: 539]
	 * Rectangle: [x: 33, y: 529, width: 0, height: 57]
	 * @return
	 */
	private static Rectangle outMario2() {
		return new Rectangle(33, 529, 1, 57);
	}
	
	/**
	 * Mario: [x: 826, y: 435]
	 * Rectangle: [x: 862, y: 426, width: 0, height: 57]
	 * @return
	 */
	private static Rectangle outMario3() {
		return new Rectangle(862, 429, 1, 57);
	}
	
	/**
	 * Mario: [x: 34, y: 333]
	 * Rectangle: [x: 34, y: 323, width: 0, height: 57]
	 * @return
	 */
	private static Rectangle outMario4() {
		return new Rectangle(34, 323, 1, 57);
	}
	
	/**
	 * Mario: [x: 826, y: 228]
	 * Rectangle: [x: 863, y: 219, width: 0, height: 57]
	 * @return
	 */
	private static Rectangle outMario5() {
		return new Rectangle(863, 219, 1, 57);
	}
	
	/**
	 * [x: 0, y: 680, width: 2, height: 97] -> 1
	 * @return
	 */
	private static Rectangle out1() {
		return new Rectangle(0, 680, 2, 97);
	}

	/**
	 * [x: 881, y: 684, width: 2, height: 71] -> 2
	 * @return
	 */
	private static Rectangle out2() {
		return new Rectangle(881, 684, 2, 71);
	}
	
	/**
	 * [x: 839, y: 618, width: 1, height: 71] -> 3
	 * @return
	 */
	private static Rectangle out3() {
		return new Rectangle(839, 618, 1, 71);
	}
	
	/**
	 * [x: 0, y: 553, width: 2, height: 97] -> 4
	 * @return
	 */
	private static Rectangle out4() {
		return new Rectangle(0, 553, 2, 97);
	}
	
	/**
	 * [x: 63, y: 489, width: 1, height: 97] -> 5
	 * @return
	 */
	private static Rectangle out5() {
		return new Rectangle(63, 489, 1, 97);
	}
	
	/**
	 * [x: 882, y: 451, width: 1, height: 97] -> 6
	 * @return
	 */
	private static Rectangle out6() {
		return new Rectangle(882, 451, 1, 97);
	}
	
	/**
	 * [x: 838, y: 411, width: 1, height: 71] -> 7
	 * @return
	 */
	private static Rectangle out7() {
		return new Rectangle(838, 411, 1, 71);
	}
	
	/**
	 * [x: 0, y: 346, width: 1, height: 97] -> 8
	 * @return
	 */
	private static Rectangle out8() {
		return new Rectangle(0, 346, 1, 97);
	}
	
	/**
	 * [x: 64, y: 290, width: 1, height: 90] -> 9
	 * @return
	 */
	private static Rectangle out9() {
		return new Rectangle(64, 290, 1, 90);
	}
	
	/**
	 * [x: 881, y: 249, width: 1, height: 92] -> 10
	 * @return
	 */
	private static Rectangle out10() {
		return new Rectangle(881, 249, 1, 92);
	}
	
	/**
	 * [x: 0, y: 680, width: 1, height: 90] -> 11
	 * @return
	 */
	private static Rectangle out11() {
		return new Rectangle(837, 185, 1, 90);
	}
	
}
