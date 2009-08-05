package org.guodman.coverfighter;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Enemy {
	public static final int SIZE = 30;
	public static final float SPEED = 0.1f;
	public float x = 0;
	public float y = 0;
	public int strength = 1;
	public boolean dead = false;
	
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
		float xdiff = CoverFighterGame.me.player.x - x;
		float ydiff = CoverFighterGame.me.player.y - y;
		float theta = (float) Math.atan(xdiff / ydiff);
		if (CoverFighterGame.me.player.y > y) {
			x += SPEED * Math.sin(theta);
			y += SPEED * Math.cos(theta);
		} else {
			x -= SPEED * Math.sin(theta);
			y -= SPEED * Math.cos(theta);
		}
		float dx = Math.abs(x-CoverFighterGame.me.player.x);
		float dy = Math.abs(y-CoverFighterGame.me.player.y);
		if (Math.sqrt((dx*dx)+(dy*dy)) < (SIZE/2 + CoverFighterGame.me.player.height/2)) {
			CoverFighterGame.me.dead = true;
		}
	}
	
	public void render(GameContainer container, Graphics g) {
		g.setColor(Color.red);
		g.fillOval(x, y, 30, 30);
		g.setColor(Color.white);
		g.drawString("" + strength, x, y);
	}
}
