package com.mortalpowers.games.line;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/**
 * Cards are currently 2 lines of text. 400x30
 * 
 * @author stephen
 * 
 */
public abstract class Card extends Renderable {
	public final String cardType;
	public final String cardText;
	public final int id;
	public static int nextID = 0;

	public Card(String type, String text) {
		cardType = type;
		cardText = text;
		this.id = nextID++;
	}

	public static Card getRandomCard() {
		int rand = (int) Math.floor(Math.random() * 3);
		int power = (int) Math.floor(Math.random() * 3);
		switch (rand) {
		case 0:
			int sign=(int) (Math.random()*2.0);
			if (sign == 0) { sign = -1; }
			return new Move(power*sign);
		case 1:
			return new KillBack(power);
		case 2:
			return new KillFront(power);
		default:
			return null;
		}
	}

	public abstract boolean action();

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
		public boolean action() {
			Creature p = null;
			for (Creature c : LineGame.line) {
				if (c.player) {
					p = c;
				}
			}
			if (distance > 0) {
				if (LineGame.line.indexOf(p) < distance - 1) {

				}
				return false;
			} else if (distance < 0) {
				LineGame.draw(-distance);
				return true;
			} else {
				return true;
			}
		}

		@Override
		public void init(GameContainer container) {
		}

		@Override
		public void render(GameContainer container, Graphics g) {
			g.setColor(Color.cyan);
			g.drawString(id + ": " + cardText, x, y);
			g.drawString("Distance: " + distance, x, y + 15);
			g.setColor(Color.white);
		}

		@Override
		public void update(GameContainer container, int delta) {
		}
		
		public String toString() {
			return cardType + " - " + id + ": " + cardText;
		}
	}

	public static class KillBack extends Card {
		public final int quantity;

		public KillBack(final int quantity) {
			super("Kill", "Kill people in the back of the line");
			this.quantity = quantity;
		}

		@Override
		public boolean action() {
			if (LineGame.line.size() >= quantity) {
				for (int i = 0; i < quantity; i++) {
					LineGame.killLast();
				}
				return true;
			} else {
				return false;
			}
		}

		@Override
		public void init(GameContainer container) {
		}

		@Override
		public void update(GameContainer container, int delta) {
		}

		@Override
		void render(GameContainer container, Graphics g) {
			g.setColor(Color.red);
			g.drawString(id + ": " + cardText, x, y);
			g.drawString("Kills: " + quantity, x, y + 15);
			g.setColor(Color.white);
		}
	}

	public static class KillFront extends Card {
		public final int quantity;

		public KillFront(final int quantity) {
			super("Kill", "Kill people in the front of the line");
			this.quantity = quantity;
		}

		@Override
		public boolean action() {
			if (LineGame.line.size() >= quantity) {
				for (int i = 0; i < quantity; i++) {
					Creature kill = LineGame.line.get(0);
					LineGame.kill(kill);
				}
				return true;
			} else {
				return false;
			}
		}

		@Override
		public void init(GameContainer container) {
		}

		@Override
		public void update(GameContainer container, int delta) {
		}

		@Override
		void render(GameContainer container, Graphics g) {
			g.setColor(Color.red);
			g.drawString(id + ": " + cardText, x, y);
			g.drawString("Kills: " + quantity, x, y + 15);
			g.setColor(Color.white);
		}
	}
}
