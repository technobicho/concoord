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

/**
 * @author   david
 */
public class GeocentricasDirecto {

	int tDatum;

	Cdatum datum = new Cdatum();
	
	private double X = 0 ;
	private double Y = 0 ;
	private double Z = 0 ;
	
	/**
	 * Crea una nueva instancia de la clase
	 * @param tipoDatum tipo de datum Bogot&aacute; o Magna
	 * @param latitudPunto latitud del punto de interes
	 * @param longitudPunto longitud del punto de interes
	 * @param alturaElipsoidalPunto altura elipsoidal del punto de interes
	 */
	public GeocentricasDirecto(int tipoDatum,double latitudPunto, double longitudPunto, double alturaElipsoidalPunto){
		double NPunto = 0;
		new setDatum(tipoDatum, datum);
		
		NPunto = datum.getA() / Math.sqrt(1 - (datum.getE2() * Math.pow(Math.sin(latitudPunto),2)));
        
        X = (NPunto + alturaElipsoidalPunto) * Math.cos(latitudPunto) * Math.cos(longitudPunto);
        Y = (NPunto + alturaElipsoidalPunto) * Math.cos(latitudPunto) * Math.sin(longitudPunto);
        Z = ((1 - datum.getE2()) * NPunto + alturaElipsoidalPunto) * Math.sin(latitudPunto);
	}
	
	/**
	 * Devuelve la coordenada X geoc&eacute;ntrica del punto de interes
	 * @return   coordenada X geocentrica
	 * @uml.property   name="x"
	 */
	public double getX(){
		return X;
	}
	/**
	 * Devuelve la coordenada Y geoc&eacute;ntrica del punto de interes
	 * @return   coordenada Y geocentrica
	 * @uml.property   name="y"
	 */
	public double getY(){
		return Y;
	}
	/**
	 * Devuelve la coordenada Z geoc&eacute;ntrica del punto de interes
	 * @return   coordenada Z geocentrica
	 * @uml.property   name="z"
	 */
	public double getZ(){
		return Z;
	}
}
