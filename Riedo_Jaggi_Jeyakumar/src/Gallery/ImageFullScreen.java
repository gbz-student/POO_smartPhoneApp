package Gallery;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.nio.ByteOrder;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Gallery.ActionBar.ActionButtonListener;
import Launcher.MainJFrame;



public class ImageFullScreen extends JPanel {
	
	private ThumbDisplay thumbDisplay = new ThumbDisplay(); 	
	public ArrayList<ImgInfo>list = thumbDisplay.getList();
	private ImgInfo imgInfo = new ImgInfo();
//	private ArrayList<ImgInfo>list = imgInfo.createList();	

	public int indexFS;
	JLabel imgLabel = new JLabel();
	
	JPanel navBar = new JPanel();
	
	JButton closeButton = new JButton("X");
	JButton previousBtn = new JButton();
	JButton nextBtn = new JButton();
	JButton removeBtn = new JButton();
	
	JFrame confirmation = new JFrame("Confirmation");
	JButton okBtn= new JButton();
	JButton cancelBtn= new JButton();

	
	
	public ImageFullScreen(){
		this.setVisible(true);
		this.setBackground(new Color(0,0,0));
		this.setLayout(new BorderLayout());
				
		addCloseButton();
		addNavBarBtn();
					
	}
	
	//Ajout de navigation
	//Side 0 vaut gauche, side 1 vaut droite 
	public void addNavBarBtn(){
		navBar.setLayout(new BorderLayout());

		previousBtn.setText("<");
		previousBtn.addActionListener(new Navigation());
		nextBtn.setText(">");
		nextBtn.addActionListener(new Navigation());
		
		removeBtn.setText("supprimer");
		removeBtn.addActionListener(new Remove());
		
		okBtn.setText("OK");
		cancelBtn.setText("Annuler");
		
		navBar.add(previousBtn, BorderLayout.WEST);
		navBar.add(removeBtn, BorderLayout.CENTER);
		navBar.add(nextBtn, BorderLayout.EAST);
		
		this.add(navBar, BorderLayout.NORTH);
		
	}
	
	private void addCloseButton(){
		
		this.add(closeButton, BorderLayout.NORTH);
		closeButton.addActionListener(new CloseListener());
	}

	
	/*
	 * affichage de l-image en fullscreen
	 */
	public void displayImg(int index){
		String imgPath = list.get(index).getImgName();
		ImageIcon imgIcon = new ImageIcon(GalleryConstants.IMG_FOLDER+imgPath);
		imgLabel.setIcon(imgIcon);

		this.add(imgLabel, BorderLayout.CENTER);
		indexFS = index;
	}
	
	public void confirmRemove(){

		confirmation.setVisible(true);
		confirmation.setLayout(new BorderLayout());
		confirmation.setSize(140, 60);
		
		confirmation.add(okBtn, BorderLayout.WEST);
		confirmation.add(cancelBtn, BorderLayout.EAST);
		
		okBtn.addActionListener(new Remove());
		cancelBtn.addActionListener(new Remove());
	}
	
	class CloseListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			MainJFrame.changePanel("galleryJPanel");
		}
		
		
	}
	
	class Navigation implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==nextBtn){
				indexFS++;
				displayImg(indexFS);
			}
			if(e.getSource()==previousBtn){
				indexFS--;
				displayImg(indexFS);
			}
			
		}

	}
	
	class Remove implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==removeBtn){
				confirmRemove();
			}
			
			if(e.getSource()==okBtn){
				System.out.println(indexFS);
				confirmation.dispose();
			}
			if(e.getSource()==cancelBtn){
				confirmation.dispose();
			}
			
		}

	}
	
	
}
