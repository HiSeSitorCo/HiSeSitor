package HiSeSitor;

import java.util.ArrayList;

public class Estrategia {
	public ArrayList<Sensor> sensores = new ArrayList<>();
	public Estado estado;

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

	// dependera de cada estrategia
	public void update() { // REVISAR ESTO PORQUE EL TEMA NODOS NO MOLA NADA
		int i = 9;
		// Grafo memoria = estado.memoria;
		for (Sensor s : sensores) {
			for (Nodo n : s.sensorKnowledge.getListaNodos()) {
				if (s.sensorKnowledge.getDistancia(n, estado.getActual()) < 3) {
					n.score += i;
					i--;
				} else {
					n.score = -1;
				}
			}
			i = 9;
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
}
