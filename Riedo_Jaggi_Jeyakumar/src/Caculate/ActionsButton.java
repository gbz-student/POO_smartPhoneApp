package Caculate;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;


 public class ActionsButton extends JPanel {
	
	
	private JPanel b = new JPanel();
	private ActionsScreen screen = new ActionsScreen();
	
	// creation des elements a afficher
    private String elements [] = {"AC","%","ON","÷","7","8","9","×","4","5","6","-","1","2","3","+","+/-","0",".","="};
	 
	 // creation des boutons par element
     private JButton but[] = new JButton[elements.length];
    
 	// agrandissement du caractere du bouton
     private Font f = new Font("Serif", Font.PLAIN, 40);
     
     private Dimension bsize = new Dimension(70,70);
	
	 private GridLayout cr = new GridLayout();
	 
	 private double number;
	 private boolean clicOperateur = false, update = false;
	 private boolean active = false;
	 private String operateur;
	 
     
     public ActionsButton(){
    	 
    	 setLayout(new BorderLayout());
    	 
 		b.setLayout(new GridLayout());
 		cr.setColumns(4);
		cr.setRows(5);
		b.setLayout(cr);
		
		// fixer des espaces entre les boutons 
		cr.setHgap(4);
		cr.setVgap(6);
		
      	// inisialisation des boutons
   		   initBut();
    	  
    	
    	   coloriageBut();
    	   
    	// operations
    	   calculBut();
	
  		// ajout des boutons dans le Panel 
    	   addBut();
    	   
    	   this.setBorder(new TitledBorder(null,"Calculatrice", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));
    	   this.add(screen, BorderLayout.NORTH);
    	   this.add(b, BorderLayout.CENTER);
     }
     
     
    	   
     
     public void initBut(){
    	 
    	 for (int i = 0; i < but.length; i++) {
 			
 			but[i] = new JButton(elements[i]);
 			
 			but[i].setPreferredSize(bsize);;
 			
 			but[i].setFont(f);
 			
 	   }
     }
    
           // coloriage des boutons 
      public void coloriageBut(){
    	 
    	 
              for (int i = 0; i < elements.length; i++) {
    		   
    		   but[i].setBackground(new Color(128,128,125));
    		   
    		   if(elements[i]=="+"||elements[i]=="-"||elements[i]=="÷"||elements[i]=="×"||elements[i]=="="){
    			
    			 but[i].setBackground(new Color(255,170,7));
    			 but[i].setForeground(Color.WHITE);
    		
    		   }
    		   but[i].setOpaque(true);
    		   but[i].setBorderPainted(false);
    
    	}
             }
             // affectation d'ActionListner aux boutons
             public void calculBut(){
            	 
            	 for (int i = 0; i < elements.length; i++) {
					
            		switch(i){
            		 case 0 :
           	          but[i].addActionListener(new ResetListener());
           	          break;
            		  case 1 : 
            		  but[i].addActionListener(new PercentListner());  
            	      break;
            		  case 2 :  
            		  but[i].addActionListener(new DeleteListner());    
            	      break;
            		 case 3 :
           	          but[i].addActionListener(new DivListener());
           	          break; 
            		 case 7 :	
           	          but[i].addActionListener(new MultiListener()); 
           	          break;
            		 case 11 :
           	          but[i].addActionListener(new MinusListener());
           	          break;
            		 case 15 :
            	      but[i].addActionListener(new PlusListener());
            	      break;
            		 case 16 :	 
            		  but[i].addActionListener(new NegatifListner()); 
               	      break;
            		 case 19 :
            	      but[i].addActionListener(new EgalListener());
            	      break;
            	     default :
            	      but[i].addActionListener(new NumberListener());
            	      break;
            		
            		}
            		 
				} 
            	 }
             
             public void addBut(){
            	 
            	 for (int i = 0; i < but.length; i++) {
             		
     			    b.add(but[i]);	   
     		} 
            	 }
           
             
             
             //Méthode permettant d'effectuer un calcul selon l'opérateur sélectionné
             
             private void calculOperateurs(){
              
               if(operateur=="+"){
                 number = number+Double.valueOf(screen.text.getText()).doubleValue();
                 screen.text.setText(String.valueOf(number));
               }
               if(operateur=="-"){
                 number = number-Double.valueOf(screen.text.getText()).doubleValue();
                 screen.text.setText(String.valueOf(number));  
               }          
               if(operateur=="*"){
                 number = number*Double.valueOf(screen.text.getText()).doubleValue();
                 screen.text.setText(String.valueOf(number));
               }     
               if(operateur=="/"){
            	 
                 try{
                	   number = number/Double.valueOf(screen.text.getText()).doubleValue();
                       screen.text.setText(String.valueOf(number));
                   
                 } catch(ArithmeticException e) {
                	
                 screen.text.setText(e.getMessage());
                   
                 }
               }
             }
             
             //Listener utilisé pour les chiffres
             //Permet de stocker les chiffres et de les afficher
             
             class NumberListener implements ActionListener {
               public void actionPerformed(ActionEvent e){
                 
                 String str = ((JButton)e.getSource()).getText();
                 if(update){
                   update = false;
                 }
                 else{
                   if(!screen.text.getText().equals("0"))
                     str = screen.text.getText() + str;
                 }
                 screen.text.setText(str);
               }
             }
             
             //Listener affecté au bouton =
             class EgalListener implements ActionListener {
               public void actionPerformed(ActionEvent arg0){
            	 calculOperateurs();
                 update = true;
                 clicOperateur = false;
               }
             }

             //Listener affecté au bouton +
             class PlusListener implements ActionListener {
               public void actionPerformed(ActionEvent arg0){
                 if(clicOperateur){
                   calculOperateurs();
                   screen.text.setText(String.valueOf(number));
                 }
                 else{
                   number = Double.valueOf(screen.text.getText()).doubleValue();
                   clicOperateur = true;
                 }
                 operateur = "+";
                 update = true;
               }
             }

             //Listener affecté au bouton -
             
             class MinusListener implements ActionListener {
               public void actionPerformed(ActionEvent arg0){
                 if(clicOperateur){
                   calculOperateurs();
                   screen.text.setText(String.valueOf(number));
                 }
                 else{
                   number = Double.valueOf(screen.text.getText()).doubleValue();
                   clicOperateur = true;
                 }
                 operateur = "-";
                 update = true;
               }
             }

             //Listener affecté au bouton *
             class MultiListener implements ActionListener {
               public void actionPerformed(ActionEvent arg0){
                 if(clicOperateur){
                   calculOperateurs();
                   screen.text.setText(String.valueOf(number));
                 }
                 else{
                   number = Double.valueOf(screen.text.getText()).doubleValue();
                   clicOperateur = true;
                 }
                 operateur = "*";
                 update = true;
               }
             }

             //Listener affecté au bouton /
             
             class DivListener implements ActionListener {
               public void actionPerformed(ActionEvent arg0){
                 if(clicOperateur){
                   calculOperateurs();
                   screen.text.setText(String.valueOf(number));
                 }
                 else{
                   number = Double.valueOf(screen.text.getText()).doubleValue();
                   clicOperateur = true;
                 }
                 operateur = "/";
                 update = true;
               }
             }
             //Listener affecté au bouton AC
             
             class ResetListener implements ActionListener {
               public void actionPerformed(ActionEvent arg0){
                 clicOperateur = false;
                 update = true;
                 number = 0;
                 operateur = "";
                 screen.text.setText("0");
               }
             }      
	
             class DeleteListner implements ActionListener{
            	 public void actionPerformed(ActionEvent arg0){
            		 
            		screen.text.setText("0"); 
            		 
            		if (arg0.getActionCommand().equals("on")){
            			active = true;
            		}
            			else{
            				active = false;
            			
            	
            		}

            	 }
             }
             
             class NegatifListner implements ActionListener{
            	 public void actionPerformed(ActionEvent arg0){
            		number = -1.0*Double.valueOf(screen.text.getText());
            		
            		clicOperateur = false;
            	    screen.text.setText(String.valueOf(number));	
            	 }
             }
             
             class PercentListner implements ActionListener{
            	 public void actionPerformed(ActionEvent arg0){
            		 
            		number = Double.valueOf(screen.text.getText())/100;
            		screen.text.setText(String.valueOf(number)); 

            	 }
             }
             
          
	
	
	
	
	
	
	
	
	
		
	

	
}