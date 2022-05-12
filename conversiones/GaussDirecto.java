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
public class GaussDirecto {

	int tDatum;

	Cdatum datum = new Cdatum();

	private double nortePunto = 0 ;
	private double estePunto = 0 ;

	public GaussDirecto(int tipoDatum, double latitudOrigen,
			double longitudOrigen, double latitudPunto, double longitudPunto) {

		double diferenciaArco, N, nu2, tao, lambda/*, cEste, cNorte*/;
		double terminoUno, terminoDos, terminoTres, terminoCuatro;

		new setDatum(tipoDatum, datum);
		
		//		calculo de parametros principales
		double GP = 0, Gorigen = 0;

		GP = ArcoMeridiano(latitudPunto);
		Gorigen = ArcoMeridiano(latitudOrigen);
		diferenciaArco = GP - Gorigen;
		nu2 = datum.getE12() * Math.pow(Math.cos(latitudPunto), 2d);		
		N = datum.getA()
				/ Math.sqrt((1d - datum.getE2()
						* Math.pow(Math.sin(latitudPunto), 2d)));
		tao = Math.tan(latitudPunto);
		lambda = longitudPunto - longitudOrigen;

		//calculo de coordenada norte
		terminoUno = (1d / 2d)
				* (tao * N * (Math.pow(lambda, 2d)) * (Math.pow(Math
						.cos(latitudPunto), 2d)));		
		terminoDos = (tao / 24d) * N
				* Math.pow(Math.cos(latitudPunto), 4d)
				* ((5d - Math.pow(tao, 2d)) + (9d * nu2) + (4d * Math.pow(nu2, 2d)))
				* Math.pow(lambda, 4d);
		terminoTres = (1d / 720d)
				* (tao
						* N
						* (Math.pow(lambda, 6d))
						* (61d - 58d * Math.pow(tao, 2d) + Math.pow(tao, 4d) + 270d
								* nu2 - 330d * Math.pow(tao, 2d) * nu2) * Math
						.pow(Math.cos(latitudPunto), 6));
		terminoCuatro = (1d / 40320d)
				* (tao
						* N
						* (Math.pow(lambda, 8d))
						* (1385d - 3111d * Math.pow(tao, 2d) + 543d
								* Math.pow(tao, 4d) - Math.pow(tao, 6d)) * Math
						.pow(Math.cos(latitudPunto), 8d));
		nortePunto = diferenciaArco + terminoUno + terminoDos + terminoTres
				+ terminoCuatro + 1000000d;

		//calculo de coordenada este
		terminoUno = N * lambda * Math.cos(latitudPunto);		
		terminoDos = (N / 6d) * Math.pow(Math.cos(latitudPunto), 3d)
				* (1d - Math.pow(tao, 2d) + nu2) * Math.pow(lambda, 3d);		
		terminoTres = (1d / 120d)
				* N
				* Math.pow(Math.cos(latitudPunto), 5d)
				* ((5d - 18d * Math.pow(tao, 2d)) + Math.pow(tao, 4d) + 14d * nu2 - 58d
						* Math.pow(tao, 2d) * nu2) * Math.pow(lambda, 5d);		
		terminoCuatro = (1d / 5040d)
				* N
				* Math.pow(Math.cos(latitudPunto), 7d)
				* (61d - 479d * Math.pow(tao, 2d) + 179d * Math.pow(tao, 4d) - Math
						.pow(tao, 6d)) * Math.pow(lambda, 7d);
		estePunto = terminoUno + terminoDos + terminoTres + terminoCuatro
				+ 1000000d;
	}
	
	public double getNorte(){
		return nortePunto;
	}
	
	public double getEste(){
		return estePunto;
	}

	/**
	 * C&aacute;lculo del arco de meridiano para Gauss Directo
	 * 
	 * @param latitud
	 *            del punto 
	 * @return arco meridiano para Gauss Directo
	 */
	private double ArcoMeridiano(double latitud) {
		double ene, alfa, beta, gamma, delta, epsilon;

		ene = (datum.getA() - datum.getB()) / (datum.getA() + datum.getB());

		alfa = ((datum.getA() + datum.getB()) / 2d)
				* (1d + (Math.pow(ene, 2d) / 4d) + (Math.pow(ene, 4d) / 64d));

		beta = -(3d / 2d) * ene + (9d / 16d)
				* Math.pow(ene, 3d) - (3d / 32d)
				* Math.pow(ene, 5d);

		gamma = (15d / 16d) * Math.pow(ene, 2d)
				- (15d / 32d) * Math.pow(ene, 4d);

		delta = -(35d / 48d) * Math.pow(ene, 3d)
				+ (105d / 256d) * Math.pow(ene, 5d);

		epsilon = (315d/ 512d) * Math.pow(ene, 4d);

		return alfa
				* (latitud + beta * Math.sin(2d * latitud) + gamma
						* Math.sin(4d * latitud) + delta * Math.sin(6d * latitud) + epsilon
						* Math.sin(8d * latitud));
	}
}