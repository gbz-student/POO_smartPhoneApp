package Contact;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Insets;
import Model.*;

public class ContactForm extends JPanel{
	
	private JButton back = new JButton("back");
	private JPanel contactInfoTop = new JPanel(new BorderLayout());
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField emailField;
	private JTextField photoField;
	private JPanel panelContainer = new JPanel();
	private JPanel panel = new JPanel();
	private static ContactController contactController = ContactJPanel.getContactController();
	private JComboBox<?>[] typeNumberLabel = new JComboBox[3];
	private JTextField[] phoneField = new JTextField[3];
	public int formFunction = 0; // 0 => formulaire d'ajout, 1 => formulaire de modification
	private String[] typePhoneNumber = { "", "Privé", "Maison", "Bureau" };
	
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
	
	public void setField(Contact contact){
		firstNameField.setText(contact.getFirstName());
		lastNameField.setText(contact.getLastName());
		emailField.setText(contact.getEmail());
		photoField.setText(contact.getPhoto());
		int i = 0;
		for(PhoneNumber number : contact.getPhoneNumbers()){
			typeNumberLabel[i].setSelectedItem(number.getTypePhoneNumber());
			phoneField[i].setText(number.getPhoneNumber());
			i++;
		}
		panelContainer.revalidate();
		panelContainer.repaint();
	}
	
	public void resetField(){
		firstNameField.setText("");
		lastNameField.setText("");
		emailField.setText("");
		photoField.setText("");
		for(int i = 0; i < typeNumberLabel.length; i++){
			typeNumberLabel[i].setSelectedIndex(0);
			phoneField[i].setText("");
		}
		panelContainer.revalidate();
		panelContainer.repaint();
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
			Boolean statut = true;
			ArrayList<PhoneNumber> phoneNumbers = new ArrayList<PhoneNumber>();
			
			for (int i=0; i < phoneField.length; i++){
				if(!phoneField[i].getText().isEmpty()){
					phoneNumbers.add(new PhoneNumber(typeNumberLabel[i].getSelectedItem().toString(), phoneField[i].getText()));
				}
			}
			
			if((firstNameField.getText().isEmpty()) && (lastNameField.getText().isEmpty())){
				statut = false;
			}
			
			if(statut){
				if(formFunction == 0){
					contactController.createContact(firstNameField.getText(), lastNameField.getText(), emailField.getText(), photoField.getText(), phoneNumbers);
				}else if (formFunction == 1) {
					contactController.editContact(firstNameField.getText(), lastNameField.getText(), emailField.getText(), photoField.getText(), phoneNumbers);
				}
			}else{
			    JOptionPane.showMessageDialog(new JPanel(), "Veuillez remplir les champs nom et prénom", "Attention", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
	
	class Back implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(formFunction == 0){
				ContactJPanel.changePanel("contactListJPanel");
			}else if (formFunction == 1) {
				ContactJPanel.changePanel("contactInfoJPanel");
			}
		}
	}
}
