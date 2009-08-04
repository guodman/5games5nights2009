package org.guodman.cow;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class CowGame extends BasicGame {
	public static final int WIDTH=1024;
	public static final int HEIGHT=768;
	public static final int CONVEYOR_LENGTH = 10;
	public static final int NUMBER_OF_BELTS = 3;
	final public static int tileSize = 64;
	public static final float CONVEYOR_OFFSET_X = 0;
	public static final float CONVEYOR_OFFSET_Y = 0;
	public static boolean quit = false;
	public static final int TURN_TIME = 2000;
	public static ArrayList<Image> images;
	
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
	
	public List<Trap> hand = new ArrayList<Trap>();
	public List<Trap> deck = new ArrayList<Trap>();
	public Trap[][] conveyor = new Trap[CONVEYOR_LENGTH][NUMBER_OF_BELTS];
	public List<Cow> cows = new ArrayList<Cow>();
	public int turnCountDown = TURN_TIME;

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
		 images = new ArrayList<Image>();
		 images.add(new Image("/resources/cow.png"));
		 images.add(new Image("/resources/human.png"));
		 images.add(new Image("/resources/chute-left.png"));
		for (int i = 0; i < 3; i++) {
			cows.add(new Cow(i, 0));
		}
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		if(quit) {
			container.exit();
		}
		turnCountDown -= delta;
		if (turnCountDown < 0) {
			turnCountDown += TURN_TIME;
			for (Cow c : cows) {
				c.location++;
				if (conveyor[c.location][c.conveyor] != null) {
					conveyor[c.location][c.conveyor].actOnEntry(c);
				}
			}
		}
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.black);
		for (int i = tileSize; i < WIDTH; i += tileSize) {
			g.drawLine(i, 0, i, HEIGHT);
		}
		for (int i = tileSize; i < HEIGHT; i += tileSize) {
			g.drawLine(0, i, WIDTH, i);
		}
		// Draw the traps
		for (int i = 0; i < CONVEYOR_LENGTH; i++) {
			for (int j = 0; j < NUMBER_OF_BELTS; j++) {
				if (conveyor[i][j] != null) {
					conveyor[i][j].render(container, g);
				}
			}
		}
		for (Cow c : cows) {
			c.render(container, g);
		}
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
	public void keyPressed(int key, char c) {
		switch (key) {
		case Input.KEY_ESCAPE:
			quit = true;
			break;
		}
	}
}
