package Contact;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import Model.Contact;
import Model.PhoneNumber;
			
public class ContactInfoJPanel extends JPanel {
	
	private JButton back = new JButton("back");
	private JButton edit = new JButton("edit");
	private JPanel contactInfoTop = new JPanel(new BorderLayout());
	private static ContactController contactController = ContactJPanel.getContactController();
	private JPanel contactPanel = new JPanel(new BorderLayout());
	private JPanel imagePanel = new JPanel();
	private JPanel infoPanel = new JPanel();
	private JLabel image = new JLabel();
	private JLabel nameLabel;
	Contact contact;
	
	public ContactInfoJPanel(String firstName, String lastName){
		setBackground(new Color(255,255,255));
		setLayout(new BorderLayout());
		
		contactInfoTop.setBackground(new Color(40,50,70));
		
		back.addActionListener(new Back());
		edit.addActionListener(new Edit());
		contactInfoTop.add(back, BorderLayout.WEST);
		contactInfoTop.add(edit, BorderLayout.EAST);
		
		add(contactInfoTop, BorderLayout.NORTH);
		
		contact = contactController.getContact(firstName, lastName);
		
		setImagePanel();
		
		setInfoPanel();
		
		
			
//		contactPanel.add(imagePanel, BorderLayout.NORTH);
//		contactPanel.add(infoPanel, BorderLayout.CENTER);
		
		add(contactPanel, BorderLayout.CENTER);
	}
	
	public void setImagePanel(){
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
		
		imagePanel.setLayout(new BorderLayout());
		imagePanel.setBackground(new Color(40,50,70));
		imagePanel.setPreferredSize(new Dimension(480, 300));
		imagePanel.add(image, BorderLayout.NORTH);
		imagePanel.add(nameLabel, BorderLayout.CENTER);
		
		contactPanel.add(imagePanel, BorderLayout.NORTH);
	}
	
	public void setInfoPanel(){
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
		
		for(PhoneNumber number : contact.getPhoneNumbers()){
			JPanel panel = new ContactInfoDetail("Téléphone " + number.getTypePhoneNumber() + ": ", number.getPhoneNumber());
			JButton phone = new JButton("phone");
			panel.add(phone, BorderLayout.EAST);
			infoPanel.add(panel);
		}
		
		JPanel panelEmail = new ContactInfoDetail("Mail: ", contact.getEmail());
		infoPanel.add(panelEmail);
		
		contactPanel.add(infoPanel, BorderLayout.CENTER);
	}
	
	public void updateContact(){
		infoPanel.removeAll();
		
		setImagePanel();
		setInfoPanel();
		
		contactPanel.revalidate();
		contactPanel.repaint();
	}
	
	class Back implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
//			ContactJPanel.removeLastPanel();
//			ContactJPanel.goFirstPanel();
			ContactJPanel.backPanel("contactInfo");
		}
	}
	
	class Edit implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			ContactForm contactForm = (ContactForm) ContactJPanel.getCards().getComponent(1);
			contactForm.formFunction = 1;
			contactForm.setField(contact);
			ContactJPanel.changePanel("contactForm");
		}
	}
}
