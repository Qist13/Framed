package com.qist13.framed;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

public class TitleLabel extends JLabel {
	
	private static final long serialVersionUID = 1L;

	public TitleLabel(String title) {
	      super(title);
	      Font font = new Font(Font.SERIF,Font.BOLD,32);
	      setFont(font);
	      setBackground(Color.BLACK);
	      setForeground(Color.white);
	      setOpaque(true);
	      setHorizontalAlignment(JLabel.CENTER);
	      setText(title);
	      			
	    }
}
				