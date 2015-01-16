package HiSeSitor;

import gestionDatos.Datos;
import gestionDatos.DatosIteracion;

import java.util.ArrayList;

public class Proceso {

	public ArrayList<Sensor> sensores = new ArrayList<Sensor>();
	public Simulacion simulacion;
	public static Datos dato = new Datos();
	public DatosIteracion mejorCapturados = new DatosIteracion("mas capturados");
	public DatosIteracion mejorOptimizado = new DatosIteracion("mas optimizados");
	public DatosIteracion masVisibles = new DatosIteracion("mas optimizados");

	public Proceso(ArrayList<Sensor> s, Simulacion sim) {
		sensores = s;
		simulacion = sim;
	}

	public void iteraEstrategias(ArrayList<Estrategia> es, ArrayList<Integer> v) {

		int contador = 0;
		ArrayList<DatosIteracion> mejoresIteracionesCapturados = new ArrayList<DatosIteracion>();
		ArrayList<DatosIteracion> mejoresIteracionesOptimizadas = new ArrayList<DatosIteracion>();
		ArrayList<DatosIteracion> iteracionesMasVisibles = new ArrayList<DatosIteracion>();
		
		for (int i = 0; i < es.size(); i++){
			
			itera(es.get(i), i, dato, v, contador);
			
			mejoresIteracionesCapturados.add(dato.mejorIteracionCapturados(es.get(i).nombre));
			mejoresIteracionesOptimizadas.add(dato.mejorIteracionOptimizada(es.get(i).nombre));
			iteracionesMasVisibles.add(dato.mejorIteracionVisibles(es.get(i).nombre));
		}
		
		mejorCapturados = dato.mejorIteracionCapturados(mejoresIteracionesCapturados);
		mejorOptimizado = dato.mejorIteracionOptimizada(mejoresIteracionesOptimizadas);
		masVisibles = dato.mejorIteracionVisibles(mejoresIteracionesOptimizadas);
	}

	public void itera(Estrategia e, int i, Datos d, ArrayList<Integer> vars,
			int c) {

		 if(c >= vars.size()) {
			// parada
			String cadena;
			//e = new Estrategia(sensores, vars);
			//simulacion.correSimulacion(e);

			// no se como se sacan los valores de tiempo, nNodos y capturados de
			// la simulacion.
			// se los tienes que poner aqui sustituyendo las cadenas
			System.out.println(vars.toString());
			cadena = (Integer.toString(i) + vars.toString() + vars.get(0) + vars.get(1) + vars.get(2));
			d.agregaDatos(cadena);
			return;
		 }
			for (int j = 0; j < vars.get(c); j++) {
				vars.remove(c);
				vars.add(c, j);
				itera(e, i, d, vars, c++);
		}
	}

}
