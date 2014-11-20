/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apartmentlab5;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * creates the main menu gui
 * @author Owner
 */
public class MainMenuGui extends JFrame {
    
    private JPanel panel;
    private JButton button[];
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem openMenuItem;
    private JMenuItem exitMenuItem;
    private GridBagLayout layout;
    private GridBagConstraints c;
    private int which;
    private MainMenuGui copyMenu;
    private ShowTable Apt;
    private ShowTable Emp;
    private ShowTable Tenants;
    private InputEmployee EmpInput;
    private InputTenant TenantInput;
    private TerminateLease TermLeaseGui;
    private FireEmployee fireEmployeeGui;
    private ShowTable bankGui;
    private PayEmployee payEmployeeGui;
    private JFileChooser fc;
    private ApartmentComplex AptComplex;
    
    
    
    
    /**
     * 
     * @param A
     */
    public MainMenuGui(ApartmentComplex A)
            
            
    {
        super("Apurrrrrtment Complex Management System");
        AptComplex = A;
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        try{
        BufferedImage kittens = ImageIO.read(new File("iconKitty.jpg"));        
        this.setIconImage(kittens);

        }
        catch(IOException e)
        {
            
        }
        
        
        panel = new JPanel();
        layout = new GridBagLayout();
        panel.setLayout(layout);
        c = new GridBagConstraints();

        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        fileMenu.setMnemonic('F');
        openMenuItem = new JMenuItem("Open");
        openMenuItem.setMnemonic('O');
        openMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                fc = new JFileChooser();
                int val = fc.showOpenDialog(MainMenuGui.this);
                
                if(val == JFileChooser.APPROVE_OPTION)
                {
                    File file = fc.getSelectedFile();
                    
                    Scanner input;
                    try {
                        input = new Scanner(file);
                        AptComplex.readFile(AptComplex, input);
                        JOptionPane.showMessageDialog(null, "File has been read.");
                        
                    } catch (FileNotFoundException ex) {
                        JOptionPane.showMessageDialog(null, "File not found!!");
                    }
                       catch (ParseException ex) {
                        JOptionPane.showMessageDialog(null, "Can't parse the file");
                    }
                    
                }
            }
        });
        exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setMnemonic('E');
        addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {
                
            }

            @Override
            public void windowClosing(WindowEvent e) {
               int n = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?","Message",JOptionPane.YES_NO_OPTION);
                if(n == JOptionPane.YES_OPTION)
                {
                    JOptionPane.showMessageDialog(null, "Thank you for using Apurrrrrtment Complex Management System");
                    dispose(); 
                    System.exit(0); 
                     
                }
                else
                {
                   
                }
            }

            @Override
            public void windowClosed(WindowEvent e) {
                //dispose();
                //throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void windowIconified(WindowEvent e) {
                //t//hrow new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
               // throw new UnsupportedOperationException("Not supported yet.");
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
        
        exitMenuItem.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
            
                int n = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?","Message",JOptionPane.YES_NO_OPTION);
                if(n == JOptionPane.YES_OPTION)
                {
                    JOptionPane.showMessageDialog(null, "Thank you for using Apurrrrrtment Complex Management System");
                    dispose();
                    System.exit(0);
                }
                else
                {
                    
                }
            }
        });
        
        
        fileMenu.add(openMenuItem);
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
        
       
        String [] buttonTitles =  {"Lease Apartment","Terminate Lease","Hire Employee","Pay an Employee","Fire Employee","Display Employees","Display Tenants","Display Apartments","Display Bank Acct. Info","Collect rent from Tenants","Apartment Lease History"};
        button = new JButton[buttonTitles.length];
        c.gridx = 0;
        c.gridy = 0;
        c.ipadx = 34;
        c.anchor = GridBagConstraints.CENTER;
        for (int i = 0; i < buttonTitles.length; i++) {
            button[i] = new JButton(buttonTitles[i]);
            button[i].setMaximumSize(new Dimension(180,25));
            button[i].setPreferredSize(new Dimension(150, 25));
            button[i].setMinimumSize(new Dimension(125,25));
            button[i].addActionListener(new ButtonHandler(i,A));
            c.gridy = i;
            
            panel.add(button[i],c);
            
            
        }

        
        

        add(panel);
        setSize(400,400);
        this.setLocationRelativeTo(null);
        
        setVisible(true);
        copyMenu = this;
        
    }

    /**
     *
     * @return
     */
    public ShowTable getApt() {
        return Apt;
    }

    /**
     *
     * @return
     */
    public ShowTable getEmp() {
        return Emp;
    }
    
    
   

   private class ButtonHandler implements ActionListener {

        private int which;
        private ApartmentComplex AptComplex;
        
        
        public ButtonHandler(int i,ApartmentComplex A) {
            AptComplex = A;
            which = i;

        }

        public int getWhich() {
            return which;
        }

        
        
        @Override
        public void actionPerformed(ActionEvent e) {
            
        String[] columnNamesTen = {"Apt #", "Last Name", "First Name", "Age", "Gender","Monthly Payment","Carport","Garage","Lease Period","Lease End Date"};    
        String[] columnNamesEmp = {"ID No.", "Last Name", "First Name", "Salary", "Occupation"}; 
        String[] columnNamesApt = {"Apt #", "Area", "Bedrooms", "Bathrooms", "Patio","Occupied","Monthly Rent"};
        String[] columnNamesBank = {"Last Name ", "First Name", "Balance","Employee or Tenants"};
        String[] columnNamesOldTen = {"Apt #", "Last Name", "First Name", "Age", "Gender","Monthly Payment","Carport","Garage","Termination Date"}; 
        AptComplex.sortEmployees();
        AptComplex.sortTenants();
       
        switch (which) {
                case 0:
                                       
                    //LEASE APARTMENT BUTTON
                    
                    TenantInput = new InputTenant(copyMenu,AptComplex); // Begins lease process by asking for tenant input
                    TenantInput.setVisible(true);
                    setVisible(false);
                    break;                    
                    

                case 1:
                    //TERMINATE LEASE BUTTON
                    setVisible(false);
                    
                    Tenants = new ShowTable(copyMenu, AptComplex, AptComplex.getTenantData(), columnNamesTen, "Tenant Info");                    
                    TermLeaseGui = new TerminateLease(copyMenu,Tenants,AptComplex);
        
                    setVisible(false);
                    
                    break;
 
                case 2:
                                       
                    //HIRE EMPLOYEE BUTTON
                    setVisible(false);
                   
                    EmpInput = new InputEmployee(copyMenu,AptComplex);
                    
                    break;

                case 3:
                    //PAY EMPLOYEE BUTTON
                    setVisible(false);
                    Emp = new ShowTable(copyMenu, AptComplex, AptComplex.getEmpData(), columnNamesEmp, "Employee Info");
                    payEmployeeGui = new PayEmployee(copyMenu,Emp,AptComplex);
                    
                    break;
                    
                case 4:
                    //FIRE EMPLOYEE BUTTON
                    setVisible(false);
                    Emp = new ShowTable(copyMenu, AptComplex, AptComplex.getEmpData(), columnNamesEmp, "Employee Info");
                    fireEmployeeGui = new FireEmployee(copyMenu,Emp,AptComplex);
                    
                    
                    break;

                case 5:
                     setVisible(false);
                     // DISPLAYS EMPLOYEE
                    Emp = new ShowTable(copyMenu, AptComplex, AptComplex.getEmpData(), columnNamesEmp, "Employee Info");
                    Emp.getFrame().setVisible(true);
                    break;
 
                case 6:
                    setVisible(false);  // DISPLAYS TENANTS  
                    Tenants = new ShowTable(copyMenu, AptComplex, AptComplex.getTenantData(), columnNamesTen, "Tenant Info");
                    Tenants.getFrame().setVisible(true);
                    break;                   
                case 7:
                    //DISPLAYS APARTMENT INFO
                    setVisible(false);
                    //AptComplex.ShowTable(AptComplex.getAptData(), columnNames, "Apartment Info");
                    Apt = new ShowTable(copyMenu, AptComplex, AptComplex.getAptData(), columnNamesApt, "Apartment Info");
                    Apt.getFrame().setVisible(true);
                    break;

                case 8:
                    //DISPLAY BANK INFO
                    setVisible(false);
                    bankGui = new ShowTable(copyMenu,AptComplex,AptComplex.getBankData(),columnNamesBank,"Bank Account info");
                    bankGui.getFrame().setVisible(true);
                    
                    break;
                    
                case 9:
                    setVisible(false);
                    ShowTable tenants = new ShowTable(copyMenu, AptComplex, AptComplex.getTenantData(), columnNamesTen, "Tenant Info");
                    CollectRent rentGui = new CollectRent(copyMenu,tenants,AptComplex);
                   
                    break;
                    
                case 10:
                    //APT. LEASE HISTORY
                    
                    setVisible(false);
                    ShowTable oldTenants = new ShowTable(copyMenu, AptComplex, AptComplex.getOldTenantData(), columnNamesOldTen, "Apartment History");
                    oldTenants.getFrame().setVisible(true);
                    
                    break;
                    
                default:
                    
                    throw new AssertionError();
            }
           // throw new UnsupportedOperationException("Not supported yet.");
        }


    
    
    
    }
    
}
