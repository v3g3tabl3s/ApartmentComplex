/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apartmentlab5;


import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;



/**
 *the generic table setup
 * @author Owner
 */
public class TableSetup extends JPanel{
    
    private JTable table;
    
    
    /**
     * 
     * 
     * @param data
     * @param colNames
     * Window layout as a Jtable
     */
    public TableSetup(Object data[][], String colNames[])
    {
        super(new GridLayout(1,0));

        table = new JTable(data,colNames);
        table.setEnabled(false);
        table.setPreferredScrollableViewportSize(new Dimension(1000, 500));
        table.setFillsViewportHeight(true);

        
        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane);
               

    }

    /**
     *
     * @return
     */
    public JTable getTable() {
        return table;
    }


}
