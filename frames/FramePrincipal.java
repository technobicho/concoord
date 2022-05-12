/*
 * Created on 26-nov-2004
 * Derechos reservados @ 2004 David Acosta
 * <dac77@tutopia.com>
 * Este es un software libre; como tal redistribuirlo y/o modificarlo esta permitido, 
 * siempre y cuando se haga bajo los terminos y condiciones de la Licencia Publica General
 * GNU publicada por la Free Software Foundation, ya sea en su version 2 o cualquier otra
 * de las posteriores a la misma.
 */
package frames;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.net.URL;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import main.ProcesarArchivo;
import main.ProcesarPunto;
import main.ProcesarVectores;
import tools.Clasificacion;
import tools.ConverAngulos;

/**
 * @author   david
 */
public class FramePrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3618134567698117432L;

	private javax.swing.JPanel jContentPane = null;

	private JMenuBar jJMenuBar = null;

	private JMenu jmMenu = null;

	private JMenu jmTransformacion = null;

	private JMenu jmAyuda = null;

	private JMenuItem jmiSalir = null;

	private JCheckBoxMenuItem jcbAfin = null;

	private JMenuItem jmiAyuda = null;

	private JMenuItem jmiAcercaDe = null;

	private JTabbedPane jTabbedPane = null;

	private JPanel jpPunto = null;

	private JPanel jpArchivo = null;

	private JPanel jpDe1 = null;

	private JPanel jpDatum1 = null;

	private JPanel jPanel2 = null;

	private JTextField jtfCampo3 = null;

	private JTextField jtfCampo1 = null;

	private JTextField jtfCampo2 = null;

	private JRadioButton jrbBogota1 = null;

	private JRadioButton jrbMagna1 = null;

	private ButtonGroup bgDatum1 = new ButtonGroup();

	private ButtonGroup bgProyecciones1 = new ButtonGroup();

	private ButtonGroup bgDatum2 = new ButtonGroup();

	private ButtonGroup bgProyecciones2 = new ButtonGroup();

	private ButtonGroup bgDatum3 = new ButtonGroup();

	private ButtonGroup bgProyecciones3 = new ButtonGroup();

	private ButtonGroup bgDatum4 = new ButtonGroup();

	private ButtonGroup bgProyecciones4 = new ButtonGroup();

	private JRadioButton jrbPCartesianas1 = null;

	private JRadioButton jrbGeograficas1 = null;

	private JRadioButton jrbPGauss1 = null;

	private JLabel jlb1 = null;

	private JLabel jlb2 = null;

	private JLabel jlb3 = null;

	private JPanel jPanel = null;

	private JButton jbOkPunto = null;

	private JPanel jPanel1 = null;

	private JPanel jPanel3 = null;

	private JRadioButton jrbBogota2 = null;

	private JRadioButton jrbMagna2 = null;

	private JRadioButton jrbPCartesianas2 = null;

	private JRadioButton jrbGeograficas2 = null;

	private JRadioButton jrbPGauss2 = null;

	private JTextField jtfCampo4 = null;

	private JTextField jtfCampo5 = null;

	private JLabel jlb4 = null;

	private JLabel jlb5 = null;

	private JPanel jPanel4 = null;

	private JPanel jPanel5 = null;

	private JPanel jPanel6 = null;

	private JPanel jPanel7 = null;

	private JPanel jPanel8 = null;

	private JRadioButton jrbBogota3 = null;

	private JRadioButton jrbMagna3 = null;

	private JRadioButton jrbPCartesianas3 = null;

	private JRadioButton jrbGeograficas3 = null;

	private JRadioButton jrbPGauss3 = null;

	private JPanel jPanel9 = null;

	private JButton jbAbrir = null;

	private JTextField jtfAbrir = null;

	private JLabel jlbFormato = null;

	private JComboBox jcbFormato = null;

	private JPanel jPanel10 = null;

	private JPanel jPanel11 = null;

	private JPanel jPanel12 = null;

	private JPanel jPanel13 = null;

	private JRadioButton jrbBogota4 = null;

	private JRadioButton jrbMagna4 = null;

	private JRadioButton jrbPCartesianas4 = null;

	private JRadioButton jrbGeograficas4 = null;

	private JRadioButton jrbPGauss4 = null;

	private JButton jbGuardar = null;

	private JTextField jtfGuardar = null;

	private JButton jbOkArchivo = null;

	private double campo1 = 0 ;
	private double campo2 = 0 ;
	private double campo3 = 0 ;

	private int operacion = 0 ;
	private int tipoDatum = 0 ;

	private boolean /*transAfin,*/ igualSeleccion = false;

	private JMenuItem jmiInfo = null;

	private Informacion info = new Informacion(this, true);

	private File FileIn = null ;
	private File FileOut = null ;

	private JFrame jFrame = null;

	private String directorio = "";
	
	private String helpSetName = null;
	
	//private String helpSetURL = null;
	
	private HelpSet hs;
	
	private HelpBroker hb;

	/**
	 * This is the default constructor
	 */
	public FramePrincipal() {
		super();
		jFrame = this;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/icons/concoordicon.gif")));
		this.setJMenuBar(getJJMenuBar());
		this.setSize(547, 513);
		//		calcula la posciocion de la ventana
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(screenSize.width / 2 - (547 / 2), screenSize.height / 2
				- (537 / 2));
		screenSize = null;
		this.setContentPane(getJContentPane());
		this.setTitle("CONCOORD 1.0");
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				System.exit(0);
			}
		});
	}

	/**
	 * This method initializes jContentPane
	 * @return   javax.swing.JPanel
	 * @uml.property   name="jContentPane"
	 */
	private javax.swing.JPanel getJContentPane() {
		if (jContentPane == null) {
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			jContentPane = new javax.swing.JPanel();
			jContentPane.setLayout(new GridBagLayout());
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.gridy = 0;
			gridBagConstraints1.weightx = 1.0;
			gridBagConstraints1.weighty = 1.0;
			gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
			gridBagConstraints1.ipadx = 467;
			gridBagConstraints1.ipady = 408;
			jContentPane.add(getJTabbedPane(), gridBagConstraints1);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jJMenuBar
	 * @return   javax.swing.JMenuBar
	 * @uml.property   name="jJMenuBar"
	 */
	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.add(getJmMenu());
			jJMenuBar.add(getJmTransformacion());
			jJMenuBar.add(getJmAyuda());
		}
		return jJMenuBar;
	}

	/**
	 * This method initializes jmMenu
	 * @return   javax.swing.JMenu
	 * @uml.property   name="jmMenu"
	 */
	private JMenu getJmMenu() {
		if (jmMenu == null) {
			jmMenu = new JMenu();
			jmMenu.setText("Men\u00fa");
			jmMenu.add(getJmiInfo());
			jmMenu.add(getJmiSalir());
		}
		return jmMenu;
	}

	/**
	 * This method initializes jmTransformacion
	 * @return   javax.swing.JMenu
	 * @uml.property   name="jmTransformacion"
	 */
	private JMenu getJmTransformacion() {
		if (jmTransformacion == null) {
			jmTransformacion = new JMenu();
			jmTransformacion.setText("Transformaci\u00f3n");
			jmTransformacion.add(getJcbAfin());
		}
		return jmTransformacion;
	}

	/**
	 * This method initializes jmAyuda
	 * @return   javax.swing.JMenu
	 * @uml.property   name="jmAyuda"
	 */
	private JMenu getJmAyuda() {
		if (jmAyuda == null) {
			jmAyuda = new JMenu();
			jmAyuda.setText("Ayuda");
			jmAyuda.add(getJmiAyuda());
			jmAyuda.add(getJmiAcercaDe());
		}
		return jmAyuda;
	}

	/**
	 * This method initializes jmiSalir
	 * @return   javax.swing.JMenuItem
	 * @uml.property   name="jmiSalir"
	 */
	private JMenuItem getJmiSalir() {
		if (jmiSalir == null) {
			jmiSalir = new JMenuItem();
			jmiSalir.setText("Salir");
			jmiSalir.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.exit(0);
				}
			});
		}
		return jmiSalir;
	}

	/**
	 * This method initializes jcbAfin
	 * @return   javax.swing.JCheckBoxMenuItem
	 * @uml.property   name="jcbAfin"
	 */
	private JCheckBoxMenuItem getJcbAfin() {
		if (jcbAfin == null) {
			jcbAfin = new JCheckBoxMenuItem();
			jcbAfin.setText("Af\u00edn");
			jcbAfin.setSelected(true);
		}
		return jcbAfin;
	}

	/**
	 * This method initializes jmiAyuda
	 * @return   javax.swing.JMenuItem
	 * @uml.property   name="jmiAyuda"
	 */
	private JMenuItem getJmiAyuda() {
		if (jmiAyuda == null) {
			jmiAyuda = new JMenuItem();
			jmiAyuda.setText("Ayuda");
			jmiAyuda.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					visualizarAyuda();
				}
			});
		}
		return jmiAyuda;
	}

	/**
	 * This method initializes jmiAcercaDe
	 * @return   javax.swing.JMenuItem
	 * @uml.property   name="jmiAcercaDe"
	 */
	private JMenuItem getJmiAcercaDe() {
		if (jmiAcercaDe == null) {
			jmiAcercaDe = new JMenuItem();
			jmiAcercaDe.setText("Acerca De...");
			jmiAcercaDe.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					new AcercaDe(jFrame,true).setVisible(true);
				}
			});
		}
		return jmiAcercaDe;
	}

	/**
	 * This method initializes jTabbedPane
	 * @return   javax.swing.JTabbedPane
	 * @uml.property   name="jTabbedPane"
	 */
	private JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();
			jTabbedPane.addTab("Procesar Punto", null, getJpPunto(), null);
			jTabbedPane.addTab("Procesar Archivo", null, getJpArchivo(), null);
			jTabbedPane.addTab("Procesar Velocidades", null,new ProcesarVectores(),null);
			
		}
		return jTabbedPane;
	}

	/**
	 * This method initializes jpPunto
	 * @return   javax.swing.JPanel
	 * @uml.property   name="jpPunto"
	 */
	private JPanel getJpPunto() {
		if (jpPunto == null) {
			jpPunto = new JPanel();
			jpPunto.setLayout(null);
			jpPunto.setName("Punto");
			jpPunto.add(getJpDe1(), null);
			jpPunto.add(getJPanel(), null);
			jpPunto.add(getJbOkPunto(), null);
		}
		return jpPunto;
	}

	/**
	 * This method initializes jpArchivo
	 * @return   javax.swing.JPanel
	 * @uml.property   name="jpArchivo"
	 */
	private JPanel getJpArchivo() {
		if (jpArchivo == null) {
			GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
			jpArchivo = new JPanel();
			jpArchivo.setLayout(new GridBagLayout());
			jpArchivo.setName("Archivo");
			gridBagConstraints12.gridx = 0;
			gridBagConstraints12.gridy = 0;
			gridBagConstraints12.ipadx = 492;
			gridBagConstraints12.ipady = 191;
			gridBagConstraints12.insets = new java.awt.Insets(15, 23, 6, 18);
			gridBagConstraints21.gridx = 0;
			gridBagConstraints21.gridy = 1;
			gridBagConstraints21.ipadx = 486;
			gridBagConstraints21.ipady = 185;
			gridBagConstraints21.insets = new java.awt.Insets(6, 27, 5, 20);
			gridBagConstraints31.gridx = 0;
			gridBagConstraints31.gridy = 2;
			gridBagConstraints31.ipadx = 29;
			gridBagConstraints31.ipady = -2;
			gridBagConstraints31.insets = new java.awt.Insets(6, 223, 19, 231);
			jpArchivo.add(getJPanel6(), gridBagConstraints12);
			jpArchivo.add(getJPanel10(), gridBagConstraints21);
			jpArchivo.add(getJbOkArchivo(), gridBagConstraints31);
		}
		return jpArchivo;
	}

	/**
	 * This method initializes jpDe1
	 * @return   javax.swing.JPanel
	 * @uml.property   name="jpDe1"
	 */
	private JPanel getJpDe1() {
		if (jpDe1 == null) {
			jlb3 = new JLabel();
			jlb2 = new JLabel();
			jlb1 = new JLabel();
			jpDe1 = new JPanel();
			jpDe1.setLayout(null);
			jpDe1.setBorder(javax.swing.BorderFactory.createTitledBorder(
					javax.swing.BorderFactory.createLineBorder(
							java.awt.Color.black, 1), "DE:",
					javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
					javax.swing.border.TitledBorder.DEFAULT_POSITION,
					new java.awt.Font("Dialog", java.awt.Font.BOLD, 14),
					java.awt.Color.black));
			jpDe1.setBounds(30, 21, 490, 166);
			jlb1.setText("Norte");
			jlb2.setText("Este");
			jlb3.setText("Altura");
			jlb3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jlb3.setBounds(12, 94, 72, 20);
			jlb2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jlb2.setBounds(12, 56, 72, 20);
			jlb1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jlb1.setBounds(12, 18, 72, 20);
			jpDe1.add(getJpDatum1(), null);
			jpDe1.add(getJPanel2(), null);
			jpDe1.add(getJPanel4(), null);
		}
		return jpDe1;
	}

	/**
	 * This method initializes jpDatum1
	 * @return   javax.swing.JPanel
	 * @uml.property   name="jpDatum1"
	 */
	private JPanel getJpDatum1() {
		if (jpDatum1 == null) {
			jpDatum1 = new JPanel();
			jpDatum1.setLayout(null);
			jpDatum1.setBorder(javax.swing.BorderFactory.createLineBorder(
					java.awt.Color.black, 1));
			jpDatum1.setBounds(12, 21, 226, 32);
			jpDatum1.add(getJrbBogota1(), null);
			jpDatum1.add(getJrbMagna1(), null);
		}
		return jpDatum1;
	}

	/**
	 * This method initializes jPanel2
	 * @return   javax.swing.JPanel
	 * @uml.property   name="jPanel2"
	 */
	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jPanel2 = new JPanel();
			jPanel2.setLayout(null);
			jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(
					java.awt.Color.black, 1));
			jPanel2.setBounds(12, 61, 226, 94);
			jPanel2.add(getJrbPCartesianas1(), null);
			jPanel2.add(getJrbGeograficas1(), null);
			jPanel2.add(getJrbPGauss1(), null);
		}
		return jPanel2;
	}

	/**
	 * This method initializes jtfCampo3
	 * @return   javax.swing.JTextField
	 * @uml.property   name="jtfCampo3"
	 */
	private JTextField getJtfCampo3() {
		if (jtfCampo3 == null) {
			jtfCampo3 = new JTextField();
			jtfCampo3.setBounds(93, 94, 119, 20);
			jtfCampo3.setToolTipText("Formato: 2550.00");
			jtfCampo3.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
			jtfCampo3.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					jbOkPunto.requestFocus();
				}
			});
			jtfCampo3.addFocusListener(new FocusAdapter() {
				public void focusLost(FocusEvent e) {
					JTextField textField = (JTextField) e.getSource();
					String content = textField.getText();
					if (content.length() != 0) {
						try {
							Float.parseFloat(content);
						} catch (NumberFormatException nfe) {
							getToolkit().beep();
							textField.requestFocus();
						}
					}
				}
			});
		}
		return jtfCampo3;
	}

	/**
	 * This method initializes jtfCampo1
	 * @return   javax.swing.JTextField
	 * @uml.property   name="jtfCampo1"
	 */
	private JTextField getJtfCampo1() {
		if (jtfCampo1 == null) {
			jtfCampo1 = new JTextField();
			jtfCampo1.setBounds(93, 18, 119, 20);
			jtfCampo1.setToolTipText("Formato: 100000.00");
			jtfCampo1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
			jtfCampo1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					jtfCampo2.requestFocus();
					jtfCampo2.setText("");
				}
			});
			jtfCampo1.addFocusListener(new FocusAdapter() {
				public void focusLost(FocusEvent e) {
					JTextField textField = (JTextField) e.getSource();
					String content = textField.getText();
					if (content.length() != 0) {
						try {
							Float.parseFloat(content);
						} catch (NumberFormatException nfe) {
							getToolkit().beep();
							textField.requestFocus();
						}
					}
				}
			});
		}
		return jtfCampo1;
	}

	/**
	 * This method initializes jtfCampo2
	 * @return   javax.swing.JTextField
	 * @uml.property   name="jtfCampo2"
	 */
	private JTextField getJtfCampo2() {
		if (jtfCampo2 == null) {
			jtfCampo2 = new JTextField();
			jtfCampo2.setBounds(93, 56, 119, 20);
			jtfCampo2.setToolTipText("Formato: 100000.00");
			jtfCampo2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
			jtfCampo2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					jtfCampo3.requestFocus();
					jtfCampo3.setText("");
				}
			});
			jtfCampo2.addFocusListener(new FocusAdapter() {
				public void focusLost(FocusEvent e) {
					JTextField textField = (JTextField) e.getSource();
					String content = textField.getText();
					if (content.length() != 0) {
						try {
							Float.parseFloat(content);
						} catch (NumberFormatException nfe) {
							getToolkit().beep();
							textField.requestFocus();
						}
					}
				}
			});
		}
		return jtfCampo2;
	}

	/**
	 * This method initializes jrbBogota1
	 * @return   javax.swing.JRadioButton
	 * @uml.property   name="jrbBogota1"
	 */
	private JRadioButton getJrbBogota1() {
		if (jrbBogota1 == null) {
			jrbBogota1 = new JRadioButton();
			jrbBogota1.setBounds(9, 6, 71, 21);
			jrbBogota1.setText("Bogot\u00e1");
			jrbBogota1.setSelected(true);
			jrbBogota1.setEnabled(true);
			bgDatum1.add(jrbBogota1);
		}
		return jrbBogota1;
	}

	/**
	 * This method initializes jrbMagna1
	 * @return   javax.swing.JRadioButton
	 * @uml.property   name="jrbMagna1"
	 */
	private JRadioButton getJrbMagna1() {
		if (jrbMagna1 == null) {
			jrbMagna1 = new JRadioButton();
			jrbMagna1.setBounds(97, 6, 123, 21);
			jrbMagna1.setText("Magna-Sirgas");
			bgDatum1.add(jrbMagna1);
		}
		return jrbMagna1;
	}

	/**
	 * This method initializes jrbPCartesianas1
	 * @return   javax.swing.JRadioButton
	 * @uml.property   name="jrbPCartesianas1"
	 */
	private JRadioButton getJrbPCartesianas1() {
		if (jrbPCartesianas1 == null) {
			jrbPCartesianas1 = new JRadioButton();
			jrbPCartesianas1.setBounds(14, 7, 196, 21);
			jrbPCartesianas1.setText("Planas Cartesianas");
			jrbPCartesianas1.setSelected(true);
			jrbPCartesianas1
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							jlb1.setText("Norte");
							jlb2.setText("Este");
							jlb3.setText("Altura");
							jtfCampo1.setToolTipText("Formato: 100000.00");
							jtfCampo2.setToolTipText("Formato: 100000.00");
							jtfCampo3.setToolTipText("Formato: 2550.00");
							jtfCampo1.setText("");
							jtfCampo2.setText("");
							jtfCampo3.setText("");
						}
					});
			bgProyecciones1.add(jrbPCartesianas1);
		}
		return jrbPCartesianas1;
	}

	/**
	 * This method initializes jrbGeograficas1
	 * @return   javax.swing.JRadioButton
	 * @uml.property   name="jrbGeograficas1"
	 */
	private JRadioButton getJrbGeograficas1() {
		if (jrbGeograficas1 == null) {
			jrbGeograficas1 = new JRadioButton();
			jrbGeograficas1.setBounds(14, 35, 196, 21);
			jrbGeograficas1.setText("Geogr\u00e1ficas");
			jrbGeograficas1
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							jlb1.setText("Latitud");
							jlb2.setText("Longitud");
							jlb3.setText("Altura");
							jtfCampo1.setToolTipText("Formato: GG.MMSSSS");
							jtfCampo2.setToolTipText("Formato: -GG.MMSSSS");
							jtfCampo3.setToolTipText("Formato: 2550.00");
							jtfCampo1.setText("");
							jtfCampo2.setText("");
							jtfCampo3.setText("");
						}
					});
			bgProyecciones1.add(jrbGeograficas1);
		}
		return jrbGeograficas1;
	}

	/**
	 * This method initializes jrbPGauss1
	 * @return   javax.swing.JRadioButton
	 * @uml.property   name="jrbPGauss1"
	 */
	private JRadioButton getJrbPGauss1() {
		if (jrbPGauss1 == null) {
			jrbPGauss1 = new JRadioButton();
			jrbPGauss1.setBounds(14, 63, 196, 21);
			jrbPGauss1.setText("Planas Gauss-Kr\u00fcger");
			jrbPGauss1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					jlb1.setText("Norte");
					jlb2.setText("Este");
					jlb3.setText("Altura");
					jtfCampo1.setToolTipText("Formato: 1000000.00");
					jtfCampo2.setToolTipText("Formato: 1000000.00");
					jtfCampo3.setToolTipText("Formato: 2550.00");
					jtfCampo1.setText("");
					jtfCampo2.setText("");
					jtfCampo3.setText("");
				}
			});
			bgProyecciones1.add(jrbPGauss1);
		}
		return jrbPGauss1;
	}

	/**
	 * This method initializes jPanel
	 * @return   javax.swing.JPanel
	 * @uml.property   name="jPanel"
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jlb4 = new JLabel();
			jlb5 = new JLabel();
			jPanel.setLayout(null);
			jPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(
					javax.swing.BorderFactory.createLineBorder(
							java.awt.Color.black, 1), "A:",
					javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
					javax.swing.border.TitledBorder.DEFAULT_POSITION,
					new java.awt.Font("Dialog", java.awt.Font.BOLD, 14),
					java.awt.Color.black));
			jPanel.setBounds(30, 211, 490, 166);
			jlb4.setText("Norte");
			jlb4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jlb4.setBounds(9, 32, 74, 20);
			jlb5.setText("Este");
			jlb5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jlb5.setBounds(9, 84, 74, 20);
			jPanel.add(getJPanel3(), null);
			jPanel.add(getJPanel5(), null);
			jPanel.add(getJPanel1(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes jbOkPunto
	 * @return   javax.swing.JButton
	 * @uml.property   name="jbOkPunto"
	 */
	private JButton getJbOkPunto() {
		final JFrame jFrame = this;
		if (jbOkPunto == null) {
			jbOkPunto = new JButton();
			jbOkPunto.setText("OK");
			jbOkPunto.setBounds(224, 409, 81, 20);
			jbOkPunto.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					datosIn();
					datumPunto();
					tipoOperacionPuntos();
					if ((igualSeleccion == false) && (getCampo2() != 0)
							&& (getCampo1() != 0)) {
						ProcesarPunto procPunto = new ProcesarPunto(jFrame,
								getTipoDatum(), getOperacion(), getCampo1(),
								getCampo2(), getCampo3(), getTransAfin());
						escribirCampos(procPunto.getCampo4(), procPunto
								.getCampo5());
						info.setInfo(procPunto.getMensaje());
					} else {
						igualSeleccion = false;
					}
				}
			});
		}
		return jbOkPunto;
	}

	/**
	 * This method initializes jPanel1
	 * @return   javax.swing.JPanel
	 * @uml.property   name="jPanel1"
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setLayout(null);
			jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(
					java.awt.Color.black, 1));
			jPanel1.setBounds(12, 21, 226, 32);
			jPanel1.add(getJrbBogota2(), null);
			jPanel1.add(getJrbMagna2(), null);
		}
		return jPanel1;
	}

	/**
	 * This method initializes jPanel3
	 * @return   javax.swing.JPanel
	 * @uml.property   name="jPanel3"
	 */
	private JPanel getJPanel3() {
		if (jPanel3 == null) {
			jPanel3 = new JPanel();
			jPanel3.setLayout(null);
			jPanel3.setBounds(12, 61, 226, 94);
			jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(
					java.awt.Color.black, 1));
			jPanel3.add(getJrbPCartesianas2(), null);
			jPanel3.add(getJrbGeograficas2(), null);
			jPanel3.add(getJrbPGauss2(), null);
		}
		return jPanel3;
	}

	/**
	 * This method initializes jrbBogota2
	 * @return   javax.swing.JRadioButton
	 * @uml.property   name="jrbBogota2"
	 */
	private JRadioButton getJrbBogota2() {
		if (jrbBogota2 == null) {
			jrbBogota2 = new JRadioButton();
			jrbBogota2.setBounds(9, 6, 75, 21);
			jrbBogota2.setText("Bogot\u00e1");
			bgDatum2.add(jrbBogota2);
		}
		return jrbBogota2;
	}

	/**
	 * This method initializes jrbMagna2
	 * @return   javax.swing.JRadioButton
	 * @uml.property   name="jrbMagna2"
	 */
	private JRadioButton getJrbMagna2() {
		if (jrbMagna2 == null) {
			jrbMagna2 = new JRadioButton();
			jrbMagna2.setBounds(94, 6, 121, 21);
			jrbMagna2.setText("Magna-Sirgas");
			jrbMagna2.setSelected(true);
			bgDatum2.add(jrbMagna2);
		}
		return jrbMagna2;
	}

	/**
	 * This method initializes jrbPCartesianas2
	 * @return   javax.swing.JRadioButton
	 * @uml.property   name="jrbPCartesianas2"
	 */
	private JRadioButton getJrbPCartesianas2() {
		if (jrbPCartesianas2 == null) {
			jrbPCartesianas2 = new JRadioButton();
			jrbPCartesianas2.setBounds(22, 9, 196, 21);
			jrbPCartesianas2.setText("Planas Cartesianas");
			jrbPCartesianas2.setSelected(true);
			jrbPCartesianas2
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							jlb4.setText("Norte");
							jlb5.setText("Este");
							jtfCampo4.setText("");
							jtfCampo5.setText("");
						}
					});
			bgProyecciones2.add(jrbPCartesianas2);
		}
		return jrbPCartesianas2;
	}

	/**
	 * This method initializes jrbGeograficas2
	 * @return   javax.swing.JRadioButton
	 * @uml.property   name="jrbGeograficas2"
	 */
	private JRadioButton getJrbGeograficas2() {
		if (jrbGeograficas2 == null) {
			jrbGeograficas2 = new JRadioButton();
			jrbGeograficas2.setBounds(21, 34, 196, 21);
			jrbGeograficas2.setText("Geogr\u00e1ficas");
			jrbGeograficas2
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							jlb4.setText("Latitud");
							jlb5.setText("Longitud");
							jtfCampo4.setText("");
							jtfCampo5.setText("");
						}
					});
			bgProyecciones2.add(jrbGeograficas2);
		}
		return jrbGeograficas2;
	}

	/**
	 * This method initializes jrbPGauss2
	 * @return   javax.swing.JRadioButton
	 * @uml.property   name="jrbPGauss2"
	 */
	private JRadioButton getJrbPGauss2() {
		if (jrbPGauss2 == null) {
			jrbPGauss2 = new JRadioButton();
			jrbPGauss2.setBounds(21, 59, 196, 21);
			jrbPGauss2.setText("Planas Gauss-Kr\u00fcger");
			jrbPGauss2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					jlb4.setText("Norte");
					jlb5.setText("Este");
					jtfCampo4.setText("");
					jtfCampo5.setText("");
				}
			});
			bgProyecciones2.add(jrbPGauss2);
		}
		return jrbPGauss2;
	}

	/**
	 * This method initializes jtfCampo4
	 * @return   javax.swing.JTextField
	 * @uml.property   name="jtfCampo4"
	 */
	private JTextField getJtfCampo4() {
		if (jtfCampo4 == null) {
			jtfCampo4 = new JTextField();
			jtfCampo4.setBounds(92, 32, 136, 20);
			jtfCampo4.setEnabled(true);
			jtfCampo4.setEditable(false);
			jtfCampo4.setBorder(new javax.swing.border.LineBorder(
					new java.awt.Color(0, 0, 0), 1, true));
			jtfCampo4.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
		}
		return jtfCampo4;
	}

	/**
	 * This method initializes jtfCampo5
	 * @return   javax.swing.JTextField
	 * @uml.property   name="jtfCampo5"
	 */
	private JTextField getJtfCampo5() {
		if (jtfCampo5 == null) {
			jtfCampo5 = new JTextField();
			jtfCampo5.setBounds(92, 84, 136, 20);
			jtfCampo5.setEnabled(true);
			jtfCampo5.setEditable(false);
			jtfCampo5.setBorder(new javax.swing.border.LineBorder(
					new java.awt.Color(0, 0, 0), 1, true));
			jtfCampo5.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
		}
		return jtfCampo5;
	}

	/**
	 * This method initializes jPanel4
	 * @return   javax.swing.JPanel
	 * @uml.property   name="jPanel4"
	 */
	private JPanel getJPanel4() {
		if (jPanel4 == null) {
			jPanel4 = new JPanel();
			jPanel4.setLayout(null);
			jPanel4.setBounds(254, 21, 226, 134);
			jPanel4.add(jlb1, null);
			jPanel4.add(getJtfCampo3(), null);
			jPanel4.add(jlb2, null);
			jPanel4.add(getJtfCampo1(), null);
			jPanel4.add(jlb3, null);
			jPanel4.add(getJtfCampo2(), null);
		}
		return jPanel4;
	}

	/**
	 * This method initializes jPanel5
	 * @return   javax.swing.JPanel
	 * @uml.property   name="jPanel5"
	 */
	private JPanel getJPanel5() {
		if (jPanel5 == null) {
			jPanel5 = new JPanel();
			jPanel5.setLayout(null);
			jPanel5.setBounds(244, 21, 237, 134);
			jPanel5.add(jlb4, null);
			jPanel5.add(getJtfCampo4(), null);
			jPanel5.add(getJtfCampo5(), null);
			jPanel5.add(jlb5, null);
		}
		return jPanel5;
	}

	/**
	 * This method initializes jPanel6
	 * @return   javax.swing.JPanel
	 * @uml.property   name="jPanel6"
	 */
	private JPanel getJPanel6() {
		if (jPanel6 == null) {
			jlbFormato = new JLabel();
			jPanel6 = new JPanel();
			jPanel6.setLayout(null);
			jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(
					javax.swing.BorderFactory.createLineBorder(
							java.awt.Color.black, 1), "DE:",
					javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
					javax.swing.border.TitledBorder.DEFAULT_POSITION,
					new java.awt.Font("Dialog", java.awt.Font.BOLD, 14),
					java.awt.Color.black));
			jlbFormato.setBounds(76, 161, 68, 20);
			jlbFormato.setText("Formato:");
			jlbFormato
					.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jPanel6.add(getJPanel7(), null);
			jPanel6.add(getJPanel8(), null);
			jPanel6.add(getJPanel9(), null);
			jPanel6.add(jlbFormato, null);
			jPanel6.add(getJcbFormato(), null);
		}
		return jPanel6;
	}

	/**
	 * This method initializes jPanel7
	 * @return   javax.swing.JPanel
	 * @uml.property   name="jPanel7"
	 */
	private JPanel getJPanel7() {
		if (jPanel7 == null) {
			jPanel7 = new JPanel();
			jPanel7.setLayout(null);
			jPanel7.setBounds(13, 63, 468, 36);
			jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(
					java.awt.Color.black, 1));
			jPanel7.add(getJrbPCartesianas3(), null);
			jPanel7.add(getJrbGeograficas3(), null);
			jPanel7.add(getJrbPGauss3(), null);
		}
		return jPanel7;
	}

	/**
	 * This method initializes jPanel8
	 * @return   javax.swing.JPanel
	 * @uml.property   name="jPanel8"
	 */
	private JPanel getJPanel8() {
		if (jPanel8 == null) {
			jPanel8 = new JPanel();
			jPanel8.setLayout(null);
			jPanel8.setBounds(13, 20, 468, 36);
			jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(
					java.awt.Color.black, 1));
			jPanel8.add(getJrbBogota3(), null);
			jPanel8.add(getJrbMagna3(), null);
		}
		return jPanel8;
	}

	/**
	 * This method initializes jrbBogota3
	 * @return   javax.swing.JRadioButton
	 * @uml.property   name="jrbBogota3"
	 */
	private JRadioButton getJrbBogota3() {
		if (jrbBogota3 == null) {
			jrbBogota3 = new JRadioButton();
			jrbBogota3.setBounds(82, 8, 71, 21);
			jrbBogota3.setText("Bogot\u00e1");
			jrbBogota3.setSelected(true);
			bgDatum3.add(jrbBogota3);
		}
		return jrbBogota3;
	}

	/**
	 * This method initializes jrbMagna3
	 * @return   javax.swing.JRadioButton
	 * @uml.property   name="jrbMagna3"
	 */
	private JRadioButton getJrbMagna3() {
		if (jrbMagna3 == null) {
			jrbMagna3 = new JRadioButton();
			jrbMagna3.setBounds(235, 8, 149, 21);
			jrbMagna3.setText("Magna-Sirgas");
			bgDatum3.add(jrbMagna3);
		}
		return jrbMagna3;
	}

	/**
	 * This method initializes jrbPCartesianas3
	 * @return   javax.swing.JRadioButton
	 * @uml.property   name="jrbPCartesianas3"
	 */
	private JRadioButton getJrbPCartesianas3() {
		if (jrbPCartesianas3 == null) {
			jrbPCartesianas3 = new JRadioButton();
			jrbPCartesianas3.setBounds(20, 10, 147, 21);
			jrbPCartesianas3.setText("Planas cartesianas");
			jrbPCartesianas3.setSelected(true);
			jrbPCartesianas3.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					jcbFormato
					.setModel(new javax.swing.DefaultComboBoxModel(
							new String[] {
									"Norte,Este",
									"id,Norte,Este",
									"id,Norte,Este,Altura",
									"Este,Norte",
									"id,Este,Norte",
									"id,Este,Norte,Altura" }));
				}
			});
			bgProyecciones3.add(jrbPCartesianas3);
		}
		return jrbPCartesianas3;
	}

	/**
	 * This method initializes jrbGeograficas3
	 * @return   javax.swing.JRadioButton
	 * @uml.property   name="jrbGeograficas3"
	 */
	private JRadioButton getJrbGeograficas3() {
		if (jrbGeograficas3 == null) {
			jrbGeograficas3 = new JRadioButton();
			jrbGeograficas3.setBounds(170, 10, 101, 21);
			jrbGeograficas3.setText("Geogr\u00e1ficas");
			jrbGeograficas3.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					jcbFormato
					.setModel(new javax.swing.DefaultComboBoxModel(
							new String[] {
									"Latitud, Longitud",
									"id,Latitud,Longitud",
									"id,Latitud,Longitud,Altura",
									"Longitud,Latitud",
									"id,Longitud,Latitud",
									"id,Longitud,Latitud,Altura" }));
				}
			});
			bgProyecciones3.add(jrbGeograficas3);
		}
		return jrbGeograficas3;
	}

	/**
	 * This method initializes jrbPGauss3
	 * @return   javax.swing.JRadioButton
	 * @uml.property   name="jrbPGauss3"
	 */
	private JRadioButton getJrbPGauss3() {
		if (jrbPGauss3 == null) {
			jrbPGauss3 = new JRadioButton();
			jrbPGauss3.setBounds(290, 8, 157, 21);
			jrbPGauss3.setText("Planas Gauss-Kr\u00fcger");
			jrbPGauss3.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					jcbFormato
					.setModel(new javax.swing.DefaultComboBoxModel(
							new String[] {
									"Norte,Este",
									"id,Norte,Este",
									"id,Norte,Este,Altura",
									"Este,Norte",
									"id,Este,Norte",
									"id,Este,Norte,Altura" }));
				}
			});
			bgProyecciones3.add(jrbPGauss3);
		}
		return jrbPGauss3;
	}

	/**
	 * This method initializes jPanel9
	 * @return   javax.swing.JPanel
	 * @uml.property   name="jPanel9"
	 */
	private JPanel getJPanel9() {
		if (jPanel9 == null) {
			jPanel9 = new JPanel();
			jPanel9.setLayout(null);
			jPanel9.setBounds(13, 110, 468, 44);
			jPanel9.add(getJbAbrir(), null);
			jPanel9.add(getJtfAbrir(), null);
		}
		return jPanel9;
	}

	/**
	 * This method initializes jbAbrir
	 * @return   javax.swing.JButton
	 * @uml.property   name="jbAbrir"
	 */
	private JButton getJbAbrir() {

		if (jbAbrir == null) {
			jbAbrir = new JButton();
			jbAbrir.setBounds(14, 10, 86, 24);
			jbAbrir.setText("Abrir");
			jbAbrir.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFileChooser jfcAbrirArchivo;

					//creacion del objeto JFileChooser
					if (directorio.equals(""))
						jfcAbrirArchivo = new JFileChooser();
					else
						jfcAbrirArchivo = new JFileChooser(directorio);

					jfcAbrirArchivo
							.addChoosableFileFilter(new FiltroArchivos());

					int returnVal = jfcAbrirArchivo.showOpenDialog(jFrame);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						FileIn = jfcAbrirArchivo.getSelectedFile();
						jtfAbrir.setText(FileIn.getAbsolutePath());
						directorio = jfcAbrirArchivo.getCurrentDirectory()
								.toString();
					}
				}
			});
		}
		return jbAbrir;
	}

	/**
	 * This method initializes jtfAbrir
	 * @return   javax.swing.JTextField
	 * @uml.property   name="jtfAbrir"
	 */
	private JTextField getJtfAbrir() {
		if (jtfAbrir == null) {
			jtfAbrir = new JTextField();
			jtfAbrir.setBounds(110, 10, 338, 24);
			jtfAbrir.setEditable(false);
		}
		return jtfAbrir;
	}

	/**
	 * This method initializes jcbFormato
	 * @return   javax.swing.JComboBox
	 * @uml.property   name="jcbFormato"
	 */
	private JComboBox getJcbFormato() {
		if (jcbFormato == null) {
			jcbFormato = new JComboBox();
			jcbFormato.setBounds(160, 160, 299, 20);
			jcbFormato
					.setModel(new javax.swing.DefaultComboBoxModel(
							new String[] {
									"Norte,Este",
									"id,Norte,Este",
									"id,Norte,Este,Altura",
									"Este,Norte",
									"id,Este,Norte",
									"id,Este,Norte,Altura" }));
		}
		return jcbFormato;
	}

	/**
	 * This method initializes jPanel10
	 * @return   javax.swing.JPanel
	 * @uml.property   name="jPanel10"
	 */
	private JPanel getJPanel10() {
		if (jPanel10 == null) {
			jPanel10 = new JPanel();
			jPanel10.setLayout(null);
			jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(
					javax.swing.BorderFactory.createLineBorder(
							java.awt.Color.black, 1), "A:",
					javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
					javax.swing.border.TitledBorder.DEFAULT_POSITION,
					new java.awt.Font("Dialog", java.awt.Font.BOLD, 14),
					java.awt.Color.black));
			jPanel10.add(getJPanel11(), null);
			jPanel10.add(getJPanel12(), null);
			jPanel10.add(getJPanel13(), null);
		}
		return jPanel10;
	}

	/**
	 * This method initializes jPanel11
	 * @return   javax.swing.JPanel
	 * @uml.property   name="jPanel11"
	 */
	private JPanel getJPanel11() {
		if (jPanel11 == null) {
			jPanel11 = new JPanel();
			jPanel11.setLayout(null);
			jPanel11.setBounds(13, 20, 468, 36);
			jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(
					java.awt.Color.black, 1));
			jPanel11.add(getJrbBogota4(), null);
			jPanel11.add(getJrbMagna4(), null);
		}
		return jPanel11;
	}

	/**
	 * This method initializes jPanel12
	 * @return   javax.swing.JPanel
	 * @uml.property   name="jPanel12"
	 */
	private JPanel getJPanel12() {
		if (jPanel12 == null) {
			jPanel12 = new JPanel();
			jPanel12.setLayout(null);
			jPanel12.setBounds(13, 63, 468, 36);
			jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(
					java.awt.Color.black, 1));
			jPanel12.add(getJrbPCartesianas4(), null);
			jPanel12.add(getJrbGeograficas4(), null);
			jPanel12.add(getJrbPGauss4(), null);
		}
		return jPanel12;
	}

	/**
	 * This method initializes jPanel13
	 * @return   javax.swing.JPanel
	 * @uml.property   name="jPanel13"
	 */
	private JPanel getJPanel13() {
		if (jPanel13 == null) {
			jPanel13 = new JPanel();
			jPanel13.setLayout(null);
			jPanel13.setBounds(13, 110, 468, 44);
			jPanel13.add(getJbGuardar(), null);
			jPanel13.add(getJtfGuardar(), null);
		}
		return jPanel13;
	}

	/**
	 * This method initializes jrbBogota4
	 * @return   javax.swing.JRadioButton
	 * @uml.property   name="jrbBogota4"
	 */
	private JRadioButton getJrbBogota4() {
		if (jrbBogota4 == null) {
			jrbBogota4 = new JRadioButton();
			jrbBogota4.setBounds(82, 9, 73, 21);
			jrbBogota4.setText("Bogot\u00e1");
			bgDatum4.add(jrbBogota4);
		}
		return jrbBogota4;
	}

	/**
	 * This method initializes jrbMagna4
	 * @return   javax.swing.JRadioButton
	 * @uml.property   name="jrbMagna4"
	 */
	private JRadioButton getJrbMagna4() {
		if (jrbMagna4 == null) {
			jrbMagna4 = new JRadioButton();
			jrbMagna4.setBounds(237, 9, 149, 21);
			jrbMagna4.setText("Magna-Sirgas");
			jrbMagna4.setSelected(true);
			bgDatum4.add(jrbMagna4);
		}
		return jrbMagna4;
	}

	/**
	 * This method initializes jrbPCartesianas4
	 * @return   javax.swing.JRadioButton
	 * @uml.property   name="jrbPCartesianas4"
	 */
	private JRadioButton getJrbPCartesianas4() {
		if (jrbPCartesianas4 == null) {
			jrbPCartesianas4 = new JRadioButton();
			jrbPCartesianas4.setBounds(23, 10, 147, 21);
			jrbPCartesianas4.setText("Planas Cartesianas");
			jrbPCartesianas4.setSelected(true);
			bgProyecciones4.add(jrbPCartesianas4);
		}
		return jrbPCartesianas4;
	}

	/**
	 * This method initializes jrbGeograficas4
	 * @return   javax.swing.JRadioButton
	 * @uml.property   name="jrbGeograficas4"
	 */
	private JRadioButton getJrbGeograficas4() {
		if (jrbGeograficas4 == null) {
			jrbGeograficas4 = new JRadioButton();
			jrbGeograficas4.setBounds(170, 10, 101, 21);
			jrbGeograficas4.setText("Geogr\u00e1ficas");
			bgProyecciones4.add(jrbGeograficas4);
		}
		return jrbGeograficas4;
	}

	/**
	 * This method initializes jrbPGauss4
	 * @return   javax.swing.JRadioButton
	 * @uml.property   name="jrbPGauss4"
	 */
	private JRadioButton getJrbPGauss4() {
		if (jrbPGauss4 == null) {
			jrbPGauss4 = new JRadioButton();
			jrbPGauss4.setBounds(290, 10, 157, 21);
			jrbPGauss4.setText("Planas Gauss-Kr\u00fcger");
			bgProyecciones4.add(jrbPGauss4);
		}
		return jrbPGauss4;
	}

	/**
	 * This method initializes jbGuardar
	 * @return   javax.swing.JButton
	 * @uml.property   name="jbGuardar"
	 */
	private JButton getJbGuardar() {
		
		if (jbGuardar == null) {
			jbGuardar = new JButton();
			jbGuardar.setBounds(14, 10, 86, 24);
			jbGuardar.setText("Guardar");
			jbGuardar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFileChooser jfcGuardarArchivo;

					//creacion del objeto JFileChooser
					if (directorio.equals(""))
						jfcGuardarArchivo = new JFileChooser();
					else
						jfcGuardarArchivo = new JFileChooser(directorio);

					jfcGuardarArchivo
							.addChoosableFileFilter(new FiltroArchivos());

					int returnVal = jfcGuardarArchivo.showSaveDialog(jFrame);
					if (returnVal == JFileChooser.APPROVE_OPTION) {						
						FileOut = jfcGuardarArchivo.getSelectedFile();
						//valida la existencia del archivo
						validaExistenciaArchivo(FileOut);						
						jtfGuardar.setText(FileOut.getAbsolutePath());
					}
				}
			});
		}
		return jbGuardar;
	}
	
	/**
	 * Metodo que valida la existencia del archivo de salida
	 * @param fileOut archivo de salida
	 */
	void validaExistenciaArchivo(File fileOut){
//		valida la existencia del archivo
		if (FileOut.exists()){
			int n = JOptionPane.showConfirmDialog(jFrame,
					"El archivo ya existe, desea\n sobreescribirlo?",
					"Advertencia", JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.WARNING_MESSAGE);	
			if (n == JOptionPane.CANCEL_OPTION){
				FileOut = null;
				return;
			}else{
				String path = FileOut.getAbsoluteFile().toString();
				FileOut.delete();
				FileOut = new File(path);
			}
		}
	}

	/**
	 * This method initializes jtfGuardar
	 * @return   javax.swing.JTextField
	 * @uml.property   name="jtfGuardar"
	 */
	private JTextField getJtfGuardar() {
		if (jtfGuardar == null) {
			jtfGuardar = new JTextField();
			jtfGuardar.setBounds(110, 10, 338, 24);
			jtfGuardar.setEditable(false);
		}
		return jtfGuardar;
	}

	/**
	 * This method initializes jbOkArchivo
	 * @return   javax.swing.JButton
	 * @uml.property   name="jbOkArchivo"
	 */
	private JButton getJbOkArchivo() {
		if (jbOkArchivo == null) {
			jbOkArchivo = new JButton();
			jbOkArchivo.setText("OK");
			jbOkArchivo.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (validarArchivos()) {
						tipoOperacionArchivos();
						if (igualSeleccion != true) {
							datumArchivo();
							/*ProcesarArchivo nprocArch = */new ProcesarArchivo(
									jFrame, jcbFormato.getSelectedIndex() + 1,
									getOperacion(), getTipoDatum(),
									getTransAfin(), FileIn, FileOut);
						} else {
							return;
						}

					} else {
						JOptionPane
								.showMessageDialog(
										jFrame,
										"Debe seleccionar un archivo de entrada y otro\n de salida",
										"Error...", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		}
		return jbOkArchivo;
	}

	private boolean validarArchivos() {
		boolean estado = false;
		//valida existencia del archivo de salida
		validaExistenciaArchivo(FileOut);
		if ((FileIn != null) && (FileOut != null)) {
			estado = true;
		}
		return estado;
	}

	/**
	 * Determina el tipo de datos, si es angular o rectangular
	 * 
	 * @return void
	 */
	private void datosIn() {
		ConverAngulos nconvAng = new ConverAngulos();
		String contCampo3 = jtfCampo3.getText();
		if (validaContenido(jtfCampo1.getText()) == true
				&& validaContenido(jtfCampo2.getText()) == true) {

			//valida coordenadas geograficas
			if (jrbGeograficas1.isSelected()) {
				campo1 = nconvAng.gmsToRad(Double.valueOf(jtfCampo1.getText())
						.doubleValue());
				campo2 = nconvAng.gmsToRad(Double.valueOf(jtfCampo2.getText())
						.doubleValue());
				//valida el formato de la latitud
				if(campo1 == 0){
					JOptionPane.showMessageDialog(this,
							"La latitud es incorrecta\n"
									+ " verifique el formato GG.MMSSSSS", "Error...",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				//valida el formato de la longitud
				if(campo2 == 0){
					JOptionPane.showMessageDialog(this,
							"La longitud es incorrecta\n"
									+ " verifique el formato -GG.MMSSSS", "Error...",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				//garantiza el signo del angulo en la longitud
				if (campo2 > 0) {
					campo2 = campo2 * (-1);
				}
				//valida el rango de los angulos
				if (!(validarAngLat(campo1))) {
					JOptionPane.showMessageDialog(this,
							"Ha seleccionado una latitud por fuera del rango\n"
									+ " -5\u00b0 a 14\u00b0", "Error...",
							JOptionPane.ERROR_MESSAGE);
					campo1 = 0;
					return;
				}
				if (!(validarAngLong(campo2))) {
					JOptionPane.showMessageDialog(this,
							"Ha seleccionado una longitud por fuera del rango\n"
									+ " -66\u00b0 a -82\u00b0", "Error...",
							JOptionPane.ERROR_MESSAGE);
					campo2 = 0;
					return;
				}
			}

			//valida coordenadas planas cartesianas
			if (jrbPCartesianas1.isSelected()) {
				campo1 = Double.valueOf(jtfCampo1.getText()).doubleValue();
				campo2 = Double.valueOf(jtfCampo2.getText()).doubleValue();
				//valida el rango de coordenadas
				if (!validarCoorCart(campo1)) {
					JOptionPane
							.showMessageDialog(
									this,
									"Ha seleccionado un valor incorrecto en la coordenada Norte",
									"Error...", JOptionPane.ERROR_MESSAGE);
					campo1 = 0;
					return;
				}
				if (!validarCoorCart(campo2)) {
					JOptionPane
							.showMessageDialog(
									this,
									"Ha seleccionado un valor incorrecto en la coordenada Este",
									"Error...", JOptionPane.ERROR_MESSAGE);
					campo1 = 0;
					return;
				}
			}

			//valida coordenadas planas Gauss-Kr�ger
			if (jrbPGauss1.isSelected()) {
				campo1 = Double.valueOf(jtfCampo1.getText()).doubleValue();
				campo2 = Double.valueOf(jtfCampo2.getText()).doubleValue();
				//valida el rango de coordenadas
				if (!validarCoorGauss(campo1)) {
					JOptionPane
							.showMessageDialog(
									this,
									"Ha seleccionado un valor incorrecto en la coordenada Norte",
									"Error...", JOptionPane.ERROR_MESSAGE);
					campo1 = 0;
					return;
				}
				if (!validarCoorGauss(campo2)) {
					JOptionPane
							.showMessageDialog(
									this,
									"Ha seleccionado un valor incorrecto en la coordenada Este",
									"Error...", JOptionPane.ERROR_MESSAGE);
					campo1 = 0;
					return;
				}
			}

		} else {
			JOptionPane.showMessageDialog(this, "Hay campos vacios\n"
					+ "Por favor complete los datos de entrada", "Error...",
					JOptionPane.ERROR_MESSAGE);
			igualSeleccion = true;
		}

		if (contCampo3.compareToIgnoreCase("") == 0) {
			campo3 = 0;
		} else
			campo3 = Double.valueOf(jtfCampo3.getText()).doubleValue();
	}

	/**
	 * Valida el contenido de la celda
	 * 
	 * @param contenido
	 * @return si el contenido es valido true, si no false
	 */
	private boolean validaContenido(String contenido) {
		boolean valido;
		if (contenido.compareToIgnoreCase("") == 0)
			valido = false;
		else
			valido = true;
		return valido;
	}

	/**
	 * valida el &aacute;ngulo de entrada
	 * 
	 * @param angulo &aacute;ngulo a validar
	 * @return si el angulo es valido true, si no false
	 */
	private boolean validarAngLong(double angulo) {
		boolean valido = false;
		if ((angulo >= -1.4311699866353502) && (angulo <= -1.1519173063162575)) {
			valido = true;
		} else {
			valido = false;
		}
		return valido;
	}

	private boolean validarAngLat(double angulo) {
		boolean valido = false;
		if ((angulo >= -0.08726646259971647) && (angulo <= 0.24434609527920614)) {
			valido = true;
		} else {
			valido = false;
		}
		return valido;
	}

	/**
	 * Valida el valor de coordenadas planas cartesianas
	 * 
	 * @param coordenada
	 *            a validar
	 * @return si las coordenadas son validas true, si no false
	 */
	private boolean validarCoorCart(double coordenada) {
		boolean valido = false;
		if (coordenada > 100 || coordenada < -100)
			valido = true;
		return valido;
	}

	/**
	 * Valida el valor de coordenadas planas Gauss-Kr�ger
	 * 
	 * @param coordenada
	 *            a validar
	 * @return si las coordendas son validas true, si no false
	 */
	private boolean validarCoorGauss(double coordenada) {
		boolean valido = false;
		if (coordenada > 100000)
			valido = true;
		return valido;
	}

	/**
	 * Determina el tipo de datum del origen para los puntos
	 * 
	 * @return void
	 */
	private void datumPunto() {
		if (jrbBogota1.isSelected())
			tipoDatum = Clasificacion.TIPO_DATUM.DATUM_BOGOTA;
		else
			tipoDatum = Clasificacion.TIPO_DATUM.DATUM_MAGNA;
	}

	/**
	 * Determina el tipo de datum del origen para los archivos
	 *  
	 */
	private void datumArchivo() {
		if (jrbBogota3.isSelected())
			tipoDatum = Clasificacion.TIPO_DATUM.DATUM_BOGOTA;
		else
			tipoDatum = Clasificacion.TIPO_DATUM.DATUM_MAGNA;
	}

	/**
	 * Determina el tipo de operacion a realizar
	 * 
	 * @return void
	 */
	private void tipoOperacionPuntos() {
		//conversiones puntos
		if ((jrbBogota1.isSelected() && jrbBogota2.isSelected())
				|| (jrbMagna1.isSelected() && jrbMagna2.isSelected())) {
			if (jrbPCartesianas1.isSelected()) {
				if (jrbPCartesianas2.isSelected()) {
					mensajeSeleccion();
					igualSeleccion = true;

				}
				if (jrbGeograficas2.isSelected()) {
					operacion = Clasificacion.TIPO_CONVERSION.TIPO_CARTESIANAS2GEOGRAFICAS;
				}
				if (jrbPGauss2.isSelected()) {
					operacion = Clasificacion.TIPO_CONVERSION.TIPO_CARTESIANAS2GAUSS;
				}
			}
			if (jrbGeograficas1.isSelected()) {
				if (jrbPCartesianas2.isSelected()) {
					operacion = Clasificacion.TIPO_CONVERSION.TIPO_GEOGRAFICAS2CARTESIANAS;
				}
				if (jrbGeograficas2.isSelected()) {
					mensajeSeleccion();
					igualSeleccion = true;

				}
				if (jrbPGauss2.isSelected()) {
					operacion = Clasificacion.TIPO_CONVERSION.TIPO_GEOGRAFICAS2GAUSS;
				}
			}
			if (jrbPGauss1.isSelected()) {
				if (jrbPCartesianas2.isSelected()) {
					operacion = Clasificacion.TIPO_CONVERSION.TIPO_GAUSS2CARTESIANAS;
				}
				if (jrbGeograficas2.isSelected()) {
					operacion = Clasificacion.TIPO_CONVERSION.TIPO_GAUSS2GEOGRAFICAS;
				}
				if (jrbPGauss2.isSelected()) {
					mensajeSeleccion();
					igualSeleccion = true;

				}
			}
		}

		//trasnformacion puntos
		if ((jrbBogota1.isSelected() && jrbMagna2.isSelected())
				|| (jrbMagna1.isSelected() && jrbBogota2.isSelected())) {
			if (jrbPCartesianas1.isSelected()) {
				if (jrbPCartesianas2.isSelected()) {
					operacion = Clasificacion.TIPO_TRANSFORMACION.TIPO_CARTESIANAS2CARTESIANAS;
				}
				if (jrbGeograficas2.isSelected()) {
					operacion = Clasificacion.TIPO_TRANSFORMACION.TIPO_CARTESIANAS2GEOGRAFICAS;
				}
				if (jrbPGauss2.isSelected()) {
					operacion = Clasificacion.TIPO_TRANSFORMACION.TIPO_CARTESIANAS2GAUSS;
				}
			}
			if (jrbGeograficas1.isSelected()) {
				if (jrbPCartesianas2.isSelected()) {
					operacion = Clasificacion.TIPO_TRANSFORMACION.TIPO_GEOGRAFICAS2CARTESIANAS;
				}
				if (jrbGeograficas2.isSelected()) {
					operacion = Clasificacion.TIPO_TRANSFORMACION.TIPO_GEOGRAFICAS2GEOGRAFICAS;
				}
				if (jrbPGauss2.isSelected()) {
					operacion = Clasificacion.TIPO_TRANSFORMACION.TIPO_GEOGRAFICAS2GAUSS;
				}
			}
			if (jrbPGauss1.isSelected()) {
				if (jrbPCartesianas2.isSelected()) {
					operacion = Clasificacion.TIPO_TRANSFORMACION.TIPO_GAUSS2CARTESIANAS;
				}
				if (jrbGeograficas2.isSelected()) {
					operacion = Clasificacion.TIPO_TRANSFORMACION.TIPO_GAUSS2GEOGRAFICAS;
				}
				if (jrbPGauss2.isSelected()) {
					operacion = Clasificacion.TIPO_TRANSFORMACION.TIPO_GAUSS2GAUSS;
				}
			}
		}

	}

	/**
	 * Metodo que determina el tipo de operaci&oacute;n sobre los archivos
	 *@return void
	 */
	private void tipoOperacionArchivos() {
		//		conversiones archivos
		if ((jrbBogota3.isSelected() && jrbBogota4.isSelected())
				|| (jrbMagna3.isSelected() && jrbMagna4.isSelected())) {
			if (jrbPCartesianas3.isSelected()) {
				if (jrbPCartesianas4.isSelected()) {
					mensajeSeleccion();
					igualSeleccion = true;

				}
				if (jrbGeograficas4.isSelected()) {
					operacion = Clasificacion.TIPO_CONVERSION.TIPO_CARTESIANAS2GEOGRAFICAS;
				}
				if (jrbPGauss4.isSelected()) {
					operacion = Clasificacion.TIPO_CONVERSION.TIPO_CARTESIANAS2GAUSS;
				}
			}
			if (jrbGeograficas3.isSelected()) {
				if (jrbPCartesianas4.isSelected()) {
					operacion = Clasificacion.TIPO_CONVERSION.TIPO_GEOGRAFICAS2CARTESIANAS;
				}
				if (jrbGeograficas4.isSelected()) {
					mensajeSeleccion();
					igualSeleccion = true;

				}
				if (jrbPGauss4.isSelected()) {
					operacion = Clasificacion.TIPO_CONVERSION.TIPO_GEOGRAFICAS2GAUSS;
				}
			}
			if (jrbPGauss3.isSelected()) {
				if (jrbPCartesianas4.isSelected()) {
					operacion = Clasificacion.TIPO_CONVERSION.TIPO_GAUSS2CARTESIANAS;
				}
				if (jrbGeograficas4.isSelected()) {
					operacion = Clasificacion.TIPO_CONVERSION.TIPO_GAUSS2GEOGRAFICAS;
				}
				if (jrbPGauss4.isSelected()) {
					mensajeSeleccion();
					igualSeleccion = true;

				}
			}
		}

		//		transformacion archivos
		if ((jrbBogota3.isSelected() && jrbMagna4.isSelected())
				|| (jrbMagna3.isSelected() && jrbBogota4.isSelected())) {
			if (jrbPCartesianas3.isSelected()) {
				if (jrbPCartesianas4.isSelected()) {
					operacion = Clasificacion.TIPO_TRANSFORMACION.TIPO_CARTESIANAS2CARTESIANAS;
				}
				if (jrbGeograficas4.isSelected()) {
					operacion = Clasificacion.TIPO_TRANSFORMACION.TIPO_CARTESIANAS2GEOGRAFICAS;
				}
				if (jrbPGauss4.isSelected()) {
					operacion = Clasificacion.TIPO_TRANSFORMACION.TIPO_CARTESIANAS2GAUSS;
				}
			}
			if (jrbGeograficas3.isSelected()) {
				if (jrbPCartesianas4.isSelected()) {
					operacion = Clasificacion.TIPO_TRANSFORMACION.TIPO_GEOGRAFICAS2CARTESIANAS;
				}
				if (jrbGeograficas4.isSelected()) {
					operacion = Clasificacion.TIPO_TRANSFORMACION.TIPO_GEOGRAFICAS2GEOGRAFICAS;
				}
				if (jrbPGauss4.isSelected()) {
					operacion = Clasificacion.TIPO_TRANSFORMACION.TIPO_GEOGRAFICAS2GAUSS;
				}
			}
			if (jrbPGauss3.isSelected()) {
				if (jrbPCartesianas4.isSelected()) {
					operacion = Clasificacion.TIPO_TRANSFORMACION.TIPO_GAUSS2CARTESIANAS;
				}
				if (jrbGeograficas4.isSelected()) {
					operacion = Clasificacion.TIPO_TRANSFORMACION.TIPO_GAUSS2GEOGRAFICAS;
				}
				if (jrbPGauss4.isSelected()) {
					operacion = Clasificacion.TIPO_TRANSFORMACION.TIPO_GAUSS2GAUSS;
				}
			}
		}
	}

	/**
	 * Escribe el contenido de los campos 4 y 5
	 * 
	 * @param campo4
	 * @param campo5
	 */
	private void escribirCampos(String campo4, String campo5) {
		jtfCampo4.setText(campo4);
		jtfCampo5.setText(campo5);
	}

	/**
	 * Muestra un mensaje de advertencia de seleccion
	 * 
	 * @return void
	 */
	private void mensajeSeleccion() {
		JOptionPane.showMessageDialog(this,
				"Ha seleccionado el mismo origen y destino\n"
						+ "en la conversi\u00f3n!", "Error...",
				JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Metodo que visualiza la ayuda
	 *@return void
	 */
	private void visualizarAyuda(){
		helpSetName = "help/layout.hs";
		//helpSetURL = "help/";
		
		ClassLoader loader = this.getClass().getClassLoader();
		URL url;
		try{
			url = HelpSet.findHelpSet(loader, helpSetName);
			hs = new HelpSet(loader , url);
		}catch(Exception ee){
			System.out.println("Problemas al crear HelpSet;");
			ee.printStackTrace();
			return;
		}
		
		hb = hs.createHelpBroker();
		hb.setDisplayed(true);
	}
	
	/**
	 * Retorna el tipo de datum del origen
	 * @return   tipo de datum Bogota o Magna
	 * @uml.property   name="tipoDatum"
	 */
	public int getTipoDatum() {
		return tipoDatum;
	}

	/**
	 * Retorna el tipo de operaci&oacute;n a realizar
	 * @return   operacion a realizar
	 * @uml.property   name="operacion"
	 */
	public int getOperacion() {
		return operacion;
	}

	/**
	 * Retorna el valor de selecci&oacute;n para la transformaci&oacute;n af&iacute;n
	 * 
	 * @return verdadero para transformacion afin
	 */
	public boolean getTransAfin() {
		if (jcbAfin.isSelected())
			return true;
		else
			return false;
	}

	/**
	 * Retorna el contenido del campo1
	 * @return   contenido campo1
	 * @uml.property   name="campo1"
	 */
	public double getCampo1() {
		return campo1;
	}

	/**
	 * Retorna el contenido del campo2
	 * @return   contenido campo2
	 * @uml.property   name="campo2"
	 */
	public double getCampo2() {
		return campo2;
	}

	/**
	 * Retorna el contenido del campo3
	 * @return   contenido campo3
	 * @uml.property   name="campo3"
	 */
	public double getCampo3() {
		return campo3;
	}

	/**
	 * This method initializes jmiInfo
	 * @return   javax.swing.JMenuItem
	 * @uml.property   name="jmiInfo"
	 */
	private JMenuItem getJmiInfo() {

		if (jmiInfo == null) {
			jmiInfo = new JMenuItem();
			jmiInfo.setText("Info");
			jmiInfo.setVisible(false);
			jmiInfo.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					info.setVisible(true);
				}
			});
		}
		return jmiInfo;
	}

                	public static void main(String args[]) {

		new FramePrincipal().setVisible(true);
	}
} //  @jve:decl-index=0:visual-constraint="41,16"
