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
		e1_vars.add(2);
		e1_vars.add(3);
		es.add(new Estrategia_espiral_testv2(sen, e1_vars));
		ArrayList<ArrayList<Integer>> exp = new ArrayList<ArrayList<Integer>>();
		exp.add(e1_vars);


		p.iteraEstrategias(es, exp);

		System.out.println("finiquitaoh");
		System.out.println("-----------------------------");
		System.out.println("-----------------------------\n");
		System.out.println(p.mejorCapturados.getNombreIteracion());
		for (Lectura l : p.mejorCapturados.getValoresLecturas()) {
			System.out.println(l.getTiempo() + "," + l.getnNodos() + ","
					+ l.getCapturados());
		}
		System.out.println("-----------------------------");
		System.out.println(p.mejorOptimizado.getNombreIteracion());
		for (Lectura l : p.mejorOptimizado.getValoresLecturas()) {
			System.out.println(l.getTiempo() + "," + l.getnNodos() + ","
					+ l.getCapturados());
		}
		System.out.println("-----------------------------");
		System.out.println(p.masVisibles.getNombreIteracion());
		for (Lectura l : p.masVisibles.getValoresLecturas()) {
			System.out.println(l.getTiempo() + "," + l.getnNodos() + ","
					+ l.getCapturados());
		}
		System.out.println("-----------------------------");

	}

}
