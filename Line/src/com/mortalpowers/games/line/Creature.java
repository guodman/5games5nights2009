package com.mortalpowers.games.line;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Creature extends Renderable {
	boolean female = true;
	String name = "";
	public Creature() {
		name = "Creature " + Math.round(Math.random()*10000);
	}
	@Override
	void render(GameContainer container, Graphics g) {
		g.drawOval(x,y,30,30);
		
	}
}
