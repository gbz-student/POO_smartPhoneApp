package Gallery;

import java.awt.Image;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.naming.LimitExceededException;

public class ImgInfo {

	private String imgName;
	private String thumbName;
	private FileTime creationDate;
	
	private Path imgPath = Paths.get(GalleryConstants.IMG_FOLDER_PATH+imgName);
	protected int imgCount = GalleryConstants.IMG_FOLDER_FILE.list().length-1;

	
//	ArrayList<ImgInfo>imgInfoList = new ArrayList<ImgInfo>();
	
	public ImgInfo(){
		String imgName;
		String thumbName;
	}

	public ImgInfo(String imgName){
		this.imgName = imgName;
		this.thumbName = nameThumb(imgName);		
//		File img = new File(GalleryConstants.IMG_FOLDER_PATH+"/"+imgName);
//		this.creationDate = getCreationTime(img);
	}
	
	public String getThumbName(){
		return thumbName;
	}
	public String getImgName(){
		return imgName;
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
	private static String nameThumb(String inputName){
		String beforeExtension = inputName.substring(0, inputName.lastIndexOf("."));
		String extension = inputName.substring(inputName.lastIndexOf("."), inputName.length());
		
		String outputName = beforeExtension + GalleryConstants.THUMB_SUFFIX + extension;
		
		return outputName;		
	}

	//Création de la liste d'objet
	protected ArrayList<ImgInfo> createList(){
		
		ArrayList<ImgInfo>list = new ArrayList<ImgInfo>();
		String[]listImage = GalleryConstants.IMG_FOLDER_FILE.list();
		
		for (int i = 0; i < imgCount; i++) {
			ImgInfo itd = new ImgInfo(listImage[i]);
			list.add(itd);						
		}
		
		return list;
				
	}
}




