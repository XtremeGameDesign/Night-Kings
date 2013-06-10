package me.Xtreme.NightKings.Sprites;

import me.Xtreme.NightKings.Graphics.Screen;
import me.Xtreme.NightKings.Sprites.Sprite;
import me.Xtreme.NightKings.Sprites.Tiles.FlowerTile;
import me.Xtreme.NightKings.Sprites.Tiles.GrassTile;
import me.Xtreme.NightKings.Sprites.Tiles.RockTile;
import me.Xtreme.NightKings.Sprites.Tiles.voidTile;
import me.Xtreme.NightKings.Sprites.Tiles.SpawnLevel.SpawnFloorTile;
import me.Xtreme.NightKings.Sprites.Tiles.SpawnLevel.SpawnGrassTile;
import me.Xtreme.NightKings.Sprites.Tiles.SpawnLevel.SpawnHedgeTile;
import me.Xtreme.NightKings.Sprites.Tiles.SpawnLevel.SpawnWall1Tile;
import me.Xtreme.NightKings.Sprites.Tiles.SpawnLevel.SpawnWall2Tile;
import me.Xtreme.NightKings.Sprites.Tiles.SpawnLevel.SpawnWaterTile;


public class Tile {
    
    public int x ,y;
    public Sprite sprite;
   
    public static Tile grass = new GrassTile(Sprite.grass);
    public static Tile flower = new FlowerTile(Sprite.flower);
    public static Tile rock = new RockTile(Sprite.rock);
	
	public static Tile voidTile = new voidTile(Sprite.voidSprite);
	
	public static Tile spawn_Grass = new SpawnGrassTile(Sprite.spawn_grass);
	public static Tile spawn_hedge = new SpawnHedgeTile(Sprite.spawn_hedge);
	public static Tile spawn_water = new SpawnWaterTile (Sprite.spawn_water);
	public static Tile spawn_wall1 = new SpawnWall1Tile (Sprite.spawn_wall1);
	public static Tile spawn_wall2 = new SpawnWall2Tile (Sprite.spawn_wall2);
	public static Tile spawn_floor = new SpawnFloorTile (Sprite.spawn_floor);
	
	public static final int col_spawn_grass = 0xff00FF21;
	public static final int col_spawn_hedge = 0;//unused
	public static final int col_spawn_water = 0;//unused
	public static final int col_spawn_wall1 = 0xff808080;
	public static final int col_spawn_wall2 = 0xffFF7F7F;
	public static final int col_spawn_floor = 0xff7F0037;

   
    	public Tile(Sprite sprite){
            
    		this.sprite = sprite;
    }
    
    	public void render(int x, int y, Screen screen){
    
    }

    	public boolean solid(){
            return false;
    }
}