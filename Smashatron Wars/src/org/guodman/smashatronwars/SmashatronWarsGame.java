package org.guodman.smashatronwars;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Controller;
import org.lwjgl.input.Controllers;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class SmashatronWarsGame extends BasicGame {
	public static final int WIDTH = 1024;
	public static final int HEIGHT = 768;
	public static final float SPEED = 5;
	public static final int ENEMY_DEPLOY_INCREMENT = 1000;
	public static boolean quit;
	public static SmashatronWarsGame me = null;
	public Player player;
	public ArrayList<Projectile> projectiles;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			AppGameContainer container = new AppGameContainer(
					new SmashatronWarsGame(), WIDTH, HEIGHT, false);
			container.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Controller joystick = null;
	public List<Enemy> enemies = new ArrayList<Enemy>();
	public int enemyTime = ENEMY_DEPLOY_INCREMENT;
	public boolean dead = false;
	public int score = 0;

	public SmashatronWarsGame() {
		super("Smashatron Wars");
		me = this;
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		projectiles = new ArrayList<Projectile>();
		try {
			Controllers.create();
			if (Controllers.getControllerCount() > 0) {
				joystick = Controllers.getController(0);
				System.out.println("Joystick Name: " + joystick.getName());
				System.out.println("Joystick has " + joystick.getButtonCount()
						+ " buttons. Its name is " + joystick.getName());
			} else
				joystick = null;
		} catch (org.lwjgl.LWJGLException e) {
			System.err.println("Couldn't initialize Controllers: "
					+ e.getMessage());
		}
		player = new Player();
		container.setAlwaysRender(true);
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		if (quit) {
			container.exit();
		}

		if (!dead) {
			float x1, y1, x2, y2;

			if (joystick != null) {

				switch (joystick.getAxisCount()) {
				// Dougbert controller
				case 4:
					x1 = joystick.getAxisValue(0);
					y1 = joystick.getAxisValue(1);
					x2 = joystick.getAxisValue(2);
					y2 = joystick.getAxisValue(3);
					break;

				// Xbox controller
				case 7:
					x1 = joystick.getAxisValue(1);
					y1 = joystick.getAxisValue(2);
					x2 = joystick.getAxisValue(4);
					y2 = joystick.getAxisValue(5);

					break;

				default:
					x1 = x2 = y1 = y2 = 0;
				}
				/**
				 * Sanitize close to zero values.
				 */
				double tolerance = 0.14;
				if (Math.abs(x1) < tolerance)
					x1 = 0;
				if (Math.abs(x2) < tolerance)
					x2 = 0;
				if (Math.abs(y1) < tolerance)
					y1 = 0;
				if (Math.abs(y2) < tolerance)
					y2 = 0;

				player.update(x1, y1, delta);
				spawnProjectiles(x2, y2, delta);
			}

			// add new enemies
			enemyTime -= delta;
			if (enemyTime < 0) {
				enemyTime += ENEMY_DEPLOY_INCREMENT;
				enemies.add(new Enemy(3, WIDTH / 2, 0));
				enemies.add(new Enemy(3, WIDTH / 2, HEIGHT - Enemy.SIZE));
				enemies.add(new Enemy(3, 0, HEIGHT / 2));
				enemies.add(new Enemy(3, WIDTH - Enemy.SIZE, HEIGHT / 2));
			}

			for (Enemy e : enemies) {
				e.update(container, delta);
			}
			for (Projectile p : projectiles) {
				p.update(container, delta);
			}

			// Remove projectiles that have left the screen.
			for (int i = projectiles.size() - 1; i >= 0; i--) {
				Projectile remover = projectiles.get(i);
				if (remover.x < 0 || remover.y < 0 || remover.x > WIDTH
						|| remover.y > HEIGHT) {
					projectiles.remove(i);
				}
			}
		}

	}

	private void spawnProjectiles(float x, float y, int delta) {
		if (x != 0 || y != 0) {
			Projectile p = new Projectile(player.x, player.y, (float) (Math
					.atan(x / -y)
					/ Math.PI * 180f), 1);

			projectiles.add(p);
		}
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		for (Enemy e : enemies) {
			e.render(container, g);
		}
		player.render(container, g);
		for (Projectile p : projectiles) {
			p.render(container, g);
		}
		if (joystick != null) {
			String joyInfo = "Joystick Info: ";
			for (int i = 0; i < joystick.getAxisCount(); i++) {
				joyInfo += joystick.getAxisValue(i) + " : ";
			}
			g.drawString(joyInfo, 10, 25);
		}
		g.drawString("Dead Status is " + dead, 10, 40);
	}

	public void keyPressed(int key, char c) {
		// System.out.println("Someone pressed " + key);

		switch (key) {
		case Input.KEY_ESCAPE:
			quit = true;
			break;
		}
	}
}
