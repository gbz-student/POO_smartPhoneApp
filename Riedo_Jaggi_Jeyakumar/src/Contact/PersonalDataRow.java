package Contact;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class PersonalDataRow extends JPanel{
	Border paddingBorder = BorderFactory.createEmptyBorder(20,10,20,10);
	Border raisedetched = BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(189, 195, 199));
	
	public PersonalDataRow(String label, String data){
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createCompoundBorder(raisedetched,paddingBorder));
		setMaximumSize(new Dimension(480, 70));
		
		JLabel jlabel = new JLabel(label);
		Font f = jlabel.getFont();
		jlabel.setFont(f.deriveFont(f.getStyle() ^ Font.BOLD));
		
		JLabel jlabelData = new JLabel(data);
		
		add(jlabel, BorderLayout.WEST);
		add(jlabelData, BorderLayout.CENTER);
	}
}
