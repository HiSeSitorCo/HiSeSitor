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
		ArrayList<Integer> e3_vars = new ArrayList<>();
		e1_vars.add(9);
		e1_vars.add(2);
		e2_vars.add(9);
		e2_vars.add(2);
		e3_vars.add(3); 
		es.add(new Estrategia_espiral_testv2(sen, e1_vars));
		es.add(new Estrategia_FarAway(sen, e3_vars));
		ArrayList<ArrayList<Integer>> exp = new ArrayList<ArrayList<Integer>>();
		exp.add(e1_vars);
		//exp.add(e2_vars);
		exp.add(e3_vars);


		p.iteraEstrategias(es, exp);

		p.imprimeResultados("fichSalida.txt");
		p.muestraVentana();
		
		System.out.println("finiquitaoh");

	}

}
