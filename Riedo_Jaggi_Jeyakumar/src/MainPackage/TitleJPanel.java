package MainPackage;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TitleJPanel extends JPanel{

	private String str;
	
	public TitleJPanel(String str){
		
		
		JLabel titre = new JLabel(str);
		titre.setForeground(Color.WHITE);
		add(titre);
		
		
		this.setLayout(new FlowLayout());
		this.setBackground(Color.BLACK);
		this.setVisible(true);

		
		
	}
	
}
