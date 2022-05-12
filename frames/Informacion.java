/*
 * Created on 07-dic-2004
 * Derechos reservados @ 2004 David Acosta
 * <dac77@tutopia.com>
 * Este es un software libre; como tal redistribuirlo y/o modificarlo esta permitido, 
 * siempre y cuando se haga bajo los terminos y condiciones de la Licencia Publica General
 * GNU publicada por la Free Software Foundation, ya sea en su version 2 o cualquier otra
 * de las posteriores a la misma.
 */
package frames;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
/**
 * @author   david
 */
public class Informacion extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3257572797537792817L;

	private javax.swing.JPanel jContentPane = null;

	private JPanel jpInfo = null;
	private JScrollPane jScrollPane = null;
	private JTextPane jtpInfo = null;
	/**
	 * This is the default constructor
	 */
	public Informacion(JFrame parent, boolean modal) {
		super(parent,modal);
		initialize();
	}
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setTitle("Informaci\u00f3n");
		this.setSize(300,200);
		this.setContentPane(getJContentPane());
	}
	/**
	 * This method initializes jContentPane
	 * @return   javax.swing.JPanel
	 * @uml.property   name="jContentPane"
	 */
	private javax.swing.JPanel getJContentPane() {
		if(jContentPane == null) {
			jContentPane = new javax.swing.JPanel();
			jContentPane.setLayout(new java.awt.BorderLayout());
			jContentPane.add(getJpInfo(), java.awt.BorderLayout.CENTER);
		}
		return jContentPane;
	}
	/**
	 * This method initializes jpInfo	
	 * @return   javax.swing.JPanel
	 * @uml.property   name="jpInfo"
	 */    
	private JPanel getJpInfo() {
		if (jpInfo == null) {
			jpInfo = new JPanel();
			jpInfo.setLayout(new BorderLayout());
			jpInfo.add(getJScrollPane(), java.awt.BorderLayout.CENTER);
		}
		return jpInfo;
	}
	/**
	 * This method initializes jScrollPane	
	 * @return   javax.swing.JScrollPane
	 * @uml.property   name="jScrollPane"
	 */    
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getJtpInfo());
		}
		return jScrollPane;
	}
	/**
	 * This method initializes jtpInfo	
	 * @return   javax.swing.JTextPane
	 * @uml.property   name="jtpInfo"
	 */    
	private JTextPane getJtpInfo() {
		if (jtpInfo == null) {
			jtpInfo = new JTextPane();
		}
		return jtpInfo;
	}
	
	/**
	 * Escribe el texto de acuerdo al procesamiento
	 * @param texto
	 */
	public void  setInfo(String texto){
	    jtpInfo.setText(texto);
	}
   }
