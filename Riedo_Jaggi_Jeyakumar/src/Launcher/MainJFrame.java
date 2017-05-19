package Launcher;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import Caculate.CalculateJPanel;
import Contact.ContactJPanel;
import Gallery.GalleryJPanel;


public class MainJFrame extends JFrame{
	//private JButton buttonMenu = new JButton(new ImageIcon("resources/menu.png" ));
	private JButton buttonMenu = new JButton();
	private static CardLayout cardLayout;
	public static JPanel cards;
	private JPanel launcherPanel= new LauncherJPanel();
	private JPanel contactJPanel = new ContactJPanel();
	private JPanel calculateJPanel = new CalculateJPanel();
	private JPanel galleryJPanel = new GalleryJPanel();
	
	public MainJFrame() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(480, 800);
		
		/*
		try {
			ImageIcon menuIcon = new ImageIcon("resources/menu.png");
			Image img = menuIcon.getImage() ;  
			Image newimg = img.getScaledInstance(80, 50,  java.awt.Image.SCALE_SMOOTH ) ;  
			menuIcon = new ImageIcon( newimg );
			
		    buttonMenu.setIcon(menuIcon);
		    //buttonMenu.setSize(480, 0);
		} catch (Exception ex) {
		    System.out.println(ex);
		}
		*/
		
		
		
		
		cards = new JPanel (cardLayout = new CardLayout());
		cards.add(launcherPanel, "launcherJPanel");
		cards.add(contactJPanel, "contactJPanel");
		cards.add(calculateJPanel, "calculateJPanel");
		cards.add(galleryJPanel, "galleryJPanel");
		
		//setContentPane(cards);
		add(cards, BorderLayout.NORTH);
		
		add(buttonMenu, BorderLayout.SOUTH);
		
		buttonMenu.addActionListener(new ListerButtonMenu());
		
		
		buttonMenu.setBackground(Color.RED);
		buttonMenu.setOpaque(true);
	}
	
	public static void changePanel(String name){
		cardLayout.show(cards, name);
    }
	
	class ListerButtonMenu implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			MainJFrame.changePanel("launcherJPanel");
		}
	}
}
