package org.guodman.coverfighter;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Projectile {
	public static final int SIZE = 3;
	public float mapx, mapy;
	/**
	 * Direction is in Radians
	 */
	public float direction;
	public float speed;
	public static final int WALL_LIFE = 1100;
	public float life = 1250;
	public boolean dead = false;
	public List nohits = new ArrayList();

	public Projectile(float x,float y, float direction, float speed) {
		this.mapx = x;
		this.mapy = y;
		this.direction = direction;
		this.speed = speed;
	}
	
	public void update(GameContainer c, int delta) {
		mapx+= Math.sin(direction)*(float)CoverFighterGame.SPEED*speed*delta;
		mapy+= Math.cos(direction)*(float)CoverFighterGame.SPEED*speed*delta;
		for (Enemy e : CoverFighterGame.me.enemies) {
			float dx = Math.abs(mapx-(e.mapx+e.SIZE/2));
			float dy = Math.abs(mapy-(e.mapy+e.SIZE/2));
			if (Math.sqrt((dx*dx)+(dy*dy)) < (SIZE/2 + Enemy.SIZE/2) && !e.dead && !dead && !nohits.contains(e)) {
				e.dead = true;
				dead = true;
			}
		}
		for (Cover co : CoverFighterGame.me.covers) {
			if (co.containsPoint((int)mapx, (int)mapy) && life < WALL_LIFE) {
				dead = true;
			}
		}
		life -= ((float)delta)*speed;
		if (life < 0) {
			dead = true;
		}
	}
	public void render(GameContainer c, Graphics g) {
		g.drawOval(mapx-CoverFighterGame.screenXOffset,mapy-CoverFighterGame.screenYOffset,SIZE,SIZE);
	}


}
