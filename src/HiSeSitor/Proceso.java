package HiSeSitor;

import gestionDatos.Datos;
import gestionDatos.DatosIteracion;

import java.util.ArrayList;

public class Proceso {

	public static boolean enableGUI;
	public int SALTO;
	public ArrayList<Sensor> sensores = new ArrayList<Sensor>();
	public Simulacion simulacion;
	public static Datos dato = new Datos();
	public DatosIteracion mejorCapturados = new DatosIteracion("masCapturados");
	public DatosIteracion mejorOptimizado = new DatosIteracion("masOptimizados");
	public DatosIteracion masVisibles = new DatosIteracion("masOptimizados");

	public int flag = 1;
	/**
	 * el salto lo podemos manejar desde aqui
	 * 
	 * @param s
	 * @param sim
	 */
	public Proceso(ArrayList<Sensor> s, Simulacion sim) {
		sensores = s;
		simulacion = sim;
		SALTO = 4;
		Logger.debug = true;
		enableGUI = false;
	}

	/**
	 * recibe un array de estrategias o por lo menos deberia indicar el numero
	 * de estrategias que se quieren y un array de arrays de enteros. el tamao
	 * del array grande debe ser igual que el numero de estrategias.
	 * 
	 * @param es
	 * @param v
	 */
	public void iteraEstrategias(ArrayList<Estrategia> es,
			ArrayList<ArrayList<Integer>> v) {

		for (int i = 0; i < es.size(); i++) {
			itera(es.get(i), v.get(i), dato);
//ESTO HAY QUE TOCARLO
			mejorCapturados = dato.mejorIteracionCapturados();
			mejorOptimizado = dato.mejorIteracionOptimizada();
			masVisibles = dato.mejorIteracionVisibles();
		}

	}

	
	
	public void preparaSimulacion(Estrategia estr, Simulacion s) {
		s.InitSimulacion();
		estr.reset();
	}
	
	
	/**
	 * itera lo voy explicando entre el codigo. recibe una estrategia y el array
	 * de variables de la misma
	 * 
	 * @param e
	 * @param vars
	 * @param d
	 */
	public void itera(Estrategia e, ArrayList<Integer> vars, Datos d) {
		
	}
	

	public void funcionDecisionParametros() {
		
	}
	
	public void iteraAux(int num, Estrategia e, ArrayList<Integer> vars, Datos d ) {
		if (num > 0) {
			funcionDecisionParametros();
		} else {
			
		}
	}

	/**
	 * funcion interna para calcular el maximo entre tres elementos y devuelve 0
	 * en caso de que sean los tres iguales
	 * 
	 * @param x1
	 * @param x2
	 * @param x3
	 * @return
	 */
	private int maximo(int x1, int x2, int x3) {

		if (x1 > x2)
			if (x1 >= x3)
				return x1;
			else
				return x3;
		if (x1 < x2)
			if (x2 >= x3)
				return x2;
			else
				return x3;
		if (x1 == x2)
			if (x1 > x3)
				return x1;
			else if (x1 < x3)
				return x3;
			else if (x1 == x3)
				return 0;

		return 0;
	}

	public String toString(ArrayList<Integer> array) {

		String cadena = array.toString();
		cadena = cadena.replace(",", ".");
		return cadena;
	}
}
