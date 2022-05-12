/*
 * Created on 09-dic-2004
 * Derechos reservados @ 2004 David Acosta
 * <dac77@tutopia.com>
 * Este es un software libre; como tal redistribuirlo y/o modificarlo esta permitido, 
 * siempre y cuando se haga bajo los terminos y condiciones de la Licencia Publica General
 * GNU publicada por la Free Software Foundation, ya sea en su version 2 o cualquier otra
 * de las posteriores a la misma.
 */
package main;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import tools.Clasificacion;
import tools.ConverAngulos;
import tools.FormatText;
import transformaciones.Afin;
import transformaciones.Bidimensional;
import transformaciones.MolodeskyBadekas;
import conversiones.CartesianoDirecto;
import conversiones.CartesianoInverso;
import conversiones.GaussDirecto;
import conversiones.GaussInverso;
import conversiones.GeocentricasDirecto;
import conversiones.GeocentricasInversa;
import conversiones.OrigenGauss;
import frames.ParAfinTrans;
import frames.ParCarteLocales;
import frames.SelecOrigenGauss;

/**
 * @author   david
 */
public class ProcesarPunto {

	String campo4 = "" ;
	String campo5 = "" ;
	String mensaje = "" ;

	double lat = 0 ;
	double lon = 0 ;
	double norte = 0 ;
	double este = 0 ;

	double latMaxAfin;
	double latMinAfin;
	double lonMaxAfin;
	double lonMinAfin;
	double paramA;
	double paramB;
	double paramC;
	double paramD;
	double paramE;
	double paramF;

	double latitudOrigenBogota = 0 ;
	double longitudOrigenBogota = 0 ;
	double latitudOrigenMagna = 0 ;
	double longitudOrigenMagna = 0 ;
	double norteCart = 0 ;
	double esteCart = 0 ;
	double planoProy = 0 ;

	int tipoDatum = 0;

	boolean transformacion = false;

	JFrame parent;

	/**
	 * Crea una nueva instancia de la clase
	 * 
	 * @param parent
	 *            JFrame padre
	 * @param tipoDatum
	 *            tipo de datum Bogot&aacute; o Magna
	 * @param tipoOperacion
	 *            tipo de operaci&oacute;n a realizar conversi&oacute;n o transformaci&oacute;n
	 * @param campo1
	 *            contenido del campo1 del JFrame padre
	 * @param campo2
	 *            contenido del campo2 del JFrame padre
	 * @param campo3
	 *            contenido del campo3 del JFrame padre
	 * @param transAfin
	 *            bandera para señalar si se realiza la transformaci&oacute;n af&iacute;n
	 */
	public ProcesarPunto(JFrame parent, int tipoDatum, int tipoOperacion,
			double campo1, double campo2, double campo3, boolean transAfin) {

		this.parent = parent;
		this.tipoDatum = tipoDatum;

		//seleccion del tipo de operacion
		switch (tipoOperacion) {
		case Clasificacion.TIPO_CONVERSION.TIPO_CARTESIANAS2GEOGRAFICAS:
			parametrosCartesianos();
			convCartesianasToGeograficas(campo1, campo2);
			break;
		case Clasificacion.TIPO_CONVERSION.TIPO_GEOGRAFICAS2CARTESIANAS:
			parametrosCartesianos();
			convGeograficasToCartesianas(campo1, campo2);
			break;
		case Clasificacion.TIPO_CONVERSION.TIPO_GAUSS2GEOGRAFICAS:
			origenesGauss();
			convGaussToGeograficas(campo1, campo2);
			break;
		case Clasificacion.TIPO_CONVERSION.TIPO_GEOGRAFICAS2GAUSS:
			origenesGaussAut(campo2);
			convGeograficasToGauss(campo1, campo2);
			break;
		case Clasificacion.TIPO_CONVERSION.TIPO_CARTESIANAS2GAUSS:
			convCartesianasToGauss(campo1, campo2);
			break;
		case Clasificacion.TIPO_CONVERSION.TIPO_GAUSS2CARTESIANAS:
			convGaussToCartesianas(campo1, campo2);
			break;
		case Clasificacion.TIPO_TRANSFORMACION.TIPO_CARTESIANAS2CARTESIANAS:
			transformacion = true;
			transCartesianaToCartesiana(campo1, campo2, campo3);
			transformacionAfin(transAfin, false);
			break;
		case Clasificacion.TIPO_TRANSFORMACION.TIPO_CARTESIANAS2GEOGRAFICAS:
			transformacion = true;
			transCartesianaToGeograficas(campo1, campo2, campo3);
			break;
		case Clasificacion.TIPO_TRANSFORMACION.TIPO_CARTESIANAS2GAUSS:
			transformacion = true;
			transCartesianaToGauss(campo1, campo2, campo3);
			transformacionAfin(transAfin, true);
			break;
		case Clasificacion.TIPO_TRANSFORMACION.TIPO_GAUSS2CARTESIANAS:
			transformacion = true;
			transGaussToCartesianas(campo1, campo2, campo3);
			transformacionAfin(transAfin, false);
			break;
		case Clasificacion.TIPO_TRANSFORMACION.TIPO_GAUSS2GAUSS:
			transformacion = true;
			transGaussToGauss(campo1, campo2, campo3);
			transformacionAfin(transAfin, true);
			break;
		case Clasificacion.TIPO_TRANSFORMACION.TIPO_GAUSS2GEOGRAFICAS:
			transformacion = true;
			transGaussToGeograficas(campo1, campo2, campo3);
			break;
		case Clasificacion.TIPO_TRANSFORMACION.TIPO_GEOGRAFICAS2CARTESIANAS:
			transformacion = true;
			transGeograficasToCartesianas(campo1, campo2, campo3);
			transformacionAfin(transAfin, false);
			break;
		case Clasificacion.TIPO_TRANSFORMACION.TIPO_GEOGRAFICAS2GAUSS:
			transformacion = true;
			transGeograficasToGauss(campo1, campo2, campo3);
			transformacionAfin(transAfin, true);
			break;
		case Clasificacion.TIPO_TRANSFORMACION.TIPO_GEOGRAFICAS2GEOGRAFICAS:
			transformacion = true;
			transGeograficasToGeograficas(campo1, campo2, campo3);
			break;
		default:
			break;
		}
	}

