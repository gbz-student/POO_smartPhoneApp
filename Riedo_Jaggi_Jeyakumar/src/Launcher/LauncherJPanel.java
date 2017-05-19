package Launcher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LauncherJPanel extends JPanel{
	
	private JButton buttonConctact = new JButton("Contact");
	private JButton buttonCalculate = new JButton("Calculate");
	private JButton buttonGallery = new JButton("Gallery");
	private MainJFrame frame;
	
	public LauncherJPanel(){
		add(buttonConctact);
		add(buttonCalculate);
		add(buttonGallery);
		
		buttonConctact.addActionListener(new ListerButtonMenu());
		buttonCalculate.addActionListener(new ListerButtonMenu());
		buttonGallery.addActionListener(new ListerButtonMenu());
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
