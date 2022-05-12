/*
 * Created on 30-nov-2004
 * Derechos reservados @ 2004 David Acosta
 * <dac77@tutopia.com>
 * Este es un software libre; como tal redistribuirlo y/o modificarlo esta permitido, 
 * siempre y cuando se haga bajo los terminos y condiciones de la Licencia Publica General
 * GNU publicada por la Free Software Foundation, ya sea en su version 2 o cualquier otra
 * de las posteriores a la misma.
 */
package tools;

/**
 * Clase de enumeracion de tipos
 * @author David Acosta
 * 
 */
public class Clasificacion {
	/**
	 * Clase para determinar el tipo de datum
	 */
	public class TIPO_DATUM {
		public static final int DATUM_MAGNA = 1;

		public static final int DATUM_BOGOTA = 2;
	}

	/**
	 * Clase para determinar el tipo de region
	 */
	public class TIPO_REGION {
		public static final int REGION_UNO = 1;

		public static final int REGION_DOS = 2;

		public static final int REGION_TRES = 3;

		public static final int REGION_CUATRO = 4;

		public static final int REGION_CINCO = 5;

		public static final int REGION_SEIS = 6;

		public static final int REGION_SIETE = 7;

		public static final int REGION_OCHO = 8;
	}

	/**
	 * Clase para determinar el tipo de conversion
	 */
	public class TIPO_CERT_CONV {
		public static final int TIPO_GEOGRAFICA_PLANA = 1;

		public static final int TIPO_PLANA_GEOGRAFICA = 2;
	}

	/**
	 * Clase para determinar el origen Gauss
	 */
	public class ORIGEN_GAUSS {
		public static final int ORIGEN_BOGOTA = 1;

		public static final int ORIGEN_ESTE = 2;

		public static final int ORIGEN_OESTE = 3;

		public static final int ORIGEN_ESTE_ESTE = 4;

		public static final int ORIGEN_OESTE_OESTE = 5;
	}

	/**
	 * Clase para determinar el tipo de coordenadas planas
	 */
	public class TIPO_PLANAS {
		public static final int TIPO_GAUSS = 1;

		public static final int TIPO_CARTESIANAS = 2;
	}

	/**
	 * Clase para determinar el tipo de transformacion
	 */
	public class TIPO_TRANSFORMACION {
		public static final int TIPO_CARTESIANAS2CARTESIANAS = 7;

		public static final int TIPO_CARTESIANAS2GEOGRAFICAS = 8;

		public static final int TIPO_CARTESIANAS2GAUSS = 9;

		public static final int TIPO_GEOGRAFICAS2CARTESIANAS = 10;

		public static final int TIPO_GEOGRAFICAS2GEOGRAFICAS = 11;

		public static final int TIPO_GEOGRAFICAS2GAUSS = 12;

		public static final int TIPO_GAUSS2CARTESIANAS = 13;

		public static final int TIPO_GAUSS2GEOGRAFICAS = 14;

		public static final int TIPO_GAUSS2GAUSS = 15;
	}

	/**
	 * Clase para derminat el tipo de transformacion
	 */
	public class TIPO_CAMBIO {
		public static final int TIPO_BOGOTA2MAGNA = 1;

		public static final int TIPO_MAGNA2BOGOTA = 2;
	}

	/**
	 * clase para determinar el tipo de azimut
	 */
	public class TIPO_AZIMUT {
		public static final int TIPO_AZIMUT_GEODESICO = 1;

		public static final int TIPO_AZIMUT_PLANO = 2;
	}

	public class TIPO_CONVERSION {
		public static final int TIPO_CARTESIANAS2GEOGRAFICAS = 1;

		public static final int TIPO_GEOGRAFICAS2CARTESIANAS = 2;

		public static final int TIPO_GAUSS2GEOGRAFICAS = 3;

		public static final int TIPO_GEOGRAFICAS2GAUSS = 4;

		public static final int TIPO_CARTESIANAS2GAUSS = 5;

		public static final int TIPO_GAUSS2CARTESIANAS = 6;
	}
	
	public class TIPO_ARCHIVO{
		public static final int TIPO_NORTE_ESTE = 1;
		public static final int TIPO_ID_NORTE_ESTE = 2;
		public static final int TIPO_ID_NORTE_ESTE_ALTURA = 3;
		public static final int TIPO_ESTE_NORTE = 4;
		public static final int TIPO_ID_ESTE_NORTE = 5;
		public static final int TIPO_ID_ESTE_NORTE_ALTURA = 6;		
	}
}