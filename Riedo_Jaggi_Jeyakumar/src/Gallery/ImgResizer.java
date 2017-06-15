package Gallery;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

/**
 * 
 * Classe permettant le redimensionement et enregistrement des images des images lors de leur téléchargement
 * 
 * @author Gabriel Riedo
 *
 */
public class ImgResizer {
	
	/**
	 * initialisation des variables permettant le renomage par incrémentation si le nom de l'image téléchargée
	 * existe déjà dans le programme
	 */
	int imgIncrement = 0;
	String imgIncrementStr;
	String outputFileName;

	
	/**
	 * Constructeur par défaut
	 */
	public ImgResizer(){
	}
	
	/**
	 * Méthode pour redimensionner l'image pour optimiser son affichage
	 * 
	 * @param src
	 * 			source de l'image à redimensionner
	 * @param dest
	 * 			chemin du dossier dans lequel enregistrer la sortie
	 * @param dim
	 * 			dimension de la sortie
	 */
	protected void resizeImg(Path src, Path dest, Dimension dim){
 		
		try {
			double maxWidth = dim.getWidth();
			double maxHeight = dim.getHeight();
			
			/**
			 *  lecture de l'image en input
			 */
			String imgOriginalPathStr = src.toString();
			File inputFile = new File(imgOriginalPathStr);
			String inputFileName = inputFile.getName();
			String beforeExtension = inputFileName.substring(0, inputFileName.lastIndexOf("."));
			String extension = inputFileName.substring(inputFileName.lastIndexOf("."), inputFileName.length());
			BufferedImage inputImage = ImageIO.read(inputFile);
			
			/**
			 *  instanciation de l'image output
			 */
			double inputWidth = inputImage.getWidth();
			double inputHeight = inputImage.getHeight();
			
			double outputWidth;
			double outputHeight;
			double ratio;
			
			if( (inputWidth/inputHeight) > (maxWidth/maxHeight) ){
				outputWidth = maxWidth;
				ratio = maxWidth/inputWidth;
				outputHeight = inputHeight * ratio;
				}
			else {
				outputHeight = maxHeight;
				ratio = maxHeight/inputHeight;
				outputWidth = inputWidth * ratio;
				}

			BufferedImage outputImage = new BufferedImage((int)outputWidth, (int)outputHeight, inputImage.getType());

				
			/**
			 * redimensionnement de l'image en input à la taille de l'instance output
			 */
			Graphics2D g2d = outputImage.createGraphics();
			        
			/**
			 * génère le contenu de l'image output
			 */
			g2d.drawImage(inputImage, 0, 0, (int)outputWidth, (int)outputHeight, null);
			g2d.dispose();

			/**
			 * extraction de l'extension
			 */
			String formatName = inputFileName.substring(inputFileName.lastIndexOf(".") + 1);
			  
			/**
			 * écriture du fichier de sortie avec incrément si le nom de l'image existe déjà dans le dossier de sortie	
			 */
			File outputFile ;
			do{
				if(imgIncrement>0)
					imgIncrementStr = "_("+imgIncrement+")";
				else
					imgIncrementStr = "";
				
				
				outputFileName = beforeExtension+imgIncrementStr+extension;
				outputFile = new File(GalleryConstants.IMG_FOLDER+outputFileName);
				imgIncrement++;
			}
			while(outputFile.exists());
						
			ImageIO.write(outputImage, formatName, outputFile);
			
		} catch (IOException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
	}
	
	/**
	 * Méthode pour redimensionner et recadrer la vignette pour optimiser son affichage
	 * 
	 * @param src
	 * 			source de l'image à redimensionner
	 * @param dest
	 * 			chemin du dossier dans lequel enregistrer la sortie
	 * @param dim
	 * 			dimension de la sortie
	 * 
	 */
	protected void resizeThumb(Path src, Path dest, Dimension dim){
	 				
		try {
			int width = (int)dim.getWidth();
			int height = (int)dim.getHeight();
			
			/**
			 *  lecture de l'image en input
			 */
			String imgOriginalPathStr = src.toString();
			File inputFile = new File(imgOriginalPathStr);
			String inputFileName = inputFile.getName();
			String beforeExtension = inputFileName.substring(0, inputFileName.lastIndexOf("."));
			String extension = inputFileName.substring(inputFileName.lastIndexOf("."), inputFileName.length());
			BufferedImage inputImage;
			
			inputImage = ImageIO.read(inputFile);
						
			/**
			 *  instanciation de l'image output
			 */
			BufferedImage outputImage = new BufferedImage(width, height, inputImage.getType());
			        
			/**
			 * création de l'image de sortie
			 */
			Graphics2D g2d = outputImage.createGraphics();
			        
			/**
			 * recadrage de la vignette
			 */
				int[]cropCoord = getCropCoord(inputImage);
				BufferedImage inputImageCropped = inputImage.getSubimage(cropCoord[0],cropCoord[1],cropCoord[2],cropCoord[3]);

				g2d.drawImage(inputImageCropped.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, width, height, null);
				g2d.dispose();
 
				//extracts extension of output file
			String formatName = inputFileName.substring(inputFileName.lastIndexOf(".") + 1);
			  
			/**
			 * écriture du fichier de sortie avec récupération du nom de l'image en cas d'écrémentation du nom	
			 */	
			File outputFile ;
			
			outputFile = new File(GalleryConstants.THUMB_FOLDER+outputFileName);

							
			ImageIO.write(outputImage, formatName, outputFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
   	}
	

	/**
	 * Calcul de coordonnées pour le recadrage de la vignette
	 * 
	 * @param inputImage
	 * 			image à croper
	 * 
	 * @return Les coordonnées pour le recadrage sous forme d'un tableau de 4 lignes
	 * 
	 */		
	private static int[]getCropCoord(BufferedImage inputImage){
		int height = inputImage.getHeight();
		int width = inputImage.getWidth();
		int diff = Math.abs(height - width);
			
		int[]cropCoord = new int[4];
		
		if(height>width){
			int startY = diff/2;
			int endY = height - diff;
			cropCoord[0] = 0;
			cropCoord[1] = startY;
			cropCoord[2] = width;
			cropCoord[3] = endY;
			
		}
		else if(height<width){
			int startX = diff/2;
			int endX = width - diff;
			cropCoord[0] = startX;
			cropCoord[1] = 0;
			cropCoord[2] = endX;
			cropCoord[3] = height;
		}

		return cropCoord;
	}
	


	
}



