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
import java.text.DecimalFormat;
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

/**creates gui for collecting rent
 *
 * @author Owner
 */
public class CollectRent extends JFrame{
        
    private JPanel panel2;
    private JButton but;
    private JComboBox input;
    private JLabel title;
    private JLabel label;
    private int tenantSize;
    private ApartmentComplex AptComplex;
    private int[] tenIndex;
    private ShowTable TT;
    private MainMenuGui menu;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem GoToMM;
    private DecimalFormat df = new DecimalFormat("0.00");
    
    public CollectRent(MainMenuGui M, ShowTable TenTables, ApartmentComplex A) {
        
        super("Collect Rent");
        AptComplex = A;
        menu = M;
        TT = TenTables;
        this.setLayout(new GridLayout(2,0));
        panel2 = new JPanel();
        this.add(TenTables.getNewContentPane());
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
                dispose();
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
        
        title = new JLabel("Choose the Tenant you'd like to collect from");

        
        tenantSize = A.getNumOfTenants();
        String[] IDNumString ;
        tenIndex = new int[tenantSize];

        if(tenantSize > 0)
        {
            IDNumString = new String[tenantSize];
            for (int i = 0; i < tenantSize; i++) {
                IDNumString[i] = A.getTenantList().get(i).getFirstName()+" "+A.getTenantList().get(i).getLastName();
                tenIndex[i] = i;
                
            }
        }
        else
        {
            IDNumString = new String[1];
            IDNumString[0] = "No Tenants";
            
        }
        input = new JComboBox(IDNumString);
        label = new JLabel("Select Tenant");
        
        but = new JButton("Collect Rent");
        but.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
            
                
                if(tenantSize == 0)
                {
                   JOptionPane.showMessageDialog(null, "The Apartment Complex has no Tenants!"); 
                   //dispose();
                }
                else
                {   
                    
                    int num = tenIndex[input.getSelectedIndex()];
                    
                    String output = "Would you like to collect rent from "+AptComplex.getTenantList().get(num).getFirstName()+" "+AptComplex.getTenantList().get(num).getLastName()+"?";
                    int n = JOptionPane.showConfirmDialog(null, output,"Message",JOptionPane.OK_CANCEL_OPTION);
                    
                    if(n == JOptionPane.OK_OPTION)
                    {
            
                        if(AptComplex.collectRent(num))
                        {
                            JOptionPane.showMessageDialog(null, AptComplex.getTenantList().get(num).getFirstName()+" "+AptComplex.getTenantList().get(num).getLastName()+" does not have the proper funds in his/her bank Account");
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, AptComplex.getTenantList().get(num).getFirstName()+" "+AptComplex.getTenantList().get(num).getLastName()+"'s rent has been collected\nOperating Budget is up to: $" +df.format(AptComplex.getBalance()));
                        }

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
        BufferedImage kittens = ImageIO.read(new File("urKitty.jpg"));
        JLabel picLabel = new JLabel(new ImageIcon(kittens));
        picLabel.setOpaque(true);

        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 2.0;
        c.weighty = 2.0;
        c.gridheight = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0,0,10,0);
        c.anchor = GridBagConstraints.CENTER;
        panel2.add(picLabel,c);
        }
        catch(IOException e)
        {
            
        }
        
        

        add(panel2);
        setVisible(true);
        this.setSize(900, 700);   
        setLocationRelativeTo(null);
        
    }    
    
}
