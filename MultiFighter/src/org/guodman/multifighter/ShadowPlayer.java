package org.guodman.multifighter;

import java.util.ArrayList;

import net.phys2d.raw.shapes.Box;

import org.newdawn.slick.GameContainer;

public class ShadowPlayer extends Player {
	int lastshownClick = 0;

	public ShadowPlayer(Player p) {
		super(p.startx, p.starty, new Box(30, 50), 1f);
		this.savedActions = p.savedActions;
		this.id = p.id;

	}

	public void update(GameContainer c, int delta) {
		showPlayback();
	}

	public void showPlayback() {
		if (lastshownClick < MultiFighterGame.gameClick) {
			for (int i = lastshownClick + 1; i <= MultiFighterGame.gameClick; i++) {
				ArrayList<Action> actions = savedActions.get(i);
				if (actions != null) {
					for (Action a : actions) {
						perform(a);
					}
				}

			}
			lastshownClick = MultiFighterGame.gameClick;

		}
	}
}
