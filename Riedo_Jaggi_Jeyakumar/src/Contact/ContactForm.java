package Contact;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ContactForm extends JPanel{
	
	private JButton back = new JButton("back");
	private JPanel contactInfoTop = new JPanel(new BorderLayout());
	
	public ContactForm(){
		setBackground(new Color(255,255,255));
		setLayout(new BorderLayout());
		
		contactInfoTop.setBackground(new Color(40,50,70));
		
		back.addActionListener(new Back());
		contactInfoTop.add(back, BorderLayout.WEST);
		
		add(contactInfoTop, BorderLayout.NORTH);	
	}
	
	class Back implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			ContactJPanel.backPanel("contactForm");
		}
	}

}
