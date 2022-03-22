package game.scene.level_1;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;

import game.character.Mario;
import game.scene.level_1.collision.Collision;
import lombok.Getter;
import lombok.Setter;

public class Flame {
	
	@Getter @Setter private int x; 
	@Getter @Setter private int y; 
	private int width;
	private int height;
	private final ImageIcon flame_left_1 = new ImageIcon("img\\flame\\flame_left_1.png");
	private final ImageIcon flame_left_2 = new ImageIcon("img\\flame\\flame_left_2.png");
	private final ImageIcon flame_right_1 = new ImageIcon("img\\flame\\flame_right_1.png");
	private final ImageIcon flame_right_2 = new ImageIcon("img\\flame\\flame_right_2.png");
	private final ImageIcon flame_blue_left_1 = new ImageIcon("img\\flame\\flame_blue_left_1.png");
	private final ImageIcon flame_blue_left_2 = new ImageIcon("img\\flame\\flame_blue_left_2.png");
	private final ImageIcon flame_blue_right_1 = new ImageIcon("img\\flame\\flame_blue_right_1.png");
	private final ImageIcon flame_blue_right_2 = new ImageIcon("img\\flame\\flame_blue_right_2.png");
	private int steps;
	private int direccion;
	private List<Floor> listFloor1;
	private List<Floor> listFloor2;
	private List<Floor> listFloor3;
	private List<Floor> listFloor4;
	private List<Floor> listFloor5;
	private List<Floor> listFloor6;
	
	public Flame() {
		this.x = 72; 
		this.y = 689;
		this.width = 40;
		this.height = 40;
		this.steps = 0;
		this.direccion = 0;
		this.listFloor1 = new ArrayList<Floor>();
		this.listFloor2 = new ArrayList<Floor>();
		this.listFloor3 = new ArrayList<Floor>();
		this.listFloor4 = new ArrayList<Floor>();
		this.listFloor5 = new ArrayList<Floor>();
		this.listFloor6 = new ArrayList<Floor>();
		buildFloor1();
		buildFloor2();
		buildFloor3();
		buildFloor4();
		buildFloor5();
		buildFloor6();
		new ChangeImage().start();
		new MoveFlame().start();
	}
	
	public void paint(Graphics2D g) {
		if (direccion == 0) {
			if (steps == 0) {
				if (Mario.isMarioHammer()) g.drawImage(flame_blue_right_1.getImage(), x, y, width, height, null);
				else g.drawImage(flame_right_1.getImage(), x, y, width, height, null);
			}
			else if (steps == 1) {
				if (Mario.isMarioHammer()) g.drawImage(flame_blue_right_2.getImage(), x, y, width, height, null);
				else g.drawImage(flame_right_2.getImage(), x, y, width, height, null);
			}
		} else if (direccion == 1){
			if (steps == 0) {
				if (Mario.isMarioHammer()) g.drawImage(flame_blue_left_1.getImage(), x, y, width, height, null);
				else g.drawImage(flame_left_1.getImage(), x, y, width, height, null);
			}
			else if (steps == 1) {
				if (Mario.isMarioHammer()) g.drawImage(flame_blue_left_2.getImage(), x, y, width, height, null);
				else g.drawImage(flame_left_2.getImage(), x, y, width, height, null);
			}
		}
	}
	
	/**
	 * Stairs:
	 	[x: 323, y: 667, width: 30, height: 111] //First-Broken-Floor1
	 	----------------------------------------------
		[x: 741, y: 688, width: 30, height: 71] //Second-Floor1
		----------------------------------------------
		[x: 386, y: 573, width: 31, height: 95] //First-Floor2
		----------------------------------------------
		[x: 131, y: 584, width: 32, height: 73] //Second-Floor2
		----------------------------------------------
		[x: 259, y: 459, width: 31, height: 118] //First-Broken-Floor3
		----------------------------------------------
		[x: 454, y: 466, width: 30, height: 102] //Second-Floor3
		----------------------------------------------
		[x: 741, y: 481, width: 30, height: 71] //Third-Floor3
		----------------------------------------------
		[x: 678, y: 355, width: 30, height: 121] //First-Broken-Floor4
		----------------------------------------------
		[x: 291, y: 373, width: 30, height: 84] //Second-Floor4
		----------------------------------------------
		[x: 131, y: 377, width: 31, height: 74] //Third-Floor4
		----------------------------------------------
		[x: 354, y: 267, width: 31, height: 100] //First-Broken-Floor5
		----------------------------------------------
		[x: 740, y: 267, width: 31, height: 71] //Second-Floor5
	 *
	 */
	
