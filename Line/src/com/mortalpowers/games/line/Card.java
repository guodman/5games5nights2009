package com.mortalpowers.games.line;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public abstract class Card extends Renderable {
	public final String cardType;
	public final String cardText;
	public int x;
	public int y;
	
	public Card(String type, String text) {
		cardType = type;
		cardText = text;
	}
	
	public static Card getRandomCard() {
		return new Move(3);
	}
	
	public abstract void action();
	public abstract void init(GameContainer container);
	public abstract void update(GameContainer container, int delta);
	
	public static class Move extends Card {
		private final int distance;
		
		public Move(final int distance) {
			super("Movement", "Move forward in line");
			this.distance = distance;
			x = 100;
			y = 100;
		}
		@Override
		public void action() {}
		@Override
		public void init(GameContainer container) {}
		@Override
		public void render(GameContainer container, Graphics g) {
			g.setColor(Color.cyan);
			g.drawString(cardText, x, y);
			g.drawString("Distance: " + distance, x, y+15);
			g.setColor(Color.white);
		}
		@Override
		public void update(GameContainer container, int delta) {}
	}
	
	public static class Kill extends Card {
		public final int quantity;
		public Kill(final int quantity) {
			super("Kill", "Kill people in the back of the line");
			this.quantity = quantity;
		}
		@Override
		public void action() {}
		@Override
		public void init(GameContainer container) {}
		@Override
		public void update(GameContainer container, int delta) {}
		@Override
		void render(GameContainer container, Graphics g) {
			g.setColor(Color.red);
			g.drawString(cardText, x, y);
			g.drawString("Kills: " + quantity, x, y+15);
			g.setColor(Color.white);
		}
		
	}
}
