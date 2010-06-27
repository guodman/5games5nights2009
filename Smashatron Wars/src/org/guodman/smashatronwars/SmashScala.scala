package org.guodman.smashatronwars

import java.util.ArrayList

import org.lwjgl.input.Controller
import org.lwjgl.input.Controllers
import org.newdawn.slick.AppGameContainer
import org.newdawn.slick.BasicGame
import org.newdawn.slick.GameContainer
import org.newdawn.slick.Graphics
import org.newdawn.slick.Image
import org.newdawn.slick.Input
import org.newdawn.slick.SlickException

object SmashScala extends BasicGame("Smashatron Wars") {
	val WIDTH : Int = 1024;
	val HEIGHT : Int = 768
	val SPEED : Float = 0.8f
	val ENEMY_DEPLOY_INCREMENT : Int = 1000
	var quit : Boolean = false
	var player : Player = null
	var projectiles : List[Projectile] = List()
	var images : List[Image] = List()
	var pistolButton : Int = 0
	var machineButton : Int = 0
	var shottyButton : Int = 0
	
	var joystick : Controller = null;
	var enemies : List[Enemy] = List()
	var enemyTime : Int = ENEMY_DEPLOY_INCREMENT
	var dead : Boolean = false
	var score : Int = 0
	var pistol : Weapon = new Weapon.Pistol()
	var machine : Weapon = new Weapon.Machine()
	var shotty : Weapon = new Weapon.Shotty()
	var myWeapon : Weapon = pistol
	
	def main(args: Array[String]): Unit = {
			val container : AppGameContainer = new AppGameContainer(
					new SmashatronWarsGame(), WIDTH, HEIGHT, false);
			container.start()
	}
	
	def init(container : GameContainer) = {
		container.setAlwaysRender(true);
		startGame(); // throws SlickException
	}
	
	def startGame() = {
		projectiles = List()
		images = List()
		enemies = List()
		images = new Image("/resources/shooter.png") :: images
		try {
			Controllers.create();
			if (Controllers.getControllerCount() > 0) {
				joystick = Controllers.getController(0);
				System.out.println("Joystick Name: " + joystick.getName());
				System.out.println("Joystick has " + joystick.getButtonCount()
						+ " buttons. Its name is " + joystick.getName());
			} else
				joystick = null;
		} catch { case e : org.lwjgl.LWJGLException =>
			System.err.println("Couldn't initialize Controllers: "
					+ e.getMessage());
		}
		player = new Player(images.head);
		dead = false;
		pistol = new Weapon.Pistol();
		machine = new Weapon.Machine();
		shotty = new Weapon.Shotty();
		myWeapon = shotty;
	}
	
	def render(container : GameContainer, g : Graphics) = {
		for (e : Enemy <- enemies) {
			e.render(container, g)
		}
		player.render(container, g)
		for (p : Projectile <- projectiles) {
			p.render(container, g)
		}
		g.drawString("Score: " + score, 10, 25)
		g.drawString("Pistol Ammo: Infinite", 10, 40)
		g.drawString("Shotgun Ammo: " + shotty.ammo, 10, 55)
		g.drawString("Machine Gun Ammo: " + machine.ammo, 10, 70)
		g.drawString("Number of Enemies: " + enemies.length, 10, 85)
	}
	
	def update(container : GameContainer, delta : Int) = {
		if (quit) {
			container.exit();
		}

		if (!dead) {
			var (x1, y1, x2, y2) = (0f, 0f, 0f, 0f)

			if (joystick != null) {

				joystick.getAxisCount() match {
				// Dougbert controller
				case 4 =>
					x1 = joystick.getAxisValue(0);
					y1 = joystick.getAxisValue(1);
					x2 = joystick.getAxisValue(2);
					y2 = joystick.getAxisValue(3);
					pistolButton = 1;
					shottyButton = 2;
					machineButton = 3;
				// Xbox controller
				case 7 =>
					x1 = joystick.getAxisValue(1);
					y1 = joystick.getAxisValue(2);
					x2 = joystick.getAxisValue(4);
					y2 = joystick.getAxisValue(5);
					pistolButton = 2;
					shottyButton = 3;
					machineButton = 4;
				}
				/**
				 * Sanitize close to zero values.
				 */
				var tolerance : Double = 0.14;
				if (Math.abs(x1) < tolerance)
					x1 = 0;
				if (Math.abs(x2) < tolerance)
					x2 = 0;
				if (Math.abs(y1) < tolerance)
					y1 = 0;
				if (Math.abs(y2) < tolerance)
					y2 = 0;

				player.update(x1, y1, delta);
				if (myWeapon.reloadStatus > 0) {
					myWeapon.reloadStatus -= delta;
				}
				myWeapon.fire(x2, y2, delta);
			}

			// add new enemies
			enemyTime -= delta;
			if (enemyTime < 0) {
				enemyTime += ENEMY_DEPLOY_INCREMENT;
				new Enemy(3, WIDTH / 2, 0) :: enemies
				new Enemy(3, WIDTH / 2, HEIGHT - Enemy.SIZE) :: enemies
				new Enemy(3, 0, HEIGHT / 2) :: enemies
				new Enemy(3, WIDTH - Enemy.SIZE, HEIGHT / 2) :: enemies
			}

			for (e : Enemy <- enemies) {
				e.update(container, delta);
			}
			for (p : Projectile <- projectiles) {
				p.update(container, delta);
			}

			// Remove projectiles that have left the screen.
			projectiles.filter(projectileFilter)
			enemies.filter(enemyFilter)
		}
	}
	
	def projectileFilter(p : Projectile) : Boolean = {
		return p.x < 0 || p.y < 0 || p.x > WIDTH || p.y > HEIGHT || p.dead
	}
	
	def enemyFilter(e : Enemy) : Boolean = {
		return e.dead
	}
	
	override def keyPressed(key : Int, c : Char) = {
		// System.out.println("Someone pressed " + key);

		key match {
		case Input.KEY_ESCAPE =>
			quit = true;
		case Input.KEY_SPACE =>
			try {
				startGame();
			} catch {
				case e : SlickException =>
					e.printStackTrace();
			}
		}
	}
	
	override def controllerButtonReleased(controller : Int, button : Int) = {
		if (button == pistolButton) {
			myWeapon = pistol;
		} else if (button == shottyButton) {
			if (myWeapon != shotty) {
				myWeapon = shotty;
			} else {
				if (score >= 10) {
					shotty.ammo += 10;
					score -= 10;
				}
			}
		} else if (button == machineButton) {
			if (myWeapon != machine) {
				myWeapon = machine;
			} else {
				if (score >= 10) {
					machine.ammo += 50;
					score -= 10;
				}
			}
		}
	}
}