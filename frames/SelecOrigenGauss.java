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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
/**
 * @author   david
 */
public class SelecOrigenGauss extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3258689901301216566L;

	private javax.swing.JPanel jContentPane = null;

	private JLabel jLabel = null;

	private JLabel jLabel1 = null;

	private JComboBox jComboBox = null;

	private boolean tipoConversion;
	
	private int seleccion;

	private JButton jbOk = null;
	/**
	 * This is the default constructor
	 */
	public SelecOrigenGauss(java.awt.Frame parent, boolean modal,
			boolean tipoConversion) {
		super(parent, modal);
		this.tipoConversion = tipoConversion;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
		this.setTitle("Or\u00edgenes Gauss - Kr\u00fcger");
		this.setSize(300, 157);
//		calcula la posciocion de la ventana
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(screenSize.width / 2 - (300 / 2), screenSize.height / 2
				- (157 / 2));
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
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			jLabel1 = new JLabel();
			jLabel = new JLabel();
			jContentPane = new javax.swing.JPanel();
			jContentPane.setLayout(new GridBagLayout());
			
			if (tipoConversion == true)
				jLabel.setText("Geogr\u00e1ficas a Planas Gauss");
			else
				jLabel.setText("Planas Gauss a Geogr\u00e1ficas");
			
			jLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jLabel1.setText("Origen:");
			jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.gridy = 0;
			gridBagConstraints1.gridwidth = 2;
			gridBagConstraints1.ipadx = 86;
			gridBagConstraints1.ipady = 23;
			gridBagConstraints1.insets = new java.awt.Insets(10,22,3,24);
			gridBagConstraints2.gridx = 0;
			gridBagConstraints2.gridy = 1;
			gridBagConstraints2.ipadx = 37;
			gridBagConstraints2.ipady = 12;
			gridBagConstraints2.insets = new java.awt.Insets(4,35,6,5);
			gridBagConstraints3.gridx = 1;
			gridBagConstraints3.gridy = 1;
			gridBagConstraints3.weightx = 1.0;
			gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints3.ipadx = 42;
			gridBagConstraints3.ipady = 3;
			gridBagConstraints3.insets = new java.awt.Insets(4,6,6,26);
			gridBagConstraints4.gridx = 0;
			gridBagConstraints4.gridy = 2;
			gridBagConstraints4.gridwidth = 2;
			gridBagConstraints4.ipadx = 26;
			gridBagConstraints4.ipady = -5;
			gridBagConstraints4.insets = new java.awt.Insets(6,94,13,121);
			jContentPane.add(jLabel, gridBagConstraints1);
			jContentPane.add(jLabel1, gridBagConstraints2);
			jContentPane.add(getJComboBox(), gridBagConstraints3);
			jContentPane.add(getJbOk(), gridBagConstraints4);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jComboBox
	 * @return   javax.swing.JComboBox
	 * @uml.property   name="jComboBox"
	 */
	private JComboBox getJComboBox() {
		if (jComboBox == null) {
			jComboBox = new JComboBox();
			if (tipoConversion == true) {
				jComboBox.setModel(new javax.swing.DefaultComboBoxModel(
						new String[] { "Bogot\u00e1", "Este", "Oeste", "Este-Este",
								"Oeste-Oeste", "Autom\u00e1tico" }));
			} else {
				jComboBox.setModel(new javax.swing.DefaultComboBoxModel(
						new String[] { "Bogot\u00e1", "Este", "Oeste", "Este-Este",
								"Oeste-Oeste" }));
			}
		}
		return jComboBox;
	}
	
	/**
	 * @return   Returns the seleccion.
	 * @uml.property   name="seleccion"
	 */
	public int getSeleccion(){
		return seleccion;
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
			jbOk.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					seleccion = jComboBox.getSelectedIndex() + 1;
					setVisible(false);
				}
			});
		}
		return jbOk;
	}
 } //  @jve:decl-index=0:visual-constraint="10,10"
