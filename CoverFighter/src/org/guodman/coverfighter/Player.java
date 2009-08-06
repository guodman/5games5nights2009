package org.guodman.coverfighter;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Player {
	float mapx, mapy;
	int width, height;
	Image resource;
	/**
	 * 0 is down, 90 is right, 180 is up, -90 is left
	 */
	float previousDir = -90;

	public Player() {
		mapx = CoverFighterGame.MAPWIDTH / 2 - 15;
		mapy = CoverFighterGame.MAPHEIGHT / 2 - 15;

		width = 30;
		height = 20;
		resource = CoverFighterGame.images.get(0);
		resource.rotate(-90);
	}

	public void render(GameContainer c, Graphics g) {
		g.drawImage(resource, mapx - CoverFighterGame.screenXOffset, mapy
				- CoverFighterGame.screenYOffset);
	}

	public void update(float x1, float y1, float delta) {
		mapx += x1 * CoverFighterGame.SPEED * delta;
		mapy += y1 * CoverFighterGame.SPEED * delta;

		/**
		 * Sanitize board values
		 */
		if (mapx < 0)
			mapx = 0;
		if (mapy < 0)
			mapy = 0;
		if (mapy > (CoverFighterGame.MAPHEIGHT - width))
			mapy = (CoverFighterGame.MAPHEIGHT - width);
		if (mapx > (CoverFighterGame.MAPWIDTH - height))
			mapx = (CoverFighterGame.MAPWIDTH - height);

		/**
		 * Handle screen motion
		 */
		int scrollBuffer = 350;
		float screenx, screeny;
		screenx = CoverFighterGame.screenXOffset;
		screeny = CoverFighterGame.screenYOffset;
		if (screenx > 0 && (mapx - screenx) < scrollBuffer) {
			CoverFighterGame.screenXOffset -= delta*CoverFighterGame.SPEED;
		}
		if (screenx < (CoverFighterGame.MAPWIDTH - CoverFighterGame.SCREENWIDTH)
				&& ((screenx + CoverFighterGame.SCREENWIDTH) - mapx < scrollBuffer)) {
			CoverFighterGame.screenXOffset += delta *CoverFighterGame.SPEED;
		}
		if (screeny > 0 && (mapy - screeny) < scrollBuffer) {
			CoverFighterGame.screenYOffset -= delta *CoverFighterGame.SPEED;
		}
		if (screeny < (CoverFighterGame.MAPHEIGHT - CoverFighterGame.SCREENHEIGHT)
				&& ((screeny + CoverFighterGame.SCREENHEIGHT) - mapy < scrollBuffer)) {
			CoverFighterGame.screenYOffset += delta *CoverFighterGame.SPEED;
		}
	}

	public void setRotation(float direction) {
		// if(!temp) {
		float newDir = (float) (direction / Math.PI * 180);
		// System.out.println("Moving to direction" + newDir);

		float rotation = (previousDir - newDir);
		// System.out.println("Rotating by " + rotation);
		if (rotation != 0) {
			resource.rotate(rotation);
			System.out.println("Rotating by " + rotation);
		}
		// }
		previousDir = newDir;
		System.out.println("New direction is " + previousDir);

	}

	public float getGunX() {
		float result = (float) (width / 2f + Math
				.sin((360 - previousDir - 135f) / 180f * Math.PI) * 10f);
		System.out
				.println("Gun location is " + result + " and mapx is " + mapx);
		result += mapx;

		return result;

	}

	public float getGunY() {
		float result = (float) (height / 2f - Math
				.cos((360 - previousDir - 135f) / 180f * Math.PI) * 10f);
		System.out
				.println("Gun location is " + result + " and mapy is " + mapx);
		result += mapy;
		return result;

	}

	public Projectile makeProjectile(float x, float y) {
		return makeProjectile(CoverFighterGame.convertToRads(x, y));
	}

	public Projectile makeProjectile(float direction) {

		Projectile p = new Projectile(getGunX(), getGunY(), direction, 1);
		setRotation(direction);
		CoverFighterGame.me.projectiles.add(p);
		return p;
	}

}
