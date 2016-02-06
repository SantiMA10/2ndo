package igu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;

import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import org.jvnet.substance.SubstanceLookAndFeel;

import logica.Inmobiliaria;
import logica.Mansion;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JCheckBox;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class VentanaPrincipal extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblAgenciaInmobiliariaEii;
	private JLabel lblIcono;
	private JTabbedPane tabbedPane;
	private JPanel panelVentas;
	private JPanel panelAlquier;
	private JScrollPane scrollPane;
	private JTable tablaMansiones;
	private ModeloNoEditable modeloTabla;
	private JScrollPane scrollPaneDescripcion;
	private JTextArea txtrDescripcion;
	private JPanel panelVisitas;
	private JPanel panelEntrada;
	private JScrollPane scrollPane_1;
	private JList<String> list;
	private DefaultListModel<String> modeloLista;
	private JButton btnAadir;
	private JButton btnEliminar;
	private JSpinner spinner;
	private JLabel labelPorcentaje;
	private JButton btnCalcular;
	private JTextField textField;
	private JButton btnCancelar;
	private JButton btnAceptar;
	private SpinnerNumberModel modeloSpinner;
	private Inmobiliaria inmobiliaria;
	private JLabel lblElRestoA;
	private JTextField txtRestoAPagar;
	private JPanel panelCheck;
	private JLabel lblZonas;
	private JCheckBox chNorte;
	private JCheckBox chCentro;
	private JCheckBox chSur;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame.setDefaultLookAndFeelDecorated(true);
					JDialog.setDefaultLookAndFeelDecorated(true);
					SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.MistSilverSkin");
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		inmobiliaria = new Inmobiliaria();
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/llave.JPG")));
		setTitle("Agencia inmobiliaria");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 660, 608);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblAgenciaInmobiliariaEii());
		contentPane.add(getLblIcono());
		contentPane.add(getTabbedPane());
		contentPane.add(getScrollPaneDescripcion());
		contentPane.add(getPanelVisitas());
		contentPane.add(getPanelEntrada());
		contentPane.add(getBtnCancelar());
		contentPane.add(getBtnAceptar());
	}
	private JLabel getLblAgenciaInmobiliariaEii() {
		if (lblAgenciaInmobiliariaEii == null) {
			lblAgenciaInmobiliariaEii = new JLabel("Agencia Inmobiliaria EII");
			lblAgenciaInmobiliariaEii.setFont(new Font("Tahoma", Font.PLAIN, 46));
			lblAgenciaInmobiliariaEii.setBounds(24, 21, 501, 90);
		}
		return lblAgenciaInmobiliariaEii;
	}
	private JLabel getLblIcono() {
		if (lblIcono == null) {
			lblIcono = new JLabel("");
			lblIcono.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/llave.JPG")));
			lblIcono.setBounds(532, 14, 100, 100);
		}
		return lblIcono;
	}
	private JTabbedPane getTabbedPane() {
		if (tabbedPane == null) {
			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.setBounds(24, 150, 432, 275);
			tabbedPane.addTab("Venta de mansiones", null, getPanelVentas(), null);
			tabbedPane.setDisplayedMnemonicIndexAt(0, 0);
			tabbedPane.addTab("Alquiler de mansiones", null, getPanelAlquier(), null);
			tabbedPane.setDisplayedMnemonicIndexAt(1, 0);
		}
		return tabbedPane;
	}
	private JPanel getPanelVentas() {
		if (panelVentas == null) {
			panelVentas = new JPanel();
			panelVentas.setLayout(new BorderLayout(0, 0));
			panelVentas.add(getScrollPane(), BorderLayout.CENTER);
			panelVentas.add(getPanelCheck(), BorderLayout.NORTH);
		}
		return panelVentas;
	}
	private JPanel getPanelAlquier() {
		if (panelAlquier == null) {
			panelAlquier = new JPanel();
		}
		return panelAlquier;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTablaMansiones());
		}
		return scrollPane;
	}
	private JTable getTablaMansiones() {
		if (tablaMansiones == null) {
			String[] nombreColumnas = {"Codigo","Zona","Localidad","Precio"};
			modeloTabla = new ModeloNoEditable(nombreColumnas, 0);
			tablaMansiones = new JTable(modeloTabla);
			tablaMansiones.setDefaultRenderer(Object.class, new RendererSubstance());
			tablaMansiones.getTableHeader().setReorderingAllowed(false);
			añadirFilas(getChNorte().isSelected(), getChCentro().isSelected(), getChSur().isSelected());
			tablaMansiones.getTableHeader().getColumnModel().getColumn(0).setPreferredWidth(20);
			tablaMansiones.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					txtrDescripcion.setText(String.valueOf(inmobiliaria.getDescripcionMansion((String)tablaMansiones.getValueAt(tablaMansiones.getSelectedRow(),0))));
					textField.setText("");
					txtRestoAPagar.setText("");
					if(arg0.getClickCount() == 2){
						int fila = tablaMansiones.getSelectedRow();
						if(fila != -1){
							String codigo = ((String)tablaMansiones.getValueAt(fila, 0));
							if(!modeloLista.contains(codigo))
								modeloLista.addElement(codigo);
						}
					}
				}
			});
		}
		return tablaMansiones;
	}	
	private void añadirFilas(boolean norte, boolean centro, boolean sur){
		Object[] nuevaFila = new Object[4];
		List<Mansion> relacionMansiones = inmobiliaria.getRelacionMansiones();
		for (int i = 0; i < relacionMansiones.size(); i++){
			String zonaM = relacionMansiones.get(i).getZona();
			if((norte && zonaM.equals("Norte")) || (centro && zonaM.equals("Centro")) ||
					(sur && zonaM.equals("Sur"))){
				nuevaFila[0] = relacionMansiones.get(i).getCodigo();
				nuevaFila[1] = relacionMansiones.get(i).getZona();
				nuevaFila[2] = relacionMansiones.get(i).getLocalidad();
				nuevaFila[3] = new Float(relacionMansiones.get(i).getPrecio());
				modeloTabla.addRow(nuevaFila);
			}
		}
	}
	private JScrollPane getScrollPaneDescripcion() {
		if (scrollPaneDescripcion == null) {
			scrollPaneDescripcion = new JScrollPane();
			scrollPaneDescripcion.setBounds(24, 435, 432, 96);
			scrollPaneDescripcion.setViewportView(getTxtrDescripcion());
		}
		return scrollPaneDescripcion;
	}
	private JTextArea getTxtrDescripcion() {
		if (txtrDescripcion == null) {
			txtrDescripcion = new JTextArea();
			txtrDescripcion.setFont(new Font("Dialog", Font.PLAIN, 15));
			txtrDescripcion.setWrapStyleWord(true);
			txtrDescripcion.setLineWrap(true);
			txtrDescripcion.setEditable(false);
		}
		return txtrDescripcion;
	}
	private JPanel getPanelVisitas() {
		if (panelVisitas == null) {
			panelVisitas = new JPanel();
			panelVisitas.setBorder(new TitledBorder(null, "Mansiones a visitar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelVisitas.setBounds(466, 122, 182, 267);
			panelVisitas.setLayout(null);
			panelVisitas.add(getScrollPane_1());
			panelVisitas.add(getBtnAadir());
			panelVisitas.add(getBtnEliminar());
		}
		return panelVisitas;
	}
	private JPanel getPanelEntrada() {
		if (panelEntrada == null) {
			panelEntrada = new JPanel();
			panelEntrada.setBorder(new TitledBorder(null, "Entrada a pagar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelEntrada.setBounds(466, 400, 182, 140);
			panelEntrada.setLayout(null);
			panelEntrada.add(getSpinner());
			panelEntrada.add(getLabelPorcentaje());
			panelEntrada.add(getBtnCalcular());
			panelEntrada.add(getTextField());
			panelEntrada.add(getLblElRestoA());
			panelEntrada.add(getTxtRestoAPagar());
		}
		return panelEntrada;
	}
	private JScrollPane getScrollPane_1() {
		if (scrollPane_1 == null) {
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(10, 27, 162, 195);
			scrollPane_1.setViewportView(getList());
		}
		return scrollPane_1;
	}
	private JList<String> getList() {
		if (list == null) {
			modeloLista = new DefaultListModel<String>();
			list = new JList<String>(modeloLista);
		}
		return list;
	}
	private JButton getBtnAadir() {
		if (btnAadir == null) {
			btnAadir = new JButton("A\u00F1adir");
			btnAadir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int fila = tablaMansiones.getSelectedRow();
					if(fila != -1){
						String codigo = ((String)tablaMansiones.getValueAt(fila, 0));
						if(!modeloLista.contains(codigo))
							modeloLista.addElement(codigo);
					}
						
				}
			});
			btnAadir.setBounds(10, 233, 79, 23);
		}
		return btnAadir;
	}
	private JButton getBtnEliminar() {
		if (btnEliminar == null) {
			btnEliminar = new JButton("Eliminar");
			btnEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					List<String> mansiones = list.getSelectedValuesList();
					for(int i = 0; i <  mansiones.size(); i++){
						modeloLista.removeElement(mansiones.get(i));
					}
				}
			});
			btnEliminar.setBounds(99, 233, 73, 23);
		}
		return btnEliminar;
	}
	private JSpinner getSpinner() {
		if (spinner == null) {
			modeloSpinner = new SpinnerNumberModel(15, 0 , 20, 1);
			spinner = new JSpinner(modeloSpinner);
			spinner.setBounds(10, 22, 42, 20);
		}
		return spinner;
	}
	private JLabel getLabelPorcentaje() {
		if (labelPorcentaje == null) {
			labelPorcentaje = new JLabel("%");
			labelPorcentaje.setBounds(62, 25, 11, 14);
		}
		return labelPorcentaje;
	}
	private JButton getBtnCalcular() {
		if (btnCalcular == null) {
			btnCalcular = new JButton("Calcular");
			btnCalcular.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int fila = tablaMansiones.getSelectedRow();
					if(fila != -1){
						int porcentaje = ((Integer) spinner.getValue()).intValue();
						float precio = ((Float) tablaMansiones.getValueAt(fila, 3)).floatValue();
						float entrada = inmobiliaria.calcularEntrada(porcentaje, precio);
						textField.setText(String.valueOf(entrada) + " €");
						txtRestoAPagar.setText(String.valueOf(precio-entrada) + " €");
					}
				}
			});
			btnCalcular.setBounds(83, 21, 85, 23);
		}
		return btnCalcular;
	}
	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setEditable(false);
			textField.setBounds(10, 53, 159, 24);
			textField.setColumns(10);
		}
		return textField;
	}
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					inicializar();
				}
			});
			btnCancelar.setBounds(559, 551, 89, 23);
		}
		return btnCancelar;
	}
	private JButton getBtnAceptar() {
		if (btnAceptar == null) {
			btnAceptar = new JButton("Aceptar");
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					grabarVisitas();
					inicializar();
				}
			});
			btnAceptar.setBounds(466, 551, 89, 23);
		}
		return btnAceptar;
	}
	
	protected void inicializar() {
		tabbedPane.setSelectedIndex(0);
		tablaMansiones.clearSelection();
		modeloLista.clear();
		txtrDescripcion.setText("");
		spinner.setValue(new Integer(15));
		txtRestoAPagar.setText("");
		textField.setText("");
		chNorte.setSelected(true);
		chSur.setSelected(true);
		chCentro.setSelected(true);
	}

	protected void grabarVisitas() {
		String linea = "";
		Object[] codigos = modeloLista.toArray();
		for(int i = 0; i < codigos.length; i++){
			linea += codigos[i]+" ";
		}
		if(inmobiliaria.grabarFichero(linea) == 0){
			JOptionPane.showMessageDialog(null, "Su peticion ha sido generada con exito,");
		}
		else{
			JOptionPane.showMessageDialog(null, "Hubo un problema al generar su peticion.");
		}
		
	}

	private JLabel getLblElRestoA() {
		if (lblElRestoA == null) {
			lblElRestoA = new JLabel("El resto a pagar:");
			lblElRestoA.setBounds(9, 81, 90, 14);
		}
		return lblElRestoA;
	}
	private JTextField getTxtRestoAPagar() {
		if (txtRestoAPagar == null) {
			txtRestoAPagar = new JTextField();
			txtRestoAPagar.setEditable(false);
			txtRestoAPagar.setBounds(10, 103, 159, 24);
			txtRestoAPagar.setColumns(10);
		}
		return txtRestoAPagar;
	}
	private JPanel getPanelCheck() {
		if (panelCheck == null) {
			panelCheck = new JPanel();
			panelCheck.add(getLblZonas());
			panelCheck.add(getChNorte());
			panelCheck.add(getChCentro());
			panelCheck.add(getChSur());
		}
		return panelCheck;
	}
	private JLabel getLblZonas() {
		if (lblZonas == null) {
			lblZonas = new JLabel("Indique las zonas:");
			lblZonas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lblZonas;
	}
	private JCheckBox getChNorte() {
		if (chNorte == null) {
			chNorte = new JCheckBox("Norte");
			chNorte.setSelected(true);
			chNorte.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent arg0) {
					mostrarMansionesZona();
				}
			});
			
			chNorte.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return chNorte;
	}
	protected void mostrarMansionesZona() {
		modeloTabla.getDataVector().clear();
		añadirFilas(chNorte.isSelected(), chCentro.isSelected(), chSur.isSelected());
		modeloTabla.fireTableDataChanged();
	}

	private JCheckBox getChCentro() {
		if (chCentro == null) {
			chCentro = new JCheckBox("Centro");
			chCentro.setSelected(true);
			chCentro.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent arg0) {
					mostrarMansionesZona();
				}
			});
			
			chCentro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return chCentro;
	}
	private JCheckBox getChSur() {
		if (chSur == null) {
			chSur = new JCheckBox("Sur");
			chSur.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent arg0) {
					mostrarMansionesZona();
				}
			});
			chSur.setSelected(true);
			chSur.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return chSur;
	}
}
