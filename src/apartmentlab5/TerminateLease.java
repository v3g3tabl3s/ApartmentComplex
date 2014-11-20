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
 * creates gui for the terminating a lease
 * @author Owner
 */
public class TerminateLease extends JFrame {
    
    private JPanel panel2;
    private JButton but;
    private JComboBox input;
    private JLabel title;
    private int tenantSize;
    private ApartmentComplex AptComplex;
    private int[] aptNums;
    private ShowTable TT;
    private MainMenuGui menu;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem GoToMM;
    
    
    
    /**
     *
     *  
     * @param M
     * @param TenantTable
     * @param A
     */
    public TerminateLease(MainMenuGui M,ShowTable TenantTable,ApartmentComplex A)
    {
        
        super("Termainte Lease");
        AptComplex = A;
        menu = M;
        TT = TenantTable;
        this.setLayout(new GridLayout(2,0));
        panel2 = new JPanel();
        this.add(TenantTable.getNewContentPane());
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
                menu.setVisible(true);
                dispose();
            }

            @Override
            public void windowClosed(WindowEvent e) {
                //menu.setVisible(true);
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
        
        title = new JLabel("Choose Apartment and click Terminate Lease, to terminate the lease");

        
        tenantSize = A.getNumOfTenants();
        String[] aptNumString ;
        
        aptNums = new int[tenantSize];
        if(tenantSize > 0)
        {
            aptNumString = new String[tenantSize];
            for (int i = 0; i < tenantSize; i++) {
                aptNumString[i] = "Apartment #"+ A.getTenantList().get(i).getAptNum();
                aptNums[i] = A.getTenantList().get(i).getAptNum();
                
            }
        }
        else
        {
            aptNumString = new String[1];
            aptNumString[0] = "No Tenants";
            
        }
        input = new JComboBox(aptNumString);
        
        
        but = new JButton("Terminate Lease");
        but.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
            
                JFrame frame = new JFrame();
                if(tenantSize == 0)
                {
                   JOptionPane.showMessageDialog(null, "The Apartment Complex is a little empty, No Tenants!"); 
                   String[] columnNamesTen = {"Apt #", "Last Name", "First Name", "Age", "Gender","Monthly Payment","Carport","Garage","Lease Period","Lease End Date"};    
                   TT = new ShowTable(menu,AptComplex,AptComplex.getTenantData(),columnNamesTen,"Tenant Info");
                   TerminateLease newGui = new TerminateLease(menu,TT,AptComplex);
                   dispose();
                }
                else
                {   
                    
                    int num = input.getSelectedIndex();
                    String output = "Would you like "+AptComplex.getTenantList().get(num).getFirstName()+" "+AptComplex.getTenantList().get(num).getLastName()+" removed from Apartment #"+aptNums[num]+"?";
                    int n = JOptionPane.showConfirmDialog(null, output,"Message",JOptionPane.OK_CANCEL_OPTION);
                    
                    if(n == JOptionPane.OK_OPTION)
                    {
                        JOptionPane.showMessageDialog(null, "The Lease for "+AptComplex.getTenantList().get(num).getFirstName()+" "+AptComplex.getTenantList().get(num).getLastName()+ " has been terminated.");
                        AptComplex.removeTenant(aptNums[num]);
                        String[] columnNamesTen = {"Apt #", "Last Name", "First Name", "Age", "Gender","Monthly Payment","Carport","Garage","Lease Period","Lease End Date"};    
                       
                        TT = new ShowTable(menu,AptComplex,AptComplex.getTenantData(),columnNamesTen,"Tenant Info");
                        TerminateLease newGui = new TerminateLease(menu,TT,AptComplex);
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
        c.ipadx = 100;
        c.insets = new Insets(50,150,50,10);
        c.anchor = GridBagConstraints.CENTER;
        panel2.add(input,c);
        
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 1;
        panel2.add(but,c);
        
        try{
        BufferedImage kittens = ImageIO.read(new File("cuteKittens.jpg"));
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
        this.setLocationRelativeTo(null);

        setVisible(true);
        
        
    }
    
}
