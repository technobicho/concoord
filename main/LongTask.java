/*
 * Copyright (c) 2003 Sun Microsystems, Inc. All  Rights Reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 
 * -Redistributions of source code must retain the above copyright
 *  notice, this list of conditions and the following disclaimer.
 * 
 * -Redistribution in binary form must reproduct the above copyright
 *  notice, this list of conditions and the following disclaimer in
 *  the documentation and/or other materials provided with the distribution.
 * 
 * Neither the name of Sun Microsystems, Inc. or the names of contributors
 * may be used to endorse or promote products derived from this software
 * without specific prior written permission.
 * 
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING
 * ANY IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE
 * OR NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN AND ITS LICENSORS SHALL NOT
 * BE LIABLE FOR ANY DAMAGES OR LIABILITIES SUFFERED BY LICENSEE AS A RESULT
 * OF OR RELATING TO USE, MODIFICATION OR DISTRIBUTION OF THE SOFTWARE OR ITS
 * DERIVATIVES. IN NO EVENT WILL SUN OR ITS LICENSORS BE LIABLE FOR ANY LOST
 * REVENUE, PROFIT OR DATA, OR FOR DIRECT, INDIRECT, SPECIAL, CONSEQUENTIAL,
 * INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER CAUSED AND REGARDLESS OF THE THEORY
 * OF LIABILITY, ARISING OUT OF THE USE OF OR INABILITY TO USE SOFTWARE, EVEN
 * IF SUN HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 * 
 * You acknowledge that Software is not designed, licensed or intended for
 * use in the design, construction, operation or maintenance of any nuclear
 * facility.
 */
/*
 * Created on 09-dic-2004
 * Modificado por David Acosta
 */
package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StreamTokenizer;

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
public class LongTask {

	//	variables globales
	private int lengthOfTask;

	private int current = 0;

	private String statMessage;

	private BufferedReader brArchivo = null;

	private BufferedWriter bwArchivo = null;

	private StreamTokenizer st;

	private int tipoProceso = 0 ;
	private int tipoArchivo = 0 ;
	private int tipoDatum = 0 ;
	private int tipoDatumOrg = 0 /*, tipoOrigen = 0*/ ;

	private boolean transAfin = false;

	private boolean transformacion = false;

	//variables de entrada y salida de datos
	private double XpuntoIn = 0 ;
	//variables de entrada y salida de datos
	private double YpuntoIn = 0 ;
	//variables de entrada y salida de datos
	private double ZpuntoIn = 0 ;

	private double XpuntoOut = 0 ;
	private double YpuntoOut = 0 /*, ZpuntoOut = 0*/ ;

	private String idPunto = ""/*, XpuntoProc = "", YpuntoInProc = "",
			ZpuntoInProc = ""*/;

	private JFrame parent;

	double lat = 0 ;
	double lon = 0 ;
	double norte = 0 ;
	double este = 0 ;

	//variables parametros cartesianos
	private double latitudBogota = 0 ;
	//variables parametros cartesianos
	private double longitudBogota = 0 ;
	//variables parametros cartesianos
	private double latitudMagna = 0 ;
	//variables parametros cartesianos
	private double longitudMagna = 0 ;
	//variables parametros cartesianos
	private double planoProy = 0 ;
	//variables parametros cartesianos
	private double norteCart = 0 ;
	//variables parametros cartesianos
	private double esteCart = 0 ;

	//variables origen planas gauss
	private int origenGauss = 0 ;
	//variables origen planas gauss
	private int selecOrigen = 0 ;

	private double latitudBogotaGauss = 0 ;
	private double longitudBogotaGauss = 0 ;
	private double latitudMagnaGauss = 0 ;
	private double longitudMagnaGauss = 0 ;

	//variables parametros afin
	private double latMaxAfin = 0 ;
	//variables parametros afin
	private double latMinAfin = 0 ;
	//variables parametros afin
	private double lonMaxAfin = 0 ;
	//variables parametros afin
	private double lonMinAfin = 0 ;
	//variables parametros afin
	private double paramA = 0 ;
	//variables parametros afin
	private double paramB = 0 ;
	//variables parametros afin
	private double paramC = 0 ;
	//variables parametros afin
	private double paramD = 0 ;
	//variables parametros afin
	private double paramE = 0 ;
	//variables parametros afin
	private double paramF = 0 ;

	boolean flagCarte = true ;
	boolean flagGauss = true ;
	boolean flagAfin = true ;
	boolean flagOrgAut = false ;

	boolean flagCab = false ;
	boolean flagverifica = true ;

	private String textoOrigen = "";

	/**
	 * Crea una nueva instancia de la clase
	 * 
	 * @param principal
	 *            JFrame padre
	 * @param formatoArchivo
	 *            tipo de formato para el archivo
	 * @param tipoProceso
	 *            tipo de proceso a realizar
	 * @param tipoDatum
	 *            tipo de datum de referencia
	 * @param archivoLectura
	 *            archivo de lectura
	 * @param archivoEscritura
	 *            archivo de escritura
	 */
	public LongTask(JFrame principal, int formatoArchivo, int tipoProceso,
			int tipoDatum, boolean transAfin, File archivoLectura,
			File archivoEscritura) {

		this.tipoDatum = tipoDatum;
		this.tipoDatumOrg = tipoDatum;
		this.parent = principal;
		this.transAfin = transAfin;
		this.tipoArchivo = formatoArchivo;
		this.tipoProceso = tipoProceso;
		//double dato1 = 0, dato2 = 0, dato = 3;
		//determina la longitud del trabajo
		lengthOfTask = numeroLineas(archivoLectura);
		if (!validaArchivo(archivoLectura)) {
			flagverifica = false;
		}
		//inicializa los archivos de trabajo
		iniFiles(archivoLectura, archivoEscritura);

	}

	/**
	 * Metodo para iniciar el Thread
	 */
	void go() {
		current = 0;
		/*final SwingWorker worker = */new SwingWorker() {
			public Object construct() {
				return new ActualTask();
			}
		};
	}

	/**
	 * Metodo que devuelve la longitud de la tarea
	 * @uml.property   name="lengthOfTask"
	 */
	int getLengthOfTask() {
		return lengthOfTask;
	}

	/**
	 * Metodo que indica el proceso actual
	 * @uml.property   name="current"
	 */
	int getCurrent() {
		return current;
	}

	/**
	 * Metodo que detiene el Thread
	 */
	void stop() {
		current = lengthOfTask;

	}

	/**
	 * Metodo que indica si la tarea a terminado
	 */
	boolean done() {
		if (current >= lengthOfTask) {
			return true;
		} else
			return false;
	}

	/**
	 * Metodo que retorna el mensaje de estado
	 */
	String getMessage() {
		return statMessage;
	}

	/**
	 * Clase que realiza la tarea dentro del Thread
	 */
	class ActualTask {

