package Gallery;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GalleryConstants {
	
	
	//ALL ABOUT IMG FOLDER
	public final static String IMG_FOLDER = "./img_library/";
	public final static File IMG_FOLDER_FILE = new File(IMG_FOLDER);
	public final static Path IMG_FOLDER_PATH = Paths.get(IMG_FOLDER);
	
	//ALL ABOUT THUMB FOLDER
	public final static String THUMB_FOLDER = IMG_FOLDER+"/thumb/";
	public final static File THUMB_FOLDER_FILE = new File(THUMB_FOLDER);
	public final static Path THUMB_FOLDER_PATH = Paths.get(THUMB_FOLDER);
	
	//suffixe des thumbs
	public final static String THUMB_SUFFIX = "_thumb";
	
}
