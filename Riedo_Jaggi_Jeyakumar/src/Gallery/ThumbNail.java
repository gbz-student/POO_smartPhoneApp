package Gallery;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public class ThumbNail {
	
	private static final int scaledWidth = 120;
	private static final int scaledHeight = 120;

	public ThumbNail(Path imgOriginalPath){
		
		try {
			resize(imgOriginalPath);
		} catch (IOException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
	}
	
	//Redimensionnement de l'image
	private static void resize(Path imgOriginalPath)
	 
		throws IOException {
		
			// reads input image
			String imgOriginalPathStr = imgOriginalPath.toString();
	        File inputFile = new File(imgOriginalPathStr);
	        String inputFileName = inputFile.getName();
	        BufferedImage inputImage = ImageIO.read(inputFile);
	       	        
	        //crop du inputimage
	        int[]cropCoord = getCropCoord(inputImage);
	        BufferedImage inputImageCropped = inputImage.getSubimage(cropCoord[0],cropCoord[1],cropCoord[2],cropCoord[3]);

	        // creates output image
	        BufferedImage outputImage = new BufferedImage(scaledWidth, scaledHeight, inputImage.getType());
	      	        
	        // scales the input image to the output image
	        Graphics2D g2d = outputImage.createGraphics();
	        g2d.drawImage(inputImageCropped.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH), 0, 0, scaledWidth, scaledHeight, null);
	        g2d.dispose();
	 
	        //extracts extension of output file
	        String formatName = inputFileName.substring(inputFileName.lastIndexOf(".") + 1);
	        
	        //modification du nom du fichier
	        String outputFileName = rename(inputFileName, "_thumb");
	 
	        // writes to output file
	        ImageIO.write(outputImage, formatName, new File("./img_library/thumb/"+outputFileName));
	        System.out.println("./img_library/thumb/"+inputFileName);
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



