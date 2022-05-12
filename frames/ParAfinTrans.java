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
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import tools.ConverAngulos;
import tools.ReadFiles;

import javax.swing.JButton;

/**
 * @author   david
 */
public class ParAfinTrans extends JDialog implements ListSelectionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4050477915688874040L;

	private javax.swing.JPanel jContentPane = null;

	private JScrollPane jScrollPane = null;

	private JList jlOrigenes = null;

	private JPanel jPanel = null;

	private JPanel jPanel1 = null;

	private JPanel jPanel2 = null;

	private JLabel jLabel = null;

	private JLabel jLabel1 = null;

	private JTextField jtfLatMin = null;

	private JLabel jLabel2 = null;

	private JTextField jtfLatMax = null;

	private JLabel jLabel3 = null;

	private JLabel jLabel4 = null;

	private JTextField jtfLonMin = null;

	private JLabel jLabel5 = null;

	private JTextField jtfLonMax = null;

	private JLabel jLabel6 = null;

	private JLabel jLabel7 = null;

	private JLabel jLabel8 = null;

	private JLabel jLabel9 = null;

	private JLabel jLabel10 = null;

	private JLabel jLabel11 = null;

	private JLabel jLabel12 = null;

	private JTextField jtfParamA = null;

	private JTextField jtfParamB = null;

	private JTextField jtfParamC = null;

	private JTextField jtfParamF = null;

	private JTextField jtfParamE = null;

	private JTextField jtfParamD = null;

	private String[][] paramAfin;

	double latMin = 0 ;
	double latMax = 0 ;
	double lonMin = 0 ;
	double lonMax = 0 ;
	double paramA = 0 ;
	double paramB = 0 ;
	double paramC = 0 ;
	double paramD = 0 ;
	double paramE = 0 ;
	double paramF = 0 ;

	private JButton jButton = null;

	/**
	 * This is the default constructor
	 */
	public ParAfinTrans(java.awt.Frame parent, boolean modal, boolean siGauss) {
		super(parent, modal);
		ReadFiles rArchivos = new ReadFiles();
		
		if (siGauss)
			paramAfin = rArchivos.readParamAfinGauss();
		else
			paramAfin = rArchivos.readParamAfinCart();
		
		Vector vOrigenes = new Vector();
		for (int j = 0; j < paramAfin.length - 1; j++) {
			vOrigenes.add(paramAfin[j][0]);
		}
		initialize();

		jlOrigenes.setListData(vOrigenes);
		jlOrigenes.setSelectedIndex(0);
		jlOrigenes.addListSelectionListener(this);
		jtfLatMin.setText(paramAfin[0][1]);
		jtfLatMax.setText(paramAfin[0][2]);
		jtfLonMin.setText(paramAfin[0][3]);
		jtfLonMax.setText(paramAfin[0][4]);
		jtfParamA.setText(paramAfin[0][5]);
		jtfParamB.setText(paramAfin[0][6]);
		jtfParamC.setText(paramAfin[0][7]);
		jtfParamD.setText(paramAfin[0][8]);
		jtfParamE.setText(paramAfin[0][9]);
		jtfParamF.setText(paramAfin[0][10]);
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setTitle("Par\u00e1metros de Transformaci\u00f3n Af\u00edn");
		this.setSize(491, 352);
		//		calcula la posciocion de la ventana
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(screenSize.width / 2 - (444 / 2), screenSize.height / 2
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
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			jContentPane = new javax.swing.JPanel();
			jContentPane.setLayout(new GridBagLayout());
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.gridy = 0;
			gridBagConstraints1.weightx = 1.0;
			gridBagConstraints1.weighty = 1.0;
			gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
			gridBagConstraints1.ipadx = -133;
			gridBagConstraints1.ipady = 143;
			gridBagConstraints1.insets = new java.awt.Insets(14,17,4,6);
			gridBagConstraints2.gridx = 1;
			gridBagConstraints2.gridy = 0;
			gridBagConstraints2.ipadx = 306;
			gridBagConstraints2.ipady = 270;
			gridBagConstraints2.insets = new java.awt.Insets(17,6,4,19);
			gridBagConstraints3.gridx = 1;
			gridBagConstraints3.gridy = 1;
			gridBagConstraints3.ipadx = 10;
			gridBagConstraints3.ipady = -7;
			gridBagConstraints3.insets = new java.awt.Insets(4,87,11,182);
			gridBagConstraints4.gridx = 0;
			gridBagConstraints4.gridy = 0;
			gridBagConstraints4.weightx = 1.0;
			gridBagConstraints4.weighty = 1.0;
			gridBagConstraints4.fill = java.awt.GridBagConstraints.BOTH;
			gridBagConstraints4.ipadx = -133;
			gridBagConstraints4.ipady = 143;
			gridBagConstraints4.insets = new java.awt.Insets(14,17,4,6);
			gridBagConstraints5.gridx = 1;
			gridBagConstraints5.gridy = 0;
			gridBagConstraints5.ipadx = 306;
			gridBagConstraints5.ipady = 270;
			gridBagConstraints5.insets = new java.awt.Insets(17,6,4,19);
			gridBagConstraints6.gridx = 1;
			gridBagConstraints6.gridy = 1;
			gridBagConstraints6.ipadx = 10;
			gridBagConstraints6.ipady = -7;
			gridBagConstraints6.insets = new java.awt.Insets(4,87,11,182);
			jContentPane.add(getJScrollPane(), gridBagConstraints4);
			jContentPane.add(getJPanel(), gridBagConstraints5);
			jContentPane.add(getJButton(), gridBagConstraints6);
		}
		return jContentPane;
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
		}
		return jScrollPane;
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
	 * This method initializes jPanel
	 * @return   javax.swing.JPanel
	 * @uml.property   name="jPanel"
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setBorder(javax.swing.BorderFactory.createLineBorder(
					java.awt.Color.black, 1));
			jPanel.add(getJPanel1(), null);
			jPanel.add(getJPanel2(), null);
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
			jPanel1 = new JPanel();
			jLabel = new JLabel();
			jLabel1 = new JLabel();
			jLabel2 = new JLabel();
			jLabel3 = new JLabel();
			jLabel4 = new JLabel();
			jLabel5 = new JLabel();
			jPanel1.setLayout(null);
			jPanel1.setBounds(15, 11, 279, 120);
			jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(
					java.awt.Color.black, 1));
			jLabel.setBounds(13, 9, 68, 20);
			jLabel.setText("Latitud");
			jLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jLabel1.setBounds(10, 37, 29, 21);
			jLabel1.setText("Min");
			jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jLabel2.setBounds(134, 37, 33, 21);
			jLabel2.setText("Max");
			jLabel3.setBounds(12, 65, 72, 17);
			jLabel3.setText("Longitud");
			jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jLabel4.setBounds(10, 93, 29, 19);
			jLabel4.setText("Min");
			jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jLabel5.setBounds(134, 91, 33, 22);
			jLabel5.setText("Max");
			jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jPanel1.add(jLabel, null);
			jPanel1.add(jLabel1, null);
			jPanel1.add(getJtfLatMin(), null);
			jPanel1.add(jLabel2, null);
			jPanel1.add(getJtfLatMax(), null);
			jPanel1.add(jLabel3, null);
			jPanel1.add(jLabel4, null);
			jPanel1.add(getJtfLonMin(), null);
			jPanel1.add(jLabel5, null);
			jPanel1.add(getJtfLonMax(), null);
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
			jLabel6 = new JLabel();
			jLabel7 = new JLabel();
			jLabel8 = new JLabel();
			jLabel9 = new JLabel();
			jLabel10 = new JLabel();
			jLabel11 = new JLabel();
			jLabel12 = new JLabel();
			jPanel2.setLayout(null);
			jPanel2.setBounds(15, 142, 279, 117);
			jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(
					java.awt.Color.black, 1));
			jLabel6.setBounds(12, 11, 94, 18);
			jLabel6.setText("Par\u00e1metros");
			jLabel7.setBounds(7, 37, 19, 18);
			jLabel7.setText("a");
			jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jLabel8.setBounds(7, 60, 19, 18);
			jLabel8.setText("b");
			jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jLabel9.setBounds(7, 84, 19, 18);
			jLabel9.setText("c");
			jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jLabel10.setBounds(140, 37, 22, 18);
			jLabel10.setText("d");
			jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jLabel11.setBounds(140, 60, 22, 18);
			jLabel11.setText("e");
			jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jLabel12.setBounds(140, 83, 22, 20);
			jLabel12.setText("f");
			jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jPanel2.add(jLabel6, null);
			jPanel2.add(jLabel7, null);
			jPanel2.add(jLabel8, null);
			jPanel2.add(jLabel9, null);
			jPanel2.add(jLabel10, null);
			jPanel2.add(jLabel11, null);
			jPanel2.add(jLabel12, null);
			jPanel2.add(getJtfParamA(), null);
			jPanel2.add(getJtfParamB(), null);
			jPanel2.add(getJtfParamC(), null);
			jPanel2.add(getJtfParamF(), null);
			jPanel2.add(getJtfParamE(), null);
			jPanel2.add(getJtfParamD(), null);
		}
		return jPanel2;
	}

	/**
	 * This method initializes jtfLatMin
	 * @return   javax.swing.JTextField
	 * @uml.property   name="jtfLatMin"
	 */
	private JTextField getJtfLatMin() {
		if (jtfLatMin == null) {
			jtfLatMin = new JTextField();
			jtfLatMin.setBounds(49, 37, 75, 21);
			jtfLatMin.setEditable(false);
			jtfLatMin.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
		}
		return jtfLatMin;
	}

	/**
	 * This method initializes jtfLatMax
	 * @return   javax.swing.JTextField
	 * @uml.property   name="jtfLatMax"
	 */
	private JTextField getJtfLatMax() {
		if (jtfLatMax == null) {
			jtfLatMax = new JTextField();
			jtfLatMax.setBounds(177, 37, 91, 21);
			jtfLatMax.setEditable(false);
			jtfLatMax.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
		}
		return jtfLatMax;
	}

	/**
	 * This method initializes jtfLonMin
	 * @return   javax.swing.JTextField
	 * @uml.property   name="jtfLonMin"
	 */
	private JTextField getJtfLonMin() {
		if (jtfLonMin == null) {
			jtfLonMin = new JTextField();
			jtfLonMin.setBounds(49, 92, 75, 20);
			jtfLonMin.setEditable(false);
			jtfLonMin.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
		}
		return jtfLonMin;
	}

	/**
	 * This method initializes jtfLonMax
	 * @return   javax.swing.JTextField
	 * @uml.property   name="jtfLonMax"
	 */
	private JTextField getJtfLonMax() {
		if (jtfLonMax == null) {
			jtfLonMax = new JTextField();
			jtfLonMax.setBounds(177, 92, 91, 20);
			jtfLonMax.setEditable(false);
			jtfLonMax.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
		}
		return jtfLonMax;
	}

	/**
	 * This method initializes jtfParamA
	 * @return   javax.swing.JTextField
	 * @uml.property   name="jtfParamA"
	 */
	private JTextField getJtfParamA() {
		if (jtfParamA == null) {
			jtfParamA = new JTextField();
			jtfParamA.setBounds(33, 37, 100, 18);
			jtfParamA.setEditable(false);
			jtfParamA.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
		}
		return jtfParamA;
	}

	/**
	 * This method initializes jtfParamB
	 * @return   javax.swing.JTextField
	 * @uml.property   name="jtfParamB"
	 */
	private JTextField getJtfParamB() {
		if (jtfParamB == null) {
			jtfParamB = new JTextField();
			jtfParamB.setBounds(33, 61, 100, 17);
			jtfParamB.setEditable(false);
			jtfParamB.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
		}
		return jtfParamB;
	}

	/**
	 * This method initializes jtfParamC
	 * @return   javax.swing.JTextField
	 * @uml.property   name="jtfParamC"
	 */
	private JTextField getJtfParamC() {
		if (jtfParamC == null) {
			jtfParamC = new JTextField();
			jtfParamC.setBounds(33, 84, 100, 18);
			jtfParamC.setEditable(false);
			jtfParamC.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
		}
		return jtfParamC;
	}

	/**
	 * This method initializes jtfParamF
	 * @return   javax.swing.JTextField
	 * @uml.property   name="jtfParamF"
	 */
	private JTextField getJtfParamF() {
		if (jtfParamF == null) {
			jtfParamF = new JTextField();
			jtfParamF.setBounds(169, 83, 102, 20);
			jtfParamF.setEditable(false);
			jtfParamF.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
		}
		return jtfParamF;
	}

	/**
	 * This method initializes jtfParamE
	 * @return   javax.swing.JTextField
	 * @uml.property   name="jtfParamE"
	 */
	private JTextField getJtfParamE() {
		if (jtfParamE == null) {
			jtfParamE = new JTextField();
			jtfParamE.setBounds(169, 60, 102, 18);
			jtfParamE.setEditable(false);
			jtfParamE.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
		}
		return jtfParamE;
	}

	/**
	 * This method initializes jtfParamD
	 * @return   javax.swing.JTextField
	 * @uml.property   name="jtfParamD"
	 */
	private JTextField getJtfParamD() {
		if (jtfParamD == null) {
			jtfParamD = new JTextField();
			jtfParamD.setBounds(169, 37, 102, 18);
			jtfParamD.setEditable(false);
			jtfParamD.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
		}
		return jtfParamD;
	}

	/**
	 * Devuelve el valor de la latitud m&iacute;nima
	 * @return   laititud minima en radianes
	 * @uml.property   name="latMin"
	 */
	public double getLatMin() {
		return latMin;
	}

	/**
	 * Devuelve el valor de la latitud m&aacute;xima
	 * @return   latitud maxima en radianes
	 * @uml.property   name="latMax"
	 */
	public double getLatMax() {
		return latMax;
	}

	/**
	 * Devuelve el valor de la longitud m&iacute;nima
	 * @return   longitud minima en radianes
	 * @uml.property   name="lonMin"
	 */
	public double getLonMin() {
		return lonMin;
	}

	/**
	 * Devuelve el valor de la longitud m&aacute;xima
	 * @return   longitud maxima en radianes
	 * @uml.property   name="lonMax"
	 */
	public double getLonMax() {
		return lonMax;
	}

	/**
	 * Devuelve el valor del par&aacute;metro A
	 * @return   parametro A
	 * @uml.property   name="paramA"
	 */
	public double getParamA() {
		return paramA;
	}

	/**
	 * Devuelve el valor del par&aacute;metro B
	 * @return   parametro B
	 * @uml.property   name="paramB"
	 */
	public double getParamB() {
		return paramB;
	}

	/**
	 * Devuelve el valor del par&aacute;metro C
	 * @return   parametro C
	 * @uml.property   name="paramC"
	 */
	public double getParamC() {
		return paramC;
	}

	/**
	 * Devuelve el valor del par&aacute;metro D
	 * @return   parametro D
	 * @uml.property   name="paramD"
	 */
	public double getParamD() {
		return paramD;
	}

	/**
	 * Devuelve el valor del par&aacute;metro E
	 * @return   parametro E
	 * @uml.property   name="paramE"
	 */
	public double getParamE() {
		return paramE;
	}

	/**
	 * Devuelve el valor del par&aacute;metro F
	 * @return   parametro F
	 * @uml.property   name="paramF"
	 */
	public double getParamF() {
		return paramF;
	}

	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting())
			return;
		JList list = (JList) e.getSource();
		if (!list.isSelectionEmpty()) {
			//int index = list.getSelectedIndex();
			jtfLatMin.setText(paramAfin[0][1]);
			jtfLatMax.setText(paramAfin[0][2]);
			jtfLonMin.setText(paramAfin[0][3]);
			jtfLonMax.setText(paramAfin[0][4]);
			jtfParamA.setText(paramAfin[0][5]);
			jtfParamB.setText(paramAfin[0][6]);
			jtfParamC.setText(paramAfin[0][7]);
			jtfParamD.setText(paramAfin[0][8]);
			jtfParamE.setText(paramAfin[0][9]);
			jtfParamF.setText(paramAfin[0][10]);

		}
	}

	/**
	 * This method initializes jButton
	 * @return   javax.swing.JButton
	 * @uml.property   name="jButton"
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setText("OK");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ConverAngulos cvAng = new ConverAngulos();
					latMin = cvAng.gmsToRad(Double.valueOf(jtfLatMin.getText())
							.doubleValue());
					latMax = cvAng.gmsToRad(Double.valueOf(jtfLatMax.getText())
							.doubleValue());
					lonMin = cvAng.gmsToRad(Double.valueOf(jtfLonMin.getText())
							.doubleValue());
					lonMax = cvAng.gmsToRad(Double.valueOf(jtfLonMax.getText())
							.doubleValue());
					paramA = Double.valueOf(jtfParamA.getText()).doubleValue();
					paramB = Double.valueOf(jtfParamB.getText()).doubleValue();
					paramC = Double.valueOf(jtfParamC.getText()).doubleValue();
					paramD = Double.valueOf(jtfParamD.getText()).doubleValue();
					paramE = Double.valueOf(jtfParamE.getText()).doubleValue();
					paramF = Double.valueOf(jtfParamF.getText()).doubleValue();

					setVisible(false);
				}
			});
		}
		return jButton;
	}

} //  @jve:decl-index=0:visual-constraint="10,10"
