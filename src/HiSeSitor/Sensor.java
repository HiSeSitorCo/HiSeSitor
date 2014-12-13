package HiSeSitor;

import java.util.ArrayList;
import java.util.Random;

public class Sensor {
	//Funciones DUMMIE
	public Grafo sensorKnoledge;
	public Estado estado;

	public boolean isVisto(Nodo n) {
		return sensorKnoledge.contains(n);
	}

	public void updateKnoledge() {
		sensorKnoledge.union(getSensorGraph());

	}
	public Grafo getSensorGraph() {
		Grafo sensor = new Grafo();
		ArrayList<Nodo> list = sensor.getListaNodos();
		Nodo actual = estado.actual;
		for (Nodo n : list) {
			if (checkNode(n, actual) == true) sensor.add(n); //Necesario revisar por la parte de grafo
		}

	}

	/**
	*Estra funcion es lo que diferenciará unos sensores de otros
	*/
	private boolean checkNode(Nodo check, Nodo actual) {
		//dummie
		if (sensorKnoledge.getDistancia(check, actual) > 1) {
			return false;
		}
		return true;
	}


}
