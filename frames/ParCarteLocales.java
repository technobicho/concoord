/*
 * Created on 29-nov-2004
 * Derechos reservados @ 2004 David Acosta
 * <dac77@tutopia.com>
 * Este es un software libre; como tal redistribuirlo y/o modificarlo esta permitido, 
 * siempre y cuando se haga bajo los terminos y condiciones de la Licencia Publica General
 * GNU publicada por la Free Software Foundation, ya sea en su version 2 o cualquier otra
 * de las posteriores a la misma.
 */
package frames;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import tools.ConverAngulos;
import tools.ReadFiles;

/**
 * @author   david
 */
public class ParCarteLocales extends JDialog implements ListSelectionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4050765979128574516L;

	private javax.swing.JPanel jContentPane = null;

	private JList jlOrigenes = null;

	private JScrollPane jScrollPane = null;

	private JPanel jPanel = null;

	private JPanel jPanel1 = null;

	private JPanel jPanel2 = null;

	private JPanel jPanel3 = null;

	private JLabel jlbAnio = null;

	private JTextField jtfAnio = null;

	private JLabel jlbPlanoProy = null;

	private JTextField jtfPlanoProy = null;

	private JButton jbOk = null;

	private JLabel jlbLatMagna = null;

	private JLabel jlbLonMagna = null;

	private JTextField jtfLatMagna = null;

	private JTextField jtfLonMagna = null;

	private JLabel jlbLatBogota = null;

	private JLabel jlbLonBogota = null;

	private JTextField jtfLatBogota = null;

	private JTextField jtfLonBogota = null;

	private JLabel jlbNorteCart = null;

	private JLabel jlbEsteCart = null;

	private JTextField jtfNorteCart = null;

	private JTextField jtfEsteCart = null;

	private String[][] paramCartesianas;

	private JScrollPane jScrollPane1 = null;

	private JTextArea jtaDescripcion = null;
	
	private double latitudBogota = 0 ;
	private double longitudBogota = 0 ;
	private double latitudMagna = 0 ;
	private double longitudMagna = 0 ;
	private double planoProyeccion = 0 ;
	private double norteCart = 0 ;
	private double esteCart = 0 ;

	private JLabel jLabel = null;
	/**
	 * This is the default constructor
	 */
	public ParCarteLocales(JFrame parent, boolean modal) {
		super(parent, modal);
		ReadFiles rArchivos = new ReadFiles();
		paramCartesianas = rArchivos.readParamCarte();
		Vector vOrigenes = new Vector();
		for (int j = 0; j < paramCartesianas.length - 1; j++) {
			vOrigenes.add(paramCartesianas[j][1]);
		}
		initialize();

		jlOrigenes.setListData(vOrigenes);
		jlOrigenes.setSelectedIndex(0);
		jlOrigenes.addListSelectionListener(this);
		jtfAnio.setText(paramCartesianas[0][2]);
		jtfLatMagna.setText(paramCartesianas[0][3]);
		jtfLonMagna.setText(paramCartesianas[0][4]);
		jtfLatBogota.setText(paramCartesianas[0][5]);
		jtfLonBogota.setText(paramCartesianas[0][6]);
		jtfNorteCart.setText(paramCartesianas[0][8]);
		jtfEsteCart.setText(paramCartesianas[0][9]);
		jtfPlanoProy.setText(paramCartesianas[0][7]);		
		jtaDescripcion.setText(paramCartesianas[0][10]);
		jtaDescripcion.moveCaretPosition(0);
		
		
		
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this
				.setTitle("Par\u00e1metros de los or\u00edgenes cartesianos");
		this.setSize(560, 368);
		//		calcula la posciocion de la ventana
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(screenSize.width / 2 - (518 / 2), screenSize.height / 2
				- (352 / 2));
		screenSize = null;
		this.setContentPane(getJContentPane());
	}

	/**
	 * This method initializes jContentPane
	 * @return   javax.swing.JPanel
	 * @uml.property   name="jContentPane"
	 */
	private javax.swing.JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new javax.swing.JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJScrollPane(), null);
			jContentPane.add(getJPanel(), null);
			jContentPane.add(getJbOk(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jlOrigenes
	 * @return   javax.swing.JList
	 * @uml.property   name="jlOrigenes"
	 */
	private JList getJlOrigenes() {
		if (jlOrigenes == null) {
			jlOrigenes = new JList();
		}
		return jlOrigenes;
	}

	/**
	 * This method initializes jScrollPane
	 * @return   javax.swing.JScrollPane
	 * @uml.property   name="jScrollPane"
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getJlOrigenes());
			jScrollPane.setBounds(17, 19, 123, 287);
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jPanel
	 * @return   javax.swing.JPanel
	 * @uml.property   name="jPanel"
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jLabel = new JLabel();
			jPanel = new JPanel();
			jlbAnio = new JLabel();
			jlbPlanoProy = new JLabel();
			jPanel.setLayout(null);
			jPanel.setBorder(javax.swing.BorderFactory.createLineBorder(
					java.awt.Color.black, 1));
			jPanel.setBounds(151, 19, 384, 287);
			jlbAnio.setBounds(18, 14, 45, 20);
			jlbAnio.setText("A\u00f1o:");
			jlbAnio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jlbPlanoProy.setBounds(222, 134, 140, 19);
			jlbPlanoProy.setText("Plano de Proyecci\u00d3n:");
			jlbPlanoProy.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
			jlbPlanoProy.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jLabel.setBounds(14, 212, 128, 19);
			jLabel.setText("Observaciones");
			jPanel.add(getJPanel1(), null);
			jPanel.add(getJPanel2(), null);
			jPanel.add(getJPanel3(), null);
			jPanel.add(jlbAnio, null);
			jPanel.add(getJtfAnio(), null);
			jPanel.add(jlbPlanoProy, null);
			jPanel.add(getJtfPlanoProy(), null);
			jPanel.add(getJScrollPane1(), null);
			jPanel.add(jLabel, null);
		}
		return jPanel;
	}

	/**
	 * This method initializes jPanel1
	 * @return   javax.swing.JPanel
	 * @uml.property   name="jPanel1"
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jlbLonMagna = new JLabel();
			jlbLatMagna = new JLabel();
			jPanel1 = new JPanel();
			jPanel1.setLayout(null);
			jPanel1.setBounds(11, 49, 176, 75);
			jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(
					javax.swing.BorderFactory.createLineBorder(
							java.awt.Color.black, 1), "Magna",
					javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
					javax.swing.border.TitledBorder.DEFAULT_POSITION, null,
					null));
			jlbLatMagna.setText("Latitud:");
			jlbLatMagna.setBounds(9, 19, 59, 20);
			jlbLonMagna.setBounds(9, 42, 59, 20);
			jlbLonMagna.setText("Longitud:");
			jPanel1.add(jlbLatMagna, null);
			jPanel1.add(jlbLonMagna, null);
			jPanel1.add(getJtfLatMagna(), null);
			jPanel1.add(getJtfLonMagna(), null);
		}
		return jPanel1;
	}

	/**
	 * This method initializes jPanel2
	 * @return   javax.swing.JPanel
	 * @uml.property   name="jPanel2"
	 */
	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jPanel2 = new JPanel();
			jlbLatBogota = new JLabel();
			jlbLonBogota = new JLabel();
			jPanel2.setLayout(null);
			jPanel2.setBounds(193, 49, 182, 75);
			jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(
					javax.swing.BorderFactory.createLineBorder(
							java.awt.Color.black, 1), "Bogot\u00e1",
					javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
					javax.swing.border.TitledBorder.DEFAULT_POSITION, null,
					null));
			jlbLatBogota.setText("Latitud:");
			jlbLatBogota.setBounds(9, 17, 64, 20);
			jlbLonBogota.setBounds(9, 42, 64, 20);
			jlbLonBogota.setText("Longitud:");
			jPanel2.add(jlbLatBogota, null);
			jPanel2.add(jlbLonBogota, null);
			jPanel2.add(getJtfLatBogota(), null);
			jPanel2.add(getJtfLonBogota(), null);
		}
		return jPanel2;
	}

	/**
	 * This method initializes jPanel3
	 * @return   javax.swing.JPanel
	 * @uml.property   name="jPanel3"
	 */
	private JPanel getJPanel3() {
		if (jPanel3 == null) {
			jPanel3 = new JPanel();
			jlbNorteCart = new JLabel();
			jlbEsteCart = new JLabel();
			jPanel3.setLayout(null);
			jPanel3.setBounds(11, 131, 177, 71);
			jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(
					javax.swing.BorderFactory.createLineBorder(
							java.awt.Color.black, 1), "Cartesianas",
					javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
					javax.swing.border.TitledBorder.DEFAULT_POSITION, null,
					null));
			jlbNorteCart.setText("Norte:");
			jlbNorteCart.setBounds(10, 19, 45, 20);
			jlbEsteCart.setBounds(10, 43, 45, 19);
			jlbEsteCart.setText("Este:");
			jPanel3.add(jlbNorteCart, null);
			jPanel3.add(jlbEsteCart, null);
			jPanel3.add(getJtfNorteCart(), null);
			jPanel3.add(getJtfEsteCart(), null);
		}
		return jPanel3;
	}

	/**
	 * This method initializes jtfAnio
	 * @return   javax.swing.JTextField
	 * @uml.property   name="jtfAnio"
	 */
	private JTextField getJtfAnio() {
		if (jtfAnio == null) {
			jtfAnio = new JTextField();
			jtfAnio.setBounds(75, 14, 65, 20);
			jtfAnio.setEditable(false);
			jtfAnio.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
		}
		return jtfAnio;
	}

	/**
	 * This method initializes jtfPlanoProy
	 * @return   javax.swing.JTextField
	 * @uml.property   name="jtfPlanoProy"
	 */
	private JTextField getJtfPlanoProy() {
		if (jtfPlanoProy == null) {
			jtfPlanoProy = new JTextField();
			jtfPlanoProy.setBounds(247, 162, 91, 18);
			jtfPlanoProy.setEditable(false);
			jtfPlanoProy.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
		}
		return jtfPlanoProy;
	}

	/**
	 * This method initializes jbOk
	 * @return   javax.swing.JButton
	 * @uml.property   name="jbOk"
	 */
	private JButton getJbOk() {
		if (jbOk == null) {
			jbOk = new JButton();
			jbOk.setText("OK");
			jbOk.setBounds(215, 314, 65, 20);
			jbOk.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ConverAngulos cangulo = new ConverAngulos();
					latitudBogota = cangulo.gmsToRad(Double.valueOf(jtfLatBogota.getText()).doubleValue());
					longitudBogota = cangulo.gmsToRad(Double.valueOf(jtfLonBogota.getText()).doubleValue());
					latitudMagna = cangulo.gmsToRad(Double.valueOf(jtfLatMagna.getText()).doubleValue());
					longitudMagna = cangulo.gmsToRad(Double.valueOf(jtfLonMagna.getText()).doubleValue());
					planoProyeccion = Double.valueOf(jtfPlanoProy.getText()).doubleValue();
					norteCart = Double.valueOf(jtfNorteCart.getText()).doubleValue();
					esteCart = Double.valueOf(jtfEsteCart.getText()).doubleValue();
					setVisible(false);
				}
			});
		}
		return jbOk;
	}
	
	/**
	 * Metodo que retorna el valor de la latitud Bogot&aacute;
	 * @return   Latitud Bogot&aacute;
	 * @uml.property   name="latitudBogota"
	 */
	public double getLatitudBogota(){
		return latitudBogota;
	}
	
	/**
	 * Metodo que retorna el valor de la Longitud Bogot&aacute;
	 * @return   Longitud Bogot&aacute;
	 * @uml.property   name="longitudBogota"
	 */
	public double getLongitudBogota(){
		return longitudBogota;
	}
	
	/**
	 * Metodo que retorna el valor de la Latitud Magna
	 * @return   Latitud Magna
	 * @uml.property   name="latitudMagna"
	 */
	public double getLatitudMagna(){
		return latitudMagna;
	}
	
	/**
	 * Metodo que retorna el valor de la Longitud Magna
	 * @return   Longitud Magna
	 * @uml.property   name="longitudMagna"
	 */
	public double getLongitudMagna(){
		return longitudMagna;
	}
	
	/**
	 * Metodo que retorna el valor del plano de proyecci&oacute;n
	 * @return   Plano de Proyecci&oacute;n
	 * @uml.property   name="planoProyeccion"
	 */
	public double getPlanoProyeccion(){
		return planoProyeccion;
	}
	
	/**
	 * Metodo que retorna el valor de la coordenada norte cartesiana
	 * @return Coordenada norte cartesiana
	 */
	public double getNorteCartesiano(){
		return norteCart;
	}
	
	/**
	 * Metodo que retorna el valor de la coordenada este cartesiana
	 * @return Coordenada este cartesiana
	 */
	public double getEsteCartesiano(){
		return esteCart;
	}

	/**
	 * This method initializes jtfLatMagna
	 * @return   javax.swing.JTextField
	 * @uml.property   name="jtfLatMagna"
	 */
	private JTextField getJtfLatMagna() {
		if (jtfLatMagna == null) {
			jtfLatMagna = new JTextField();
			jtfLatMagna.setBounds(74, 19, 94, 20);
			jtfLatMagna.setEditable(false);
			jtfLatMagna.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
		}
		return jtfLatMagna;
	}

	/**
	 * This method initializes jtfLonMagna
	 * @return   javax.swing.JTextField
	 * @uml.property   name="jtfLonMagna"
	 */
	private JTextField getJtfLonMagna() {
		if (jtfLonMagna == null) {
			jtfLonMagna = new JTextField();
			jtfLonMagna.setBounds(74, 42, 94, 20);
			jtfLonMagna.setEditable(false);
			jtfLonMagna.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
		}
		return jtfLonMagna;
	}

	/**
	 * This method initializes jtfLatBogota
	 * @return   javax.swing.JTextField
	 * @uml.property   name="jtfLatBogota"
	 */
	private JTextField getJtfLatBogota() {
		if (jtfLatBogota == null) {
			jtfLatBogota = new JTextField();
			jtfLatBogota.setBounds(80, 17, 95, 20);
			jtfLatBogota.setEditable(false);
			jtfLatBogota.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
		}
		return jtfLatBogota;
	}

	/**
	 * This method initializes jtfLonBogota
	 * @return   javax.swing.JTextField
	 * @uml.property   name="jtfLonBogota"
	 */
	private JTextField getJtfLonBogota() {
		if (jtfLonBogota == null) {
			jtfLonBogota = new JTextField();
			jtfLonBogota.setBounds(80, 42, 95, 20);
			jtfLonBogota.setEditable(false);
			jtfLonBogota.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
		}
		return jtfLonBogota;
	}

	/**
	 * This method initializes jtfNorteCart
	 * @return   javax.swing.JTextField
	 * @uml.property   name="jtfNorteCart"
	 */
	private JTextField getJtfNorteCart() {
		if (jtfNorteCart == null) {
			jtfNorteCart = new JTextField();
			jtfNorteCart.setBounds(62, 19, 107, 20);
			jtfNorteCart.setEditable(false);
			jtfNorteCart.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
		}
		return jtfNorteCart;
	}

	/**
	 * This method initializes jtfEsteCart
	 * @return   javax.swing.JTextField
	 * @uml.property   name="jtfEsteCart"
	 */
	private JTextField getJtfEsteCart() {
		if (jtfEsteCart == null) {
			jtfEsteCart = new JTextField();
			jtfEsteCart.setBounds(62, 43, 107, 19);
			jtfEsteCart.setEditable(false);
			jtfEsteCart.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
		}
		return jtfEsteCart;
	}

	/**
	 * This method initializes jScrollPane1
	 * @return   javax.swing.JScrollPane
	 * @uml.property   name="jScrollPane1"
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setBounds(14, 234, 358, 39);			
			jScrollPane1.setViewportView(getJtaDescripcion());
			
		}
		return jScrollPane1;
	}

	/**
	 * This method initializes jtaDescripcion
	 * @return   javax.swing.JTextArea
	 * @uml.property   name="jtaDescripcion"
	 */
	private JTextArea getJtaDescripcion() {
		if (jtaDescripcion == null) {
			jtaDescripcion = new JTextArea();
			jtaDescripcion.setEditable(false);
		}
		return jtaDescripcion;
	}

	/**
	 * Metodo que determina los cambios producido en el JList
	 */
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting())
			return;
		JList list = (JList) e.getSource();
		if (!list.isSelectionEmpty()) {
			int index = list.getSelectedIndex();
			jtfAnio.setText(paramCartesianas[index][2]);
			jtfLatMagna.setText(paramCartesianas[index][3]);
			jtfLonMagna.setText(paramCartesianas[index][4]);
			jtfLatBogota.setText(paramCartesianas[index][5]);
			jtfLonBogota.setText(paramCartesianas[index][6]);
			jtfNorteCart.setText(paramCartesianas[index][8]);
			jtfEsteCart.setText(paramCartesianas[index][9]);
			jtfPlanoProy.setText(paramCartesianas[index][7]);
			jtaDescripcion.setText(paramCartesianas[index][10]);
			jtaDescripcion.moveCaretPosition(0);
		}
	}
} //  @jve:decl-index=0:visual-constraint="10,10"
