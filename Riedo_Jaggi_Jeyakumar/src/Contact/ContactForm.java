package Contact;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Model.Person;
import Model.PhoneNumber;

import java.awt.Insets;

public class ContactForm extends JPanel{
	
	private JButton back = new JButton("back");
	private JPanel contactInfoTop = new JPanel(new BorderLayout());
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField emailField;
	private JTextField photoField;
	private JPanel panelContainer = new JPanel();
	private JPanel panel = new JPanel();
	String[] typePhoneNumber = { "Privé", "Maison", "Bureau" };
	private static ContactController contactController = ContactJPanel.getContactController();
	JComboBox<String>[] typeNumberLabel = new JComboBox[3];
	JTextField[] phoneField = new JTextField[3];
	
	public ContactForm(){
		setBackground(new Color(255,255,255));
		setLayout(new BorderLayout());
		
		contactInfoTop.setBackground(new Color(40,50,70));
		
		back.addActionListener(new Back());
		contactInfoTop.add(back, BorderLayout.WEST);
		
		add(contactInfoTop, BorderLayout.NORTH);
		
		add(panelContainer, BorderLayout.CENTER);
		panelContainer.add(panel);
		
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{70, 330};
		gbl_panel.rowHeights = new int[]{40, 40, 40, 40, 40, 40, 40, 40, 40};
		gbl_panel.columnWeights = new double[]{0.0, 1.0};
		panel.setLayout(gbl_panel);
		
		JLabel firstNameLabel = new JLabel("Prénom");
		addElement(firstNameLabel, new Insets(0, 0, 5, 5), GridBagConstraints.EAST, 0, 0);
		
		firstNameField = new JTextField();
		addElement(firstNameField, new Insets(0, 0, 5, 0), GridBagConstraints.HORIZONTAL, 1, 0);
		
		JLabel lastNameLabel = new JLabel("Nom");
		addElement(lastNameLabel, new Insets(0, 0, 5, 5), GridBagConstraints.EAST, 0, 1);
		
		lastNameField = new JTextField();
		addElement(lastNameField, new Insets(0, 0, 5, 0), GridBagConstraints.HORIZONTAL, 1, 1);
		
		JLabel emailLabel = new JLabel("Email");
		addElement(emailLabel, new Insets(0, 0, 5, 5), GridBagConstraints.EAST, 0, 2);
		
		emailField = new JTextField();
		addElement(emailField, new Insets(0, 0, 5, 0), GridBagConstraints.HORIZONTAL, 1, 2);
		
		JLabel photoLabel = new JLabel("Photo");
		addElement(photoLabel, new Insets(0, 0, 5, 5), GridBagConstraints.EAST, 0, 3);
		
		photoField = new JTextField();
		addElement(photoField, new Insets(0, 0, 5, 0), GridBagConstraints.HORIZONTAL, 1, 3);
		
		for (int i=0; i < typeNumberLabel.length; i++){
			typeNumberLabel[i] = new JComboBox<String>(typePhoneNumber);
			addElement(typeNumberLabel[i], new Insets(0, 0, 5, 5), GridBagConstraints.EAST, 0, 4 + i);
		}
		
		for (int i=0; i < phoneField.length; i++){
			phoneField[i] = new JTextField();
			addElement(phoneField[i], new Insets(0, 0, 5, 0), GridBagConstraints.HORIZONTAL, 1, 4 + i);
		}
		
		JButton save = new JButton("Save");
		save.addActionListener(new Save());
		addElement(save, new Insets(0, 0, 5, 0), GridBagConstraints.HORIZONTAL, 1, 7);
	}
	
	public void addElement(Component comp, Insets insets, int anchor, int gridx, int gridy){
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = insets;
		gbc.fill = anchor;
		gbc.gridx = gridx;
		gbc.gridy = gridy;
		panel.add(comp, gbc);	
	}
	
	class Save implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			contactController.createContact(firstNameField.getText(), lastNameField.getText(), emailField.getText(), photoField.getText(), typeNumberLabel, phoneField);
			
		}
	}
	
	class Back implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			ContactJPanel.backPanel("contactForm");
		}
	}

}
