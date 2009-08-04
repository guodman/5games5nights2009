package org.guodman.cow;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Trap {
	float x, y;
	public String name;
	public Image imgResource;
	public int states;
	boolean flipped = false;

	public Trap(int i) {
		imgResource = CowGame.images.get(i);

	}

	public Trap setLocation(int location, int conveyor) {
		x = location * CowGame.tileSize + CowGame.CONVEYOR_OFFSET_X;
		y = conveyor * CowGame.tileSize + CowGame.CONVEYOR_OFFSET_Y;
		return this;
	}

	public boolean canBeEnteredLeft = true;
	public boolean canBeEnteredRight = true;

	public void actOnEntry(Cow c) {
		if (!flipped) {
			if (c.sanitizeLocation()) {
				System.out.println("sanitation wasn't necessary.");
			} else {
				System.out.println("Sanitation was necessary");
				System.out.println("Currently at " + c.conveyor
						+ " and location: " + c.location);
			}
			Trap t = CowGame.me.conveyor[c.location][c.conveyor];
			if (t != null && t != this) {
				t.actOnEntry(c);
			}
		}

	}

	public void flip() {
		flipped = !flipped;
	}

	public void update(GameContainer c, int delta) {

	}

	public void render(GameContainer c, Graphics g) {
		g.drawImage(imgResource, x, y);
		if (flipped) {
			g.drawString("Flipped", x, y);
		}
	}


	public static class Mover extends Trap {
		/**
		 * xTileChange and yTileChange indicate where the cow is pushed to.
		 */
		int conveyorChange, locationChange;
		boolean skipIntermediate = false;

		public Mover(int conveyorChange, int locationChange,
				boolean skipIntermediateSteps) {
			super(4);

			this.conveyorChange = conveyorChange;
			this.locationChange = locationChange;
			skipIntermediate = skipIntermediateSteps;

		}

		public void actOnEntry(Cow c) {
			if (!flipped) {
				c.conveyor += conveyorChange;
				c.location += locationChange;
				super.actOnEntry(c);
			}
			flip();
		}
		public void render(GameContainer c, Graphics g) {
			super.render(c,g);
			g.drawString(conveyorChange + "x" + locationChange,x,y+20);
		}
	}

	public static class Success extends Trap {
		public Success() {
			super(5);
		}

		public void actOnEntry(Cow c) {
			if(!flipped) {CowGame.score++;}
			flip();
			c.dead = true;
			System.out.println("You got a point for saving a cow!");
			System.out.println("Total score is " + CowGame.score);
		}
	}

	public static class Failure extends Trap {
		public Failure() {
			super(6);
		}

		public void actOnEntry(Cow c) {
			c.dead = true;
		}
	}
}
