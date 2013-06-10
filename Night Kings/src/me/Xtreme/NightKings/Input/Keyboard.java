package me.Xtreme.NightKings.Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener{

	 public int debugMode = -1;
	private boolean[] keys = new boolean [120];
	public boolean up, down, left, right, one, two, three, four, five, six;

	public void update() {
		up = keys[KeyEvent.VK_UP]|| keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D]; 


	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_1) debugMode = 1;
		if(e.getKeyCode() == KeyEvent.VK_2) debugMode = 2;
		if(e.getKeyCode() == KeyEvent.VK_3) debugMode = 3;
		if(e.getKeyCode() == KeyEvent.VK_4) debugMode = 4;
		if(e.getKeyCode()== KeyEvent.VK_5) debugMode = 5;
		keys[e.getKeyCode()] = true;
		
	}

	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}


	public void keyTyped(KeyEvent e) {

	}

}
