package org.guodman.multifighter;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;

public class ShadowPlayer extends Player {
	int lastshownClick = 0;
	public  ShadowPlayer(Player p) {
		this.savedActions = p.savedActions;
		
	}
	public void update(GameContainer c, int delta) {
		showPlayback();
	}
	public void showPlayback() {
		if(lastshownClick < MultiFighterGame.gameClick) {
			for(int i = lastshownClick+1;i<=MultiFighterGame.gameClick;i++) {
				ArrayList<Action> actions = savedActions.get(i);
				for(Action a : actions) {
					perform(a);
				}
				
			}
			lastshownClick = MultiFighterGame.gameClick;
			
		}
	}
}
