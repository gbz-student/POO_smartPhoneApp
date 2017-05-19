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
		
		buttonConctact.addActionListener(new ListerButtonMenu());
		buttonCalculate.addActionListener(new ListerButtonMenu());
		buttonGallery.addActionListener(new ListerButtonMenu());
	}
	
	class ListerButtonMenu implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			MainJFrame.changePanel("contactJPanel");	
		}
	}
}
