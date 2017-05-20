package Launcher;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class LauncherButton extends JButton{

	public LauncherButton(String iconPath){
		
		try {
			ImageIcon menuIcon = new ImageIcon(iconPath);
			setIcon(menuIcon);	
		} catch (Exception ex) {
		    System.out.println(ex);
		}
		
		setBackground(new Color(0,0,0,0));
		setOpaque(false);
		setContentAreaFilled(false);
		setBorderPainted(false);
	}
}