	/**
	 * Conversi&oacute;n de coordenadas Planas cartesianas a geogr&aacute;ficas
	 * 
	 * @param nortePunto
	 *            coordenada norte del punto
	 * @param estePunto
	 *            coordenada este del punto
	 */
	private void convCartesianasToGeograficas(double nortePunto,
			double estePunto) {
		CartesianoInverso cartInv;
		//calcula las coordenadas
		if (tipoDatum == Clasificacion.TIPO_DATUM.DATUM_BOGOTA) {
			cartInv = new CartesianoInverso(tipoDatum, latitudOrigenBogota,
					longitudOrigenBogota, norteCart, esteCart, planoProy,
					nortePunto, estePunto);
		} else {
			cartInv = new CartesianoInverso(tipoDatum, latitudOrigenMagna,
					longitudOrigenMagna, norteCart, esteCart, planoProy,
					nortePunto, estePunto);
		}
		//devuelve el valor calculado
		lat = cartInv.getLatitud();
		lon = cartInv.getLongitud();
		campo4 = formatAng(cartInv.getLatitud());
		campo5 = formatAng(cartInv.getLongitud());
		mensaje = mensaje
				+ "**Conversi\u00f3n Coordenadas Planas Cartesianas a Geograficas\n";
	}

	/**
	 * Conversi&oacute;n de coordenadas Geogr&aacute;ficas a Planas Cartesianas
	 * 
	 * @param latitudPunto
	 *            latitud del punto
	 * @param longitudPunto
	 *            longitud del punto
	 */
	private void convGeograficasToCartesianas(double latitudPunto,
			double longitudPunto) {
		CartesianoDirecto cartDirec;
		//      calcula las coordenadas
		if (tipoDatum == Clasificacion.TIPO_DATUM.DATUM_BOGOTA) {
			cartDirec = new CartesianoDirecto(tipoDatum, latitudOrigenBogota,
					longitudOrigenBogota, norteCart, esteCart, planoProy,
					latitudPunto, longitudPunto);
		} else {
			cartDirec = new CartesianoDirecto(tipoDatum, latitudOrigenMagna,
					longitudOrigenMagna, norteCart, esteCart, planoProy,
					latitudPunto, longitudPunto);
		}
		//      devuelve el valor calculado
		norte = cartDirec.getNortePunto();
		este = cartDirec.getEstePunto();
		campo4 = formatNum(cartDirec.getNortePunto());
		campo5 = formatNum(cartDirec.getEstePunto());
		mensaje = mensaje
				+ "**Conversi\u00f3n Coordenadas Geograficas a Planas Cartesianas\n";
	}

