package Launcher;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LauncherJPanel extends JPanel{
	
	private JButton buttonConctact = new JButton("Contact");
	private JButton buttonCalculate = new JButton("Calculate");
	private JButton buttonGallery = new JButton("Gallery");
	private Image img;
	
	public LauncherJPanel(){
		setPreferredSize(new Dimension(480, 750));
		
		img = new ImageIcon("resources/wallpapers.png").getImage();
		
        add(buttonConctact);
        add(buttonCalculate);
        add(buttonGallery);
        
		buttonConctact.addActionListener(new ListerButtonMenu());
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
			if(e.getSource() == buttonConctact){
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
