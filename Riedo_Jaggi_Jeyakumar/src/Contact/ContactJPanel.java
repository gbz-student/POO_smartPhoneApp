package Contact;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import Launcher.MainJFrame;
import MainPackage.TitleJPanel;
import Model.Person;
import Model.PhoneNumber;

public class ContactJPanel extends JPanel{

	JTextField searchField = new JTextField();
	JButton searchButton = new JButton("recherche");
	JButton addContact = new JButton("add");
	JPanel searchPanel = new JPanel();
	JPanel contact = new JPanel();
	JPanel topPanel = new JPanel();
	private TitleJPanel title = new TitleJPanel("Mes contacts");
	JScrollPane listPanel = new JScrollPane();
	
	public ContactJPanel(){
		setPreferredSize(new Dimension(480, 750));
		setLayout(new BorderLayout());
		
		ArrayList<Person> contacts = getContacts();
		
		JPanel gridPanel = new JPanel(new GridLayout(0,1));
		gridPanel.setBackground(new Color(255,255,255));
		
		Border paddingBorder = BorderFactory.createEmptyBorder(20,10,20,10);
		Border raisedetched = BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(189, 195, 199));
		for (Person contact : contacts) {
			JButton label = new JButton(contact.getFirstName() + " " + contact.getLastName());
			label.setBorder(BorderFactory.createCompoundBorder(raisedetched,paddingBorder));
			label.addActionListener(new ShowContactListener());
			label.setHorizontalAlignment(SwingConstants.LEFT);
            gridPanel.add(label);
        }
		
        JScrollPane scrollPanel = new JScrollPane(gridPanel);
        scrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        topPanel.setLayout(new BorderLayout());
        
		searchPanel.setBackground(new Color(40,50,70));
		
		searchField.setColumns(15);
		
		searchPanel.add(title, BorderLayout.NORTH);
		searchPanel.add(searchField);
		searchPanel.add(searchButton);
		searchPanel.add(addContact);
		
		topPanel.add(title, BorderLayout.NORTH);
		topPanel.add(searchPanel, BorderLayout.CENTER);
		
		add(topPanel, BorderLayout.NORTH);
		add(scrollPanel, BorderLayout.CENTER);
	}
	
	public ArrayList<Person> getContacts(){
		ArrayList<Person> contacts = new ArrayList();
		ArrayList<PhoneNumber> phoneNumbers = new ArrayList();
		
		phoneNumbers.add(new PhoneNumber("privé", "0799999999"));
		
		for(int i = 0; i < 50; i++) {
			contacts.add(new Person("Test" + i, "Test", phoneNumbers, "gdhdjk", "bbb"));
		}
		return contacts;
	}
	
	public Person getContact(String firstName, String lastName){
		ArrayList<Person> contacts = getContacts();
		Person contact = null;

	    for (Person c : contacts) {
	        if (firstName.equals(c.getFirstName()) && lastName.equals(c.getLastName())) {
	            contact = c;
	            break;
	        }
	    }

		return contact;
	}
	
	
	class ListerButtonBack implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			MainJFrame.changePanel("launcherJPanel");	
		}
	}
	
	class ShowContactListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(((AbstractButton) e.getSource()).getText());
			MainJFrame.changePanel("contactInfoJPanel");	
		}
	}
}
