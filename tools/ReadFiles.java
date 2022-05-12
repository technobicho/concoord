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

import java.io.*;

/**
 * Clase para leer los diferentes archivos de parametros
 * @author David Acosta
 * 
 */
public class ReadFiles {

    private int nLineas;

    /**
     * M&eacute;todo que lee el archivo con los par&aacute;metros de conversi&oacute;n cartesianos
     * 
     * @return matriz con los par&aacute;metros de conversi&oacute;n cartesianos
     */
    public String[][] readParamCarte() {
        String paramCarte[][];
        String in_archivo = "filesConfig/paramCarte.dat";
        BufferedReader brArchivo = null;
        nLineas = numeroLineas(in_archivo);
        paramCarte = new String[nLineas][11];
        try {
            brArchivo = new BufferedReader(new FileReader(in_archivo));
        } catch (FileNotFoundException e) {
            System.out.println("El archivo " + in_archivo + " no existe");
        }
        //      lee el archivo palabra por palabra y las almacena en una matriz
        try {
            int i = 0;
            final char DELIMITADOR_WORD = '|';
            StreamTokenizer st = new StreamTokenizer(brArchivo);
            st.quoteChar(DELIMITADOR_WORD);
            while ((st.nextToken()) != StreamTokenizer.TT_EOF) {

                paramCarte[i][0] = "" + (int) st.nval; //idOrigen
                st.nextToken();
                st.nextToken();
                paramCarte[i][1] = st.sval; //Nombre
                st.nextToken();
                st.nextToken();
                paramCarte[i][2] = "" + (int) st.nval; //a�o
                st.nextToken();
                st.nextToken();
                paramCarte[i][3] = "" + st.nval; //latitudMagna
                st.nextToken();
                st.nextToken();
                paramCarte[i][4] = "" + st.nval; //longitudMagna
                st.nextToken();
                st.nextToken();
                paramCarte[i][5] = "" + st.nval; //latitudBogota
                st.nextToken();
                st.nextToken();
                paramCarte[i][6] = "" + st.nval; //longitudBogota
                st.nextToken();
                st.nextToken();
                paramCarte[i][7] = "" + st.nval; //plano Proyeccion
                st.nextToken();
                st.nextToken();
                paramCarte[i][8] = "" + st.nval; //norte
                st.nextToken();
                st.nextToken();
                paramCarte[i][9] = "" + st.nval; //este
                st.nextToken();
                st.nextToken();
                paramCarte[i][10] = "" + st.sval; //descripcion
                i++;
            }
            brArchivo.close();
        } catch (IOException e) {
            System.out.println("Error de I/O" + e);
        }

        return paramCarte;
    }

    /**
     * M&eacute;todo para leer el archivo de par&aacute;metros de transformaci&oacute;n af&iacute;n
     * para coordenadas de proyecci&oacute;n cartesianas
     * @return matriz con los par&aacute;metros de transformaci&oacute;n af&iacute;n
     */
    public String[][] readParamAfinCart() {
        String paramAfin[][];
        String in_archivo = "filesConfig/paramAfinCart.dat";
        BufferedReader brArchivo = null;
        nLineas = numeroLineas(in_archivo);
        paramAfin = new String[nLineas][11];
        try {
            brArchivo = new BufferedReader(new FileReader(in_archivo));
        } catch (FileNotFoundException e) {
            System.out.println("El archivo " + in_archivo + " no existe");
        }

        //lee el archivo palabra por palabra y las almacena en una matriz
        try {
            int i = 0;

            StreamTokenizer st = new StreamTokenizer(brArchivo);

            while ((st.nextToken()) != StreamTokenizer.TT_EOF) {

                paramAfin[i][0] = st.sval; //Ciudad
                st.nextToken();
                st.nextToken();
                paramAfin[i][1] = "" + st.nval; //latMin
                st.nextToken();
                st.nextToken();
                paramAfin[i][2] = "" + st.nval; //latMax
                st.nextToken();
                st.nextToken();
                paramAfin[i][3] = "" + st.nval; //longMin
                st.nextToken();
                st.nextToken();
                paramAfin[i][4] = "" + st.nval; //longMax
                st.nextToken();
                st.nextToken();
                paramAfin[i][5] = "" + st.nval; //a
                st.nextToken();
                st.nextToken();
                paramAfin[i][6] = "" + st.nval; //b
                st.nextToken();
                st.nextToken();
                paramAfin[i][7] = "" + st.nval; //c
                st.nextToken();
                st.nextToken();
                paramAfin[i][8] = "" + st.nval; //d
                st.nextToken();
                st.nextToken();
                paramAfin[i][9] = "" + st.nval; //e
                st.nextToken();
                st.nextToken();
                paramAfin[i][10] = "" + st.nval; //f
                i++;
            }
            brArchivo.close();
        } catch (IOException e) {
            System.out.println("Error de I/O" + e);
        }
        return paramAfin;
    }
    
