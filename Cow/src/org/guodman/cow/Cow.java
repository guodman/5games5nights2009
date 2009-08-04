package org.guodman.cow;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Cow {
	public int location;
	public int conveyor;
	public Image imageResource;
	
	public Cow(int conv, int loc) {
		location = loc;
		conveyor = conv;
		imageResource = CowGame.images.get(0);
	}

	public void init(GameContainer container) {
		
	}

	public void update(GameContainer container, int delta) {}

	public void render(GameContainer container, Graphics g) {
		g.drawString("COW",
				location*CowGame.tileSize+CowGame.CONVEYOR_OFFSET_X,
				conveyor*CowGame.tileSize+CowGame.CONVEYOR_OFFSET_Y);
		g.drawImage(imageResource, location*CowGame.tileSize+CowGame.CONVEYOR_OFFSET_X, conveyor*CowGame.tileSize+CowGame.CONVEYOR_OFFSET_Y);
	}
}
