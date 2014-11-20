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
import javax.swing.JTextField;

/**
 * creates gui for inputting employees
 * @author
 */
public class InputEmployee extends JFrame{
    
    private JPanel panel;
    private GridBagLayout layout;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem GoToMM;
    private JTextField input1;
    private JTextField input2;
    private JTextField input3;
    private JTextField input4;
    private JComboBox input5;
    private JTextField input6;
    private JLabel title;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JButton button;
    private GridBagConstraints c;
    private Employee emp;
    private JPopupMenu popup;
    private ApartmentComplex AptComplex;
    private MainMenuGui menu;
    
    /**
     *
     * @param M
     * @param A
     */
    public InputEmployee(MainMenuGui M,ApartmentComplex A)
    {
        super("Create Employee");
        AptComplex = A;
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
        
        emp = new Employee();
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
        
        title = new JLabel("New Employee");
        label1 = new JLabel("First Name");
        label2 = new JLabel("Last Name");
        label3 = new JLabel("ID Number");
        label4 = new JLabel("Monthly Salary");
        label5 = new JLabel("Occupation");
        label6 = new JLabel("Initial Account Balance");
        
        input1 = new JTextField(20);
        input2 = new JTextField(20);
        input3 = new JTextField(20);
        input4 = new JTextField(20);
        String[] Occupations = {"SALES","HANDYPERSON","LAWNDOCTOR","SECURITY","MANAGER"};
        input5 = new JComboBox(Occupations);
        input6 = new JTextField(20);
        
        button = new JButton("Create Employee");
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                int num;
                double salary;
                String job;
                float bal;
                int count=0;  // count of Joption panes opened
               
               if("".equals(input1.getText()))
               {
                    JOptionPane.showMessageDialog(null, "No First Name Entered");
                    count++;
               }
               else
               {
                   emp.setFirstName(input1.getText());
               }
               if("".equals(input2.getText()))
               {
                   JOptionPane.showMessageDialog(null, "No Last Name Entered");
                   count++;
               }
               else
               {
                   emp.setLastName(input2.getText());
               }
               if("".equals(input3.getText()))
               {
                   JOptionPane.showMessageDialog(null, "No ID Number Entered");
                   count++;
               }
               else
               {
                   try{
                   num = Integer.parseInt(input3.getText());
                   if(emp.setIDnum(num))
                   {
                       JOptionPane.showMessageDialog(null, "No Negative Integers for ID number!!!");
                       count++;
                   }
                   else
                   {
                       if(AptComplex.FindIDNum(num))
                        {
                           int n = JOptionPane.showConfirmDialog(null, "ID number taken\nWould you like the system to find the next available ID number?","Message",JOptionPane.YES_NO_OPTION);
                           //count++;
                           if(n == JOptionPane.YES_OPTION)
                           {
                               num = AptComplex.SetNewIDNum();
                               input3.setText(Integer.toString(num));
                               int m = JOptionPane.showConfirmDialog(null, "Is ID number "+num+" okay?","Message",JOptionPane.YES_NO_OPTION);
                               if(m == JOptionPane.YES_OPTION)
                               {
                                   emp.setIDnum(num);
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
                       JOptionPane.showMessageDialog(null, "Wrong Input format for the ID number");
                       count++;
                   }
                   
                   
               }               
               
               if("".equals(input4.getText()))
               {
                   JOptionPane.showMessageDialog(null, "No Monthly Salary Entered");
                   count++;
               }
               else
               {
                   try{
                   salary = Double.parseDouble(input4.getText());
                   if(emp.setMoSalary(salary))
                   {
                       JOptionPane.showMessageDialog(null, "No Negative Salary!!!");
                       count++;
                   }

                   }
                   catch(NumberFormatException error)
                   {
                       JOptionPane.showMessageDialog(null, "Wrong Input format for the Monthly Salary");
                       count++;
                   }  
                   

               }
               
               if("".equals(input6.getText()))
               {
                   JOptionPane.showMessageDialog(null, "No Balance Entered");
                   count++;
               }
               else
               {
                   try{
                   bal = Float.parseFloat(input6.getText());
                   if(emp.setAccount(bal))
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
               
                   job = (String) input5.getSelectedItem();
                   emp.setJob(job);
                    
                 if(count == 0)
                 {
                   AptComplex.hire(emp);
                   JOptionPane.showMessageDialog(null, "Employee named "+emp.getFirstName() +" "+ emp.getLastName() + " has been hired");
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
        
        c.gridwidth = 2;
        c.gridy = 7;
        panel.add(button,c);
        
        
        add(panel);
        setSize(630,500);
        setVisible(true);
        setLocationRelativeTo(null);
        
        
        
    }
    
}
