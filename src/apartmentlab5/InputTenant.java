/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apartmentlab5;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

/**
 * Creates the gui for inputting tenants
 * @author Owner
 */
public class InputTenant extends JFrame {
    
    
    private JPanel panel;
    private GridBagLayout layout;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem GoToMM;
    private JTextField input1; // first name
    private JTextField input2; // last name
    private JSpinner input3; // age
    private JComboBox input4;  // gender
    private JComboBox input5; // Car port yes or No
    private JComboBox input6;  // garage yes or no
    private JTextField input7;  // inital acct balance    
    private JComboBox input8; //a list of unoccupied Apartments
    private JSpinner input9; //lease period
    
    private JLabel title;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JLabel label8;  // choose an Apartment
    private JButton button;
    private GridBagConstraints c;
    private Tenant tenant;
    private JPopupMenu popup;
    private ApartmentComplex AptComplex;
    private MainMenuGui menu;
    private InputApt aptMenu;
    
    /**
     * 
     * @param M
     * @param A
     */
    public InputTenant(MainMenuGui M,ApartmentComplex A)
    {
        super("Create Tenant");
        AptComplex = A;
        menu = M;
        int sizeApts;   // number of unoccupied apartments
        
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {
                
            }

            @Override
            public void windowClosing(WindowEvent e) {
                
                int n = JOptionPane.showConfirmDialog(null, "Input won't be saved.\nAre you sure you want to go back to the Main Menu","Message",JOptionPane.OK_CANCEL_OPTION);
               if(n == JOptionPane.OK_OPTION)
                {
                    dispose();
                    menu.setVisible(true);               
                }    

            }

            @Override
            public void windowClosed(WindowEvent e) {
              
            }

            @Override
            public void windowIconified(WindowEvent e) {
               
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                
            }

            @Override
            public void windowActivated(WindowEvent e) {
                
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                
            }
        });
        
        tenant = new Tenant();
        panel = new JPanel();
        layout = new GridBagLayout();
        panel.setLayout(layout);
        
        menuBar = new JMenuBar();
        fileMenu = new JMenu("Back");
        GoToMM = new JMenuItem("Back to Main Menu");
        fileMenu.setMnemonic('B');
        fileMenu.add(GoToMM);
        GoToMM.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int n = JOptionPane.showConfirmDialog(null, "Input won't be saved.\nAre you sure you want to go back to the Main Menu","Message",JOptionPane.OK_CANCEL_OPTION);
                if(n == JOptionPane.OK_OPTION)
                {
                    dispose();
                    menu.setVisible(true);               
                }


            }
        });
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
        
        title = new JLabel("New Tenant");
        label1 = new JLabel("First Name");
        label2 = new JLabel("Last Name");
        label3 = new JLabel("Age");
        label4 = new JLabel("Sex");
        label5 = new JLabel("Car Port");
        label6 = new JLabel("Garage");
        label7 = new JLabel("Initial Account Balance");
        label8 = new JLabel("Choose an Apartment");
        
        input1 = new JTextField(20);
        input2 = new JTextField(20);
        SpinnerModel model = new SpinnerNumberModel(18,18,99,1);
        input3 = new JSpinner(model);        
        String[] gender = {"MALE","FEMALE"};        
        input4 = new JComboBox(gender);
        String[] options = {"YES", "NO"};
        input5 = new JComboBox(options);
        input6 = new JComboBox(options);
        input7 = new JTextField(20);
        sizeApts = AptComplex.numberOfUnoccupiedApts();
        
        if(sizeApts > 0)
        {
        String[] unoccupiedApts;
        unoccupiedApts = new String[sizeApts];
        int j=0;
        int AptSize = AptComplex.getNumOfApts();
        int num;
        for (int i = 0; i < AptSize; i++) 
        {
           num = AptComplex.getAptList().get(i).getAptNum();
           
           if(AptComplex.checkUnoccupiedAptNum(num) == false) // checks for unoccupied apartments
           {
                unoccupiedApts[j++] = "Apartment "+ num;
                
           }
        }
        input8 = new JComboBox(unoccupiedApts);
        SpinnerModel model3 = new SpinnerNumberModel(6,1,36,1);
        input9 = new JSpinner(model3);
        
        input8.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                float bal;
                int count = 0;  // count of joptionpanes that opened
               
               if("".equals(input1.getText()))
               {
                    JOptionPane.showMessageDialog(null, "No First Name Entered");
                    count++;
               }
               else
               {
                   tenant.setFirstName(input1.getText());
               }
               if("".equals(input2.getText()))
               {
                   JOptionPane.showMessageDialog(null, "No Last Name Entered");
                   count++;
               }
               else
               {
                   tenant.setLastName(input2.getText());
               }

               tenant.setAge((int)input3.getValue());
               
               tenant.setGender((String)input4.getSelectedItem());
               tenant.setCarPort((String)input5.getSelectedItem());
               tenant.setGarage((String)input6.getSelectedItem());
               
               if("".equals(input7.getText()))
               {
                   JOptionPane.showMessageDialog(null, "No Balance Entered");
                   count++;
               }
               else
               {
                   try{
                   bal = Float.parseFloat(input7.getText());
                   if(tenant.setAccount(bal))
                   {
                       JOptionPane.showMessageDialog(null, "No Negative Balance!!!");
                       count++;
                   }

                   }
                   
                   catch(NumberFormatException error)
                   {
                       JOptionPane.showMessageDialog(null, "Wrong Input format for the Balance");
                       count++;
                   }  

               } 
               
               if(count == 0)
               {
                     int option = JOptionPane.showOptionDialog(null,input9,"Enter the Lease Period in Months",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
                     if(option == JOptionPane.OK_OPTION)
                     {
                        int leasePer = (int)input9.getValue();
                        String str = (String)input8.getSelectedItem();
                        Scanner name = new Scanner(str);
                        int num = 0;
                        name.next();
                        num = name.nextInt();
                        
                        AptComplex.lease(tenant, num, leasePer,true);
                        dispose();
                        menu.setVisible(true);
                     }
                     
  
               }
                             
                }
            });
        
        
        }
        
        else
        {
            String[] none = {"None Available"};
            input8 = new JComboBox(none);
        }
        
        

        button = new JButton("Create Apartment");
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                float bal;
                int count = 0;  // count of joptionpanes that opened
               
               if("".equals(input1.getText()))
               {
                    JOptionPane.showMessageDialog(null, "No First Name Entered");
                    count++;
               }
               else
               {
                   tenant.setFirstName(input1.getText());
               }
               if("".equals(input2.getText()))
               {
                   JOptionPane.showMessageDialog(null, "No Last Name Entered");
                   count++;
               }
               else
               {
                   tenant.setLastName(input2.getText());
               }

               tenant.setAge((int)input3.getValue());
               
               tenant.setGender(input4.getName());
               
               tenant.setCarPort((String)input5.getSelectedItem());
               tenant.setGarage((String)input6.getSelectedItem());
               
               if("".equals(input7.getText()))
               {
                   JOptionPane.showMessageDialog(null, "No Balance Entered");
                   count++;
               }
               else
               {
                   try{
                   bal = Float.parseFloat(input7.getText());
                   if(tenant.setAccount(bal))
                   {
                       JOptionPane.showMessageDialog(null, "No Negative Balance!!!");
                       count++;
                   }

                   }
                   
                   catch(NumberFormatException error)
                   {
                       JOptionPane.showMessageDialog(null, "Wrong Input format for the Balance");
                       count++;
                   }  

               } 
               
               if(count == 0)
               {
                     dispose();
                     aptMenu = new InputApt(AptComplex,menu,tenant);
                     
  
               }
            }

            
        });
        
        c = new GridBagConstraints();
        
        c.gridx = 0;
        c.gridy = 0;
        c.ipady = 40;
        c.ipadx = 10;
        c.anchor = GridBagConstraints.CENTER;
        
        panel.add(title,c);        
        
        c.gridx = 0;
        c.gridy = 1;
        c.ipady = 40;
        c.ipadx = 10;
        c.anchor = GridBagConstraints.CENTER;
        
        panel.add(label1,c);
        
        c.gridx = 0;
        c.gridy = 2;
        c.anchor = GridBagConstraints.CENTER;
        panel.add(label2,c);       
        
        c.gridx = 0;
        c.gridy = 3;
        c.anchor = GridBagConstraints.CENTER;
        panel.add(label3,c); 
        
        c.gridx = 0;
        c.gridy = 4;
        c.anchor = GridBagConstraints.CENTER;
        panel.add(label4,c);
        
        c.gridx = 0;
        c.gridy = 5;
        c.anchor = GridBagConstraints.CENTER;
        panel.add(label5,c);
        
        c.gridx = 0;
        c.gridy = 6;
        c.anchor = GridBagConstraints.CENTER;
        panel.add(label6,c);
        
        c.gridx = 0;
        c.gridy = 7;
        c.anchor = GridBagConstraints.CENTER;
        panel.add(label7,c);  
 
        
        c.gridx = 1;
        c.gridy = 1;
        c.ipadx = 0;
        c.ipady = 0;
        c.anchor = GridBagConstraints.CENTER;
        panel.add(input1,c);

        c.gridx = 1;
        c.gridy = 2;
        c.anchor = GridBagConstraints.CENTER;
        panel.add(input2,c);        
        
        c.gridx = 1;
        c.gridy = 3;
        c.anchor = GridBagConstraints.CENTER;
        panel.add(input3,c);  
        
        c.gridx = 1;
        c.gridy = 4;
        c.anchor = GridBagConstraints.CENTER;
        panel.add(input4,c);
        
        c.gridx = 1;
        c.gridy = 5;
        c.anchor = GridBagConstraints.CENTER;
        panel.add(input5,c);
        
        c.gridx = 1;
        c.gridy = 6;
        c.anchor = GridBagConstraints.CENTER;
        panel.add(input6,c);        
        
        c.gridx = 1;
        c.gridy = 7;
        c.anchor = GridBagConstraints.CENTER;
        panel.add(input7,c);    

        c.gridx = 0;
        c.gridy = 9;
        panel.add(label8,c);
        
        c.gridy = 10;
        panel.add(input8,c);
        
        c.gridx = 1;
        c.gridwidth = 2;
        c.gridy = 10;
        panel.add(button,c);
        
        
        
        
        add(panel);
        setSize(800,600);
        setLocationRelativeTo(null);
        //setVisible(true);
        
        
        
    }

    /**
     *
     * @return Jpanel
     */
    public JPanel getPanel() {
        return panel;
    }
    
    
    
}
