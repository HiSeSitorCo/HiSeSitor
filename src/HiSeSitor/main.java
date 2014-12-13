package HiSeSitor;

import java.util.ArrayList;

public class main {

	/**
	 * Main de prueba
	 * @param args
	 */
	public main(String[] args) {
		Sensor dummie = new Sensor();
		ArrayList<Sensor> sen = new ArrayList<>();
		sen.add(dummie);
		Simulacion sim = new Simulacion(sen);
		sim.correSimulacion();
		
	}

}
