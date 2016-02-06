package igu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import logica.*;

import javax.swing.DefaultComboBoxModel;

import java.awt.Color;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 6607258746735187764L;
	private JPanel contentPane;
	private JLabel lblLogo;
	private JLabel lblPcAccesorios;
	private JLabel lblArticulos;
	private JComboBox<Articulo> comboBoxArticulos;
	private JLabel lblUnidades;
	private JTextField textUnidades;
	private JButton btnMas;
	private JTextField textPrecioPedido;
	private JLabel lblPrecioPedido;
	private JButton btnSiguiente;
	private JButton btnCancelar;
	
	private Catalogo catalogo;
	private Pedido pedido;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
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
		catalogo = new Catalogo();
		setPedido(new Pedido());
		
		setTitle("Venta de Accesorios de Ordenador");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 612, 430);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblLogo());
		contentPane.add(getLblPcAccesorios());
		contentPane.add(getLblArticulos());
		contentPane.add(getComboBoxArticulos());
		contentPane.add(getLblUnidades());
		contentPane.add(getTextUnidades());
		contentPane.add(getBtnMas());
		contentPane.add(getTextPrecioPedido());
		contentPane.add(getLblPrecioPedido());
		contentPane.add(getBtnSiguiente());
		contentPane.add(getBtnCancelar());
	}
	private JLabel getLblLogo() {
		if (lblLogo == null) {
			lblLogo = new JLabel("");
			lblLogo.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/logo.jpg")));
			lblLogo.setBounds(36, 11, 134, 152);
		}
		return lblLogo;
	}
	private JLabel getLblPcAccesorios() {
		if (lblPcAccesorios == null) {
			lblPcAccesorios = new JLabel("PC ACCESORIOS");
			lblPcAccesorios.setFont(new Font("Tahoma", Font.BOLD, 22));
			lblPcAccesorios.setBounds(199, 72, 195, 37);
		}
		return lblPcAccesorios;
	}
	private JLabel getLblArticulos() {
		if (lblArticulos == null) {
			lblArticulos = new JLabel("Articulos:");
			lblArticulos.setLabelFor(getComboBoxArticulos());
			lblArticulos.setDisplayedMnemonic('a');
			lblArticulos.setBounds(36, 193, 61, 19);
		}
		return lblArticulos;
	}
	private JComboBox<Articulo> getComboBoxArticulos() {
		if (comboBoxArticulos == null) {
			comboBoxArticulos = new JComboBox<Articulo>();
			comboBoxArticulos.setModel(new DefaultComboBoxModel<Articulo>(catalogo.getArticulos()));
			comboBoxArticulos.setBounds(36, 223, 321, 20);
			
		}
		return comboBoxArticulos;
	}
	private JLabel getLblUnidades() {
		if (lblUnidades == null) {
			lblUnidades = new JLabel("Unidades:");
			lblUnidades.setLabelFor(getTextUnidades());
			lblUnidades.setDisplayedMnemonic('u');
			lblUnidades.setBounds(390, 195, 68, 14);
		}
		return lblUnidades;
	}
	private JTextField getTextUnidades() {
		if (textUnidades == null) {
			textUnidades = new JTextField();
			textUnidades.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent arg0) {
					if(textUnidades.getText().isEmpty()) {
						textUnidades.setText("1");
					}
					textUnidades.selectAll();
				}
				@Override
				public void focusLost(FocusEvent arg0) {
					boolean error = false;
					int unidades = 0;
					try{
						unidades = Integer.parseInt(textUnidades.getText());
					} catch(NumberFormatException e ) {
						error = true;
					}
					if((error) || (unidades <= 0)){
						textUnidades.setText("");
					}
				}
			});
			textUnidades.setBounds(390, 223, 86, 20);
			textUnidades.setColumns(10);
		}
		return textUnidades;
	}
	private JButton getBtnMas() {
		if (btnMas == null) {
			btnMas = new JButton("+");
			btnMas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(!textUnidades.getText().equals("")){
						Articulo articuloSeleccionado = (Articulo)comboBoxArticulos.getSelectedItem();
						int unidadesSolicitadas = Integer.parseInt(textUnidades.getText());
						if(unidadesSolicitadas >= 10) {
							JOptionPane.showMessageDialog(null, "Se ha aplicado un descuento del 15% en " + articuloSeleccionado.getDenominacion());
						}
						getPedido().add(articuloSeleccionado, unidadesSolicitadas);
						float precioPedido = getPedido().calcularTotalSinIva();
						textPrecioPedido.setText(String.valueOf(precioPedido) + " \u20ac");
						if(!btnSiguiente.isEnabled()){
							btnSiguiente.setEnabled(true);
						}
					}
					else{
						textUnidades.grabFocus();
					}
				}
			});
			btnMas.setToolTipText("A\u00F1ade las unidades indicadas al pedido");
			btnMas.setMnemonic('+');
			btnMas.setBounds(486, 222, 52, 23);
		}
		return btnMas;
	}
	private JTextField getTextPrecioPedido() {
		if (textPrecioPedido == null) {
			textPrecioPedido = new JTextField();
			textPrecioPedido.setEditable(false);
			textPrecioPedido.setBounds(390, 299, 86, 20);
			textPrecioPedido.setColumns(10);
		}
		return textPrecioPedido;
	}
	private JLabel getLblPrecioPedido() {
		if (lblPrecioPedido == null) {
			lblPrecioPedido = new JLabel("Precio Pedido:");
			lblPrecioPedido.setBounds(390, 274, 86, 14);
		}
		return lblPrecioPedido;
	}
	private void mostrarVentanaRegistro(){
		VentanaRegistro frame = new VentanaRegistro(this);
		frame.setLocationRelativeTo(null);
		frame.setModal(true);
		frame.setVisible(true);

	}
	private JButton getBtnSiguiente() {
		if (btnSiguiente == null) {
			btnSiguiente = new JButton("Siguiente");
			btnSiguiente.setEnabled(false);
			btnSiguiente.setMnemonic('s');
			btnSiguiente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					mostrarVentanaRegistro();
				}
			});
			btnSiguiente.setBounds(390, 358, 89, 23);
		}
		return btnSiguiente;
	}
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.setMnemonic('c');
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.exit(0);
				}
			});
			btnCancelar.setBounds(489, 358, 89, 23);
		}
		return btnCancelar;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
}
