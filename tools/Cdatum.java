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
 * Clase que define los parametros del datum
 * @author David Acosta
 * 
 */
public class Cdatum {

	private double dblA;
	private double dblB;
	private double dblF;
	private double dblE2;
	private double dblE12;
	private double dblNorteEcuador;

	public Cdatum() {
		dblA = 0;
		dblB = 0;
		dblF = 0;
		dblE2 = 0;
		dblE12 = 0;
		dblNorteEcuador = 0;
	}

	/**
	 * Establece los valores para el semieje mayor(a)
	 * 
	 * @param valor
	 */
	public void setA(double valor) {
		dblA = valor;
	}

	/**
	 * 
	 * @return El valor del semieje mayor (a)
	 */
	public double getA() {
		return dblA;
	}

	/**
	 * Establece el valor para el semieje menor(b)
	 * 
	 * @param valor
	 */
	public void setB(double valor) {
		dblB = valor;
	}

	/**
	 * 
	 * @return El valor del semieje menor(b)
	 */
	public double getB() {
		return dblB;
	}

	/**
	 * Establece el valor para el aplanamiento reciproco(1/f)
	 * 
	 * @param valor
	 */
	public void setF(double valor) {
		dblF = valor;
	}

	/**
	 * 
	 * @return El valor del aplanamiento reciproco(1/f)
	 */
	public double getF() {
		return dblF;
	}

	/**
	 * Establece el valor para la primera excentricidad(e2)
	 * 
	 * @param valor
	 */
	public void setE2(double valor) {
		dblE2 = valor;
	}

	/**
	 * 
	 * @return El valor de la primera excentricidad(e2)
	 */
	public double getE2() {
		return dblE2;
	}

	/**
	 * Establece el valor para la segunda excentricidad(e12)
	 * 
	 * @param valor
	 */
	public void setE12(double valor) {
		dblE12 = valor;
	}

	/**
	 * 
	 * @return El valor de la segunda excentricidad(e12)
	 */
	public double getE12() {
		return dblE12;
	}

	/**
	 * 
	 * @param valor
	 */
	public void setNorteEcuador(double valor) {
		dblNorteEcuador = valor;
	}

	/**
	 * 
	 * @return valor del norte ecuador
	 */
	public double getNorteEcuador() {
		return dblNorteEcuador;
	}
}