package igu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jvnet.substance.SubstanceLookAndFeel;

import player.MiFile;
import player.MusicPlayer;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private DefaultListModel<MiFile> modeloListaLib;
	private DefaultListModel<MiFile> modeloListaPlay;
	private MusicPlayer mP;
	private JPanel contentPane;
	private JPanel pnNorte;
	private JPanel pnCentro;
	private JLabel lblogo;
	private JSlider sVolumen;
	private JPanel pnVolumen;
	private JLabel lblVol;
	private JTextField txVolumen;
	private JPanel pnLibrary;
	private JPanel pnPlay;
	private JLabel lbLibrary;
	private JLabel lblPlaylist;
	private JPanel pnBtLibrary;
	private JPanel pnBtPlay;
	private JScrollPane scListaLib;
	private JScrollPane scListaPlay;
	private JList<MiFile> listaLib;
	private JList<MiFile> listaPlay;
	private JButton btPasar;
	private JButton btDeleteLib;
	private JButton btAtras;
	private JButton btPlay;
	private JButton btParar;
	private JButton btSiguiente;
	private JButton btDeletePlay;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenu mnPlay;
	private JMenu mnOptions;
	private JMenu mnHelp;
	private JMenuItem mntmOpen;
	private JSeparator separator;
	private JMenuItem mntmExit;
	private JMenuItem mntmAbout;
	private JFileChooser selector;
	private boolean playing = false;
	private JCheckBoxMenuItem chckbxmntmRandom;
	private JMenuItem mntmClearAll;
	private JMenuItem mntmClearLibrary;
	private JMenuItem mntmClearPlaylist;
	private Font fuenteDigital;
	private JCheckBoxMenuItem chckbxmntmOneSongMultiple;
	private JSeparator separator_1;
	private JMenuItem mntmContents;
	
	private void cargarFuente(){
		try{
			InputStream myStream = new BufferedInputStream(new FileInputStream("bin/ttf/DS-DIGIB.ttf"));
			fuenteDigital = Font.createFont(Font.TRUETYPE_FONT, myStream);
		}catch(Exception e){
			System.err.println("No se ha podido cargar la fuente");
			e.printStackTrace();
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame.setDefaultLookAndFeelDecorated(true);
					JDialog.setDefaultLookAndFeelDecorated(true);
					SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.RavenSkin");
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void cargaAyuda(){
		  URL hsURL;
		  HelpSet hs;
		  
		  try {
			  File fichero = new File("help/ayuda.hs");
		   	  hsURL = fichero.toURI().toURL();
		  	  hs = new HelpSet(null, hsURL);
	      }catch (Exception e){
	    	  System.out.println("Ayuda no encontrada");
	    	  return;
		  }

		  HelpBroker hb = hs.createHelpBroker();
		  hb.initPresentation();
		  
		  hb.enableHelpKey(getRootPane(),"introduccion", hs);
		  hb.enableHelpOnButton(mntmContents, "introduccion", hs);
		  hb.enableHelp(listaLib, "anadir", hs);
		  hb.enableHelp(listaPlay, "reproducir", hs);
		  //Meter otro HTML para subir/bajar el volumen con ayuda sensible al contexto
			   
	}

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		mP = new MusicPlayer();
		cargarFuente();
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/LogoTitulo.PNG")));
		setTitle("EII Music Player ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 645, 553);
		setJMenuBar(getMenuBar_1());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPnNorte(), BorderLayout.NORTH);
		contentPane.add(getPanel_1(), BorderLayout.CENTER);
		cargaAyuda();
	}

	private JFileChooser getSelector(){
		if(selector == null){
			selector = new JFileChooser();
			selector.setMultiSelectionEnabled(true);
			selector.setFileFilter(new FileNameExtensionFilter("Archivos .mp3", "mp3"));
			//String directorio = System.getProperty("user.dir"); //El directorio de la aplicacion
			String directorio = System.getProperty("user.home") + "/Desktop";
			selector.setCurrentDirectory(new File(directorio));
		}
		return selector;
	}
	
	private JPanel getPnNorte() {
		if (pnNorte == null) {
			pnNorte = new JPanel();
			pnNorte.setLayout(new GridLayout(0, 3, 0, 0));
			pnNorte.add(getLblogo());
			pnNorte.add(getSVolumen());
			pnNorte.add(getPanel_2());
		}
		return pnNorte;
	}
	private JPanel getPanel_1() {
		if (pnCentro == null) {
			pnCentro = new JPanel();
			pnCentro.setLayout(new GridLayout(0, 2, 5, 0));
			pnCentro.add(getPnLibrary());
			pnCentro.add(getPanel_1_1());
		}
		return pnCentro;
	}
	private JLabel getLblogo() {
		if (lblogo == null) {
			lblogo = new JLabel("");
			lblogo.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/Logo.PNG")));
		}
		return lblogo;
	}
	private JSlider getSVolumen() {
		if (sVolumen == null) {
			sVolumen = new JSlider();
			sVolumen.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent arg0) {
					txVolumen.setText(String.valueOf(sVolumen.getValue()));
					setVolumen(sVolumen.getValue());
				}
			});
			sVolumen.setMinorTickSpacing(10);
			sVolumen.setPaintTicks(true);
			sVolumen.setPaintLabels(true);
			sVolumen.setMajorTickSpacing(20);
			sVolumen.setForeground(Color.RED);
			sVolumen.setFont(new Font("Dialog", Font.BOLD, 14));
		}
		return sVolumen;
	}
	private JPanel getPanel_2() {
		if (pnVolumen == null) {
			pnVolumen = new JPanel();
			pnVolumen.add(getLblVol());
			pnVolumen.add(getTxVolumen());
		}
		return pnVolumen;
	}
	private JLabel getLblVol() {
		if (lblVol == null) {
			lblVol = new JLabel("Vol:");
			lblVol.setForeground(Color.RED);
			lblVol.setFont(new Font("Dialog", Font.BOLD, 26));
		}
		return lblVol;
	}
	private JTextField getTxVolumen() {
		if (txVolumen == null) {
			txVolumen = new JTextField();
			txVolumen.setMinimumSize(new Dimension(90, 56));
			txVolumen.setMaximumSize(new Dimension(90, 56));
			txVolumen.setPreferredSize(new Dimension(90, 56));
			txVolumen.setForeground(Color.RED);
			//txVolumen.setFont(new Font("Dialog", Font.BOLD, 40));
			Font fuente = fuenteDigital.deriveFont(Font.PLAIN, 44);
			txVolumen.setFont(fuente);
			txVolumen.setHorizontalAlignment(SwingConstants.CENTER);
			txVolumen.setText("50");
			txVolumen.setEditable(false);
		}
		return txVolumen;
	}
	private JPanel getPnLibrary() {
		if (pnLibrary == null) {
			pnLibrary = new JPanel();
			pnLibrary.setLayout(new BorderLayout(0, 2));
			pnLibrary.add(getLbLibrary(), BorderLayout.NORTH);
			pnLibrary.add(getScListaLib(), BorderLayout.CENTER);
			pnLibrary.add(getPnBtLibrary(), BorderLayout.SOUTH);
		}
		return pnLibrary;
	}
	private JPanel getPanel_1_1() {
		if (pnPlay == null) {
			pnPlay = new JPanel();
			pnPlay.setLayout(new BorderLayout(0, 2));
			pnPlay.add(getLblPlaylist(), BorderLayout.NORTH);
			pnPlay.add(getScListaPlay(), BorderLayout.CENTER);
			pnPlay.add(getPnBtPlay(), BorderLayout.SOUTH);
		}
		return pnPlay;
	}
	private JLabel getLbLibrary() {
		if (lbLibrary == null) {
			lbLibrary = new JLabel("Library:");
			lbLibrary.setForeground(Color.RED);
			lbLibrary.setFont(new Font("Dialog", Font.BOLD, 16));
		}
		return lbLibrary;
	}
	private JLabel getLblPlaylist() {
		if (lblPlaylist == null) {
			lblPlaylist = new JLabel("PlayList:");
			lblPlaylist.setFont(new Font("Dialog", Font.BOLD, 16));
			lblPlaylist.setForeground(Color.RED);
		}
		return lblPlaylist;
	}
	private JPanel getPnBtLibrary() {
		if (pnBtLibrary == null) {
			pnBtLibrary = new JPanel();
			pnBtLibrary.setLayout(new GridLayout(0, 2, 5, 0));
			pnBtLibrary.add(getBtPasar());
			pnBtLibrary.add(getBtDeleteLib());
		}
		return pnBtLibrary;
	}
	private JPanel getPnBtPlay() {
		if (pnBtPlay == null) {
			pnBtPlay = new JPanel();
			pnBtPlay.setLayout(new GridLayout(0, 5, 5, 0));
			pnBtPlay.add(getBtAtras());
			pnBtPlay.add(getBtPlay());
			pnBtPlay.add(getBtParar());
			pnBtPlay.add(getBtSiguiente());
			pnBtPlay.add(getBtDeletePlay());
		}
		return pnBtPlay;
	}
	private JScrollPane getScListaLib() {
		if (scListaLib == null) {
			scListaLib = new JScrollPane();
			scListaLib.setBorder(new LineBorder(Color.RED, 3));
			scListaLib.setViewportView(getListaLib());
		}
		return scListaLib;
	}
	private JScrollPane getScListaPlay() {
		if (scListaPlay == null) {
			scListaPlay = new JScrollPane();
			scListaPlay.setBorder(new LineBorder(Color.RED, 3));
			scListaPlay.setViewportView(getListaPlay());
		}
		return scListaPlay;
	}
	private JList<MiFile> getListaLib() {
		if (listaLib == null) {
			this.modeloListaLib = new DefaultListModel<MiFile>();
			listaLib = new JList<MiFile>(modeloListaLib);
		}
		return listaLib;
	}
	private JList<MiFile> getListaPlay() {
		if (listaPlay == null) {
			this.modeloListaPlay = new DefaultListModel<MiFile>();
			listaPlay = new JList<MiFile>(modeloListaPlay);
		}
		return listaPlay;
	}
	private JButton getBtPasar() {
		if (btPasar == null) {
			btPasar = new JButton("Add to PlayList");
			btPasar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					for(int i = 0; i < listaLib.getSelectedValuesList().size(); i++){
						if(chckbxmntmOneSongMultiple.isSelected() || !modeloListaPlay.contains(listaLib.getSelectedValuesList().get(i)))
							modeloListaPlay.addElement(listaLib.getSelectedValuesList().get(i));
					}
				}
			});
			btPasar.setMargin(new Insets(0, 0, 0, 0));
			btPasar.setFont(new Font("Dialog", Font.BOLD, 14));
		}
		return btPasar;
	}
	private JButton getBtDeleteLib() {
		if (btDeleteLib == null) {
			btDeleteLib = new JButton("Delete");
			btDeleteLib.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					List<MiFile> canciones = listaLib.getSelectedValuesList();
					for(int i = 0; i <= canciones.size(); i++){
						modeloListaLib.removeElement(canciones.get(i));
					}
				}
			});
			btDeleteLib.setMargin(new Insets(0, 0, 0, 0));
			btDeleteLib.setFont(new Font("Dialog", Font.BOLD, 14));
		}
		return btDeleteLib;
	}
	private JButton getBtAtras() {
		if (btAtras == null) {
			btAtras = new JButton("\u25C4\u25C4");
			btAtras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(listaPlay.getSelectedIndex() == 0){
						listaPlay.setSelectedIndex(modeloListaPlay.getSize() - 1);
					}
					else{
						listaPlay.setSelectedIndex(listaPlay.getSelectedIndex() - 1);
					}
					if(chckbxmntmRandom.isSelected()){
						do{
							listaPlay.setSelectedIndex((int) (Math.random()*modeloListaPlay.getSize()));
						}while(listaPlay.getSelectedValue().equals(mP.getPlaying()));
					}
					if(playing)
						mP.play(listaPlay.getSelectedValue().getF());
					setVolumen(sVolumen.getValue());
				}
			});
			btAtras.setMargin(new Insets(2, 0, 0, 0));
			btAtras.setFont(new Font("Dialog", Font.BOLD, 14));
		}
		return btAtras;
	}
	
	private void setVolumen(double vol){
		double volMax = this.sVolumen.getMaximum();
		mP.setVolume(vol, volMax);
	}
	
	private JButton getBtPlay() {
		if (btPlay == null) {
			btPlay = new JButton("\u25BA");
			btPlay.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(listaPlay.getSelectedIndex() == -1){
						listaPlay.setSelectedIndex(0);
					}
					if(chckbxmntmRandom.isSelected()){
						do{
							listaPlay.setSelectedIndex((int) (Math.random()*modeloListaPlay.getSize()));
						}while(listaPlay.getSelectedValue().equals(mP.getPlaying()));
					}
					mP.play(listaPlay.getSelectedValue().getF());
					playing = true;
					setVolumen(sVolumen.getValue());
				}
			});
			btPlay.setMargin(new Insets(2, 0, 0, 0));
			btPlay.setFont(new Font("Dialog", Font.BOLD, 14));
		}
		return btPlay;
	}
	private JButton getBtParar() {
		if (btParar == null) {
			btParar = new JButton("\u25A0");
			btParar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					mP.stop();
					playing = false;
				}
			});
			btParar.setMargin(new Insets(2, 0, 0, 0));
			btParar.setFont(new Font("Dialog", Font.BOLD, 14));
		}
		return btParar;
	}
	private JButton getBtSiguiente() {
		if (btSiguiente == null) {
			btSiguiente = new JButton("\u25BA\u25BA");
			btSiguiente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(listaPlay.getSelectedIndex() == modeloListaPlay.getSize() - 1){
						listaPlay.setSelectedIndex(0);
					}
					else{
						listaPlay.setSelectedIndex(listaPlay.getSelectedIndex() + 1);
					}
					if(chckbxmntmRandom.isSelected()){
						do{
							listaPlay.setSelectedIndex((int) (Math.random()*modeloListaPlay.getSize()));
						}while(listaPlay.getSelectedValue().equals(mP.getPlaying()));
					}
					if(playing)
						mP.play(listaPlay.getSelectedValue().getF());
					setVolumen(sVolumen.getValue());
				}
			});
			btSiguiente.setMargin(new Insets(2, 0, 0, 0));
			btSiguiente.setFont(new Font("Dialog", Font.BOLD, 14));
		}
		return btSiguiente;
	}
	private JButton getBtDeletePlay() {
		if (btDeletePlay == null) {
			btDeletePlay = new JButton("Del");
			btDeletePlay.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					List<MiFile> canciones = listaPlay.getSelectedValuesList();
					for(int i = 0; i <= canciones.size(); i++){
						if(canciones.get(i).getF().equals(mP.getPlaying())){
							mP.stop();
						}
						modeloListaPlay.removeElement(canciones.get(i));
					}
				}
			});
			btDeletePlay.setMargin(new Insets(2, 0, 0, 0));
			btDeletePlay.setFont(new Font("Dialog", Font.BOLD, 14));
		}
		return btDeletePlay;
	}
	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnFile());
			menuBar.add(getMnPlay());
			menuBar.add(getMnOptions());
			menuBar.add(getMnHelp());
		}
		return menuBar;
	}
	private JMenu getMnFile() {
		if (mnFile == null) {
			mnFile = new JMenu("File");
			mnFile.setMnemonic('f');
			mnFile.add(getMntmOpen());
			mnFile.add(getSeparator());
			mnFile.add(getMntmExit());
		}
		return mnFile;
	}
	private JMenu getMnPlay() {
		if (mnPlay == null) {
			mnPlay = new JMenu("Play");
			mnPlay.setMnemonic('p');
			mnPlay.add(getChckbxmntmRandom());
		}
		return mnPlay;
	}
	private JMenu getMnOptions() {
		if (mnOptions == null) {
			mnOptions = new JMenu("Options");
			mnOptions.setMnemonic('o');
			mnOptions.add(getMntmClearLibrary());
			mnOptions.add(getMntmClearPlaylist());
			mnOptions.add(getMntmClearAll());
			mnOptions.add(getChckbxmntmOneSongMultiple());
		}
		return mnOptions;
	}
	private JMenu getMnHelp() {
		if (mnHelp == null) {
			mnHelp = new JMenu("Help");
			mnHelp.setMnemonic('h');
			mnHelp.add(getMntmContents());
			mnHelp.add(getSeparator_1());
			mnHelp.add(getMntmAbout());
		}
		return mnHelp;
	}
	private JMenuItem getMntmOpen() {
		if (mntmOpen == null) {
			mntmOpen = new JMenuItem("Open");
			mntmOpen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int respuesta = getSelector().showOpenDialog(null);
					if(respuesta == JFileChooser.APPROVE_OPTION){
						for(int i = 0; i < selector.getSelectedFiles().length; i++){
							modeloListaLib.addElement(new MiFile(selector.getSelectedFiles()[i]));
						}
					}
				}
			});
			mntmOpen.setMnemonic('n');
		}
		return mntmOpen;
	}
	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
		}
		return separator;
	}
	private JMenuItem getMntmExit() {
		if (mntmExit == null) {
			mntmExit = new JMenuItem("Exit");
			mntmExit.setMnemonic('e');
		}
		return mntmExit;
	}
	private JMenuItem getMntmAbout() {
		if (mntmAbout == null) {
			mntmAbout = new JMenuItem("About");
			mntmAbout.setMnemonic('b');
		}
		return mntmAbout;
	}
	private JCheckBoxMenuItem getChckbxmntmRandom() {
		if (chckbxmntmRandom == null) {
			chckbxmntmRandom = new JCheckBoxMenuItem("Random");
		}
		return chckbxmntmRandom;
	}
	private JMenuItem getMntmClearAll() {
		if (mntmClearAll == null) {
			mntmClearAll = new JMenuItem("Clear all");
			mntmClearAll.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int size = modeloListaPlay.getSize();
					for(int i = 0; i < size; i++){
						modeloListaPlay.remove(0);
					}
					mP.stop();
					size = modeloListaLib.getSize();
					for(int i = 0; i < size; i++){
						modeloListaLib.remove(0);
					}
				}
			});
		}
		return mntmClearAll;
	}
	private JMenuItem getMntmClearLibrary() {
		if (mntmClearLibrary == null) {
			mntmClearLibrary = new JMenuItem("Clear library");
			mntmClearLibrary.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int size = modeloListaLib.getSize();
					for(int i = 0; i < size; i++){
						modeloListaLib.remove(0);
					}
				}
			});
		}
		return mntmClearLibrary;
	}
	private JMenuItem getMntmClearPlaylist() {
		if (mntmClearPlaylist == null) {
			mntmClearPlaylist = new JMenuItem("Clear playlist");
			mntmClearPlaylist.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int size = modeloListaPlay.getSize();
					for(int i = 0; i < size; i++){
						modeloListaPlay.remove(0);
					}
					mP.stop();
				}
			});
		}
		return mntmClearPlaylist;
	}
	private JCheckBoxMenuItem getChckbxmntmOneSongMultiple() {
		if (chckbxmntmOneSongMultiple == null) {
			chckbxmntmOneSongMultiple = new JCheckBoxMenuItem("One song multiple times in PlayList");
		}
		return chckbxmntmOneSongMultiple;
	}
	private JSeparator getSeparator_1() {
		if (separator_1 == null) {
			separator_1 = new JSeparator();
		}
		return separator_1;
	}
	private JMenuItem getMntmContents() {
		if (mntmContents == null) {
			mntmContents = new JMenuItem("Contents");
			mntmContents.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		}
		return mntmContents;
	}
}
