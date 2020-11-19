/*
Clase utilizada para poder agregar combobox a una tabla
 */
package INS;
import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
/**
 *
 * @author Leonel Londono
 * @author Erika Gonzalez
 * @author Brayan Gonzalez
 * */
public class CeldaRender extends DefaultTableCellRenderer
{
     private int columna = -1;
    
    public CeldaRender(int columna) {
        this.columna = columna;
    }
      
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {

        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        
        if (columna == column) {
            JComboBox comboBox = new JComboBox();            
            comboBox.addItem(value);                        
            return comboBox;            
        }

        return cellComponent;
    
}
}
