/*
 * Created on 29-nov-2004
 * Derechos reservados @ 2004 David Acosta
 * <dac77@tutopia.com>
 * Este es un software libre; como tal redistribuirlo y/o modificarlo esta permitido, 
 * siempre y cuando se haga bajo los terminos y condiciones de la Licencia Publica General
 * GNU publicada por la Free Software Foundation, ya sea en su version 2 o cualquier otra
 * de las posteriores a la misma.
 */
package tools;

/**
 * Clase para convertir angulos De: gms a radianes y radianes a gms
 * 
 * @author David Acosta
 *  
 */
public class ConverAngulos {

	/**
	 * M&eacute;todo para convertir un &aacute;ngulo de gms a radianes
	 * 
	 * @param angulo
	 *            como gms
	 * @return angulo en radianes
	 */
	public double gmsToRad(double angulo) {
		double segundos = 0, minutos = 0, grados = 0;
		byte signo;

		if (angulo < 0) {
			angulo = angulo * (-1);
			signo = -1;
		} else
			signo = 1;

		String grad = "", min = "", seg = "";
		int cont = 0;
		String anguloStr = new String(angulo + "");
		int lon = anguloStr.length();
		int posPunto = anguloStr.lastIndexOf('.');

		if (cont < lon) {
			grad = anguloStr.substring(cont, posPunto);
		}
		cont = posPunto + 1;
		if ((cont + 2) <= lon) {
			min = anguloStr.substring(cont, cont + 2);
		} else if ((cont + 1) <= lon) {
			min = anguloStr.substring(cont);
			min = min + "0";
		}
		cont = cont + 2;
		if ((cont + 2) <= lon) {
			seg = anguloStr.substring(cont, cont + 2);
		} else if ((cont + 1) <= lon) {
			seg = anguloStr.substring(cont);
			seg = seg + "0";
		} else {
			seg = "0";
		}
		cont = cont + 2;
		if (cont <= lon) {
			seg = seg + ".";
			seg = seg + anguloStr.substring(cont, lon);
		}

		grados = Double.valueOf(grad).doubleValue();
		minutos = Double.valueOf(min).doubleValue();
		segundos = Double.valueOf(seg).doubleValue();

		if (minutos > 60) {
			return 0;
		}

		if (segundos > 60) {
			return 0;
		}

		return (grados + (minutos / (float) 60) + (segundos / (float) 3600))
				* (Math.PI / (float) 180) * signo;
	}

	/**
	 * M&eacute;todo para convertir un &aacute;ngulo de radianes a gms
	 * 
	 * @param angulo
	 *            en radianes
	 * @return angulo en gms
	 */
	public double radToGms(double angulo) {
		double anguloDecimal;
		double anguloGms;
		double grados = 0, minutos = 0, segundos = 0, segundosp = 0;
		boolean signo = false;

		anguloDecimal = angulo * (180d / Math.PI);
		if (anguloDecimal < 0) {
			signo = true;
			anguloDecimal = anguloDecimal * -1;
		} else
			signo = false;

		grados = Math.floor(anguloDecimal);
		minutos = Math.floor((anguloDecimal - grados) * 60);
		segundosp = ((anguloDecimal - grados) * 60) - minutos;

		segundos = segundosp * 60;

		if (segundos >= 59.99949) {
			segundos = 0;
			minutos++;
		}
		if (minutos >= 59.99949) {
			minutos = 0;
			grados++;
		}

		anguloGms = ((segundos / 100d) + minutos) / 100d + grados;
		if (signo == true)
			anguloGms = anguloGms * (-1);
		return anguloGms;
	}

}