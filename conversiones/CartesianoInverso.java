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
public class CartesianoInverso {

	int tDatum;

	Cdatum datum = new Cdatum();

	private double latitudPunto = 0 ;
	private double longitudPunto = 0 ;

	/**
	 * Crea una nueva instancia para convertir coordenadas planas cartesianas a coordenadas geogr&aacute;ficas
	 * @param tipoDatum datum de proyecci&oacute;n Bogot&aacute; o Magna
	 * @param latitudOrigen latitud del punto de origen
	 * @param longitudOrigen longitud del punto de origen
	 * @param norteCartesianoOrigen coordenada norte del origen
	 * @param esteCartesianoOrigen coordenada este del origen
	 * @param planoProyeccion altura del plano de proyecci&oacute;n
	 * @param nortePunto coordenada norte del punto de interes
	 * @param estePunto coordenada este del punto de interes
	 */
	public CartesianoInverso(int tipoDatum, double latitudOrigen,
			double longitudOrigen, double norteCartesianoOrigen,
			double esteCartesianoOrigen, double planoProyeccion,
			double nortePunto, double estePunto) {

		double nOrigen, mOrigen, nPunto, deltaLatRad, deltaLonRad;
		double deltaNorteCartesiana, deltaEsteCartesiana, term1a, term1b, term1, term2, term3;

		new setDatum(tipoDatum, datum);

		//		Terminos auxiliares
		nOrigen = datum.getA()
				/ Math.sqrt(1d - datum.getE2() * Math.sin(latitudOrigen)
						* Math.sin(latitudOrigen));

		mOrigen = (datum.getA() * (1d - datum.getE2()))
				/ (Math.pow(1d - datum.getE2() * Math.sin(latitudOrigen)
						* Math.sin(latitudOrigen), (3d / 2d)));

		deltaNorteCartesiana = nortePunto - norteCartesianoOrigen;
		deltaEsteCartesiana = estePunto - esteCartesianoOrigen;

		//calculo de la latitud del punto
		term1a = planoProyeccion / (datum.getA() * (1d - datum.getE2()));
		term1b = (1d + term1a) * mOrigen;
		term1 = deltaNorteCartesiana / term1b;
		term2 = Math.tan(latitudOrigen) / (2d * nOrigen * mOrigen);
		term3 = Math.pow(deltaEsteCartesiana
				/ (1d + (planoProyeccion / datum.getA())), 2d);

		deltaLatRad = term1 - (term2 * term3);
		latitudPunto = latitudOrigen + deltaLatRad;

		//calculo de la logitud del punto
		nPunto = datum.getA()
				/ Math.sqrt(1d - datum.getE2() * Math.sin(latitudPunto)
						* Math.sin(latitudPunto));

		term1 = nPunto * Math.cos(latitudPunto)
				* (1d + (planoProyeccion / datum.getA()));

		deltaLonRad = deltaEsteCartesiana / term1;
		longitudPunto = longitudOrigen + deltaLonRad;
	}
	
	/**
	 * Devuelve la latitud del punto de interes
	 * @return latitud del punto en radianes
	 */
	public double getLatitud(){
		return latitudPunto;
	}
	
	/**
	 * Devuelve la longitud del punto de interes
	 * @return longitud del punto en radianes
	 */
	public double getLongitud(){
		return longitudPunto;
	}

}