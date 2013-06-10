package me.Xtreme.NightKings.Entity;


import me.Xtreme.NightKings.Game.Main;
import me.Xtreme.NightKings.Graphics.Screen;
import me.Xtreme.NightKings.Sprites.Sprite;
import me.Xtreme.NightKings.Input.Keyboard;
import me.Xtreme.NightKings.Input.Mouse;



public class Player extends Mob{

	private Keyboard input;
	private Sprite sprite;
	private int anim = 0;
	private boolean walking = false;

	public Player(Keyboard input) {
		this.input = input;
		sprite = Sprite.player_forward;
	}

	public Player(int x, int y, Keyboard input) {
		this.x = x;
		this.y = y;
		this.input = input;
	}
	


	public void update() {
		int xa = 0, ya = 0;
		if (anim > 7500) anim = 0;
		else anim++;
		if (input.up) ya--;
		if (input.down) ya++;
		if (input.left) xa--;
		if (input.right) xa++;
		if (xa != 0 || ya != 0) 
		{
			move(xa, ya);
			walking = true;
		} else {
			walking = false;
		}
		
		updateshooting();
	}

	private void updateshooting() {
		
		if(Mouse.getButton() == 1){
			double dx = Mouse.getX() - Main.getWindowWidth() / 2;
			double dy = Mouse.getY() - Main.getWindowHeight() / 2;
			double dir = Math.atan2(dy, dx);
			Math.atan(dy / dx);
			
			shoot(x,y,dir);
		}
	}


	public void render(Screen screen) {
		int flip = 0;
		if (dir == 0) {
			sprite = Sprite.player_forward;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.player_forward_1;
				}
				else
				{
					sprite = Sprite.player_forward_2;
				}
			}
		}
		if (dir == 1) {
			sprite = Sprite.player_side;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.player_side_1;
				} else {
					sprite = Sprite.player_side_2;
				}
			}
		}
		if (dir == 2) {
			sprite = Sprite.player_backward;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.player_backward_1;
				} else {
					sprite = Sprite.player_backward_2;
				}
			}
		}
		if (dir == 3) {
			sprite = Sprite.player_side;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.player_side_1;
				} else {
					sprite = Sprite.player_side_2;
				}
			}
			flip = 1;
		}
		screen.renderPlayer(x - 16, y - 16, sprite, flip);
	}

}