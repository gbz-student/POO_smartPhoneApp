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

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.xml.transform.Source;

import org.w3c.dom.events.MouseEvent;

import Launcher.MainJFrame;

public class ThumbDisplay extends JPanel{
	
	private ImgThumbDate imgList;
	
	private GalleryJPanel gallery = new GalleryJPanel();
	private String thumbsFolder = gallery.getThumbsFolder();
	
	public ThumbDisplay() {

	}
	
	//Afficher les vignettes à partir de la liste
	public JPanel displayThumbs(int column){
		
		for (int i = 0; i < thumbsList.length; i++) {

			String thumbPath = thumbsFolder+"/"+thumbsList[i];
			String originalPath = fromThumb2Originall(thumbPath);
			ImageIcon thumb = new ImageIcon(thumbPath);
			JLabel thumbLabel = new JLabel(thumb);
						
			MouseListenerThumb click = new MouseListenerThumb();
			thumbLabel.addMouseListener(click);
						
			this.add(thumbLabel);
		}
		
		this.columnDisplay(column);
	
		return this;
		
	}
	
	protected GridLayout columnDisplay(int nbColumn){
		GridLayout column = new GridLayout(0, nbColumn);
		column.setVgap(20);
		column.setHgap(40);

		this.setLayout(column);
		return column;
	}
		
	protected void regenerateThumbs(){

		
		for (int i = 0; i < thumbsList.length; i++) {
			File file = new File(thumbsFolder+"/"+thumbsList[i]);
			file.delete();
		}
		for (int i = 0; i < originalImgList.length; i++) {
			Path imgOriginalPath = Paths.get(originalImgFolder+"/"+originalImgList[i]);
			ThumbNail thumb = new ThumbNail(imgOriginalPath);
		}
	}
	
	//Retrait du suffixe au nom de fichier
	private String fromThumb2Originall(String inputName){
		String beforeExtension = inputName.substring(0, inputName.lastIndexOf("."));
		String extension = inputName.substring(inputName.lastIndexOf("."), inputName.length());
		String removeThumbAdd = beforeExtension.substring(0,beforeExtension.length()-6);
		String outputName = removeThumbAdd + extension;
		
		return outputName;		
	}
	

	
	
	
	class MouseListenerThumb implements MouseListener{
		
		
		@Override
		public void mouseClicked(java.awt.event.MouseEvent e){
			MainJFrame.changePanel("imageFullScreen");
			JPanel imgFullScreen = new JPanel();

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
