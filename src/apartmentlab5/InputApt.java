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
 * creates gui for inputting apartment
 * @author Owner
 */
public class InputApt  extends JFrame implements Constants{
    private JPanel panel;
    private GridBagLayout layout;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem GoToMM;
    private JSpinner input1;  //Apt Number
    private JSpinner input2;  //Apartment area in square footage
    private JSpinner input3;   //number of bedrooms
    private JSpinner input4;  //number of bathrooms
    private JComboBox input5;  //Patio- yes or no
    private JTextField input6;  // monthly rent for apartment
    private JSpinner input7;  //Lease Period
    private JLabel title;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JButton button;
    private GridBagConstraints c;
    private Apartment Apt;
    private Tenant tenant;
    private JPopupMenu popup;
    private ApartmentComplex AptComplex;
    private MainMenuGui menu;
    /**
     * 
     * 
     * @param A
     * @param M
     * @param T
     */
    public InputApt(ApartmentComplex A, MainMenuGui M, Tenant T)
    {
        super("Create Employee");
        AptComplex = A;
        tenant = T;
        menu = M;
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
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
        
        Apt = new Apartment();
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
        
        title = new JLabel("New Apartment");
        label1 = new JLabel("Apartment Number");
        label2 = new JLabel("Area in Square feet");
        label3 = new JLabel("Bedrooms");
        label4 = new JLabel("Bathrooms");
        label5 = new JLabel("Patio");
        label6 = new JLabel("Monthly Rent");
        label7 = new JLabel("Lease Period in Months");
        
        
        input1 = new JSpinner(new SpinnerNumberModel(100,100,999,1));
        SpinnerModel model0 = new SpinnerNumberModel(AREA_MIN,AREA_MIN,AREA_MAX,10);
        input2 = new JSpinner(model0);
        SpinnerModel model = new SpinnerNumberModel(2,1,5,1);
        input3 = new JSpinner(model);
        SpinnerModel model2 = new SpinnerNumberModel(1,1,4,0.5);
        input4 = new JSpinner(model2);
        String[] choice = {"YES","NO"};
        input5 = new JComboBox(choice);
        input6 = new JTextField(20);
        SpinnerModel model3 = new SpinnerNumberModel(1,1,36,1);
        input7 = new JSpinner(model3);
        
        button = new JButton("Lease Apartment");
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                int num;
                double d;
                String job;
                float bal;
                int count=0;  // count of Joption panes opened
               
            /*   if("".equals(input1.getText()))
               {
                    JOptionPane.showMessageDialog(null, "No Apartment Number Entered");
                    count++;
               }*/
               
               
                   try{
                   //num = Integer.parseInt(input1.getText());
                       num = (int)input1.getValue();
                   if(Apt.setAptNum(num))
                   {
                       JOptionPane.showMessageDialog(null, "No Negative Integers for Apartment number!!!");
                       count++;
                   }
                   else
                   {
                       if(AptComplex.checkAptNum(num))
                       {
                           int n = JOptionPane.showConfirmDialog(null, "Apartment Number taken\nWould you like the system to find the next available apartment?","Message",JOptionPane.YES_NO_OPTION);
                           //count++;
                           if(n == JOptionPane.YES_OPTION)
                           {
                               num = AptComplex.FindNewAptNum();
                               input1.setValue(num);
                               int m = JOptionPane.showConfirmDialog(null, "Is Apartment number "+num+" okay?","Message",JOptionPane.YES_NO_OPTION);
                               if(m == JOptionPane.YES_OPTION)
                               {
                                   Apt.setAptNum(num);
                               }
                               else
                               {
                                   count++;
                               }
                           }
                           else
                           {
                               count++;
                           }
                       }
                   }
                   }
                   catch(NumberFormatException error)
                   {
                       JOptionPane.showMessageDialog(null, "Wrong Input format for the Apartment number");
                       count++;
                   }
                   
                   
               
               if(Apt.setArea((int)input2.getValue()))
               {
                   JOptionPane.showMessageDialog(null, "Not a Valid Area Amount");
                   count++;
               }
               
               
               Apt.setBathrooms((double)input4.getValue());
               Apt.setPatio((String)input5.getSelectedItem());
               
               if("".equals(input6.getText()))
               {
                   JOptionPane.showMessageDialog(null, "No Monthly Rent Value Entered");
                   count++;
               }
               else
               {
                   try{
                   d = Double.parseDouble(input6.getText());
                   if(Apt.setMoRent(d))
                   {
                       JOptionPane.showMessageDialog(null, "No Negative Amount!!!");
                       count++;
                   }

                   }
                   catch(NumberFormatException error)
                   {
                       JOptionPane.showMessageDialog(null, "Wrong Input format for the Monthly Rent");
                       count++;
                   }  

               }
               
               Apt.setLeasePer((int)input7.getValue());
               Apt.setEndLease(Apt.getLeasePer());
               

                    
                 if(count == 0)
                 {
                   
                   AptComplex.lease(tenant, Apt, Apt.getLeasePer());
                   JOptionPane.showMessageDialog(null, "Apartment #"+Apt.getAptNum()+" will be added with "+tenant.getFirstName()+" "+tenant.getLastName()+" signing the lease");
                   dispose();
                   menu.setVisible(true);
                   
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
        
        c.gridy = 7;
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
        c.ipadx = 10;
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
        c.ipadx = 0;
        c.anchor = GridBagConstraints.CENTER;
        panel.add(input6,c);
        
        c.gridy = 7;
        panel.add(input7,c);
        
        c.gridwidth = 2;
        c.gridy = 8;
        panel.add(button,c);
        
        
        add(panel);
        setSize(900,800);
        setVisible(true);
        setLocationRelativeTo(null);
        
        
        
    }
}

