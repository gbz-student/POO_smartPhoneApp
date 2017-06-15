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
import Launcher.MainJFrame;
import MainPackage.ButtonImage;
import Model.*;
			
/**
 * Cette vue permet d'afficher les informations d'un contact (Phonto, prénom, nom, numéros de téléphone et l'adresse email)
 * @author ken
 *
 */
public class ContactInfoView extends JPanel {
	private JButton back = new ButtonImage("resources/ic_back.png");
	private JButton edit = new ButtonImage("resources/ic_edit.png");
	private JButton delete = new ButtonImage("resources/ic_delete.png");
	private JPanel contactInfoTop = new JPanel(new BorderLayout());
	private static ContactController contactController = ContactView.getContactController();
	private JPanel contactPanel = new JPanel(new BorderLayout());
	private JPanel imagePanel = new JPanel();
	private JPanel infoPanel = new JPanel();
	private JLabel image = new JLabel();
	private JLabel nameLabel;
	private Contact contact;
	
	/**
	 * Construit le JPanel de base et appel les autres méthodes pour se complété
	 */
	public ContactInfoView(){
		setBackground(new Color(255,255,255));
		setLayout(new BorderLayout());
		
		contactInfoTop.setBackground(new Color(38, 166, 154));
		JPanel deleteEdit = new JPanel();
		deleteEdit.setBackground(new Color(0,0,0,0));
		
		back.addActionListener(new Back());
		edit.addActionListener(new Edit());
		delete.addActionListener(new Delete());
		
		deleteEdit.add(delete, BorderLayout.WEST);
		deleteEdit.add(edit, BorderLayout.EAST);
		
		contactInfoTop.add(back, BorderLayout.WEST);
		contactInfoTop.add(deleteEdit, BorderLayout.EAST);
		
		add(contactInfoTop, BorderLayout.NORTH);
		
		add(contactPanel, BorderLayout.CENTER);
	}
	
	public void setContact(int id){
		contact = contactController.getContactById(id);
		
		updateContact();
	}
	
	/**
	 * Cette méthode permet de rafraichir les JPanel image et info.
	 * Elle est appelé après l'enregistrement des modifications d'un contact
	 */
	public void updateContact(){
		imagePanel.removeAll();
		infoPanel.removeAll();
		
		setImagePanel();
		setInfoPanel();
		
		imagePanel.revalidate();
		imagePanel.repaint();
		infoPanel.revalidate();
		infoPanel.repaint();
	}
	
	/**
	 * Méthodes qui construit mon JPnael image (contient la photo, le prénom et le nom)
	 */
	public void setImagePanel(){
		nameLabel = new JLabel(contact.getFirstName() + " " + contact.getLastName());
		nameLabel.setFont(nameLabel.getFont().deriveFont(25f));
		nameLabel.setForeground(Color.white);
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		image.setHorizontalAlignment(SwingConstants.CENTER);
		try {
			ImageIcon menuIcon = new ImageIcon(contact.getPhoto());
			image.setIcon(menuIcon);	
		} catch (Exception ex) {
		    System.out.println(ex);
		}
		
		imagePanel.setLayout(new BorderLayout());
		imagePanel.setBackground(new Color(38, 166, 154));
		imagePanel.setPreferredSize(new Dimension(MainJFrame.getWidthJFrame(), 300));
		imagePanel.add(image, BorderLayout.NORTH);
		imagePanel.add(nameLabel, BorderLayout.CENTER);
		
		contactPanel.add(imagePanel, BorderLayout.NORTH);
	}
	
	/**
	 * Méthode qui construit mon JPanel info (contient les numéros de téléphone et l'addresse email)
	 */
	public void setInfoPanel(){
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
		
		for(PhoneNumber number : contact.getPhoneNumbers()){
			JPanel panel = new PersonalDataRow("Téléphone " + number.getTypePhoneNumber() + ": ", number.getPhoneNumber());
			JButton phone = new ButtonImage("resources/ic_phone.png");
			panel.add(phone, BorderLayout.EAST);
			infoPanel.add(panel);
		}
		
		JPanel panelEmail = new PersonalDataRow("Mail: ", contact.getEmail());
		infoPanel.add(panelEmail);
		
		contactPanel.add(infoPanel, BorderLayout.CENTER);
	}
	
	/**
	 * Listener pour le boutton back
	 * @author ken
	 *
	 */
	class Back implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			ContactView.changePanel("contactListView");
		}
	}
	
	/**
	 * Listener pour le boutton edit
	 * @author ken
	 *
	 */
	class Edit implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			ContactFormView contactFormView = (ContactFormView) ContactView.getCardsComponent(1);
			contactFormView.formFunction = 1;
			contactFormView.setField(contact);
			ContactView.changePanel("contactFormView");
		}
	}
	
	/**
	 * Listerner pour le delete
	 * @author ken
	 *
	 */
	class Delete implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			contactController.removeContact(contact.getId());
			ContactView.changePanel("contactListView");
		}
	}
}
