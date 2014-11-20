/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apartmentlab5;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *creates the gui for firing employees
 * @author Owner
 */
class FireEmployee extends JFrame {
    
    private JPanel panel2;
    private JButton but;
    private JComboBox input;
    private JLabel title;
    private JLabel label;
    private int empSize;
    private ApartmentComplex AptComplex;
    private int[] IDNums;
    private ShowTable TT;
    private MainMenuGui menu;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem GoToMM;
    
/**
 * 
 * @param M
 * @param empTables
 * @param A 
 */
    public FireEmployee(MainMenuGui M, ShowTable empTables, ApartmentComplex A) {
        
        super("Fire Employee");
        AptComplex = A;
        menu = M;
        TT = empTables;
        this.setLayout(new GridLayout(2,0));
        panel2 = new JPanel();
        this.add(empTables.getNewContentPane());
        panel2.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        menuBar = new JMenuBar();
        fileMenu = new JMenu("Back");
        GoToMM = new JMenuItem("Back to Main Menu");
        fileMenu.setMnemonic('B');
        fileMenu.add(GoToMM);
        GoToMM.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                    dispose();
                    menu.setVisible(true);               
                

            }
        });
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
        
        addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void windowClosing(WindowEvent e) {
                //JOptionPane.showMessageDialog(null, "Going Back to the Main Menu");
               dispose();
               menu.setVisible(true);
               
            }

            @Override
            public void windowClosed(WindowEvent e) {
               // menu.setVisible(true);
                
            }

            @Override
            public void windowIconified(WindowEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
               /// throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void windowActivated(WindowEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
               // throw new UnsupportedOperationException("Not supported yet.");
            }
        });
        
        title = new JLabel("Choose the proper ID number of the employee you'd like removed");

        
        empSize = A.getNumOfEmployees();
        String[] IDNumString ;
        
        IDNums = new int[empSize];
        if(empSize > 0)
        {
            IDNumString = new String[empSize];
            for (int i = 0; i < empSize; i++) {
                IDNumString[i] = "ID #"+ A.getEmployeeList().get(i).getIDnum();
                IDNums[i] = A.getEmployeeList().get(i).getIDnum(); //
                
            }
        }
        else
        {
            IDNumString = new String[1];
            IDNumString[0] = "No Employees";
            
        }
        input = new JComboBox(IDNumString);
        label = new JLabel("Select Employee number");
        
        but = new JButton("Fire Employee");
        but.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
            
                
                if(empSize == 0)
                {
                   JOptionPane.showMessageDialog(null, "The Apartment Complex has no employees!"); 
                   String[] columnNamesEmp = {"ID No.", "Last Name", "First Name", "Salary", "Occupation"};    
                   TT = new ShowTable(menu,AptComplex,AptComplex.getEmpData(),columnNamesEmp,"Employee Info");
                   FireEmployee newGui = new FireEmployee(menu,TT,AptComplex);
                   dispose();
                }
                else
                {   
                    
                    int num = input.getSelectedIndex();
                    String output = "Would you like "+AptComplex.getEmployeeList().get(num).getFirstName()+" "+AptComplex.getEmployeeList().get(num).getLastName()+" to be fired?";
                    int n = JOptionPane.showConfirmDialog(null, output,"Message",JOptionPane.OK_CANCEL_OPTION);
                    
                    if(n == JOptionPane.OK_OPTION)
                    {
                        JOptionPane.showMessageDialog(null, AptComplex.getEmployeeList().get(num).getFirstName()+" "+AptComplex.getEmployeeList().get(num).getLastName()+" has been removed");
                        AptComplex.removeEmployee(IDNums[num]);
                        String[] columnNamesEmp = {"ID No.", "Last Name", "First Name", "Salary", "Occupation"}; 
                        TT = new ShowTable(menu,AptComplex,AptComplex.getEmpData(),columnNamesEmp,"Employee Info");
                        FireEmployee newGui = new FireEmployee(menu,TT,AptComplex);
                        dispose();

                    }
                    
                   if((n == JOptionPane.CANCEL_OPTION) || (n == JOptionPane.CLOSED_OPTION))
                    {
                        
                    }
                    
                    
                
                }
                
            }
        });
        
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.SOUTH;
        c.insets = new Insets(60,140,0,0);
        panel2.add(label,c);
        
        c.gridx = 0;
        c.gridy = 1;
        c.ipadx = 100;
        c.insets = new Insets(40,140,50,10);
        c.anchor = GridBagConstraints.CENTER;
        panel2.add(input,c);
        
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 2;
        panel2.add(but,c);
        
        try{
        BufferedImage kittens = ImageIO.read(new File("firedCat.jpg"));
        JLabel picLabel = new JLabel(new ImageIcon(kittens));
        picLabel.setOpaque(true);
        
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridheight = 0;
        c.insets = new Insets(0,0,0,0);
        c.anchor = GridBagConstraints.EAST;
        panel2.add(picLabel,c);
        }
        catch(IOException e)
        {
            
        }
        
        

        add(panel2);
        
        this.setSize(900, 700);
        setLocationRelativeTo(null);
        setVisible(true);
        
    }
    
}