	/**
	 * Conversi&oacute;n de coordenadas Planas Gauss-Kr&uuml;ger a Geogr&aacute;ficas
	 * 
	 * @param nortePunto
	 *            coordenada norte del punto
	 * @param estePunto
	 *            coordenada este del punto
	 */
	private void convGaussToGeograficas(double nortePunto, double estePunto) {
		GaussInverso gInvert;
		//      realiza la conversion de coordenadas
		if (tipoDatum == Clasificacion.TIPO_DATUM.DATUM_BOGOTA) {
			gInvert = new GaussInverso(Clasificacion.TIPO_DATUM.DATUM_BOGOTA,
					latitudOrigenBogota, longitudOrigenBogota, nortePunto,
					estePunto);
		} else {
			gInvert = new GaussInverso(Clasificacion.TIPO_DATUM.DATUM_MAGNA,
					latitudOrigenMagna, longitudOrigenMagna, nortePunto,
					estePunto);
		}
		//Devuelve el valor calculado y formateado
		lat = gInvert.getLatitud();
		lon = gInvert.getLongitud();
		campo4 = formatAng(gInvert.getLatitud());
		campo5 = formatAng(gInvert.getLongitud());
		mensaje = mensaje
				+ "**Conversi\u00f3n coordenadas Planas Gauss a Geograficas\n";
	}

	/**
	 * Conversi&oacute;n de coordenadas Geogr&aacute;ficas a Planas Gauss-Kr&uuml;ger
	 * 
	 * @param latitudPunto
	 *            latitud del punto
	 * @param longitudPunto
	 *            longitud del punto
	 */
	private void convGeograficasToGauss(double latitudPunto,
			double longitudPunto) {
		GaussDirecto gdirect;
		//realiza la conversion de coordenadas
		if (tipoDatum == Clasificacion.TIPO_DATUM.DATUM_BOGOTA) {
			gdirect = new GaussDirecto(Clasificacion.TIPO_DATUM.DATUM_BOGOTA,
					latitudOrigenBogota, longitudOrigenBogota, latitudPunto,
					longitudPunto);
		} else {
			gdirect = new GaussDirecto(Clasificacion.TIPO_DATUM.DATUM_MAGNA,
					latitudOrigenMagna, longitudOrigenMagna, latitudPunto,
					longitudPunto);
		}
		//Devuelve el valor calculado y formateado
		norte = gdirect.getNorte();
		este = gdirect.getEste();
		campo4 = formatNum(gdirect.getNorte());
		campo5 = formatNum(gdirect.getEste());
		mensaje = mensaje
				+ "**Conversi\u00f3n de coordenadas Geograficas a Planas Gauss\n";
	}

	/**
	 * Conversi&oacute;n de coordenadas Planas cartesianas a Planas Gauss-Kr&uuml;ger
	 * 
	 * @param nortePunto
	 *            coordenada norte del punto
	 * @param estePunto
	 *            coordenada este del punto
	 */
	private void convCartesianasToGauss(double nortePunto, double estePunto) {
		campo4 = "";
		campo5 = "";
		parametrosCartesianos();
		convCartesianasToGeograficas(nortePunto, estePunto);
		origenesGaussAut(lon);
		convGeograficasToGauss(lat, lon);
		mensaje = mensaje
				+ "**Conversi\u00f3n de coordenadas Planas cartesianas a Planas Gauss\n";
	}

	/**
	 * Conversi&oacute;n de coordenadas Gauss-Kr&uuml;ger a Planas Cartesianas
	 * 
	 * @param nortePunto
	 *            coordenada norte del punto
	 * @param estePunto
	 *            coordenada este del punto
	 */
	private void convGaussToCartesianas(double nortePunto, double estePunto) {
		campo4 = "";
		campo5 = "";
		origenesGauss();
		convGaussToGeograficas(nortePunto, estePunto);
		parametrosCartesianos();
		convGeograficasToCartesianas(lat, lon);
		mensaje = mensaje
				+ "**Conversi\u00f3n de coordenadas Planas Gauss a Planas Cartesianas\n";
	}

