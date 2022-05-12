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
public class GaussInverso {

	int tDatum;

	Cdatum datum = new Cdatum();

	private double latitudPunto = 0 ;
	private double longitudPunto = 0 ;

	public GaussInverso(int tipoDatum, double latitudOrigen,
			double longitudOrigen, double nortePunto, double estePunto) {
		double latitudTemporal, N, nu2, tao, deltaNorte, deltaEste, alfaQuer, betaQuer, gammaQuer, deltaQuer, epsilonQuer, ene;
		double terminoUno, terminoDos, terminoTres, terminoCuatro, terminoCinco;

		new setDatum(tipoDatum, datum);

		//		C�lculo latitud temporal
		ene = (datum.getA() - datum.getB()) / (datum.getA() + datum.getB());

		alfaQuer = ((datum.getA() + datum.getB())/2d) * (1d + (Math.pow(ene, 2d)
				/ 4d) + (Math.pow(ene, 4d) / 64d));

		betaQuer = ((3d/2d) * ene) - ((27d/32d) * Math.pow(ene, 3d)) + ((269d/512d) * Math.pow(ene, 5d));

		gammaQuer = ((21d/16d) * Math.pow(ene, 2d))
				- ((55d/32d) * Math.pow(ene, 4d));

		deltaQuer = ((151d/96d) * Math.pow(ene, 3d)) - ((417d/128d) * Math.pow(ene, 5d));

		epsilonQuer = (1097d/512d) * Math.pow(ene, 4d);

		deltaNorte = nortePunto - datum.getNorteEcuador();
		deltaEste = estePunto - 1000000d;

		terminoUno = (deltaNorte / alfaQuer);
		terminoDos = betaQuer * Math.sin((2d * deltaNorte) / alfaQuer);
		terminoTres = gammaQuer * Math.sin((4d * deltaNorte) / alfaQuer);
		terminoCuatro = deltaQuer * Math.sin((6d * deltaNorte) / alfaQuer);
		terminoCinco = epsilonQuer * Math.sin((8d * deltaNorte) / alfaQuer);
		latitudTemporal = terminoUno + terminoDos + terminoTres + terminoCuatro
				+ terminoCinco;

		nu2 = datum.getE12() * Math.pow(Math.cos(latitudTemporal), 2d);
		N = Math.pow(datum.getA(), 2d) / (datum.getB() * Math.sqrt(1d + nu2));
//		N = datum.getA() / Math.sqrt(1 - datum.getE2() * Math.pow(Math.sin(latitudTemporal),2));
		tao = Math.tan(latitudTemporal);

		//calculo de la latitud
		terminoUno = (tao / (2d * Math.pow(N, 2d))) * (-1d - nu2)
				* Math.pow(deltaEste, 2d);

		terminoDos = (tao / (24d * Math.pow(N, 4d)))
				* (5d + (3d * Math.pow(tao, 2d)) + (6d * nu2) - (6d * Math.pow(tao, 2d)
						* nu2) - (3d * Math.pow(nu2, 2d)) - (9d * Math.pow(tao, 2d)
						* Math.pow(nu2, 2d))) * Math.pow(deltaEste, 4d);

		terminoTres = (tao / (720d * Math.pow(N, 6d)))
				* (-61d - (90d * Math.pow(tao, 2d)) - (45d * Math.pow(tao, 4d)) - (107d
						* nu2) + (162d * Math.pow(tao, 2d) * nu2) + (45d
						* Math.pow(tao, 4d) * nu2)) * Math.pow(deltaEste, 6d);

		terminoCuatro = (tao / (40320d * Math.pow(N, 8d)))
				* (1385d + 3633d * Math.pow(tao, 2d) + 4095d * Math.pow(tao, 4d) + 1575d * Math
						.pow(tao, 6d)) * Math.pow(deltaEste, 8d);

		latitudPunto = latitudTemporal + terminoUno + terminoDos + terminoTres
				+ terminoCuatro;

		//calculo de la longitud
		terminoUno = deltaEste / (N * Math.cos(latitudTemporal));

		terminoDos = (-1d - (2d * Math.pow(tao, 2d)) - nu2)
				* Math.pow(deltaEste, 3d)
				/ (6d * Math.pow(N, 3d) * Math.cos(latitudTemporal));

		terminoTres = (5d + (28d * Math.pow(tao, 2d)) + 24d * Math.pow(tao, 4d)
				+ 6d * nu2 + 8d * Math.pow(tao, 2d) * nu2)
				* Math.pow(deltaEste, 5d)
				/ (120d * Math.pow(N, 5d) * Math.cos(latitudTemporal));

		terminoCuatro = (-61d - (662d * Math.pow(tao, 2d)) - 1320d
				* Math.pow(tao, 4d) - 720d * Math.pow(tao, 6d))
				* Math.pow(deltaEste, 7d)
				/ (5040d * Math.pow(N, 7d) * Math.cos(latitudTemporal));

		longitudPunto = longitudOrigen + terminoUno + terminoDos + terminoTres
				+ terminoCuatro;
	}
	
	public double getLatitud(){
		return latitudPunto;
	}
	
	public double getLongitud(){
		return longitudPunto;
	}

}