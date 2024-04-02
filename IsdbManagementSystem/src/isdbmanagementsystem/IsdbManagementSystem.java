/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package isdbmanagementsystem;

import java.util.logging.Level;
import java.util.logging.Logger;
import view.LogInForm;
import view.Progressbar;

/**
 *
 * @author User
 */
public class IsdbManagementSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
          LogInForm form=new LogInForm();
      
        
        Progressbar pb=new Progressbar();
        pb.setVisible(true);
        pb.setLocationRelativeTo(null);
        for(int i=1; i<=100; ++i){
            try {
                Thread.sleep(30);
                pb.progressbar.setValue(i);
                if(i%2==0){
                pb.pleaseWait.setText("Please Wait..");
                
                }
                else{
                pb.pleaseWait.setText("Please Wait...");
                }
                if(i==100){
                
                pb.setVisible(false);
                 form.setVisible(true);
        form.setLocationRelativeTo(null);
                
                }
                
            } catch (InterruptedException ex) {
                Logger.getLogger(IsdbManagementSystem.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        
        
        
        
        
    }
    
}