	/**
	 * Transformaci&oacute;n de coordenadas Planas Cartesianas a Planas Cartesianas
	 * 
	 * @param nortePunto
	 *            coordenada norte del punto
	 * @param estePunto
	 *            coordenada este del punto
	 * @param altura
	 *            del punto
	 */
	private void transCartesianaToCartesiana(double nortePunto,
			double estePunto, double altura) {
		campo4 = "";
		campo5 = "";
		//seleccion de parametros cartesianos
		parametrosCartesianos();
		//convierte de coordenadas planas cartesianas a geograficas
		convCartesianasToGeograficas(nortePunto, estePunto);
		//verifica si hay altura para emplear la transformacion
		// Molodesky-Badekas
		if (altura != 0) {
			transMolodeskyBadekas(altura);
			//conversion de coordenadas geograficas a Planas cartesianas
			convGeograficasToCartesianas(lat, lon);
		} else {
			//transformacion bidimensional
			transBidimensional();
			convGeograficasToCartesianas(lat, lon);
		}
		mensaje = mensaje
				+ "**Transformaci\u00f3n de coordenadas Planas Cartesianas a Planas Cartesianas\n";
	}

	/**
	 * Realiza la transformaci&oacute;n de coordenadas Planas Cartesianas a Geogr&aacute;ficas
	 * 
	 * @param nortePunto
	 *            coordenada norte del punto
	 * @param estePunto
	 *            coordenada este del punto
	 * @param altura
	 *            del punto
	 */
	private void transCartesianaToGeograficas(double nortePunto,
			double estePunto, double altura) {
		campo4 = "";
		campo5 = "";
		//seleccion de parametros cartesianos
		parametrosCartesianos();
		//convierte de coordenadas planas cartesianas a geograficas
		convCartesianasToGeograficas(nortePunto, estePunto);
		//verifica si hay altura para emplear la transformacion
		// Molodesky-Badekas
		if (altura != 0) {
			transMolodeskyBadekas(altura);
			campo4 = formatAng(lat);
			campo5 = formatAng(lon);
		} else {
			//transformacion bidimensional
			transBidimensional();
			campo4 = formatAng(lat);
			campo5 = formatAng(lon);
		}
		mensaje = mensaje
				+ "**Transformaci\u00f3n de coordenadas Planas Cartesianas a Geograficas\n";
	}

	/**
	 * Realiza la transformaci&oacute;n de coordenadas Planas cartesianas a Planas
	 * Gauss
	 * 
	 * @param nortePunto
	 *            coordenada norte del punto
	 * @param estePunto
	 *            coordenada este del punto
	 * @param altura
	 *            del punto
	 */
	private void transCartesianaToGauss(double nortePunto, double estePunto,
			double altura) {
		campo4 = "";
		campo5 = "";
		//seleccion de parametros cartesianos
		parametrosCartesianos();
		//convierte de coordenadas planas cartesianas a geograficas
		convCartesianasToGeograficas(nortePunto, estePunto);
		//verifica si hay altura para emplear la transformacion
		// Molodesky-Badekas
		if (altura != 0) {
			transMolodeskyBadekas(altura);
			//seleccion de origen gauss
			origenesGaussAut(lon);
			//conversion de coordenadas geograficas a Planas cartesianas
			convGeograficasToGauss(lat, lon);
		} else {
			//transformacion bidimensional
			transBidimensional();
			//          seleccion de origen gauss
			origenesGaussAut(lon);
			//conversion de coordenadas geograficas a Planas cartesianas
			convGeograficasToGauss(lat, lon);
		}
		mensaje = mensaje
				+ "**Transformaci\u00f3n de coordenadas Planas Cartesianas a Planas Gauss\n";
	}

