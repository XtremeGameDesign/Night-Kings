package me.Xtreme.NightKings.Sprites.Tiles;


	import me.Xtreme.NightKings.Graphics.Screen;
import me.Xtreme.NightKings.Sprites.Sprite;
import me.Xtreme.NightKings.Sprites.Tile;


	
	public class RockTile extends Tile {

		public RockTile(Sprite sprite) {
			super(sprite);
		}

		public void render(int x, int y, Screen screen) {
			screen.renderTile(x << 4, y << 4, this);
		}

		public boolean solid() {
			return true;
		}

	}
