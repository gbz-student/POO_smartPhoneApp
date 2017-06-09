package Contact;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import Model.Person;
import Model.PhoneNumber;
			
public class ContactInfoJPanel extends JPanel {
	
	private ContactController contactController = new ContactController();
	private JPanel contactPanel = new JPanel();
	private JPanel infoPanel = new JPanel();
	private JLabel image = new JLabel();
	private JLabel nameLabel;
	
	public ContactInfoJPanel(String firstName, String lastName){
		setBackground(new Color(255,255,255));
		setLayout(new BorderLayout());
		
		Person contact = contactController.getContact(firstName, lastName);
		
		nameLabel = new JLabel(contact.getFirstName() + " " + contact.getLastName());
		nameLabel.setFont(nameLabel.getFont().deriveFont(25f));
		nameLabel.setForeground(Color.white);
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		image.setHorizontalAlignment(SwingConstants.CENTER);
		try {
			ImageIcon menuIcon = new ImageIcon("img_library/" + contact.getPhoto());
			image.setIcon(menuIcon);	
		} catch (Exception ex) {
		    System.out.println(ex);
		}
		
		contactPanel.setLayout(new BorderLayout());
		contactPanel.setBackground(new Color(40,50,70));
		contactPanel.setPreferredSize(new Dimension(480, 300));
		contactPanel.add(image, BorderLayout.NORTH);
		contactPanel.add(nameLabel, BorderLayout.CENTER);
		
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
		
		for(PhoneNumber number : contact.getPhoneNumbers()){
			JPanel panel = new ContactInfoDetail("Téléphone " + number.getTypePhoneNumber() + ": ", number.getPhoneNumber());
			JButton phone = new JButton("phone");
			panel.add(phone, BorderLayout.EAST);
			infoPanel.add(panel);
		}
		
		JPanel panelEmail = new ContactInfoDetail("Mail: ", contact.getEmail());
		infoPanel.add(panelEmail);
			
		add(contactPanel, BorderLayout.NORTH);
		add(infoPanel, BorderLayout.CENTER);
	}
}
