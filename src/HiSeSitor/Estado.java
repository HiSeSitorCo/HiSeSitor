package HiSeSitor;

import gestionDatos.Datos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

public class Estado {

	public Random random = new Random();
	public ArrayList<Nodo> hiddenNodes = new ArrayList<>();
	public int numHN;
	public Nodo actual;
	public Estrategia estrategia;
	public Nodo inicio;
	public int presas = 0;
	public int salvadas = 0;
	public int definicionMalla = 100;
	public int time = 0;

	public Grafo memoria;
	public Grafo mapa;

	public Estado(Estrategia estr) {
		initGraph();
		this.estrategia = estr;
	}

	// __GUILLE

	private void initHiddenNodes() {
		boolean visto = false;
		for (Nodo n : mapa.getListaNodos()) {
			if(n.score < 0)
				continue;
			for (Sensor s : estrategia.getSensores()) {
				if (s.isVisto(n)) {

					visto = true;
					//break;
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

	public void addAleatOponent() {
		if (hiddenNodes.size() < 1){
			Logger.debug("INFO - No se aÒaden mas presas porque no hay nodos ocultos");
			return;
		}
		Nodo auxN = null;
		int rng = 0;
		while(auxN == null){
		rng = Proceso.getPseudoRand();
		if(rng >= hiddenNodes.size())
			continue;
	    auxN = hiddenNodes.get(rng);
		}
		Logger.debug("INFO - Rand: "+rng);
		mapa.creaPresa(auxN);
		presas++;
		hiddenNodes.remove(rng);
	}

	public Nodo busca() {
		estrategia.updateMemoria(); // recalcular subestructuras
		Nodo objetivo = estrategia.getObjetivo(); // coger el nodo con mayor
													// puntuaci√≥n
		if (objetivo == null)
			return null;
		if (actual == null) {
			actual = mapa.getCazador();
		}
		return mapa.getShortestPathNode(getActual(), objetivo);

	}

	public void initEstado() {
		initHiddenNodes();
		updateSensores();
		inicio = getActual();
	}

	public void updateEstado(Nodo nodo) {

		actual = mapa.setCazador(nodo);
		ArrayList<Nodo> presasList = mapa.getPresas();
		int dist = mapa.getDistancia(actual, inicio);
		for (Nodo aux : presasList) {
			if (mapa.getDistancia(aux, inicio) <= dist) {
				presas--;
				mapa.borraPresa(aux);
				Logger.debug("Presa salvada: "+aux.toString());
				this.presas--;
				this.salvadas++;
			}
		}
		updateSensores();
		time++;
	}

	public boolean isCalcula(Sensor x, Estrategia s, Nodo n) {
		return x.isVisto(n);
	}

	public boolean isEstima(Sensor x, Estrategia s, Nodo n) {
		return !isCalcula(x, s, n);
	}

	@SuppressWarnings("resource")
	public int[] loadMap() {
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		int[] tamanoMapa = new int[1000];
		try {
			archivo = new File("mapa1.txt");
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

	public void initGraph() {
		this.mapa = new Grafo();
		this.mapa.generaGrafo(this.loadMap(), this.definicionMalla);
		if(Proceso.enableGUI)
		this.mapa.plotGraph("Grafo Inicial");
		if(Proceso.randPos == null){
			Proceso.initPseudoRand(mapa.x, mapa.y);
		}else{
			Proceso.restartPseudoRand();
		}
		mapa.setCazador();
		mapa.setArbolDondeCuenta(mapa.getCazador());
	}

	public void guardaValoresEstado(Datos dato, String idIteracion, int maxEnemigos) {
		int capt =  maxEnemigos - presas - salvadas;
		int nodos = estrategia.memoria.getListaNodos().size();
		Logger.debug("" + nodos + "");
		dato.agregaDatos(estrategia.nombre + "," + idIteracion + "," + time + "," + nodos + "," + capt);
	}

	public ArrayList<Nodo> getAdyacentes(Nodo n) {
		return mapa.getAdjacents(n);
	}

	public void updateSensores() {
		estrategia.updateSensores();
	}

	public boolean evaluaVictoria() {
		if (presas == 0) {
			return true;
		}
		return false;
	}

	public Nodo getActual() {
		return mapa.getCazador();
	}

}
