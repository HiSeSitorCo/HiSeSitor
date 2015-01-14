package HiSeSitor;

import java.util.ArrayList;

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

		// Asigna variables iterables (iteraciones)
		asignaVariables(vars);

	}

	// override
	public int checkSensores(ArrayList<Sensor> s) {
		return 0;

		// TODO:
	}

	// override
	public void asignaVariables(ArrayList<Integer> v) {
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
			memoria.union(s.getKnowledge());
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
				if (i < 0)
					memoria.creaNodoEstimacion();
		}
	}

	// dependera de cada estrategia
	public void update() {
		// Grafo memoria = estado.memoria;
		for (Sensor s : sensores) {
			for (Nodo n : s.sensorKnowledge.getListaNodos())
				calculaEstima(n);
			agregaSensorMemoria(s);
		}
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
		return dest;
	}

	public void agregaSensorMemoria(Sensor sensor) {
		double total;
		for (int pond : ponderaciones) {
			for (Nodo n : sensor.sensorKnowledge) {
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
