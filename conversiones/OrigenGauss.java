/*
 * Derechos reservados @ 2004 David Acosta
 * <dac77@tutopia.com>
 * Este es un software libre; como tal redistribuirlo y/o modificarlo esta permitido, 
 * siempre y cuando se haga bajo los terminos y condiciones de la Licencia Publica General
 * GNU publicada por la Free Software Foundation, ya sea en su version 2 o cualquier otra
 * de las posteriores a la misma.
 */

package conversiones;

import tools.Clasificacion;
import tools.ConverAngulos;

/**
 * @author David Acosta
 *  
 */
public class OrigenGauss {

	/**
	 * Determina el origen correspondiente al Datum Bogot&aacute; segun la longitud
	 * @param longitud del punto en radianes
	 * @return or&iacute;gen Gauss
	 */
	public int getOrigen(double longitud) {
		if (-1.26677596994896 > longitud && longitud > -1.31913584750879)
			return Clasificacion.ORIGEN_GAUSS.ORIGEN_BOGOTA;
		else if (-1.31913584750879 > longitud && longitud > -1.37149572506862)
			return Clasificacion.ORIGEN_GAUSS.ORIGEN_OESTE;
		else if (-1.37149572506862 > longitud && longitud > -1.42385560262845)
			return Clasificacion.ORIGEN_GAUSS.ORIGEN_OESTE_OESTE;
		else if (longitud < -1.42385560262845)
			return Clasificacion.ORIGEN_GAUSS.ORIGEN_OESTE_OESTE;
		else if (-1.21441609238913 > longitud && longitud > -1.26677596994896)
			return Clasificacion.ORIGEN_GAUSS.ORIGEN_ESTE;
		else if (-1.1620562148293 > longitud && longitud > -1.21441609238913)
			return Clasificacion.ORIGEN_GAUSS.ORIGEN_ESTE_ESTE;
		else if (longitud > -1.1620562148293)
			return Clasificacion.ORIGEN_GAUSS.ORIGEN_ESTE_ESTE;
		else
			return -1;
	}

	/**
	 * Devuelve la longitud del punto segun el origen Gauss y el datum
	 * @param origen Gauss
	 * @param tipoDatum tipo de datum Bogot&aacute; o Magna
	 * @return longitud del or&iacute;gen en radianes
	 */
	public double getLongitud(int origen, int tipoDatum) {
		ConverAngulos tRad = new ConverAngulos();
		double tmpLong = 0;
		if (tipoDatum == Clasificacion.TIPO_DATUM.DATUM_BOGOTA) {
			switch (origen) {
			case Clasificacion.ORIGEN_GAUSS.ORIGEN_BOGOTA:
				tmpLong = tRad.gmsToRad(Double.valueOf("-74.045130")
						.doubleValue());
				break;
			case Clasificacion.ORIGEN_GAUSS.ORIGEN_ESTE:
				tmpLong = tRad.gmsToRad(Double.valueOf("-71.045130")
						.doubleValue());
				break;
			case Clasificacion.ORIGEN_GAUSS.ORIGEN_ESTE_ESTE:
				tmpLong = tRad.gmsToRad(Double.valueOf("-68.045130")
						.doubleValue());
				break;
			case Clasificacion.ORIGEN_GAUSS.ORIGEN_OESTE:
				tmpLong = tRad.gmsToRad(Double.valueOf("-77.045130")
						.doubleValue());
				break;
			case Clasificacion.ORIGEN_GAUSS.ORIGEN_OESTE_OESTE:
				tmpLong = tRad.gmsToRad(Double.valueOf("-80.045130")
						.doubleValue());
				break;
			default:
				tmpLong = 0;
				break;
			}
		}
		if (tipoDatum == Clasificacion.TIPO_DATUM.DATUM_MAGNA) {
			switch (origen) {
			case Clasificacion.ORIGEN_GAUSS.ORIGEN_BOGOTA:
				tmpLong = tRad.gmsToRad(Double.valueOf("-74.04390285")
						.doubleValue());
				break;
			case Clasificacion.ORIGEN_GAUSS.ORIGEN_ESTE:
				tmpLong = tRad.gmsToRad(Double.valueOf("-71.04390285")
						.doubleValue());
				break;
			case Clasificacion.ORIGEN_GAUSS.ORIGEN_ESTE_ESTE:
				tmpLong = tRad.gmsToRad(Double.valueOf("-68.04390285")
						.doubleValue());
				break;
			case Clasificacion.ORIGEN_GAUSS.ORIGEN_OESTE:
				tmpLong = tRad.gmsToRad(Double.valueOf("-77.04390285")
						.doubleValue());
				break;
			case Clasificacion.ORIGEN_GAUSS.ORIGEN_OESTE_OESTE:
				tmpLong = tRad.gmsToRad(Double.valueOf("-80.04390285")
						.doubleValue());
				break;
			default:
				tmpLong = 0;
				break;
			}
		}
		return tmpLong;
	}
	
	/**
	 * Devuelve la latitud del origen seg&uacute;n el datum de referencia
	 * @param tipoDatum tipo de datum Bogot&aacute; o Magna
	 * @return latitud del or&iacute;gen
	 */
	public double getLatitud(int tipoDatum){
		ConverAngulos tRad = new ConverAngulos();
		double latitudOrigen = 0;
		if (tipoDatum == Clasificacion.TIPO_DATUM.DATUM_BOGOTA)
            latitudOrigen = tRad.gmsToRad(Double.valueOf("4.355657").doubleValue());
        if (tipoDatum == Clasificacion.TIPO_DATUM.DATUM_MAGNA)
            latitudOrigen = tRad.gmsToRad(Double.valueOf("4.35463215").doubleValue());
        return latitudOrigen;
	}
}

