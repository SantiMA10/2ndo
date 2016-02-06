package igu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Font;

import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import logica.Pedido;
import java.awt.Color;

public class VentanaConfirmacion extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textCodigoRecogida;
	private VentanaPrincipal vp;
	private VentanaRegistro vr;
	private JTextField textPrecio;

	/**
	 * Create the dialog.
	 */
	public VentanaConfirmacion(VentanaPrincipal vp, VentanaRegistro vr) {
		this.vp = vp;
		this.vr = vr;
		setTitle("Venta de accesorios: Confirmacion Pedido");
		setBounds(100, 100, 450, 261);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblCheck = new JLabel("");
			lblCheck.setIcon(new ImageIcon(VentanaConfirmacion.class.getResource("/img/ok.png")));
			lblCheck.setBounds(57, 75, 50, 73);
			contentPanel.add(lblCheck);
		}
		{
			JLabel lblEstamosProcesandoSu = new JLabel("Estamos procesando su pedido");
			lblEstamosProcesandoSu.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblEstamosProcesandoSu.setBounds(79, 42, 333, 49);
			contentPanel.add(lblEstamosProcesandoSu);
		}
		{
			JLabel lblCodigoRecogida = new JLabel("El codigo de recogida es:");
			lblCodigoRecogida.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblCodigoRecogida.setBounds(117, 102, 169, 26);
			contentPanel.add(lblCodigoRecogida);
		}
		{
			textCodigoRecogida = new JTextField();
			textCodigoRecogida.setText(generarCodigo());
			textCodigoRecogida.setEditable(false);
			textCodigoRecogida.setBounds(278, 106, 86, 20);
			contentPanel.add(textCodigoRecogida);
			textCodigoRecogida.setColumns(10);
		}
		
		textPrecio = new JTextField();
		textPrecio.setEditable(false);
		textPrecio.setText(String.valueOf(vp.getPedido().calcularTotalSinIva()) + " \u20ac");
		textPrecio.setBounds(226, 134, 134, 28);
		contentPanel.add(textPrecio);
		textPrecio.setColumns(10);
		
		JLabel lblTotalAPagar = new JLabel("Total a pagar:");
		lblTotalAPagar.setBounds(119, 140, 125, 16);
		contentPanel.add(lblTotalAPagar);
	
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Finalizar");
				cancelButton.setMnemonic('f');
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						System.exit(1);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private String generarCodigo(){
		String codigo = "";
		String base = "0123456789abcdefghijklmnñopqrstuvwxyzABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
		int longitudCodigo = 10;
		for(int i=0; i<longitudCodigo; i++){
			int numero = (int)(Math.random()*(base.length()));
			codigo += base.charAt(numero);
		}
		vp.getPedido().grabarPedido(codigo);
		return codigo;
	}
}
