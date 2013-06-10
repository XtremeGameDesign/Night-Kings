package me.Xtreme.NightKings.Sprites.Tiles;


	import me.Xtreme.NightKings.Graphics.Screen;
import me.Xtreme.NightKings.Sprites.Sprite;
import me.Xtreme.NightKings.Sprites.Tile;

	public class FlowerTile extends Tile {

		public FlowerTile(Sprite sprite) {
			super(sprite);
		}

		public void render(int x, int y, Screen screen) {
			screen.renderTile(x << 4, y << 4, this);
		}
		public boolean solid() {
			return false;
		}
		
		public boolean breakable() {
			return true;
		}
	}
