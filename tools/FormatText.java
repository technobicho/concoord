/*
 * Created on 30-nov-2004
 * Derechos reservados @ 2004 David Acosta
 * <dac77@tutopia.com>
 * Este es un software libre; como tal redistribuirlo y/o modificarlo esta permitido, 
 * siempre y cuando se haga bajo los terminos y condiciones de la Licencia Publica General
 * GNU publicada por la Free Software Foundation, ya sea en su version 2 o cualquier otra
 * de las posteriores a la misma.
 */
package tools;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase para el formateo de texto formateo de angulos y formateo de coordenadas
 * planas
 * 
 * @author David Acosta
 *  
 */
public class FormatText {

	/**
	 * M&eacute;todo que formatea un &aacute;ngulo gms de formato gg.mmssss a gg°mm'ss.ss"
	 * 
	 * @param numero
	 *            del &aacute;ngulo en formato gg.mmssss
	 * @param siTransformacion
	 *            flag que indica si ha echo una transformaci&oacute;n
	 * @return angulo en formato gg°mm'ss.ss"
	 */
	public String formatTextAngulo(double numero, boolean siTransformacion) {
		double grados = 0, minutos = 0, segundos = 0;
		boolean signo = false;
		String minutosp, segundosp;
		DecimalFormat myFormatter;

		if (numero < 0) {
			numero = numero * (-1);
			signo = true;
		} else
			signo = false;

		grados = Math.floor(numero);
		minutos = Math.floor((numero - grados) * 100);
		segundos = (numero - grados) * 100;
		segundos = segundos - minutos;
		segundos = segundos * 100;

		//verifica si hay transformacion y limita el numero de decimales
		if (siTransformacion)
			myFormatter = new DecimalFormat("00.000");
		else
			myFormatter = new DecimalFormat("00.000");

		String output = myFormatter.format(segundos);
		output = remplaceSeparadores(output);
		//		segundos = Double.valueOf(output).doubleValue();
		segundosp = output;

		if (signo == true)
			grados = grados * (-1);

		if (minutos < 10)
			minutosp = "0" + (int) minutos;
		else
			minutosp = (int) minutos + "";

		output = (int) grados + "\u00b0 " + minutosp + "\u00b4 " + segundosp + "\u201d";

		return output;
	}

	/**
	 * Remplaza la coma por un punto
	 * 
	 * @param cadena
	 *            numero como cadena de texto
	 * @return numero con separador decimal un punto
	 */
	private String remplaceSeparadores(String cadena) {
		Pattern patron = Pattern.compile(",");
		Matcher encaja = patron.matcher(cadena);
		return encaja.replaceAll(".");
	}

	/**
	 * Metodo que formatea el numero de decimales
	 * 
	 * @param numero
	 * @param siTransformacion
	 *            flag que indica si se ha echo una transformacion
	 * @return numero formateado "###.000"
	 */
	public String formatTextCoord(double numero, boolean siTransformacion) {
		DecimalFormat myFormatter;
		if (siTransformacion)
			myFormatter = new DecimalFormat("0.000");
		else
			myFormatter = new DecimalFormat("0.000");

		String output = myFormatter.format(numero);
		output = remplaceSeparadores(output);
		return output;
	}
	
	/**
	 * Metodo que formatea el numero de decimales
	 * @param numero &aacute;ngulo a formatear
	 * @return un string con el angulo formateado
	 */
	public String formatAnguloVelocidades(double numero){
		double grados = 0, minutos = 0, segundos = 0;
		boolean signo = false;
		String minutosp, segundosp;
		DecimalFormat myFormatter;

		if (numero < 0) {
			numero = numero * (-1);
			signo = true;
		} else
			signo = false;

		grados = Math.floor(numero);
		minutos = Math.floor((numero - grados) * 100);
		segundos = (numero - grados) * 100;
		segundos = segundos - minutos;
		segundos = segundos * 100;
		
		myFormatter = new DecimalFormat("00.00000");
		

		String output = myFormatter.format(segundos);
		output = remplaceSeparadores(output);
		//		segundos = Double.valueOf(output).doubleValue();
		segundosp = output;

		if (signo == true)
			grados = grados * (-1);

		if (minutos < 10)
			minutosp = "0" + (int) minutos;
		else
			minutosp = (int) minutos + "";

		output = (int) grados + "\u00b0 " + minutosp + "\u00b4 " + segundosp + "\u201d";

		return output;
	}
	
	/**
	 * Metodo que formatea el numero de decimales
	 * @param numero año a formatear
	 * @return string con el valor formateado a un decimal
	 */
	public String formatAnio(double numero){
		DecimalFormat myFormatter;
		myFormatter = new DecimalFormat("0.0");
		String output = myFormatter.format(numero);
		output = remplaceSeparadores(output);
		return output;
	}

	/**
	 * Metodo que formatea el numero de decimales en los angulos para los
	 * archivos de texto
	 * 
	 * @param numero
	 * @return numero formateado "###.#####"
	 */
	public double formatTextAngArchivo(double numero, boolean siTransformacion) {
		DecimalFormat myFormatter;
		if (siTransformacion)
			myFormatter = new DecimalFormat("0.00000");
		else
			myFormatter = new DecimalFormat("0.0000000");

		String output = myFormatter.format(numero);
		output = remplaceSeparadores(output);
		return Double.valueOf(output).doubleValue();
	}
}