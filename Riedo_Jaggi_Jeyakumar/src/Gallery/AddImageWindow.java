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

public class AddImageWindow extends JFileChooser{
	
	private File imgSourceFile;
	private ArrayList<String>list = ThumbDisplay.getList();
	private JButton addImgButton;
		
	public AddImageWindow(){
		initActionWindows();
		addImage(imgSourceFile);
		refreshGallery();
	}
	
	private void initActionWindows(){
		addImgButton = new JButton();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");
	   	this.setFileFilter(filter);
	   	this.setCurrentDirectory(GalleryConstants.DEFAULT_FOLDER_FILECHOOSER);
	    int returnVal = this.showOpenDialog(addImgButton);
	    imgSourceFile = this.getSelectedFile();
	  }
	
	private void addImage(File file){
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
	
	
	
	private void refreshGallery(){
		ThumbDisplay thumbDisplay = GalleryJPanel.getThumbDisplay();
		thumbDisplay.refresh();

	}
		

						
	
	

}