		ActualTask() {
			if (flagverifica == false)
				return;
			while (current < lengthOfTask) {
				try {
					Thread.sleep(0,0); //duerme por cero nanosegundo
					tipoDatum = tipoDatumOrg;
					leeLinea(); //lee una linea del archivo
					selectTipoOperacion(tipoProceso);
					escribeLinea();
					current++; //make some progress
					statMessage = "Completado " + current + " de "
							+ lengthOfTask + ".";
				} catch (InterruptedException e) {
					System.out.println("Proceso interrumpido " + e);
				}
			}
			try {
				brArchivo.close();
				bwArchivo.close();
			} catch (IOException e3) {
				System.out.println("Error de I/O" + e3);
				return;
			}
		}
	}

	/**
	 * Inicializa los archivos de trabajo
	 * 
	 * @param FileIn
	 *            Archivo de lectura
	 * @param FileOut
	 *            Achivo de escritura
	 */
	public void iniFiles(File FileIn, File FileOut) {
		try {
			brArchivo = new BufferedReader(new FileReader(FileIn));
			st = new StreamTokenizer(brArchivo);
			st.whitespaceChars('\u002c', '\u002c'); //incluye la coma ',' como
			// caracter de espacio
			st.whitespaceChars('\u0000', '\u0020'); //incluye este rango de
			// caracteres como espacio
			st.wordChars('\u002d', '\u002d'); //incluye el menos '-' como parte
			// de un token
			st.wordChars('\u005f', '\u005f'); //incluye el underlinu '_' como
			// parte de un token
		} catch (FileNotFoundException e) {
			System.out.println("El archivo " + FileIn + " no existe");
			return;
		}

		try {
			bwArchivo = new BufferedWriter(new FileWriter(FileOut, true));
		} catch (IOException e) {
			System.out.println("Error al escribir en el archivo");
		}
	}

	/**
	 * Selecci&oacute;n del tipo de operaci&oacute;n a realizar
	 * 
	 * @param tipoProceso
	 *            tipo de proceso a realizar
	 */
	public void selectTipoOperacion(int tipoProceso) {
		ConverAngulos nconvert = new ConverAngulos();
		//		seleccion del tipo de operacion
		switch (tipoProceso) {
		case Clasificacion.TIPO_CONVERSION.TIPO_CARTESIANAS2GEOGRAFICAS:
			parametrosCartesianos();
			convCartesianasToGeograficas(YpuntoIn, XpuntoIn);
			break;
		case Clasificacion.TIPO_CONVERSION.TIPO_GEOGRAFICAS2CARTESIANAS:
			YpuntoIn = nconvert.gmsToRad(YpuntoIn);
			XpuntoIn = (XpuntoIn < 0 ? nconvert.gmsToRad(XpuntoIn) : nconvert
					.gmsToRad(XpuntoIn * -1));
			parametrosCartesianos();
			convGeograficasToCartesianas(YpuntoIn, XpuntoIn);
			break;
		case Clasificacion.TIPO_CONVERSION.TIPO_GAUSS2GEOGRAFICAS:
			origenesGauss();
			convGaussToGeograficas(YpuntoIn, XpuntoIn);
			break;
		case Clasificacion.TIPO_CONVERSION.TIPO_GEOGRAFICAS2GAUSS:
			YpuntoIn = nconvert.gmsToRad(YpuntoIn);
			XpuntoIn = (XpuntoIn < 0 ? nconvert.gmsToRad(XpuntoIn) : nconvert
					.gmsToRad(XpuntoIn * -1));
			origenesGaussAut(XpuntoIn);
			convGeograficasToGauss(YpuntoIn, XpuntoIn);
			break;
		case Clasificacion.TIPO_CONVERSION.TIPO_CARTESIANAS2GAUSS:
			convCartesianasToGauss(YpuntoIn, XpuntoIn);
			break;
		case Clasificacion.TIPO_CONVERSION.TIPO_GAUSS2CARTESIANAS:
			convGaussToCartesianas(YpuntoIn, XpuntoIn);
			break;
		case Clasificacion.TIPO_TRANSFORMACION.TIPO_CARTESIANAS2CARTESIANAS:
			transformacion = true;
			transCartesianaToCartesiana(YpuntoIn, XpuntoIn, ZpuntoIn);
			transformacionAfin(transAfin, false);
			break;
		case Clasificacion.TIPO_TRANSFORMACION.TIPO_CARTESIANAS2GEOGRAFICAS:
			transformacion = true;
			transCartesianaToGeograficas(YpuntoIn, XpuntoIn, ZpuntoIn);
			break;
		case Clasificacion.TIPO_TRANSFORMACION.TIPO_CARTESIANAS2GAUSS:
			transformacion = true;
			transCartesianaToGauss(YpuntoIn, XpuntoIn, ZpuntoIn);
			transformacionAfin(transAfin, true);
			break;
		case Clasificacion.TIPO_TRANSFORMACION.TIPO_GAUSS2CARTESIANAS:
			transformacion = true;
			transGaussToCartesianas(YpuntoIn, XpuntoIn, ZpuntoIn);
			transformacionAfin(transAfin, false);
			break;
		case Clasificacion.TIPO_TRANSFORMACION.TIPO_GAUSS2GAUSS:
			transformacion = true;
			transGaussToGauss(YpuntoIn, XpuntoIn, ZpuntoIn);
			transformacionAfin(transAfin, true);
			break;
		case Clasificacion.TIPO_TRANSFORMACION.TIPO_GAUSS2GEOGRAFICAS:
			transformacion = true;
			transGaussToGeograficas(YpuntoIn, XpuntoIn, ZpuntoIn);
			break;
		case Clasificacion.TIPO_TRANSFORMACION.TIPO_GEOGRAFICAS2CARTESIANAS:
			YpuntoIn = nconvert.gmsToRad(YpuntoIn);
			XpuntoIn = (XpuntoIn < 0 ? nconvert.gmsToRad(XpuntoIn) : nconvert
					.gmsToRad(XpuntoIn * -1));
			transformacion = true;
			transGeograficasToCartesianas(YpuntoIn, XpuntoIn, ZpuntoIn);
			transformacionAfin(transAfin, false);
			break;
		case Clasificacion.TIPO_TRANSFORMACION.TIPO_GEOGRAFICAS2GAUSS:
			YpuntoIn = nconvert.gmsToRad(YpuntoIn);
			XpuntoIn = (XpuntoIn < 0 ? nconvert.gmsToRad(XpuntoIn) : nconvert
					.gmsToRad(XpuntoIn * -1));
			transformacion = true;
			transGeograficasToGauss(YpuntoIn, XpuntoIn, ZpuntoIn);
			transformacionAfin(transAfin, true);
			break;
		case Clasificacion.TIPO_TRANSFORMACION.TIPO_GEOGRAFICAS2GEOGRAFICAS:
			YpuntoIn = nconvert.gmsToRad(YpuntoIn);
			XpuntoIn = (XpuntoIn < 0 ? nconvert.gmsToRad(XpuntoIn) : nconvert
					.gmsToRad(XpuntoIn * -1));
			transformacion = true;
			transGeograficasToGeograficas(YpuntoIn, XpuntoIn, ZpuntoIn);
			break;
		default:
			break;
		}
	}

