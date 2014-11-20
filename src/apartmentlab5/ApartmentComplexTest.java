/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apartmentlab5;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Owner
 */
 
public class ApartmentComplexTest implements Constants {

     /**
     * Main method
     * @param args
     * @throws FileNotFoundException
     * @throws ParseException
     */
    public static void main(String[] args) throws FileNotFoundException, ParseException {
      
        ApartmentComplex A = new ApartmentComplex();
        
              
        
        try
        {
        Scanner inFile = new Scanner(new File(FILE_NAME));
        A.readFile(A,inFile);
        }
        catch(FileNotFoundException e)
        {
            JOptionPane.showMessageDialog(null, FILE_NAME+ " was not found!!");
            return;
        } 
        
        
        
        
        
        MainMenuGui M = new MainMenuGui(A);

    }
    
    /**
     *
     * @param A
     * @param inFile
     * @return false if successful
     * @throws FileNotFoundException
     * @throws ParseException
     */

    
    
  

}