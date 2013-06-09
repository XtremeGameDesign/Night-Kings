	

    package me.Xtreme.NightKings;
     
    import java.util.Random;
     
    import me.Xtreme.NightKings.Tile;
     
    public class Screen {
           
            public int width, height;
            public int[] pixels;
            public final int MAP_SIZE = 64;
            public final int MAP_SIZE_MASK = MAP_SIZE - 1;
           
            public int xOffset, yOffset;
           
            public int[] tiles = new int[MAP_SIZE * MAP_SIZE];
           
            private Random random = new Random();
           
            public Screen(int width, int height) {
                    this.width = width;
                    this.height = height;
                    pixels = new int[width * height];
                   
                    for (int i = 0; i<(MAP_SIZE*MAP_SIZE); i++)
                    {
                            tiles[i] = random.nextInt(0xffffff);
                    }
            }
           
            public void clear(){
                    for(int i = 0; i < this.pixels.length; i++){
                            this.pixels[i] = 0;
                    }
            }
           /*
            public void renterTile(int xp, int yp, Tile tile) {
                    xp = xp - xOffset;
                    yp = yp - yOffset;
                    for (int y = 0; y < tile.sprite.SIZE; y++) {
                            int ya = y + yp;
                            for (int x = 0; x < tile.sprite.SIZE; x++) {
                                    int xa = y + xp;
                                    if (xa < 0 || xa >= this.width || ya < 0 || ya >= this.height) break;
                                    pixels[xa + (ya * width)] = tile.sprite.pixels[x + (y * tile.sprite.SIZE)];
                            }
                    }
            }
           */
            public void setOffset(int xOffset, int yOffset) {
                    this.xOffset = xOffset;
                    this.yOffset = yOffset;
                   
            }
    }