	/**
	 * Realiza la transformaci&oacute;n de coordenadas Planas Gauss a Planas
	 * Cartesianas
	 * 
	 * @param nortePunto
	 *            coordenada norte del punto
	 * @param estePunto
	 *            coordenada este del punto
	 * @param altura
	 *            del punto
	 */
	private void transGaussToCartesianas(double nortePunto, double estePunto,
			double altura) {
		campo4 = "";
		campo5 = "";
		//      seleccion de origen gauss
		origenesGauss();
		//conversion de coordenadas gauss a geograficas
		convGaussToGeograficas(nortePunto, estePunto);
		if (altura != 0) {
			transMolodeskyBadekas(altura);
			//          parametros cartesianos
			parametrosCartesianos();
			//          conversion de coordenadas geograficas a cartesianas
			convGeograficasToCartesianas(lat, lon);
		} else {
			//          transformacion bidimensional
			transBidimensional();
			//parametros cartesianos
			parametrosCartesianos();
			//conversion de coordenadas geograficas a cartesianas
			convGeograficasToCartesianas(lat, lon);
		}
		mensaje = mensaje
				+ "**Transformaci\u00f3n de coordenadas Planas Gauss a coordenadas Planas Cartesianas\n";

	}

	/**
	 * Realiza la transformaci&oacute;n de coordenadas Planas Gauss a planas Gauss
	 * 
	 * @param nortePunto
	 *            coordenada norte del punto
	 * @param estePunto
	 *            coordenada este del punto
	 * @param altura
	 *            del punto
	 */
	private void transGaussToGauss(double nortePunto, double estePunto,
			double altura) {
		campo4 = "";
		campo5 = "";
		//      seleccion de origen gauss
		origenesGauss();
		//conversion de coordenadas gauss a geograficas
		convGaussToGeograficas(nortePunto, estePunto);
		if (altura != 0) {
			transMolodeskyBadekas(altura);
			//          conversion de coordenadas geograficas a gauss
			convGeograficasToGauss(lat, lon);
		} else {
			//          transformacion bidimensional
			transBidimensional();
			//conversion de coordenadas geograficas a gauss
			convGeograficasToGauss(lat, lon);
		}
		mensaje = mensaje
				+ "**Transformaci\u00f3n de coordenadas Planas Gauss o Planas Gauss\n";
	}

	/**
	 * Realiza la transformaci&oacute;n de coordenadas Planas Gauss a Geogr&aacute;ficas
	 * 
	 * @param nortePunto
	 *            coordenada norte del punto
	 * @param estePunto
	 *            coordenada este del punto
	 * @param altura
	 *            del punto
	 */
	private void transGaussToGeograficas(double nortePunto, double estePunto,
			double altura) {
		campo4 = "";
		campo5 = "";
		//      seleccion de origen gauss
		origenesGauss();
		//conversion de coordenadas gauss a geograficas
		convGaussToGeograficas(nortePunto, estePunto);

		if (altura != 0) {
			transMolodeskyBadekas(altura);
			campo4 = formatAng(lat);
			campo5 = formatAng(lon);
		} else {
			//          transformacion bidimensional
			transBidimensional();
			campo4 = formatAng(lat);
			campo5 = formatAng(lon);
		}
		mensaje = mensaje
				+ "**Transformaci\u00f3n de coordenadas planas Gauss a Geograficas\n";
	}

	/**
	 * Realiza la transformaci&oacute;n de coordenadas Geogr&aacute;ficas a Planas Cartesianas
	 * 
	 * @param latitudPunto
	 *            latitud del punto en radianes
	 * @param longitudPunto
	 *            longitud del punto en radianes
	 * @param altura
	 *            del punto
	 */
	private void transGeograficasToCartesianas(double latitudPunto,
			double longitudPunto, double altura) {
		campo4 = "";
		campo5 = "";
		lat = latitudPunto;
		lon = longitudPunto;
		if (altura != 0) {
			transMolodeskyBadekas(altura);
			//          parametros cartesianos
			parametrosCartesianos();
			//conversion de coordenadas geograficas a cartesianas
			convGeograficasToCartesianas(lat, lon);
		} else {
			//          transformacion bidimensional
			transBidimensional();
			//          parametros cartesianos
			parametrosCartesianos();
			//conversion de coordenadas geograficas a cartesianas
			convGeograficasToCartesianas(lat, lon);
		}
		mensaje = mensaje
				+ "**Transformaci\u00f3n de coordenadas Geograficas a Planas Cartesianas\n";
	}

