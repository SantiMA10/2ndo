package modulo.igu;

import javax.help.*;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import modulo.logica.*;
import modulo.util.ComponentsUtil;
import modulo.util.DateUtil;
import modulo.util.ImageUtil;
import modulo.util.MiCellRender;
import modulo.util.MiModeloTabla;

import java.awt.CardLayout;

import javax.swing.JTextPane;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.Color;

import javax.swing.ImageIcon;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.FlowLayout;
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.GridLayout;

import javax.swing.ListSelectionModel;
import javax.swing.JList;
import javax.swing.JTextArea;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.SwingConstants;

import com.toedter.calendar.JCalendar;

import java.awt.Component;

import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

public class VentanaPrincipal extends JFrame {
	private static final long serialVersionUID = 1L;
	public static Color DEFAULT_COLOR = new Color(32, 178, 170);
	public static final int DEFAULT_BUTTON_SIZE = 25;// 35
	public static final int DEFAULT_TITTLE_SIZE = 60;
	public static final int DEFAULT_TEXTAREA_SIZE = 25;
	public static final int DEFAULT_BORDER_TITTLE_SIZE = 30;
	public static final int TABLE_HEADER_HEIGHT = 32;
	public static final int TABLE_HEADER_SIZE = 18;

	private JPanel contentPane;
	private GestorDePedidos gestor;
	private JPanel panelInicial;
	private JPanel panelInicialSur;
	private JButton btnInicioEspaol;
	private JButton btnInicioIngls;
	private ResourceBundle traduccion;
	private JPanel panelCartelera;
	private JLabel lblCarteleraTitulo;
	private JPanel panelCarteleraCentro;
	private JScrollPane scrollPaneTablaCartelera;
	private JTable tablaCartelera;
	private MiModeloTabla modeloCartelera;
	private JPanel panelCarteleraSur;
	private JPanel panelCarteleraSurIzquierda;
	private JPanel panelCarteleraSurDerecha;
	private JButton btnCarteleraAtras;
	private JButton btnCarteleraSiguiente;
	private JPanel panelCarteleraEste;
	private JPanel panelCarteleraRecomendadas;
	private JPanel panelCarteleraDatos;
	private JPanel panelCarteleraPrecios;
	private JPanel panelCarteleraPedido;
	private JLabel lblCarteleraRecomendada1;
	private JLabel lblCarteleraRecomendada2;
	private JScrollPane scrollPanePrecios;
	private JScrollPane scrollPanePedido;
	private JList<Pelicula> listaPedido;
	private DefaultListModel<Pelicula> modeloListaPedido;
	private JPanel panelCarteleraPedidoSur;
	private JButton btnCarteleraIrPedido;
	private JTextArea txtrCarteleraPrecios;
	private JPanel panelPelicula;
	private JLabel lblPeliculaTitulo;
	private JPanel panelPeliculaSur;
	private JPanel panelPeliculaSurIzquierda;
	private JPanel panelPeliculaSurDerecha;
	private JButton btnPeliculaAtras;
	private JButton btnPeliculaSiguiente;
	private JPanel panelPeliculaOeste;
	private JPanel panelPeliculaCentro;
	private JPanel panelPeliculaInformacion;
	private JPanel panelPeliculaSinopsis;
	private JLabel lblPeliculaImagen;
	private JButton btnPeliculaVerTrailer;
	private JScrollPane scrollPaneInformacion;
	private JScrollPane scrollPaneSinopsis;
	private JTextArea txtrSinopsis;
	private JTextArea txtrInformacion;
	private JButton btnPeliculaFormato;
	private JPanel panelHorario;
	private JCalendar calendar;
	private JPanel panelHorarioCentro;
	private JPanel panelHorarioHoras;
	private JScrollPane scrollPaneHoras;
	private JList<String> listaHoras;
	private DefaultListModel<String> modeloListaHoras;
	private JLabel lblHorarioTitulo;
	private JPanel panelHorarioSur;
	private JPanel panelHorarioOeste;
	private JPanel panelHorarioSurIzquierda;
	private JPanel panelHorarioSurCentro;
	private JPanel panelHorarioSurDerecha;
	private JButton btnHorarioAtras;
	private JButton btnHorarioVolverCartelera;
	private JButton btnHorarioSiguiente;
	private JLabel lblHorarioImagen;
	private Component horizontalGlue;
	private JPanel panelHorarioJCalendar;
	private JPanel panelSala;
	private JPanel panelSalaNorteIzquierda;
	private JPanel panelSalaSur;
	private JPanel panelSalaSurDerecha;
	private JPanel panelSalaSurCentro;
	private JPanel panelSalaSurIzquierda;
	private JPanel panelSalaEste;
	private JPanel panelSalaEsteLeyenda;
	private JPanel panelSalaEsteAbajo;
	private JPanel panelSalaTipoEntrada;
	private JPanel panelSalaPedido;
	private JButton btnSalaAtras;
	private JButton btnSalaVolverCartelera;
	private JButton btnSalaSiguiente;
	private JLabel lblSalaTitulo;
	private JLabel lblSalaSesion;
	private JLabel lblLeyendaLibre;
	private JLabel lblLeyendaLibreImagen;
	private JLabel lblLeyendaOcupado;
	private JLabel lblLeyendaOcupadoImagen;
	private JLabel lblLeyendaNormal;
	private JLabel lblLeyendaNormalImagen;
	private JLabel lblLeyendaJubilado;
	private JLabel lblLeyendaJubiladoImagen;
	private JLabel lblLeyendaCumple;
	private JLabel lblLeyendaCumpleImagen;
	private JLabel lblLeyendaJubicumpleImagen;
	private JPanel panelSalaTipoEntradaCheck;
	private JCheckBox chckbxJubilado;
	private JCheckBox chckbxCumple;
	private JScrollPane scrollPaneTipoEntrada;
	private JTextArea txtrTipoEntrada;
	private JPanel panelSalaPedidoTotal;
	private JScrollPane scrollPanelSalaPedido;
	private JTextArea txtrSalaPedido;
	private JLabel lblEntradasTotales;
	private JTextField txtEntradasTotales;
	private JTextArea txtrLeyendaJubiCumple;
	private JPanel panelSalaCentro;
	private JButton btnButaca;
	private JButton btnButaca_1;
	private JButton btnButaca_2;
	private JButton btnButaca_3;
	private JButton btnButaca_4;
	private Component horizontalGluePasillo3;
	private JButton btnButaca_5;
	private JButton btnButaca_6;
	private JButton btnButaca_7;
	private JButton btnButaca_8;
	private JButton btnButaca_9;
	private Component horizontalGlueHueco2I;
	private JButton btnButaca_10;
	private JButton btnButaca_11;
	private JButton btnButuca;
	private JButton btnButaca_12;
	private Component horizontalGluePasillo2;
	private JButton btnButaca_13;
	private JButton btnButaca_14;
	private JButton btnButaca_15;
	private JButton btnButaca_16;
	private Component horizontalGlueHueco2D;
	private Component horizontalGlueHueco1I;
	private JButton btnButaca_18;
	private JButton btnButaca_19;
	private JButton btnButaca_20;
	private JButton btnButaca_21;
	private Component horizontalGluePasillo1;
	private JButton btnButaca_22;
	private JButton btnButaca_23;
	private JButton btnButaca_24;
	private JButton btnButaca_25;
	private Component horizontalGlueHueco1D;
	private Component horizontalGlueHueco0I2;
	private Component horizontalGlueHueco0I1;
	private Component horizontalGlueHueco0I0;
	private JButton btnButaca_17;
	private JButton btnButaca_26;
	private Component horizontalGluePasillo0;
	private JButton btnButaca_27;
	private JButton btnButaca_28;
	private Component horizontalGlueHueco0D0;
	private Component horizontalGlueHueco0D1;
	private Component horizontalGlueHueco0D2;
	private JPanel panelPedido;
	private JLabel lblPedidoTitulo;
	private JPanel panelPedidoSur;
	private JPanel panelPedidoSurIzquierda;
	private JPanel panelPedidoSurCentro;
	private JPanel panelPedidoSurDerecha;
	private JButton btnPedidoAtras;
	private JButton btnPedidoComprarMas;
	private JButton btnPedidoFinalizar;
	private JScrollPane scrollPanePedidoCentro;
	private JTable tablaPedido;
	private MiModeloTabla modeloPedido;
	private JPanel panelPanelPedidoCentro;
	private JPanel panelPanelPedidoCentroSur;
	private JLabel lblPedidoPrecioTotal;
	private JTextField txtPedidoPrecioTotal;
	private JPanel panelInicialCentro;
	private JLabel lblLogoCine;
	private JLabel lblBienvenida;
	private JPanel panelCarteleraNorte;
	private JPanel panelCarteleraNorteIzquierda;
	private JPanel panelCarteleraNorteDerecha;
	private JButton btnCarteleraAyuda;
	private JPanel panelPeliculaNorte;
	private JPanel panelPeliculaNorteIzquierda;
	private JPanel panelPeliculaNorteDerecha;
	private JButton btnPeliculaAyuda;
	private JPanel panelHorarioNorte;
	private JPanel panelHorarioNorteIzquierda;
	private JPanel panelHorarioNorteDerecha;
	private JButton btnHorarioAyuda;
	private JPanel panelSalaNorte;
	private JPanel panelSalaNorteDerecha;
	private JButton btnSalaAyuda;
	private JPanel panelPedidoNorte;
	private JButton btnPedidoAyuda;
	private JPanel panelPedidoNorteDerecha;
	private URL hsURL;
	private HelpSet hs;
	private HelpBroker hb;
	private JButton btnPedidoCancelar;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gestor = new GestorDePedidos();
		setTitle(gestor.getNombreCine());
		setBounds(100, 100, 805, 481);
		contentPane = new JPanel();
		contentPane.setBackground(DEFAULT_COLOR);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		contentPane.add(getPanelInicial(), "inicial");
		contentPane.add(getPanelCartelera(), "cartelera");
		contentPane.add(getPanelPelicula(), "pelicula");
		contentPane.add(getPanelHorario(), "horario");
		contentPane.add(getPanelSala(), "sala");
		contentPane.add(getPanelPedido(), "pedido");
		localizar(null);
		cargarAyuda();
		
	}
	private void cargarAyuda(){
		try{
			File fichero = new File("help/ayuda.hs");
		   	hsURL = fichero.toURI().toURL();
			hs = new HelpSet(null, hsURL);
			hb = hs.createHelpBroker();
			hb.enableHelpKey(getPanelInicial(), "introduccion", hs);
			hb.enableHelpKey(getPanelCartelera(), "cartelera", hs);
			hb.enableHelpKey(getPanelPelicula(), "ficha", hs);
			hb.enableHelpKey(getPanelHorario(), "horario", hs);
			hb.enableHelpKey(getPanelSala(), "sala", hs);
			hb.enableHelpKey(getPanelPedido(), "pedido", hs);
			
			hb.enableHelpOnButton(btnCarteleraAyuda, "cartelera", hs);
			hb.enableHelpOnButton(btnPeliculaAyuda, "ficha", hs);
			hb.enableHelpOnButton(btnHorarioAyuda, "horario", hs);
			hb.enableHelpOnButton(btnSalaAyuda, "sala", hs);
			hb.enableHelpOnButton(btnPedidoAyuda, "pedido", hs);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	private void localizar(Locale localizacion) {
		if (localizacion == null) {
			localizacion = Locale.getDefault(Locale.Category.FORMAT);
		}
		traduccion = ResourceBundle.getBundle("modulo.traducciones/traduccion",
				localizacion);
		// Inicio
		lblBienvenida.setText(traduccion.getString("bienvenidos") + " "
				+ gestor.getNombreCine());
		btnInicioEspaol.setText(traduccion.getString("btnEs"));
		btnInicioEspaol.setMnemonic(traduccion.getString("nBtnEs").charAt(0));
		btnInicioIngls.setText(traduccion.getString("btnEn"));
		btnInicioIngls.setMnemonic(traduccion.getString("nBtnEn").charAt(0));
		// Cartelera
		lblCarteleraTitulo.setText(traduccion.getString("cartelera"));
		btnCarteleraAtras.setText(traduccion.getString("btnAtras"));
		btnCarteleraAtras.setMnemonic(traduccion.getString("nBtnAtras").charAt(0));
		btnCarteleraSiguiente.setText(traduccion.getString("btnSiguiente"));
		btnCarteleraSiguiente.setMnemonic(traduccion.getString("nBtnSiguiente").charAt(0));
		panelCarteleraRecomendadas.setBorder(ComponentsUtil
				.getBorder(traduccion.getString("recomendadas")));
		panelCarteleraPrecios.setBorder(ComponentsUtil.getBorder(traduccion
				.getString("precios")));
		panelCarteleraPedido.setBorder(ComponentsUtil.getBorder(traduccion
				.getString("pedido")));
		btnCarteleraIrPedido.setText(traduccion.getString("btnIrPedido"));
		modeloCartelera.setColumnIdentifiers(new String[] {
				traduccion.getString("pelicula"),
				traduccion.getString("informacion"), "Oculto" });
		ajustarTablaCartelera();
		txtrCarteleraPrecios.setText(gestor.getListaPrecios(traduccion));
		// Pelicula
		btnPeliculaAtras.setText(traduccion.getString("btnAtras"));
		btnPeliculaAtras.setMnemonic(traduccion.getString("nBtnAtras").charAt(0));
		btnPeliculaSiguiente.setText(traduccion.getString("btnSiguiente"));
		btnPeliculaSiguiente.setMnemonic(traduccion.getString("nBtnSiguiente").charAt(0));
		btnPeliculaVerTrailer.setText(traduccion.getString("btnVerTrailer"));
		btnPeliculaVerTrailer.setMnemonic(traduccion.getString("nBtnVerTrailer").charAt(0));
		panelPeliculaSinopsis.setBorder(ComponentsUtil.getBorder(traduccion
				.getString("sinopsis")));
		panelPeliculaInformacion.setBorder(ComponentsUtil.getBorder(traduccion
				.getString("informacion")));
		// Horario
		btnHorarioAtras.setText(traduccion.getString("btnAtras"));
		btnHorarioAtras.setMnemonic(traduccion.getString("nBtnAtras").charAt(0));
		btnHorarioVolverCartelera.setText(traduccion
				.getString("btnVolverACartelera"));
		btnHorarioVolverCartelera.setMnemonic(traduccion.getString("nBtnVolverACartelera").charAt(0));
		btnHorarioSiguiente.setText(traduccion.getString("btnSiguiente"));
		btnHorarioSiguiente.setMnemonic(traduccion.getString("nBtnSiguiente").charAt(0));
		lblHorarioTitulo.setText(traduccion.getString("horario"));
		panelHorarioJCalendar.setBorder(ComponentsUtil.getBorder(traduccion
				.getString("calendario")));
		// Sala
		panelSalaEsteLeyenda.setBorder(ComponentsUtil.getBorder(traduccion
				.getString("leyenda")));
		panelSalaTipoEntrada.setBorder(ComponentsUtil.getBorder(traduccion
				.getString("tipoEntrada")));
		panelSalaPedido.setBorder(ComponentsUtil.getBorder(traduccion
				.getString("pedidoActual")));
		lblLeyendaLibre.setText(traduccion.getString("libre"));
		lblLeyendaOcupado.setText(traduccion.getString("ocupado"));
		lblLeyendaNormal.setText(traduccion.getString("normal"));
		lblLeyendaJubilado.setText(traduccion.getString("jubilado"));
		lblLeyendaCumple.setText(traduccion.getString("cumple"));
		txtrLeyendaJubiCumple.setText(traduccion.getString("jubicumple"));
		chckbxCumple.setText(traduccion.getString("cumple"));
		chckbxJubilado.setText(traduccion.getString("jubilado"));
		btnSalaAtras.setText(traduccion.getString("btnAtras"));
		btnSalaAtras.setMnemonic(traduccion.getString("nBtnAtras").charAt(0));
		btnSalaSiguiente.setText(traduccion.getString("btnSiguiente"));
		btnSalaSiguiente.setMnemonic(traduccion.getString("nBtnSiguiente").charAt(0));
		btnSalaVolverCartelera.setText(traduccion
				.getString("btnVolverACartelera"));
		btnSalaVolverCartelera.setMnemonic(traduccion
				.getString("nBtnVolverACartelera").charAt(0));
		txtrTipoEntrada.setText(traduccion.getString("notaTipoEntrada"));
		txtrSalaPedido.setText(traduccion.getString("pedidoVacio"));
		lblEntradasTotales.setText(traduccion.getString("entradasTotales"));
		// Pedido
		lblPedidoTitulo.setText(traduccion.getString("pedido"));
		btnPedidoAtras.setText(traduccion.getString("btnAtras"));
		btnPedidoAtras.setMnemonic(traduccion.getString("nBtnAtras").charAt(0));
		btnPedidoFinalizar.setText(traduccion.getString("btnFinalizar"));
		btnPedidoFinalizar.setMnemonic(traduccion.getString("nBtnFinalizar").charAt(0));
		btnPedidoComprarMas.setText(traduccion.getString("btnComprarMas"));
		btnPedidoComprarMas.setMnemonic(traduccion.getString("nBtnComprarMas").charAt(0));
		btnPedidoCancelar.setText(traduccion.getString("btnCancelar"));
		btnPedidoCancelar.setMnemonic(traduccion.getString("nBtnCancelar").charAt(0));
		modeloPedido.setColumnIdentifiers(new String[] {
				traduccion.getString("numEntradas"),
				traduccion.getString("sesion"),
				traduccion.getString("pelicula"),
				traduccion.getString("precio") });
		lblPedidoPrecioTotal.setText(traduccion.getString("precioTotal"));
	}

	private JPanel getPanelInicial() {
		if (panelInicial == null) {
			panelInicial = new JPanel();
			panelInicial.setBackground(DEFAULT_COLOR);
			panelInicial.setLayout(new BorderLayout(0, 0));
			panelInicial.add(getPanelInicialSur(), BorderLayout.SOUTH);
			panelInicial.add(getPanelInicialCentro(), BorderLayout.CENTER);
			panelInicial.add(getLblBienvenida(), BorderLayout.NORTH);
		}
		return panelInicial;
	}

	private JPanel getPanelInicialSur() {
		if (panelInicialSur == null) {
			panelInicialSur = new JPanel();
			panelInicialSur.setBackground(DEFAULT_COLOR);
			panelInicialSur.add(getBtnInicioEspaol());
			panelInicialSur.add(getBtnInicioIngls());
		}
		return panelInicialSur;
	}

	private JButton getBtnInicioEspaol() {
		if (btnInicioEspaol == null) {
			btnInicioEspaol = new JButton("Espa\u00F1ol");
			btnInicioEspaol.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					localizar(new Locale("es"));
					((CardLayout) contentPane.getLayout()).show(contentPane,
							"cartelera");
				}
			});
			btnInicioEspaol.setIcon(new ImageIcon(VentanaPrincipal.class
					.getResource("/modulo/img/banderaEs.jpg")));
			btnInicioEspaol.setFont(new Font("Lucida Grande", Font.PLAIN,
					DEFAULT_BUTTON_SIZE));
		}
		return btnInicioEspaol;
	}

	private JButton getBtnInicioIngls() {
		if (btnInicioIngls == null) {
			btnInicioIngls = new JButton("Ingl\u00E9s");
			btnInicioIngls.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					localizar(new Locale("en"));
					((CardLayout) contentPane.getLayout()).show(contentPane,
							"cartelera");
				}
			});
			btnInicioIngls.setIcon(new ImageIcon(VentanaPrincipal.class
					.getResource("/modulo/img/banderaEn.jpg")));
			btnInicioIngls.setFont(new Font("Lucida Grande", Font.PLAIN,
					DEFAULT_BUTTON_SIZE));
		}
		return btnInicioIngls;
	}

	private JPanel getPanelCartelera() {
		if (panelCartelera == null) {
			panelCartelera = new JPanel();
			panelCartelera.setBackground(DEFAULT_COLOR);
			panelCartelera.setLayout(new BorderLayout(0, 0));
			panelCartelera.add(getPanelCarteleraCentro(), BorderLayout.CENTER);
			panelCartelera.add(getPanelCarteleraSur(), BorderLayout.SOUTH);
			panelCartelera.add(getPanelCarteleraEste(), BorderLayout.EAST);
			panelCartelera.add(getPanelCarteleraNorte(), BorderLayout.NORTH);
		}
		return panelCartelera;
	}

	private JLabel getLblCarteleraTitutlo() {
		if (lblCarteleraTitulo == null) {
			lblCarteleraTitulo = new JLabel("Cartelera Titutlo");
			lblCarteleraTitulo.setFont(new Font("Lucida Grande", Font.PLAIN,
					DEFAULT_TITTLE_SIZE));
		}
		return lblCarteleraTitulo;
	}

	private JPanel getPanelCarteleraCentro() {
		if (panelCarteleraCentro == null) {
			panelCarteleraCentro = new JPanel();
			panelCarteleraCentro.setBackground(DEFAULT_COLOR);
			panelCarteleraCentro.setLayout(new BorderLayout(0, 0));
			panelCarteleraCentro.add(getScrollPaneTablaCartelera(),
					BorderLayout.CENTER);
		}
		return panelCarteleraCentro;
	}

	private JScrollPane getScrollPaneTablaCartelera() {
		if (scrollPaneTablaCartelera == null) {
			scrollPaneTablaCartelera = new JScrollPane();
			scrollPaneTablaCartelera.setOpaque(false);
			scrollPaneTablaCartelera.setBackground(DEFAULT_COLOR);
			scrollPaneTablaCartelera.setViewportView(getTablaCartelera());
		}
		return scrollPaneTablaCartelera;
	}

	private JTable getTablaCartelera() {
		if (tablaCartelera == null) {
			String[] nombreColumnas = { "Pelicula", "Informacion", "Oculto" };
			modeloCartelera = new MiModeloTabla(nombreColumnas, 0);
			tablaCartelera = new JTable(modeloCartelera);
			tablaCartelera.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int fila = tablaCartelera.getSelectedRow();
					if (fila != -1) {
						if (e.getClickCount() == 1) {
							btnCarteleraSiguiente.setEnabled(true);
						}
						if (e.getClickCount() == 2) {
							gestor.setPeliculaActual((Pelicula) modeloCartelera
									.getValueAt(fila, 2));
							pasarAPanelPelicula();
						}
						if (fila > 0) {
							JTextPane objecto = ((JTextPane) tablaCartelera
									.getValueAt(fila - 1, 1));
							objecto.setForeground(Color.BLACK);
							tablaCartelera.setValueAt(objecto, fila - 1, 1);
						}
						if (fila < tablaCartelera.getRowCount()) {
							JTextPane objecto = ((JTextPane) tablaCartelera
									.getValueAt(fila + 1, 1));
							objecto.setForeground(Color.BLACK);
							tablaCartelera.setValueAt(objecto, fila + 1, 1);
						}
					}
				}
			});
			tablaCartelera.getTableHeader().setReorderingAllowed(false);
			tablaCartelera
					.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			prepararModeloCartelera();
			tablaCartelera.setDefaultRenderer(JTextPane.class,
					new MiCellRender());

		}
		return tablaCartelera;
	}

	private void prepararModeloCartelera() {
		Object[] nuevaFila = new Object[3];
		for (int i = 0; i < gestor.getCartelera().size(); i++) {
			nuevaFila[0] = ImageUtil.redimensionarImagen(gestor.getCartelera()
					.get(i).getRutaImagen(), 432, 300);
			nuevaFila[1] = ComponentsUtil.getJTextPane(gestor.getCartelera()
					.get(i)
					+ "\n"
					+ gestor.getCartelera().get(i).getNombreSala());
			nuevaFila[2] = gestor.getCartelera().get(i);
			modeloCartelera.addRow(nuevaFila);
		}
	}

	private void ajustarTablaCartelera() {
		for (int i = 0; i < tablaCartelera.getRowCount(); i++) {
			tablaCartelera.setRowHeight(i, 300);
		}
		ComponentsUtil.changeJTableHeaderSize(tablaCartelera);
		tablaCartelera.getColumnModel().getColumn(0).setMaxWidth(208);
		tablaCartelera.getColumnModel().getColumn(0).setMinWidth(208);
		if(tablaCartelera.getColumnCount() == 3)
			tablaCartelera.getColumnModel().removeColumn(tablaCartelera.getColumnModel().getColumn(2));
	}

	private JPanel getPanelCarteleraSur() {
		if (panelCarteleraSur == null) {
			panelCarteleraSur = new JPanel();
			panelCarteleraSur.setBackground(DEFAULT_COLOR);
			panelCarteleraSur.setLayout(new GridLayout(0, 2, 0, 0));
			panelCarteleraSur.add(getPanelCarteleraSurIzquierda());
			panelCarteleraSur.add(getPanelCarteleraSurDerecha());
		}
		return panelCarteleraSur;
	}

	private JPanel getPanelCarteleraSurIzquierda() {
		if (panelCarteleraSurIzquierda == null) {
			panelCarteleraSurIzquierda = new JPanel();
			panelCarteleraSurIzquierda.setBackground(DEFAULT_COLOR);
			panelCarteleraSurIzquierda.setLayout(new FlowLayout(
					FlowLayout.LEFT, 5, 5));
			panelCarteleraSurIzquierda.add(getBtnCarteleraAtras());
		}
		return panelCarteleraSurIzquierda;
	}

	private JPanel getPanelCarteleraSurDerecha() {
		if (panelCarteleraSurDerecha == null) {
			panelCarteleraSurDerecha = new JPanel();
			panelCarteleraSurDerecha.setBackground(DEFAULT_COLOR);
			FlowLayout flowLayout = (FlowLayout) panelCarteleraSurDerecha
					.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			panelCarteleraSurDerecha.add(getBtnCarteleraSiguiente());
		}
		return panelCarteleraSurDerecha;
	}

	private JButton getBtnCarteleraAtras() {
		if (btnCarteleraAtras == null) {
			btnCarteleraAtras = new JButton("Cartelera Atras");
			btnCarteleraAtras.setFont(new Font("Lucida Grande", Font.PLAIN,
					DEFAULT_BUTTON_SIZE));
			btnCarteleraAtras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					((CardLayout) contentPane.getLayout()).show(contentPane,
							"inicial");
				}
			});
		}
		return btnCarteleraAtras;
	}

	private JButton getBtnCarteleraSiguiente() {
		if (btnCarteleraSiguiente == null) {
			btnCarteleraSiguiente = new JButton("Cartelera Siguiente");
			btnCarteleraSiguiente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int fila = tablaCartelera.getSelectedRow();
					if (fila != -1) {
						gestor.setPeliculaActual((Pelicula) modeloCartelera
								.getValueAt(fila, 2));
						pasarAPanelPelicula();
					}
				}
			});
			btnCarteleraSiguiente.setEnabled(false);
			btnCarteleraSiguiente.setFont(new Font("Lucida Grande", Font.PLAIN,
					DEFAULT_BUTTON_SIZE));
		}
		return btnCarteleraSiguiente;
	}

	private void pasarAPanelPelicula() {
		prepararPanelPelicula();
		((CardLayout) contentPane.getLayout()).show(contentPane, "pelicula");
	}

	private JPanel getPanelCarteleraEste() {
		if (panelCarteleraEste == null) {
			panelCarteleraEste = new JPanel();
			panelCarteleraEste.setBackground(DEFAULT_COLOR);
			panelCarteleraEste.setLayout(new GridLayout(2, 0, 0, 0));
			panelCarteleraEste.add(getPanelCarteleraRecomendadas());
			panelCarteleraEste.add(getPanelCarteleraDatos());
		}
		return panelCarteleraEste;
	}

	private JPanel getPanelCarteleraRecomendadas() {
		if (panelCarteleraRecomendadas == null) {
			panelCarteleraRecomendadas = new JPanel();
			panelCarteleraRecomendadas.setBackground(DEFAULT_COLOR);
			panelCarteleraRecomendadas.setLayout(new GridLayout(0, 2, 5, 5));
			panelCarteleraRecomendadas.add(getLblCarteleraRecomendada1());
			panelCarteleraRecomendadas.add(getLblCarteleraRecomendada2());
		}
		return panelCarteleraRecomendadas;
	}

	private JPanel getPanelCarteleraDatos() {
		if (panelCarteleraDatos == null) {
			panelCarteleraDatos = new JPanel();
			panelCarteleraDatos.setBackground(DEFAULT_COLOR);
			panelCarteleraDatos.setLayout(new GridLayout(2, 0, 0, 0));
			panelCarteleraDatos.add(getPanelCarteleraPrecios());
			panelCarteleraDatos.add(getPanelCarteleraPedido());
		}
		return panelCarteleraDatos;
	}

	private JPanel getPanelCarteleraPrecios() {
		if (panelCarteleraPrecios == null) {
			panelCarteleraPrecios = new JPanel();
			panelCarteleraPrecios.setBackground(DEFAULT_COLOR);
			panelCarteleraPrecios.setLayout(new BorderLayout(0, 0));
			panelCarteleraPrecios.add(getScrollPanePrecios());
		}
		return panelCarteleraPrecios;
	}

	private JPanel getPanelCarteleraPedido() {
		if (panelCarteleraPedido == null) {
			panelCarteleraPedido = new JPanel();
			if (gestor.isPedidoVacio()) {
				panelCarteleraPedido.setVisible(false);
			}
			panelCarteleraPedido.setBackground(DEFAULT_COLOR);
			panelCarteleraPedido.setLayout(new BorderLayout(0, 0));
			panelCarteleraPedido.add(getScrollPanePedido());
			panelCarteleraPedido.add(getPanelCarteleraPedidoSur(),
					BorderLayout.SOUTH);
		}
		return panelCarteleraPedido;
	}

	private JLabel getLblCarteleraRecomendada1() {
		if (lblCarteleraRecomendada1 == null) {
			lblCarteleraRecomendada1 = new JLabel("");
			lblCarteleraRecomendada1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					gestor.setPeliculaActual(gestor.getRecomendadas()[0]);
					pasarAPanelPelicula();
				}
			});
			lblCarteleraRecomendada1
					.setHorizontalAlignment(SwingConstants.CENTER);
			lblCarteleraRecomendada1
					.addComponentListener(new ComponentAdapter() {
						@Override
						public void componentResized(ComponentEvent e) {
							ImageUtil
									.adaptarImagen(lblCarteleraRecomendada1,
											gestor.getRecomendadas()[0]
													.getRutaImagen());
						}
					});
		}
		return lblCarteleraRecomendada1;
	}

	private JLabel getLblCarteleraRecomendada2() {
		if (lblCarteleraRecomendada2 == null) {
			lblCarteleraRecomendada2 = new JLabel("");
			lblCarteleraRecomendada2
					.setHorizontalAlignment(SwingConstants.CENTER);
			lblCarteleraRecomendada2.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					gestor.setPeliculaActual(gestor.getRecomendadas()[1]);
					pasarAPanelPelicula();
				}
			});
			lblCarteleraRecomendada2
					.addComponentListener(new ComponentAdapter() {
						@Override
						public void componentResized(ComponentEvent e) {
							ImageUtil
									.adaptarImagen(lblCarteleraRecomendada2,
											gestor.getRecomendadas()[1]
													.getRutaImagen());
						}
					});
		}
		return lblCarteleraRecomendada2;
	}

	private JScrollPane getScrollPanePrecios() {
		if (scrollPanePrecios == null) {
			scrollPanePrecios = new JScrollPane();
			scrollPanePrecios.setBackground(DEFAULT_COLOR);
			scrollPanePrecios.setViewportView(getTxtrCarteleraPrecios());
		}
		return scrollPanePrecios;
	}

	private JScrollPane getScrollPanePedido() {
		if (scrollPanePedido == null) {
			scrollPanePedido = new JScrollPane();
			scrollPanePedido.setBackground(DEFAULT_COLOR);
			scrollPanePedido.setViewportView(getListaPedido());
		}
		return scrollPanePedido;
	}

	private JList<Pelicula> getListaPedido() {
		if (listaPedido == null) {
			modeloListaPedido = new DefaultListModel<Pelicula>();
			listaPedido = new JList<Pelicula>(modeloListaPedido);
		}
		return listaPedido;
	}

	private JPanel getPanelCarteleraPedidoSur() {
		if (panelCarteleraPedidoSur == null) {
			panelCarteleraPedidoSur = new JPanel();
			panelCarteleraPedidoSur.setBackground(DEFAULT_COLOR);
			panelCarteleraPedidoSur.setLayout(new FlowLayout(FlowLayout.RIGHT,
					5, 5));
			panelCarteleraPedidoSur.add(getBtnCarteleraIrPedido());
		}
		return panelCarteleraPedidoSur;
	}

	private JButton getBtnCarteleraIrPedido() {
		if (btnCarteleraIrPedido == null) {
			btnCarteleraIrPedido = new JButton("Cartelera Ir Pedido");
			btnCarteleraIrPedido.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnPedidoAtras.setEnabled(false);
					((CardLayout) contentPane.getLayout()).show(contentPane,
							"pedido");
				}
			});
		}
		return btnCarteleraIrPedido;
	}

	private JTextArea getTxtrCarteleraPrecios() {
		if (txtrCarteleraPrecios == null) {
			txtrCarteleraPrecios = new JTextArea();
			txtrCarteleraPrecios.setText("Cartelera Precios");
		}
		return txtrCarteleraPrecios;
	}

	private void volverACartelera() {
		modeloCartelera.setRowCount(0);
		prepararModeloCartelera();
		ajustarTablaCartelera();
		btnCarteleraSiguiente.setEnabled(false);
		((CardLayout) contentPane.getLayout()).show(contentPane, "cartelera");
		if (gestor.isPedidoVacio()) {
			panelCarteleraPedido.setVisible(false);
		} else {
			panelCarteleraPedido.setVisible(true);
		}
	}

	private JPanel getPanelPelicula() {
		if (panelPelicula == null) {
			panelPelicula = new JPanel();
			panelPelicula.setBackground(DEFAULT_COLOR);
			panelPelicula.setLayout(new BorderLayout(0, 0));
			panelPelicula.add(getPanelPeliculaSur(), BorderLayout.SOUTH);
			panelPelicula.add(getPanelPeliculaOeste(), BorderLayout.WEST);
			panelPelicula.add(getPanelPeliculaCentro(), BorderLayout.CENTER);
			panelPelicula.add(getPanelPeliculaNorte(), BorderLayout.NORTH);
		}
		return panelPelicula;
	}

	private JLabel getLblPeliculaTitulo() {
		if (lblPeliculaTitulo == null) {
			lblPeliculaTitulo = new JLabel("Pelicula Titulo");
			lblPeliculaTitulo.setFont(new Font("Lucida Grande", Font.PLAIN,
					DEFAULT_TITTLE_SIZE));
		}
		return lblPeliculaTitulo;
	}

	private JPanel getPanelPeliculaSur() {
		if (panelPeliculaSur == null) {
			panelPeliculaSur = new JPanel();
			panelPeliculaSur.setBackground(DEFAULT_COLOR);
			panelPeliculaSur.setLayout(new BorderLayout(0, 0));
			panelPeliculaSur.add(getPanelPeliculaSurIzquierda(), BorderLayout.WEST);
			panelPeliculaSur.add(getPanelPeliculaSurDerecha());
		}
		return panelPeliculaSur;
	}

	private JPanel getPanelPeliculaSurIzquierda() {
		if (panelPeliculaSurIzquierda == null) {
			panelPeliculaSurIzquierda = new JPanel();
			panelPeliculaSurIzquierda.setBackground(DEFAULT_COLOR);
			panelPeliculaSurIzquierda.setLayout(new FlowLayout(FlowLayout.LEFT,
					5, 5));
			panelPeliculaSurIzquierda.add(getBtnPeliculaAtras());
		}
		return panelPeliculaSurIzquierda;
	}

	private JPanel getPanelPeliculaSurDerecha() {
		if (panelPeliculaSurDerecha == null) {
			panelPeliculaSurDerecha = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panelPeliculaSurDerecha
					.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			panelPeliculaSurDerecha.setBackground(DEFAULT_COLOR);
			panelPeliculaSurDerecha.add(getBtnPeliculaFormato());
			panelPeliculaSurDerecha.add(getBtnPeliculaSiguiente());
		}
		return panelPeliculaSurDerecha;
	}

	private JButton getBtnPeliculaAtras() {
		if (btnPeliculaAtras == null) {
			btnPeliculaAtras = new JButton("Pelicula Atras");
			btnPeliculaAtras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					lblPeliculaImagen.setIcon(null);
					lblPeliculaImagen.removeComponentListener(lblPeliculaImagen
							.getComponentListeners()[0]);
					((CardLayout) contentPane.getLayout()).show(contentPane,
							"cartelera");
				}
			});
			btnPeliculaAtras.setFont(new Font("Lucida Grande", Font.PLAIN,
					DEFAULT_BUTTON_SIZE));
		}
		return btnPeliculaAtras;
	}

	private JButton getBtnPeliculaSiguiente() {
		if (btnPeliculaSiguiente == null) {
			btnPeliculaSiguiente = new JButton("Pelicula Siguiente");
			btnPeliculaSiguiente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					lblPeliculaImagen.setIcon(null);
					lblPeliculaImagen.removeComponentListener(lblPeliculaImagen
							.getComponentListeners()[0]);
					prepararPanelHorario();
					((CardLayout) contentPane.getLayout()).show(contentPane,
							"horario");
				}
			});
			btnPeliculaSiguiente.setFont(new Font("Lucida Grande", Font.PLAIN,
					DEFAULT_BUTTON_SIZE));
		}
		return btnPeliculaSiguiente;
	}

	private JPanel getPanelPeliculaOeste() {
		if (panelPeliculaOeste == null) {
			panelPeliculaOeste = new JPanel();
			panelPeliculaOeste.setBackground(DEFAULT_COLOR);
			panelPeliculaOeste.setLayout(new BorderLayout(0, 0));
			panelPeliculaOeste.add(getLblPeliculaImagen());
			panelPeliculaOeste.add(getBtnPeliculaVerTrailer(),
					BorderLayout.SOUTH);
		}
		return panelPeliculaOeste;
	}

	private JPanel getPanelPeliculaCentro() {
		if (panelPeliculaCentro == null) {
			panelPeliculaCentro = new JPanel();
			panelPeliculaCentro.setBackground(DEFAULT_COLOR);
			panelPeliculaCentro.setLayout(new GridLayout(2, 0, 0, 0));
			panelPeliculaCentro.add(getPanelPeliculaInformacion());
			panelPeliculaCentro.add(getPanelPeliculaSinopsis());
		}
		return panelPeliculaCentro;
	}

	private JPanel getPanelPeliculaInformacion() {
		if (panelPeliculaInformacion == null) {
			panelPeliculaInformacion = new JPanel();
			panelPeliculaInformacion.setBackground(DEFAULT_COLOR);
			panelPeliculaInformacion.setLayout(new BorderLayout(0, 0));
			panelPeliculaInformacion.add(getScrollPaneInformacion());
		}
		return panelPeliculaInformacion;
	}

	private JPanel getPanelPeliculaSinopsis() {
		if (panelPeliculaSinopsis == null) {
			panelPeliculaSinopsis = new JPanel();
			panelPeliculaSinopsis.setBackground(DEFAULT_COLOR);
			panelPeliculaSinopsis.setLayout(new BorderLayout(0, 0));
			panelPeliculaSinopsis.add(getScrollPaneSinopsis());
		}
		return panelPeliculaSinopsis;
	}

	private JLabel getLblPeliculaImagen() {
		if (lblPeliculaImagen == null) {
			lblPeliculaImagen = new JLabel("");
			lblPeliculaImagen.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblPeliculaImagen;
	}

	private void prepararPanelPelicula() {
		ImageUtil.adaptarImagen(lblPeliculaImagen, gestor.getPeliculaActual()
				.getRutaImagen());
		lblPeliculaImagen.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				ImageUtil.adaptarImagen(lblPeliculaImagen, gestor
						.getPeliculaActual().getRutaImagen());
			}
		});
		lblPeliculaTitulo.setText(gestor.getPeliculaActual().toString());
		txtrSinopsis.setText(gestor.getPeliculaActual().getSinopsis());
		txtrInformacion.setText(gestor.getPeliculaActual().getInformacion(
				traduccion));
		Pelicula pelicula = gestor.cambiarFormato();
		if (pelicula != null) {
			btnPeliculaFormato.setVisible(true);
			if (!pelicula.isIs3D()) {
				btnPeliculaFormato.setActionCommand("2D");
				btnPeliculaFormato
						.setText(traduccion.getString("btnFormato2D"));
			} else {
				btnPeliculaFormato.setActionCommand("3D");
				btnPeliculaFormato
						.setText(traduccion.getString("btnFormato3D"));
			}
		} else {
			btnPeliculaFormato.setVisible(false);
		}
		if (gestor.getPeliculaActual().getTrailerID() != null) {
			btnPeliculaVerTrailer.setEnabled(true);
		} else {
			btnPeliculaVerTrailer.setEnabled(false);
		}
	}

	private void openOnYouTube(String id) {
		if (Desktop.isDesktopSupported() && !id.isEmpty()) {
			try {
				URI url = new URI("http://www.youtube.com/v/" + id);
				Desktop.getDesktop().browse(url);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(this,
					traduccion.getString("errorYouTube"),
					traduccion.getString("error"), JOptionPane.WARNING_MESSAGE);
		}
	}

	private JButton getBtnPeliculaVerTrailer() {
		if (btnPeliculaVerTrailer == null) {
			btnPeliculaVerTrailer = new JButton("Pelicula Ver Trailer");
			btnPeliculaVerTrailer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					openOnYouTube(gestor.getPeliculaActual().getTrailerID());
				}
			});
			btnPeliculaVerTrailer.setFont(new Font("Lucida Grande", Font.PLAIN,
					DEFAULT_BUTTON_SIZE));
		}
		return btnPeliculaVerTrailer;
	}

	private JScrollPane getScrollPaneInformacion() {
		if (scrollPaneInformacion == null) {
			scrollPaneInformacion = new JScrollPane();
			scrollPaneInformacion.setViewportView(getTxtrInformacion());
		}
		return scrollPaneInformacion;
	}

	private JScrollPane getScrollPaneSinopsis() {
		if (scrollPaneSinopsis == null) {
			scrollPaneSinopsis = new JScrollPane();
			scrollPaneSinopsis.setViewportView(getTxtrSinopsis());
		}
		return scrollPaneSinopsis;
	}

	private JTextArea getTxtrSinopsis() {
		if (txtrSinopsis == null) {
			txtrSinopsis = new JTextArea();
			txtrSinopsis.setFont(new Font("Lucida Grande", Font.PLAIN,
					DEFAULT_TEXTAREA_SIZE));
			txtrSinopsis.setLineWrap(true);
			txtrSinopsis.setWrapStyleWord(true);
			txtrSinopsis.setEditable(false);
			txtrSinopsis.setText("Sinopsis");
		}
		return txtrSinopsis;
	}

	private JTextArea getTxtrInformacion() {
		if (txtrInformacion == null) {
			txtrInformacion = new JTextArea();
			txtrInformacion.setFont(new Font("Lucida Grande", Font.PLAIN,
					DEFAULT_TEXTAREA_SIZE));
			txtrInformacion.setLineWrap(true);
			txtrInformacion.setEditable(false);
			txtrInformacion.setWrapStyleWord(true);
			txtrInformacion.setText("Informacion");
		}
		return txtrInformacion;
	}

	private JButton getBtnPeliculaFormato() {
		if (btnPeliculaFormato == null) {
			btnPeliculaFormato = new JButton("Pelicula Formato");
			btnPeliculaFormato.setFont(new Font("Lucida Grande", Font.PLAIN,
					DEFAULT_BUTTON_SIZE));
			btnPeliculaFormato.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gestor.setPeliculaActual(gestor.cambiarFormato());
					prepararPanelPelicula();
				}
			});
		}
		return btnPeliculaFormato;
	}

	private JPanel getPanelHorario() {
		if (panelHorario == null) {
			panelHorario = new JPanel();
			panelHorario.setBackground(DEFAULT_COLOR);
			panelHorario.setLayout(new BorderLayout(0, 0));
			panelHorario.add(getPanelHorarioSur(), BorderLayout.SOUTH);
			panelHorario.add(getPanelHorarioOeste(), BorderLayout.WEST);
			panelHorario.add(getPanelHorarioCentro());
			panelHorario.add(getPanelHorarioNorte(), BorderLayout.NORTH);
		}
		return panelHorario;
	}

	private void prepararPanelHorario() {
		ImageUtil.adaptarImagen(lblHorarioImagen, gestor.getPeliculaActual()
				.getRutaImagen());
		iniciarJCalendar();
		calendar.addPropertyChangeListener("calendar",
				new PropertyChangeListener() {
					@Override
					public void propertyChange(PropertyChangeEvent e) {
						String fecha = calendar.getDayChooser().getDay() + "/"
								+ (calendar.getMonthChooser().getMonth() + 1)
								+ "/" + calendar.getYearChooser().getYear();
						Date date = DateUtil.getFecha(fecha);
						if ((calendar.getMinSelectableDate().before(date) && calendar
								.getMaxSelectableDate().after(date))
								|| calendar.getMaxSelectableDate().equals(date)
								|| calendar.getMinSelectableDate().equals(date)) {
							panelHorarioHoras.setBorder(ComponentsUtil
									.getBorder(DateUtil.getFechaConFormato(
											fecha, traduccion.getLocale())));
							prepararModeloListaHoras();
						}
					}
				});
		btnHorarioSiguiente.setEnabled(false);
		prepararModeloListaHoras();
	}

	private void iniciarJCalendar() {
		String fechaMinima = gestor.getPeliculaActual().getFechas()[0];
		String fechaMaxima = gestor.getPeliculaActual().getFechas()[gestor
				.getPeliculaActual().getFechas().length - 1];
		calendar = new JCalendar();
		calendar.setLocale(traduccion.getLocale());
		calendar.setDate(DateUtil.getFecha(fechaMinima));
		if (panelHorarioJCalendar.getComponentCount() == 1) {
			panelHorarioJCalendar.remove(0);
			panelHorarioJCalendar.add(calendar);
		} else {
			panelHorarioJCalendar.add(calendar);
		}
		panelHorarioHoras.setBorder(ComponentsUtil.getBorder(DateUtil
				.getFechaConFormato(fechaMinima, traduccion.getLocale())));
		calendar.setSelectableDateRange(DateUtil.getFecha(fechaMinima),
				DateUtil.getFecha(fechaMaxima));
	}

	private void prepararModeloListaHoras() {
		modeloListaHoras.clear();
		for (int i = 0; i < gestor.getPeliculaActual().getHoras().length; i++) {
			if (!gestor.isSalaLlena(DateUtil.getFechaSinFormato(
					((TitledBorder) panelHorarioHoras.getBorder()).getTitle(),
					traduccion.getLocale()), gestor.getPeliculaActual()
					.getHoras()[i])) {
				modeloListaHoras.addElement(gestor.getPeliculaActual()
						.getHoras()[i]);
			}
		}
		if (modeloListaHoras.isEmpty()) {
			modeloListaHoras.addElement("Todas las salas llenas");
		}
	}

	private JPanel getPanelHorarioCentro() {
		if (panelHorarioCentro == null) {
			panelHorarioCentro = new JPanel();
			panelHorarioCentro.setBackground(DEFAULT_COLOR);
			panelHorarioCentro.setLayout(new GridLayout(2, 2, 2, 5));
			panelHorarioCentro.add(getPanelHorarioJCalendar());
			panelHorarioCentro.add(getPanelHorarioHoras());

		}
		return panelHorarioCentro;
	}

	private JPanel getPanelHorarioHoras() {
		if (panelHorarioHoras == null) {
			panelHorarioHoras = new JPanel();
			panelHorarioHoras.setBackground(DEFAULT_COLOR);
			panelHorarioHoras.setLayout(new BorderLayout(0, 0));
			panelHorarioHoras.add(getScrollPaneHoras(), BorderLayout.CENTER);
		}
		return panelHorarioHoras;
	}

	private JScrollPane getScrollPaneHoras() {
		if (scrollPaneHoras == null) {
			scrollPaneHoras = new JScrollPane();
			scrollPaneHoras.setViewportView(getListaHoras());
		}
		return scrollPaneHoras;
	}

	private JList<String> getListaHoras() {
		if (listaHoras == null) {
			modeloListaHoras = new DefaultListModel<String>();
			listaHoras = new JList<String>(modeloListaHoras);
			listaHoras.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 1) {
						btnHorarioSiguiente.setEnabled(true);
					} else if (e.getClickCount() == 2) {
						gestor.setSalaActual(DateUtil.getFechaSinFormato(
								((TitledBorder) panelHorarioHoras.getBorder())
										.getTitle(), traduccion.getLocale()),
								listaHoras.getSelectedValue());
						prepararPanelSala();
						((CardLayout) contentPane.getLayout()).show(
								contentPane, "sala");
					}
				}
			});
			listaHoras.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
			listaHoras.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		return listaHoras;
	}

	private JLabel getLblHorarioTitulo() {
		if (lblHorarioTitulo == null) {
			lblHorarioTitulo = new JLabel("Horario Titulo");
			lblHorarioTitulo.setFont(new Font("Lucida Grande", Font.PLAIN,
					DEFAULT_TITTLE_SIZE));
		}
		return lblHorarioTitulo;
	}

	private JPanel getPanelHorarioSur() {
		if (panelHorarioSur == null) {
			panelHorarioSur = new JPanel();
			panelHorarioSur.setBackground(DEFAULT_COLOR);
			panelHorarioSur.setLayout(new BorderLayout(0, 0));
			panelHorarioSur.add(getPanelHorarioSurIzquierda(), BorderLayout.WEST);
			panelHorarioSur.add(getPanelHorarioSurCentro());
			panelHorarioSur.add(getPanelHorarioSurDerecha(), BorderLayout.EAST);
		}
		return panelHorarioSur;
	}

	private JPanel getPanelHorarioOeste() {
		if (panelHorarioOeste == null) {
			panelHorarioOeste = new JPanel();
			panelHorarioOeste.setBackground(DEFAULT_COLOR);
			panelHorarioOeste.setLayout(new BorderLayout(5, 5));
			panelHorarioOeste.add(getLblHorarioImagen(), BorderLayout.CENTER);
			panelHorarioOeste.add(getHorizontalGlue(), BorderLayout.SOUTH);
		}
		return panelHorarioOeste;
	}

	private JPanel getPanelHorarioSurIzquierda() {
		if (panelHorarioSurIzquierda == null) {
			panelHorarioSurIzquierda = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panelHorarioSurIzquierda
					.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			panelHorarioSurIzquierda.setBackground(DEFAULT_COLOR);
			panelHorarioSurIzquierda.add(getBtnHorarioAtras());
		}
		return panelHorarioSurIzquierda;
	}

	private JPanel getPanelHorarioSurCentro() {
		if (panelHorarioSurCentro == null) {
			panelHorarioSurCentro = new JPanel();
			panelHorarioSurCentro.setBackground(DEFAULT_COLOR);
			panelHorarioSurCentro.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			panelHorarioSurCentro.add(getBtnHorarioVolverCartelera());
		}
		return panelHorarioSurCentro;
	}

	private JPanel getPanelHorarioSurDerecha() {
		if (panelHorarioSurDerecha == null) {
			panelHorarioSurDerecha = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panelHorarioSurDerecha
					.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			panelHorarioSurDerecha.setBackground(DEFAULT_COLOR);
			panelHorarioSurDerecha.add(getBtnHorarioSiguiente());
		}
		return panelHorarioSurDerecha;
	}

	private JButton getBtnHorarioAtras() {
		if (btnHorarioAtras == null) {
			btnHorarioAtras = new JButton("Horario Atras");
			btnHorarioAtras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					prepararPanelPelicula();
					lblHorarioImagen.setIcon(null);
					((CardLayout) contentPane.getLayout()).show(contentPane,
							"pelicula");
				}
			});
			btnHorarioAtras.setFont(new Font("Lucida Grande", Font.PLAIN,
					DEFAULT_BUTTON_SIZE));
		}
		return btnHorarioAtras;
	}

	private JButton getBtnHorarioVolverCartelera() {
		if (btnHorarioVolverCartelera == null) {
			btnHorarioVolverCartelera = new JButton("Horario Volver Cartelera");
			btnHorarioVolverCartelera.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					lblHorarioImagen.setIcon(null);
					volverACartelera();
				}
			});
			btnHorarioVolverCartelera.setFont(new Font("Lucida Grande",
					Font.PLAIN, DEFAULT_BUTTON_SIZE));
		}
		return btnHorarioVolverCartelera;
	}

	private JButton getBtnHorarioSiguiente() {
		if (btnHorarioSiguiente == null) {
			btnHorarioSiguiente = new JButton("Horario Siguiente");
			btnHorarioSiguiente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int indice = listaHoras.getSelectedIndex();
					if (indice != -1) {
						gestor.setSalaActual(DateUtil.getFechaSinFormato(
								((TitledBorder) panelHorarioHoras.getBorder())
										.getTitle(), traduccion.getLocale()),
								listaHoras.getSelectedValue());
						((CardLayout) contentPane.getLayout()).show(
								contentPane, "sala");
						prepararPanelSala();
					}
				}
			});
			btnHorarioSiguiente.setEnabled(false);
			btnHorarioSiguiente.setFont(new Font("Lucida Grande", Font.PLAIN,
					DEFAULT_BUTTON_SIZE));
		}
		return btnHorarioSiguiente;
	}

	private JLabel getLblHorarioImagen() {
		if (lblHorarioImagen == null) {
			lblHorarioImagen = new JLabel("");
			lblHorarioImagen.addComponentListener(new ComponentAdapter() {
				@Override
				public void componentResized(ComponentEvent e) {
					if (gestor.getPeliculaActual() != null) {
						ImageUtil.adaptarImagen(lblHorarioImagen, gestor
								.getPeliculaActual().getRutaImagen());
					}
				}
			});
		}
		return lblHorarioImagen;
	}

	private Component getHorizontalGlue() {
		if (horizontalGlue == null) {
			horizontalGlue = Box.createHorizontalGlue();
		}
		return horizontalGlue;
	}

	private JPanel getPanelHorarioJCalendar() {
		if (panelHorarioJCalendar == null) {
			panelHorarioJCalendar = new JPanel();
			panelHorarioJCalendar.setBackground(DEFAULT_COLOR);
			panelHorarioJCalendar.setLayout(new GridLayout(1, 1, 0, 0));
		}
		return panelHorarioJCalendar;
	}

	private JPanel getPanelSala() {
		if (panelSala == null) {
			panelSala = new JPanel();
			panelSala.setBackground(DEFAULT_COLOR);
			panelSala.setLayout(new BorderLayout(0, 0));
			panelSala.add(getPanelSalaSur(), BorderLayout.SOUTH);
			panelSala.add(getPanelSalaEste(), BorderLayout.EAST);
			panelSala.add(getPanelSalaCentro(), BorderLayout.CENTER);
			panelSala.add(getPanelSalaNorte(), BorderLayout.NORTH);
		}
		return panelSala;
	}

	private JPanel getPanelSalaNorteIzquierda() {
		if (panelSalaNorteIzquierda == null) {
			panelSalaNorteIzquierda = new JPanel();
			panelSalaNorteIzquierda.setBackground(DEFAULT_COLOR);
			panelSalaNorteIzquierda.setLayout(new GridLayout(2, 0, 0, 0));
			panelSalaNorteIzquierda.add(getLblSalaTitutlo());
			panelSalaNorteIzquierda.add(getLblSalaSesion());
		}
		return panelSalaNorteIzquierda;
	}

	private JPanel getPanelSalaSur() {
		if (panelSalaSur == null) {
			panelSalaSur = new JPanel();
			panelSalaSur.setBackground(DEFAULT_COLOR);
			panelSalaSur.setLayout(new BorderLayout(0, 0));
			panelSalaSur.add(getPanelSalaSurIzquierda(), BorderLayout.WEST);
			panelSalaSur.add(getPanelSalaSurCentro());
			panelSalaSur.add(getPanelSalaSurDerecha(), BorderLayout.EAST);
		}
		return panelSalaSur;
	}

	private JPanel getPanelSalaSurDerecha() {
		if (panelSalaSurDerecha == null) {
			panelSalaSurDerecha = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panelSalaSurDerecha
					.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			panelSalaSurDerecha.setBackground(DEFAULT_COLOR);
			panelSalaSurDerecha.add(getBtnSalaSiguiente());
		}
		return panelSalaSurDerecha;
	}

	private JPanel getPanelSalaSurCentro() {
		if (panelSalaSurCentro == null) {
			panelSalaSurCentro = new JPanel();
			panelSalaSurCentro.setBackground(DEFAULT_COLOR);
			panelSalaSurCentro.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			panelSalaSurCentro.add(getBtnSalaVolverCartelera());
		}
		return panelSalaSurCentro;
	}

	private JPanel getPanelSalaSurIzquierda() {
		if (panelSalaSurIzquierda == null) {
			panelSalaSurIzquierda = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panelSalaSurIzquierda
					.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			panelSalaSurIzquierda.setBackground(DEFAULT_COLOR);
			panelSalaSurIzquierda.add(getBtnSalaAtras());
		}
		return panelSalaSurIzquierda;
	}

	private JPanel getPanelSalaEste() {
		if (panelSalaEste == null) {
			panelSalaEste = new JPanel();
			panelSalaEste.setBackground(DEFAULT_COLOR);
			panelSalaEste.setLayout(new GridLayout(2, 0, 0, 0));
			panelSalaEste.add(getPanelSalaEsteLeyenda());
			panelSalaEste.add(getPanelSalaEsteAbajo());
		}
		return panelSalaEste;
	}

	private JPanel getPanelSalaEsteLeyenda() {
		if (panelSalaEsteLeyenda == null) {
			panelSalaEsteLeyenda = new JPanel();
			panelSalaEsteLeyenda.setBackground(DEFAULT_COLOR);
			panelSalaEsteLeyenda.setLayout(new GridLayout(3, 4, 0, 0));
			panelSalaEsteLeyenda.add(getLblLeyendaLibre());
			panelSalaEsteLeyenda.add(getLblLeyendaLibreImagen());
			panelSalaEsteLeyenda.add(getLblLeyendaOcupado());
			panelSalaEsteLeyenda.add(getLblLeyendaOcupadoImagen());
			panelSalaEsteLeyenda.add(getLblLeyendaNormal());
			panelSalaEsteLeyenda.add(getLblLeyendaNormalImagen());
			panelSalaEsteLeyenda.add(getLblLeyendaJubilado());
			panelSalaEsteLeyenda.add(getLblLeyendaJubiladoImagen());
			panelSalaEsteLeyenda.add(getLblLeyendaCumple());
			panelSalaEsteLeyenda.add(getLblLeyendaCumpleImagen());
			panelSalaEsteLeyenda.add(getTxtrLeyendaJubiCumple());
			panelSalaEsteLeyenda.add(getLblLeyendaJubicumpleImagen());
		}
		return panelSalaEsteLeyenda;
	}

	private JPanel getPanelSalaEsteAbajo() {
		if (panelSalaEsteAbajo == null) {
			panelSalaEsteAbajo = new JPanel();
			panelSalaEsteAbajo.setBackground(DEFAULT_COLOR);
			panelSalaEsteAbajo.setLayout(new GridLayout(0, 1, 0, 0));
			panelSalaEsteAbajo.add(getPanelSalaTipoEntrada());
			panelSalaEsteAbajo.add(getPanelSalaPedido());
		}
		return panelSalaEsteAbajo;
	}

	private JPanel getPanelSalaTipoEntrada() {
		if (panelSalaTipoEntrada == null) {
			panelSalaTipoEntrada = new JPanel();
			panelSalaTipoEntrada.setBackground(DEFAULT_COLOR);
			panelSalaTipoEntrada.setLayout(new BorderLayout(0, 0));
			panelSalaTipoEntrada.add(getPanelSalaTipoEntradaCheck(),
					BorderLayout.SOUTH);
			panelSalaTipoEntrada.add(getScrollPaneTipoEntrada(),
					BorderLayout.CENTER);
		}
		return panelSalaTipoEntrada;
	}

	private JPanel getPanelSalaPedido() {
		if (panelSalaPedido == null) {
			panelSalaPedido = new JPanel();
			panelSalaPedido.setBackground(DEFAULT_COLOR);
			panelSalaPedido.setLayout(new BorderLayout(0, 0));
			panelSalaPedido.add(getPanelSalaPedidoTotal(), BorderLayout.SOUTH);
			panelSalaPedido
					.add(getScrollPanelSalaPedido(), BorderLayout.CENTER);
		}
		return panelSalaPedido;
	}

	private JButton getBtnSalaAtras() {
		if (btnSalaAtras == null) {
			btnSalaAtras = new JButton("Sala Atras");
			btnSalaAtras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					prepararPanelHorario();
					((CardLayout) contentPane.getLayout()).show(contentPane,
							"horario");
				}
			});
			btnSalaAtras.setFont(new Font("Lucida Grande", Font.PLAIN,
					DEFAULT_BUTTON_SIZE));
		}
		return btnSalaAtras;
	}

	private JButton getBtnSalaVolverCartelera() {
		if (btnSalaVolverCartelera == null) {
			btnSalaVolverCartelera = new JButton("Sala Volver Cartelera");
			btnSalaVolverCartelera.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					volverACartelera();
				}
			});
			btnSalaVolverCartelera.setFont(new Font("Lucida Grande",
					Font.PLAIN, DEFAULT_BUTTON_SIZE));
		}
		return btnSalaVolverCartelera;
	}

	private JButton getBtnSalaSiguiente() {
		if (btnSalaSiguiente == null) {
			btnSalaSiguiente = new JButton("Sala Siguiente");
			btnSalaSiguiente.setEnabled(false);
			btnSalaSiguiente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					prepararModeloPedido();
					DecimalFormat formato = new DecimalFormat("###.##");
					if (gestor.getEntradasPeliculaActual() > 0
							&& !modeloListaPedido.contains(gestor
									.getPeliculaActual())) {
						modeloListaPedido.addElement(gestor.getPeliculaActual());
						txtPedidoPrecioTotal.setText(String.valueOf(formato.format(gestor
								.getPrecioTotal())) + "€");
					}
					((CardLayout) contentPane.getLayout()).show(contentPane,
							"pedido");
				}
			});
			btnSalaSiguiente.setFont(new Font("Lucida Grande", Font.PLAIN,
					DEFAULT_BUTTON_SIZE));
		}
		return btnSalaSiguiente;
	}

	private JLabel getLblSalaTitutlo() {
		if (lblSalaTitulo == null) {
			lblSalaTitulo = new JLabel("Sala Titulo");
			lblSalaTitulo.setFont(new Font("Lucida Grande", Font.PLAIN,
					DEFAULT_TITTLE_SIZE));
		}
		return lblSalaTitulo;
	}

	private JLabel getLblSalaSesion() {
		if (lblSalaSesion == null) {
			lblSalaSesion = new JLabel("Sala Sesion");
			lblSalaSesion.setFont(new Font("Lucida Grande", Font.PLAIN,
					DEFAULT_TITTLE_SIZE / 2));
		}
		return lblSalaSesion;
	}

	private JLabel getLblLeyendaLibre() {
		if (lblLeyendaLibre == null) {
			lblLeyendaLibre = new JLabel("Leyenda Libre");
			lblLeyendaLibre.setVerticalAlignment(SwingConstants.TOP);
			lblLeyendaLibre.setVerticalTextPosition(SwingConstants.TOP);
			lblLeyendaLibre.setHorizontalAlignment(SwingConstants.TRAILING);
		}
		return lblLeyendaLibre;
	}

	private JLabel getLblLeyendaLibreImagen() {
		if (lblLeyendaLibreImagen == null) {
			lblLeyendaLibreImagen = new JLabel("");
			lblLeyendaLibreImagen.addComponentListener(new ComponentAdapter() {
				@Override
				public void componentResized(ComponentEvent e) {
					ImageUtil.adaptarImagen(lblLeyendaLibreImagen,
							"/modulo/img/silla.png");
				}
			});
		}
		return lblLeyendaLibreImagen;
	}

	private JLabel getLblLeyendaOcupado() {
		if (lblLeyendaOcupado == null) {
			lblLeyendaOcupado = new JLabel("Leyenda Ocupado");
			lblLeyendaOcupado.setVerticalAlignment(SwingConstants.TOP);
			lblLeyendaOcupado.setHorizontalAlignment(SwingConstants.TRAILING);
		}
		return lblLeyendaOcupado;
	}

	private JLabel getLblLeyendaOcupadoImagen() {
		if (lblLeyendaOcupadoImagen == null) {
			lblLeyendaOcupadoImagen = new JLabel("");
			lblLeyendaOcupadoImagen
					.addComponentListener(new ComponentAdapter() {
						@Override
						public void componentResized(ComponentEvent e) {
							ImageUtil.adaptarImagen(lblLeyendaOcupadoImagen,
									"/modulo/img/sillaO.png");
						}
					});
		}
		return lblLeyendaOcupadoImagen;
	}

	private JLabel getLblLeyendaNormal() {
		if (lblLeyendaNormal == null) {
			lblLeyendaNormal = new JLabel("Leyenda Normal");
			lblLeyendaNormal.setVerticalAlignment(SwingConstants.TOP);
			lblLeyendaNormal.setHorizontalAlignment(SwingConstants.TRAILING);
		}
		return lblLeyendaNormal;
	}

	private JLabel getLblLeyendaNormalImagen() {
		if (lblLeyendaNormalImagen == null) {
			lblLeyendaNormalImagen = new JLabel("");
			lblLeyendaNormalImagen.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					chckbxCumple.setSelected(false);
					chckbxJubilado.setSelected(false);
				}
			});
			lblLeyendaNormalImagen.addComponentListener(new ComponentAdapter() {
				@Override
				public void componentResized(ComponentEvent e) {
					ImageUtil.adaptarImagen(lblLeyendaNormalImagen,
							"/modulo/img/sillaEN.png");
				}
			});
		}
		return lblLeyendaNormalImagen;
	}

	private JLabel getLblLeyendaJubilado() {
		if (lblLeyendaJubilado == null) {
			lblLeyendaJubilado = new JLabel("Leyenda Jubilado");
			lblLeyendaJubilado.setVerticalAlignment(SwingConstants.TOP);
			lblLeyendaJubilado.setHorizontalAlignment(SwingConstants.TRAILING);
		}
		return lblLeyendaJubilado;
	}

	private JLabel getLblLeyendaJubiladoImagen() {
		if (lblLeyendaJubiladoImagen == null) {
			lblLeyendaJubiladoImagen = new JLabel("");
			lblLeyendaJubiladoImagen.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					chckbxCumple.setSelected(false);
					chckbxJubilado.setSelected(true);
				}
			});
			lblLeyendaJubiladoImagen
					.addComponentListener(new ComponentAdapter() {
						@Override
						public void componentResized(ComponentEvent e) {
							ImageUtil.adaptarImagen(lblLeyendaJubiladoImagen,
									"/modulo/img/sillaEJ.png");
						}
					});
		}
		return lblLeyendaJubiladoImagen;
	}

	private JLabel getLblLeyendaCumple() {
		if (lblLeyendaCumple == null) {
			lblLeyendaCumple = new JLabel("Leyenda Cumple");
			lblLeyendaCumple.setVerticalAlignment(SwingConstants.TOP);
			lblLeyendaCumple.setHorizontalAlignment(SwingConstants.TRAILING);
		}
		return lblLeyendaCumple;
	}

	private JLabel getLblLeyendaCumpleImagen() {
		if (lblLeyendaCumpleImagen == null) {
			lblLeyendaCumpleImagen = new JLabel("");
			lblLeyendaCumpleImagen.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					chckbxCumple.setSelected(true);
					chckbxJubilado.setSelected(false);
				}
			});
			lblLeyendaCumpleImagen.addComponentListener(new ComponentAdapter() {
				@Override
				public void componentResized(ComponentEvent e) {
					ImageUtil.adaptarImagen(lblLeyendaCumpleImagen,
							"/modulo/img/sillaEC.png");
				}
			});
		}
		return lblLeyendaCumpleImagen;
	}

	private JLabel getLblLeyendaJubicumpleImagen() {
		if (lblLeyendaJubicumpleImagen == null) {
			lblLeyendaJubicumpleImagen = new JLabel("");
			lblLeyendaJubicumpleImagen.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					chckbxCumple.setSelected(true);
					chckbxJubilado.setSelected(true);
				}
			});
			lblLeyendaJubicumpleImagen
					.addComponentListener(new ComponentAdapter() {
						@Override
						public void componentResized(ComponentEvent e) {
							ImageUtil.adaptarImagen(lblLeyendaJubicumpleImagen,
									"/modulo/img/sillaEJC.png");
						}
					});
		}
		return lblLeyendaJubicumpleImagen;
	}

	private JPanel getPanelSalaTipoEntradaCheck() {
		if (panelSalaTipoEntradaCheck == null) {
			panelSalaTipoEntradaCheck = new JPanel();
			panelSalaTipoEntradaCheck.setBackground(DEFAULT_COLOR);
			panelSalaTipoEntradaCheck.add(getChckbxJubilado());
			panelSalaTipoEntradaCheck.add(getChckbxCumple());
		}
		return panelSalaTipoEntradaCheck;
	}

	private JCheckBox getChckbxJubilado() {
		if (chckbxJubilado == null) {
			chckbxJubilado = new JCheckBox("Jubilado");
		}
		return chckbxJubilado;
	}

	private JCheckBox getChckbxCumple() {
		if (chckbxCumple == null) {
			chckbxCumple = new JCheckBox("Cumple");
		}
		return chckbxCumple;
	}

	private JScrollPane getScrollPaneTipoEntrada() {
		if (scrollPaneTipoEntrada == null) {
			scrollPaneTipoEntrada = new JScrollPane();
			scrollPaneTipoEntrada.setBackground(DEFAULT_COLOR);
			scrollPaneTipoEntrada.setViewportView(getTxtrTipoEntrada());
		}
		return scrollPaneTipoEntrada;
	}

	private JTextArea getTxtrTipoEntrada() {
		if (txtrTipoEntrada == null) {
			txtrTipoEntrada = new JTextArea();
			txtrTipoEntrada.setWrapStyleWord(true);
			txtrTipoEntrada.setLineWrap(true);
			txtrTipoEntrada.setEditable(false);
			txtrTipoEntrada.setText("Tipo Entrada");
		}
		return txtrTipoEntrada;
	}

	private JPanel getPanelSalaPedidoTotal() {
		if (panelSalaPedidoTotal == null) {
			panelSalaPedidoTotal = new JPanel();
			panelSalaPedidoTotal.setBackground(DEFAULT_COLOR);
			panelSalaPedidoTotal.add(getLblEntradasTotales());
			panelSalaPedidoTotal.add(getTxtEntradasTotales());
		}
		return panelSalaPedidoTotal;
	}

	private JScrollPane getScrollPanelSalaPedido() {
		if (scrollPanelSalaPedido == null) {
			scrollPanelSalaPedido = new JScrollPane();
			scrollPanelSalaPedido.setBackground(DEFAULT_COLOR);
			scrollPanelSalaPedido.setViewportView(getTxtrSalaPedido());
		}
		return scrollPanelSalaPedido;
	}

	private JTextArea getTxtrSalaPedido() {
		if (txtrSalaPedido == null) {
			txtrSalaPedido = new JTextArea();
			txtrSalaPedido.setLineWrap(true);
			txtrSalaPedido.setEditable(false);
			txtrSalaPedido.setWrapStyleWord(true);
			txtrSalaPedido.setText("Sala Pedido");
		}
		return txtrSalaPedido;
	}

	private JLabel getLblEntradasTotales() {
		if (lblEntradasTotales == null) {
			lblEntradasTotales = new JLabel("Entradas Totales");
		}
		return lblEntradasTotales;
	}

	private JTextField getTxtEntradasTotales() {
		if (txtEntradasTotales == null) {
			txtEntradasTotales = new JTextField();
			txtEntradasTotales.setEditable(false);
			txtEntradasTotales.setText("Entradas totales");
			txtEntradasTotales.setColumns(10);
		}
		return txtEntradasTotales;
	}

	private void prepararPanelSala() {
		lblSalaTitulo.setText(gestor.getPeliculaActual().getTitulo());
		lblSalaSesion.setText(gestor.getSalaActual().getFecha() + " - "
				+ gestor.getSalaActual().getHora());
		ImageUtil.adaptarImagen(lblLeyendaLibreImagen, "/modulo/img/silla.png");
		ImageUtil.adaptarImagen(lblLeyendaOcupadoImagen,
				"/modulo/img/sillaO.png");
		ImageUtil.adaptarImagen(lblLeyendaNormalImagen,
				"/modulo/img/sillaEN.png");
		ImageUtil.adaptarImagen(lblLeyendaJubiladoImagen,
				"/modulo/img/sillaEJ.png");
		ImageUtil.adaptarImagen(lblLeyendaCumpleImagen,
				"/modulo/img/sillaEC.png");
		ImageUtil.adaptarImagen(lblLeyendaJubicumpleImagen,
				"/modulo/img/sillaEJC.png");
		colocarButacas();
		panelSalaCentro.setBorder(ComponentsUtil.getBorder(gestor
				.getSalaActual().getNombreSala()));
		txtEntradasTotales.setText(gestor.getEntradasPeliculaActual() + " "
				+ traduccion.getString("entradas"));
		txtrSalaPedido.setText(gestor.printPedidoPeliculaActual(traduccion));
	}

	private JTextArea getTxtrLeyendaJubiCumple() {
		if (txtrLeyendaJubiCumple == null) {
			txtrLeyendaJubiCumple = new JTextArea();
			txtrLeyendaJubiCumple.setEditable(false);
			txtrLeyendaJubiCumple.setOpaque(false);
			txtrLeyendaJubiCumple.setLineWrap(true);
			txtrLeyendaJubiCumple.setWrapStyleWord(true);
			txtrLeyendaJubiCumple.setText("");
		}
		return txtrLeyendaJubiCumple;
	}

	private JPanel getPanelSalaCentro() {
		if (panelSalaCentro == null) {
			panelSalaCentro = new JPanel();
			panelSalaCentro.setBackground(DEFAULT_COLOR);
			panelSalaCentro.setLayout(new GridLayout(4, 11, 0, 0));
			panelSalaCentro.add(getBtnButaca());
			panelSalaCentro.add(getBtnButaca_1());
			panelSalaCentro.add(getBtnButaca_2());
			panelSalaCentro.add(getBtnButaca_3());
			panelSalaCentro.add(getBtnButaca_4());
			panelSalaCentro.add(getHorizontalGluePasillo3());
			panelSalaCentro.add(getBtnButaca_5());
			panelSalaCentro.add(getBtnButaca_6());
			panelSalaCentro.add(getBtnButaca_7());
			panelSalaCentro.add(getBtnButaca_8());
			panelSalaCentro.add(getBtnButaca_9());
			panelSalaCentro.add(getHorizontalGlueHueco2I());
			panelSalaCentro.add(getBtnButaca_10());
			panelSalaCentro.add(getBtnButaca_11());
			panelSalaCentro.add(getBtnButuca());
			panelSalaCentro.add(getBtnButaca_12());
			panelSalaCentro.add(getHorizontalGluePasillo2());
			panelSalaCentro.add(getBtnButaca_13());
			panelSalaCentro.add(getBtnButaca_14());
			panelSalaCentro.add(getBtnButaca_15());
			panelSalaCentro.add(getBtnButaca_16());
			panelSalaCentro.add(getHorizontalGlueHueco2D());
			panelSalaCentro.add(getHorizontalGlueHueco1I());
			panelSalaCentro.add(getBtnButaca_18());
			panelSalaCentro.add(getBtnButaca_19());
			panelSalaCentro.add(getBtnButaca_20());
			panelSalaCentro.add(getBtnButaca_21());
			panelSalaCentro.add(getHorizontalGluePasillo1());
			panelSalaCentro.add(getBtnButaca_22());
			panelSalaCentro.add(getBtnButaca_23());
			panelSalaCentro.add(getBtnButaca_24());
			panelSalaCentro.add(getBtnButaca_25());
			panelSalaCentro.add(getHorizontalGlueHueco1D());
			panelSalaCentro.add(getHorizontalGlueHueco0I2());
			panelSalaCentro.add(getHorizontalGlueHueco0I1());
			panelSalaCentro.add(getHorizontalGlueHueco0I0());
			panelSalaCentro.add(getBtnButaca_17());
			panelSalaCentro.add(getBtnButaca_26());
			panelSalaCentro.add(getHorizontalGluePasillo0());
			panelSalaCentro.add(getBtnButaca_27());
			panelSalaCentro.add(getBtnButaca_28());
			panelSalaCentro.add(getHorizontalGlueHueco0D0());
			panelSalaCentro.add(getHorizontalGlueHueco0D1());
			panelSalaCentro.add(getHorizontalGlueHueco0D2());
		}
		return panelSalaCentro;
	}

	private JButton getBtnButaca() {
		if (btnButaca == null) {
			btnButaca = new JButton("butaca,3,0");
		}
		return btnButaca;
	}

	private JButton getBtnButaca_1() {
		if (btnButaca_1 == null) {
			btnButaca_1 = new JButton("butaca,3,1");
		}
		return btnButaca_1;
	}

	private JButton getBtnButaca_2() {
		if (btnButaca_2 == null) {
			btnButaca_2 = new JButton("butaca,3,2");
		}
		return btnButaca_2;
	}

	private JButton getBtnButaca_3() {
		if (btnButaca_3 == null) {
			btnButaca_3 = new JButton("butaca,3,3");
		}
		return btnButaca_3;
	}

	private JButton getBtnButaca_4() {
		if (btnButaca_4 == null) {
			btnButaca_4 = new JButton("butaca,3,4");
		}
		return btnButaca_4;
	}

	private Component getHorizontalGluePasillo3() {
		if (horizontalGluePasillo3 == null) {
			horizontalGluePasillo3 = Box.createHorizontalGlue();
		}
		return horizontalGluePasillo3;
	}

	private JButton getBtnButaca_5() {
		if (btnButaca_5 == null) {
			btnButaca_5 = new JButton("butaca,3,5");
		}
		return btnButaca_5;
	}

	private JButton getBtnButaca_6() {
		if (btnButaca_6 == null) {
			btnButaca_6 = new JButton("butaca,3,6");
		}
		return btnButaca_6;
	}

	private JButton getBtnButaca_7() {
		if (btnButaca_7 == null) {
			btnButaca_7 = new JButton("butaca,3,7");
		}
		return btnButaca_7;
	}

	private JButton getBtnButaca_8() {
		if (btnButaca_8 == null) {
			btnButaca_8 = new JButton("butaca,3,8");
		}
		return btnButaca_8;
	}

	private JButton getBtnButaca_9() {
		if (btnButaca_9 == null) {
			btnButaca_9 = new JButton("butaca,3,9");
		}
		return btnButaca_9;
	}

	private Component getHorizontalGlueHueco2I() {
		if (horizontalGlueHueco2I == null) {
			horizontalGlueHueco2I = Box.createHorizontalGlue();
		}
		return horizontalGlueHueco2I;
	}

	private JButton getBtnButaca_10() {
		if (btnButaca_10 == null) {
			btnButaca_10 = new JButton("butaca,2,1");
		}
		return btnButaca_10;
	}

	private JButton getBtnButaca_11() {
		if (btnButaca_11 == null) {
			btnButaca_11 = new JButton("butaca,2,2");
		}
		return btnButaca_11;
	}

	private JButton getBtnButuca() {
		if (btnButuca == null) {
			btnButuca = new JButton("butuca,2,3");
		}
		return btnButuca;
	}

	private JButton getBtnButaca_12() {
		if (btnButaca_12 == null) {
			btnButaca_12 = new JButton("butaca,2,4");
		}
		return btnButaca_12;
	}

	private Component getHorizontalGluePasillo2() {
		if (horizontalGluePasillo2 == null) {
			horizontalGluePasillo2 = Box.createHorizontalGlue();
		}
		return horizontalGluePasillo2;
	}

	private JButton getBtnButaca_13() {
		if (btnButaca_13 == null) {
			btnButaca_13 = new JButton("butaca,2,5");
		}
		return btnButaca_13;
	}

	private JButton getBtnButaca_14() {
		if (btnButaca_14 == null) {
			btnButaca_14 = new JButton("butaca,2,6");
		}
		return btnButaca_14;
	}

	private JButton getBtnButaca_15() {
		if (btnButaca_15 == null) {
			btnButaca_15 = new JButton("butaca,2,7");
		}
		return btnButaca_15;
	}

	private JButton getBtnButaca_16() {
		if (btnButaca_16 == null) {
			btnButaca_16 = new JButton("butaca,2,8");
		}
		return btnButaca_16;
	}

	private Component getHorizontalGlueHueco2D() {
		if (horizontalGlueHueco2D == null) {
			horizontalGlueHueco2D = Box.createHorizontalGlue();
		}
		return horizontalGlueHueco2D;
	}

	private Component getHorizontalGlueHueco1I() {
		if (horizontalGlueHueco1I == null) {
			horizontalGlueHueco1I = Box.createHorizontalGlue();
		}
		return horizontalGlueHueco1I;
	}

	private JButton getBtnButaca_18() {
		if (btnButaca_18 == null) {
			btnButaca_18 = new JButton("butaca,1,1");
		}
		return btnButaca_18;
	}

	private JButton getBtnButaca_19() {
		if (btnButaca_19 == null) {
			btnButaca_19 = new JButton("butaca,1,2");
		}
		return btnButaca_19;
	}

	private JButton getBtnButaca_20() {
		if (btnButaca_20 == null) {
			btnButaca_20 = new JButton("butaca,1,3");
		}
		return btnButaca_20;
	}

	private JButton getBtnButaca_21() {
		if (btnButaca_21 == null) {
			btnButaca_21 = new JButton("butaca,1,4");
		}
		return btnButaca_21;
	}

	private Component getHorizontalGluePasillo1() {
		if (horizontalGluePasillo1 == null) {
			horizontalGluePasillo1 = Box.createHorizontalGlue();
		}
		return horizontalGluePasillo1;
	}

	private JButton getBtnButaca_22() {
		if (btnButaca_22 == null) {
			btnButaca_22 = new JButton("butaca,1,5");
		}
		return btnButaca_22;
	}

	private JButton getBtnButaca_23() {
		if (btnButaca_23 == null) {
			btnButaca_23 = new JButton("butaca,1,6");
		}
		return btnButaca_23;
	}

	private JButton getBtnButaca_24() {
		if (btnButaca_24 == null) {
			btnButaca_24 = new JButton("butaca,1,7");
		}
		return btnButaca_24;
	}

	private JButton getBtnButaca_25() {
		if (btnButaca_25 == null) {
			btnButaca_25 = new JButton("butaca,1,8");
		}
		return btnButaca_25;
	}

	private Component getHorizontalGlueHueco1D() {
		if (horizontalGlueHueco1D == null) {
			horizontalGlueHueco1D = Box.createHorizontalGlue();
		}
		return horizontalGlueHueco1D;
	}

	private Component getHorizontalGlueHueco0I2() {
		if (horizontalGlueHueco0I2 == null) {
			horizontalGlueHueco0I2 = Box.createHorizontalGlue();
		}
		return horizontalGlueHueco0I2;
	}

	private Component getHorizontalGlueHueco0I1() {
		if (horizontalGlueHueco0I1 == null) {
			horizontalGlueHueco0I1 = Box.createHorizontalGlue();
		}
		return horizontalGlueHueco0I1;
	}

	private Component getHorizontalGlueHueco0I0() {
		if (horizontalGlueHueco0I0 == null) {
			horizontalGlueHueco0I0 = Box.createHorizontalGlue();
		}
		return horizontalGlueHueco0I0;
	}

	private JButton getBtnButaca_17() {
		if (btnButaca_17 == null) {
			btnButaca_17 = new JButton("butaca,0,3");
		}
		return btnButaca_17;
	}

	private JButton getBtnButaca_26() {
		if (btnButaca_26 == null) {
			btnButaca_26 = new JButton("butaca,0,4");
		}
		return btnButaca_26;
	}

	private Component getHorizontalGluePasillo0() {
		if (horizontalGluePasillo0 == null) {
			horizontalGluePasillo0 = Box.createHorizontalGlue();
		}
		return horizontalGluePasillo0;
	}

	private JButton getBtnButaca_27() {
		if (btnButaca_27 == null) {
			btnButaca_27 = new JButton("butaca,0,5");
		}
		return btnButaca_27;
	}

	private JButton getBtnButaca_28() {
		if (btnButaca_28 == null) {
			btnButaca_28 = new JButton("butaca,0,6");
		}
		return btnButaca_28;
	}

	private Component getHorizontalGlueHueco0D0() {
		if (horizontalGlueHueco0D0 == null) {
			horizontalGlueHueco0D0 = Box.createHorizontalGlue();
		}
		return horizontalGlueHueco0D0;
	}

	private Component getHorizontalGlueHueco0D1() {
		if (horizontalGlueHueco0D1 == null) {
			horizontalGlueHueco0D1 = Box.createHorizontalGlue();
		}
		return horizontalGlueHueco0D1;
	}

	private Component getHorizontalGlueHueco0D2() {
		if (horizontalGlueHueco0D2 == null) {
			horizontalGlueHueco0D2 = Box.createHorizontalGlue();
		}
		return horizontalGlueHueco0D2;
	}

	private void colocarButacas() {
		for (int i = 0; i < panelSalaCentro.getComponentCount(); i++) {
			if (panelSalaCentro.getComponents()[i] instanceof JButton) {
				JButton boton = (JButton) panelSalaCentro.getComponents()[i];
				if (!boton.getText().isEmpty()) {
					boton.setActionCommand(boton.getText().split(",")[1] + ","
							+ boton.getText().split(",")[2]);
					boton.setText("");
				}
				if (boton.getComponents().length == 0)
					;
				boton.addComponentListener(new ComponentAdapter() {
					@Override
					public void componentResized(ComponentEvent e) {
						obtenerEstadoSala((JButton) e.getSource());
					}
				});
				boton.setBorderPainted(false);
				obtenerEstadoSala(boton);
				if (boton.getActionListeners().length == 0) {
					boton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							int tipo = getTipoEntrada();
							int fila = Integer.parseInt(e.getActionCommand()
									.split(",")[0]);
							int butaca = Integer.parseInt(e.getActionCommand()
									.split(",")[1]);
							boolean result = gestor.getSalaActual().isLibre(
									fila, butaca);
							boolean fd = gestor.isEnPedido(fila, butaca);
							if (result) {
								gestor.comprarEntrada(fila, butaca, tipo);
								cambiarImagenButaca((JButton) e.getSource(),
										tipo);
							} else if (fd) {
								gestor.quitarEntrada(fila, butaca);
								cambiarImagenButaca((JButton) e.getSource(),
										Entrada.LIBRE);
							}
							if (gestor.getEntradasPeliculaActual() == 0) {
								btnSalaSiguiente.setEnabled(false);
							} else {
								btnSalaSiguiente.setEnabled(true);
							}
							txtrSalaPedido.setText(gestor
									.printPedidoPeliculaActual(traduccion));
							txtEntradasTotales.setText(gestor
									.getEntradasPeliculaActual()
									+ " "
									+ traduccion.getString("entradas"));
						}
					});
				}
			}

		}
	}

	private void obtenerEstadoSala(JButton boton) {
		int fila = Integer.parseInt(boton.getActionCommand().split(",")[0]);
		int butaca = Integer.parseInt(boton.getActionCommand().split(",")[1]);
		int estado = gestor.isLibre(fila, butaca);
		cambiarImagenButaca(boton, estado);
	}

	protected void cambiarImagenButaca(JButton boton, int tipo) {
		if (tipo == Entrada.LIBRE) {
			ImageUtil.adaptarImagen(boton, "/modulo/img/silla.png");
		} else if (tipo == Entrada.NORMAL) {
			ImageUtil.adaptarImagen(boton, "/modulo/img/sillaEN.png");
		} else if (tipo == Entrada.JUBILADO) {
			ImageUtil.adaptarImagen(boton, "/modulo/img/sillaEJ.png");
		} else if (tipo == Entrada.JUBILADO_Y_PACK_CUMPLE) {
			ImageUtil.adaptarImagen(boton, "/modulo/img/sillaEJC.png");
		} else if (tipo == Entrada.PACK_CUMPLE) {
			ImageUtil.adaptarImagen(boton, "/modulo/img/sillaEC.png");
		} else {
			ImageUtil.adaptarImagen(boton, "/modulo/img/sillaO.png");
		}
	}

	private int getTipoEntrada() {
		if (chckbxCumple.isSelected() && !chckbxJubilado.isSelected()) {
			chckbxCumple.setSelected(false);
			return Entrada.PACK_CUMPLE;
		} else if (!this.chckbxCumple.isSelected()
				&& chckbxJubilado.isSelected()) {
			chckbxJubilado.setSelected(false);
			return Entrada.JUBILADO;
		} else if (chckbxCumple.isSelected() && chckbxJubilado.isSelected()) {
			chckbxCumple.setSelected(false);
			chckbxJubilado.setSelected(false);
			return Entrada.JUBILADO_Y_PACK_CUMPLE;
		} else {
			return Entrada.NORMAL;
		}
	}

	private JPanel getPanelPedido() {
		if (panelPedido == null) {
			panelPedido = new JPanel();
			panelPedido.setBackground(DEFAULT_COLOR);
			panelPedido.setLayout(new BorderLayout(0, 0));
			panelPedido.add(getPanelPanelPedidoCentro(), BorderLayout.CENTER);
			panelPedido.add(getPanelPedidoSur(), BorderLayout.SOUTH);
			panelPedido.add(getPanelPedidoNorte(), BorderLayout.NORTH);
		}
		return panelPedido;
	}

	private JLabel getLblPedidoTitulo() {
		if (lblPedidoTitulo == null) {
			lblPedidoTitulo = new JLabel("Pedido Titulo");
			lblPedidoTitulo.setFont(new Font("Lucida Grande", Font.PLAIN,
					DEFAULT_TITTLE_SIZE));
		}
		return lblPedidoTitulo;
	}

	private JPanel getPanelPedidoSur() {
		if (panelPedidoSur == null) {
			panelPedidoSur = new JPanel();
			panelPedidoSur.setBackground(DEFAULT_COLOR);
			panelPedidoSur.setLayout(new BorderLayout(0, 0));
			panelPedidoSur.add(getPanelPedidoSurIzquierda(), BorderLayout.WEST);
			panelPedidoSur.add(getPanelPedidoSurCentro());
			panelPedidoSur.add(getPanelPedidoSurDerecha(), BorderLayout.EAST);
		}
		return panelPedidoSur;
	}

	private JPanel getPanelPedidoSurIzquierda() {
		if (panelPedidoSurIzquierda == null) {
			panelPedidoSurIzquierda = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panelPedidoSurIzquierda
					.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			panelPedidoSurIzquierda.setBackground(DEFAULT_COLOR);
			panelPedidoSurIzquierda.add(getBtnPedidoAtras());
		}
		return panelPedidoSurIzquierda;
	}

	private JPanel getPanelPedidoSurCentro() {
		if (panelPedidoSurCentro == null) {
			panelPedidoSurCentro = new JPanel();
			panelPedidoSurCentro.setBackground(DEFAULT_COLOR);
			panelPedidoSurCentro.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			panelPedidoSurCentro.add(getBtnPedidoComprarMas());
		}
		return panelPedidoSurCentro;
	}

	private JPanel getPanelPedidoSurDerecha() {
		if (panelPedidoSurDerecha == null) {
			panelPedidoSurDerecha = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panelPedidoSurDerecha
					.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			panelPedidoSurDerecha.setBackground(DEFAULT_COLOR);
			panelPedidoSurDerecha.add(getBtnPedidoCancelar());
			panelPedidoSurDerecha.add(getBtnPedidoFinalizar());
		}
		return panelPedidoSurDerecha;
	}

	private JButton getBtnPedidoAtras() {
		if (btnPedidoAtras == null) {
			btnPedidoAtras = new JButton("Pedido Atras");
			btnPedidoAtras.setFont(new Font("Lucida Grande", Font.PLAIN,
					DEFAULT_BUTTON_SIZE));
			btnPedidoAtras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					((CardLayout) contentPane.getLayout()).show(contentPane,
							"sala");
				}
			});
		}
		return btnPedidoAtras;
	}

	private JButton getBtnPedidoComprarMas() {
		if (btnPedidoComprarMas == null) {
			btnPedidoComprarMas = new JButton("Pedido Comprar Mas");
			btnPedidoComprarMas.setFont(new Font("Lucida Grande", Font.PLAIN,
					DEFAULT_BUTTON_SIZE));
			btnPedidoComprarMas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					volverACartelera();
				}
			});
		}
		return btnPedidoComprarMas;
	}

	private JButton getBtnPedidoFinalizar() {
		if (btnPedidoFinalizar == null) {
			btnPedidoFinalizar = new JButton("Pedido Finalizar");
			btnPedidoFinalizar.setFont(new Font("Lucida Grande", Font.PLAIN,
					DEFAULT_BUTTON_SIZE));
			btnPedidoFinalizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String dni = comprobarDNI();
					if (dni != null) {
						gestor.guardarSalas();
						gestor.printPedido(dni);
						for (int i = 0; i < modeloPedido.getRowCount(); i++) {
							modeloPedido.removeRow(i);
						}
						modeloCartelera.setRowCount(0);
						prepararModeloCartelera();
						ajustarTablaCartelera();
						((CardLayout) contentPane.getLayout()).show(
								contentPane, "inicial");
					}
				}
			});
		}
		return btnPedidoFinalizar;
	}

	protected String comprobarDNI() {
		String dni = JOptionPane.showInputDialog(this,
				traduccion.getString("dni"), traduccion.getString("dni"),
				JOptionPane.QUESTION_MESSAGE);
		while (dni == "") {
			dni = JOptionPane.showInputDialog(this,
					traduccion.getString("dni"), traduccion.getString("dni"),
					JOptionPane.QUESTION_MESSAGE);
		}
		return dni;
	}

	private JScrollPane getScrollPanePedidoCentro() {
		if (scrollPanePedidoCentro == null) {
			scrollPanePedidoCentro = new JScrollPane();
			scrollPanePedidoCentro.setBackground(DEFAULT_COLOR);
			scrollPanePedidoCentro.setViewportView(getTablaPedido());
		}
		return scrollPanePedidoCentro;
	}

	private JTable getTablaPedido() {
		if (tablaPedido == null) {
			String[] nombreColumnas = { "N Entradas", "Sesion", "Pelicula",
					"Precio entradas" };
			modeloPedido = new MiModeloTabla(nombreColumnas, 0);
			tablaPedido = new JTable(modeloPedido);
			tablaPedido.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
			ComponentsUtil.changeJTableHeaderSize(tablaPedido);
			tablaPedido.getTableHeader().setReorderingAllowed(false);
			tablaPedido.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		return tablaPedido;
	}

	private void prepararModeloPedido() {
		DecimalFormat formato = new DecimalFormat("###.##");
		Object[] nuevaFila = new Object[4];
		if (isEnModeloPedido() == -1) {
			nuevaFila[0] = String.valueOf(gestor.getEntradasPeliculaActual());
			nuevaFila[1] = gestor.getSalaActual().getFecha() + " - "
					+ gestor.getSalaActual().getHora();
			nuevaFila[2] = gestor.getPeliculaActual().toString();
			nuevaFila[3] = formato.format(gestor.getPrecioEntradasPeliculaActual()) + "€";
			modeloPedido.addRow(nuevaFila);
		} else {
			modeloPedido.removeRow(isEnModeloPedido());
			nuevaFila[0] = String.valueOf(gestor.getEntradasPeliculaActual());
			nuevaFila[1] = gestor.getSalaActual().getFecha() + " - "
					+ gestor.getSalaActual().getHora();
			nuevaFila[2] = gestor.getPeliculaActual().toString();
			nuevaFila[3] = formato.format(gestor.getPrecioEntradasPeliculaActual()) + "€";
			modeloPedido.addRow(nuevaFila);
		}
	}

	private int isEnModeloPedido() {
		for (int i = 0; i < modeloPedido.getRowCount(); i++) {
			if (modeloPedido.getValueAt(i, 1).equals(
					gestor.getSalaActual().getFecha() + " - "
							+ gestor.getSalaActual().getHora())
					&& modeloPedido.getValueAt(i, 2).equals(
							gestor.getPeliculaActual().toString())) {
				return i;
			}
		}
		return -1;
	}

	private JPanel getPanelPanelPedidoCentro() {
		if (panelPanelPedidoCentro == null) {
			panelPanelPedidoCentro = new JPanel();
			panelPanelPedidoCentro.setBackground(DEFAULT_COLOR);
			panelPanelPedidoCentro.setLayout(new BorderLayout(0, 0));
			panelPanelPedidoCentro.add(getScrollPanePedidoCentro());
			panelPanelPedidoCentro.add(getPanelPanelPedidoCentroSur(),
					BorderLayout.SOUTH);
		}
		return panelPanelPedidoCentro;
	}

	private JPanel getPanelPanelPedidoCentroSur() {
		if (panelPanelPedidoCentroSur == null) {
			panelPanelPedidoCentroSur = new JPanel();
			panelPanelPedidoCentroSur.setBackground(DEFAULT_COLOR);
			panelPanelPedidoCentroSur.add(getLblPedidoPrecioTotal());
			panelPanelPedidoCentroSur.add(getTxtPedidoPrecioTotal());
		}
		return panelPanelPedidoCentroSur;
	}

	private JLabel getLblPedidoPrecioTotal() {
		if (lblPedidoPrecioTotal == null) {
			lblPedidoPrecioTotal = new JLabel("Pedido Precio Total");
			lblPedidoPrecioTotal.setFont(new Font("Lucida Grande", Font.PLAIN,
					DEFAULT_BORDER_TITTLE_SIZE));
		}
		return lblPedidoPrecioTotal;
	}

	private JTextField getTxtPedidoPrecioTotal() {
		if (txtPedidoPrecioTotal == null) {
			txtPedidoPrecioTotal = new JTextField();
			txtPedidoPrecioTotal.setFont(new Font("Lucida Grande", Font.PLAIN,
					DEFAULT_BORDER_TITTLE_SIZE));
			txtPedidoPrecioTotal.setEditable(false);
			txtPedidoPrecioTotal.setColumns(10);
		}
		return txtPedidoPrecioTotal;
	}

	private JPanel getPanelInicialCentro() {
		if (panelInicialCentro == null) {
			panelInicialCentro = new JPanel();
			panelInicialCentro.setBackground(DEFAULT_COLOR);
			panelInicialCentro.setLayout(new BorderLayout(0, 0));
			panelInicialCentro.add(getLblLogoCine());
		}
		return panelInicialCentro;
	}

	private JLabel getLblLogoCine() {
		if (lblLogoCine == null) {
			lblLogoCine = new JLabel("");
			lblLogoCine.addComponentListener(new ComponentAdapter() {
				@Override
				public void componentResized(ComponentEvent e) {
					ImageUtil.adaptarImagen(lblLogoCine, "/modulo/img/cine.png");
				}
			});
			lblLogoCine.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblLogoCine;
	}
	private JLabel getLblBienvenida() {
		if (lblBienvenida == null) {
			lblBienvenida = new JLabel("Bienvenida");
			lblBienvenida.setHorizontalAlignment(SwingConstants.CENTER);
			lblBienvenida.setFont(new Font("Lucida Grande", Font.PLAIN, DEFAULT_TITTLE_SIZE));
		}
		return lblBienvenida;
	}
	private JPanel getPanelCarteleraNorte() {
		if (panelCarteleraNorte == null) {
			panelCarteleraNorte = new JPanel();
			panelCarteleraNorte.setBackground(DEFAULT_COLOR);
			panelCarteleraNorte.setLayout(new BorderLayout(0, 0));
			panelCarteleraNorte.add(getPanelCarteleraNorteIzquierda());
			panelCarteleraNorte.add(getPanelCarteleraNorteDerecha(), BorderLayout.EAST);
		}
		return panelCarteleraNorte;
	}
	private JPanel getPanelCarteleraNorteIzquierda() {
		if (panelCarteleraNorteIzquierda == null) {
			panelCarteleraNorteIzquierda = new JPanel();
			panelCarteleraNorteIzquierda.setBackground(DEFAULT_COLOR);
			panelCarteleraNorteIzquierda.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			panelCarteleraNorteIzquierda.add(getLblCarteleraTitutlo());
		}
		return panelCarteleraNorteIzquierda;
	}
	private JPanel getPanelCarteleraNorteDerecha() {
		if (panelCarteleraNorteDerecha == null) {
			panelCarteleraNorteDerecha = new JPanel();
			panelCarteleraNorteDerecha.setBackground(DEFAULT_COLOR);
			panelCarteleraNorteDerecha.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
			panelCarteleraNorteDerecha.add(getBtnCarteleraAyuda());
		}
		return panelCarteleraNorteDerecha;
	}
	private JButton getBtnCarteleraAyuda() {
		if (btnCarteleraAyuda == null) {
			btnCarteleraAyuda = new JButton("");
			btnCarteleraAyuda.setBorderPainted(false);
			btnCarteleraAyuda.setIcon(ImageUtil.redimensionarImagen("/modulo/img/help.png", DEFAULT_TITTLE_SIZE, DEFAULT_TITTLE_SIZE));
		}
		return btnCarteleraAyuda;
	}
	private JPanel getPanelPeliculaNorte() {
		if (panelPeliculaNorte == null) {
			panelPeliculaNorte = new JPanel();
			panelPeliculaNorte.setBackground(DEFAULT_COLOR);
			panelPeliculaNorte.setLayout(new BorderLayout(0, 0));
			panelPeliculaNorte.add(getPanelPeliculaNorteIzquierda(), BorderLayout.CENTER);
			panelPeliculaNorte.add(getPanelPeliculaNorteDerecha(), BorderLayout.EAST);
		}
		return panelPeliculaNorte;
	}
	private JPanel getPanelPeliculaNorteIzquierda() {
		if (panelPeliculaNorteIzquierda == null) {
			panelPeliculaNorteIzquierda = new JPanel();
			panelPeliculaNorteIzquierda.setBackground(DEFAULT_COLOR);
			panelPeliculaNorteIzquierda.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			panelPeliculaNorteIzquierda.add(getLblPeliculaTitulo());
		}
		return panelPeliculaNorteIzquierda;
	}
	private JPanel getPanelPeliculaNorteDerecha() {
		if (panelPeliculaNorteDerecha == null) {
			panelPeliculaNorteDerecha = new JPanel();
			panelPeliculaNorteDerecha.setBackground(DEFAULT_COLOR);
			panelPeliculaNorteDerecha.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
			panelPeliculaNorteDerecha.add(getBtnPeliculaAyuda());
		}
		return panelPeliculaNorteDerecha;
	}
	private JButton getBtnPeliculaAyuda() {
		if (btnPeliculaAyuda == null) {
			btnPeliculaAyuda = new JButton("");
			btnPeliculaAyuda.setBorderPainted(false);
			btnPeliculaAyuda.setIcon(ImageUtil.redimensionarImagen("/modulo/img/help.png", DEFAULT_TITTLE_SIZE, DEFAULT_TITTLE_SIZE));

		}
		return btnPeliculaAyuda;
	}
	private JPanel getPanelHorarioNorte() {
		if (panelHorarioNorte == null) {
			panelHorarioNorte = new JPanel();
			panelHorarioNorte.setBackground(DEFAULT_COLOR);
			panelHorarioNorte.setLayout(new BorderLayout(0, 0));
			panelHorarioNorte.add(getPanelHorarioNorteIzquierda(), BorderLayout.CENTER);
			panelHorarioNorte.add(getPanelHorarioNorteDerecha(), BorderLayout.EAST);
		}
		return panelHorarioNorte;
	}
	private JPanel getPanelHorarioNorteIzquierda() {
		if (panelHorarioNorteIzquierda == null) {
			panelHorarioNorteIzquierda = new JPanel();
			panelHorarioNorteIzquierda.setBackground(DEFAULT_COLOR);
			panelHorarioNorteIzquierda.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			panelHorarioNorteIzquierda.add(getLblHorarioTitulo());
		}
		return panelHorarioNorteIzquierda;
	}
	private JPanel getPanelHorarioNorteDerecha() {
		if (panelHorarioNorteDerecha == null) {
			panelHorarioNorteDerecha = new JPanel();
			panelHorarioNorteDerecha.setBackground(DEFAULT_COLOR);
			panelHorarioNorteDerecha.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			panelHorarioNorteDerecha.add(getBtnHorarioAyuda());
		}
		return panelHorarioNorteDerecha;
	}
	private JButton getBtnHorarioAyuda() {
		if (btnHorarioAyuda == null) {
			btnHorarioAyuda = new JButton("");
			btnHorarioAyuda.setBorderPainted(false);
			btnHorarioAyuda.setIcon(ImageUtil.redimensionarImagen("/modulo/img/help.png", DEFAULT_TITTLE_SIZE, DEFAULT_TITTLE_SIZE));
		}
		return btnHorarioAyuda;
	}
	private JPanel getPanelSalaNorte() {
		if (panelSalaNorte == null) {
			panelSalaNorte = new JPanel();
			panelSalaNorte.setBackground(DEFAULT_COLOR);
			panelSalaNorte.setLayout(new BorderLayout(0, 0));
			panelSalaNorte.add(getPanelSalaNorteIzquierda(), BorderLayout.CENTER);
			panelSalaNorte.add(getPanelSalaNorteDerecha(), BorderLayout.EAST);
		}
		return panelSalaNorte;
	}
	private JPanel getPanelSalaNorteDerecha() {
		if (panelSalaNorteDerecha == null) {
			panelSalaNorteDerecha = new JPanel();
			panelSalaNorteDerecha.setBackground(DEFAULT_COLOR);
			panelSalaNorteDerecha.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
			panelSalaNorteDerecha.add(getBtnSalaAyuda());
		}
		return panelSalaNorteDerecha;
	}
	private JButton getBtnSalaAyuda() {
		if (btnSalaAyuda == null) {
			btnSalaAyuda = new JButton("");
			btnSalaAyuda.setBorderPainted(false);
			btnSalaAyuda.setIcon(ImageUtil.redimensionarImagen("/modulo/img/help.png", DEFAULT_TITTLE_SIZE, DEFAULT_TITTLE_SIZE));
		}
		return btnSalaAyuda;
	}
	private JPanel getPanelPedidoNorte() {
		if (panelPedidoNorte == null) {
			panelPedidoNorte = new JPanel();
			panelPedidoNorte.setBackground(DEFAULT_COLOR);
			panelPedidoNorte.setLayout(new BorderLayout(0, 0));
			panelPedidoNorte.add(getLblPedidoTitulo(), BorderLayout.CENTER);
			panelPedidoNorte.add(getPanelPedidoNorteDerecha(), BorderLayout.EAST);
		}
		return panelPedidoNorte;
	}
	private JButton getBtnPedidoAyuda() {
		if (btnPedidoAyuda == null) {
			btnPedidoAyuda = new JButton("");
			btnPedidoAyuda.setBorderPainted(false);
			btnPedidoAyuda.setIcon(ImageUtil.redimensionarImagen("/modulo/img/help.png", DEFAULT_TITTLE_SIZE, DEFAULT_TITTLE_SIZE));
		}
		return btnPedidoAyuda;
	}
	private JPanel getPanelPedidoNorteDerecha() {
		if (panelPedidoNorteDerecha == null) {
			panelPedidoNorteDerecha = new JPanel();
			panelPedidoNorteDerecha.setBackground(DEFAULT_COLOR);
			panelPedidoNorteDerecha.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
			panelPedidoNorteDerecha.add(getBtnPedidoAyuda());
		}
		return panelPedidoNorteDerecha;
	}
	private JButton getBtnPedidoCancelar() {
		if (btnPedidoCancelar == null) {
			btnPedidoCancelar = new JButton("");
			btnPedidoCancelar.setFont(new Font("Lucida Grande", Font.PLAIN, DEFAULT_BUTTON_SIZE));
			btnPedidoCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gestor.borrarPedido();
					modeloCartelera.setRowCount(0);
					prepararModeloCartelera();
					ajustarTablaCartelera();
					((CardLayout) contentPane.getLayout()).show(
							contentPane, "inicial");
				}
			});
		}
		return btnPedidoCancelar;
	}
}
