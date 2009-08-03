package org.guodman.cow;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class CowGame extends BasicGame {
	public static final int WIDTH=1024;
	public static final int HEIGHT=768;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			AppGameContainer container = new AppGameContainer(new CowGame(),
					WIDTH, HEIGHT, false);
			container.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public final int CONVEYOR_LENGTH = 10;
	public final int NUMBER_OF_BELTS = 3;
	public List<Trap> hand = new ArrayList<Trap>();
	public List<Trap> deck = new ArrayList<Trap>();
	public Trap[][] conveyor = new Trap[CONVEYOR_LENGTH][NUMBER_OF_BELTS];

	public CowGame() {
		super("Cow");
		for (int i = 0; i < CONVEYOR_LENGTH; i++) {
			for (int j = 0; j < NUMBER_OF_BELTS; j++) {
				conveyor[i][j] = null;
			}
		}
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	public void mouseMoved(final int oldx, final int oldy, final int newx,
			final int newy) {
		
	}

	public void mousePressed(final int button, final int x, final int y) {
		
	}

	public void mouseReleased(final int button, final int x, final int y) {
		
	}

	public void mouseWheelMoved(final int arg0) {
		
	}
}
