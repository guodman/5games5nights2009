package org.guodman.smashatronwars;

public abstract class Weapon {
	public final int fireRate;
	public int reloadStatus;
	public int ammo;

	public Weapon(final int fireRate) {
		this.fireRate = fireRate;
		reloadStatus = fireRate;
	}

	public abstract void fire(float x, float y, int delta);

	public static class Pistol extends Weapon {
		public Pistol() {
			super(250);
			ammo = 0;
		}

		@Override
		public void fire(float x, float y, int delta) {
			if (reloadStatus <= 0 && (x != 0 || y != 0)) {
				reloadStatus += fireRate;
				Projectile p = new Projectile(
						SmashatronWarsGame.me.player.getGunX(),
						SmashatronWarsGame.me.player.getGunY(), (float) Math.atan(x / y), 1);
				if (y < 0) {
					p.direction += Math.PI;
				}
				SmashatronWarsGame.me.player.setRotation(p.direction);
				SmashatronWarsGame.me.projectiles.add(p);
			}
		}
	}

	public static class Machine extends Weapon {
		public Machine() {
			super(25);
			ammo = 1000;
		}

		@Override
		public void fire(float x, float y, int delta) {
			if (reloadStatus <= 0 && (x != 0 || y != 0)) {
				reloadStatus += fireRate;
				Projectile p = new Projectile(
						SmashatronWarsGame.me.player.getGunX(),
						SmashatronWarsGame.me.player.getGunY(), (float) Math.atan(x / y), 1);
				if (y < 0) {
					p.direction += Math.PI;
				}
				SmashatronWarsGame.me.player.setRotation(p.direction);
				SmashatronWarsGame.me.projectiles.add(p);
				ammo -= 1;
			}
		}
	}

	public static class Shotty extends Weapon {
		public Shotty() {
			super(250);
		}

		@Override
		public void fire(float x, float y, int delta) {
			if (reloadStatus <= 0 && (x != 0 || y != 0)) {
				for (int i = 0; i < 8; i++) {
					reloadStatus += fireRate;
					Projectile p = new Projectile(
							SmashatronWarsGame.me.player.getGunX(),
							SmashatronWarsGame.me.player.getGunY(), (float) Math.atan(x / y), 1);
					if (y < 0) {
						p.direction += Math.PI;
					}
					p.direction += Math.random()*Math.PI/12 - Math.PI/6;
					SmashatronWarsGame.me.player.setRotation(p.direction);
					SmashatronWarsGame.me.projectiles.add(p);
				}
			}
		}
	}
}
