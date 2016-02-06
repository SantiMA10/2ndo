package modulo.util;

import javax.swing.table.DefaultTableModel;

public class MiModeloTabla extends DefaultTableModel {
	private static final long serialVersionUID = 1L;

	public MiModeloTabla(Object[] columnNames, int rowCount) {
		super(columnNames, rowCount);
   }
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		Class<?> clazz = Object.class;
		Object aux = getValueAt(0, columnIndex);
		if (aux != null) {
			clazz = aux.getClass();
		}	   
		return clazz;
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
        return false;
    }
}
