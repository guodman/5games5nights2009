package org.guodman.smashatronwars;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Projectile {
	public static final int SIZE = 3;
	public float x, y;
	/**
	 * Direction is in Radians
	 */
	public float direction;
	public float speed;
	public boolean dead = false;

	public Projectile(float x,float y, float direction, float speed) {
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.speed = speed;
	}
	
	public void update(GameContainer c, int delta) {
		x+= Math.sin(direction)*(float)SmashatronWarsGame.SPEED*speed;
		y+= Math.cos(direction)*(float)SmashatronWarsGame.SPEED*speed;
		for (Enemy e : SmashatronWarsGame.me.enemies) {
			float dx = Math.abs(x-(e.x+e.SIZE/2));
			float dy = Math.abs(y-(e.y+e.SIZE/2));
			if (Math.sqrt((dx*dx)+(dy*dy)) < (SIZE/2 + Enemy.SIZE/2) && !e.dead && !dead) {
				e.dead = true;
				dead = true;
			}
		}
	}
	public void render(GameContainer c, Graphics g) {
		g.drawOval(x,y,SIZE,SIZE);
	}


}