	/**
	 * Realiza la transformaci&oacute;n de coordenadas Geogr&aacute;ficas a Planas Gauss
	 * 
	 * @param latitudPunto
	 *            latitud del punto en radianes
	 * @param longitudPunto
	 *            longitud del punto en radianes
	 * @param altura
	 *            del punto
	 */
	private void transGeograficasToGauss(double latitudPunto,
			double longitudPunto, double altura) {
		campo4 = "";
		campo5 = "";
		lat = latitudPunto;
		lon = longitudPunto;
		if (altura != 0) {
			transMolodeskyBadekas(altura);
			//          seleccion de origen gauss
			origenesGaussAut(lon);
			//          conversion de coordenadas gauss a geograficas
			convGeograficasToGauss(lat, lon);
		} else {
			//          transformacion bidimensional
			transBidimensional();
			//          seleccion de origen gauss
			origenesGaussAut(lon);
			//          conversion de coordenadas gauss a geograficas
			convGeograficasToGauss(lat, lon);
		}
		mensaje = mensaje
				+ "**Transformaci\u00f3n de coordenadas Geograficas a Planas Gauss\n";
	}

	/**
	 * Realiza la transformaci&oacute;n de coordenadas Geogr&aacute;ficas a Geogr&aacute;ficas
	 * 
	 * @param latitudPunto
	 *            latitud del punto en radianes
	 * @param longitudPunto
	 *            longitud del punto en radianes
	 * @param altura
	 *            del punto
	 */
	private void transGeograficasToGeograficas(double latitudPunto,
			double longitudPunto, double altura) {
		campo4 = "";
		campo5 = "";
		lat = latitudPunto;
		lon = longitudPunto;
		if (altura != 0) {
			transMolodeskyBadekas(altura);
			campo4 = formatAng(lat);
			campo5 = formatAng(lon);
		} else {
			//          transformacion bidimensional
			transBidimensional();
			campo4 = formatAng(lat);
			campo5 = formatAng(lon);
		}
		mensaje = mensaje
				+ "**Transformaci\u00f3n de coordenadas Geograficas a Geograficas\n";
	}

	/**
	 * Realiza la transformaci&oacute;n Molodesky - Badekas
	 * 
	 * @param altura
	 *            elipsoidal
	 */
	private void transMolodeskyBadekas(double altura) {
		int tipoCambio = 0;

		//determinacion del tipo de transformacion
		if (tipoDatum == Clasificacion.TIPO_DATUM.DATUM_BOGOTA) {
			tipoCambio = Clasificacion.TIPO_CAMBIO.TIPO_BOGOTA2MAGNA;
		} else {
			tipoCambio = Clasificacion.TIPO_CAMBIO.TIPO_MAGNA2BOGOTA;
		}

		//      conversion de coordenadas geograficas a geocentricas
		GeocentricasDirecto gd = new GeocentricasDirecto(tipoDatum, lat, lon,
				altura);

		//transformacion Molodesky-Badekas
		MolodeskyBadekas mb = new MolodeskyBadekas(gd.getX(), gd.getY(), gd
				.getZ(), tipoCambio);

		if (tipoCambio == Clasificacion.TIPO_CAMBIO.TIPO_BOGOTA2MAGNA) {
			tipoDatum = Clasificacion.TIPO_DATUM.DATUM_MAGNA;
		} else {
			tipoDatum = Clasificacion.TIPO_DATUM.DATUM_BOGOTA;
		}

		//conversion de coordenadas geocentricas a geograficas
		GeocentricasInversa gdInv = new GeocentricasInversa(tipoDatum, mb
				.getX(), mb.getY(), mb.getZ());

		lat = gdInv.getLatitud();
		lon = gdInv.getLongitud();
		mensaje = mensaje + "**Transformaci\u00f3n Molodesky - Badekas\n";
	}

	/**
	 * Realiza la transformaci&oacute;n bidimensional
	 *  
	 */
	private void transBidimensional() {
		int tipoCambio = 0;
		//determinacion del tipo de transformacion
		if (tipoDatum == Clasificacion.TIPO_DATUM.DATUM_BOGOTA) {
			tipoCambio = Clasificacion.TIPO_CAMBIO.TIPO_BOGOTA2MAGNA;
		} else {
			tipoCambio = Clasificacion.TIPO_CAMBIO.TIPO_MAGNA2BOGOTA;
		}

		//      transformacion bidimensional
		Bidimensional bd = new Bidimensional(lat, lon, tipoCambio);

		//Modifica el datum despues de la transformacion
		if (tipoDatum == Clasificacion.TIPO_DATUM.DATUM_BOGOTA) {
			tipoDatum = Clasificacion.TIPO_DATUM.DATUM_MAGNA;
		} else {
			tipoDatum = Clasificacion.TIPO_DATUM.DATUM_BOGOTA;
		}

		lat = bd.getLatitud();
		lon = bd.getLongitud();
		mensaje = mensaje + "**Transformaci\u00f3n Bidimensional\n";
	}

