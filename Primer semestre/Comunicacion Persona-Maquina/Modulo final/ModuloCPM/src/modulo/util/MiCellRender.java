package modulo.util;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableCellRenderer;

public class MiCellRender extends DefaultTableCellRenderer{
 
	private static final long serialVersionUID = 1L;
	
	public void fillColor(JTable tabla, JTextPane textPane, boolean isSelected ){
        if(isSelected){
            textPane.setBackground(tabla.getSelectionBackground());
            textPane.setForeground(Color.WHITE);
        }
        else{
            textPane.setBackground(tabla.getBackground());
            textPane.setForeground(Color.BLACK);
        }
    }

	@Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
         boolean hasFocus, int row, int column) {
    	if(value instanceof JTextPane){
    		JTextPane textPane = (JTextPane)value;
    		textPane.setOpaque(true);
    		fillColor(table, textPane, isSelected);
    		return textPane;
    	}
    	else{
    		return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    	}
    }
}
