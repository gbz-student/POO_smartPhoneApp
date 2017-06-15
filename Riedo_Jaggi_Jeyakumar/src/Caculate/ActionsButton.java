package Caculate;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;


 public class ActionsButton extends JPanel {
	
	private JPanel b = new JPanel();
	private ActionsScreen screen = new ActionsScreen();
	
	// creation des elements a afficher
    private String elements [] = {"AC","DEL","ON","÷","7","8","9","×","4","5","6","-","1","2","3","+","+/-","0",".","="};
    
	 // creation des boutons par element
    private JButton but[] = new JButton[elements.length];
    
 	// agrandissement du caractere du bouton
    private Font f = new Font("Serif", Font.PLAIN, 40);
     
    private Dimension bsize = new Dimension(70,70);
	
	private GridLayout cr = new GridLayout();
	 
	private double number;
	private boolean clicOperateur = false, update = false;
	private String operateur;
	private int cpt = 0;
	
	
	/**
	 * constructeur permettant de positionner les boutons, de les personnaliser, et d'ajouter un ActionsScreen
	 * @author ashan
	 * 
	 */
     
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
    	  
    	// coloriage des boutons
    	   coloriageBut();
    	   
    	// operations
    	   calculBut();
	
  		// ajout des boutons dans le Panel 
    	   addBut();
  
    	   this.add(screen, BorderLayout.NORTH);
    	   this.add(b, BorderLayout.CENTER);
     }
     	   
     /**
      * methode permettant d'initialiser, d'agrandir la taille et d'agrandir la taille du caractere des boutons
      * @author ashan
      * 
      */
     public void initBut(){
    	 
    	 for (int i = 0; i < but.length; i++) {
 			
 			but[i] = new JButton(elements[i]);
 			
 			but[i].setPreferredSize(bsize);;
 			
 			but[i].setFont(f);
 			
 	   }
     }
     
    /**
     * methode permettant de colorier les boutons
     * @author ashan
     * 
     */
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
      
      /**
       * methode permettant d'affecter ActionListner aux boutons
       * @author ashan
       * 
       */
             public void calculBut(){
            	 
            	 for (int i = 0; i < elements.length; i++) {
					
            		switch(i){
            		 case 0 :
           	          but[i].addActionListener(new ResetListener());
           	          break;
            		  case 1 : 
            		  but[i].addActionListener(new DeleteListner());  
            	      break;
            		  case 2 :  
            		  but[i].addActionListener(new OnOffListner());    
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
             
             /**
              * methode permettant d'ajouter les boutons sur le JPanel
              */
             
             public void addBut(){
            	 
            	 for (int i = 0; i < but.length; i++) {
             		
     			    b.add(but[i]);	   
     		} 
            	 }
           
             /**
              * Méthode permettant d'effectuer un calcul selon l'opérateur sélectionné
              * @author ashan
              */
            
             private void calculOperateurs(){
              
               if(operateur=="+"){
                 number = number+Double.valueOf(screen.text.getText()).doubleValue();
                 screen.text.setText(String.valueOf(number));
               }
               if(operateur=="-"){
                 number = number-Double.valueOf(screen.text.getText()).doubleValue();
                 screen.text.setText(String.valueOf(number));  
               }          
               if(operateur=="×"){
                 number = number*Double.valueOf(screen.text.getText()).doubleValue();
                 screen.text.setText(String.valueOf(number));
               }     
               if(operateur=="÷"){
            	 
                 number = number/Double.valueOf(screen.text.getText()).doubleValue();
                
                 if(Double.valueOf(screen.text.getText()).doubleValue()==0){
                	 
                	 screen.text.setText(String.valueOf("Erreur"));
                	 
                 }else{
                	 
                	 screen.text.setText(String.valueOf(number));
                 }
                	 
                   
               }
               
             }
             
             /**
              * Listener affecté aux boutons des chiffres
              * @author ashan
              *
              */
             class NumberListener implements ActionListener {
               public void actionPerformed(ActionEvent e){
            
                 String stk = ((JButton)e.getSource()).getText();
                 if(update){
                   update = false;
                 }
                 else{
                   if(!screen.text.getText().equals("0"))
                     stk = screen.text.getText() + stk;
                 }
                 screen.text.setText(stk);
               }
             }
             
             /**
              * Listener affecté au bouton +
              * @author ashan
              *
              */
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

             /**
              * Listener affecté au bouton -
              * @author ashan
              *
              */
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
             
             /**
              * Listener affecté au bouton ×
              * @author ashan
              *
              */

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
                 operateur = "×";
                 update = true;
               }
             }

             /**
              * Listener affecté au bouton ÷
              * @author ashan
              *
              */
             class DivListener implements ActionListener {
               public void actionPerformed(ActionEvent arg0){
                 if(clicOperateur){
                   calculOperateurs();
                   //DevisionZero();
                   screen.text.setText(String.valueOf(number));
                 }
                 else{
                   number = Double.valueOf(screen.text.getText()).doubleValue();
                   clicOperateur = true;
                 }
                 operateur = "÷";
                 update = true;
               }
             }
             
            /**
             * Listener affecté au bouton AC
             * @author ashan
             *
             */
           
             class ResetListener implements ActionListener {
               public void actionPerformed(ActionEvent arg0){
                 clicOperateur = false;
                 update = true;
                 number = 0;
               //  operateur = "";
                 screen.text.setText("0");
               }
             }    
             
             /**
              * Listener affecté au bouton off
              * @author ashan
              *
              */
	
             class OnOffListner implements ActionListener{
            	 public void actionPerformed(ActionEvent arg0){
            		 
            	        cpt++;
            	        
            		
            		for (int i = 0; i < but.length; i++) {
						
            			if(cpt%2==0){
            				
            			
            				but[i].setEnabled(true);
            				screen.text.setEnabled(true);
            				
            			}else{
            				
            				but[2].setEnabled(true);
            				but[i].setEnabled(false);
            				screen.text.setEnabled(false);		
            			}		
            			
					}
            	 }
             }
             
             /**
              *  Listener affecté au bouton -+
              * @author ashan
              *
              */
            	 
             class NegatifListner implements ActionListener{
            	 public void actionPerformed(ActionEvent e){
            		
            		Object src = e.getSource();
            		
        			if(src==but[16])
        			screen.text.setText(""+Double.parseDouble(screen.text.getText())*-1);
            		
            	 }
            	 }
            	 
             /**
              * Listener affecté au bouton DEL
              * @author ashan
              *
              */  
             class DeleteListner implements ActionListener{
            	 public void actionPerformed(ActionEvent e){
            		 
            		Object src = e.getSource();
            		
            		if(src==but[1])
           			screen.text.setText(screen.text.getText().substring(0,screen.text.getText().length()-1));

            	 }
             }
             
             /**
              * Listener affecté au bouton =
              * @author ashan
              *
              */
             
             class EgalListener implements ActionListener {
               public void actionPerformed(ActionEvent arg0){
            	 calculOperateurs();
                 update = true;
                 clicOperateur = false;
               }
             }
 
}