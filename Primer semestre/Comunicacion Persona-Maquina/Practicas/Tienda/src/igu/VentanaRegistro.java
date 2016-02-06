package igu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.JRadioButton;

import java.awt.FlowLayout;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPasswordField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ButtonGroup;

import logica.Pedido;
import java.awt.Color;

public class VentanaRegistro extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnSiguiente;
	private JButton btnCancelar;
	private JLabel lblNombre;
	private JTextField textNombre;
	private JLabel lblApellidos;
	private JTextField textApellidos;
	private JPanel pnlSexo;
	private JRadioButton rdbtnHombre;
	private JRadioButton rdbtnMujer;
	private JPanel pnlNacimiento;
	private JComboBox<String> cBoxDia;
	private JComboBox<String> cbBoxMes;
	private JComboBox<String> cbBoxAno;
	private JPanel panel;
	private JTextField textUsuario;
	private JLabel lblUsuario;
	private JPasswordField pwdPassword;
	private JLabel lblPassword;
	private JPasswordField pwdReintroduzca;
	private final ButtonGroup buttonGroupSexo = new ButtonGroup();
	private JLabel lblDia;
	private JLabel lblYear;
	private JLabel lblMes;
	private VentanaPrincipal vp;


	/**
	 * Create the frame.
	 */
	public VentanaRegistro(VentanaPrincipal vp) {
		this.vp = vp;
		setTitle("Venta de Productos: Formulario de registro");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnSiguiente());
		contentPane.add(getBtnCancelar());
		contentPane.add(getLblNombre());
		contentPane.add(getTextNombre());
		contentPane.add(getLblApellidos());
		contentPane.add(getTextApellidos());
		contentPane.add(getPnlSexo());
		contentPane.add(getPnlNacimiento());
		contentPane.add(getPanel());
	}
	private JButton getBtnSiguiente() {
		if (btnSiguiente == null) {
			btnSiguiente = new JButton("Siguiente");
			btnSiguiente.setMnemonic('s');
			btnSiguiente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(isAllCorrect()) {
						mostrarVentanaConfirmacion();
					}
				}
			});
			btnSiguiente.setBounds(386, 228, 89, 23);
		}
		return btnSiguiente;
	}
	
	private void mostrarVentanaConfirmacion() {
		VentanaConfirmacion dialog = new VentanaConfirmacion(vp, this);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setLocationRelativeTo(null);
		dialog.setModal(true);
		dialog.setVisible(true);
	}
	
	private boolean isAllCorrect() {
		boolean correct = true;
		if(!isAllComplete()) {
			JOptionPane.showMessageDialog(null, "Faltán campos por rellenar.");
			correct = false;
		}
		if(!isTheSamePassword()){
			JOptionPane.showMessageDialog(null, "No coinciden las contraseñas.");
			correct = false;
		}
		return correct;
	}
	
	private boolean isTheSamePassword() {
		String password = String.valueOf(pwdPassword.getPassword());
		String rpassword = String.valueOf(pwdReintroduzca.getPassword());

		return password.equals(rpassword);
	}
	
	private boolean isAllComplete() {
		String nombre = this.textNombre.getText();
		String apellidos = this.textApellidos.getText();
		String usuario = this.textUsuario.getText();
		String password = String.valueOf(pwdPassword.getPassword());
		String rpassword = String.valueOf(pwdReintroduzca.getPassword());
		
		return !nombre.isEmpty() && !apellidos.isEmpty() && !usuario.isEmpty() && !password.isEmpty() && !rpassword.isEmpty();
	}
	
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.setMnemonic('c');
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
			btnCancelar.setBounds(485, 228, 89, 23);
		}
		return btnCancelar;
	}
	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre:");
			lblNombre.setLabelFor(getTextNombre());
			lblNombre.setDisplayedMnemonic('n');
			lblNombre.setBounds(10, 9, 200, 50);
		}
		return lblNombre;
	}
	private JTextField getTextNombre() {
		if (textNombre == null) {
			textNombre = new JTextField();
			textNombre.setBounds(64, 23, 200, 23);
			textNombre.setColumns(10);
		}
		return textNombre;
	}
	private JLabel getLblApellidos() {
		if (lblApellidos == null) {
			lblApellidos = new JLabel("Apellidos:");
			lblApellidos.setLabelFor(getTextApellidos());
			lblApellidos.setDisplayedMnemonic('a');
			lblApellidos.setBounds(299, 10, 200, 50);
		}
		return lblApellidos;
	}
	private JTextField getTextApellidos() {
		if (textApellidos == null) {
			textApellidos = new JTextField();
			textApellidos.setBounds(363, 23, 200, 23);
			textApellidos.setColumns(10);
		}
		return textApellidos;
	}
	private JPanel getPnlSexo() {
		if (pnlSexo == null) {
			pnlSexo = new JPanel();
			pnlSexo.setBackground(Color.WHITE);
			pnlSexo.setBorder(new TitledBorder(null, "Sexo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnlSexo.setBounds(20, 71, 205, 63);
			pnlSexo.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			pnlSexo.add(getRdbtnHombre());
			pnlSexo.add(getRdbtnMujer());
		}
		return pnlSexo;
	}
	private JRadioButton getRdbtnHombre() {
		if (rdbtnHombre == null) {
			rdbtnHombre = new JRadioButton("Hombre");
			rdbtnHombre.setBackground(Color.WHITE);
			rdbtnHombre.setMnemonic('h');
			buttonGroupSexo.add(rdbtnHombre);
			rdbtnHombre.setSelected(true);
		}
		return rdbtnHombre;
	}
	private JRadioButton getRdbtnMujer() {
		if (rdbtnMujer == null) {
			rdbtnMujer = new JRadioButton("Mujer");
			rdbtnMujer.setBackground(Color.WHITE);
			rdbtnMujer.setMnemonic('m');
			buttonGroupSexo.add(rdbtnMujer);
		}
		return rdbtnMujer;
	}
	private JPanel getPnlNacimiento() {
		if (pnlNacimiento == null) {
			pnlNacimiento = new JPanel();
			pnlNacimiento.setBackground(Color.WHITE);
			pnlNacimiento.setBorder(new TitledBorder(null, "Fecha de nacimiento", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnlNacimiento.setBounds(235, 71, 339, 63);
			pnlNacimiento.setLayout(null);
			pnlNacimiento.add(getCBoxDia());
			pnlNacimiento.add(getCbBoxMes());
			pnlNacimiento.add(getCbBoxAno());
			pnlNacimiento.add(getLblDia());
			pnlNacimiento.add(getLblYear());
			pnlNacimiento.add(getLblMes());
		}
		return pnlNacimiento;
	}
	private JComboBox<String> getCBoxDia() {
		if (cBoxDia == null) {
			cBoxDia = new JComboBox<String>();
			cBoxDia.setBounds(63, 32, 47, 20);
			cBoxDia.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		}
		return cBoxDia;
	}
	private JComboBox<String> getCbBoxMes() {
		if (cbBoxMes == null) {
			cbBoxMes = new JComboBox<String>();
			cbBoxMes.setBounds(120, 32, 79, 20);
			cbBoxMes.setModel(new DefaultComboBoxModel<String>(new String[] {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"}));
		}
		return cbBoxMes;
	}
	
	private String[] generateYears() {
		String[]years = new String[90];
		for(int i = 0; i < 90; i++) {
			years[i] = i+1925+"";
		}
		return years;
	}
	private JComboBox<String> getCbBoxAno() {
		if (cbBoxAno == null) {
			cbBoxAno = new JComboBox<String>();
			cbBoxAno.setBounds(209, 32, 60, 20);
			cbBoxAno.setModel(new DefaultComboBoxModel<String>(generateYears()));
		}
		return cbBoxAno;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBackground(Color.WHITE);
			panel.setBorder(new TitledBorder(null, "Datos de registro", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(20, 137, 346, 114);
			panel.setLayout(null);
			panel.add(getLblUsuario());
			panel.add(getTextUsuario());
			panel.add(getPwdPassword());
			panel.add(getLblPassword());
			
			JLabel lblReintroduzcaPassword = new JLabel("Reintroduzca Password:");
			lblReintroduzcaPassword.setDisplayedMnemonic('r');
			lblReintroduzcaPassword.setBounds(17, 82, 170, 16);
			panel.add(lblReintroduzcaPassword);
			
			pwdReintroduzca = new JPasswordField();
			lblReintroduzcaPassword.setLabelFor(pwdReintroduzca);
			pwdReintroduzca.setBounds(193, 81, 129, 20);
			panel.add(pwdReintroduzca);
		}
		return panel;
	}
	private JTextField getTextUsuario() {
		if (textUsuario == null) {
			textUsuario = new JTextField();
			textUsuario.setBounds(193, 24, 129, 20);
			textUsuario.setColumns(10);
		}
		return textUsuario;
	}
	private JLabel getLblUsuario() {
		if (lblUsuario == null) {
			lblUsuario = new JLabel("Usuario (email):");
			lblUsuario.setLabelFor(getTextUsuario());
			lblUsuario.setDisplayedMnemonic('u');
			lblUsuario.setBounds(17, 24, 129, 17);
		}
		return lblUsuario;
	}
	private JPasswordField getPwdPassword() {
		if (pwdPassword == null) {
			pwdPassword = new JPasswordField();
			pwdPassword.setBounds(193, 52, 129, 20);
		}
		return pwdPassword;
	}
	private JLabel getLblPassword() {
		if (lblPassword == null) {
			lblPassword = new JLabel("Password:");
			lblPassword.setDisplayedMnemonic('p');
			lblPassword.setLabelFor(lblPassword);
			lblPassword.setBounds(17, 37, 200, 50);
		}
		return lblPassword;
	}
	private JLabel getLblDia() {
		if (lblDia == null) {
			lblDia = new JLabel("Dia:");
			lblDia.setLabelFor(getCBoxDia());
			lblDia.setDisplayedMnemonic('d');
			lblDia.setBounds(63, 15, 46, 14);
		}
		return lblDia;
	}
	private JLabel getLblYear() {
		if (lblYear == null) {
			lblYear = new JLabel("Año:");
			lblYear.setLabelFor(getCbBoxAno());
			lblYear.setDisplayedMnemonic('ñ');
			lblYear.setBounds(209, 15, 46, 14);
		}
		return lblYear;
	}
	private JLabel getLblMes() {
		if (lblMes == null) {
			lblMes = new JLabel("Mes:");
			lblMes.setDisplayedMnemonic('e');
			lblMes.setLabelFor(getCbBoxMes());
			lblMes.setBounds(120, 15, 46, 14);
		}
		return lblMes;
	}
}
