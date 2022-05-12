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

import tools.Clasificacion;
import tools.ConverAngulos;

/**
 * Transformaci&oacute;n bidimensional de coordenadas entre el Datum Bogot&aacute; y el
 * Sistema Magna-Sirgas a partir de coordenadas elipsoidales, utilizando los
 * par&aacute;metros oficiales del IGAC. La formulaci&oacute;n Matem&aacute;tica se encuentra en
 * Torge 2003, P. 215 o Heiskannen & Moritz, 1967, P. 208
 * 
 * @author David Acosta
 * 
 */
public class Bidimensional {
	
	private double latD2 = 0 ;
	private double lonD2 = 0 ;

	/**
	 * Crea una nueva instancia de la clase
	 * @param latD1 latitud del punto de interes
	 * @param lonD1 longitud del punto de interes
	 * @param tipoCambio tipo de transformaci&oacute;n
	 */
	public Bidimensional(double latD1, double lonD1, int tipoCambio) {
		double terminoUno, terminoDos, terminoTres, terminoCuatro;
		double terminoCinco, latBogotaPuntoDatum, lonBogotaPuntoDatum, dLon;
		final double GRS80A = 6378137;
		//final double GRS80B = 6356752.31414;
		final double GRS80F = 3.35281068118 * Math.pow(10, -3);
		//final double GRS80E2 = 6.6943800229 * Math.pow(10, -3);
		//final double GRS80E12 = 6.73949677548 * Math.pow(10, -3);
		final double IntA = 6378388;
		//final double IntB = 6356911.94613;
		final double IntF = 3.367003367 * Math.pow(10, -3);
		//final double IntE2 = 6.72267002233 * Math.pow(10, -3);
		//final double IntE12 = 6.76817019722 * Math.pow(10, -3);
		ConverAngulos convAng = new ConverAngulos();
		IdentificaRegion idReg = new IdentificaRegion(latD1, lonD1);
		double dphi = 0,dlambda = 0;
		
		latBogotaPuntoDatum = convAng.gmsToRad(Double.valueOf("4.355657")
				.doubleValue());
		lonBogotaPuntoDatum = convAng.gmsToRad(Double.valueOf("-74.045130")
				.doubleValue());
		dLon = lonD1 - lonBogotaPuntoDatum;

		if (Clasificacion.TIPO_CAMBIO.TIPO_MAGNA2BOGOTA == tipoCambio) {
			// De MAGNA-SIRGAS a Datum BOGOTA
			dphi = -idReg.getDphi();
			dlambda = -idReg.getDlambda();
		}else{
			dphi = idReg.getDphi();
			dlambda = idReg.getDlambda();
		}

		//transformacion de la latitud
		terminoUno = (Math.cos(latBogotaPuntoDatum) * Math.cos(latD1) + Math
				.sin(latBogotaPuntoDatum)
				* Math.sin(latD1) * Math.cos(dLon))
				* dphi;

		terminoDos = Math.sin(latD1) * Math.sin(dLon)
				* Math.cos(latBogotaPuntoDatum) * dlambda;

		terminoTres = Math.sin(latBogotaPuntoDatum) * Math.cos(latD1)
				- Math.cos(latBogotaPuntoDatum) * Math.sin(latD1)
				* Math.cos(dLon);

		terminoCuatro = ((GRS80A - IntA) / GRS80A)
				+ (Math.pow(Math.sin(latBogotaPuntoDatum), 2) * (GRS80F - IntF));

		terminoCinco = 2 * Math.cos(latD1)
				* (Math.sin(latD1) - Math.sin(latBogotaPuntoDatum))
				* (GRS80F - IntF);

		latD2 = (terminoUno - terminoDos + (terminoTres * terminoCuatro) + terminoCinco);
		latD2 = Math.toDegrees(latD1) + (latD2 / 3600);

		//Transformacion de la longitud
		terminoUno = Math.sin(latBogotaPuntoDatum) * Math.sin(dLon) * (dphi);
		terminoDos = Math.cos(dLon) * Math.cos(latBogotaPuntoDatum) * (dlambda);
		terminoTres = Math.cos(latBogotaPuntoDatum) * Math.sin(dLon);
		terminoCuatro = ((GRS80A - IntA) / GRS80A)
				+ (Math.pow(Math.sin(latBogotaPuntoDatum), 2) * (GRS80F - IntF));
		//terminoCinco = 1.0 / Math.cos(latD1);
		double lonTemp = 0;
		lonTemp = (terminoUno + terminoDos - terminoTres * terminoCuatro)
				/ Math.cos(latD1);
		lonD2 = Math.toDegrees(lonD1) + (lonTemp / 3600);
	}
	
	/**
	 * Devuelve el valor de la latitud transformada
	 * @return latitud transformada en radianes
	 */
	public double getLatitud(){
		return latD2 * (Math.PI / 180);
	}
	/**
	 * Devuelve el valor de la longitud transformada
	 * @return longitud transformada en radianes
	 */
	public double getLongitud(){
		return lonD2 * (Math.PI / 180);
	}
}