	/**
	 * Formatea el &aacute;ngulo para la salida gr&aacute;fica
	 * 
	 * @param numero
	 *            a formatear
	 * @return angulo ya formateado
	 */
	private String formatAng(double numero) {
		FormatText fText = new FormatText();
		ConverAngulos ca = new ConverAngulos();
		numero = ca.radToGms(numero);
		return fText.formatTextAngulo(numero, transformacion);
	}

	/**
	 * Formatea el valor de la coordenada
	 * 
	 * @param numero
	 *            a formatear
	 * @return numero formateado a "###.000"
	 */
	private String formatNum(double numero) {
		FormatText fText = new FormatText();
		return fText.formatTextCoord(numero, transformacion);
	}

	/**
	 * Muestra y selecciona los par&aacute;metros cartesianos
	 * 
	 * @return void
	 */
	private void parametrosCartesianos() {
		//      crea una nueva interfaz de seleccion de parametros de transformacion
		// cartesianos
		ParCarteLocales pcLocal = new ParCarteLocales(parent, true);
		pcLocal.setVisible(true);

		latitudOrigenBogota = pcLocal.getLatitudBogota();
		longitudOrigenBogota = pcLocal.getLongitudBogota();

		latitudOrigenMagna = pcLocal.getLatitudMagna();
		longitudOrigenMagna = pcLocal.getLongitudMagna();

		norteCart = pcLocal.getNorteCartesiano();
		esteCart = pcLocal.getEsteCartesiano();
		planoProy = pcLocal.getPlanoProyeccion();
	}

	/**
	 * Muestra y selecciona los par&aacute;metros Af&iacute;n
	 *  
	 */
	private void parametrosAfin(boolean siGauss) {

		//crea una nueva instancia de seleccion de parametros de transformacion
		//Afin
		ParAfinTrans nparAfin = new ParAfinTrans(parent, true, siGauss);
		nparAfin.setVisible(true);

		latMaxAfin = nparAfin.getLatMax();
		latMinAfin = nparAfin.getLatMin();
		lonMaxAfin = nparAfin.getLonMax();
		lonMinAfin = nparAfin.getLonMin();
		paramA = nparAfin.getParamA();
		paramB = nparAfin.getParamB();
		paramC = nparAfin.getParamC();
		paramD = nparAfin.getParamD();
		paramE = nparAfin.getParamE();
		paramF = nparAfin.getParamF();
	}

	/**
	 * Selecci&oacute;n del origen gauss, en la conversi&oacute;n Gauss-Kr&uuml;ger a Geogr&aacute;ficas
	 * 	 
	 * @return void
	 */
	private void origenesGauss() {
		int origenGauss = 0;
		//      Crea una nueva interfas de seleccion de origenes
		SelecOrigenGauss selOrgGauss = new SelecOrigenGauss(parent, true, false);
		selOrgGauss.setVisible(true);
		//      crea un objeto para la determinacion del origen
		OrigenGauss orGauss = new OrigenGauss();
		origenGauss = selOrgGauss.getSeleccion();

		longitudOrigenBogota = orGauss.getLongitud(origenGauss,
				Clasificacion.TIPO_DATUM.DATUM_BOGOTA);
		latitudOrigenBogota = orGauss
				.getLatitud(Clasificacion.TIPO_DATUM.DATUM_BOGOTA);

		longitudOrigenMagna = orGauss.getLongitud(origenGauss,
				Clasificacion.TIPO_DATUM.DATUM_MAGNA);
		latitudOrigenMagna = orGauss
				.getLatitud(Clasificacion.TIPO_DATUM.DATUM_MAGNA);
		//identifica el origen empleado
		switch (origenGauss) {
		case 1:
			mensaje = mensaje + "Origen Gauss: Bogot\u00e1\n";
			break;
		case 2:
			mensaje = mensaje + "Origen Gauss: Este\n";
			break;
		case 3:
			mensaje = mensaje + "Origen Gauss: Oeste\n";
			break;
		case 4:
			mensaje = mensaje + "Origen Gauss: Este-Este\n";
			break;
		case 5:
			mensaje = mensaje + "Origen Gauss: Oeste-Oeste\n";
			break;
		default:
			mensaje = mensaje + "Origen Gauss: no determinado\n";
			break;
		}

	}

