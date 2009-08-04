package org.guodman.smashatronwars;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Projectile {
	public float x, y;
	public float direction;
	public float speed;

	public void update(GameContainer c, int delta) {
		x+= Math.cos(direction/180*Math.PI)*SmashatronWarsGame.SPEED*speed;
		y-= Math.cos(direction/180*Math.PI)*SmashatronWarsGame.SPEED*speed;
	}
	public void render(GameContainer c, Graphics g) {
		g.drawOval(x,y,3,3);
	}
}
