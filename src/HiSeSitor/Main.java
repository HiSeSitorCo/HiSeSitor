package HiSeSitor;

import java.util.ArrayList;

public class Main {

	/**
	 * Main de prueba
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Sensor dummie = new Sensor();
		ArrayList<Sensor> sen = new ArrayList<>();
		sen.add(dummie);
		Estrategia dummiest = new Estrategia_espiral_test(sen);
		Simulacion sim = new Simulacion(5);
		sim.correSimulacion(dummiest);
		System.out.println("********************************FIN SIM");
	}

}
