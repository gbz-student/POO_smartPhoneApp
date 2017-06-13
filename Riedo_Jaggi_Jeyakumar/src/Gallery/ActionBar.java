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

public class ActionBar extends JPanel{

	JButton addImg = new JButton("Ajouter");
	JButton removeImg = new JButton("Supprimer");
	
	
	public ActionBar(){

		this.setLayout(new FlowLayout());
		this.setBackground(Color.BLACK);
		this.setVisible(true);

		initButton();
		
	}
	
	private void initButton(){
				
		addImg.addActionListener(new ActionButtonListener());
		removeImg.addActionListener(new ActionButtonListener());
				
		this.add(addImg);
		this.add(removeImg);
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
		
