package HiSeSitor;

import java.util.ArrayList;

public class Sensor {
	// Funciones DUMMIE
	public Grafo sensorKnoledge;
	public Estado estado;

	public boolean isVisto(Nodo n) {
		return sensorKnoledge.contains(n);
	}

	public Sensor(Estado est) {
		sensorKnoledge = new Grafo();
		estado = est;
	}

	public Sensor() {
		sensorKnoledge = new Grafo();
	}

	public void setEstado(Estado e) {
		estado = e;
	}

	public void updateKnoledge() {
		sensorKnoledge.union(getSensorGraph());
		for (Nodo n : sensorKnoledge.getListaNodos()) {
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
