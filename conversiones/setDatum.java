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
import tools.Clasificacion;

/**
 * Establece los par&aacute;metros seg&uacute;n el datum
 * @author David Acosta
 * 
 */
public class setDatum {

	/**
	 * Crea una nueva instacia de la clase
	 * @param tipo de datum Bogot&aacute; o magna
	 * @param datum objeto datum
	 */
	public setDatum(int tipo,Cdatum datum){
		if (tipo == Clasificacion.TIPO_DATUM.DATUM_MAGNA) {
            datum.setA(6378137d);
            datum.setB(6356752.31414);
            datum.setF(3.35281068118 * (Math.pow(10d, -3d)));
            datum.setE2(6.6943800229 * Math.pow(10d, -3d));
            datum.setE12(6.73949677548 * Math.pow(10d, -3d));
            datum.setNorteEcuador(491767.5344);
        }
        if (tipo == Clasificacion.TIPO_DATUM.DATUM_BOGOTA) {
            datum.setA(6378388d);
            datum.setB(6356911.94613);
            datum.setF(3.367003367 * Math.pow(10d, -3d));
            datum.setE2(6.72267002233 * Math.pow(10d, -3d));
            datum.setE12(6.76817019722 * Math.pow(10d, -3d));
            datum.setNorteEcuador(491447.1557);
        }
	}
}