    /**
     * M&eacute;todo para leer el archivo de par&aacute;metros de transformaci&oacute;n af&iacute;n
     * para coordenadas de proyecci&oacute;n Gauss-Kr�ger
     * @return matriz con los par&aacute;metros de transformaci&oacute;n af&iacute;n
     */
    public String[][] readParamAfinGauss() {
        String paramAfin[][];
        String in_archivo = "filesConfig/paramAfinGauss.dat";
        BufferedReader brArchivo = null;
        nLineas = numeroLineas(in_archivo);
        paramAfin = new String[nLineas][11];
        try {
            brArchivo = new BufferedReader(new FileReader(in_archivo));
        } catch (FileNotFoundException e) {
            System.out.println("El archivo " + in_archivo + " no existe");
        }

        //lee el archivo palabra por palabra y las almacena en una matriz
        try {
            int i = 0;

            StreamTokenizer st = new StreamTokenizer(brArchivo);

            while ((st.nextToken()) != StreamTokenizer.TT_EOF) {

                paramAfin[i][0] = st.sval; //Ciudad
                st.nextToken();
                st.nextToken();
                paramAfin[i][1] = "" + st.nval; //latMin
                st.nextToken();
                st.nextToken();
                paramAfin[i][2] = "" + st.nval; //latMax
                st.nextToken();
                st.nextToken();
                paramAfin[i][3] = "" + st.nval; //longMin
                st.nextToken();
                st.nextToken();
                paramAfin[i][4] = "" + st.nval; //longMax
                st.nextToken();
                st.nextToken();
                paramAfin[i][5] = "" + st.nval; //a
                st.nextToken();
                st.nextToken();
                paramAfin[i][6] = "" + st.nval; //b
                st.nextToken();
                st.nextToken();
                paramAfin[i][7] = "" + st.nval; //c
                st.nextToken();
                st.nextToken();
                paramAfin[i][8] = "" + st.nval; //d
                st.nextToken();
                st.nextToken();
                paramAfin[i][9] = "" + st.nval; //e
                st.nextToken();
                st.nextToken();
                paramAfin[i][10] = "" + st.nval; //f
                i++;
            }
            brArchivo.close();
        } catch (IOException e) {
            System.out.println("Error de I/O" + e);
        }
        return paramAfin;
    }

    /**
     * M&eacute;todo que lee el archivo de los par&aacute;metros de transformaci&oacute;n para magna
     * 
     * @return matriz con los par&aacute;metros de magna
     */
    public double[][] readParamMagna() {
        double paramMagna[][];
        String in_archivo = "filesConfig/paramMagna.dat";
        BufferedReader brArchivo = null;
        nLineas = numeroLineas(in_archivo);
        paramMagna = new double[nLineas][18];
        try {
            brArchivo = new BufferedReader(new FileReader(in_archivo));
        } catch (FileNotFoundException e) {
            System.out.println("El archivo " + in_archivo + " no existe");
        }

        //lee el archivo palabra por palabra y las almacena en una matriz
        try {
            int i = 0;

            StreamTokenizer st = new StreamTokenizer(brArchivo);

            while ((st.nextToken()) != StreamTokenizer.TT_EOF) {

                paramMagna[i][0] = st.nval; //zona
                st.nextToken();
                st.nextToken();
                paramMagna[i][1] = st.nval; //latitud menor
                st.nextToken();
                st.nextToken();
                paramMagna[i][2] = st.nval; //latitud mayor
                st.nextToken();
                st.nextToken();
                paramMagna[i][3] = st.nval; //longitud menor
                st.nextToken();
                st.nextToken();
                paramMagna[i][4] = st.nval; //longitud mayor
                st.nextToken();
                st.nextToken();
                paramMagna[i][5] = st.nval; //DeltaX
                st.nextToken();
                st.nextToken();
                paramMagna[i][6] = st.nval; //DeltaY
                st.nextToken();
                st.nextToken();
                paramMagna[i][7] = st.nval; //DeltaZ
                st.nextToken();
                st.nextToken();
                paramMagna[i][8] = st.nval; //Lambda
                st.nextToken();
                st.nextToken();
                paramMagna[i][9] = st.nval; //RotacionX
                st.nextToken();
                st.nextToken();
                paramMagna[i][10] = st.nval; //RotacionY
                st.nextToken();
                st.nextToken();
                paramMagna[i][11] = st.nval; //RotacionZ
                st.nextToken();
                st.nextToken();
                paramMagna[i][12] = st.nval; //Coordenada geocentrica X del
                // punto
                st.nextToken();
                st.nextToken();
                paramMagna[i][13] = st.nval; //Coordenada geocentrica Y del
                // punto
                st.nextToken();
                st.nextToken();
                paramMagna[i][14] = st.nval; //Coordenada geocentrica Z del
                // punto
                st.nextToken();
                st.nextToken();
                paramMagna[i][15] = st.nval; //parametro phi bidimencional
                st.nextToken();
                st.nextToken();
                paramMagna[i][16] = st.nval; //parametro lambda bidimencional
                i++;
            }
            brArchivo.close();
        } catch (IOException e) {
            System.out.println("Error de I/O" + e);
        }

        return paramMagna;
    }

    /**
     * M&eacute;todo que determina el n&uacute;mero de lineas de un archivo plano de texto
     * 
     * @param archivo
     * @return numero de lineas en el archivo
     */
    private int numeroLineas(String archivo) {
        //String s;
        int lineas = 1;
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
}