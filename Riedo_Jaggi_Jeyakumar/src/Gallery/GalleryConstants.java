package Gallery;

import java.awt.Dimension;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * Regroupement des constantes utilisées dans la galerie
 * 
 * @author Gabriel Riedo
 *
 */
public class GalleryConstants {
	
	
	/**
	 * Variables concernant l'emplacement des images, en version string, path et file
	 */
	public final static String IMG_FOLDER = "./img_library/originals/";
	public final static File IMG_FOLDER_FILE = new File(IMG_FOLDER);
	public final static Path IMG_FOLDER_PATH = Paths.get(IMG_FOLDER);
	
	/**
	 * Variables concernant l'emplacement des vignettes, en version string, path et file 
	 */
	public final static String THUMB_FOLDER = "./img_library/thumbs/";
	public final static File THUMB_FOLDER_FILE = new File(THUMB_FOLDER);
	public final static Path THUMB_FOLDER_PATH = Paths.get(THUMB_FOLDER);
	
	/**
	 * Variables concernant la taille et dimension des vignettes 
	 */
	public final static int THUMBS_WIDTH = 130;
	public final static int THUMBS_HEIGHT = 130;
	public final static Dimension THUMBS_DIM = new Dimension(THUMBS_WIDTH, THUMBS_HEIGHT);
		
	/**
	 * Variables concernant la taille et dimension des images
	 */
	public final static int IMG_WIDTH = 480;
	public final static int IMG_HEIGHT = 750;
	public final static Dimension IMG_DIM = new Dimension(IMG_WIDTH, IMG_HEIGHT);
	
	/**
	 * Variable concernant le nombre de colonnes de vignettes
	 */
	public final static int COLUMN = 3;
	
	/**
	 * Variable concernant le dossier par defaut de JFileChooser pour stocker des images pour faire les tests
	 */
	public final static File DEFAULT_FOLDER_FILECHOOSER = new File("./images_test/");

	
	
}
