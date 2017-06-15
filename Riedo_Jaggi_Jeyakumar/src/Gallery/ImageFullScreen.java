package Gallery;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.nio.ByteOrder;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Gallery.ActionBar.ActionButtonListener;
import Launcher.MainJFrame;
import MainPackage.TitleJPanel;


/**
 * Affichage en plein écran d'une image sélectionnée dans la galerie
 * 
 * @author Gabriel Riedo
 *
 */
public class ImageFullScreen extends JPanel {
	
	/**
	 * Récupération de l'instance de la classe ThumbDisplay, de la ArrayList associée 
	 * ainsi que de l'index de l'image sélectionnée dans la galerie
	 */
	private static ThumbDisplay thumbDisplay = GalleryJPanel.getThumbDisplay(); 
	private static ArrayList<String>list = thumbDisplay.getList();
	private int indexFS;
	
	/*
	 * Nouvelles instances pour l'affichage du fullscreen
	 * <ul>
	 * <li>imgLabel pour contenir l'image</li>
	 * <li>JPanels pour la mise en page</li>
	 * <li>JButtons pour les boutons</li>
	 * <li>JFrame pour la confirmation d'action supprimer</li>
	 * </ul>
	 */
	JLabel imgLabel = new JLabel();
	
	JPanel navBar = new JPanel();
	JPanel navBarMiddle = new JPanel();
	JPanel navBarEast = new JPanel();
	JPanel navBarWest = new JPanel();
	
	JButton closeBtn = new JButton();
	JButton previousBtn = new JButton();
	JButton nextBtn = new JButton();
	JButton removeBtn = new JButton();
	
	JFrame confirmation = new JFrame("Confirmation");
	JButton okBtn= new JButton();
	JButton cancelBtn= new JButton();

	
	/**
	 * Consructeur par défaut de la classe
	 * <ul>
	 * <li>définition des paramêtre d'affichage</li>
	 * <li>Ajout des boutons d'actions</li>
	 * </ul>
	 */
	public ImageFullScreen(){
		this.setVisible(true);
		this.setBackground(new Color(0,0,0));
		this.setLayout(new BorderLayout());
				
		addNavBarBtn();
	}
	
	/**
	 * Ajout de la navigation
	 * <ul>
	 * <li>définition des espaces d'affichage et de leur layout</li>
	 * <li>définition du titre de l'image</li>
	 * <li>ajout des boutons de navigation et d'action</li>
	 * <li>organisation des espaces d'affichage dans l'affichage principal</li>
	 * </ul>
	 */
	public void addNavBarBtn(){
		navBar.setLayout(new BorderLayout());
		navBarMiddle.setLayout(new FlowLayout());
		navBarWest.setLayout(new FlowLayout());
		navBarEast.setLayout(new FlowLayout());
		
		TitleJPanel imgTitle = new TitleJPanel(list.get(indexFS));

		previousBtn.setText("<");
		previousBtn.addActionListener(new Navigation());
				
		nextBtn.setText(">");
		nextBtn.addActionListener(new Navigation());
		
		removeBtn.setText("supprimer");
		removeBtn.addActionListener(new Remove());
		
		closeBtn.setText("Galerie");
		closeBtn.addActionListener(new CloseListener());
		
		okBtn.setText("OK");
		cancelBtn.setText("Annuler");
		
		navBarMiddle.add(removeBtn);
		navBarMiddle.add(closeBtn);
		
		navBarEast.add(nextBtn);
		navBarWest.add(previousBtn);
		
		navBar.add(navBarWest, BorderLayout.WEST);
		navBar.add(navBarMiddle, BorderLayout.CENTER);
		navBar.add(navBarEast, BorderLayout.EAST);
		
		this.add(navBar, BorderLayout.SOUTH);
		this.add(imgTitle, BorderLayout.NORTH);
		
		navBar.setBackground(Color.black);
		navBarMiddle.setBackground(Color.black);
		navBarWest.setBackground(Color.black);
		navBarEast.setBackground(Color.black);
		
	}
	
	
	
	
	/**
	 * 	Affichage de l'image en fullscreen
	 * 
	 * <ul>
	 * <li>récupération de l'index</li>
	 * <li>création de l'ImgIcon à partir du nom d'image récupéré dans la liste d'image grâce à l'index</li>
	 * <li>création du chemin vers l'image</li>
	 * <li>insertion de l'ImgIcon créé dans un JLabel</li>
	 * <li>actualisation de l'index de l'image</li>
	 * </ul>
	 * 
	 * @param index
	 * 			Index de l'image cliqué dans la galerie
	 */
	public void displayImg(int index){
		String imgPath = list.get(index);
		ImageIcon imgIcon = new ImageIcon(GalleryConstants.IMG_FOLDER+imgPath);
		imgLabel.setIcon(imgIcon);

		this.add(imgLabel, BorderLayout.CENTER);
		indexFS = index;
	}
	
	public void confirmRemove(){

		confirmation.setVisible(true);
		FlowLayout layout = new FlowLayout(0);
		confirmation.setLayout(layout);
		confirmation.setSize(140, 100);
		
		confirmation.add(okBtn);
		confirmation.add(cancelBtn);
		
		okBtn.addActionListener(new Remove());
		cancelBtn.addActionListener(new Remove());
	}
	
	class CloseListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			MainJFrame.changePanel("galleryJPanel");
		}
		
		
	}
	
	class Navigation implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==nextBtn && indexFS < (list.size()-1)){
				indexFS++;
				displayImg(indexFS);
			}
			if(e.getSource()==previousBtn && indexFS > 0){
				indexFS--;
				displayImg(indexFS);
			}
			
		}

	}
	
	class Remove implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==removeBtn){
				confirmRemove();
			}
			
			if(e.getSource()==okBtn){
				String name = thumbDisplay.getImgName(indexFS);
				File img = new File(GalleryConstants.IMG_FOLDER+name);
				File thumb = new File(GalleryConstants.THUMB_FOLDER+name);
				img.delete();
				thumb.delete();
				thumbDisplay.refreshList();
				ThumbDisplay thumbDisplay = GalleryJPanel.getThumbDisplay();
				thumbDisplay.refresh();
				MainJFrame.changePanel("galleryJPanel");
				confirmation.dispose();

			}
			if(e.getSource()==cancelBtn){
				confirmation.dispose();
			}
			
		}

	}
	
	
}
