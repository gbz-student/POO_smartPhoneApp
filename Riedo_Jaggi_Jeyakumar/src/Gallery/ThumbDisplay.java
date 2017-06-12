package Gallery;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
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
	
	
	public ThumbDisplay(int column) {
		displayThumbs();
		columnDisplay(column);
	}
	
	//Afficher les vignettes à partir de la liste
	public void displayThumbs(){
		ImgInfo itd = new ImgInfo();
		ArrayList<ImgInfo>list = itd.createList();
		
		for(int i=0 ; i<list.size() ; i++){
			String thumbName = list.get(i).getThumbName();
			ImageIcon thumb = new ImageIcon(GalleryConstants.THUMB_FOLDER_PATH+"/"+thumbName);
			JLabel thumbLabel = new JLabel(thumb);
			
			MouseListenerThumb click = new MouseListenerThumb();
			click.index = i;
			thumbLabel.addMouseListener(click);
			
			this.add(thumbLabel);
		}

	}
	
	protected void columnDisplay(int nbColumn){
		GridLayout column = new GridLayout(0, nbColumn);
		column.setVgap(40);
		column.setHgap(40);

		this.setLayout(column);
	}
		
//	protected void regenerateThumbs(){
//
//		
//		for (int i = 0; i < thumbsList.length; i++) {
//			File file = new File(thumbsFolder+"/"+thumbsList[i]);
//			file.delete();
//		}
//		for (int i = 0; i < originalImgList.length; i++) {
//			Path imgOriginalPath = Paths.get(originalImgFolder+"/"+originalImgList[i]);
//			ThumbNail thumb = new ThumbNail(imgOriginalPath);
//		}
//	}
	
//	//Retrait du suffixe au nom de fichier
//	private String fromThumb2Originall(String inputName){
//		String beforeExtension = inputName.substring(0, inputName.lastIndexOf("."));
//		String extension = inputName.substring(inputName.lastIndexOf("."), inputName.length());
//		String removeThumbAdd = beforeExtension.substring(0,beforeExtension.length()-6);
//		String outputName = removeThumbAdd + extension;
//		
//		return outputName;		
//	}
		
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
