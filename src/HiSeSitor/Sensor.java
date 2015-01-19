package HiSeSitor;

import java.util.ArrayList;

/**
 * 
 * @author HiSeSiTor Co.
 * 
 */
public class Sensor {
	public Grafo sensorKnowledge;
	public Estado estado;

	public boolean isVisto(Nodo n) {
		return checkNode(n, estado.getActual());
	}

	public void initSensor() {
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
			Nodo nod = estado.mapa.getNode(n.id);
			if (nod.presa == true) {
				nod.cazada = true;
				nod.presa = false;
				estado.presas--;

				Logger.debug("Presa atrapada: " + nod.toString());
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
				sensor.addNode(n, estado.mapa);
		}
		return sensor;

	}

	public Grafo getSensorKnowledge() {
		return sensorKnowledge;
	}

	public void setSensorKnowledge(Grafo sensorKnowledge) {
		this.sensorKnowledge = sensorKnowledge;
	}

	private boolean checkNode(Nodo check, Nodo actual) {

		if (estado.mapa.getDistancia(check, actual) > 1) {
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
