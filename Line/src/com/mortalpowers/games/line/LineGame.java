package com.mortalpowers.games.line;

import java.util.ArrayList;
import java.util.Collections;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class LineGame extends BasicGame {
	private static int gameHeight, gameWidth, margin;
	private boolean quit;
	private static ArrayList<Renderable> screenImages = new ArrayList<Renderable>();
	private static Creature player;

	/**
	 * Sensible Defaults
	 */
	final static int handSize = 5;
	final static int deckSize = 50;
	final static int lineSize = 20;

	/**
	 * Game-specific variables
	 */
	private static ArrayList<Card> deck;
	public static ArrayList<Creature> line;
	private static Hand hand;

	private static boolean gameOn;

	public LineGame() {
		super("Line");
		gameWidth = 1024;
		gameHeight = 768;
		margin = 50;

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

	public void setup() {
		deck = new ArrayList<Card>();
		line = new ArrayList<Creature>();
		hand = new Hand();

		for (int i = 0; i < deckSize; i++) {
			deck.add(Card.getRandomCard());

		}
		for (int i = 0; i < lineSize; i++) {
			line.add(new Creature());
		}
		player = line.get((int) Math.floor(Math.random() * line.size()));
		player.player = true;

		Collections.shuffle(deck);
		for (int i = 0; i < 5; i++) {
			hand.add(deck.remove(0));
		}

		gameOn = true;
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		setup();

		// Main game loop, once per round.
		startTurn();

	}

	public static void startTurn() {
		if(!gameOn || hand.size() <= 0 || deck.size() <= 0 || line.size() <= 0) {
			gameOver();
		} else {
			System.out.println("Starting this turn.");
			screenImages.clear();
			screenImages.add(hand);
			
			int n = 0;
			int distance = (gameHeight - margin*2) / line.size();
			for(Creature c : line) {
				c.y = n++*distance + margin;
				c.x = 500;
				screenImages.add(c);
			}
		}
		
		
	}

	public static void endTurn() {
		killLast();
		
		
		startTurn();
	}

	public static void draw(int cards) {
		for(int i = 0;i<cards;i++) {
			if (deck.size() > 0) {
				hand.add(deck.remove(0));
			}
		}
	}
	public static void kill(Creature c) {
		if (c == player) {
			gameOver();
			System.out.println("game player was killed");
		} else {
			line.remove(c);
		}
	}
	public static void killLast() {
		kill(line.get(line.size() -1));
	}
	
	public static void useCard(Card c) {
		if(c.action()) {
			hand.remove(c);
			endTurn();
		}
		
	}

	public static void gameOver() {
		if(gameOn) {
			gameOn = false;
			screenImages.add(new Renderable() {
	
				@Override
				void render(GameContainer container, Graphics g) {
					g.setColor(Color.cyan);
					g.drawString("Game is OVER!",margin, margin);
					g.drawString("You were position " + (line.indexOf(player)+1),margin,margin+30);
				}
				
			});
		}
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		if (quit) {
			container.exit();
		}
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		for (int i = 0; i < screenImages.size(); i++) {
			screenImages.get(i).render(container, g);
		}

	}

	public void keyPressed(int key, char c) {
		// System.out.println("Someone pressed " + key);

		switch (key) {

		case Input.KEY_ESCAPE:
			quit = true;
			break;
		case Input.KEY_SPACE:
			setup();
			break;

		}

	}

	public void mouseMoved(final int oldx, final int oldy, final int newx,
			final int newy) {
		
	}

	public void mousePressed(final int button, final int x, final int y) {
		
	}

	public void mouseReleased(final int button, final int x, final int y) {
		for (int i = hand.hand.size()-1; i > -1; i--) {
			Card item = hand.hand.get(i);
			if (x >= item.x && y >= item.y) {
				System.out.println("Clicked on " + item.cardText);
				useCard(item);
				break;
			}
		}
	}

	public void mouseWheelMoved(final int arg0) {
		
	}
}
