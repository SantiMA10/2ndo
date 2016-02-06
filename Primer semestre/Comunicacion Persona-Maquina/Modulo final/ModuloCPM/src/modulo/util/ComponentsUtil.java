package modulo.util;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.JTableHeader;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import modulo.igu.VentanaPrincipal;

public class ComponentsUtil {
	
	public static TitledBorder getBorder(String titulo){
		TitledBorder borde = new TitledBorder(null, titulo, TitledBorder.LEADING, TitledBorder.TOP, null, null);
		borde.setTitleFont(new Font("Lucida Grande", Font.PLAIN, VentanaPrincipal.DEFAULT_BORDER_TITTLE_SIZE));
		return borde;
	}
	
	public static JTextPane getJTextPane(String datos){
		JTextPane textPane = new JTextPane();
		textPane.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		textPane.setEditable(false);
		textPane.setText("\n\n"+datos);
		StyledDocument doc = textPane.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		
		return textPane;
	}
	
	public static void changeJTableHeaderSize(JTable tabla){
		JTableHeader header = tabla.getTableHeader();
		header.setPreferredSize(new Dimension(100, VentanaPrincipal.TABLE_HEADER_HEIGHT));
		header.setFont(new Font("Lucida Grande", Font.PLAIN, VentanaPrincipal.TABLE_HEADER_SIZE));
	}
}
