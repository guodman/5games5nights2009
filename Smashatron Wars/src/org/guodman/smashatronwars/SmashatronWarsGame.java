package org.guodman.smashatronwars;

import org.lwjgl.input.Controller;
import org.lwjgl.input.Controllers;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class SmashatronWarsGame extends BasicGame {
	public static final int WIDTH = 1024;
	public static final int HEIGHT = 768;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			AppGameContainer container = new AppGameContainer(new SmashatronWarsGame(),
					WIDTH, HEIGHT, false);
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
		// TODO Auto-generated method stub
		Controllers controllers = new Controllers();
		try {
			controllers.create();
			if (controllers.getControllerCount() > 0) {
				joystick = controllers.getController(0);
				System.out.println("Joystick has "+joystick.getButtonCount() +" buttons. Its name is "+joystick.getName());
			} else joystick = null;
		}
		catch (org.lwjgl.LWJGLException e) {System.err.println("Couldn't initialize Controllers: "+e.getMessage());}
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		if (joystick != null) {
			String joyInfo="Joystick Info: ";
			for (int i=0; i < joystick.getAxisCount(); i++) {
				joyInfo += joystick.getAxisValue(i);
			}
			System.out.println(joyInfo);
		}
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub

	}

	public void controllerButtonPressed(final int controller, final int button) {
		System.out.println("Controller: " + controller + " Button: " + button);
	}

	public void controllerButtonReleased(final int controller, final int button) {
	}

	public void controllerDownPressed(final int controller) {
	}

	public void controllerDownReleased(final int controller) {
	}

	public void controllerLeftPressed(final int controller) {
	}

	public void controllerLeftReleased(final int controller) {
	}

	public void controllerRightPressed(final int controller) {
	}

	public void controllerRightReleased(final int controller) {
	}

	public void controllerUpPressed(final int controller) {
	}

	public void controllerUpReleased(final int controller) {
	}
}
