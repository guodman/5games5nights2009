package org.guodman.stabRoom;

import java.util.Vector;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class StabRoom extends BasicGame {

	public static final int WIDTH = 1024;
	public static final int HEIGHT = 768;
	public static final int DUDE_HEIGHT = 20;
	public static final int DUDE_WIDTH = 20;
	public static final int RANGE = 70;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			AppGameContainer container = new AppGameContainer(new StabRoom(),
					WIDTH, HEIGHT, false);
			container.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean movingUp = false;
	private boolean movingDown = false;
	private boolean movingLeft = false;
	private boolean movingRight = false;
	private float speed = 0.2f;
	public float x = WIDTH / 2;
	public float y = HEIGHT / 2;
	private Vector<Monster> monsters = new Vector<Monster>();
	private int spawnInterval = 1000;
	private int spawnTime = spawnInterval;
	private int chargeRate = 5000;
	private int charged = chargeRate;
	private int attackCircleTime = 250;
	private int attackCircle = 0;
	private int kills = 0;
	private int attacks = 0;
	private boolean dead = false;

	public StabRoom() {
		super("Stab Room");
	}

	public void reset() {
		x = WIDTH / 2;
		y = HEIGHT / 2;
		monsters = new Vector<Monster>();
		spawnTime = spawnInterval;
		charged = chargeRate;
		attackCircle = 0;
		kills = 0;
		attacks = 0;
		dead = false;
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		monsters.add(new Monster(10, 10, this));
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		if (!dead) {
			if (movingUp) {
				y -= speed * (float) delta;
			}
			if (movingDown) {
				y += speed * (float) delta;
			}
			if (movingLeft) {
				x -= speed * (float) delta;
			}
			if (movingRight) {
				x += speed * (float) delta;
			}
			if (x < 0) {
				x = 0;
			}
			if (y < 0) {
				y = 0;
			}
			if (x > WIDTH) {
				x = WIDTH;
			}
			if (y > HEIGHT) {
				y = HEIGHT;
			}
			for (Monster m : monsters) {
				m.update(container, delta);
			}
			spawnTime -= delta;
			if (spawnTime <= 0) {
				spawnTime += spawnInterval;
				monsters.add(new Monster(10, 10, this));
			}
			if (charged < chargeRate) {
				charged += delta;
			}
			if (attackCircle > 0) {
				attackCircle -= delta;
			}
			for (Monster m : monsters) {
				if (m.getDistance() < DUDE_HEIGHT) {
					dead = true;
				}
			}
		}
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		g.drawString("KILLS: " + kills, 10, 25);
		g.drawString("ATTACKS: " + attacks, 10, 40);
		for (Monster m : monsters) {
			m.render(container, g);
		}
		if (attackCircle > 0) {
			g.setColor(Color.red);
			g.fillOval(x - RANGE / 2, y - RANGE / 2, RANGE, RANGE);
			g.setColor(Color.white);
		}
		if (charged >= chargeRate) {
			g.setColor(Color.red);
		} else {
			g.setColor(Color.pink);
		}
		g.drawOval(x - DUDE_WIDTH / 2, y - DUDE_HEIGHT / 2, DUDE_WIDTH,
				DUDE_HEIGHT);
		g.setColor(Color.white);
		if (dead) {
			g.drawString("You died, press enter to try again", 100, 100);
		}
	}

	public void keyPressed(int key, char c) {
		if (key == Input.KEY_ESCAPE) {
			System.exit(0);
		} else if (key == Input.KEY_UP) {
			movingUp = true;
		} else if (key == Input.KEY_DOWN) {
			movingDown = true;
		} else if (key == Input.KEY_LEFT) {
			movingLeft = true;
		} else if (key == Input.KEY_RIGHT) {
			movingRight = true;
		} else if (key == Input.KEY_SPACE && charged >= chargeRate && !dead) {
			charged = 0;
			attackCircle = attackCircleTime;
			attacks++;
			for (int i = monsters.size() - 1; i >= 0; i--) {
				if (monsters.get(i).getDistance() <= RANGE) {
					System.out.println("Distance: "
							+ monsters.get(i).getDistance());
					monsters.remove(i);
					kills++;
				}
			}
		} else if (dead && key == Input.KEY_ENTER) {
			reset();
		}
	}

	public void keyReleased(int key, char c) {
		if (key == Input.KEY_UP) {
			movingUp = false;
		} else if (key == Input.KEY_DOWN) {
			movingDown = false;
		} else if (key == Input.KEY_LEFT) {
			movingLeft = false;
		} else if (key == Input.KEY_RIGHT) {
			movingRight = false;
		}
	}
}
