/*
 * Created on 10/03/2005
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

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.ImageIcon;

/**
 * @author   david
 */
public class AcercaDe extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3257002168199689778L;

	private javax.swing.JPanel jContentPane = null;

	private JPanel jPanel = null;

	private JTabbedPane jTabbedPane = null;

	private JPanel jPanel1 = null;

	private JTextPane jTextPane = null;

	private JPanel jPanel2 = null;

	private JScrollPane jScrollPane = null;

	private JTextArea jTextArea = null;

	private JPanel jPanel3 = null;

	private JScrollPane jScrollPane1 = null;

	private JTextArea jTextArea1 = null;

	private JLabel jLabel = null;

	/**
	 * Constructor de la clase
	 * @param padre JFrame padre
	 * @param modal bandera de informaci&oacute;n modal
	 */
	public AcercaDe(JFrame padre, boolean modal) {
		super(padre, modal);
		initialize();
	}

	/**
	 * Este metodo inicializa los par&aacute;metros del dialogo
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setTitle("Acerca De CONCOORD");
		this.setResizable(false);
		this.setSize(500, 400);
		//		calcula la posciocion de la ventana
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this
				.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setLocation(screenSize.width / 2 - (500 / 2), screenSize.height / 2
				- (400 / 2));
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
			jContentPane.setLayout(new java.awt.BorderLayout());
			jContentPane.add(getJPanel(), java.awt.BorderLayout.CENTER);
		}
		return jContentPane;
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
			jPanel.add(getJTabbedPane(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes jTabbedPane
	 * @return   javax.swing.JTabbedPane
	 * @uml.property   name="jTabbedPane"
	 */
	private JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();
			jTabbedPane.setSize(489, 371);
			jTabbedPane.setLocation(0, 0);
			jTabbedPane.setName("");
			jTabbedPane.addTab("Acerca De CONCOORD", null, getJPanel1(), null);
			jTabbedPane.addTab("Escrito por", null, getJPanel2(), null);
			jTabbedPane.addTab("Documentado por", null, getJPanel3(), null);
		}
		return jTabbedPane;
	}

	/**
	 * This method initializes jPanel1
	 * @return   javax.swing.JPanel
	 * @uml.property   name="jPanel1"
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jLabel = new JLabel();
			jPanel1 = new JPanel();
			jPanel1.setLayout(null);
			jLabel.setBounds(22, 11, 437, 194);
			jLabel.setText("");
			jLabel.setIcon(new ImageIcon(getClass().getResource(
					"/icons/concoord2.jpg")));
			jPanel1.add(getJTextPane(), null);
			jPanel1.add(jLabel, null);
		}
		return jPanel1;
	}

	/**
	 * This method initializes jTextPane
	 * @return   javax.swing.JTextPane
	 * @uml.property   name="jTextPane"
	 */
	private JTextPane getJTextPane() {
		if (jTextPane == null) {
			jTextPane = new JTextPane();
			SimpleAttributeSet aSet = new SimpleAttributeSet();
			StyleConstants.setAlignment(aSet, StyleConstants.ALIGN_CENTER);
			jTextPane.setCharacterAttributes(aSet, true);
			jTextPane.setEditable(false);
			jTextPane
					.setText("CONCOORD es una herramienta de conversi\u00f3n y transformaci\u00f3n de coordenadas, D\u00e1tum Internacional o Hayford - D\u00e1tum GRS80 (Magna Sirgas).\n\nCopyright \u00a9 2004-2005 Acueducto Agua y Alcantarillado de Bogot\u00e1 - ESP.\n\nnachocalendar se utiliz\u00f3 como selector de fechas. http://nachocalendar.sourceforge.net/ \nDise\u00f1o del logo por: Paola Lievano <paola.lievano@gmail.com>\n");
			jTextPane.setBackground(java.awt.Color.lightGray);
			jTextPane.setBounds(10, 210, 464, 128);

			jTextPane.setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 12));
		}
		return jTextPane;
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
			jPanel2.add(getJScrollPane(), null);
		}
		return jPanel2;
	}

	/**
	 * This method initializes jScrollPane
	 * @return   javax.swing.JScrollPane
	 * @uml.property   name="jScrollPane"
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(11, 10, 465, 326);
			jScrollPane.setViewportView(getJTextArea());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jTextArea
	 * @return   javax.swing.JTextArea
	 * @uml.property   name="jTextArea"
	 */
	private JTextArea getJTextArea() {
		if (jTextArea == null) {
			jTextArea = new JTextArea();
			jTextArea.setText("David Acosta <dacosta77@gmail.com>");
			jTextArea.setEditable(false);
			jTextArea.setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 12));
			jTextArea.setBackground(java.awt.Color.lightGray);
		}
		return jTextArea;
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
			jPanel3.add(getJScrollPane1(), null);
		}
		return jPanel3;
	}

	/**
	 * This method initializes jScrollPane1
	 * @return   javax.swing.JScrollPane
	 * @uml.property   name="jScrollPane1"
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setBounds(11, 10, 464, 325);
			jScrollPane1.setViewportView(getJTextArea1());
		}
		return jScrollPane1;
	}

	/**
	 * This method initializes jTextArea1
	 * @return   javax.swing.JTextArea
	 * @uml.property   name="jTextArea1"
	 */
	private JTextArea getJTextArea1() {
		if (jTextArea1 == null) {
			jTextArea1 = new JTextArea();
			jTextArea1
					.setText("David Acosta <dacosta77@gmail.com>");
			jTextArea1.setBackground(java.awt.Color.lightGray);
			jTextArea1.setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 12));
			jTextArea1.setEditable(false);
		}
		return jTextArea1;
	}

}