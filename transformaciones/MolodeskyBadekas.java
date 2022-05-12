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
import conversiones.GeocentricasInversa;

/**
 * Transformaci&oacute;n tridiemencional seg&uacute;n el modelo Molodesky-Badekas
 * 
 * @author David Acosta
 *  
 */
public class MolodeskyBadekas {
	double Dx1;
	double Dy1;
	double Dz1;

	double Rx1;
	double Ry1;
	double Rz1;

	double escala1;

	double XD2;
	double YD2;
	double ZD2;

	/**
	 * Crea una nueva instancia de la clase
	 * 
	 * @param XD1
	 *            Coordenada X geocentrica del punto
	 * @param YD1
	 *            Coordenada Y geocentrica del punto
	 * @param ZD1
	 *            Coordenada Z geocenrica del punto
	 * @param tipoCambio
	 *            tipo de transformaci&oacute;n
	 */
	public MolodeskyBadekas(double XD1, double YD1, double ZD1, int tipoCambio) {
		int tipoDatum;
		if (Clasificacion.TIPO_CAMBIO.TIPO_BOGOTA2MAGNA == tipoCambio)
			tipoDatum = Clasificacion.TIPO_DATUM.DATUM_BOGOTA;
		else
			tipoDatum = Clasificacion.TIPO_DATUM.DATUM_MAGNA;

		GeocentricasInversa geoInv = new GeocentricasInversa(tipoDatum, XD1,
				YD1, ZD1);
		IdentificaRegion idReg = new IdentificaRegion(geoInv.getLatitud(),
				geoInv.getLongitud());

		if (Clasificacion.TIPO_CAMBIO.TIPO_MAGNA2BOGOTA == tipoCambio) {
			// De MAGNA-SIRGAS a Datum BOGOTA
			Dx1 = -idReg.getDx();
			Dy1 = -idReg.getDy();
			Dz1 = -idReg.getDz();
			Rx1 = -idReg.getRx();
			Ry1 = -idReg.getRy();
			Rz1 = -idReg.getRz();
			escala1 = -idReg.getEscala();
		} else {
			Dx1 = idReg.getDx();
			Dy1 = idReg.getDy();
			Dz1 = idReg.getDz();
			Rx1 = idReg.getRx();
			Ry1 = idReg.getRy();
			Rz1 = idReg.getRz();
			escala1 = idReg.getEscala();
		}

		XD2 = idReg.getXo()
				+ Dx1
				+ (((XD1 - idReg.getXo()) * (1 + escala1))
						+ (Rz1 * (YD1 - idReg.getYo()) * (1 + escala1)) - (Ry1
						* (ZD1 - idReg.getZo()) * (1 + escala1)));
		YD2 = idReg.getYo() + Dy1 - Rz1 * (XD1 - idReg.getXo()) * (1 + escala1)
				+ (YD1 - idReg.getYo()) * (1 + escala1) + Rx1
				* (ZD1 - idReg.getZo()) * (1 + escala1);
		ZD2 = idReg.getZo() + Dz1 + Ry1 * (XD1 - idReg.getXo()) * (1 + escala1)
				- Rx1 * (YD1 - idReg.getYo()) * (1 + escala1)
				+ (ZD1 - idReg.getZo()) * (1 + escala1);
	}

	/**
	 * Devuelve el valor de la coordenada X transformada
	 * 
	 * @return X geocentrica transformada
	 */
	public double getX() {
		return XD2;
	}

	/**
	 * Devuelve el valor de la coordenada Y transformada
	 * 
	 * @return Y geocentrica transformada
	 */
	public double getY() {
		return YD2;
	}

	/**
	 * Devuelve el valor de la coordenada Z transformada
	 * 
	 * @return Z geocentrica transformada
	 */
	public double getZ() {
		return ZD2;
	}
}