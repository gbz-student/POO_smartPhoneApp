package Gallery;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.image.CropImageFilter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.xml.transform.Source;

import org.omg.CORBA.IMP_LIMIT;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.w3c.dom.events.MouseEvent;

import Launcher.MainJFrame;

public class ThumbDisplay extends JPanel{
	
	private static ArrayList<String>imgArrayList;
		
	public ThumbDisplay() {
		imgArrayList = createList();
		displayThumbs();
		
		FlowLayout mainLayout = new FlowLayout(0,13,13);
		this.setLayout(mainLayout);
	}
	
	//Afficher les vignettes à partir de la liste
	public void displayThumbs(){
		
		GridLayout columnDisplay = new GridLayout(0,3);
		columnDisplay.setHgap(15);
		columnDisplay.setVgap(15);
		JPanel imgContainer = new JPanel(columnDisplay);
		
		
		if(!imgArrayList.isEmpty()){
			for(int i=0 ; i<imgArrayList.size() ; i++){
				
				String thumbName = imgArrayList.get(i);
				ImageIcon thumb = new ImageIcon(GalleryConstants.THUMB_FOLDER_PATH+"/"+thumbName);
				JLabel thumbLabel = new JLabel(thumb);
				
				MouseListenerThumb click = new MouseListenerThumb();
				click.index = i;
				thumbLabel.addMouseListener(click);
				imgContainer.add(thumbLabel);
//				}
				
				this.add(imgContainer);
			}
		}
	}
	
	private ArrayList<String> createList(){
		
		imgArrayList = new ArrayList<String>();

		String[]imgList = GalleryConstants.IMG_FOLDER_FILE.list();
		int countImg = imgList.length;
		
		if(countImg>0){		
			for(int i=0 ; i<countImg ; i++){
				imgArrayList.add(imgList[i]);
			}
		}
		
		return imgArrayList;
	
	}
	
	public static ArrayList<String>getList(){
		return imgArrayList;
	}
	
	public static void setList(ArrayList<String>newList){
		imgArrayList = newList;
	}
	
	public static String getImgName(int index){
		String name = imgArrayList.get(index);
		return name;
	}
	
	public void refresh(){
		removeAll();		
		displayThumbs();
		revalidate();
		repaint();
	}
	
	public ArrayList<String> refreshList(){
		
		System.out.println("avant refresh: "+imgArrayList.size());
		imgArrayList.clear();
		System.out.println("apres clear: "+imgArrayList.size());

		imgArrayList = createList();
		System.out.println("apres recreation "+imgArrayList.size());
		
		return imgArrayList;
	}

	
	class MouseListenerThumb implements MouseListener{
		
		int index;
 
		@Override
		public void mouseClicked(java.awt.event.MouseEvent e){
			MainJFrame.changePanel("imageFullScreen");
			ImageFullScreen imgFS = (ImageFullScreen)MainJFrame.getCards(4);
			imgFS.displayImg(index);

		}

		@Override
		public void mouseEntered(java.awt.event.MouseEvent e) {
	      
		}

		@Override
		public void mouseExited(java.awt.event.MouseEvent e) {
			// TODO Stub de la méthode généré automatiquement
			
		}

		@Override
		public void mousePressed(java.awt.event.MouseEvent e) {
			// TODO Stub de la méthode généré automatiquement
			
		}

		@Override
		public void mouseReleased(java.awt.event.MouseEvent e) {
			// TODO Stub de la méthode généré automatiquement
			
		}
		
	}
	
}