	/**
	 * [x: 323, y: 667, width: 30, height: 111] //First-Broken-Floor1
	 * @return
	 */
	private Rectangle getBoundsFirstBrokenFloor1() {
		return new Rectangle(323, 667, 30, 111);
	}
	
	/**
	 * [x: 741, y: 690, width: 30, height: 70] //Second-Floor1
	 * @return
	 */
	private Rectangle getBoundsSecondFloor1() {
		return new Rectangle(741, 690, 30, 70);
	}
	
	/**
	 * [x: 386, y: 573, width: 31, height: 95] //First-Floor2
	 * @return
	 */
	private Rectangle getBoundsFirstFloor2() {
		return new Rectangle(386, 573, 31, 95);
	}
	
	/**
	 * [x: 131, y: 584, width: 32, height: 73] //Second-Floor2
	 * @return
	 */
	private Rectangle getBoundsSecondFloor2() {
		return new Rectangle(131, 584, 32, 73);
	}
	
	/**
	 * [x: 259, y: 459, width: 31, height: 118] //First-Broken-Floor3
	 * @return
	 */
	private Rectangle getBoundsFirstBrokenFloor3() {
		return new Rectangle(259, 459, 31, 118);
	}
	
	/**
	 * [x: 454, y: 466, width: 30, height: 102] //Second-Floor3
	 * @return
	 */
	private Rectangle getBoundsSecondFloor3() {
		return new Rectangle(454, 466, 30, 102);
	}
	
	/**
	 * [x: 741, y: 481, width: 30, height: 71] //Third-Floor3
	 * @return
	 */
	private Rectangle getBoundsThirdFloor3() {
		return new Rectangle(741, 481, 30, 71);
	}
	
	/**
	 * [x: 678, y: 355, width: 30, height: 121] //First-Broken-Floor4
	 * @return
	 */
	private Rectangle getBoundsFirstBrokenFloor4() {
		return new Rectangle(678, 355, 30, 121);
	}
	
	/**
	 * [x: 291, y: 373, width: 30, height: 84] //Second-Floor4
	 * @return
	 */
	private Rectangle getBoundsSecondFloor4() {
		return new Rectangle(291, 373, 30, 84);
	}
	
	/**
	 * [x: 131, y: 377, width: 31, height: 74] //Third-Floor4
	 * @return
	 */
	private Rectangle getBoundsThirdFloor4() {
		return new Rectangle(131, 377, 31, 74);
	}
	
	/**
	 * [x: 354, y: 267, width: 31, height: 100] //First-Broken-Floor5
	 * @return
	 */
	private Rectangle getBoundsFirstBrokenFloor5() {
		return new Rectangle(354, 267, 31, 100);
	}
	
