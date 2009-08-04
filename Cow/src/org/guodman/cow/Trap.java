package org.guodman.cow;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.AppletGameContainer.Container;

public class Trap {
	float x, y;
	public String name;
	public Image imgResource;
	public int states;
	
	public Trap() {
		imgResource = CowGame.images.get(2);
	}
	
	public boolean canBeEnteredLeft = true;
	public boolean canBeEnteredRight = true;
	
	public void actOnEntry(Cow c) {
		System.out.println("Error, trap with no defined action for cow.");
	}
	
	public void update(GameContainer c, int delta) {
		
	}
	public void render(GameContainer c, Graphics g) {
		g.drawImage(imgResource, x, y);
	}
	
	
	public class Chute extends Trap {
		public Chute() {
			
		}
	}
	public class Magnet extends Trap {
		public Magnet() {
			
		}
	}
	public class Wall extends Trap {
		public Wall(boolean right) {
			if(right) {
				canBeEnteredRight = false;
			} else {
				canBeEnteredLeft = false;
			}
		}
	}
	public class Mover extends Trap {
		/**
		 * xTileChange and yTileChange indicate where the cow is pushed to.
		 */
		int conveyorChange, locationChange;
		boolean skipIntermediate = false;
		
		public Mover(int conveyorChange, int locationChange, boolean skipIntermediateSteps) {
			this.conveyorChange = conveyorChange;
			this.locationChange = locationChange;
			skipIntermediate = skipIntermediateSteps;
		}
		public void actOnEntry(Cow c) {
			c.conveyor += conveyorChange;
			c.location += locationChange;
			c.sanitizeLocation();
		}
	}
}
