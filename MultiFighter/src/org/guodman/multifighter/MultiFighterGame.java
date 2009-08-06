package org.guodman.multifighter;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class MultiFighterGame extends BasicGame {
	public static final int SCREENWIDTH = 1024;
	public static final int SCREENHEIGHT = 768;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			AppGameContainer container = new AppGameContainer(
					new MultiFighterGame(), SCREENWIDTH, SCREENHEIGHT, false);
			container.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean quit = false;

	public MultiFighterGame() {
		super("Multi Fighter");
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		// TODO Auto-generated method stub

	}

	public void startGame() {

	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		if (quit) {
			container.exit();
		}
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub

	}

	public void keyPressed(int key, char c) {
		// System.out.println("Someone pressed " + key);

		switch (key) {
		case Input.KEY_ESCAPE:
			quit = true;
			break;
		case Input.KEY_BACK:
			startGame();
		}
	}
}
