/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apartmentlab5;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


/**
 * creates a table based on the inputted data
 * @author Owner
 */

public class ShowTable  {
    
    private JFrame frame;
    private TableSetup newContentPane;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem GoToMM;
    private ApartmentComplex Apt;
    private MainMenuGui menu;
    /**
     *
     * 
     * 
     * 
     * @param M
     * @param A
     * @param data
     * @param colNames
     * @param title
     */
    public ShowTable(MainMenuGui M, ApartmentComplex A, Object data[][],String colNames[],String title)
    {
        Apt = A;
        menu = M;
        frame = new JFrame(title);
        frame.addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void windowClosing(WindowEvent e) {
                menu.setVisible(true);
            }

            @Override
            public void windowClosed(WindowEvent e) {
                menu.setVisible(true);
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
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        
        menuBar = new JMenuBar();
        fileMenu = new JMenu("Back");
        GoToMM = new JMenuItem("Back to Main Menu");
        fileMenu.setMnemonic('B');
        fileMenu.add(GoToMM);
        GoToMM.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                menu.setVisible(true);

            }
        });
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);
        
        //Create and set up the content pane.
        newContentPane = new TableSetup(data,colNames);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
        //newContentPane.getTable().getModel().addTableModelListener(this);
        //Display the window.
        frame.pack();
        frame.setLocationRelativeTo(null);
        //frame.setVisible(true);
    }

    /**
     *
     * @return
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     *
     * @return
     */
    public TableSetup getNewContentPane() {
        return newContentPane;
    }
    

}

