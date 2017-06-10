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
	private ActionBar imgActionBar = new ActionBar();
	
	public GalleryJPanel() {
		
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		
		setPreferredSize(new Dimension(480, 500));
		
		
		ThumbDisplay thumbsPanel = new ThumbDisplay(3);
					
		JScrollPane thumbScroll = new JScrollPane(thumbsPanel);
		thumbScroll.getVerticalScrollBar().setUnitIncrement(16);
		
		
		add(title, BorderLayout.NORTH);
		add(thumbScroll, BorderLayout.CENTER);
		add(imgActionBar, BorderLayout.NORTH);
        
		}
	

}
