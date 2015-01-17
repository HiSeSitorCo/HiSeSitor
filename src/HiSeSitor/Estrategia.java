package HiSeSitor;

import java.util.ArrayList;
import java.util.Random;

public class Estrategia {
	public String nombre;
	public ArrayList<Sensor> sensores;
	public Estado estado;
	public Grafo memoria;
	public ArrayList<Integer> ponderaciones = new ArrayList<>();

	public Estrategia(ArrayList<Sensor> sensores, ArrayList<Integer> vars) {

		// Comprueba que tiene los sensores necesarios
		if (checkSensores(sensores) == -1) {
			return;
		}
		this.sensores = sensores;
		memoria = new Grafo();
		// Asigna variables iterables (iteraciones)
		try {
			asignaVariables(vars);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// override
	public int checkSensores(ArrayList<Sensor> s) {
		return 0;

		// TODO:
	}

	// override
	public void asignaVariables(ArrayList<Integer> v) throws Exception  {
		// TODO:
	}

	public void updateSensores() {
		for (Sensor s : sensores) {
			s.updateKnowledge();
		}
	}

	public Estrategia(ArrayList<Sensor> sen) {
		sensores = sen;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public ArrayList<Sensor> getSensores() {
		return sensores;
	}

	public void init(ArrayList<Sensor> sensores) {
		// Depende de la estrategia inicializar unos u otros sensores
		this.sensores = sensores;
	}

	public void updateMemoria() {

		for (Sensor s : sensores) {
			agregaSensorMemoria(s);
		}
		generaEstimacion();
		update();
	}

	// Funcion a reimplementar
	public double estima(Nodo n) {
		return -1;
	}

	// Funcion a reimplementar
	public double calcula(Nodo n) {
		return 1;
	}

	public void generaEstimacion() {
		ArrayList<Nodo> lista = memoria.getListaNodos();
		for (Nodo n : lista) {
			ArrayList<Integer> aristas = n.getListaAristas();
			for (int i : aristas)
				if (i < 0){
					/**
					 * Nodo tiene un atributo boolean que especifica si es una estimaci�n.
					 * En dicho caso, lo crear�a un nodo estimaci�n llamando al creador
					 * y luego setEstimacion true...
					 */
					memoria.creaNodoEstimacion();
				}
		}
	}

	// dependera de cada estrategia
	public void update() {
		// Grafo memoria = estado.memoria;
		for (Nodo n : memoria.getListaNodos())
			calculaEstima(n);
	}

	public Nodo getObjetivo() {

		ArrayList<Nodo> nodos = estado.getAdyacentes(estado.getActual());
		int max = 0;
		Nodo dest = null;
		for (Nodo n : nodos) {
			if (max < n.score) {
				max = (int) n.score;
				dest = n;
			}
		}
		if(dest == null){//No deberia hacerse esto, pero asi evitamos algun que otro pete
			Random r = new Random();
			dest = nodos.get(r.nextInt(nodos.size()));
		}
		return dest;
	}

	public void agregaSensorMemoria(Sensor sensor) {
		double total;
		for (int pond : ponderaciones) {
			for (Nodo n : sensor.getSensorGraph().getListaNodos()) {
				Nodo nod = memoria.getNode(n.id);
				nod.setScore((n.score * pond)+nod.score);
			}
		}
	}

	public double calculaEstima(Nodo n) {

		if (memoria.isEstimacion(n)) {
			return estima(n);
		}
		return calcula(n);
	}
}
