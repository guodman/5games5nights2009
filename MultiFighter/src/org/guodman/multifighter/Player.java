package org.guodman.multifighter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.shapes.Box;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

public class Player {
	/** Store time in replay friendly increments of 1000/33 or 30ms per tick. */
	int timeCounter = 0;
	/**
	 * Number of 1000s of seconds in a tick.
	 */
	int tickSize = 30;
	int startx;
	int starty;
	public Body body;
	private float damping = 0.007f;
	private float rotationDamping = 20f;
	int id;

	ArrayList<Action> actionQueue = new ArrayList<Action>();
	HashMap<Integer, ArrayList<Action>> savedActions = new HashMap<Integer, ArrayList<Action>>();

	public Player(final int x, final int y) {
		this(x,y,new Box(30,50),1f);
		System.out.println("Incremented player count from " + MultiFighterGame.playerCount);
		id = MultiFighterGame.playerCount++;
		System.out.println("Incremented player count to " + MultiFighterGame.playerCount);
	}
	public Player(final int x,final int y,Box b, float mass) {
		body = new Body(b, mass);
		body.setDamping(damping);
		body.setRotDamping(rotationDamping);
		body.setRestitution(1f);
		body.setUserData(this);
		this.startx = x;
		this.starty = y;
		body.setPosition(x, y);
	}

	/**
	 * This happens after delta time passes.
	 */
	public void update(GameContainer c, int delta) {
		timeCounter += delta;
		while (timeCounter > tickSize) {
			// System.out.println("click");
			MultiFighterGame.gameClick++;
			timeCounter -= tickSize;
			if (actionQueue.size() > 0) {
				flushSavedActions();
			}
		}

	}

	/**
	 * Run and store all of the actions since the last tick.
	 */
	private void flushSavedActions() {
		while (actionQueue.size() > 0) {
			
			Action performed = actionQueue.remove(0);
			ArrayList<Action> thisClick = savedActions.get(new Integer(
					MultiFighterGame.gameClick));

			if (thisClick == null) {
				ArrayList<Action> newClick = new ArrayList<Action>();
				newClick.add(performed);
				savedActions.put(new Integer(MultiFighterGame.gameClick),
						newClick);
			} else {
				thisClick.add(performed);
			}

			perform(performed);

		}

	}

	protected void perform(Action performed) {
		if (performed.start == true) {
			switch (performed.key) {
			case Input.KEY_UP:
				body.addForce(new Vector2f(0, -3));
				System.out.println("Applying force up to player " + id + ".");
				break;
			case Input.KEY_RIGHT:
				body.addForce(new Vector2f(3, 0));
				break;
			case Input.KEY_LEFT:
				body.addForce(new Vector2f(-3,0));
				break;
			}
		}
		
	}

	public static class Action {
		public boolean start;
		public int key;

		public Action(boolean start, int key) {
			this.start = start;
			this.key = key;
		}
	}

	public void addAction(Action a) {
		actionQueue.add(a);
	}

	public void addAction(boolean down, int key) {
		addAction(new Action(down, key));
	}
}
