package org.guodman.smashatronwars;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Enemy {
	public static final int SIZE = 30;
	public static final float SPEED = 0.2f;
	public float x = 0;
	public float y = 0;
	public int strength = 1;
	
	public Enemy(int str) {
		this(str, 0, 0);
	}
	
	public Enemy(int str, int x, int y) {
		strength = str;
		this.x = x;
		this.y = y;
	}
	
	public void init(GameContainer container) {
		
	}
	
	public void update(GameContainer container, int delta) {
		float xdiff = SmashatronWarsGame.me.player.x - x;
		float ydiff = SmashatronWarsGame.me.player.y - y;
		float theta = (float) Math.atan(xdiff / ydiff);
		if (SmashatronWarsGame.me.player.y > y) {
			x += SPEED * Math.sin(theta);
			y += SPEED * Math.cos(theta);
		} else {
			x -= SPEED * Math.sin(theta);
			y -= SPEED * Math.cos(theta);
		}
		float dx = Math.abs(x-SmashatronWarsGame.me.player.x);
		float dy = Math.abs(y-SmashatronWarsGame.me.player.y);
		if (Math.sqrt((dx*dx)+(dy*dy)) < (SIZE/2 + SmashatronWarsGame.me.player.height/2)) {
			SmashatronWarsGame.me.dead = true;
		}
	}
	
	public void render(GameContainer container, Graphics g) {
		g.setColor(Color.red);
		g.fillOval(x, y, 30, 30);
		g.setColor(Color.white);
		g.drawString("" + strength, x, y);
	}
}
