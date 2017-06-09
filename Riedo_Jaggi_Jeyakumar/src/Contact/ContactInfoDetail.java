package Contact;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class ContactInfoDetail extends JPanel{
	Border paddingBorder = BorderFactory.createEmptyBorder(20,10,20,10);
	Border raisedetched = BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(189, 195, 199));
	
	public ContactInfoDetail(String labelS, String infoS){
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createCompoundBorder(raisedetched,paddingBorder));
		setMaximumSize(new Dimension(480, 70));
		
		JLabel label = new JLabel(labelS);
		Font f = label.getFont();
		label.setFont(f.deriveFont(f.getStyle() ^ Font.BOLD));
		
		JLabel info = new JLabel(infoS);
		
		add(label, BorderLayout.WEST);
		add(info, BorderLayout.CENTER);
	}
}
