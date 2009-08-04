package org.guodman.cow;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.AppletGameContainer.Container;

public class Trap {
	float x, y;
	public String name;
	public Image imgResource;
	public int states;
	
	public Trap() {
		imgResource = CowGame.images.get(2);
	}
	
	public void update(GameContainer c, int delta) {
		
	}
	public void render(GameContainer c, Graphics g) {
		g.drawImage(imgResource, x, y);
	}
	
	
	public class Chute extends Trap {
		public Chute() {
			
		}
	}
	public class Magnet extends Trap {
		public Magnet() {
			
		}
	}
	public class Wall extends Trap {
		public Wall() {
			
		}
	}
	public class Mover extends Trap {
		public Mover() {
			
		}
	}
}
