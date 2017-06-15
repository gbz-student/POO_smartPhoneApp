package Launcher;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import MainPackage.ButtonImage;

public class LauncherJPanel extends JPanel{
	
	private JButton buttonContact = new ButtonImage("resources/contactIcon.png");
	private JButton buttonCalculate = new ButtonImage("resources/calculatorIcon.png");
	private JButton buttonGallery = new ButtonImage("resources/galleryIcon.png");
	private Image img;
	
	public LauncherJPanel(){
		setLayout(new FlowLayout(FlowLayout.LEFT));
		setSize(MainJFrame.getWidthJFrame(), MainJFrame.getHeightJFrame() - 50);
		
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
						
		}
	}
}
