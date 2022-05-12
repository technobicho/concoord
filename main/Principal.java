/*
 * Created on 02-dic-2004
* Derechos reservados @ 2004 David Acosta
 * <dac77@tutopia.com>
 * Este es un software libre; como tal redistribuirlo y/o modificarlo esta permitido, 
 * siempre y cuando se haga bajo los terminos y condiciones de la Licencia Publica General
 * GNU publicada por la Free Software Foundation, ya sea en su version 2 o cualquier otra
 * de las posteriores a la misma.
 */
package main;

import javax.swing.UIManager;
import frames.presentacion;

/**
 * Clase principal que invoca el programa
 * @author David Acosta
 * 
 */
public class Principal {

	public static void main(String[] args) {
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			System.out.println("Error setting native LAF: " + e);
		}
		new presentacion(null,false).setVisible(true);
	}
}