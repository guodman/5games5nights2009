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
	public static SmashatronWarsGame me = null;
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
		me = this;
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
		container.setAlwaysRender(true);
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		if (quit) {
			container.exit();
		}

		player.update(joystick, delta);
		

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
