package Gallery;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ColumnLayout extends JPanel{

	
	public ColumnLayout(){
		super();
		
		this.setBackground(Color.blue);
		this.setVisible(true);
	}

	

	public void setColumnwidth(int column){
		int colWidth;
		int displayWidth;
		
		displayWidth = 480 - 40;
		colWidth = (displayWidth-15)/column; 
	}
	
}
