package org.guodman.coverfighter;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Enemy {
	public static final int SIZE = 30;
	public static final float SPEED = 0.1f;
	public int fireRate = 500;
	public int reload;
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
		//TODO: this might not be actually working
		reload = (int) (Math.random()*(float)fireRate);
	}
	
	public void init(GameContainer container) {
		
	}
	
	public void fire() {
		float xdiff = CoverFighterGame.me.player.x - x;
		float ydiff = CoverFighterGame.me.player.y - y;
		float theta = (float) Math.atan(xdiff / ydiff);
		Projectile p = new Projectile(x+SIZE/2, y+SIZE/2, CoverFighterGame.convertToRads(xdiff, ydiff), SPEED*3);
		p.nohits.add(this);
		CoverFighterGame.me.projectiles.add(p);
	}
	
	public void update(GameContainer container, int delta) {
		reload -= delta;
		if (reload < 0) {
			reload += fireRate;
			fire();
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
