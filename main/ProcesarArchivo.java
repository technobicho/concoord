/*
 * Created on 09-dic-2004
 * Derechos reservados @ 2004 David Acosta
 * <dac77@tutopia.com>
 * Este es un software libre; como tal redistribuirlo y/o modificarlo esta permitido, 
 * siempre y cuando se haga bajo los terminos y condiciones de la Licencia Publica General
 * GNU publicada por la Free Software Foundation, ya sea en su version 2 o cualquier otra
 * de las posteriores a la misma.
 */
package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ProgressMonitor;
import javax.swing.Timer;

/**
 * @author   david
 */
public class ProcesarArchivo {
    public final static int ONE_SECOND = 1;

    private ProgressMonitor progressMonitor;

    private Timer timer;

    private LongTask task;

    //variables globales
    double XPunto;
    //variables globales
    double YPunto;
    //variables globales
    double ZPunto;

    double XPuntoCal;
    double YPuntoCal;
    double ZPuntoCal;

    public ProcesarArchivo(JFrame principal, int formatoArchivo,
            int tipoProceso, int tipoDatum, boolean transAfin, File archivoLectura,
            File archivoEscritura) {
        //int tipoArchivo;
        task = new LongTask(principal, formatoArchivo, tipoProceso, tipoDatum, transAfin,
                archivoLectura, archivoEscritura);
        timer = new Timer(ONE_SECOND, new TimerListener());
        progressMonitor = new ProgressMonitor(principal, "Procesando Puntos",
                "", 0, task.getLengthOfTask());
        progressMonitor.setProgress(0);
        progressMonitor.setMillisToDecideToPopup(ONE_SECOND);
        task.go();
        timer.start();
    }

    class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            if (progressMonitor.isCanceled() || task.done()) {
                progressMonitor.close();
                task.stop();
                JOptionPane.showMessageDialog(null, "El proceso ha terminado!",
                        "Informaci\u00f3n", JOptionPane.INFORMATION_MESSAGE);
                timer.stop();
            } else {
                progressMonitor.setNote(task.getMessage());
                progressMonitor.setProgress(task.getCurrent());
            }
        }
    }
}