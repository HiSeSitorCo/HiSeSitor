package HiSeSitor;

import gestionDatos.Datos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

/**
 * 
 * @author HiSeSiTor Co.
 * 
 * Define el estado de una simulacion en un instante en concreto
 */
public class Estado {

	public Random random = new Random();
	public ArrayList<Nodo> hiddenNodes = new ArrayList<>();
	public Nodo actual;
	public Estrategia estrategia;
	public Nodo inicio;
	public int presas = 0;
	public int salvadas = 0;
	public int definicionMalla = 100;
	public int time = 0;
	public Grafo mapa;

	/**
	 * Inicializa el estado de la simulacion
	 * @param estr Estrategia con la que se desarrolla la simulacion.
	 */
	public Estado(Estrategia estr) {
		initGraph();
		actual = mapa.getCazador();
		this.estrategia = estr;
	}
	/**
	 * Inicializa los nodos que el cazador no ve al empezar la simulacion.
	 */
	private void initHiddenNodes() {
		boolean visto = false;
		for (Nodo n : mapa.getListaNodos()) {
			if (n.score < 0)
				continue;
			for (Sensor s : estrategia.getSensores()) {
				if (s.isVisto(n)) {
					visto = true;
				}
			}
			if (!visto) {
				if (n.cazador == true)
					continue;
				Logger.debug("No visto: " + n.toString());
				hiddenNodes.add(n);
			}
			visto = false;
		}

	}
	/**
	 * Añade a las presas de forma aleatoria en los nodos no visibles para el cazador.
	 */
	public void addAleatOponent() {
		if (hiddenNodes.size() < 1) {
			Logger.debug("INFO - No se incluyen mas presas porque no hay nodos ocultos");
			return;
		}
		Nodo auxN = null;
		int rng = 0;
		while (auxN == null) {
			rng = Proceso.getPseudoRand();
			if (rng >= hiddenNodes.size())
				continue;
			auxN = hiddenNodes.get(rng);
		}
		Logger.debug("INFO - Rand: " + rng);
		mapa.creaPresa(auxN);
		presas++;
		hiddenNodes.remove(rng);
	}
	/**
	 * Busca empleando la estrategia y la informacion el nodo
	 *  objetivo.
	 * @return Nodo destino
	 */
	public Nodo busca() {
		estrategia.updateMemoria(); // recalcular subestructuras
		Nodo objetivo = estrategia.getObjetivo();
		Nodo aux = new Nodo(0, 0, null);
		if (objetivo == null)
			return null;
		if (actual == null) {
			aux.copyNode(mapa.getCazador());
			actual = aux;
		}
		return objetivo;

	}
	/**
	 * Inicializa todos los recursos de Estado.
	 */
	public void initEstado() {
		initHiddenNodes();
		updateSensores();
		inicio = getActual();
	}

	/**
	 * Actualiza el estado actual con el nodo que recibe por parámetros y
	 *  realiza los nuevos cálculos para la elección del próximo movimiento.
	 * @param nodo Nodo actual.
	 */
	public void updateEstado(Nodo nodo) {

		actual = mapa.setCazador(nodo);
		ArrayList<Nodo> presasList = mapa.getPresas();
		int dist = mapa.getDistancia(actual, inicio);
		for (Nodo aux : presasList) {
			if (mapa.getDistancia(aux, inicio) <= dist) {
				mapa.borraPresa(aux);
				aux.setSalvada(true);
				Logger.debug("Presa salvada: " + aux.imprimePresa() + " Dist: "
						+ mapa.getDistancia(aux, this.inicio));
				this.presas--;
				this.salvadas++;
			}
		}
		updateSensores();
		time++;
	}

	/**
	 * Evalua si un nodo es Calculado
	 * @param x Sensor
	 * @param s Estrategia de la simulacion
	 * @param n Nodo a evaluar
	 * @return
	 */
	public boolean isCalcula(Sensor x, Estrategia s, Nodo n) {
		return x.isVisto(n);
	}

	/**
	 * Evalua si un nodo es una estimacion
	 * @param x Sensor
	 * @param s Estrategia de la simulacion
	 * @param n Nodo a evaluar
	 * @return
	 */
	public boolean isEstima(Sensor x, Estrategia s, Nodo n) {
		return !isCalcula(x, s, n);
	}

	/**
	 * Carga el Mapa.
	 * @return
	 */
	@SuppressWarnings("resource")
	public int[] loadMap() {
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		int[] tamanoMapa = new int[1000];
		try {
			archivo = new File(ProcesoMain.maptext.getText());
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			String linea;

			linea = br.readLine();
			String[] lineaTamano = linea.split(",");

			tamanoMapa[0] = Integer.parseInt(lineaTamano[0]);
			tamanoMapa[1] = Integer.parseInt(lineaTamano[1]);
			int i = 2;
			while ((linea = br.readLine()) != null) {
				lineaTamano = linea.split(",");
				tamanoMapa[i] = Integer.parseInt(lineaTamano[0]);
				tamanoMapa[i + 1] = Integer.parseInt(lineaTamano[1]);
				tamanoMapa[i + 2] = Integer.parseInt(lineaTamano[2]);
				tamanoMapa[i + 3] = Integer.parseInt(lineaTamano[3]);
				i += 4;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return tamanoMapa;
	}
	/**
	 * Inicializa la estructura del mapa.
	 */
	public void initGraph() {
		this.mapa = new Grafo();
		this.mapa.generaGrafo(this.loadMap(), this.definicionMalla);
		if (Proceso.enableGUI)
			this.mapa.plotGraph("Grafo Inicial");
		if (Proceso.randPos == null) {
			Proceso.initPseudoRand(mapa.x, mapa.y);
		} else {
			Proceso.restartPseudoRand();
		}
		mapa.setCazador();
		mapa.setArbolDondeCuenta(mapa.getCazador());
	}
	/**
	 * Almacena la informacion del estado actual de la simulacion
	 * @param dato
	 * @param idIteracion Identificador de la iteracion
	 * @param maxEnemigos Enemigos capturados
	 */
	public void guardaValoresEstado(Datos dato, String idIteracion,
			int maxEnemigos) {
		int capt = maxEnemigos - presas - salvadas;
		int nodos = 0;
		ArrayList<Nodo> ld = estrategia.memoria.getListaNodos();
		for (int i = 0; i < ld.size(); i++)
			if (!ld.get(i).isEstimacion())
				nodos++;

		Logger.debug("" + nodos + "");
		dato.agregaDatos(estrategia.nombre + "," + idIteracion + "," + time
				+ "," + nodos + "," + capt);
	}

	/**
	 * Encapsulamiento para conseguir los nodos adyacentes de un nodo dado.
	 * @param n
	 * @return
	 */
	public ArrayList<Nodo> getAdyacentes(Nodo n) {
		return mapa.getAdjacents(n);
	}
	/**
	 * Actualiza el conocimiento de los sensores en base a la estrategia
	 */
	public void updateSensores() {
		estrategia.updateSensores();
	}
	/**
	 * Evalua la victoria en el estado actual.
	 * @return
	 */
	public boolean evaluaVictoria() {
		if (presas == 0) {
			return true;
		}
		return false;
	}
	/**
	 * Devuelve el nodo donde se encuentra el cazador.
	 * @return
	 */
	public Nodo getActual() {
		return actual;
	}

}
