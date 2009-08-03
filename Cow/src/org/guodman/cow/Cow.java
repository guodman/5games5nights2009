package org.guodman.cow;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Cow {
	public int location;
	public int conveyor;
	
	public Cow(int conv, int loc) {
		location = loc;
		conveyor = conv;
	}

	public void init(GameContainer container) {
		
	}

	public void update(GameContainer container, int delta) {}

	public void render(GameContainer container, Graphics g) {
		g.drawString("COW",
				location*CowGame.tileSize+CowGame.CONVEYOR_OFFSET_X,
				conveyor*CowGame.tileSize+CowGame.CONVEYOR_OFFSET_Y);
	}
}
