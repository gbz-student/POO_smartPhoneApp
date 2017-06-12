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

public class AddImageWindow extends JFileChooser{
	
	
	private GalleryJPanel gallery = new GalleryJPanel();
	private File imgSource = this.getSelectedFile();
	private ImgInfo itd;
		
	public AddImageWindow(){
		System.out.println("test");

//		initActionWindows();
//
//		addImage();
//		ArrayList<ImgInfo>list = itd.createList();
	}
	
	private void initActionWindows(){
		JButton addImgButton = new JButton();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");
	   
		this.setFileFilter(filter);
	    int returnVal = this.showOpenDialog(addImgButton);
	}
	
	private void addImage(){
		//ajout de l'image taille originale
		System.out.println(imgSource);

		String imgName = imgSource.getName();
		String imgSourcePath = imgSource.getPath();
				
		Path source = Paths.get(imgSourcePath);
		Path originalImgFolder = Paths.get(GalleryConstants.IMG_FOLDER+imgName);
				
//		try {
			System.out.println(source);
//			Files.copy(source, originalImgFolder) ;
//
//			//création du thumb
//			ImgResizer thumb = new ImgResizer(originalImgFolder);
//		} catch (IOException e) {	
//			JFrame warningFrame = new JFrame("avertissement");
//			JLabel warningLabel = new JLabel("Cette image existe deja !");
//			warningFrame.add(warningLabel);
//			warningFrame.setSize(220, 120);
//			warningFrame.setVisible(true);
//						
//		}				
	}
	

}
