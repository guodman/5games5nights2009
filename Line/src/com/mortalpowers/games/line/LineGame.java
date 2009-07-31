package com.mortalpowers.games.line;

import java.util.ArrayList;
import java.util.Collections;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
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
		player = line.get((int) Math.round(Math.random() * line.size()));

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

	public static void endTurn() {
		if (deck.size() > 0) {
			hand.add(deck.remove(0));
		}
	}

	public static void kill(Creature c) {
		if (c == player) {
			gameOver();
		} else {
			line.remove(c);
		}
	}
	public static void killLast() {
		kill(line.get(line.size() -1));
	}
	
	public static void useCard(Card c) {
		c.action();
	}

	public static void gameOver() {
		gameOn = false;
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

		}

	}
}
