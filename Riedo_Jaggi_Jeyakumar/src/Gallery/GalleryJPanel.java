package Gallery;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Launcher.MainJFrame;
import MainPackage.TitleJPanel;

public class GalleryJPanel extends JPanel{
		
		private JPanel galleryPanel = new JPanel();
		 
		
		
		public GalleryJPanel(){
			
			this.setVisible(true);
			this.setLayout(new BorderLayout());
			
			TitleJPanel title = new TitleJPanel("Mes images");
			add(title, BorderLayout.NORTH);
			
			
			Image images = new Image();
			
			ArrayList<String>listImage = new ArrayList<String>(Arrays.asList("pet1.jpg","pet2.jpg","pet3.jpg","pet4.jpg","pet5.jpg","pet6.jpg","pet7.jpg"));

			images.createButton(listImage);
			
//			add(images, BorderLayout.CENTER);
			
		

			
		}
		
		class ListerButtonBack implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				MainJFrame.changePanel("launcherJPanel");	
			}
		}
		
	

}