	/**
	 * [x: 740, y: 267, width: 31, height: 71] //Second-Floor5
	 * @return
	 */
	private Rectangle getBoundsSecondFloor5Floor5() {
		return new Rectangle(740, 267, 31, 71);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
	
	private class ChangeImage extends Thread {
		
		@Override
		public void run() {
			while (true) {
				try { Thread.sleep(200); } catch (InterruptedException e) {}
				if (steps == 0) {
					steps++;
				} else if (steps == 1) {
					steps = 0;
				}
			}
		}
		
	}
	
	private class MoveFlame extends Thread {
		
		private Random rand = new Random();
		private boolean floor1 = true;
		private boolean floor2 = false;
		private boolean floor3 = false;
		private boolean floor4 = false;
		private boolean floor5 = false;
		private boolean floor6 = false;
		private boolean animationStart = true;
		private boolean stop = false;
		private boolean right = true;
		private boolean left = false;
		private int speed = 15;//10
		private int xFi;
		private int xFf;
		private int stair = 0;
		private int collision;
		private int step = 1;
		private int stepsStop = 0;
		
		
		@Override
		public void run() {
			while (true) {
				try { Thread.sleep(160); } catch (InterruptedException e) {}
				if (Game_1.winMario) break;
				if (!PointsAnimation.isAnimationPoints()) {
					if (animationStart) animationStart();
					if (!stop) {
						if (right) {
							direccion = 0; //Right
							x += speed;
						} else if (left) {
							direccion = 1; //Left
							x -= speed;
						}
					}
					floorFlame();
					if (stair != 0) stair--;
					if ((collision = Collision.getCollisionFlameOutOfMap(getBounds(), speed)) != 0) {
						x = x + collision;
					}
					if (stepsStop == 0) {
						if (stop) stop = false;
						else if (rand.nextInt((10 - 1) + 1) + 1 == 5) {
							stop = true;
							stepsStop = rand.nextInt(10);
						} else if (rand.nextInt((5 - 1) + 1) + 1 == 4) {
							if (left) {
								left = false;
								right = true;
								if (floor2 || floor4 || floor6) stepsStop = rand.nextInt(40);
								else stepsStop = rand.nextInt(20);
							} else if (right) {
								left = true;
								right = false;
								if (floor1 || floor3 || floor5) stepsStop = rand.nextInt(40);
								else stepsStop = rand.nextInt(20);
							}
						}
					} else {
						stepsStop--;
					}
					if (step == 1 || step == 2) {
						y -= 3;
						step++;
					} else if (step == 3){
						y += 3;
						step++;
					} else if (step == 4){
						y += 3;
						step = 1;
					}
				}
			}
		}
		
		/**
		 * Colision con el suelo
		 */
		private void floorFlame() {
			if (floor1) floor1();
			else if (floor2) floor2();
			else if (floor3) floor3();
			else if (floor4) floor4();
			else if (floor5) floor5();
			else if (floor6) {
				for (Floor floor : listFloor6) {
					xFi = floor.getX();
					xFf = xFi + floor.getWidth();
					if (xFi <= x && x >= xFf) {
						y = floor.getY() - 35;
						break;
					}
				}
			}
		}
		
		private void floor1() {
			/**
			 * Stairs
			 */
			if (getBounds().intersects(getBoundsFirstBrokenFloor1()) && stair == 0) {
				if ((rand.nextInt((4 - 1) + 1) + 1) == 1 || Game_1.getTimer() == 40) {
					while (getBounds().intersects(getBoundsFirstBrokenFloor1())) {
						if (getBounds().intersects(getBoundsFirstBrokenFloor1())) {
							try { Thread.sleep(150); } catch (InterruptedException e) {}
							x = 323;
							y -= 2;
						} else {
							break;
						}
					}
					floor1 = false;
					floor2 = true;
				} else {
					stair = 15;
				}
			} else if (getBounds().intersects(getBoundsSecondFloor1()) && stair == 0) {
				if ((rand.nextInt((4 - 1) + 1) + 1) == 1 || Game_1.getTimer() == 40) {
					while (getBounds().intersects(getBoundsSecondFloor1())) {
						if (getBounds().intersects(getBoundsSecondFloor1())) {
							try { Thread.sleep(150); } catch (InterruptedException e) {}
							x = 735;
							y -= 2;
						} else {
							break;
						}
					}
					floor1 = false;
					floor2 = true;
				} else {
					stair = 15;
				}
			/**
			 * walk floor
			 */
			} else {
				for (Floor floor : listFloor1) {
					xFi = floor.getX();
					xFf = xFi + floor.getWidth();
					if (xFi >= x && x<= xFf) {
						y = floor.getY() - 38;
						break;
					}
				}
			}
		}
		
		private void floor2() {
			/**
			 * Stairs
			 */
			if (getBounds().intersects(getBoundsFirstFloor2()) && stair == 0) {
				if ((rand.nextInt((4 - 1) + 1) + 1) == 1 || Game_1.getTimer() == 80) {
					while (getBounds().intersects(getBoundsFirstFloor2())) {
						if (getBounds().intersects(getBoundsFirstFloor2())) {
							try { Thread.sleep(150); } catch (InterruptedException e) {}
							x = 381;
							y -= 2;
						} else {
							break;
						}
					}
					floor2 = false;
					floor3 = true;
				} else {
					stair = 15;
				}
			} else if (getBounds().intersects(getBoundsSecondFloor2()) && stair == 0) {
				if ((rand.nextInt((4 - 1) + 1) + 1) == 1 || Game_1.getTimer() == 80) {
					while (getBounds().intersects(getBoundsSecondFloor2())) {
						if (getBounds().intersects(getBoundsSecondFloor2())) {
							try { Thread.sleep(150); } catch (InterruptedException e) {}
							x = 125;
							y -= 2;
						} else {
							break;
						}
					}
					floor2 = false;
					floor3 = true;
				} else {
					stair = 15;
				}
			/**
			 * walk floor
			 */
			} else {
				for (Floor floor : listFloor2) {
					xFi = floor.getX();
					xFf = xFi + floor.getWidth();
					if (xFi <= x && x >= xFf) {
						y = floor.getY() - 38;
						break;
					}
				}
			}
		}
		
		private void floor3() {
			/**
			 * Stairs
			 */
			if (getBounds().intersects(getBoundsFirstBrokenFloor3()) && stair == 0) {
				if ((rand.nextInt((4 - 1) + 1) + 1) == 1 || Game_1.getTimer() == 120) {
					while (getBounds().intersects(getBoundsFirstBrokenFloor3())) {
						if (getBounds().intersects(getBoundsFirstBrokenFloor3())) {
							try { Thread.sleep(150); } catch (InterruptedException e) {}
							x = 254;
							y -= 2;
						} else {
							break;
						}
					}
					floor3 = false;
					floor4 = true;
				} else {
					stair = 15;
				}
			} else if (getBounds().intersects(getBoundsSecondFloor3()) && stair == 0) {
				if ((rand.nextInt((4 - 1) + 1) + 1) == 1 || Game_1.getTimer() == 120) {
					while (getBounds().intersects(getBoundsSecondFloor3())) {
						if (getBounds().intersects(getBoundsSecondFloor3())) {
							try { Thread.sleep(150); } catch (InterruptedException e) {}
							x = 448;
							y -= 2;
						} else {
							break;
						}
					}
					floor3 = false;
					floor4 = true;
				} else {
					stair = 15;
				}
			} else if (getBounds().intersects(getBoundsThirdFloor3()) && stair == 0) {
				if ((rand.nextInt((4 - 1) + 1) + 1) == 1 || Game_1.getTimer() == 120) {
					while (getBounds().intersects(getBoundsThirdFloor3())) {
						if (getBounds().intersects(getBoundsThirdFloor3())) {
							try { Thread.sleep(150); } catch (InterruptedException e) {}
							x = 736;
							y -= 2;
						} else {
							break;
						}
					}
					floor3 = false;
					floor4 = true;
				} else {
					stair = 15;
				}
			/**
			 * walk floor
			 */
			} else {
				for (Floor floor : listFloor3) {
					xFi = floor.getX();
					xFf = xFi + floor.getWidth();
					if (xFi >= x && x <= xFf) {
						y = floor.getY() - 38;
						break;
					}
				}
			}
		}
		
		private void floor4() {
			/**
			 * Stairs
			 */
			if (getBounds().intersects(getBoundsFirstBrokenFloor4()) && stair == 0) {
				if ((rand.nextInt((4 - 1) + 1) + 1) == 1 || Game_1.getTimer() == 160) {
					while (getBounds().intersects(getBoundsFirstBrokenFloor4())) {
						if (getBounds().intersects(getBoundsFirstBrokenFloor4())) {
							try { Thread.sleep(150); } catch (InterruptedException e) {}
							x = 673;
							y -= 2;
						} else {
							break;
						}
					}
					floor4 = false;
					floor5 = true;
				} else {
					stair = 15;
				}
			} else if (getBounds().intersects(getBoundsSecondFloor4()) && stair == 0) {
				if ((rand.nextInt((4 - 1) + 1) + 1) == 1 || Game_1.getTimer() == 160) {
					while (getBounds().intersects(getBoundsSecondFloor4())) {
						if (getBounds().intersects(getBoundsSecondFloor4())) {
							try { Thread.sleep(150); } catch (InterruptedException e) {}
							x = 284;
							y -= 2;
						} else {
							break;
						}
					}
					floor4 = false;
					floor5 = true;
				} else {
					stair = 15;
				}
			} else if (getBounds().intersects(getBoundsThirdFloor4()) && stair == 0) {
				if ((rand.nextInt((4 - 1) + 1) + 1) == 1 || Game_1.getTimer() == 160) {
					while (getBounds().intersects(getBoundsThirdFloor4())) {
						if (getBounds().intersects(getBoundsThirdFloor4())) {
							try { Thread.sleep(150); } catch (InterruptedException e) {}
							x = 124;
							y -= 2;
						} else {
							break;
						}
					}
					floor4 = false;
					floor5 = true;
				} else {
					step = 15;
				}
			/**
			 * walk floor
			 */
			} else {
				for (Floor floor : listFloor4) {
					xFi = floor.getX();
					xFf = xFi + floor.getWidth();
					if (xFi <= x && x >= xFf) {
						y = floor.getY() - 38;
						break;
					}
				}
			}
		}
		
		private void floor5() {
			/**
			 * Stairs
			 */
			if (getBounds().intersects(getBoundsFirstBrokenFloor5()) && stair == 0) {
				if ((rand.nextInt((4 - 1) + 1) + 1) == 1 || Game_1.getTimer() == 200) {
					while (getBounds().intersects(getBoundsFirstBrokenFloor5())) {
						if (getBounds().intersects(getBoundsFirstBrokenFloor5())) {
							try { Thread.sleep(150); } catch (InterruptedException e) {}
							x = 349;
							y -= 2;
						} else {
							break;
						}
					}
					floor5 = false;
					floor6 = true;
				} else {
					stair = 15;
				}
			} else if (getBounds().intersects(getBoundsSecondFloor5Floor5()) && stair == 0) {
				if ((rand.nextInt((4 - 1) + 1) + 1) == 1 || Game_1.getTimer() == 200) {
					while (getBounds().intersects(getBoundsSecondFloor5Floor5())) {
						if (getBounds().intersects(getBoundsSecondFloor5Floor5())) {
							try { Thread.sleep(150); } catch (InterruptedException e) {}
							x = 735;
							y -= 2;
						} else {
							break;
						}
					}
					floor5 = false;
					floor6 = true;
				} else {
					stair = 15;
				}
			/**
			 * walk floor
			 */
			} else {
				for (Floor floor : listFloor5) {
					xFi = floor.getX();
					xFf = xFi + floor.getWidth();
					if (xFi >= x && x <= xFf) {
						y = floor.getY() - 38;
						break;
					}
				}
			}
		}
		
		private void animationStart() {
			try { Thread.sleep(50); } catch (InterruptedException e) {}
			direccion = 0;
			x = 110;
			y = 687;
			try { Thread.sleep(50); } catch (InterruptedException e) {}
			x = 127;
			y = 704;
			try { Thread.sleep(50); } catch (InterruptedException e) {}
			x = 128;
			y = 716;
			try { Thread.sleep(50); } catch (InterruptedException e) {}
			x = 128;
			y = 735;
			animationStart = false;
		}
		
	}
	
	private void buildFloor1() {
		listFloor1.addAll(Arrays.asList(
				new Floor(455, 776), // 1
				new Floor(517, 772), // 2
				new Floor(581, 768), // 3
				new Floor(645, 765), // 4
				new Floor(710, 761), // 5
				new Floor(778, 759), // 6
				new Floor(841, 757)  // 7
		));
	}
	
	private void buildFloor2() {
		listFloor2.addAll(Arrays.asList(
				new Floor(774, 690), // 8
				new Floor(708, 687), // 9
				new Floor(645, 685), // 10
				new Floor(580, 681), // 11
				new Floor(517, 679), // 12
				new Floor(453, 673), // 13
				new Floor(388, 671), // 14
				new Floor(321, 669), // 15
				new Floor(258, 665), // 16
				new Floor(194, 663), // 17
				new Floor(129, 659), // 18
				new Floor(65, 657), // 19
				new Floor(-1, 653) // 20
		));
	}
	
	private void buildFloor3() {
		listFloor3.addAll(Arrays.asList(
				new Floor(65, 588), // 8
				new Floor(131, 584), // 9
				new Floor(195, 580), // 10
				new Floor(258, 578), // 11
				new Floor(322, 574), // 12
				new Floor(389, 572), // 13
				new Floor(453, 569), // 14
				new Floor(518, 566), // 15
				new Floor(582, 564), // 16
				new Floor(646, 558), // 17
				new Floor(709, 554), // 18
				new Floor(774, 552), // 19
				new Floor(840, 550) // 20
		));
	}
	
	private void buildFloor4() {
		listFloor4.addAll(Arrays.asList(
				new Floor(775, 484), // 34
				new Floor(709, 481), // 35
				new Floor(646, 478), // 36
				new Floor(580, 475), // 37
				new Floor(517, 473), // 38
				new Floor(454, 467), // 39
				new Floor(389, 463), // 40
				new Floor(321, 461), // 41
				new Floor(259, 458), // 42
				new Floor(194, 455), // 43
				new Floor(131, 453), // 44
				new Floor(65, 450),  // 45
				new Floor(0, 445) 	 // 46
		));
	}
	
	private void buildFloor5() {
		listFloor5.addAll(Arrays.asList(
				new Floor(64, 381),  // 47
				new Floor(130, 375), // 48
				new Floor(196, 371), // 49
				new Floor(258, 369), // 50
				new Floor(322, 367), // 51
				new Floor(390, 365), // 52
				new Floor(454, 361), // 53
				new Floor(516, 359), // 54
				new Floor(582, 357), // 55
				new Floor(646, 351), // 56
				new Floor(710, 347), // 57
				new Floor(774, 345), // 58
				new Floor(840, 343)  // 59
		));
	}
	
	private void buildFloor6() {
		listFloor6.addAll(Arrays.asList(
				new Floor(774, 275), // 60
				new Floor(708, 273), // 61
				new Floor(644, 269), // 62
				new Floor(581, 267), // 63
				new Floor(518, 265) // 64
		));
	}
	
}