	/**
	 * Lee una l&iacute;nea del archivo de entrada
	 * 
	 */
	public void leeLinea() {
		try {
			switch (tipoArchivo) {
			case Clasificacion.TIPO_ARCHIVO.TIPO_NORTE_ESTE:
				st.nextToken();
				YpuntoIn = st.nval;
				st.nextToken();
				//st.nextToken();
				XpuntoIn = st.nval;
				break;
			case Clasificacion.TIPO_ARCHIVO.TIPO_ID_NORTE_ESTE:
				st.nextToken();
				idPunto = (st.ttype == StreamTokenizer.TT_WORD ? st.sval
						: ("" + (int) st.nval));
				st.nextToken();
				//st.nextToken();
				YpuntoIn = st.nval;
				st.nextToken();
				//st.nextToken();
				XpuntoIn = st.nval;
				break;
			case Clasificacion.TIPO_ARCHIVO.TIPO_ID_NORTE_ESTE_ALTURA:
				st.nextToken();
				idPunto = (st.ttype == StreamTokenizer.TT_WORD ? st.sval
						: ("" + (int) st.nval));
				st.nextToken();
				//st.nextToken();
				YpuntoIn = st.nval;
				st.nextToken();
				//st.nextToken();
				XpuntoIn = st.nval;
				st.nextToken();
				//st.nextToken();
				ZpuntoIn = st.nval;
				break;
			case Clasificacion.TIPO_ARCHIVO.TIPO_ESTE_NORTE:
				st.nextToken();
				XpuntoIn = st.nval;
				st.nextToken();
				//st.nextToken();
				YpuntoIn = st.nval;
				break;
			case Clasificacion.TIPO_ARCHIVO.TIPO_ID_ESTE_NORTE:
				st.nextToken();
				idPunto = (st.ttype == StreamTokenizer.TT_WORD ? st.sval
						: ("" + (int) st.nval));
				st.nextToken();
				//st.nextToken();
				XpuntoIn = st.nval;
				st.nextToken();
				//st.nextToken();
				YpuntoIn = st.nval;
				break;
			case Clasificacion.TIPO_ARCHIVO.TIPO_ID_ESTE_NORTE_ALTURA:
				st.nextToken();
				idPunto = (st.ttype == StreamTokenizer.TT_WORD ? st.sval
						: ("" + (int) st.nval));
				st.nextToken();
				//st.nextToken();
				XpuntoIn = st.nval;
				st.nextToken();
				//st.nextToken();
				YpuntoIn = st.nval;
				st.nextToken();
				//st.nextToken();
				ZpuntoIn = st.nval;
				break;
			default:
				break;

			}
		} catch (IOException e2) {
			System.out.println("Error de I/O" + e2);
			return;
		}

	}

	/**
	 * metodo que lanza un jdialog indicando la l&iacute;nea del archivo donde hay
	 * error
	 * 
	 * @param numLinea
	 *            n&uacute;mero de la l&iacute;nea que tiene el error
	 */
	private void errorArchivo(int numLinea) {
		JOptionPane.showMessageDialog(null,
				"Hay un error en el formato del archivo\n" + "en la l\u00ednea No."
						+ numLinea, "Error...", JOptionPane.ERROR_MESSAGE);
	}

