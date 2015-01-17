package HiSeSitor;

import java.util.ArrayList;

public class Sensor {
	// Funciones DUMMIE
	public Grafo sensorKnowledge;
	public Estado estado;

	public boolean isVisto(Nodo n) {
		return checkNode(n, estado.getActual());
		//return sensorKnowledge.contains(n);
	}

	public void initSensor(){
		sensorKnowledge.InitSensorGraph(estado.mapa);
		
	}
	
	public Sensor(Estado est) {
		sensorKnowledge = new Grafo();
		estado = est;
		initSensor();
	}

	public Sensor() {
		sensorKnowledge = new Grafo();
	}

	public void setEstado(Estado e) {
		estado = e;
		initSensor();
		
	}

	public void updateKnowledge() {
		sensorKnowledge.union(getSensorGraph());
		for (Nodo n : sensorKnowledge.getListaNodos()) {
			//System.out.println(n.toString());
			if (n.presa == true) {
				n.cazada = true;
				n.presa = false;
				estado.presas--;

				Logger.debug("Presa atrapada: " + n.toString());
			}
		}

	}

	public Grafo getSensorGraph() {
		Grafo sensor = new Grafo();
		ArrayList<Nodo> list = estado.mapa.getListaNodos();
		for (Nodo n : list) {
			if (sensorKnowledge.contains(n)) {
				Nodo aux = sensorKnowledge.getNode(n.id);
				sensorKnowledge.updateNodo(aux, n);
			}
			if (checkNode(n, estado.getActual()) == true)
				sensor.addNode(n, estado.mapa); // Necesario revisar por la
												// parte de grafo
		}
		return sensor;

	}
	

	public Grafo getSensorKnowledge() {
		return sensorKnowledge;
	}

	public void setSensorKnowledge(Grafo sensorKnowledge) {
		this.sensorKnowledge = sensorKnowledge;
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

	public Grafo getKnowledge() {
		
		return sensorKnowledge;
	}

	public void reset() {
		sensorKnowledge = new Grafo();
		initSensor();
		
	}

}