	/**
	 * Selecci&oacute;n del origen gauss, en la conversi&oacute;n geogr&aacute;ficas a Gauss-Kr&uuml;ger
	 * 
	 * @param longitudPunto
	 */
	private void origenesGaussAut(double longitudPunto) {
		int origenGauss = 0;
		//      Crea una nueva interfas de seleccion de origenes
		SelecOrigenGauss selOrgGauss = new SelecOrigenGauss(parent, true, true);
		selOrgGauss.setVisible(true);
		//      crea un objeto para la determinacion del origen
		OrigenGauss orGauss = new OrigenGauss();
		//seleccion del origen Gauss
		if (selOrgGauss.getSeleccion() == 6) {
			origenGauss = orGauss.getOrigen(longitudPunto);
		} else {
			origenGauss = selOrgGauss.getSeleccion();
		}

		longitudOrigenBogota = orGauss.getLongitud(origenGauss,
				Clasificacion.TIPO_DATUM.DATUM_BOGOTA);
		latitudOrigenBogota = orGauss
				.getLatitud(Clasificacion.TIPO_DATUM.DATUM_BOGOTA);

		longitudOrigenMagna = orGauss.getLongitud(origenGauss,
				Clasificacion.TIPO_DATUM.DATUM_MAGNA);
		latitudOrigenMagna = orGauss
				.getLatitud(Clasificacion.TIPO_DATUM.DATUM_MAGNA);
		//      identifica el origen empleado
		switch (origenGauss) {
		case 1:
			mensaje = mensaje + "Origen Gauss: Bogot\u00e1\n";
			break;
		case 2:
			mensaje = mensaje + "Origen Gauss: Este\n";
			break;
		case 3:
			mensaje = mensaje + "Origen Gauss: Oeste\n";
			break;
		case 4:
			mensaje = mensaje + "Origen Gauss: Este-Este\n";
			break;
		case 5:
			mensaje = mensaje + "Origen Gauss: Oeste-Oeste\n";
			break;
		default:
			mensaje = mensaje + "Origen Gauss: no determinado\n";
			break;
		}
	}

	/**
	 * Realiza la transformaci&oacute;n af&iacute;n
	 * 
	 * @param transAfin
	 *            bandera de indicaci&oacute;n true: realiza el refinamiento false: no
	 *            realiza el refinamiento
	 */
	private void transformacionAfin(boolean transAfin, boolean siGauss) {

		if (transAfin == true) {
			parametrosAfin(siGauss);
			if (((lonMinAfin < lon) && (lonMaxAfin > lon))
					&& ((latMinAfin < lat) && (latMaxAfin > lat))) {
				Afin nafin = new Afin(norte, este, paramA, paramB, paramC,
						paramD, paramE, paramF);
				campo4 = formatNum(nafin.getNorte());
				campo5 = formatNum(nafin.getEste());
				mensaje = mensaje + "**Transformaci\u00f3n - Af\u00edn";
			} else {
				JOptionPane
						.showMessageDialog(
								parent,
								"Transformaci\u00f3n Af\u00edn no empleada\n"
										+ "Coordenadas fuera de rango en los par\u00e1metros af\u00edn",
								"Informaci\u00f3n", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	/**
	 * Retorna el valor del campo4 en el JFrame padre
	 * @return   valor del campo4
	 * @uml.property   name="campo4"
	 */
	public String getCampo4() {
		return campo4;
	}

	/**
	 * Retorna el valor del campo5 en el JFrame padre
	 * @return   valor del campo5
	 * @uml.property   name="campo5"
	 */
	public String getCampo5() {
		return campo5;
	}

	/**
	 * Retorna el mensaje de la ventana de informaci&oacute;n
	 * @return   mensaje de informacion
	 * @uml.property   name="mensaje"
	 */
	public String getMensaje() {
		return mensaje;
	}
}