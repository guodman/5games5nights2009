package org.guodman.smashatronwars;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Player {
	float x, y;
	int width, height;
	Image resource;
	float previousDir;

	public Player() {
		x = SmashatronWarsGame.WIDTH / 2 - 15;
		y = SmashatronWarsGame.HEIGHT / 2 - 15;

		width = 30;
		height = 30;
		resource = SmashatronWarsGame.images.get(0);
	}

	public void render(GameContainer c, Graphics g) {
		g.drawImage(resource,x,y);
	}

	public void update(float x1, float y1, float delta) {
		x += x1 * SmashatronWarsGame.SPEED;
		y += y1 * SmashatronWarsGame.SPEED;

		/**
		 * Sanitize board values
		 */
		if (x < 0)
			x = 0;
		if (y < 0)
			y = 0;
		if (y > (SmashatronWarsGame.HEIGHT - width))
			y = (SmashatronWarsGame.HEIGHT - width);
		if (x > (SmashatronWarsGame.WIDTH - height))
			x = (SmashatronWarsGame.WIDTH - height);
	}

	
	boolean temp = false;
	public void setRotation(float direction) {
		if(!temp) {
			previousDir = -90;
			System.out.println("Moving to direction" + direction/Math.PI*180);
		
		
			resource.rotate(-(float) (previousDir - (direction/(Math.PI*180))));
			temp = true;
		}
		previousDir = direction;
		
	}

}
