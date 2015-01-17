package HiSeSitor;

import java.util.ArrayList;

public class pruebaProcesoMain {

	public static void main(String[] args) {

		Sensor dummie = new Sensor();
		ArrayList<Sensor> sen = new ArrayList<>();
		sen.add(dummie);
		Simulacion sim = new Simulacion(0);
		Proceso p = new Proceso(sen, sim);
		
		ArrayList<Estrategia> es = new ArrayList<Estrategia>();
		
		ArrayList<ArrayList<Integer>> variables = new ArrayList<>();
		es.add(new Estrategia_espiral_test(sen));
		ArrayList<Integer> e1_vars = new ArrayList<>();
		e1_vars.add(2);
		e1_vars.add(3);
		variables.add(e1_vars);
		
		p.iteraEstrategias(es, variables);
		
		System.out.println("finiquitaoh");
		
		
	}

}
