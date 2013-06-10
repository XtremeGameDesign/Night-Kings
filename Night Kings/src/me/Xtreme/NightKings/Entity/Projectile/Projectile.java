package me.Xtreme.NightKings.Entity.Projectile;

import me.Xtreme.NightKings.Entity.Entity;
import me.Xtreme.NightKings.Sprites.Sprite;

public abstract class Projectile extends Entity {
	
	protected final int xorigin, yorigin;
	protected double angle;
	protected Sprite sprite;
	protected double nx, ny;
	protected double speed, rateOfFire, range, damage;
	
	
	public Projectile(int x, int y, double dir) {
		xorigin = x;
		yorigin = y;
		angle = dir;
		this.x = x;
		this.y = y;
	}
	
	protected void move(){
	
	}
}
