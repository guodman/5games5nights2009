package org.guodman.coverfighter;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Enemy {
	public static final int SIZE = 30;
	public static final float SPEED = 0.1f;
	public int fireRate = 500;
	public int reload;
	public float mapx = 0;
	public float mapy = 0;
	public int strength = 1;
	public boolean dead = false;
	
	public Enemy(int str) {
		this(str, 0, 0);
	}
	
	public Enemy(int str, int x, int y) {
		strength = str;
		this.mapx = x;
		this.mapy = y;
		//TODO: this might not be actually working
		reload = (int) (Math.random()*(float)fireRate);
	}
	
	public void init(GameContainer container) {
		
	}
	
	public void fire() {
		float xdiff = CoverFighterGame.me.player.mapx - mapx;
		float ydiff = CoverFighterGame.me.player.mapy - mapy;
		float theta = (float) Math.atan(xdiff / ydiff);
		Projectile p = new Projectile( mapx+SIZE/2, mapy+SIZE/2, CoverFighterGame.convertToRads(xdiff, ydiff), SPEED*3);
		p.nohits.add(this);
		CoverFighterGame.me.projectiles.add(p);
	}
	
	public void update(GameContainer container, int delta) {
		reload -= delta;
		if (reload < 0) {
			reload += fireRate;
			fire();
		}
		float dx = Math.abs(mapx-CoverFighterGame.me.player.mapx);
		float dy = Math.abs(mapy-CoverFighterGame.me.player.mapy);
		if (Math.sqrt((dx*dx)+(dy*dy)) < (SIZE/2 + CoverFighterGame.me.player.height/2)) {
			CoverFighterGame.me.dead = true;
		}
	}
	
	public void render(GameContainer container, Graphics g) {
		g.setColor(Color.red);
		g.fillOval(mapx, mapy, 30, 30);
		g.setColor(Color.white);
		g.drawString("" + strength, mapx, mapy);
	}
}
