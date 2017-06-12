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
	
	

	public ImgResizer(Path src, Path dest, Dimension dim){
		
		try {
			resize(src, dest, dim);
		} catch (IOException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
	}
	
	//Redimensionnement de l'image
	private static void resize(Path src, Path dest, Dimension dim)
	 
		throws IOException {
			
			int width = (int)dim.getWidth();
	        int height = (int)dim.getHeight();
	        
			// reads input image
			String imgOriginalPathStr = src.toString();
	        File inputFile = new File(imgOriginalPathStr);
	        String inputFileName = inputFile.getName();
	        BufferedImage inputImage = ImageIO.read(inputFile);
	        
	        // creates output image
	        BufferedImage outputImage = new BufferedImage(width, height, inputImage.getType());
	      	        
	        // scales the input image to the output image
	        Graphics2D g2d = outputImage.createGraphics();
	       	        
	        //crops inputimage for thumbs
	        if(dest == GalleryConstants.THUMB_FOLDER_PATH){
		        int[]cropCoord = getCropCoord(inputImage);
		        BufferedImage inputImageCropped = inputImage.getSubimage(cropCoord[0],cropCoord[1],cropCoord[2],cropCoord[3]);
		        g2d.drawImage(inputImageCropped.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, width, height, null);

	        }
	        else
		        g2d.drawImage(inputImage.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, width, height, null);

	        g2d.dispose();
	 
	        //extracts extension of output file
	        String formatName = inputFileName.substring(inputFileName.lastIndexOf(".") + 1);
	        
	        
	        String outputFileName;
	        //modification du nom du fichier thumb
	        if(dest == GalleryConstants.THUMB_FOLDER_PATH){
	        	outputFileName = rename(inputFileName, "_thumb");
	        }
	        else
	        	outputFileName = inputFileName;
	 
	        // writes to output file
	        ImageIO.write(outputImage, formatName, new File(GalleryConstants.THUMB_FOLDER+outputFileName));
	        System.out.println(GalleryConstants.THUMB_FOLDER+inputFileName);
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
		
        System.out.println("height : "+height);
        System.out.println("width : "+width);
	
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



