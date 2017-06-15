package JUnit;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import Gallery.AddImageWindow;
import Gallery.GalleryConstants;
import Launcher.MainJFrame;

public class GalleryApplicationTest {

	MainJFrame mainFrame = new MainJFrame();
	AddImageWindow addImageWindow = new AddImageWindow();
	GalleryConstants constants = new GalleryConstants();
	
	File imgDir = new File(GalleryConstants.IMG_FOLDER);
	File thumbDir = new File(GalleryConstants.THUMB_FOLDER);
	
	@Test
	public void testAddImage() {
		removeAllImg();
		
		File[] imgFiles = imgDir.listFiles();
		File[] thumbFiles = thumbDir.listFiles();
		
		if(imgFiles.length != 1 && thumbFiles.length != 1) {
			fail("Pas de nouveau fichier, l'image n'a pas été créée.");
		}
		
	}
	
	public void removeAllImg(){
		
		File[] imgFiles = imgDir.listFiles();
		File[] thumbFiles = thumbDir.listFiles();
		
		for(File file : imgFiles){
			try{
	    		file.delete();

	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
		}	
		
		for(File file : thumbFiles){
			try{
	    		file.delete();

	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
		}	
	}
}
