package com.mortalpowers.games.line;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public abstract class Renderable {
	int x,y;
	abstract void render(GameContainer container, Graphics g);
}
