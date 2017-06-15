package Contact;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Insets;
import Model.*;

/**
 * Class qui génére un formulaire pour l'ajout et la modification dans une vue graphique 
 * @author ken
 *
 */
public class ContactFormView extends JPanel{
	
	private JPanel contactInfoTop = new JPanel(new BorderLayout());
	private JPanel panelContainer = new JPanel();
	private JPanel panel = new JPanel();
	private JButton back = new JButton("back");
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField emailField;
	private JComboBox<?>[] typeNumberLabel = new JComboBox[3];
	private JTextField[] phoneField = new JTextField[3];
	private static ContactController contactController = ContactView.getContactController();
	private String[] typePhoneNumber = { "", "Privé", "Maison", "Bureau" };
	public int formFunction = 0; // 0 => formulaire d'ajout, 1 => formulaire de modification
	private int contactId;
	private JComboBox photoBox;
	private File[] filesImg;
	private ImageIcon[] items;
	
	/**
	 * Construit mon panel avec le différent champs
	 */
	public ContactFormView(){
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
		addConstraint(firstNameLabel, new Insets(0, 0, 5, 5), GridBagConstraints.EAST, 0, 0);
		
		firstNameField = new JTextField();
		addConstraint(firstNameField, new Insets(0, 0, 5, 0), GridBagConstraints.HORIZONTAL, 1, 0);
		
		JLabel lastNameLabel = new JLabel("Nom");
		addConstraint(lastNameLabel, new Insets(0, 0, 5, 5), GridBagConstraints.EAST, 0, 1);
		
		lastNameField = new JTextField();
		addConstraint(lastNameField, new Insets(0, 0, 5, 0), GridBagConstraints.HORIZONTAL, 1, 1);
		
		JLabel emailLabel = new JLabel("Email");
		addConstraint(emailLabel, new Insets(0, 0, 5, 5), GridBagConstraints.EAST, 0, 2);
		
		emailField = new JTextField();
		addConstraint(emailField, new Insets(0, 0, 5, 0), GridBagConstraints.HORIZONTAL, 1, 2);
		
		JLabel photoLabel = new JLabel("Photo");
		addConstraint(photoLabel, new Insets(0, 0, 5, 5), GridBagConstraints.EAST, 0, 3);
		generatePhotoList();
		
		for (int i=0; i < typeNumberLabel.length; i++){
			typeNumberLabel[i] = new JComboBox<String>(typePhoneNumber);
			addConstraint(typeNumberLabel[i], new Insets(0, 0, 5, 5), GridBagConstraints.EAST, 0, 4 + i);
		}
		
		for (int i=0; i < phoneField.length; i++){
			phoneField[i] = new JTextField();
			addConstraint(phoneField[i], new Insets(0, 0, 5, 0), GridBagConstraints.HORIZONTAL, 1, 4 + i);
		}
		
		JButton save = new JButton("Save");
		save.addActionListener(new Save());
		addConstraint(save, new Insets(0, 0, 5, 0), GridBagConstraints.HORIZONTAL, 1, 7);
	}
	
	/**
	 * Remplit les champs avec les informations du contact (formulaire d'édition) 
	 * @param contact
	 */
	public void setField(Contact contact){
		firstNameField.setText(contact.getFirstName());
		lastNameField.setText(contact.getLastName());
		emailField.setText(contact.getEmail());
		
		for(int i=0; i < filesImg.length; i++){
			if(filesImg[i].getPath().toString().equals(contact.getPhoto())){
				photoBox.setSelectedIndex(i);
			}
		}

		int i = 0;
		for(PhoneNumber number : contact.getPhoneNumbers()){
			typeNumberLabel[i].setSelectedItem(number.getTypePhoneNumber());
			phoneField[i].setText(number.getPhoneNumber());
			i++;
		}
		
		panelContainer.revalidate();
		panelContainer.repaint();
		contactId = contact.getId();
	}
	
	/**
	 * Méthode permetant de remettre les champs à vide
	 * 
	 */
	public void resetField(){
		firstNameField.setText("");
		lastNameField.setText("");
		emailField.setText("");
		photoBox.setSelectedIndex(-1);
		for(int i = 0; i < typeNumberLabel.length; i++){
			typeNumberLabel[i].setSelectedIndex(0);
			phoneField[i].setText("");
		}
		panelContainer.revalidate();
		panelContainer.repaint();
	}
	
	/**
	 * Permet d'ajouté les champs au bon endroit dans le jpanel
	 * 
	 * @param comp
	 * @param insets
	 * @param anchor
	 * @param gridx
	 * @param gridy
	 */
	public void addConstraint(Component comp, Insets insets, int anchor, int gridx, int gridy){
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = insets;
		gbc.fill = anchor;
		gbc.gridx = gridx;
		gbc.gridy = gridy;
		panel.add(comp, gbc);	
	}
	
	/**
	 * Génère une liste de photo
	 */
	public void generatePhotoList(){
		File folder = new File("img_library/thumbs");
		filesImg = folder.listFiles();
		
		items = new ImageIcon[filesImg.length];
		
		for(int i=0; i < filesImg.length; i++){
			items[i] = new ImageIcon(filesImg[i].getPath());
		}
        photoBox = new JComboBox(items);
        
		addConstraint(photoBox, new Insets(0, 0, 5, 0), GridBagConstraints.HORIZONTAL, 1, 3);
	}
	
	/**
	 * Listener pour l'action save.
	 * Créé une liste avec les numéros entré
	 * Set le chemin de l'image
	 * Selon le statut du formulaire, les données sont envoyé soit vers les méthodes createContact ou ou editContact
	 * 
	 */
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
			
			String imgPath = null;
			if(photoBox.getSelectedIndex() != -1){
				imgPath = filesImg[photoBox.getSelectedIndex()].getPath();
			}
			
			if((firstNameField.getText().isEmpty()) && (lastNameField.getText().isEmpty())){
				statut = false;
			}
			
			if(statut){
				if(formFunction == 0){
					contactController.createContact(firstNameField.getText(), lastNameField.getText(), emailField.getText(), imgPath, phoneNumbers);
				}else if (formFunction == 1) {
					contactController.editContact(contactId, firstNameField.getText(), lastNameField.getText(), emailField.getText(), imgPath, phoneNumbers);
				}
			}else{
			    JOptionPane.showMessageDialog(new JPanel(), "Veuillez remplir les champs nom et prénom", "Attention", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
	
	/**
	 * Listener pour l'action back qui rentourne au bon cards.
	 * 
	 * @author ken
	 *
	 */
	class Back implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(formFunction == 0){
				ContactView.changePanel("contactListView");
			}else if (formFunction == 1) {
				ContactView.changePanel("contactInfoView");
			}
		}
	}
}
