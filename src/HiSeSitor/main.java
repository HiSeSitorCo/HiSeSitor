package HiSeSitor;

import java.util.ArrayList;

public class main {

	/**
	 * Main de prueba
	 * @param args
	 */
	public static void main(String[] args) {
		Sensor dummie = new Sensor();
		ArrayList<Sensor> sen = new ArrayList<>();
		sen.add(dummie);
		Estrategia dummiest = new Estrategia(sen);
		Simulacion sim = new Simulacion(sen, dummiest);
		sim.correSimulacion(dummiest);
		
	}

}
