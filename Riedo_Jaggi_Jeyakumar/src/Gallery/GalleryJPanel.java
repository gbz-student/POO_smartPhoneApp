package Gallery;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import javax.sound.midi.MidiDevice.Info;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Launcher.MainJFrame;
import MainPackage.TitleJPanel;

public class GalleryJPanel extends JPanel {
		
//		private JPanel galleryPanel = new JPanel();
		private TitleJPanel title = new TitleJPanel("Mes images");
		private Image images = new Image();

		
		 
		
		
		public GalleryJPanel() {
			
			this.setVisible(true);
			this.setLayout(new BorderLayout());
			
			JPanel imagesPanel = images.generateImages();
			GridLayout column = new GridLayout(0, 3);
			column.setVgap(20);
			imagesPanel.setLayout(column);
						
			JScrollPane imgScroll = new JScrollPane(imagesPanel);
			imgScroll.setPreferredSize(new Dimension(400, 400));
			
			add(title, BorderLayout.NORTH);
			add(imgScroll, BorderLayout.CENTER);
			
					
			
		}
		

		
		class ListerButtonBack implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				MainJFrame.changePanel("launcherJPanel");	
			}
		}
		
	

}
