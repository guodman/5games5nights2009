package com.mortalpowers.com.games.line;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public abstract class Card {
	public final String cardType;
	public final String cardText;
	
	public Card(String type, String text) {
		cardType = type;
		cardText = text;
	}
	
	public abstract void action();
	public abstract void init(GameContainer container);
	public abstract void update(GameContainer container, int delta);
	public abstract void render(GameContainer container, Graphics g);
	
	public class Move extends Card {
		public Move() {
			super("Movement", "Move forward in line");
		}

		@Override
		public void action() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void init(GameContainer container) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void render(GameContainer container, Graphics g) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void update(GameContainer container, int delta) {
			// TODO Auto-generated method stub
			
		}
	}
}
