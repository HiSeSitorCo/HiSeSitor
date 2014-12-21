package HiSeSitor;

import java.util.ArrayList;

public class Sensor {
	// Funciones DUMMIE
	public Grafo sensorKnowledge;
	public Estado estado;

	public boolean isVisto(Nodo n) {
		return sensorKnowledge.contains(n);
	}

	public Sensor(Estado est) {
		sensorKnowledge = new Grafo();
		estado = est;
	}

	public Sensor() {
		sensorKnowledge = new Grafo();
	}

	public void setEstado(Estado e) {
		estado = e;
	}

	public void updateKnowledge() {
		sensorKnowledge.union(getSensorGraph());
		for (Nodo n : sensorKnowledge.getListaNodos()) {
			//System.out.println(n.toString());
			if (n.presa == true) {
				n.cazada = true;
				n.presa = false;
				estado.presas--;

				System.out.println("** PRESA ATRAPADA");
			}
		}

	}

	public Grafo getSensorGraph() {
		Grafo sensor = new Grafo();
		ArrayList<Nodo> list = estado.mapa.getListaNodos();
		for (Nodo n : list) {
			if (checkNode(n, estado.getActual()) == true)
				sensor.addNode(n, estado.mapa); // Necesario revisar por la
												// parte de grafo
		}
		return sensor;

	}

	/**
	 * Estra funcion es lo que diferenciará unos sensores de otros
	 */
	private boolean checkNode(Nodo check, Nodo actual) {
		// dummie

		if (estado.mapa.getDistancia(check, actual) > 2) {
			return false;
		}
		return true;
	}

}
