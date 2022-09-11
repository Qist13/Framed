package com.qist13.framed;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Framed extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final int GRIDSIZE = 3;
	private LightButton[][] lightButton = new LightButton[GRIDSIZE][GRIDSIZE];
	
	public static void main(String[] args) {
		Framed game = new Framed();
	}

	public Framed() {
		initGUI();
		setTitle("Framed");
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		newGame();
	}
	
	private void initGUI() {
		TitleLabel titleLabel = new TitleLabel("Framed");
		add(titleLabel, BorderLayout.PAGE_START);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(GRIDSIZE, GRIDSIZE));
		add(centerPanel, BorderLayout.CENTER);
		
		for (int row = 0; row < GRIDSIZE; ++row) {
			for (int col = 0; col < GRIDSIZE; ++col) {
				lightButton[row][col] = new LightButton(row, col);
				lightButton[row][col].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						LightButton button = (LightButton) e.getSource();
						int row = button.getRow();
						int col = button.getCol();
						
						toggleLights(row, col);
						endGameIfDone();
					}
				});
				centerPanel.add(lightButton[row][col]);
			}
		}
	}
	
	private void toggleLights(int row, int col) {
		lightButton[row][col].toggle();
		
		if (row == 0 && col == 0) {
			lightButton[row][col + 1].toggle();
			lightButton[row + 1][col].toggle();
			lightButton[row + 1][col + 1].toggle();
		} else if (row == 0 && col == 2) {
			lightButton[row][col - 1].toggle();
			lightButton[row + 1][col - 1].toggle();
			lightButton[row + 1][col].toggle();
		} else if (row == 2 && col == 0) {
			lightButton[row - 1][col].toggle();
			lightButton[row - 1][col + 1].toggle();
			lightButton[row][col + 1].toggle();
		} else if (row == 2 & col == 2) {
			lightButton[row - 1][col - 1].toggle();
			lightButton[row - 1][col].toggle();
			lightButton[row][col - 1].toggle();
		} else if (row == 0 && col == 1) {
			lightButton[row][col - 1].toggle();
			lightButton[row][col + 1].toggle();
		} else if (row == 1 && col == 0) {
			lightButton[row - 1][col].toggle();
			lightButton[row + 1][col].toggle();
		} else if (row == 1 && col == 2) {
			lightButton[row - 1][col].toggle();
			lightButton[row + 1][col].toggle();
		} else if (row == 2 && col == 1) {
			lightButton[row][col - 1].toggle();
			lightButton[row][col + 1].toggle();
		} else if (row == 1 && col == 1) {
			lightButton[row - 1][col].toggle();
			lightButton[row][col - 1].toggle();
			lightButton[row][col + 1].toggle();
			lightButton[row + 1][col].toggle();
		}
	}
	
	private void newGame() {
		for (int row = 0; row < GRIDSIZE; ++row) {
			for (int col = 0; col < GRIDSIZE; ++col) {
				lightButton[row][col].turnOn();
			}
		}
		lightButton[1][1].turnOff();
		
		Random rand = new Random();
		int numberOfTimes = rand.nextInt(11) + 10;
		
		for (int i = 0; i < numberOfTimes; ++i) {
			int row = rand.nextInt(GRIDSIZE);
			int col = rand.nextInt(GRIDSIZE);
			toggleLights(row, col);
		}
	}
	
	private void endGameIfDone() {
		boolean done = lightButton[0][0].isLit()
				&& lightButton[0][1].isLit()
				&& lightButton[0][2].isLit()
				&& lightButton[1][0].isLit()
				&& lightButton[1][2].isLit()
				&& lightButton[2][0].isLit()
				&& lightButton[2][1].isLit()
				&& lightButton[2][2].isLit();
		
		if (done) {
			String message = "Congratulations! You won! Do you want to play again?";
			int option = JOptionPane.showConfirmDialog(this, message, "Play again?", JOptionPane.YES_OPTION);
			if (option == JOptionPane.YES_OPTION) {
				newGame();
			} else {
				System.exit(0);
			}
		}
	}
}
