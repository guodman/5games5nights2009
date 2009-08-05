package org.guodman.smashatronwars;

import org.lwjgl.input.Controller;
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
		g.drawOval(x, y, width, height);
		g.setColor(Color.blue);
		g.fillOval(x, y, width, height);
	}

	public void update(float x1, float y1, float delta) {

		x += x1 * SmashatronWarsGame.SPEED;
		y += y1 * SmashatronWarsGame.SPEED;

		/**
		 * Sanitize board values
		 */
		if (x < 0)
			x = 0;
		if (y < 0)
			y = 0;
		if (y > (SmashatronWarsGame.HEIGHT - width))
			y = (SmashatronWarsGame.HEIGHT - width);
		if (x > (SmashatronWarsGame.WIDTH - height))
			x = (SmashatronWarsGame.WIDTH - height);
	}

}
