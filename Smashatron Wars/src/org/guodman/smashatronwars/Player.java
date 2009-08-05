package org.guodman.smashatronwars;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Player {
	float x, y;
	int width, height;
	Image resource;
	float previousDir = -90;

	public Player() {
		x = SmashatronWarsGame.WIDTH / 2 - 15;
		y = SmashatronWarsGame.HEIGHT / 2 - 15;

		width = 30;
		height = 30;
		resource = SmashatronWarsGame.images.get(0);
		resource.rotate(-90);
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

	
	public void setRotation(float direction) {
		//if(!temp) {
			float newDir = (float) (direction/Math.PI*180);
			//System.out.println("Moving to direction" + newDir);
		
		float rotation =  (previousDir - newDir);
		//System.out.println("Rotating by " + rotation);
		if(rotation != 0) {
			resource.rotate(rotation);
			System.out.println("Rotating by " + rotation);
		}
		//}
		previousDir = newDir;
		System.out.println("New direction is " + previousDir);
		
	}
	public float getGunX() {
		return (float) (x+width/2+Math.sin(45/180*Math.PI)*15);
	}
	public float getGunY() {
		return (float) (y+width/2-Math.cos(45/180*Math.PI)*15);
		
	}

}
