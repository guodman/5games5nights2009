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
	public static final int WIDTH = 1024;
	public static final int HEIGHT = 768;
	public static final int CONVEYOR_LENGTH = 10;
	public static final int NUMBER_OF_BELTS = 3;
	final public static int tileSize = 64;
	public static final float CONVEYOR_OFFSET_X = 0;
	public static final float CONVEYOR_OFFSET_Y = 0;
	public static boolean quit = false;
	public static final int TURN_TIME = 500;
	public static ArrayList<Image> images;
	public static CowGame me;
	public static int score = 0;

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
	/**
	 * length or location on conveyor belt is first, number of belts is second
	 */
	public Trap[][] conveyor = new Trap[CONVEYOR_LENGTH][NUMBER_OF_BELTS];
	public List<Cow> cows = new ArrayList<Cow>();
	public int turnCountDown = TURN_TIME;
	public Trap activeTile = null;

	public CowGame() {
		super("Cow");
		for (int i = 0; i < CONVEYOR_LENGTH; i++) {
			for (int j = 0; j < NUMBER_OF_BELTS; j++) {
				conveyor[i][j] = null;
			}
		}
		me = this;
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		container.setAlwaysRender(true);

		images = new ArrayList<Image>();
		images.add(new Image("/resources/cow.png"));
		images.add(new Image("/resources/human.png"));
		images.add(new Image("/resources/trap-chute-left.png"));
		images.add(new Image("/resources/trap-bird.png"));
		images.add(new Image("/resources/trap-hose.png"));
		images.add(new Image("/resources/cow-death.png"));
		images.add(new Image("/resources/cow-life.png"));

		for (int i = 0; i < 3; i++) {
			cows.add(new Cow(i, 0));
		}
		conveyor[4][1] = new Trap.Mover(1, 1, true).setLocation(4, 1);
		conveyor[8][2] = new Trap.Mover(3, 2, false).setLocation(8, 2);
		for (int i = 0; i < NUMBER_OF_BELTS; i++) {
			conveyor[CONVEYOR_LENGTH - 1][i] = new Trap.Success().setLocation(
					CONVEYOR_LENGTH - 1, i);
		}
		hand.add(new Trap.Mover(-1, 1, false).setLocation(1, NUMBER_OF_BELTS+1));
		hand.add(new Trap.Mover(1, 3, false).setLocation(3, NUMBER_OF_BELTS+1));
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		if (quit) {
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
			for (int i = cows.size() - 1; i >= 0; i--) {
				if (cows.get(i).dead) {
					cows.remove(i);
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
		// Draw the cows
		for (Cow c : cows) {
			c.render(container, g);
		}
		// Draw the hand
		for (Trap t : hand) {
			t.render(container, g);
	}
	}

	public void mouseMoved(final int oldx, final int oldy, final int newx,
			final int newy) {

	}

	public void mousePressed(final int button, final int x, final int y) {

	}

	public void mouseReleased(final int button, final int x, final int y) {
		// if we are on the conveyor belt
		if (x > CONVEYOR_OFFSET_X && x < CONVEYOR_OFFSET_X+CONVEYOR_LENGTH*tileSize && y > CONVEYOR_OFFSET_Y && y < CONVEYOR_OFFSET_Y+NUMBER_OF_BELTS*tileSize) {
			System.out.println("clicked on the belt");
			int belt = (int) ((y - CONVEYOR_OFFSET_Y) / tileSize);
			int location = (int) ((x - CONVEYOR_OFFSET_X) / tileSize);
			System.out.println("Belt: " + belt + " Location: " + location);
			if (activeTile != null && conveyor[location][belt] == null) {
				conveyor[location][belt] = activeTile;
				hand.remove(activeTile);
				activeTile = null;
			}
		}
		// if we are on the hand
		for (Trap t : hand) {
			if (x > t.x && x < t.x+tileSize && y > t.y && y < t.y+tileSize) {
				activeTile = t;
				System.out.println("clicked on tile " + t.name + " " + t.toString());
				break;
			}
		}
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
