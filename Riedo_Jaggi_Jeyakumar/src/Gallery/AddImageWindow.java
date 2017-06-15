package Gallery;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.ReplicateScaleFilter;
import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import Launcher.MainJFrame;

/**
 * Permet l'utilisation d'un JFileChooser pour ajouter des images jpg et gif
 * Rafraîchit la galerie suite à l'ajout d'une image
 * 
 * @author Gabriel Riedo
 * 
 */

public class AddImageWindow extends JFileChooser{
	
	/**
	 * Source de l'image envoyée vers la galerie
	 */
	private File imgSourceFile;
	
	/**
	 * Récupération de la liste des images ajoutées à l'application
	 */
	private ArrayList<String>list = ThumbDisplay.getList();
	

	/**
	 * <b>Constructeur AddImageWindow</b>
	 * <p>
	 * Lance les méthodes permattant l'ajout d'image
	 * <ul>
	 * <li> initialisation d'une instance de JFileChooser</li>
	 * <li> récupétation de l'image source à introduire dans le programme</li>
	 * <li> rafraîchissement de la galerie pour prendre en compte la nouvelle image</li>
	 * </ul>
	 */
	public AddImageWindow(){
		initActionWindows();
		addImage(imgSourceFile);
		refreshGallery();
	}
	
	/**
	 * <b>Initialisation d'une instance de JFileChooser</b>
	 * <ul>
	 * <li>ajout d'une instance de JButton pour réagir à la confirmation</li>
	 * <li>filtrage du type de fichier réglé sur jpg et gif</li>
	 * <li>récupération de l'image</li>
	 * </ul> 
	 */
	private void initActionWindows(){
		JButton addImgButton = new JButton();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");
	   	this.setFileFilter(filter);
	   	this.setCurrentDirectory(GalleryConstants.DEFAULT_FOLDER_FILECHOOSER);
	    int returnVal = this.showOpenDialog(addImgButton);
	    imgSourceFile = this.getSelectedFile();
	  }
	
	/**
	 * <b>méthode d'ajout de l'image</b>
	 * 
	 * @param file
	 * 			Fichier à ajouter à l'application
	 * 
	 */
	public void addImage(File file){
		//ajout de l'image 
		Path imgSource = imgSourceFile.toPath();
		Path originalImgPath = Paths.get(GalleryConstants.IMG_FOLDER+imgSourceFile.getName());
		String imgSourceName = imgSourceFile.getName();
		list.add(imgSourceName);
		int last = list.size()-1;
		Path thumbPath = Paths.get(GalleryConstants.THUMB_FOLDER+imgSourceName);
		ImgResizer resizer = new ImgResizer();

		resizer.resizeImg(imgSource, originalImgPath, GalleryConstants.IMG_DIM);
		resizer.resizeThumb(imgSource, thumbPath, GalleryConstants.THUMBS_DIM);
	}
	
	
	/**
	 * <b>Méthode de rafraîchissement de la galerie</b>
	 */
	private static void refreshGallery(){
		ThumbDisplay thumbDisplay = GalleryJPanel.getThumbDisplay();
		thumbDisplay.refresh();
	}
	
}
