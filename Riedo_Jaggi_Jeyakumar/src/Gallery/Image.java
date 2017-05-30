package Gallery;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Image extends JPanel{
	
	private static double ImgSizeConstraint = 120;
	
	public Image() {
	
	}
	

	private File getImagesPath(){
		File projectPath = new File(".");
		String projectAbsolutePath = projectPath.getAbsolutePath();
		File imagesFolder = new File(projectAbsolutePath+"/img_library");
		return imagesFolder;
	}
	
	
	private String[]getImagesList(File Folder){
		String[]imagesList = Folder.list();
		return imagesList;
	}

	
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
//		ImageIcon img = new ImageIcon(path);
//		Image image = img.getImage();
//		setImgBoudaries(image);
		
		java.awt.Image image = Toolkit.getDefaultToolkit().getImage(path);
		setImgBoudaries(image);
		
		Image newing = image.getScaledInstance(width, height, hints);
		ImageIcon imgResized = new ImageIcon(newing);
		
		return imgResized;
	}
	
	//Crop de l'image
	
	//Définit la dimension de l'image proportionnellement
	private Dimension setImgBoudaries(java.awt.Image image){
		Dimension imgBoudaries = image.getSize();
		double width = imgBoudaries.getWidth();
		double height = imgBoudaries.getHeight();
		
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
	private double setImgReduction(double bigger, double smaller){
		bigger = (bigger/smaller)*ImgSizeConstraint;
		return bigger;
	}
		
	public void addImage(){

	}
}
