package igu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Font;

public class VentanaOpciones extends JDialog {

	private static final long serialVersionUID = -790890143314580517L;
	private final JPanel contentPanel = new JPanel();
	private JCheckBox chckbxActivado;
	private JSpinner spinner;
	private VentanaPrincipal vp;

	/**
	 * Create the dialog.
	 */
	public VentanaOpciones(final VentanaPrincipal vp) {
		setTitle("Opciones - El juego de la Libre y la Tortuga");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaOpciones.class.getResource("/img/liebre_peq.JPG")));
		this.vp = vp;
		setModal(true);
		setBounds(100, 100, 450, 152);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.BLACK);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNmeroDeArboles = new JLabel("N\u00FAmero de arboles para la liebre:");
			lblNmeroDeArboles.setDisplayedMnemonic('N');
			lblNmeroDeArboles.setLabelFor(getSpinner());
			lblNmeroDeArboles.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNmeroDeArboles.setForeground(Color.WHITE);
			lblNmeroDeArboles.setBounds(52, 14, 229, 14);
			contentPanel.add(lblNmeroDeArboles);
		}
		{
			JLabel lblPremioParaLa = new JLabel("Premio para la tortuga: ");
			lblPremioParaLa.setToolTipText("Una casilla de bonus cuya puntuaci\u00F3n es el doble.");
			lblPremioParaLa.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblPremioParaLa.setForeground(Color.WHITE);
			lblPremioParaLa.setBounds(52, 51, 193, 14);
			contentPanel.add(lblPremioParaLa);
		}
		contentPanel.add(getChckbxActivado());
		contentPanel.add(getSpinner());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.BLACK);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Aplicar");
				okButton.setMnemonic('a');
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						vp.setNumArboles((int) spinner.getValue());
						if(!(chckbxActivado.isSelected() && vp.getCarrera().getBonus())){
							vp.getCarrera().cambiarBonus();
						}
						vp.reiniciar();
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setMnemonic('c');
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	private JCheckBox getChckbxActivado() {
		if (chckbxActivado == null) {
			chckbxActivado = new JCheckBox("Activado");
			chckbxActivado.setMnemonic('t');
			chckbxActivado.setFont(new Font("Tahoma", Font.PLAIN, 14));
			chckbxActivado.setForeground(Color.WHITE);
			chckbxActivado.setBackground(Color.BLACK);
			chckbxActivado.setBounds(208, 47, 97, 23);
			chckbxActivado.setSelected(vp.getCarrera().getBonus());

		}
		return chckbxActivado;
	}
	private JSpinner getSpinner() {
		if (spinner == null) {
			spinner = new JSpinner();
			spinner.setModel(new SpinnerNumberModel(0, 0, 8, 1));
			spinner.setBounds(287, 13, 29, 20);
			spinner.setValue(vp.getNumArboles());
		}
		return spinner;
	}
}
