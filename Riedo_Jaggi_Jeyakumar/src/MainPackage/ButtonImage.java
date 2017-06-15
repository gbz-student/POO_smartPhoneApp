package MainPackage;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ButtonImage extends JButton{

	public ButtonImage(String path){
		
		try {
			ImageIcon menuIcon = new ImageIcon(path);
		    setIcon(menuIcon);
		} catch (Exception ex) {
		    System.out.println(ex);
		}
		
//		setSize(150, 50);
		setOpaque(false);
		setContentAreaFilled(false); // On met à false pour empêcher le composant de peindre l'intérieur du JButton.
		setBorderPainted(false); // De même, on ne veut pas afficher les bordures.
		setFocusPainted(false); // On n'affiche pas l'effet de focus.
	}
}
