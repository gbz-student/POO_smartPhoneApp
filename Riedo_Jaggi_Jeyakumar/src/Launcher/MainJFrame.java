package Launcher;

import java.awt.CardLayout;
import javax.swing.*;

import Caculate.CalculateJPanel;
import Contact.ContactJPanel;
import Gallery.GalleryJPanel;

public class MainJFrame extends JFrame{
	
	private JButton buttonMenu = new JButton("Menu");
	private static CardLayout cardLayout;
	public static JPanel cards;
	private JPanel launcherPanel;
	private JPanel contactJPanel = new ContactJPanel();
	private JPanel calculateJPanel = new CalculateJPanel();
	private JPanel galleryJPanel = new GalleryJPanel();
	
	public MainJFrame() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(320, 480);
		
		launcherPanel = new LauncherJPanel();
		
		cards = new JPanel (cardLayout = new CardLayout());
		cards.add(launcherPanel, "launcherJPanel");
		cards.add(contactJPanel, "contactJPanel");
		cards.add(calculateJPanel, "calculateJPanel");
		cards.add(galleryJPanel, "galleryJPanel");
		
		
		setContentPane(cards);
	}
	
	public static void changePanel(String name){
		cardLayout.show(cards, name);
    }
}
