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
				SmashatronWarsGame.me.player.makeProjectile(x, y);
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
			if (reloadStatus <= 0 && ammo > 0 && (x != 0 || y != 0)) {
				reloadStatus += fireRate;
				SmashatronWarsGame.me.player.makeProjectile(x, y);
				ammo -= 1;
			}
		}
	}

	public static class Shotty extends Weapon {
		public Shotty() {
			super(150);
			ammo = 50;
		}

		@Override
		public void fire(float x, float y, int delta) {
			if (reloadStatus <= 0 && ammo > 0 && (x != 0 || y != 0)) {
				reloadStatus += fireRate;
				for (int i = 0; i < 8; i++) {
					SmashatronWarsGame.me.player.makeProjectile(x, y).direction += Math.random()*Math.PI/12 - Math.PI/6;
				}
				ammo -= 1;
			}
		}
	}
}
