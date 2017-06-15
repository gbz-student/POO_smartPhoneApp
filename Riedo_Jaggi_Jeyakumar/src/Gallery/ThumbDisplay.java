package Gallery;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.image.CropImageFilter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.xml.transform.Source;

import org.omg.CORBA.IMP_LIMIT;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.w3c.dom.events.MouseEvent;

import Launcher.MainJFrame;

/**
 * 
 * Classe permettant l'affichage des vignettes dans la galerie
 * 
 * @author Gabriel Riedo 
 *
 */
public class ThumbDisplay extends JPanel{
	
	JPanel imgContainer;
	

	/**
	 * ArrayList stockant le nom des images contenu dans le dossier img_library 
	 */
	private static ArrayList<String>imgArrayList;
		
	
	/**
	 * Constructeur par défaut permettant de générer la liste des images ainsi que leur affichage dans le panel principal 
	 */
	public ThumbDisplay() {
		imgArrayList = createList();
		displayThumbs();
		
		FlowLayout mainLayout = new FlowLayout(0,13,13);
		this.setLayout(mainLayout);
	}
	
	
	/**
	 * Afficher les vignettes à partir de la liste
	 */
	public void displayThumbs(){
		
		GridLayout columnDisplay = new GridLayout(0,3);
		columnDisplay.setHgap(15);
		columnDisplay.setVgap(15);
		imgContainer = new JPanel(columnDisplay);
		
		/**
		 * test pour éviter l'erreur de liste vide
		 */
		if(!imgArrayList.isEmpty()){
			for(int i=0 ; i<imgArrayList.size() ; i++){
				
				String thumbName = imgArrayList.get(i);
				ImageIcon thumb = new ImageIcon(GalleryConstants.THUMB_FOLDER_PATH+"/"+thumbName);
				JLabel thumbLabel = new JLabel(thumb);
				
				MouseListenerThumb click = new MouseListenerThumb();
				click.index = i;
				thumbLabel.addMouseListener(click);
				imgContainer.add(thumbLabel);
				
				this.add(imgContainer);
			}
		}
	}
	
	/**
	 * Création de la liste d'image
	 * @return ArrayList contenu en String le nom des images contenues dans le dossier img_library
	 */
	private ArrayList<String> createList(){
		
		imgArrayList = new ArrayList<String>();

		String[]imgList = GalleryConstants.IMG_FOLDER_FILE.list();
		int countImg = imgList.length;
		
		if(countImg>0){		
			for(int i=0 ; i<countImg ; i++){
				imgArrayList.add(imgList[i]);
			}
		}
		return imgArrayList;	
	}
	
	/**
	 * @return La liste des noms d'image
	 */
	public static ArrayList<String>getList(){
		return imgArrayList;
	}
	
	
	/**
	 * remplace la liste de nom d'image par une nouvelle liste fraîche
	 * @param newList
	 */
	public static void setList(ArrayList<String>newList){
		imgArrayList = newList;
	}
	
	/**
	 * @param index
	 * 			index de l'image dont on souhaite le nom
	 * @return Le nom de l'image
	 */
	public static String getImgName(int index){
		String name = imgArrayList.get(index);
		return name;
	}
	
	/**
	 * Rafraìchit la galerie
	 */
	public void refresh(){
		removeAll();	
		displayThumbs();
		revalidate();
		repaint();
	}
	
	/**
	 * Rafraichit la liste de nom d'image
	 * 
	 * 
	 */
	public void refreshList(){
		
		imgArrayList.clear();
		imgArrayList = createList();
	}

	/**
	 * Listener pour l'affichage en fullscreen de l'image cliquée par récupération de l'index de l'image
	 * 
	 */
	class MouseListenerThumb implements MouseListener{
		
		int index;
		@Override
		public void mouseClicked(java.awt.event.MouseEvent e){
			MainJFrame.changePanel("imageFullScreen");
			ImageFullScreen imgFS = (ImageFullScreen)MainJFrame.getCards(4);
			imgFS.displayImg(index);
		}

		@Override
		public void mouseEntered(java.awt.event.MouseEvent e) {
	      
		}

		@Override
		public void mouseExited(java.awt.event.MouseEvent e) {
			// TODO Stub de la méthode généré automatiquement
			
		}

		@Override
		public void mousePressed(java.awt.event.MouseEvent e) {
			// TODO Stub de la méthode généré automatiquement
			
		}

		@Override
		public void mouseReleased(java.awt.event.MouseEvent e) {
			// TODO Stub de la méthode généré automatiquement
			
		}
		
	}
	
}
