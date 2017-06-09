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
		
	private TitleJPanel title = new TitleJPanel("Mes images");
	private ThumbDisplay thumbsPanel = new ThumbDisplay();
	private ActionBar imgActionBar = new ActionBar();
	
	private static String originalImgFolder = new String("./img_library/");
	private static String thumbsFolder = new String("./img_library/thumb/");
	
	public GalleryJPanel() {
		
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		
		setPreferredSize(new Dimension(480, 700));
		
		
//		thumbsPanel.regenerateThumbs();
		thumbsPanel.displayThumbs(3);
		
					
		JScrollPane thumbScroll = new JScrollPane(thumbsPanel);
		thumbScroll.getVerticalScrollBar().setUnitIncrement(16);
		thumbScroll.setPreferredSize(new Dimension(400, 400));
		
		
		add(title, BorderLayout.NORTH);
		add(thumbScroll, BorderLayout.CENTER);
		add(imgActionBar, BorderLayout.SOUTH);
        

		}
	
	protected String getOriginalImagesFolder(){
		return 	originalImgFolder;

	}
	
	protected String getThumbsFolder(){
		return thumbsFolder;
	}
	

}
