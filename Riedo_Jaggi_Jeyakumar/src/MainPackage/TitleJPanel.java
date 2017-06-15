package MainPackage;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TitleJPanel extends JPanel{
	
	public TitleJPanel(String str){
		JLabel titre = new JLabel(str);
		titre.setForeground(Color.BLACK);
		add(titre);

		this.setBackground(new Color(38, 166, 154));
		this.setVisible(true);	
	}
	
}
