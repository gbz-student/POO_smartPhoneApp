package Contact;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import Launcher.MainJFrame;

public class ContactJPanel extends JPanel{

	private JButton buttonBack = new JButton("Retour");
	
	public ContactJPanel(){
		add(buttonBack);
		
		buttonBack.addActionListener(new ListerButtonBack());
	}
	
	class ListerButtonBack implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			MainJFrame.changePanel("launcherJPanel");	
		}
	}
}