	private void errorAngulo(int numLinea) {
		JOptionPane.showMessageDialog(null,
				"Hay un error en uno de los \u00e1ngulos del archivo\n"
						+ "en la l\u00ednea No." + numLinea, "Error...",
				JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Metodo que valida un &aacute;ngulo dado en gms si retorna 0 rad el &aacute;ngulo es
	 * incorrecto retorna false
	 * 
	 * @param angulo
	 *            en formato gms
	 * @return si el angulo es valido true, si no false
	 */
	private boolean validaAngulo(double angulo) {
		ConverAngulos nconvert = new ConverAngulos();
		angulo = nconvert.gmsToRad(angulo);
		if (angulo == 0) {
			return false;
		}
		return true;
	}

	/**
	 * Funci&oacute;n que valida el archivo de texto
	 * @return verdadero si el archivo es valido o falso si no lo es
	 */
	private boolean validaArchivo(File FileIn) {
		boolean esValido = false;

		try {
			brArchivo = new BufferedReader(new FileReader(FileIn));
			st = new StreamTokenizer(brArchivo);
			st.whitespaceChars('\u002c', '\u002c'); //incluye la coma ',' como
			// caracter de espacio
			st.whitespaceChars('\u0000', '\u0009'); //incluye este rango de
			// caracteres como espacio
			st.whitespaceChars('\u000b', '\u0020');
			st.wordChars('\u002d', '\u002d'); //incluye el menos '-' como parte
			// de un token
			st.wordChars('\u005f', '\u005f'); //incluye el underlinu '_' como
			// parte de un token
			st.eolIsSignificant(true); //no incluye el fin de linea como token
			
		} catch (FileNotFoundException e) {
			System.out.println("El archivo " + FileIn + " no existe");
			return false;
		}

		try {
			switch (tipoArchivo) {
			case Clasificacion.TIPO_ARCHIVO.TIPO_NORTE_ESTE:
				st.nextToken();
				while (st.ttype != StreamTokenizer.TT_EOF) {
					//valida cada palabra
					if (st.ttype != StreamTokenizer.TT_NUMBER) {
						errorArchivo(st.lineno()); //muestra un mensaje de erro
						// indicando la linea
						return false;
					} else {
						if (((tipoProceso == Clasificacion.TIPO_CONVERSION.TIPO_GEOGRAFICAS2CARTESIANAS)
								|| (tipoProceso == Clasificacion.TIPO_CONVERSION.TIPO_GEOGRAFICAS2GAUSS)
								|| (tipoProceso == Clasificacion.TIPO_TRANSFORMACION.TIPO_GEOGRAFICAS2CARTESIANAS)
								|| (tipoProceso == Clasificacion.TIPO_TRANSFORMACION.TIPO_GEOGRAFICAS2GAUSS) || (tipoProceso == Clasificacion.TIPO_TRANSFORMACION.TIPO_GEOGRAFICAS2GEOGRAFICAS))
								&& !validaAngulo(st.nval)) {
							errorAngulo(st.lineno());
							return false;
						}
					}
					st.nextToken();
					if (st.ttype != StreamTokenizer.TT_NUMBER) {
						errorArchivo(st.lineno()); //muestra un mensaje de erro
						// indicando la linea
						return false;
					} else {
						if (((tipoProceso == Clasificacion.TIPO_CONVERSION.TIPO_GEOGRAFICAS2CARTESIANAS)
								|| (tipoProceso == Clasificacion.TIPO_CONVERSION.TIPO_GEOGRAFICAS2GAUSS)
								|| (tipoProceso == Clasificacion.TIPO_TRANSFORMACION.TIPO_GEOGRAFICAS2CARTESIANAS)
								|| (tipoProceso == Clasificacion.TIPO_TRANSFORMACION.TIPO_GEOGRAFICAS2GAUSS) || (tipoProceso == Clasificacion.TIPO_TRANSFORMACION.TIPO_GEOGRAFICAS2GEOGRAFICAS))
								&& !validaAngulo(st.nval)) {
							errorAngulo(st.lineno());
							return false;
						}
					}
					st.nextToken();
					if ((st.ttype != StreamTokenizer.TT_EOL)
							&& (st.ttype != StreamTokenizer.TT_EOF)) {
						errorArchivo(st.lineno());
						return false;
					}
					st.nextToken();
				}
				esValido = true;
				break;
			case Clasificacion.TIPO_ARCHIVO.TIPO_ID_NORTE_ESTE:
				st.nextToken();
				while (st.ttype != StreamTokenizer.TT_EOF) {
					if ((st.ttype != StreamTokenizer.TT_NUMBER)
							&& (st.ttype != StreamTokenizer.TT_WORD)) {
						errorArchivo(st.lineno());
						return false;
					}
					st.nextToken();
					if (st.ttype != StreamTokenizer.TT_NUMBER) {
						errorArchivo(st.lineno()); //muestra un mensaje de erro
						// indicando la linea
						return false;
					} else {
						if (((tipoProceso == Clasificacion.TIPO_CONVERSION.TIPO_GEOGRAFICAS2CARTESIANAS)
								|| (tipoProceso == Clasificacion.TIPO_CONVERSION.TIPO_GEOGRAFICAS2GAUSS)
								|| (tipoProceso == Clasificacion.TIPO_TRANSFORMACION.TIPO_GEOGRAFICAS2CARTESIANAS)
								|| (tipoProceso == Clasificacion.TIPO_TRANSFORMACION.TIPO_GEOGRAFICAS2GAUSS) || (tipoProceso == Clasificacion.TIPO_TRANSFORMACION.TIPO_GEOGRAFICAS2GEOGRAFICAS))
								&& !validaAngulo(st.nval)) {
							errorAngulo(st.lineno());
							return false;
						}
					}
					st.nextToken();
					if (st.ttype != StreamTokenizer.TT_NUMBER) {
						errorArchivo(st.lineno()); //muestra un mensaje de erro
						// indicando la linea
						return false;
					} else {
						if (((tipoProceso == Clasificacion.TIPO_CONVERSION.TIPO_GEOGRAFICAS2CARTESIANAS)
								|| (tipoProceso == Clasificacion.TIPO_CONVERSION.TIPO_GEOGRAFICAS2GAUSS)
								|| (tipoProceso == Clasificacion.TIPO_TRANSFORMACION.TIPO_GEOGRAFICAS2CARTESIANAS)
								|| (tipoProceso == Clasificacion.TIPO_TRANSFORMACION.TIPO_GEOGRAFICAS2GAUSS) || (tipoProceso == Clasificacion.TIPO_TRANSFORMACION.TIPO_GEOGRAFICAS2GEOGRAFICAS))
								&& !validaAngulo(st.nval)) {
							errorAngulo(st.lineno());
							return false;
						}
					}
					st.nextToken();
					if ((st.ttype != StreamTokenizer.TT_EOL)
							&& (st.ttype != StreamTokenizer.TT_EOF)) {
						errorArchivo(st.lineno());
						return false;
					}
					st.nextToken();
				}
				esValido = true;
				break;
			case Clasificacion.TIPO_ARCHIVO.TIPO_ID_NORTE_ESTE_ALTURA:
				st.nextToken();
				while (st.ttype != StreamTokenizer.TT_EOF) {
					if ((st.ttype != StreamTokenizer.TT_NUMBER)
							&& (st.ttype != StreamTokenizer.TT_WORD)) {
						errorArchivo(st.lineno());
						return false;
					}
					st.nextToken();
					if (st.ttype != StreamTokenizer.TT_NUMBER) {
						errorArchivo(st.lineno()); //muestra un mensaje de erro
						// indicando la linea
						return false;
					} else {
						if (((tipoProceso == Clasificacion.TIPO_CONVERSION.TIPO_GEOGRAFICAS2CARTESIANAS)
								|| (tipoProceso == Clasificacion.TIPO_CONVERSION.TIPO_GEOGRAFICAS2GAUSS)
								|| (tipoProceso == Clasificacion.TIPO_TRANSFORMACION.TIPO_GEOGRAFICAS2CARTESIANAS)
								|| (tipoProceso == Clasificacion.TIPO_TRANSFORMACION.TIPO_GEOGRAFICAS2GAUSS) || (tipoProceso == Clasificacion.TIPO_TRANSFORMACION.TIPO_GEOGRAFICAS2GEOGRAFICAS))
								&& !validaAngulo(st.nval)) {
							errorAngulo(st.lineno());
							return false;
						}
					}
					st.nextToken();
					if (st.ttype != StreamTokenizer.TT_NUMBER) {
						errorArchivo(st.lineno()); //muestra un mensaje de erro
						// indicando la linea
						return false;
					} else {
						if (((tipoProceso == Clasificacion.TIPO_CONVERSION.TIPO_GEOGRAFICAS2CARTESIANAS)
								|| (tipoProceso == Clasificacion.TIPO_CONVERSION.TIPO_GEOGRAFICAS2GAUSS)
								|| (tipoProceso == Clasificacion.TIPO_TRANSFORMACION.TIPO_GEOGRAFICAS2CARTESIANAS)
								|| (tipoProceso == Clasificacion.TIPO_TRANSFORMACION.TIPO_GEOGRAFICAS2GAUSS) || (tipoProceso == Clasificacion.TIPO_TRANSFORMACION.TIPO_GEOGRAFICAS2GEOGRAFICAS))
								&& !validaAngulo(st.nval)) {
							errorAngulo(st.lineno());
							return false;
						}
					}
					st.nextToken();
					if (st.ttype != StreamTokenizer.TT_NUMBER) {
						errorArchivo(st.lineno()); //muestra un mensaje de erro
						// indicando la linea
						return false;
					}
					st.nextToken();
					if ((st.ttype != StreamTokenizer.TT_EOL)
							&& (st.ttype != StreamTokenizer.TT_EOF)) {
						errorArchivo(st.lineno());
						return false;
					}
					st.nextToken();
				}
				esValido = true;
				break;
			case Clasificacion.TIPO_ARCHIVO.TIPO_ESTE_NORTE:
				st.nextToken();
				while (st.ttype != StreamTokenizer.TT_EOF) {
					//valida cada palabra
					if (st.ttype != StreamTokenizer.TT_NUMBER) {
						errorArchivo(st.lineno()); //muestra un mensaje de erro
						// indicando la linea
						return false;
					} else {
						if (((tipoProceso == Clasificacion.TIPO_CONVERSION.TIPO_GEOGRAFICAS2CARTESIANAS)
								|| (tipoProceso == Clasificacion.TIPO_CONVERSION.TIPO_GEOGRAFICAS2GAUSS)
								|| (tipoProceso == Clasificacion.TIPO_TRANSFORMACION.TIPO_GEOGRAFICAS2CARTESIANAS)
								|| (tipoProceso == Clasificacion.TIPO_TRANSFORMACION.TIPO_GEOGRAFICAS2GAUSS) || (tipoProceso == Clasificacion.TIPO_TRANSFORMACION.TIPO_GEOGRAFICAS2GEOGRAFICAS))
								&& !validaAngulo(st.nval)) {
							errorAngulo(st.lineno());
							return false;
						}
					}
					st.nextToken();
					if (st.ttype != StreamTokenizer.TT_NUMBER) {
						errorArchivo(st.lineno()); //muestra un mensaje de erro
						// indicando la linea
						return false;
					} else {
						if (((tipoProceso == Clasificacion.TIPO_CONVERSION.TIPO_GEOGRAFICAS2CARTESIANAS)
								|| (tipoProceso == Clasificacion.TIPO_CONVERSION.TIPO_GEOGRAFICAS2GAUSS)
								|| (tipoProceso == Clasificacion.TIPO_TRANSFORMACION.TIPO_GEOGRAFICAS2CARTESIANAS)
								|| (tipoProceso == Clasificacion.TIPO_TRANSFORMACION.TIPO_GEOGRAFICAS2GAUSS) || (tipoProceso == Clasificacion.TIPO_TRANSFORMACION.TIPO_GEOGRAFICAS2GEOGRAFICAS))
								&& !validaAngulo(st.nval)) {
							errorAngulo(st.lineno());
							return false;
						}
					}
					st.nextToken();
					if ((st.ttype != StreamTokenizer.TT_EOL)
							&& (st.ttype != StreamTokenizer.TT_EOF)) {
						errorArchivo(st.lineno());
						return false;
					}
					st.nextToken();
				}
				esValido = true;
				break;
			case Clasificacion.TIPO_ARCHIVO.TIPO_ID_ESTE_NORTE:
				st.nextToken();
				while (st.ttype != StreamTokenizer.TT_EOF) {
					if ((st.ttype != StreamTokenizer.TT_NUMBER)
							&& (st.ttype != StreamTokenizer.TT_WORD)) {
						errorArchivo(st.lineno());
						return false;
					}
					st.nextToken();
					if (st.ttype != StreamTokenizer.TT_NUMBER) {
						errorArchivo(st.lineno()); //muestra un mensaje de erro
						// indicando la linea
						return false;
					} else {
						if (((tipoProceso == Clasificacion.TIPO_CONVERSION.TIPO_GEOGRAFICAS2CARTESIANAS)
								|| (tipoProceso == Clasificacion.TIPO_CONVERSION.TIPO_GEOGRAFICAS2GAUSS)
								|| (tipoProceso == Clasificacion.TIPO_TRANSFORMACION.TIPO_GEOGRAFICAS2CARTESIANAS)
								|| (tipoProceso == Clasificacion.TIPO_TRANSFORMACION.TIPO_GEOGRAFICAS2GAUSS) || (tipoProceso == Clasificacion.TIPO_TRANSFORMACION.TIPO_GEOGRAFICAS2GEOGRAFICAS))
								&& !validaAngulo(st.nval)) {
							errorAngulo(st.lineno());
							return false;
						}
					}
					st.nextToken();
					if (st.ttype != StreamTokenizer.TT_NUMBER) {
						errorArchivo(st.lineno()); //muestra un mensaje de erro
						// indicando la linea
						return false;
					} else {
						if (((tipoProceso == Clasificacion.TIPO_CONVERSION.TIPO_GEOGRAFICAS2CARTESIANAS)
								|| (tipoProceso == Clasificacion.TIPO_CONVERSION.TIPO_GEOGRAFICAS2GAUSS)
								|| (tipoProceso == Clasificacion.TIPO_TRANSFORMACION.TIPO_GEOGRAFICAS2CARTESIANAS)
								|| (tipoProceso == Clasificacion.TIPO_TRANSFORMACION.TIPO_GEOGRAFICAS2GAUSS) || (tipoProceso == Clasificacion.TIPO_TRANSFORMACION.TIPO_GEOGRAFICAS2GEOGRAFICAS))
								&& !validaAngulo(st.nval)) {
							errorAngulo(st.lineno());
							return false;
						}
					}
					st.nextToken();
					if ((st.ttype != StreamTokenizer.TT_EOL)
							&& (st.ttype != StreamTokenizer.TT_EOF)) {
						errorArchivo(st.lineno());
						return false;
					}
					st.nextToken();
				}
				esValido = true;
				break;
			case Clasificacion.TIPO_ARCHIVO.TIPO_ID_ESTE_NORTE_ALTURA:
				st.nextToken();
				while (st.ttype != StreamTokenizer.TT_EOF) {
					if ((st.ttype != StreamTokenizer.TT_NUMBER)
							&& (st.ttype != StreamTokenizer.TT_WORD)) {
						errorArchivo(st.lineno());
						return false;
					}
					st.nextToken();
					if (st.ttype != StreamTokenizer.TT_NUMBER) {
						errorArchivo(st.lineno()); //muestra un mensaje de erro
						// indicando la linea
						return false;
					} else {
						if (((tipoProceso == Clasificacion.TIPO_CONVERSION.TIPO_GEOGRAFICAS2CARTESIANAS)
								|| (tipoProceso == Clasificacion.TIPO_CONVERSION.TIPO_GEOGRAFICAS2GAUSS)
								|| (tipoProceso == Clasificacion.TIPO_TRANSFORMACION.TIPO_GEOGRAFICAS2CARTESIANAS)
								|| (tipoProceso == Clasificacion.TIPO_TRANSFORMACION.TIPO_GEOGRAFICAS2GAUSS) || (tipoProceso == Clasificacion.TIPO_TRANSFORMACION.TIPO_GEOGRAFICAS2GEOGRAFICAS))
								&& !validaAngulo(st.nval)) {
							errorAngulo(st.lineno());
							return false;
						}
					}
					st.nextToken();
					if (st.ttype != StreamTokenizer.TT_NUMBER) {
						errorArchivo(st.lineno()); //muestra un mensaje de erro
						// indicando la linea
						return false;
					} else {
						if (((tipoProceso == Clasificacion.TIPO_CONVERSION.TIPO_GEOGRAFICAS2CARTESIANAS)
								|| (tipoProceso == Clasificacion.TIPO_CONVERSION.TIPO_GEOGRAFICAS2GAUSS)
								|| (tipoProceso == Clasificacion.TIPO_TRANSFORMACION.TIPO_GEOGRAFICAS2CARTESIANAS)
								|| (tipoProceso == Clasificacion.TIPO_TRANSFORMACION.TIPO_GEOGRAFICAS2GAUSS) || (tipoProceso == Clasificacion.TIPO_TRANSFORMACION.TIPO_GEOGRAFICAS2GEOGRAFICAS))
								&& !validaAngulo(st.nval)) {
							errorAngulo(st.lineno());
							return false;
						}
					}
					st.nextToken();
					if (st.ttype != StreamTokenizer.TT_NUMBER) {
						errorArchivo(st.lineno()); //muestra un mensaje de erro
						// indicando la linea
						return false;
					}
					st.nextToken();
					if ((st.ttype != StreamTokenizer.TT_EOL)
							&& (st.ttype != StreamTokenizer.TT_EOF)) {
						errorArchivo(st.lineno());
						return false;
					}
					st.nextToken();
				}
				esValido = true;
				break;
			default:
				break;

			}
		} catch (IOException e2) {
			System.out.println("Error de I/O" + e2);
			return false;
		}
		st = null;
		return esValido;
	}

	/**
	 * Escribe una l&iacute;nea en el &aacute;rchivo de salida
	 * @return void 
	 */
	private void escribeLinea() {
		String linea = "";
		//verifica si no se a escrito la cabeza del archivo
		if (flagCab == false) {
			String cabeza = "";
			if ((tipoProceso != Clasificacion.TIPO_CONVERSION.TIPO_CARTESIANAS2GAUSS || tipoProceso != Clasificacion.TIPO_CONVERSION.TIPO_GEOGRAFICAS2GAUSS)
					&& selecOrigen != 6) {
				cabeza = cabezaArchivoOut();
			} else {
				cabeza = cabezaArchivoOut() + "\tOrigen";
			}
			try {
				bwArchivo.write(cabeza);
				bwArchivo.newLine();
			} catch (IOException e3) {
				System.out.println("Error de I/O" + e3);
			}
			flagCab = true;
		}
		try {
			switch (tipoArchivo) {
			case Clasificacion.TIPO_ARCHIVO.TIPO_NORTE_ESTE:
				if (flagOrgAut != true) {
					linea = YpuntoOut + "," + XpuntoOut;
				} else {
					linea = YpuntoOut + "," + XpuntoOut + "," + textoOrigen;
				}
				break;
			case Clasificacion.TIPO_ARCHIVO.TIPO_ID_NORTE_ESTE:
				if (flagOrgAut != true) {
					linea = idPunto + "," + YpuntoOut + "," + XpuntoOut;
				} else {
					linea = idPunto + "," + YpuntoOut + "," + XpuntoOut + ","
							+ textoOrigen;
				}
				break;
			case Clasificacion.TIPO_ARCHIVO.TIPO_ID_NORTE_ESTE_ALTURA:
				if (flagOrgAut != true) {
					linea = idPunto + "," + YpuntoOut + "," + XpuntoOut + ","
							+ ZpuntoIn;
				} else {
					linea = idPunto + "," + YpuntoOut + "," + XpuntoOut + ","
							+ ZpuntoIn + "," + textoOrigen;
				}
				break;
			case Clasificacion.TIPO_ARCHIVO.TIPO_ESTE_NORTE:
				if (flagOrgAut != true) {
					linea = XpuntoOut + "," + YpuntoOut;
				} else {
					linea = XpuntoOut + "," + YpuntoOut + "," + textoOrigen;
				}
				break;
			case Clasificacion.TIPO_ARCHIVO.TIPO_ID_ESTE_NORTE:
				if (flagOrgAut != true) {
					linea = idPunto + "," + XpuntoOut + "," + YpuntoOut;
				} else {
					linea = idPunto + "," + XpuntoOut + "," + YpuntoOut + ","
							+ textoOrigen;
				}

				break;
			case Clasificacion.TIPO_ARCHIVO.TIPO_ID_ESTE_NORTE_ALTURA:
				if (flagOrgAut != true) {
					linea = idPunto + "," + XpuntoOut + "," + YpuntoOut + ","
							+ ZpuntoIn;
				} else {
					linea = idPunto + "," + XpuntoOut + "," + YpuntoOut + ","
							+ ZpuntoIn + "," + textoOrigen;
				}
				break;
			default:
				break;
			}
			bwArchivo.write(linea);
			bwArchivo.newLine();
		} catch (IOException e2) {
			System.out.println("Error de I/O" + e2);
			return;
		}
	}

	/**
	 * Determina la cabeza del archivo de salida
	 * 
	 * @return un string con la cabeza del archivo
	 */
	private String cabezaArchivoOut() {
		String linea = "";
		if ((tipoProceso == Clasificacion.TIPO_CONVERSION.TIPO_CARTESIANAS2GEOGRAFICAS)
				|| (tipoProceso == Clasificacion.TIPO_CONVERSION.TIPO_GAUSS2GEOGRAFICAS)
				|| (tipoProceso == Clasificacion.TIPO_TRANSFORMACION.TIPO_CARTESIANAS2GEOGRAFICAS)
				|| (tipoProceso == Clasificacion.TIPO_TRANSFORMACION.TIPO_GAUSS2GEOGRAFICAS)
				|| (tipoProceso == Clasificacion.TIPO_TRANSFORMACION.TIPO_GEOGRAFICAS2GEOGRAFICAS)) {
			if (tipoArchivo == Clasificacion.TIPO_ARCHIVO.TIPO_NORTE_ESTE)
				linea = "Latitud" + "," + "Longitud";
			if (tipoArchivo == Clasificacion.TIPO_ARCHIVO.TIPO_ID_NORTE_ESTE)
				linea = "id" + "," + "Latitud" + "," + "Longitud";
			if (tipoArchivo == Clasificacion.TIPO_ARCHIVO.TIPO_ID_NORTE_ESTE_ALTURA)
				linea = "id" + "," + "Latitud" + "," + "Longitud";
			if (tipoArchivo == Clasificacion.TIPO_ARCHIVO.TIPO_ESTE_NORTE)
				linea = "Longitud" + "," + "Latitud";
			if (tipoArchivo == Clasificacion.TIPO_ARCHIVO.TIPO_ID_ESTE_NORTE)
				linea = "id" + "," + "Longitud" + "," + "Latitud";
			if (tipoArchivo == Clasificacion.TIPO_ARCHIVO.TIPO_ID_ESTE_NORTE_ALTURA)
				linea = "id" + "," + "Longitud" + "," + "Latitud";
		} else {
			if (tipoArchivo == Clasificacion.TIPO_ARCHIVO.TIPO_NORTE_ESTE)
				linea = "Norte" + "," + "Este";
			if (tipoArchivo == Clasificacion.TIPO_ARCHIVO.TIPO_ID_NORTE_ESTE)
				linea = "id" + "," + "Norte" + "," + "Este";
			if (tipoArchivo == Clasificacion.TIPO_ARCHIVO.TIPO_ID_NORTE_ESTE_ALTURA)
				linea = "id" + "," + "Norte" + "," + "Este" + "," + "Altura";
			if (tipoArchivo == Clasificacion.TIPO_ARCHIVO.TIPO_ESTE_NORTE)
				linea = "Este" + "," + "Norte";
			if (tipoArchivo == Clasificacion.TIPO_ARCHIVO.TIPO_ID_ESTE_NORTE)
				linea = "id" + "," + "Este" + "," + "Norte";
			if (tipoArchivo == Clasificacion.TIPO_ARCHIVO.TIPO_ID_ESTE_NORTE_ALTURA)
				linea = "id" + "," + "Este" + "," + "Norte" + "," + "Altura";
		}
		return linea;
	}

	/**
	 * Metodo que determina el n&uacute;mero de lineas que tiene el archivo de texto
	 * 
	 * @param archivo
	 *            Archivo que se desea determinar el n&uacute;mero de lineas
	 */
	private int numeroLineas(File archivo) {
		//String s;
		int lineas = 0;
		try {
			BufferedReader sArchivo = new BufferedReader(
					new FileReader(archivo));
			while ((sArchivo.readLine()) != null)
				lineas++;

		} catch (IOException exc) {
			System.err.println(exc);
			System.out.println("Error al leer el archivo");
		}
		return lineas;
	}

	/**
	 * Formatea el &aacute;ngulo para la salida gr&aacute;fica
	 * 
	 * @param numero
	 *            a formatear
	 * @return angulo ya formateado
	 */
	private double formatAng(double numero) {
		FormatText fText = new FormatText();
		ConverAngulos ca = new ConverAngulos();
		numero = ca.radToGms(numero);
		return fText.formatTextAngArchivo(numero, transformacion);
	}

	/**
	 * Formatea el valor de la coordenada
	 * 
	 * @param numero
	 *            a formatear
	 * @return numero formateado a "###.000"
	 */
	private double formatNum(double numero) {
		FormatText fText = new FormatText();
		return Double.valueOf(fText.formatTextCoord(numero, transformacion))
				.doubleValue();
	}

	/**
	 * Muestra y selecciona los par&aacute;metros cartesianos
	 * 
	 * @return void
	 */
	private void parametrosCartesianos() {
		if (flagCarte == true) {
			//      crea una nueva interfaz de seleccion de parametros de
			// transformacion
			// cartesianos
			ParCarteLocales pcLocal = new ParCarteLocales(parent, true);
			pcLocal.setVisible(true);

			latitudBogota = pcLocal.getLatitudBogota();
			longitudBogota = pcLocal.getLongitudBogota();

			latitudMagna = pcLocal.getLatitudMagna();
			longitudMagna = pcLocal.getLongitudMagna();

			norteCart = pcLocal.getNorteCartesiano();
			esteCart = pcLocal.getEsteCartesiano();
			planoProy = pcLocal.getPlanoProyeccion();
			flagCarte = false;
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
			cartInv = new CartesianoInverso(tipoDatum, latitudBogota,
					longitudBogota, norteCart, esteCart, planoProy, nortePunto,
					estePunto);
		} else {
			cartInv = new CartesianoInverso(tipoDatum, latitudMagna,
					longitudMagna, norteCart, esteCart, planoProy, nortePunto,
					estePunto);
		}
		//devuelve el valor calculado
		lat = cartInv.getLatitud();
		lon = cartInv.getLongitud();
		YpuntoOut = formatAng(cartInv.getLatitud());
		XpuntoOut = formatAng(cartInv.getLongitud());
		//mensaje = mensaje + "**Conversi�n Coordenadas Planas Cartesianas a
		// Geograficas\n";
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
			cartDirec = new CartesianoDirecto(tipoDatum, latitudBogota,
					longitudBogota, norteCart, esteCart, planoProy,
					latitudPunto, longitudPunto);
		} else {
			cartDirec = new CartesianoDirecto(tipoDatum, latitudMagna,
					longitudMagna, norteCart, esteCart, planoProy,
					latitudPunto, longitudPunto);
		}
		//      devuelve el valor calculado
		norte = cartDirec.getNortePunto();
		este = cartDirec.getEstePunto();
		YpuntoOut = formatNum(cartDirec.getNortePunto());
		XpuntoOut = formatNum(cartDirec.getEstePunto());
		//        mensaje = mensaje
		//                + "**Conversi�n Coordenadas Geograficas a Planas Cartesianas\n";
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
					latitudBogotaGauss, longitudBogotaGauss, nortePunto,
					estePunto);
		} else {
			gInvert = new GaussInverso(Clasificacion.TIPO_DATUM.DATUM_MAGNA,
					latitudMagnaGauss, longitudMagnaGauss, nortePunto,
					estePunto);
		}
		//Devuelve el valor calculado y formateado
		lat = gInvert.getLatitud();
		lon = gInvert.getLongitud();
		YpuntoOut = formatAng(gInvert.getLatitud());
		XpuntoOut = formatAng(gInvert.getLongitud());
		//        mensaje = mensaje
		//                + "**Conversion coordenadas Planas Gauss a Geograficas\n";
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
					latitudBogotaGauss, longitudBogotaGauss, latitudPunto,
					longitudPunto);
		} else {
			gdirect = new GaussDirecto(Clasificacion.TIPO_DATUM.DATUM_MAGNA,
					latitudMagnaGauss, longitudMagnaGauss, latitudPunto,
					longitudPunto);
		}
		//Devuelve el valor calculado y formateado
		norte = gdirect.getNorte();
		este = gdirect.getEste();
		YpuntoOut = formatNum(gdirect.getNorte());
		XpuntoOut = formatNum(gdirect.getEste());
		//        mensaje = mensaje
		//                + "**Conversi�n de coordenadas Geograficas a Planas Gauss\n";
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
		YpuntoOut = 0;
		XpuntoOut = 0;
		parametrosCartesianos();
		convCartesianasToGeograficas(nortePunto, estePunto);
		origenesGaussAut(lon);
		convGeograficasToGauss(lat, lon);
		//        mensaje = mensaje
		//                + "**Conversion de coordenadas Planas cartesianas a Planas Gauss\n";
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
		YpuntoOut = 0;
		XpuntoOut = 0;
		origenesGauss();
		convGaussToGeograficas(nortePunto, estePunto);
		parametrosCartesianos();
		convGeograficasToCartesianas(lat, lon);
		//        mensaje = mensaje
		//                + "**Conversion de coordenadas Planas Gauss a Planas Cartesianas\n";
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
		YpuntoOut = 0;
		XpuntoOut = 0;
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
		//        mensaje = mensaje
		//                + "**Transformaci�n de coordenadas Planas Cartesianas a Planas
		// Cartesianas\n";
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
		YpuntoOut = 0;
		XpuntoOut = 0;
		//seleccion de parametros cartesianos
		parametrosCartesianos();
		//convierte de coordenadas planas cartesianas a geograficas
		convCartesianasToGeograficas(nortePunto, estePunto);
		//verifica si hay altura para emplear la transformacion
		// Molodesky-Badekas
		if (altura != 0) {
			transMolodeskyBadekas(altura);
			YpuntoOut = formatAng(lat);
			XpuntoOut = formatAng(lon);
		} else {
			//transformacion bidimensional
			transBidimensional();
			YpuntoOut = formatAng(lat);
			XpuntoOut = formatAng(lon);
		}
		//        mensaje = mensaje
		//                + "**Transformaci�n de coordenadas Planas Cartesianas a
		// Geograficas\n";
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
		YpuntoOut = 0;
		XpuntoOut = 0;
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
		//        mensaje = mensaje
		//                + "**Transformaci�n de coordenadas Planas Cartesianas a Planas
		// Gauss\n";
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
		YpuntoOut = 0;
		XpuntoOut = 0;
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
		//        mensaje = mensaje
		//                + "**Transformaci�n de coordenadas Planas Gauss a coordenadas Planas
		// Cartesianas\n";

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
		YpuntoOut = 0;
		XpuntoOut = 0;
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
		//        mensaje = mensaje
		//                + "**Transformaci�n de coordenadas Planas Gauss o Planas Gauss\n";
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
		YpuntoOut = 0;
		XpuntoOut = 0;
		//      seleccion de origen gauss
		origenesGauss();
		//conversion de coordenadas gauss a geograficas
		convGaussToGeograficas(nortePunto, estePunto);
		if (altura != 0) {
			transMolodeskyBadekas(altura);
			YpuntoOut = formatAng(lat);
			XpuntoOut = formatAng(lon);
		} else {
			//          transformacion bidimensional
			transBidimensional();
			YpuntoOut = formatAng(lat);
			XpuntoOut = formatAng(lon);
		}
		//        mensaje = mensaje
		//                + "**Transformaci�n de coordenadas planas Gauss a Geograficas\n";
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
		YpuntoOut = 0;
		XpuntoOut = 0;
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
		//        mensaje = mensaje
		//                + "**Transformaci�n de coordenadas Geograficas a Planas
		// Cartesianas\n";
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
		YpuntoOut = 0;
		XpuntoOut = 0;
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
		//        mensaje = mensaje
		//                + "**Transformaci�n de coordenadas Geograficas a Planas Gauss\n";
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
		YpuntoOut = 0;
		XpuntoOut = 0;
		lat = latitudPunto;
		lon = longitudPunto;
		if (altura != 0) {
			transMolodeskyBadekas(altura);
			YpuntoOut = formatAng(lat);
			XpuntoOut = formatAng(lon);
		} else {
			//          transformacion bidimensional
			transBidimensional();
			YpuntoOut = formatAng(lat);
			XpuntoOut = formatAng(lon);
		}
		//        mensaje = mensaje
		//                + "**Transformaci�n de coordenadas Geograficas a Geograficas\n";
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
		//cambia el tipo de datum de acurdo al tipo de transformacion
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
		//        mensaje = mensaje + "**Transformaci�n Molodesky - Badekas\n";
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
		//modifica el datum transformado
		if (tipoDatum == Clasificacion.TIPO_DATUM.DATUM_BOGOTA) {
			tipoDatum = Clasificacion.TIPO_DATUM.DATUM_MAGNA;
		} else {
			tipoDatum = Clasificacion.TIPO_DATUM.DATUM_BOGOTA;
		}
		lat = bd.getLatitud();
		lon = bd.getLongitud();
		//        mensaje = mensaje + "**Transformacion Bidimensional\n";
	}

	/**
	 * Muestra y selecciona los parametros Af&iacute;n
	 *  
	 */
	private void parametrosAfin(boolean siGauss) {
		if (flagAfin == true) {
			//crea una nueva instancia de seleccion de parametros de
			// transformacion
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
			flagAfin = false;
		}
	}

	/**
	 * Selecci&oacute;n del origen gauss, en la conversion Gauss-Kr&uacute;ger a Geogr&aacute;ficas
	 * 
	 * 
	 * @return void
	 */
	private void origenesGauss() {
		if (flagGauss == true) {
			int origenGauss = 0;
			//      Crea una nueva interfas de seleccion de origenes
			SelecOrigenGauss selOrgGauss = new SelecOrigenGauss(parent, true,
					false);
			selOrgGauss.setVisible(true);
			//      crea un objeto para la determinacion del origen
			OrigenGauss orGauss = new OrigenGauss();
			origenGauss = selOrgGauss.getSeleccion();

			longitudBogotaGauss = orGauss.getLongitud(origenGauss,
					Clasificacion.TIPO_DATUM.DATUM_BOGOTA);
			latitudBogotaGauss = orGauss
					.getLatitud(Clasificacion.TIPO_DATUM.DATUM_BOGOTA);

			longitudMagnaGauss = orGauss.getLongitud(origenGauss,
					Clasificacion.TIPO_DATUM.DATUM_MAGNA);
			latitudMagnaGauss = orGauss
					.getLatitud(Clasificacion.TIPO_DATUM.DATUM_MAGNA);
			flagGauss = false;
		}
		//identifica el origen empleado
		switch (origenGauss) {
		case 1:
			textoOrigen = "Bogot\u00e1";
			break;
		case 2:
			textoOrigen = "Este";
			break;
		case 3:
			textoOrigen = "Oeste";
			break;
		case 4:
			textoOrigen = "Este-Este";
			break;
		case 5:
			textoOrigen = "Oeste-Oeste";
			break;
		default:
			textoOrigen = "no determinado";
			break;
		}

	}

	/**
	 * Selecci&oacute;n del origen gauss, en la conversi&oacute;n geogr&aacute;ficas a Gauss-Kr&uuml;ger
	 * 
	 * @param longitudPunto
	 */
	private void origenesGaussAut(double longitudPunto) {
		if (flagGauss == true) {
			//int origenGauss = 0;
			//      Crea una nueva interfas de seleccion de origenes
			SelecOrigenGauss selOrgGauss = new SelecOrigenGauss(parent, true,
					true);
			selOrgGauss.setVisible(true);
			selecOrigen = selOrgGauss.getSeleccion();
		}
		//      crea un objeto para la determinacion del origen
		OrigenGauss orGauss = new OrigenGauss();
		//seleccion del origen Gauss
		if (selecOrigen == 6) {
			origenGauss = orGauss.getOrigen(longitudPunto);
			flagOrgAut = true;
		} else {
			origenGauss = selecOrigen;
		}

		longitudBogotaGauss = orGauss.getLongitud(origenGauss,
				Clasificacion.TIPO_DATUM.DATUM_BOGOTA);
		latitudBogotaGauss = orGauss
				.getLatitud(Clasificacion.TIPO_DATUM.DATUM_BOGOTA);

		longitudMagnaGauss = orGauss.getLongitud(origenGauss,
				Clasificacion.TIPO_DATUM.DATUM_MAGNA);
		latitudMagnaGauss = orGauss
				.getLatitud(Clasificacion.TIPO_DATUM.DATUM_MAGNA);
		flagGauss = false;

		//		identifica el origen empleado
		switch (origenGauss) {
		case 1:
			textoOrigen = "Bogot\u00e1";
			break;
		case 2:
			textoOrigen = "Este";
			break;
		case 3:
			textoOrigen = "Oeste";
			break;
		case 4:
			textoOrigen = "Este-Este";
			break;
		case 5:
			textoOrigen = "Oeste-Oeste";
			break;
		default:
			textoOrigen = "no determinado";
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
				YpuntoOut = formatNum(nafin.getNorte());
				XpuntoOut = formatNum(nafin.getEste());
			}
		}
	}

}