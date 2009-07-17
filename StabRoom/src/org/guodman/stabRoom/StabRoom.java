package org.guodman.stabRoom;

import java.util.Vector;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class StabRoom extends BasicGame {

	public static final int WIDTH = 1024;
	public static final int HEIGHT = 768;
	public static final int DUDE_HEIGHT = 20;
	public static final int DUDE_WIDTH = 20;

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
	private float speed = 0.1f;
	public float x = WIDTH / 2;
	public float y = HEIGHT / 2;
	private Vector<Monster> monsters = new Vector<Monster>();

	public StabRoom() {
		super("Stab Room");
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		monsters.add(new Monster(10, 10, this));
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
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
		for (Monster m : monsters) {
			m.update(container, delta);
		}
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		for (Monster m : monsters) {
			m.render(container, g);
		}
		g.drawRect(x - DUDE_WIDTH / 2, y - DUDE_HEIGHT / 2, DUDE_WIDTH,
				DUDE_HEIGHT);
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
