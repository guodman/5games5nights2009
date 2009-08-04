package org.guodman.smashatronwars;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Enemy {
	public static final float speed = 0.2f;
	public float x = 0;
	public float y = 0;
	public int strength = 1;
	
	public Enemy(int str) {
		strength = str;
	}
	
	public void init(GameContainer container) {
		
	}
	
	public void update(GameContainer container, int delta) {
		float xdiff = SmashatronWarsGame.me.player.x - x;
		float ydiff = SmashatronWarsGame.me.player.y - y;
		float theta = (float) Math.atan(xdiff / ydiff);
		if (SmashatronWarsGame.me.player.y > y) {
			x += speed * Math.sin(theta);
			y += speed * Math.cos(theta);
		} else {
			x -= speed * Math.sin(theta);
			y -= speed * Math.cos(theta);
		}
	}
	
	public void render(GameContainer container, Graphics g) {
		g.setColor(Color.red);
		g.fillOval(x, y, 30, 30);
		g.setColor(Color.white);
		g.drawString("" + strength, x, y);
	}
}