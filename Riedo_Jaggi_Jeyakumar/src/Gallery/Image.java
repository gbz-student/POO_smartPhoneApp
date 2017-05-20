package Gallery;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Image extends JFrame{

//	ArrayList<String> imgList = new ArrayList<String>();

//	public void addImage(String str){
//		imgList.add(str);
//		
//	}
		
//	public Image(String location){
//		JLabel img = new JLabel(new ImageIcon(location));
//		
//		
//	}
	

	public void createButton(ArrayList<String>buttonList){
		int i;
		for(i=0;i<buttonList.size();i++){
			String str = buttonList.get(i);
			JButton button = new JButton(str);
			
			button.setSize(200,200);

		}
		
	}
	
	
	
	
	
	
	
	
	
	
	private int calculateImageHeight(int width){
		int height;
		
		height = width/4 * 3;
		
		return height;
	}
	
	public void setImageSize(int imgWidth){
		int imageHeight;
		
		imageHeight = calculateImageHeight(imgWidth);
		
		this.setSize(imgWidth,imageHeight);
	}


	
}
