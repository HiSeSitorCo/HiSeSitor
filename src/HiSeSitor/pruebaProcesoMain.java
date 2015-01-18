package HiSeSitor;

import gestionDatos.Lectura;

import java.util.ArrayList;

public class pruebaProcesoMain {

	public static void main(String[] args) {

		Sensor dummie = new Sensor();
		ArrayList<Sensor> sen = new ArrayList<>();
		sen.add(dummie);
		Simulacion sim = new Simulacion(15);
		Proceso p = new Proceso(sen, sim);

		ArrayList<Estrategia> es = new ArrayList<Estrategia>();
		ArrayList<Integer> vars = new ArrayList<Integer>();

		ArrayList<Integer> e1_vars = new ArrayList<>();
		ArrayList<Integer> e2_vars = new ArrayList<>();
		e1_vars.add(9);
		e1_vars.add(2);
		e2_vars.add(9);
		e2_vars.add(2);
		es.add(new Estrategia_espiral_testv2(sen, e1_vars));
		es.add(new Estrategia_espiral_testv3(sen, e2_vars));
		ArrayList<ArrayList<Integer>> exp = new ArrayList<ArrayList<Integer>>();
		exp.add(e1_vars);
		exp.add(e2_vars);


		p.iteraEstrategias(es, exp);
		
		System.out.println("ahora imprimo en fichero");
		p.imprimeResultados("fichSalida.txt");
		System.out.println("finiquitaoh");

	}

}
