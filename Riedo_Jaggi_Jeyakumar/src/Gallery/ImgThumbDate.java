package Gallery;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.Date;

import javax.naming.LimitExceededException;

public class ImgThumbDate {

	private String imgName;
	private String thumbName;
	private FileTime creationDate;
	private final static String THUMB_SUFFIX = "_thumb";
	
	private GalleryJPanel gallery = new GalleryJPanel();
	private String imgFolderPath = gallery.getOriginalImagesFolder();
	private Path imgPath = Paths.get(imgFolderPath+imgName);
	
	ArrayList<ImgThumbDate>list = new ArrayList<ImgThumbDate>();

	
	public ImgThumbDate(String imgName){
		this.imgName = imgName;
		this.thumbName = rename(imgName);		
		File img = new File(imgFolderPath+imgName);
		this.creationDate = getCreationTime(img);
	}
	
	//Date de téléchargement de l'image
	protected FileTime getCreationTime(File img){
		BasicFileAttributes view = null;
		
		try {
			view = Files.getFileAttributeView(imgPath, BasicFileAttributeView.class).readAttributes();
		} catch (IOException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
				
		FileTime fileTime = view.creationTime();
		return fileTime;				
		
	}
	
	//Ajout d'un suffixe au nom de fichier
	private static String rename(String inputName){
		String beforeExtension = inputName.substring(0, inputName.lastIndexOf("."));
		String extension = inputName.substring(inputName.lastIndexOf("."), inputName.length());
		
		String outputName = beforeExtension + THUMB_SUFFIX + extension;
		
		return outputName;		
	}

	protected ArrayList<ImgThumbDate> createList(File imgFolder){
		
		int count = imgFolder.list().length;
		String[]imgList = imgFolder.list();
		for (int i = 0; i < count; i++) {
			ImgThumbDate imgThumDate = new ImgThumbDate(imgList[i]);
			list.add(imgThumDate);
		}
		
		return list;
		
	}

	protected void addITD2List(ImgThumbDate object){
		list.add(object);
	}
}
