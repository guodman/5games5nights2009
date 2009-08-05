package org.guodman.coverfighter;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Player {
	float x, y;
	int width, height;
	Image resource;
	/**
	 * 0 is down, 90 is right, 180 is up, -90 is left
	 */
	float previousDir = -90;

	public Player() {
		x = CoverFighterGame.WIDTH / 2 - 15;
		y = CoverFighterGame.HEIGHT / 2 - 15;

		width = 30;
		height = 20;
		resource = CoverFighterGame.images.get(0);
		resource.rotate(-90);
	}

	public void render(GameContainer c, Graphics g) {
		g.drawImage(resource,x,y);
	}

	public void update(float x1, float y1, float delta) {
		x += x1 * CoverFighterGame.SPEED;
		y += y1 * CoverFighterGame.SPEED;

		/**
		 * Sanitize board values
		 */
		if (x < 0)
			x = 0;
		if (y < 0)
			y = 0;
		if (y > (CoverFighterGame.HEIGHT - width))
			y = (CoverFighterGame.HEIGHT - width);
		if (x > (CoverFighterGame.WIDTH - height))
			x = (CoverFighterGame.WIDTH - height);
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
		float result = (float) (width/2f+Math.sin((360-previousDir-135f)/180f*Math.PI)*10f);
		System.out.println("Gun location is " + result + " and x is " + x);
		result += x;
		
		return result;
		
	}
	public float getGunY() {
		float result = (float) (height/2f-Math.cos((360-previousDir-135f)/180f*Math.PI)*10f);
		System.out.println("Gun location is " + result + " and y is " + x);
		result += y;
		return result;
		
	}
	public Projectile makeProjectile(float x, float y) {
		return makeProjectile(CoverFighterGame.convertToRads(x, y));
	}
	public Projectile makeProjectile(float direction) {
		
		Projectile p = new Projectile(
				getGunX(),
				getGunY(), direction, 1);
		setRotation(direction);
		CoverFighterGame.me.projectiles.add(p);
		return p;
	}

}
