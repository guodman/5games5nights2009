package org.guodman.smashatronwars;

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
	public static boolean quit;
	public Player player;

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

	public SmashatronWarsGame() {
		super("Smashatron Wars");
	}

	@Override
	public void init(GameContainer container) throws SlickException {
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
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		if (quit) {
			container.exit();
		}

		if (joystick != null) {
			float x1, y1, x2, y2;
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
			if(Math.abs(x1) < .1) x1 = 0;
			if(Math.abs(x2) < .1) x2 = 0;
			if(Math.abs(y1) < .1) y1 = 0;
			if(Math.abs(y2) < .1) y2 = 0;
			
			
			player.x += x1 * SPEED;
			player.y += y1 * SPEED;
			
			/**
			 * Sanitize board values
			 */
			if()
		}

	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		if (joystick != null) {
			String joyInfo = "Joystick Info: ";
			for (int i = 0; i < joystick.getAxisCount(); i++) {
				joyInfo += joystick.getAxisValue(i) + " : ";
			}
			g.drawString(joyInfo, 10, 25);
		}
		player.render(container, g);
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
