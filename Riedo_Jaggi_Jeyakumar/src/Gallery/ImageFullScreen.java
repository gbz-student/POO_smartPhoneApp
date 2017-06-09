package Gallery;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.ByteOrder;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Gallery.ActionBar.ActionButtonListener;
import Launcher.MainJFrame;


public class ImageFullScreen extends JPanel {
	
	private String path;

	public ImageFullScreen(){
		
		this.setVisible(true);
		this.setBackground(new Color(0,0,0));
		this.setLayout(new BorderLayout());
		
		addCloseButton();
		addNavButton("<", 0);
		addNavButton(">", 1);
		
		
	
	}
	
	//Ajout de navigation
	//Side 0 vaut gauche, side 1 vaut droite
	private void addNavButton(String str, int side){

		JLabel nav = new JLabel(str);
		nav.setBackground(Color.blue);
		if(side==0)
			this.add(nav, BorderLayout.WEST);
		else
			this.add(nav, BorderLayout.EAST);
	}
	
	private void addCloseButton(){
		JButton closeButton = new JButton("X");
		this.add(closeButton, BorderLayout.NORTH);
		closeButton.addActionListener(new CloseListener());
	}

	public void setImgPath(String str){
		this.path = str;
	}
	
	class CloseListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			MainJFrame.changePanel("galleryJPanel");
		}
		
		
	}
	
	
}
