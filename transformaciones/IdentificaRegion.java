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

import tools.ConverAngulos;
import tools.ReadFiles;

/**
 * @author   david
 */
public class IdentificaRegion {

	private double Dx;
	private double Dy;
	private double Dz;

	private double Rx;
	private double Ry;
	private double Rz;

	private double Xo;
	private double Yo;
	private double Zo;

	private double escala; //Factor de escala

	private double dphi;
	private double dlambda;

	int zona = 0;

	/**
	 * Crea una nueva instancia de clase
	 * @param latitudPunto latitud del punto
	 * @param longitudPunto longitud del punto
	 */
	public IdentificaRegion(double latitudPunto, double longitudPunto) {
		ReadFiles rf = new ReadFiles();
		double[][] pMagna;
		pMagna = rf.readParamMagna();
		ConverAngulos convAng = new ConverAngulos();

		for (int i = 0; i < pMagna.length; i++) {
			if (latitudPunto >= convAng.gmsToRad(pMagna[i][1])
					&& latitudPunto <= convAng.gmsToRad(pMagna[i][2])
					&& longitudPunto >= convAng.gmsToRad(pMagna[i][3])
					&& longitudPunto <= convAng.gmsToRad(pMagna[i][4])) {
				zona = (int) pMagna[i][0];
				Dx = pMagna[i][5];
				Dy = pMagna[i][6];
				Dz = pMagna[i][7];
				escala = pMagna[i][8];
				Rx = pMagna[i][9];
				Ry = pMagna[i][10];
				Rz = pMagna[i][11];
				Xo = pMagna[i][12];
				Yo = pMagna[i][13];
				Zo = pMagna[i][14];
				dphi = pMagna[i][15];
				dlambda = pMagna[i][16];
				break;
			}

		}
	}
	
	/**
	 * Devuelve el valor de translaci&oacute;n Dx
	 * @return   valor de translacion Dx
	 * @uml.property   name="dx"
	 */
	public double getDx(){
		return Dx;
	}
	/**
	 * Devuelve el valor de translaci&oacute;n Dy
	 * @return   valor de translacion Dy
	 * @uml.property   name="dy"
	 */
	public double getDy(){
		return Dy;
	}
	/**
	 * Devuelve el valor de translaci&oacute;n Dz
	 * @return   valor de translacion Dz
	 * @uml.property   name="dz"
	 */
	public double getDz(){
		return Dz;
	}
	/**
	 * Devuelve el valor de rotaci&oacute;n Rx
	 * @return   valor de rotacion Rx
	 * @uml.property   name="rx"
	 */
	public double getRx(){
		return Rx;
	}
	/**
	 * Devuelve el valor de rotaci&oacute;n Ry
	 * @return   valor de rotacion Ry
	 * @uml.property   name="ry"
	 */
	public double getRy(){
		return Ry;
	}
	/**
	 * Devuelve el valor de rotaci&oacute;n Rz
	 * @return   valor de rotacion Rz
	 * @uml.property   name="rz"
	 */
	public double getRz(){
		return Rz;
	}
	/**
	 * Devuelve el valor de coordenadas geocentricas X del punto central
	 * @return   coordenada geocentrica X del punto central
	 * @uml.property   name="xo"
	 */
	public double getXo(){
		return Xo;
	}
	/**
	 * Devuelve el valor de coordenadas geocentricas Y del punto central
	 * @return   coordenada geocentrica Y del punto central
	 * @uml.property   name="yo"
	 */
	public double getYo(){
		return Yo;
	}
	/**
	 * Devuelve el valor de coordenadas geocentricas Z del punto central
	 * @return   coordenada geocentrica Z del punto central
	 * @uml.property   name="zo"
	 */
	public double getZo(){
		return Zo;
	}
	/**
	 * Devuelve el valor de phi para transformaci&oacute;n bidimensional
	 * @return   phi
	 * @uml.property   name="dphi"
	 */
	public double getDphi(){
		return dphi;
	}
	/**
	 * Devuelve el valor de lambda para transformaci&oacute;n bidimensional
	 * @return   lambda
	 * @uml.property   name="dlambda"
	 */
	public double getDlambda(){
		return dlambda;
	}
	/**
	 * Devuelve la zona de acuerdo a la latitud y longitud del punto
	 * @return   zona
	 * @uml.property   name="zona"
	 */
	public double getZona(){
		return zona;
	}
	/**
	 * Devuelve la escala de acuerdo a la latitud y longitud del punto
	 * @return   escala
	 * @uml.property   name="escala"
	 */
	public double getEscala(){
		return escala;
	}
}