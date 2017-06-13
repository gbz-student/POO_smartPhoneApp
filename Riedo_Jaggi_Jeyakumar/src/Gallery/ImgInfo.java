package Gallery;

import java.awt.Image;
import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
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
import java.util.Iterator;

import javax.naming.LimitExceededException;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImgInfo {

	private String imgName;
	private String thumbName;
	
	public ImgInfo(){
		
	}	
	
	public ImgInfo(String imgName){
		this.imgName = imgName;
		this.thumbName = nameThumb(imgName);	
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
		
		File[]imgList = GalleryConstants.IMG_FOLDER_FILE.listFiles();
		int countImg = imgList.length;
		ArrayList<ImgInfo>list = new ArrayList<ImgInfo>();
		
		if(countImg>0){
			for(int i=0 ; i<countImg ; i++){
				ImgInfo imgInfo = new ImgInfo(imgList[i].getName());
				list.add(imgInfo);
			}
		}
		
		return list;
	
	}
	
	public String getThumbName(){
		return thumbName;
	}
	public String getImgName(){
		return imgName;
	}
		
}




