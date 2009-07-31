package com.mortalpowers.games.line;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Hand extends Renderable {

	private ArrayList<Card> hand;
	
	public Hand() {
		hand = new ArrayList<Card>();
		x = 50;
		y=300;
	}

	
	void render(GameContainer container, Graphics g) {
		for(Card c : hand) {
			c.render(container, g);
		}
	

	}
	public void add(Card c) {
		
		hand.add(c);
		organizeHand();
	}
	public void organizeHand() {
		for(Card c : hand) {
			c.x = x * (hand.size()-1) * 100;
		}
	}

}
