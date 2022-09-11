package com.qist13.framed;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;

public class LightButton extends JButton {
	
	private static final long serialVersionUID = 1L;
	private static final int MAXSIZE = 50;
	private int row = 0;
	private int col = 0;
	
	public LightButton(int row, int col) {
		this.row = row;
		this.col = col;
		
		Dimension size = new Dimension(MAXSIZE, MAXSIZE);
		setBackground(Color.BLACK);
		setPreferredSize(size);
	}
	
	public boolean isLit() {
		Color color = getBackground();
		boolean isLit = color.equals(Color.RED);
		
		return isLit;
	}
	
	public void toggle() {
		if (isLit()) {
			turnOff();
		} else {
			turnOn();
		}
	}
	
	public void turnOn() {
		setBackground(Color.RED);
	}
	
	public void turnOff() {
		setBackground(Color.BLACK);
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
}
