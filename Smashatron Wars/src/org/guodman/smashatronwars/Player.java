package org.guodman.smashatronwars;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Player {
	float x, y;
	int width, height;

	
	public Player() {
		x = SmashatronWarsGame.WIDTH / 2 - 15;
		y = SmashatronWarsGame.HEIGHT / 2 - 15;
		
		width = 30;
		height = 30;
	}
	
	public void render(GameContainer c, Graphics g) {
		g.drawOval(x,y,width, height);
		g.setColor(Color.blue);
		g.fillOval(x,y,width,height);
	}
}
