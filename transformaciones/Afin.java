/*
 * Created on 30-nov-2004
 * Derechos reservados @ 2004 David Acosta
 * <dac77@tutopia.com>
 * Este es un software libre; como tal redistribuirlo y/o modificarlo esta permitido, 
 * siempre y cuando se haga bajo los terminos y condiciones de la Licencia Publica General
 * GNU publicada por la Free Software Foundation, ya sea en su version 2 o cualquier otra
 * de las posteriores a la misma.
 */
package transformaciones;

/**
 * Transformaci&oacute;n bidimensional af&iacute;n para la refinaci&oacute;n de coordenadas
 * transformadas entre dos datum.
 * 
 * @author David Acosta
 *  
 */
public class Afin {

	double norteRefinada;
	double esteRefinada;

	/**
	 * Crea una nueva instancia de la clase
	 * 
	 * @param NorteD2
	 *            coordenada norte del punto
	 * @param EsteD2
	 *            coordenada este del punto
	 * @param paramA
	 *            par&aacute;metro de refinaci&oacute;n
	 * @param paramB
	 *            par&aacute;metro de refinaci&oacute;n
	 * @param paramC
	 *            par&aacute;metro de refinaci&oacute;n
	 * @param paramD
	 *            par&aacute;metro de refinaci&oacute;n
	 * @param paramE
	 *            par&aacute;metro de refinaci&oacute;n
	 * @param paramF
	 *            par&aacute;metro de refinaci&oacute;n
	 */
	public Afin(double NorteD2, double EsteD2, double paramA, double paramB,
			double paramC, double paramD, double paramE, double paramF) {

		esteRefinada = (paramA * EsteD2) + (paramB * NorteD2) + paramC;
		norteRefinada = -1 * (paramD * EsteD2) + (paramE * NorteD2) + paramF;
	}

	public double getEste() {
		return esteRefinada;
	}

	public double getNorte() {
		return norteRefinada;
	}
}