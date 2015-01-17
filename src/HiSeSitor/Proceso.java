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
		enableGUI = true;
	}

	/**
	 * recibe un array de estrategias o por lo menos deberia indicar el numero
	 * de estrategias que se quieren y un array de arrays de enteros. el tamao
	 * del array grande debe ser igual que el numero de estrategias.
	 * 
	 * @param es
	 * @param v
	 */
	public void iteraEstrategias(ArrayList<Estrategia> es, ArrayList<ArrayList<Integer>> v) {

		for (int i = 0; i < es.size(); i++) {
			itera(es.get(i), v.get(i), dato);
		}

		mejorCapturados = dato.mejorIteracionCapturados();
		mejorOptimizado = dato.mejorIteracionOptimizada();
		masVisibles = dato.mejorIteracionVisibles();
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

		//creo las variables necesarias
		ArrayList<Integer> AR1 = new ArrayList<Integer>();
		ArrayList<Integer> AR2 = new ArrayList<Integer>();
		ArrayList<Integer> AR3 = new ArrayList<Integer>();
		int SALTO = this.SALTO;
		int i = 0, x1, x2, x3, max = 0, anterior = 0;

		//relleno los arrays a -1 que indica que ese valor no se usa
		for (i = 0; i < vars.size(); i++) {
			AR1.add(i, -1);
			AR2.add(i, -1);
			AR3.add(i, -1);
		}

		// para cada elemento del array...
		for (i = 0; i < vars.size(); i++) {

			// ...elimino el -1 y anado el valor del array bueno...
			AR1.remove(i);
			AR1.add(i, vars.get(i));

			// ...y mientras el salto sea mayor que cero...
			while (SALTO > 0) {

				// elimino los valores de la posicion i de los arrays auxiliares
				// e inserto el valor del array auxiliar1 +- el incremento
				AR2.remove(i);
				AR3.remove(i);

				AR2.add(i, AR1.get(i) + SALTO);
				AR3.add(i, AR1.get(i) - SALTO);

				// simulo para los tres arrays y guardo los valores obtenidos
				sensores.clear();
				sensores.add(new Sensor());
				e = new Estrategia(sensores, AR1);
				simulacion.InitSimulacion(); //Reiniciamos estado
				x1 = simulacion.correSimulacion(e);

				sensores.clear();
				sensores.add(new Sensor());
				e = new Estrategia(sensores, AR2);
				simulacion.InitSimulacion();
				x2 = simulacion.correSimulacion(e);

				sensores.clear();
				sensores.add(new Sensor());
				e = new Estrategia(sensores, AR3);
				simulacion.InitSimulacion(); 
				x3 = simulacion.correSimulacion(e);

				// compruebo el maximo de los valores
				max = maximo(x1, x2, x3);

				// si la desviacion es tan peque�a que implica que todos los
				// valores sean iguales, salimos
				if (max == 0)
					break;

				// si el maximo valor obtenido es mejor que el anterior maximo,
				// lo sobreescribimos en el array
				if (anterior < max) {
					AR1.remove(i);
					AR1.add(i, max);
				}
				// decrementamos el salto
				SALTO--;
			}
		}
		
		// por ultimo corro una ultima simulacion con el array mas optimo para
		// guardar sus valores en Datos
		e = new Estrategia(sensores, AR1);
		x1 = simulacion.correSimulacion(e);

		// creo que la funcion de agregadatos deberia ir en la simulacion, ya
		// que aqui no se que datos se reciben ni nada LO DEJO A LA ELECCION DE
		// GUILLE

		d.agregaDatos(/* se meten los datos de la simulacion mas optima */"");
	}

	/**
	 * funcion interna para calcular el maximo entre tres elementos
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

}
