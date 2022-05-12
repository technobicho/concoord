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

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
//import javax.swing.Timer;

/**
 * Clase que maneja el splash screen para la presentaci&oacute;n de la
 * aplicaci&oacute;n
 * 
 * @author David Acosta
 *  
 */
public class presentacion extends JWindow {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4048791286259464248L;

	private Container cPresentacion;

	private JLabel jLabel1;

	//private Timer tiempo;

	/**
	 * Constructor de la clase
	 * @param parent Frame padre
	 * @param modal bandera para determinar si es modal
	 */
	public presentacion(Frame parent, boolean modal) {
		cPresentacion = getContentPane();
		cPresentacion.setLayout(new BorderLayout());
		crearComponentes();

		setSize(500, 357);

	}

	/**
	 * Metodo para inicializar todos los componentes
	 *@return void
	 */
	private void crearComponentes() {
		jLabel1 = new JLabel();

		jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel1.setIcon(new ImageIcon(getClass()
				.getResource("/icons/concoord.jpg")));
		cPresentacion.add("Center", jLabel1);

		//calcula la poscicion de la ventana
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension labelSize = jLabel1.getPreferredSize();
		setLocation(screenSize.width / 2 - (labelSize.width / 2),
				screenSize.height / 2 - (labelSize.height / 2));
		screenSize = null;
		labelSize = null;

		//maneja el tiempo de despliege
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				setVisible(false);
				dispose();
			}
		});
		final int pause = 3500;
		final Runnable closerRunner = new Runnable() {
			public void run() {
				setVisible(false);
				dispose();
			}
		};
		Runnable waitRunner = new Runnable() {
			public void run() {
				try {
					Thread.sleep(pause);
					SwingUtilities.invokeAndWait(closerRunner);
					framePrincipal();
				} catch (Exception e) {
					e.printStackTrace();
					// can catch InvocationTargetException
					// can catch InterruptedException
				}
			}
		};
		setVisible(true);
		Thread splashThread = new Thread(waitRunner, "SplashThread");
		splashThread.start();

	}

	/**
	 * Metodo que lanza el frame principal
	 *
	 */
	public void framePrincipal() {
		new FramePrincipal().setVisible(true);
	}

	public static void main(String args[]) {
		new presentacion(new JFrame(), true).setVisible(true);
	}
}