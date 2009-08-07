package org.guodman.multifighter;

import net.phys2d.math.ROVector2f;
import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.BodyList;
import net.phys2d.raw.StaticBody;
import net.phys2d.raw.World;
import net.phys2d.raw.shapes.Box;
import net.phys2d.raw.shapes.Circle;
import net.phys2d.raw.shapes.Line;
import net.phys2d.raw.shapes.Polygon;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class MultiFighterGame extends BasicGame {
	public static final int SCREENWIDTH = 1024;
	public static final int SCREENHEIGHT = 768;
	public static int gameClick = 0;
	public static Player player;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			AppGameContainer container = new AppGameContainer(
					new MultiFighterGame(), SCREENWIDTH, SCREENHEIGHT, false);
			container.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public World world;

	public boolean quit = false;

	public MultiFighterGame() {
		super("Multi Fighter");
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		container.setAlwaysRender(true);
		startGame();
	}

	public void startGame() {
		world = new World(new Vector2f(0, 0.01f), 10);
		StaticBody southBorder = new StaticBody(new Box(1000, 50));
		southBorder.setPosition(512, 700);
		world.add(southBorder);
		player = new Player();
		player.body.setPosition(200, 50);
		world.add(player.body);
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		if (quit) {
			container.exit();
		}
		player.update(container, delta);
		world.step(delta);
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		BodyList bl = world.getBodies();
		for (int i = 0; i < bl.size(); i++) {
			drawBody(g, bl.get(i), new Vector2f(0, 0)); // set the Vector2f for offset
		}
	}

	public void keyPressed(int key, char c) {
		// System.out.println("Someone pressed " + key);

		switch (key) {
		case Input.KEY_ESCAPE:
			quit = true;
			break;
		case Input.KEY_BACK:
			startGame();
			break;
		default:
			player.addAction(true,key);
			//player.body.setForce(0, -1);
			
		}
	}
	public void keyReleased(int key, char c) {
		player.addAction(false,key);
	}
	
	/*
	 * copied from mclib.slick.component.MapArea from protocombat on 8/6/2009
	 */
	/*
	 * copied from net.phys2d.raw.test.AbstractDemo.java from the phys2d project
	 * on 5-05-2007
	 */
	/*
	 * Phys2D - a 2D physics engine based on the work of Erin Catto. The
	 * original source remains:
	 * 
	 * Copyright (c) 2006 Erin Catto http://www.gphysics.com
	 * 
	 * This source is provided under the terms of the BSD License.
	 * 
	 * Copyright (c) 2006, Phys2D All rights reserved.
	 * 
	 * Redistribution and use in source and binary forms, with or without
	 * modification, are permitted provided that the following conditions are
	 * met: Redistributions of source code must retain the above copyright
	 * notice, this list of conditions and the following disclaimer.
	 * Redistributions in binary form must reproduce the above copyright notice,
	 * this list of conditions and the following disclaimer in the documentation
	 * and/or other materials provided with the distribution. Neither the name
	 * of the Phys2D/New Dawn Software nor the names of its contributors may be
	 * used to endorse or promote products derived from this software without
	 * specific prior written permission.
	 * 
	 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
	 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
	 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
	 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
	 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
	 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
	 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
	 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
	 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
	 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
	 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
	 */
	/*
	 * all methods have been made private and everything having to do with the
	 * offset variable has been modified by Doug Reeves on 5-05-2007
	 */

	/**
	 * Draw a body
	 * 
	 * @param g
	 *            The graphics contact on which to draw
	 * @param body
	 *            The body to be drawn
	 */
	private void drawBody(Graphics g, Body body, Vector2f offset) {
		if (body.getShape() instanceof Box) {
			drawBoxBody(g, body, (Box) body.getShape(), offset);
		}
		if (body.getShape() instanceof Circle) {
			drawCircleBody(g, body, (Circle) body.getShape(), offset);
		}
		if (body.getShape() instanceof Line) {
			drawLineBody(g, body, (Line) body.getShape(), offset);
		}
		if (body.getShape() instanceof Polygon) {
			drawPolygonBody(g, body, (Polygon) body.getShape(), offset);
		}
	}

	/**
	 * Draw a polygon into the demo
	 * 
	 * @param g
	 *            The graphics to draw the poly onto
	 * @param body
	 *            The body describing the poly's position
	 * @param poly
	 *            The poly to be drawn
	 */
	private void drawPolygonBody(Graphics g, Body body, Polygon poly,
			Vector2f offset) {
		g.setColor(Color.magenta); // changed color to magenta by Doug Reeves,
		// 5-05-2007

		ROVector2f[] verts = poly.getVertices(body.getPosition(), body
				.getRotation());
		for (int i = 0, j = verts.length - 1; i < verts.length; j = i, i++) {
			Vector2f shifti = new Vector2f(verts[i]);
			shifti.add(offset);
			Vector2f shiftj = new Vector2f(verts[j]);
			shiftj.add(offset);
			g.drawLine((int) (0.5f + shifti.getX()), (int) (0.5f + shifti
					.getY()), (int) (0.5f + shiftj.getX()),
					(int) (0.5f + shiftj.getY()));
		}
	}

	/**
	 * Draw a line into the demo
	 * 
	 * @param g
	 *            The graphics to draw the line onto
	 * @param body
	 *            The body describing the line's position
	 * @param line
	 *            The line to be drawn
	 */
	private void drawLineBody(Graphics g, Body body, Line line, Vector2f offset) {
		g.setColor(Color.magenta); // changed color to magenta by Doug Reeves,
		// 5-05-2007
		Vector2f[] verts = line.getVertices(body.getPosition(), body
				.getRotation());
		Vector2f shift0 = new Vector2f(verts[0]);
		shift0.add(offset);
		Vector2f shift1 = new Vector2f(verts[1]);
		shift1.add(offset);
		g.drawLine((int) shift0.getX(), (int) shift0.getY(), (int) shift1
				.getX(), (int) shift1.getY());
	}

	/**
	 * Draw a circle in the world
	 * 
	 * @param g
	 *            The graphics contact on which to draw
	 * @param body
	 *            The body to be drawn
	 * @param circle
	 *            The shape to be drawn
	 */
	private void drawCircleBody(Graphics g, Body body, Circle circle,
			Vector2f offset) {
		g.setColor(Color.magenta); // changed color to magenta by Doug Reeves,
		// 5-05-2007
		float x = body.getPosition().getX() + offset.getX();
		float y = body.getPosition().getY() + offset.getY();
		float r = circle.getRadius();
		float rot = body.getRotation();
		float xo = (float) (Math.cos(rot) * r);
		float yo = (float) (Math.sin(rot) * r);

		g.drawOval((int) (x - r), (int) (y - r), (int) (r * 2), (int) (r * 2));
		g.drawLine((int) x, (int) y, (int) (x + xo), (int) (y + yo));
	}

	/**
	 * Draw a box in the world
	 * 
	 * @param g
	 *            The graphics contact on which to draw
	 * @param body
	 *            The body to be drawn
	 * @param box
	 *            The shape to be drawn
	 */
	private void drawBoxBody(Graphics g, Body body, Box box, Vector2f offset) {
		Vector2f[] pts = box.getPoints(body.getPosition(), body.getRotation());

		Vector2f v1 = pts[0];
		Vector2f v2 = pts[1];
		Vector2f v3 = pts[2];
		Vector2f v4 = pts[3];

		v1.add(offset);
		v2.add(offset);
		v3.add(offset);
		v4.add(offset);

		g.setColor(Color.magenta); // changed color to magenta by Doug Reeves,
		// 5-05-2007
		g.drawLine((int) v1.x, (int) v1.y, (int) v2.x, (int) v2.y);
		g.drawLine((int) v2.x, (int) v2.y, (int) v3.x, (int) v3.y);
		g.drawLine((int) v3.x, (int) v3.y, (int) v4.x, (int) v4.y);
		g.drawLine((int) v4.x, (int) v4.y, (int) v1.x, (int) v1.y);
	}
	/*
	 * end copied from net.phys2d.raw.test.AbstractDemo.java from the phys2d
	 * project on 5-05-2007
	 */
	/*
	 * end copied from mclib.slick.component.MapArea from protocombat on 8/6/2009
	 */
}
