package HiSeSitor;

import gestionDatos.Datos;
import gestionDatos.DatosIteracion;

import java.util.ArrayList;

public class Proceso {

	private ArrayList<Sensor> sensores = new ArrayList<Sensor>();
	private Simulacion simulacion;
	public static Datos dato = new Datos();

	public Proceso(ArrayList<Sensor> s, Simulacion sim) {
		sensores = s;
		simulacion = sim;
	}

	@SuppressWarnings("unused")
	private void iteraEstrategias(ArrayList<Estrategia> es, ArrayList<Integer> v) {

		int contador = 0;
		ArrayList<DatosIteracion> mejoresIteracionesCapturados = new ArrayList<DatosIteracion>();
		ArrayList<DatosIteracion> mejoresIteracionesOptimizadas = new ArrayList<DatosIteracion>();
		
		DatosIteracion mejorCapturados = new DatosIteracion("mas capturados");
		DatosIteracion mejorOptimizado = new DatosIteracion("mas optimizados");
		
		for (int i = 0; i < es.size(); i++){
			itera(es.get(i), i, dato, v, contador);
			mejoresIteracionesCapturados.add(dato.mejorIteracionCapturados(es.get(i).nombre));
			mejoresIteracionesOptimizadas.add(dato.mejorIteracionOptimizada(es.get(i).nombre));
		}
		
		mejorCapturados = dato.mejorIteracionCapturados(mejoresIteracionesCapturados);
		mejorOptimizado = dato.mejorIteracionOptimizada(mejoresIteracionesOptimizadas);
	}

	private void itera(Estrategia e, int i, Datos d, ArrayList<Integer> vars,
			int c) {

		if (c < vars.size()) {

			for (int j = 0; j < vars.get(c); j++) {
				vars.remove(c);
				vars.add(c, j);
				itera(e, i, d, vars, c++);
			}

		} else {
			// parada
			String cadena;
			e = new Estrategia(sensores, vars);
			simulacion.correSimulacion(e);

			// no se como se sacan los valores de tiempo, nNodos y capturados de
			// la simulacion.
			// se los tienes que poner aqui sustituyendo las cadenas
			cadena = (Integer.toString(i) + vars.toString() + "TIEMPO"
					+ "N_NODOS" + "CAPTURADOS");
			d.agregaDatos(cadena);
		}
	}

}
