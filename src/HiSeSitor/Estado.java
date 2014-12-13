package HiSeSitor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Estado {

	public Grafo grafo;

	public Random random;
	public ArrayList<Integer> hiddenNodes = new ArrayList<>();
	public int numHN;
	public Nodo cazador;
	public Estrategia estrategia;
	public Nodo inicio;
	public int presas = 0;
	public int definicionMalla = 50;


	public Grafo memoria;
	public Grafo mapa;

	public Estado() {
		initHiddenNodes();

	}

	// __GUILLE

	private void initHiddenNodes() {
		for (Nodo n : grafo.getListaNodos()) {
			for (Sensor s : estrategia.getSensores()) {
				if (s.isVisto()) {
					visto = true;
					break;
				}
			}
			if (!visto) {
				hiddenNodes.Add(n);
			}
			visto = false;
		}

	}

	public void addAleatOponent(){
		int rng = random.nextInt(numHN+1)
		Nodo auxN = grafo.getNodo(hiddenNodes[rng]);
		grafo.creaPresa(auxN);
		presa++;
		hiddenNodes.remove(rng);
	}

	public Nodo busca() {
		estrategia.update(); // recalcular subestructuras
		Nodo objetivo = estrategia.getObjetivo(); // coger el nodo con mayor
													// puntuación
		return grafo.getShortestPathNode(actual, objetivo);

	}

	/*
	 * codigo obsoleto private void minMax(int prof) { if (presa == 0 || prof <
	 * 0) return; ArrayList<Nodo> moves = null; moves = getAdyacentes(); for
	 * (Nodo nodo : moves) { aux = evaluaNodo(nodo); if (valor < aux) { aux =
	 * valor; eleccion = nodo; } }
	 */

	// me he ajenciado esta funcion
	public void updateEstado(Nodo nodo) {

		cazador = grafo.setCazador(nodo);
		ArrayList<Nodo> presas = grafo.getPresas();
		int dist = grafo.getDistancia(cazador, inicio);
		for (Nodo aux : presas) {
			if (grafo.getDistancia(aux, inicio) > dist) {
				presas--;
				grafo.borraPresa(aux);
			}

		}
		updateSensores();
	}

	public boolean isCalcula(Sensor x, Estrategia s, Nodo n) {
		return x.isVisto(n);
	}

	public boolean isEstima(Sensor x, Estrategia s, Nodo n) {
		return !isCalcula(x, s);
	}

	@SuppressWarnings("resource")
	public int[] loadMap(){
		File archivo = null;
	    FileReader fr = null;
	    BufferedReader br = null;
	    int[] tamanoMapa = new int[1000];
	    try {
	         archivo = new File ("mapa1.txt");
	         fr = new FileReader (archivo);
	         br = new BufferedReader(fr);

	         String linea;

	         linea=br.readLine();
	         String[] lineaTamano = linea.split(",");

	         tamanoMapa[0] = Integer.parseInt(lineaTamano[0]);
	         tamanoMapa[1] = Integer.parseInt(lineaTamano[1]);
	         int i=2;
	         while((linea=br.readLine())!=null){
	        	 lineaTamano = linea.split(",");
	        	 tamanoMapa[i]=Integer.parseInt(lineaTamano[0]);
	        	 i++;
	        	 tamanoMapa[i]=Integer.parseInt(lineaTamano[1]);
	        	 i++;
	        	 tamanoMapa[i]=Integer.parseInt(lineaTamano[0]);
	        	 i++;
	        	 tamanoMapa[i]=Integer.parseInt(lineaTamano[1]);
	        	 i++;
	         }

	      }
	      catch(Exception e){
	         e.printStackTrace();
	      }
	    return tamanoMapa;
	}

	// __OTROS NO GUILLE
	public void initGraph() {
		this.grafo = new Grafo();

		ArrayList<Nodo> lista1 = new ArrayList<Nodo>();
		ArrayList<Nodo> lista2 = new ArrayList<Nodo>();

		int[] coordenadas = this.loadMap();

		int nNodosX = (coordenadas[0]*this.definicionMalla)/100;
		int nNodosY = (coordenadas[1]*this.definicionMalla)/100;

		for(int i=0;i<nNodosY;i++){

			Nodo a = new Nodo(1, 0);
			for(int j=0;j<nNodosX;j++){

				Nodo aux = new Nodo(1,0);

				if (lista1.get(j)!=null)
					this.grafo.addEdge(1, a, lista1.get(j));

				if (lista1.get(j-1)!=null)
					this.grafo.addEdge(1, a, lista1.get(j-1));

				if (lista1.get(j+1)!=null)
					this.grafo.addEdge(1, a, lista1.get(j+1));

				lista2.add(aux);
				this.grafo.addEdge(1, a, aux);
				a = aux;
			}

			lista1.clear();
			lista1 = lista2;
			lista2.clear();

		}

		// falta quitar las aristas que atraviesan muros
	}

	public void estima() {
	}

	// recomiendo el uso de un futuro s.evalua(n, x); para esta funcion
	public void calcula() {
	}

	public void guardaValoresEstado() {
	}

	public List<Nodo> getAdyacentes(Nodo n) {
		return grafo.getAdjacents(n);
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

}
