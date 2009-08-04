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
		g.drawOval(x,y,width, height);
		g.setColor(Color.blue);
		g.fillOval(x,y,width,height);
	}

	public void update(Controller joystick, int delta) {
		if (joystick != null) {
			float x1, y1, x2, y2;
			switch (joystick.getAxisCount()) {
			// Dougbert controller
			case 4:
				x1 = joystick.getAxisValue(0);
				y1 = joystick.getAxisValue(1);
				x2 = joystick.getAxisValue(2);
				y2 = joystick.getAxisValue(3);
				break;

			// Xbox controller
			case 7:
				x1 = joystick.getAxisValue(1);
				y1 = joystick.getAxisValue(2);
				x2 = joystick.getAxisValue(4);
				y2 = joystick.getAxisValue(5);

				break;

			default:
				x1 = x2 = y1 = y2 = 0;
			}
			/**
			 * Sanitize close to zero values.
			 */
			if (Math.abs(x1) < .1)
				x1 = 0;
			if (Math.abs(x2) < .1)
				x2 = 0;
			if (Math.abs(y1) < .1)
				y1 = 0;
			if (Math.abs(y2) < .1)
				y2 = 0;

			x += x1 * SmashatronWarsGame.SPEED;
			y += y1 *SmashatronWarsGame. SPEED;

			/**
			 * Sanitize board values
			 */
			if (x < 0)
				x = 0;
			if (y < 0)
				y = 0;
			if (y > (SmashatronWarsGame.HEIGHT-width))
				y = (SmashatronWarsGame.HEIGHT-width);
			if (x > (SmashatronWarsGame.WIDTH-height))
				x = (SmashatronWarsGame.WIDTH-height);
		}
		
	}
}
