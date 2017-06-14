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

public class ImgResizer {
	
	int imgIncrement = 0;
	String imgIncrementStr;
	int thumbIncrement = 0;
	String thumbIncrementStr;
	String outputFileName;

	
	public ImgResizer(){
	
	}
	
	
	protected void resizeImg(Path src, Path dest, Dimension dim){
 		
		try {
			double maxWidth = dim.getWidth();
			double maxHeight = dim.getHeight();
			
			// reads input image
			String imgOriginalPathStr = src.toString();
			File inputFile = new File(imgOriginalPathStr);
			String inputFileName = inputFile.getName();
			String beforeExtension = inputFileName.substring(0, inputFileName.lastIndexOf("."));
			String extension = inputFileName.substring(inputFileName.lastIndexOf("."), inputFileName.length());
			BufferedImage inputImage = ImageIO.read(inputFile);
			
			// creates output image
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

				
			// scales the input image to the output image
			Graphics2D g2d = outputImage.createGraphics();
			        
			//crops inputimage for thumbs
			g2d.drawImage(inputImage, 0, 0, (int)outputWidth, (int)outputHeight, null);
			g2d.dispose();

			//extracts extension of output file
			String formatName = inputFileName.substring(inputFileName.lastIndexOf(".") + 1);
			  
			// writes to output file	
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
	
	//Redimensionnement de l'image
	protected void resizeThumb(Path src, Path dest, Dimension dim){
	 				
		try {
			int width = (int)dim.getWidth();
			int height = (int)dim.getHeight();
			
			// reads input image
			String imgOriginalPathStr = src.toString();
			File inputFile = new File(imgOriginalPathStr);
			String inputFileName = inputFile.getName();
			String beforeExtension = inputFileName.substring(0, inputFileName.lastIndexOf("."));
			String extension = inputFileName.substring(inputFileName.lastIndexOf("."), inputFileName.length());
			BufferedImage inputImage;
			
			inputImage = ImageIO.read(inputFile);
						
			// creates output image
			BufferedImage outputImage = new BufferedImage(width, height, inputImage.getType());
			        
			// scales the input image to the output image
			Graphics2D g2d = outputImage.createGraphics();
			        
			//crops inputimage for thumbs
				int[]cropCoord = getCropCoord(inputImage);
				BufferedImage inputImageCropped = inputImage.getSubimage(cropCoord[0],cropCoord[1],cropCoord[2],cropCoord[3]);

				g2d.drawImage(inputImageCropped.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, width, height, null);
				g2d.dispose();
 
				//extracts extension of output file
			String formatName = inputFileName.substring(inputFileName.lastIndexOf(".") + 1);
			  
			// writes to output file	
			File outputFile ;
			
			outputFile = new File(GalleryConstants.THUMB_FOLDER+outputFileName);

							
			ImageIO.write(outputImage, formatName, outputFile);
		} catch (IOException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
		
   	}
	

	
	//Ajout d'un suffixe au nom de fichier
	private static String rename(String inputName, String addStr){
		String beforeExtension = inputName.substring(0, inputName.lastIndexOf("."));
		String extension = inputName.substring(inputName.lastIndexOf("."), inputName.length());
		
		String outputName = beforeExtension + addStr + extension;
		
		return outputName;		
	}
	
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



