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
import javax.swing.filechooser.FileNameExtensionFilter;

import Launcher.MainJFrame;

public class AddImageWindow extends JFileChooser{
	
	private File imgSourceFile;
	private ThumbDisplay thumbDisplay = new ThumbDisplay(); 	
	private ArrayList<ImgInfo>list = thumbDisplay.getList();
	private GalleryJPanel galleryPanel = new GalleryJPanel();
	private ThumbDisplay thumbDisp = galleryPanel.getThumbDisplay();
		
	public AddImageWindow(){
		initActionWindows();
	}
	
	private void initActionWindows(){
		JButton addImgButton = new JButton();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");
	   	this.setFileFilter(filter);
	    int returnVal = this.showOpenDialog(addImgButton);
	    imgSourceFile = this.getSelectedFile();
	    addImage(imgSourceFile);
	}
	
	private void addImage(File file){
		//ajout de l'image 
		Path imgSource = imgSourceFile.toPath();
		Path originalImgPath = Paths.get(GalleryConstants.IMG_FOLDER+imgSourceFile.getName());
		ImgInfo imgInfo = new ImgInfo(imgSourceFile.getName());
		list.add(imgInfo);
		int last = list.size()-1;
		Path thumbPath = Paths.get(GalleryConstants.THUMB_FOLDER+list.get(last).getThumbName());
		ImgResizer resizer = new ImgResizer();

		resizer.resizeImg(imgSource, originalImgPath, GalleryConstants.IMG_DIM);
		resizer.resizeThumb(imgSource, thumbPath, GalleryConstants.THUMBS_DIM);
		
		
//		ThumbDisplay thumbCard = (ThumbDisplay)MainJFrame.getCards(3);
//		thumbCard.displayThumbs();
		
		

						
	}
	

}
