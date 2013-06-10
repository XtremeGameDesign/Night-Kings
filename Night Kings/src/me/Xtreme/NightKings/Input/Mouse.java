package me.Xtreme.NightKings.Input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseListener, MouseMotionListener{

	private static int MouseX = -1;
	private static int MouseY = -1;
	private static int MouseB = -1;
	
	public static int getX(){
		return MouseX;
	}
	
	public static int getY(){
		return MouseY;
	}
	
	public static int getButton(){
		return MouseB;
	}
	
	
	
	public void mouseDragged(MouseEvent e) {
		
		MouseX = e.getX();
		MouseY = e.getY();
	}


	public void mouseMoved(MouseEvent e) {
		
		MouseX = e.getX();
		MouseY = e.getY();
	}


	public void mouseClicked(MouseEvent e) {	
		
		
		
	}


	public void mouseEntered(MouseEvent e) {
		
		
		
	}


	public void mouseExited(MouseEvent e) {
		
		
		
	}

	public void mousePressed(MouseEvent e) {
		
		MouseB = e.getButton();
		
	}


	public void mouseReleased(MouseEvent e) {
		
		MouseB = -1;
		
	}

}
