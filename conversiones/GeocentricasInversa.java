/*
 * Created on 30-nov-2004
 * Derechos reservados @ 2004 David Acosta
 * <dac77@tutopia.com>
 * Este es un software libre; como tal redistribuirlo y/o modificarlo esta permitido, 
 * siempre y cuando se haga bajo los terminos y condiciones de la Licencia Publica General
 * GNU publicada por la Free Software Foundation, ya sea en su version 2 o cualquier otra
 * de las posteriores a la misma.
 */
package conversiones;

import tools.Cdatum;

/**
 * @author   david
 */
public class GeocentricasInversa {

	int tDatum;

	Cdatum datum = new Cdatum();

	private double latitudPunto = 0 ;
	private double longitudPunto = 0 ;
	private double alturaElipsoidal = 0 ;

	/**
	 * Crea una nueva instancia de la clase
	 * 
	 * @param tipoDatum
	 *            tipo de datum Bogot&aacute; o Magna
	 * @param X
	 *            coordenada X geocentrica
	 * @param Y
	 *            coordenada Y geocentrica
	 * @param Z
	 *            coordenada Z geocentrica
	 */
	public GeocentricasInversa(int tipoDatum, double X, double Y, double Z) {
		double Theta = 0, NPunto = 0;
		new setDatum(tipoDatum, datum);

		Theta = Math.atan((Z * datum.getA())
				/ (datum.getB() * Math.sqrt(Math.pow(X, 2d) + Math.pow(Y, 2d))));

		latitudPunto = Math.atan((Z + (datum.getE12() * datum.getB() * Math
				.pow(Math.sin(Theta), 3d)))
				/ (Math.sqrt(Math.pow(X, 2d) + Math.pow(Y, 2d)) - (datum.getE2()
						* datum.getA() * Math.pow(Math.cos(Theta), 3d))));

		longitudPunto = Math.atan(Y / X);
		
		NPunto = datum.getA() / Math.sqrt(1d - datum.getE2() * Math.pow(Math.sin(latitudPunto),2d));

		alturaElipsoidal = (Math.sqrt(Math.pow(X, 2d) + Math.pow(Y, 2d)) / Math
				.cos(latitudPunto))
				- NPunto;
	}

	/**
	 * Devuelve el valor de la latitud
	 * 
	 * @return latitud del punto en radianes
	 */
	public double getLatitud() {
		return latitudPunto;
	}

	/**
	 * Devuelve el valor de la longitud
	 * 
	 * @return longitud del punto en radianes
	 */
	public double getLongitud() {
		return longitudPunto;
	}

	/**
	 * Devuelve el valor de la altura elipsoidal
	 * @return   altura elipsoidal del punto
	 * @uml.property   name="alturaElipsoidal"
	 */
	public double getAlturaElipsoidal() {
		return alturaElipsoidal;
	}
}