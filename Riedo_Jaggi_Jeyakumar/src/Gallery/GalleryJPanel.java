package Gallery;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import javax.sound.midi.MidiDevice.Info;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Launcher.MainJFrame;
import MainPackage.TitleJPanel;

/**
 * Classe permettant de créer l'instance de galerie appelée au lancement de l'application
 * 
 * @author Gabriel Riedo
 *
 */
public class GalleryJPanel extends JPanel {
	
	
	/**
	 * Barre de titre en sommet de galerie
	 */
	private static TitleJPanel title;
	
	/**
	 * Barre comportant des fonctions d'ajout et retrait d'image
	 */
	private static ActionBar imgActionBar ; 
	
	/**
	 * Panel comprenant et organisation l'affichage des vignettes
	 */
	private static ThumbDisplay thumbsPanel;
	
	/**
	 * Scroll permettant de parcourir une liste de vignettes plus haute que la hauteur de l'application
	 */
	private static JScrollPane thumbScroll;
	
	
	/**
	 * <b>Constructeur par défaut de la classe GalleryJPanel</b>
	 * <ul>
	 * <li>Création d'une instance de ThumbDisplay</li>
	 * <li>Création d'une instance de JScrollPane</li>
	 * <li>Ajout de l'instance ThumbDisplay dans l'instance JScrollPane</li>
	 * <li>Instanciation de la barre de titre</li>
	 * </ul> 
	 */
	public GalleryJPanel() {
		
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		
		setPreferredSize(new Dimension(MainJFrame.getWidthJFrame(), MainJFrame.getHeightJFrame()));
		
		thumbsPanel = new ThumbDisplay();
					
		thumbScroll = new JScrollPane(thumbsPanel);
		thumbScroll.getVerticalScrollBar().setUnitIncrement(16);
		
		imgActionBar = new ActionBar();
		title = new TitleJPanel("Mes images");
		
		add(title, BorderLayout.NORTH);
		add(thumbScroll, BorderLayout.CENTER);
		add(imgActionBar, BorderLayout.SOUTH);
        
		}
	
	/**
	 * Getter du ThumbDisplay
	 * @return L'instance de Thumbdisplay utilisée dans l'affichage de la galerie
	 */
	public static ThumbDisplay getThumbDisplay(){
		return thumbsPanel;
	}
	
	/**
	 * Getter
	 * @return L'instance du JScrollPane utilisée dans l'affichage de la galerie
	 */
	public static JScrollPane getThumbScrollPane(){
		return thumbScroll;
	}
}
