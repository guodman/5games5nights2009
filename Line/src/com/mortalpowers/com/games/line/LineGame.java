package com.mortalpowers.com.games.line;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;



public class LineGame extends BasicGame {
	private static int gameHeight, gameWidth;
	private boolean quit;

	public LineGame() {
		super("Line");
		gameHeight = 800;
		gameWidth = 800;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {

			AppGameContainer container = new AppGameContainer(new LineGame(),
					gameWidth, gameHeight, false);
			container.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		// TODO Auto-generated method stub
		if(quit) {
			container.exit();
		}
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		// TODO Auto-generated method stub

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
