package Launcher;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import MainPackage.Main;

public class LauncherJPanel extends JPanel{
	
	private JButton buttonContact = new LauncherButton("resources/contactIcon.png");
	private JButton buttonCalculate = new LauncherButton("resources/calculatorIcon.png");
	private JButton buttonGallery = new LauncherButton("resources/galleryIcon.png");
	private Image img;
	
	public LauncherJPanel(){
		setLayout(new FlowLayout(FlowLayout.LEFT));
		setPreferredSize(new Dimension(480, 750));
		
		img = new ImageIcon("resources/wallpapers.png").getImage();
		
        add(buttonContact);
        add(buttonCalculate);
        add(buttonGallery);
        
		buttonContact.addActionListener(new ListerButtonMenu());
		buttonCalculate.addActionListener(new ListerButtonMenu());
		buttonGallery.addActionListener(new ListerButtonMenu());
	}
	
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
    }
	
	class ListerButtonMenu implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == buttonContact){
				MainJFrame.changePanel("contactJPanel");
			}
			else if(e.getSource() == buttonCalculate){
				MainJFrame.changePanel("calculateJPanel");
			}
			else if(e.getSource() == buttonGallery){
				MainJFrame.changePanel("galleryJPanel");
			}
			else if(e.getSource() == buttonFullScreenImg){
				MainJFrame.changePanel("imageFullScreen");
			}
			
		}
	}
}
