package com.mortalpowers.games.line;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Creature extends Renderable {
	boolean female = true;
	boolean player = false;
	String name = "";
	public Creature() {
		name = "Creature " + Math.round(Math.random()*10000);
	}
	@Override
	void render(GameContainer container, Graphics g) {
		g.drawOval(x,y,30,30);
		if(female) {
			g.setColor(Color.pink);
		} else {
			g.setColor(Color.blue);
		}
		if(player) {
			g.setColor(Color.green);
		}
		g.fillOval(x,y,30,30);
		
	}
}
