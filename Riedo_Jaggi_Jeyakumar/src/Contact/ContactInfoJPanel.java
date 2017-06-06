package Contact;

import javax.swing.JLabel;
import javax.swing.JPanel;
	
			
public class ContactInfoJPanel extends JPanel {
	
	public ContactInfoJPanel(String firstName, String lastName){
		
		JLabel label = new JLabel(lastName);
		add(label);
	}
}
