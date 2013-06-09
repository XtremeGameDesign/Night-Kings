	
  package me.Xtreme.NightKings;
     
    import java.awt.Canvas;
    import java.awt.Color;
    import java.awt.Dimension;
    import java.awt.Graphics;
    import java.awt.image.BufferStrategy;
    import java.awt.image.BufferedImage;
    import java.awt.image.DataBufferInt;
     
    import javax.swing.JFrame;
     
    import me.Xtreme.NightKings.Screen;
    import me.Xtreme.NightKings.Keyboard;
    import me.Xtreme.NightKings.Level;
    import me.Xtreme.NightKings.RandomLevel;
     
     
    public class Main extends Canvas implements Runnable{
     
            private static final long serialVersionUID = 1L;
            public static int width = 300;
            public static int height = width / 16 * 9;
            public static int scale = 3;
            public static String title = "Night Kings";
           
            private Thread thread;
            private JFrame frame;
            private Keyboard key;
            private Level level;
            private boolean running = false;
           
            Screen screen;
           
            private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            private int[] pixels = (((DataBufferInt) image.getRaster().getDataBuffer()).getData());
           
            public Main() {
                    Dimension size = new Dimension(width*scale, height*scale);
                    this.setPreferredSize(size);
                   
                    screen = new Screen(width, height);
                   
                    frame = new JFrame();
                    key = new Keyboard();
                    level = new RandomLevel(64, 64);
                   
                    addKeyListener(key);
            }
           
            public synchronized void start() {
                    running = true;
                    thread = new Thread(this, "Display");
                    thread.start();
            }
           
            public synchronized void stop(){
                    running = false;
                    try{
                            thread.join();
                    } catch(InterruptedException e) {
                            e.printStackTrace();
                    }
            }
           
            public void run() {
                   
                    long lastTime = System.nanoTime();
                    long timer = System.currentTimeMillis();
                    final double ns = 1000000000.0 / 60.0;
                    double delta = 0;
                    int frames = 0;
                    int updates = 0;
                    requestFocus();
                    while(running){
                            long now = System.nanoTime();
                            delta = delta + (now-lastTime) / ns;
                            lastTime = now; //All this can be explained in Episode 13
                            while(delta >=1) { //only happens 60 times per sec.
                                    this.update();
                                    updates++;
                                    delta--;
                            }
                           
                            this.render();
                            frames++;
                           
                            if(System.currentTimeMillis() - timer > 1000){
                                    timer = timer + 1000;
                                    System.out.println(updates + " ups, " + frames + " fps");
                                    frame.setTitle(title + "   |   " + frames + " fps" );
                                    updates = 0;
                                    frames = 0;
                            }
                    }
            }
            int x = 0, y = 0;
            public void update() {
                    key.update();
                    if(key.up) y--;
                    if(key.down) y++;
                    if(key.left) x--;
                    if(key.right) x++;
                   
            }
           
            public void render() {
                    BufferStrategy bs = getBufferStrategy();
                    if(bs == null) {
                            createBufferStrategy(3);
                            return;
                    }
                    screen.clear();
                    level.render(x, y, screen);
                   
                    for(int i = 0; i < this.pixels.length; i++){
                            this.pixels[i] = screen.pixels[i];
                    }
                   
                    Graphics g = bs.getDrawGraphics();
                    g.setColor(Color.black);
                    g.fillRect(0, 0, this.getWidth(), this.getHeight());
                    g.drawImage(image, 0,0, this.getWidth(), this.getHeight(), null);
                    g.dispose();
                    bs.show();
            }
     
            public static void main(String[] args) {
                    Main game = new Main();
                    game.frame.setResizable(false);
                    game.frame.setTitle("Rain");
                    game.frame.add(game);
                    game.frame.pack();
                    game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    game.frame.setLocationRelativeTo(null);
                    game.frame.setVisible(true);
                    
                   
                    game.start();
            }
     
           
    }

