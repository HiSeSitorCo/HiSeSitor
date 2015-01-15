package HiSeSitor;

import java.util.ArrayList;

public class pruebaProcesoMain {

	public static void main(String[] args) {

		Sensor dummie = new Sensor();
		ArrayList<Sensor> sen = new ArrayList<>();
		sen.add(dummie);
		Simulacion sim = new Simulacion(0);
		Proceso p = new Proceso(sen, sim);
		
		ArrayList<Estrategia> es = new ArrayList<Estrategia>(5);
		
		ArrayList<Integer> vars = new ArrayList<Integer>();
		vars.add(1);vars.add(2);vars.add(3);vars.add(4);
		
		p.iteraEstrategias(es, vars);
		
		System.out.println("finiquitaoh");
		
		
	}

}
