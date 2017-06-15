package Gallery;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import MainPackage.ButtonImage;

public class ActionBar extends JPanel{

	JButton addImg = new ButtonImage("./resources/ic_add.png");
	
	
	public ActionBar(){

		this.setLayout(new FlowLayout());
		this.setBackground(new Color(38, 166, 154));
		this.setVisible(true);

		initButton();
	}
	
	private void initButton(){
				
		addImg.addActionListener(new ActionButtonListener());
		this.add(addImg);
	}
	

	
	class ActionButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == addImg){
				AddImageWindow addImgWindow = new AddImageWindow();

			}
			else{
				AddImageWindow rien;
//				initActionWindows("Supprimer une image");
			}
		}
	}
}
		
