package org.guodman.smashatronwars;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Projectile {
	public float x, y;
	public float direction;
	public float speed;

	public Projectile(float x,float y, float direction, float speed) {
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.speed = speed;
	}
	
	public void update(GameContainer c, int delta) {
		x+= Math.sin(direction)*(float)SmashatronWarsGame.SPEED*speed;
		y+= Math.cos(direction)*(float)SmashatronWarsGame.SPEED*speed;
		//System.out.println("updating a projectile. direction is " + direction + "speed is " + speed);
	}
	public void render(GameContainer c, Graphics g) {
		g.drawOval(x,y,3,3);
	}
}
