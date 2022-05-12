/*
 * Created on 30-nov-2004
 * 
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
public class CartesianoDirecto {

	int tDatum;

	Cdatum datum = new Cdatum();

	private double nortePunto = 0 ;
	private double estePunto = 0 ;

	/**
	 * Crea una nueva instancia para convertir coordenadas planas cartesianas a
	 * partir de coordenadas geogr&aacute;ficas
	 * 
	 * @param tipoDatum
	 *            datum de referencia Bogot&aacute; o Magna
	 * @param latitudOrigen
	 *            latitud del punto de origen
	 * @param longitudOrigen
	 *            longitud del punto de origen
	 * @param norteCartesianoOrigen
	 *            coordenada cartesiana norte del punto
	 * @param esteCartesianoOrigen
	 *            coordenada cartesiana este del punto
	 * @param planoProyeccion
	 *            altura del plano de proyeccion
	 * @param latitudPunto
	 *            latitud del punto de interes en radianes
	 * @param longitudPunto
	 *            longitud del punto de interes en radianes
	 */
	public CartesianoDirecto(int tipoDatum, double latitudOrigen,
			double longitudOrigen, double norteCartesianoOrigen,
			double esteCartesianoOrigen, double planoProyeccion,
			double latitudPunto, double longitudPunto) {

		double nOrigen, mOrigen, nPunto, /*mPunto,*/ mMedia, latitudMedia, deltaLatRad, deltaLonRad, term1, term2;

		new setDatum(tipoDatum, datum);

		//		terminos auxiliares
		latitudMedia = (latitudOrigen + latitudPunto) / 2d;

		nOrigen = datum.getA()
				/ Math.sqrt(1d - datum.getE2()
						* Math.pow(Math.sin(latitudOrigen), 2d));

		mOrigen = (datum.getA() * (1d - datum.getE2()))
				/ (Math.pow((1d - datum.getE2()
						* Math.pow(Math.sin(latitudOrigen), 2d)),
						(3d / 2d)));

		nPunto = datum.getA()
				/ Math.sqrt(1 - datum.getE2()
						* Math.pow(Math.sin(latitudPunto), 2d));

		/*mPunto = (datum.getA() * (1d - datum.getE2()))
				/ (Math.pow((1d - datum.getE2()
						* Math.pow(Math.sin(latitudPunto), 2d)),
						(3d / 2d)));*/

		mMedia = (datum.getA() * (1d - datum.getE2()))
				/ (Math.pow(1d - datum.getE2()
						* Math.pow(Math.sin(latitudMedia), 2d),
						(3d / 2d)));

		deltaLatRad = latitudPunto - latitudOrigen;
		deltaLonRad = longitudPunto - longitudOrigen;

		//coordenadas cartesianas norte
		term1 = (Math.tan(latitudOrigen) * Math.pow(deltaLonRad, 2d)
				* Math.pow(nPunto, 2d) * Math.cos(latitudPunto) * Math
				.cos(latitudPunto))
				/ (2d * mOrigen * nOrigen);

		term2 = 1d + (planoProyeccion / mMedia);

		nortePunto = mOrigen * (deltaLatRad + term1) * term2
				+ norteCartesianoOrigen;

		//coordenadas cartesiana este
		term1 = deltaLonRad * nPunto * Math.cos(latitudPunto);
		term2 = 1d + (planoProyeccion / nOrigen);

		estePunto = (term1 * term2) + esteCartesianoOrigen;
	}

	/**
	 * @return   Returns the nortePunto.
	 * @uml.property   name="nortePunto"
	 */
	public double getNortePunto() {
		return nortePunto;
	}

	/**
	 * @return   Returns the estePunto.
	 * @uml.property   name="estePunto"
	 */
	public double getEstePunto() {
		return estePunto;
	}
}