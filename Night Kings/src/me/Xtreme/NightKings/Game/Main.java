package me.Xtreme.NightKings.Game;

import java.awt.Canvas;
//import java.awt.Color;
import java.awt.Dimension;
//import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import me.Xtreme.NightKings.Entity.Player;
import me.Xtreme.NightKings.Sprites.TileCoordinate;
import me.Xtreme.NightKings.Graphics.Screen;
import me.Xtreme.NightKings.Input.Keyboard;
import me.Xtreme.NightKings.Input.Mouse;
import me.Xtreme.NightKings.Game.Level;

public class Main extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	private static int width = 300;
	private static int height = width / 16 * 9;
	private static int scale = 3;
	private static String title = "Night Kings";

	private Thread thread;
	private JFrame frame;
	private Keyboard key;
	private Level level;
	private Player player;
	private boolean running = false;

	private Screen screen;
	

	private BufferedImage image = new BufferedImage(width, height,
			BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer())
			.getData();
	
	
	

	public Main() {
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);

		screen = new Screen(width, height);
		frame = new JFrame();
		key = new Keyboard();
		level = Level.spawn;
		TileCoordinate playerSpawn = new TileCoordinate(20, 58);
		player = new Player(playerSpawn.x(),playerSpawn.y(),key);
		player.init(level);
		
		addKeyListener(key);
		
		Mouse Mouse = new Mouse();
		addMouseListener(Mouse);
		addMouseMotionListener(Mouse);

	}
	
	public static int getWindowWidth(){
		return width + scale;
	}
	public static int getWindowHeight(){
		return width + scale;
	}

	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}

	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		int frames = 0;
		int updates = 0;
		double delta = 0;
		requestFocus();
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + " ups, " + frames + " fps");
				frame.setTitle(title + "  |  " + updates + " ups, " + frames
						+ " fps");
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}

	public void update() {
		key.update();
		player.update();
		level.update();
	}
	
	public void render() {

		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		screen.clear();
		int xScroll = player.x - screen.width / 2;
		int yScroll = player.y - screen.height / 2;
		level.render(xScroll, yScroll, screen);
		player.render(screen);

		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}

		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	//	g.fillRect(Mouse.getX()-6, Mouse.getY()-6, 16, 16);
	/*	 if (key.debugMode == 1) {   
             g.setColor(Color.WHITE);
             Font debugFont = new Font("Arial", Font.BOLD, 25);
             Font titleFont = new Font("Serif", Font.BOLD, 45);
             g.setFont(titleFont);
             g.drawString("Programming",520,40);
             g.drawString("What is it?",540,100);
             g.setFont(debugFont);
             g.drawString("Computer programming  is the process of designing, writing, testing, debugging,", 250, 130);
             g.drawString("and maintaining the source code of computer programs.", 250, 150);
             g.drawString("The purpose of programming is to create a set of instructions, ",250,170);
             g.drawString("that computers use to perform specific operations or to exhibit desired behaviors.", 250, 190);
		 }
		if (key.debugMode == 2) {   
             g.setColor(Color.WHITE);
             Font debugFont = new Font("Arial", Font.BOLD, 25);
             Font titleFont = new Font("Serif", Font.BOLD, 45);
             g.setFont(titleFont);
             g.drawString("History", 540, 40);
             g.setFont(debugFont);
             g.drawString("One hundred and ninety one years ago, the first computer was created",250,70);
             g.drawString("It was 1822, when Charles Babbage, decided to create the first computer.",250,90);
             g.drawString("It was able to compute several sets of numbers and make a hard copies of the results.",250,110);
             g.drawString("It was not until 1842, when the first 'functional' programm was made.",250,130);
             g.drawString("The person that created it, was a woman named Ada Lovalace",250,150);
             g.drawString("It was an analytical engine to compute Bernoulli numbers.",250,170);
             g.drawString("But the code was never completed. And thus, never tested.",250,190);
		 }
		 if (key.debugMode == 3) {   
             g.setColor(Color.WHITE);
             Font debugFont = new Font("Arial", Font.BOLD, 25);
             Font titleFont = new Font("Serif", Font.BOLD, 45);
             g.setFont(titleFont);
             g.drawString("Programming languages", 500, 40);
             g.setFont(debugFont);
             g.drawString("There are hundred of programming languages, but in the end they are all used for the same thing.", 160, 70);
             g.drawString("To make a computer compute a set of instructions. And this is why, they all have things in common:", 160, 90);
             g.drawString("•Input: Gather data from the keyboard, a file, or some other device.", 160, 110);
             g.drawString("•Output: Display data on the screen or send data to a file or other device.", 160, 130);
             g.drawString("•Arithmetic: Perform basic arithmetical operations like addition and multiplication.", 160,150);
             g.drawString("•Conditional execution: Check conditions and execute the appropriate sequence of statements.",160,170);
             g.drawString("•Repetition: Perform some action repeatedly, usually with some variation.",160,190);
		 }
		 if (key.debugMode == 4) {   
             g.setColor(Color.WHITE);
             Font debugFont = new Font("Arial", Font.BOLD, 25);
             Font titleFont = new Font("Serif", Font.BOLD, 45);
             g.setFont(titleFont);
             g.drawString("Requirements of code",500, 40);
             g.setFont(debugFont);
             g.drawString("When programming, there is various thing that a programmer is looking for:", 250, 70);
             g.drawString("•Reliability: High number of times the program is correct when executing an operation.", 250, 90);
             g.drawString("•Robustness: Programm can anticipate problems and solve them.",250, 110);
             g.drawString("•Usability: An easy use of the program, for everyone.",250, 130);
             g.drawString("•Portability: The program can be used with many operating system, across all platforms.",250, 150);
             g.drawString("•Maintainability: The program can be easily updated by future programmers.",250,170);
             g.drawString("•Efficiency: How much resources the program need to be able to run, the lower, the better.",250,190);
		 }*/
		g.dispose();
		bs.show();

	}
	

	public static void main(String[] args) {
		Main game = new Main();
		game.frame.setResizable(false);
		game.frame.setTitle(Main.title);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);

		game.start();
	}

}