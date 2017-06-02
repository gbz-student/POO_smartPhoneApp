package Gallery;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.image.CropImageFilter;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.w3c.dom.events.MouseEvent;

import Launcher.MainJFrame;

public class ImagesInit extends JPanel implements MouseListener{
	
	private static int ImgSizeConstraint = 120;
	
	public ImagesInit() {
		this.addMouseListener(this);

	}
	
	//Obtenir chemin des images
	private File getImagesPath(){
		File projectPath = new File(".");
		File imagesFolder = new File(projectPath+"/img_library");
		return imagesFolder;
	}
	
	//Créer liste des images
	private String[]getImagesList(File Folder){
		String[]imagesList = Folder.list();
		return imagesList;
	}

	//Générer les images à partir de la liste test
	public JPanel generateImages(){
		JPanel imagesPanel= new JPanel();
		String[]imagesList= getImagesList(getImagesPath());
		
		for (int i = 0; i < imagesList.length; i++) {
			String path = getImagesPath()+"/"+imagesList[i];
			ImageIcon imgResized = imgResize(path);

			imagesPanel.add(new JLabel(imgResized));
//			imagesPanel.add(new JLabel(getImagesPath()+"/"+imagesList[i]));

		}
		
		return imagesPanel;
		
	}
	//Opération de redimensionnement
	private ImageIcon imgResize(String path){
		ImageIcon img = new ImageIcon(path);
		Dimension newingSize = setImgBoudaries(img);
		
		int width = (int)newingSize.getWidth();
		int height = (int)newingSize.getHeight();

		Image image = img.getImage();
		
		Image newing = image.getScaledInstance(width,height,Image.SCALE_SMOOTH);
//		Image croping = image.crop();
		
		ImageIcon imgResized = new ImageIcon(newing);
		
		return imgResized;
	}
	
	//Crop de l'image
	private Image squareCropImage(Image img,int initWidth, int initHeight){
		int x = 0;
		int y = 0;		
		Image croppedImage;
		Dimension imgBoudaries = new Dimension();
		
		int width = img.getWidth(this);
		int height = img.getHeight(this);
		
		if(width>height){
			x = (width - ImgSizeConstraint)/2;
		}
		else{
			y = (height - ImgSizeConstraint)/2;
		}
		
		CropImageFilter filter = new CropImageFilter(x, y, initHeight, initHeight);
		return croppedImage;
	}
	
	//Définit la dimension de l'image proportionnellement
	private Dimension setImgBoudaries(ImageIcon image){
		
		Dimension imgBoudaries = new Dimension();
		
		int width = image.getIconWidth();
		int height = image.getIconHeight();
		
		if(width>height){
			width = setImgReduction(width, height);
			imgBoudaries.setSize(width, ImgSizeConstraint);
		}
		else{
			height = setImgReduction(height, width);
			imgBoudaries.setSize(ImgSizeConstraint, height);
		}
		return imgBoudaries;
	}
	
	//Calcul de réduction
	private int setImgReduction(int bigger, int smaller){
		bigger = (bigger/smaller)*ImgSizeConstraint;
		return bigger;
	}
		
	public void addImage(){

	}
	
	private Image img; 
	
	//Méthode appelée lors du clic de souris
	public void mouseClicked(MouseEvent event) { }


	//Méthode appelée lors du survol de la souris

	public void mouseEntered(MouseEvent event) { 
		try {
			img = ImageIO.read(new File("/img_library/pet1.jpg"));
		} catch (IOException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
	}


	//Méthode appelée lorsque la souris sort de la zone du bouton

	public void mouseExited(MouseEvent event) {
		try {
			img = ImageIO.read(new File("/img_library/pet2.jpg"));
		} catch (IOException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
	}


	//Méthode appelée lorsque l'on presse le bouton gauche de la souris

	public void mousePressed(MouseEvent event) { }


	//Méthode appelée lorsque l'on relâche le clic de souris

	public void mouseReleased(MouseEvent event) { }       
}
