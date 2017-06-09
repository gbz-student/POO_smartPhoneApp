package Launcher;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import Caculate.CalculateJPanel;
import Contact.ContactInfoJPanel;
import Contact.ContactJPanel;
import Gallery.GalleryJPanel;
import Gallery.ImageFullScreen;

public class MainJFrame extends JFrame{
	private JButton buttonMenu = new JButton();
	private static CardLayout cardLayout;
	public static JPanel cards;
	private JPanel launcherPanel= new LauncherJPanel();
	private JPanel contactJPanel = new ContactJPanel();
	private JPanel calculateJPanel = new CalculateJPanel();
	private JPanel galleryJPanel = new GalleryJPanel();
	private JPanel imageFullScreen = new ImageFullScreen();
	
	public MainJFrame() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(480, 800);
	    
	    getContentPane().setBackground(new Color(0,0,0));
		
		try {
			ImageIcon menuIcon = new ImageIcon("resources/menuIcon.png");
		    buttonMenu.setIcon(menuIcon);
		} catch (Exception ex) {
		    System.out.println(ex);
		}
		
		cards = new JPanel (cardLayout = new CardLayout());
		cards.add(launcherPanel, "launcherJPanel");
		cards.add(contactJPanel, "contactJPanel");
		cards.add(calculateJPanel, "calculateJPanel");
		cards.add(galleryJPanel, "galleryJPanel");
		cards.add(imageFullScreen, "imageFullScreen");
		
		cards.setBackground(new Color(0,0,0,0));
		
		add(cards, BorderLayout.NORTH);
		
		add(buttonMenu, BorderLayout.SOUTH);
		
		buttonMenu.addActionListener(new ListerButtonMenu());
		
		buttonMenu.setSize(150, 50);
		buttonMenu.setOpaque(false);
		buttonMenu.setContentAreaFilled(false); // On met à false pour empêcher le composant de peindre l'intérieur du JButton.
		buttonMenu.setBorderPainted(false); // De même, on ne veut pas afficher les bordures.
		buttonMenu.setFocusPainted(false); // On n'affiche pas l'effet de focus.
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
