package org.guodman.stabRoom;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Monster {
	private StabRoom sr;
	private float x;
	private float y;
	private int width = 20;
	private int height = 20;
	private float speed = 0.2f;

	public Monster(float xPos, float yPos, StabRoom sr) {
		x = xPos;
		y = yPos;
		this.sr = sr;
	}

	public void render(GameContainer container, Graphics g) {
		g.drawOval((int) x - width / 2, (int) y - height / 2, width, height);
	}

	public void update(GameContainer container, int delta) {
		float xdiff = sr.x - x;
		float ydiff = sr.y - y;
		float theta = (float) Math.atan(xdiff / ydiff);
		if (sr.y > y) {
			x += speed * Math.sin(theta);
			y += speed * Math.cos(theta);
		} else {
			x -= speed * Math.sin(theta);
			y -= speed * Math.cos(theta);
		}
	}
	
	public float getDistance() {
		System.out.println("Distance: sqrt("+sr.x+"-"+x+"^2 + "+sr.y+"-"+y+"^2");
		int xdiff=(int) (sr.x-x);
		int ydiff=(int) (sr.y-y);
		System.out.println("Differences: dx="+xdiff+" dy="+ydiff);
		return (float) Math.sqrt(xdiff*xdiff+ydiff*ydiff);
	}
}
