package igu;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import java.awt.GridLayout;

import javax.swing.JDialog;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import logica.Carrera;
import logica.Corredor;
import logica.Liebre;
import logica.Tortuga;

public class VentanaPrincipal extends JFrame {

	private Carrera carrera;
	private AccionBoton aB;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnDado;
	private JTextField textDado;
	private JLabel labelScore;
	private JLabel lblLibrep;
	private JLabel lblTortugap;
	private JLabel lblLiebre;
	private JLabel lblTortuga;
	private JTextField txtScoreliebre;
	private JTextField txtScoretortuga;
	private JPanel panelLiebre;
	private JButton btn0_l;
	private JButton btn1_l;
	private JButton btn2_l;
	private JButton btn3_l;
	private JButton btn4_l;
	private JButton btn5_l;
	private JButton btn6_l;
	private JButton btn7_l;
	private JButton btn8_l;
	private JButton btn9_l;
	private JButton btn10_l;
	private JPanel panelTortuga;
	private JButton btn0_t;
	private JButton btn1_t;
	private JButton btn2_t;
	private JButton btn3_t;
	private JButton btn4_t;
	private JButton btn5_t;
	private JButton btn6_t;
	private JButton btn7_t;
	private JButton btn8_t;
	private JButton btn9_t;
	private JButton btn10_t;
	private JMenuBar menuBar;
	private JMenu mnJuego;
	private JMenu mnAyuda;
	private JMenuItem mntmNuevo;
	private JMenuItem mntmSalir;
	private JMenuItem mntmContenidos;
	private JMenuItem mntmAcercaDe;
	private JSeparator separator;
	private JSeparator separator_1;
	private boolean mostrado;
	private JMenuItem mntmOpciones;
	private int numArboles = 2;
	private JDialog opciones;
	private boolean mensaje = false;
	private JLabel lblTurnoliebre;
	private JLabel lblTurnotortuga;

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
		carrera = new Carrera(numArboles);
		aB = new AccionBoton();
		opciones = new VentanaOpciones(this);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/liebre_peq.JPG")));
		setTitle("El juego de la Libre y la Tortuga");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 905, 392);
		setJMenuBar(getMenuBar_1());
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnDado());
		contentPane.add(getTextDado());
		contentPane.add(getLabelScore());
		contentPane.add(getLabel_1());
		contentPane.add(getLabel_2());
		contentPane.add(getLabel_3());
		contentPane.add(getLabel_4());
		contentPane.add(getTxtScoreliebre());
		contentPane.add(getTxtScoretortuga());
		contentPane.add(getPanelLiebre());
		contentPane.add(getPanelTortuga());
		modificarPanel(panelTortuga, false);
		modificarPanel(panelLiebre, false);
		contentPane.add(getLblTurnoliebre());
		contentPane.add(getLblTurnotortuga());
}
	
	public int getNumArboles() {
		return numArboles;
	}

	public void setNumArboles(int numArboles) {
		this.numArboles = numArboles;
	}

	private JButton getBtnDado() {
		if (btnDado == null) {
			btnDado = new JButton("");
			btnDado.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					carrera.lanzarDado();
					textDado.setText(String.valueOf(carrera.getNumeroDado()));
					habilitarDeshabilitarPaneles();
					if(carrera.isJugadaPosible()){
						btnDado.setEnabled(false);
					}
				}
			});
			btnDado.setDisabledIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/dado.JPG")));
			btnDado.setBackground(Color.BLACK);
			btnDado.setForeground(new Color(0, 0, 0));
			btnDado.setToolTipText("Click para lanzar el dado");
			btnDado.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/dado.JPG")));
			btnDado.setBorderPainted(false);
			btnDado.setBounds(102, 27, 65, 89);
			
		}
		return btnDado;
	}
	
	class AccionBoton implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			jugar(Integer.parseInt(((JButton) (e.getSource())).getActionCommand()));
		}		
	}
	
	private void asociarBotones(JPanel panel){		
		for(int i = 0; i < panel.getComponentCount(); i++){
			JButton boton = (JButton)(panel.getComponent(i));
			boton.setActionCommand(String.valueOf(i));
			boton.addActionListener(aB);
		}
	}
	
	private void habilitarDeshabilitarPaneles(){
		if(carrera.getCorredorActivo() instanceof Liebre){
			modificarPanel(panelLiebre, true);
			modificarPanel(panelTortuga, false);
		}
		else{
			modificarPanel(panelTortuga, true);
			modificarPanel(panelLiebre, false);
		}
	}
	
	private JTextField getTextDado() {
		if (textDado == null) {
			textDado = new JTextField();
			textDado.setForeground(Color.GREEN);
			textDado.setText("");
			textDado.setBorder(null);
			textDado.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 60));
			textDado.setEditable(false);
			textDado.setBackground(Color.BLACK);
			textDado.setBounds(177, 34, 101, 71);
			textDado.setColumns(10);
		}
		return textDado;
	}
	private JLabel getLabelScore() {
		if (labelScore == null) {
			labelScore = new JLabel("Score");
			labelScore.setForeground(Color.YELLOW);
			labelScore.setFont(new Font("Jokerman", Font.BOLD, 50));
			labelScore.setBounds(468, 11, 164, 89);
		}
		return labelScore;
	}
	private JLabel getLabel_1() {
		if (lblLibrep == null) {
			lblLibrep = new JLabel("");
			lblLibrep.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/liebre_peq.JPG")));
			lblLibrep.setBounds(669, 11, 35, 33);
		}
		return lblLibrep;
	}
	private JLabel getLabel_2() {
		if (lblTortugap == null) {
			lblTortugap = new JLabel("");
			lblTortugap.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/tortuga_peq.JPG")));
			lblTortugap.setBounds(669, 55, 35, 33);
		}
		return lblTortugap;
	}
	private JLabel getLabel_3() {
		if (lblLiebre == null) {
			lblLiebre = new JLabel("");
			lblLiebre.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/liebre.JPG")));
			lblLiebre.setBounds(35, 156, 65, 54);
		}
		return lblLiebre;
	}
	private JLabel getLabel_4() {
		if (lblTortuga == null) {
			lblTortuga = new JLabel("");
			lblTortuga.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/tortuga.JPG")));
			lblTortuga.setBounds(36, 249, 65, 54);
		}
		return lblTortuga;
	}
	private JTextField getTxtScoreliebre() {
		if (txtScoreliebre == null) {
			txtScoreliebre = new JTextField();
			txtScoreliebre.setFont(new Font("Tahoma", Font.PLAIN, 19));
			txtScoreliebre.setHorizontalAlignment(SwingConstants.CENTER);
			txtScoreliebre.setText(String.valueOf(carrera.getLiebre().getPuntuacion()));
			txtScoreliebre.setForeground(Color.MAGENTA);
			txtScoreliebre.setBackground(Color.BLACK);
			txtScoreliebre.setEditable(false);
			txtScoreliebre.setBounds(714, 18, 120, 26);
			txtScoreliebre.setColumns(10);
		}
		return txtScoreliebre;
	}
	private JTextField getTxtScoretortuga() {
		if (txtScoretortuga == null) {
			txtScoretortuga = new JTextField();
			txtScoretortuga.setFont(new Font("Tahoma", Font.PLAIN, 19));
			txtScoretortuga.setHorizontalAlignment(SwingConstants.CENTER);
			txtScoretortuga.setText(String.valueOf(carrera.getTortuga().getPuntuacion()));
			txtScoretortuga.setForeground(Color.MAGENTA);
			txtScoretortuga.setBackground(Color.BLACK);
			txtScoretortuga.setEditable(false);
			txtScoretortuga.setBounds(714, 58, 120, 26);
			txtScoretortuga.setColumns(10);
		}
		return txtScoretortuga;
	}
	private JPanel getPanelLiebre() {
		if (panelLiebre == null) {
			panelLiebre = new JPanel();
			panelLiebre.setBackground(Color.YELLOW);
			panelLiebre.setBorder(new LineBorder(Color.YELLOW, 5));
			panelLiebre.setBounds(100, 145, 778, 77);
			panelLiebre.setLayout(new GridLayout(1, 11, 2, 10));
			panelLiebre.add(getBtn0_l());
			panelLiebre.add(getBtn1_l());
			panelLiebre.add(getBtn2_l());
			panelLiebre.add(getBtn3_l());
			panelLiebre.add(getBtn4_l());
			panelLiebre.add(getBtn5_l());
			panelLiebre.add(getBtn6_l());
			panelLiebre.add(getBtn7_l());
			panelLiebre.add(getBtn8_l());
			panelLiebre.add(getBtn9_l());
			panelLiebre.add(getBtn10_l());
			pintarCalle(carrera.getLiebre(), panelLiebre.getComponents());
			this.asociarBotones(panelLiebre);
		}
		return panelLiebre;
	}
	private JButton getBtn0_l() {
		if (btn0_l == null) {
			btn0_l = new JButton("");
			btn0_l.setBackground(Color.BLACK);

		}
		return btn0_l;
	}
	private JButton getBtn1_l() {
		if (btn1_l == null) {
			btn1_l = new JButton("");
			btn1_l.setBackground(Color.BLACK);
		}
		return btn1_l;
	}
	private JButton getBtn2_l() {
		if (btn2_l == null) {
			btn2_l = new JButton("");
			btn2_l.setBackground(Color.BLACK);
		}
		return btn2_l;
	}
	private JButton getBtn3_l() {
		if (btn3_l == null) {
			btn3_l = new JButton("");
			btn3_l.setBackground(Color.BLACK);
		}
		return btn3_l;
	}
	private JButton getBtn4_l() {
		if (btn4_l == null) {
			btn4_l = new JButton("");
			btn4_l.setBackground(Color.BLACK);
		}
		return btn4_l;
	}
	private JButton getBtn5_l() {
		if (btn5_l == null) {
			btn5_l = new JButton("");
			btn5_l.setBackground(Color.BLACK);
		}
		return btn5_l;
	}
	private JButton getBtn6_l() {
		if (btn6_l == null) {
			btn6_l = new JButton("");
			btn6_l.setBackground(Color.BLACK);
		}
		return btn6_l;
	}
	private JButton getBtn7_l() {
		if (btn7_l == null) {
			btn7_l = new JButton("");
			btn7_l.setBackground(Color.BLACK);
		}
		return btn7_l;
	}
	private JButton getBtn8_l() {
		if (btn8_l == null) {
			btn8_l = new JButton("");
			btn8_l.setBackground(Color.BLACK);
		}
		return btn8_l;
	}
	private JButton getBtn9_l() {
		if (btn9_l == null) {
			btn9_l = new JButton("");
			btn9_l.setBackground(Color.BLACK);
		}
		return btn9_l;
	}
	private JButton getBtn10_l() {
		if (btn10_l == null) {
			btn10_l = new JButton("");
			btn10_l.setBackground(Color.BLACK);
		}
		return btn10_l;
	}
	private JPanel getPanelTortuga() {
		if (panelTortuga == null) {
			panelTortuga = new JPanel();
			panelTortuga.setBorder(new LineBorder(Color.YELLOW, 5));
			panelTortuga.setBackground(Color.YELLOW);
			panelTortuga.setBounds(100, 237, 778, 77);
			panelTortuga.setLayout(new GridLayout(1, 11, 2, 10));
			panelTortuga.add(getBtn0_t());
			panelTortuga.add(getBtn1_t());
			panelTortuga.add(getBtn2_t());
			panelTortuga.add(getBtn3_t());
			panelTortuga.add(getBtn4_t());
			panelTortuga.add(getBtn5_t());
			panelTortuga.add(getBtn6_t());
			panelTortuga.add(getBtn7_t());
			panelTortuga.add(getBtn8_t());
			panelTortuga.add(getBtn9_t());
			panelTortuga.add(getBtn10_t());
			pintarCalle(carrera.getTortuga(), panelTortuga.getComponents());
			this.asociarBotones(panelTortuga);
		}
		return panelTortuga;
	}
	private JButton getBtn0_t() {
		if (btn0_t == null) {
			btn0_t = new JButton("");
			btn0_t.setBackground(Color.BLACK);
		}
		return btn0_t;
	}
	private JButton getBtn1_t() {
		if (btn1_t == null) {
			btn1_t = new JButton("");
			btn1_t.setBackground(Color.BLACK);
		}
		return btn1_t;
	}
	private JButton getBtn2_t() {
		if (btn2_t == null) {
			btn2_t = new JButton("");
			btn2_t.setBackground(Color.BLACK);
		}
		return btn2_t;
	}
	private JButton getBtn3_t() {
		if (btn3_t == null) {
			btn3_t = new JButton("");
			btn3_t.setBackground(Color.BLACK);
		}
		return btn3_t;
	}
	private JButton getBtn4_t() {
		if (btn4_t == null) {
			btn4_t = new JButton("");
			btn4_t.setBackground(Color.BLACK);
		}
		return btn4_t;
	}
	private JButton getBtn5_t() {
		if (btn5_t == null) {
			btn5_t = new JButton("");
			btn5_t.setBackground(Color.BLACK);
		}
		return btn5_t;
	}
	private JButton getBtn6_t() {
		if (btn6_t == null) {
			btn6_t = new JButton("");
			btn6_t.setBackground(Color.BLACK);
		}
		return btn6_t;
	}
	private JButton getBtn7_t() {
		if (btn7_t == null) {
			btn7_t = new JButton("");
			btn7_t.setBackground(Color.BLACK);
		}
		return btn7_t;
	}
	private JButton getBtn8_t() {
		if (btn8_t == null) {
			btn8_t = new JButton("");
			btn8_t.setBackground(Color.BLACK);
		}
		return btn8_t;
	}
	private JButton getBtn9_t() {
		if (btn9_t == null) {
			btn9_t = new JButton("");
			btn9_t.setBackground(Color.BLACK);
		}
		return btn9_t;
	}
	private JButton getBtn10_t() {
		if (btn10_t == null) {
			btn10_t = new JButton("");
			btn10_t.setBackground(Color.BLACK);
		}
		return btn10_t;
	}
	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnJuego());
			menuBar.add(getMnAyuda());
		}
		return menuBar;
	}
	private JMenu getMnJuego() {
		if (mnJuego == null) {
			mnJuego = new JMenu("Juego");
			mnJuego.setMnemonic('j');
			mnJuego.add(getMntmNuevo());
			mnJuego.add(getMntmOpciones());
			mnJuego.add(getSeparator());
			mnJuego.add(getMntmSalir());
		}
		return mnJuego;
	}
	private JMenu getMnAyuda() {
		if (mnAyuda == null) {
			mnAyuda = new JMenu("Ayuda");
			mnAyuda.setMnemonic('a');
			mnAyuda.add(getMntmContenidos());
			mnAyuda.add(getSeparator_1());
			mnAyuda.add(getMntmAcercaDe());
		}
		return mnAyuda;
	}
	private JMenuItem getMntmNuevo() {
		if (mntmNuevo == null) {
			mntmNuevo = new JMenuItem("Nuevo");
			mntmNuevo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					reiniciar();
				}
			});
			mntmNuevo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
			mntmNuevo.setMnemonic('n');
		}
		return mntmNuevo;
	}
	
	public void reiniciar(){
		mostrado = false;
		mensaje = false;
		carrera.inicializarJuego(numArboles);
		this.representarEstadoJuego();
		this.btnDado.setEnabled(true);
		cambiarTurno();
	}
	
	public Carrera getCarrera(){
		return carrera;
	}
	private JMenuItem getMntmSalir() {
		if (mntmSalir == null) {
			mntmSalir = new JMenuItem("Salir");
			mntmSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.exit(0);
				}
			});
			mntmSalir.setMnemonic('s');
		}
		return mntmSalir;
	}
	private JMenuItem getMntmContenidos() {
		if (mntmContenidos == null) {
			mntmContenidos = new JMenuItem("Contenidos");
			mntmContenidos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JOptionPane.showMessageDialog(null, "La ayuda no esta disponible.");
				}
			});
			mntmContenidos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
			mntmContenidos.setMnemonic('c');
		}
		return mntmContenidos;
	}
	private JMenuItem getMntmAcercaDe() {
		if (mntmAcercaDe == null) {
			mntmAcercaDe = new JMenuItem("Acerca de");
			mntmAcercaDe.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JOptionPane.showMessageDialog(null, "Juego de la Liebre y la Tortuga \n Comunicaci�n Persona M�quina 2014-2015 \n E. I. Inform�tica de Oviedo \n Alumno: Santiago Mart�n Agra");
				}
			});
			mntmAcercaDe.setMnemonic('r');
		}
		return mntmAcercaDe;
	}
	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
		}
		return separator;
	}
	private JSeparator getSeparator_1() {
		if (separator_1 == null) {
			separator_1 = new JSeparator();
		}
		return separator_1;
	}
	
	private void modificarPanel(JPanel panel, boolean habilitado) {
		Component[] botones = panel.getComponents();
		for(int i = 0; i < botones.length; i++) {
			JButton boton = (JButton) botones[i];
			boton.setEnabled(habilitado);
		}
		
	}
	
	private void jugar(int jugar){
		if(carrera.isJugadaCorrecta(jugar)){
			carrera.resolverJugada();
			representarEstadoJuego();
			modificarPanel(panelLiebre,false);
			modificarPanel(panelTortuga, false);
		}
		cambiarTurno();
	}
	
	private void cambiarTurno(){
		if(carrera.getCorredorActivo() instanceof Liebre){
			lblTurnoliebre.setVisible(true);
			lblTurnotortuga.setVisible(false);
		}
		else{
			lblTurnoliebre.setVisible(false);
			lblTurnotortuga.setVisible(true);
		}
	}
	private void representarEstadoJuego(){
		this.textDado.setText("");
		
		this.txtScoreliebre.setText(String.valueOf(carrera.getLiebre().getPuntuacion()));
		this.txtScoretortuga.setText(String.valueOf(carrera.getTortuga().getPuntuacion()));
		
		pintarLiebre();
		pintarTortuga();
		
		if(carrera.isPartidaFinalizada()){
			finalizar();
		}
		else {
			this.btnDado.setEnabled(true);
		}
	}
	
	private void finalizar(){
		Tortuga tortuga = (Tortuga) carrera.getTortuga();
		Liebre liebre = (Liebre) carrera.getLiebre();
		
		if(tortuga.getPosicion() == 10){
			JOptionPane.showMessageDialog(null, "�Partida finalizada!\nResultado: \n1.Tortuga(" + tortuga.getPuntuacion()
					+ "ptos) \n2.Liebre(" + liebre.getPuntuacion() + "ptos)");
		}
		else if(liebre.getPosicion() == 10){
			JOptionPane.showMessageDialog(null, "�Partida finalizada!\nResultado: \n1.Liebre(" + liebre.getPuntuacion()
					+ "ptos) \n2.Tortuga(" + tortuga.getPuntuacion() + "ptos)");
		}
		else{
			if(tortuga.getPuntuacion() > liebre.getPuntuacion()){
				JOptionPane.showMessageDialog(null, "�Partida finalizada!\nResultado: \n1.Tortuga(" + tortuga.getPuntuacion()
						+ "ptos) \n2.Liebre(" + liebre.getPuntuacion() + "ptos)");
			}
			else{
				JOptionPane.showMessageDialog(null, "�Partida finalizada!\nResultado: \n1.Liebre(" + liebre.getPuntuacion()
						+ "ptos) \n2.Tortuga(" + tortuga.getPuntuacion() + "ptos)");
			}
		}
		reiniciar();
	}
	
	private void pintarLiebre(){
		if(carrera.getLiebre().getPosicion() == Corredor.POSICION_SALIDA){
			this.lblLiebre.setIcon(new ImageIcon(getClass().getResource("/img/" + carrera.getLiebre().getFoto())));
		}
		else {
			lblLiebre.setIcon(null);
		}
		pintarCalle(carrera.getLiebre(), panelLiebre.getComponents());

	}
	private void pintarTortuga(){
		if(carrera.getTortuga().getPosicion() == Corredor.POSICION_SALIDA){
			this.lblTortuga.setIcon(new ImageIcon(getClass().getResource("/img/" + carrera.getTortuga().getFoto())));
		}
		else {
			lblTortuga.setIcon(null);
		}
		pintarCalle(carrera.getTortuga(), panelTortuga.getComponents());
	}
	
	private void pintarCalle(Corredor corredor, Component[] botones){
		String img = "/img/" + corredor.getFoto();
		for(int i = 0; i < botones.length; i++)
		{
			JButton boton = (JButton) botones[i];
			if(i == corredor.getPosicion())
			{
				boton.setIcon(new ImageIcon(getClass().getResource(img)));
				boton.setDisabledIcon(new ImageIcon(getClass().getResource(img)));
				if(corredor.getCalleAsignada().getCasilla(i).tieneArbol()){
					boton.setIcon(new ImageIcon(getClass().getResource("/img/liebre_durmiendo.jpg")));
					boton.setDisabledIcon(new ImageIcon(getClass().getResource("/img/liebre_durmiendo.jpg")));
				}
				if(corredor.getCalleAsignada().getCasilla(i).tieneBonus() && !mensaje){
					mensaje = true;
					JOptionPane.showMessageDialog(null, "¡Bonus! Puntuación x2.");
				}
			}
			else if(corredor.getCalleAsignada().getCasilla(i).tieneArbol()){
					boton.setIcon(new ImageIcon(getClass().getResource("/img/arbol.jpg")));
					boton.setDisabledIcon(new ImageIcon(getClass().getResource("/img/arbol.jpg")));
			}
			else if(corredor.getCalleAsignada().getCasilla(i).tieneAgujero()){
				if(!mostrado){
					mostrado = true;
					if(corredor instanceof Liebre){
						System.out.println("Agujero Liebre en " + i);
					}
					else{
						System.out.println("Agujero Tortuga en " + i);
					}
				}
				boton.setIcon(new ImageIcon(getClass().getResource("/img/agujero.jpg")));
				boton.setDisabledIcon(new ImageIcon(getClass().getResource("/img/agujero.jpg")));
			}
			else if(corredor.getCalleAsignada().getCasilla(i).tieneBonus()){
				boton.setIcon(new ImageIcon(getClass().getResource("/img/bonus.jpg")));
				boton.setDisabledIcon(new ImageIcon(getClass().getResource("/img/bonus.jpg")));
			}
			else{
				boton.setIcon(null);
			}
		}
	}
	private JMenuItem getMntmOpciones() {
		if (mntmOpciones == null) {
			mntmOpciones = new JMenuItem("Opciones");
			mntmOpciones.setMnemonic('p');
			mntmOpciones.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					opciones.setVisible(true);
				}
			});
		}
		return mntmOpciones;
	}
	private JLabel getLblTurnoliebre() {
		if (lblTurnoliebre == null) {
			lblTurnoliebre = new JLabel("");
			lblTurnoliebre.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/turno.jpg")));
			lblTurnoliebre.setBounds(0, 177, 35, 14);
			lblTurnoliebre.setVisible(false);
		}
		return lblTurnoliebre;
	}
	private JLabel getLblTurnotortuga() {
		if (lblTurnotortuga == null) {
			lblTurnotortuga = new JLabel("");
			lblTurnotortuga.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/turno.jpg")));
			lblTurnotortuga.setBounds(0, 278, 35, 14);
		}
		return lblTurnotortuga;
	}
}
