package HiSeSitor;

import java.util.ArrayList;
import java.util.Random;

public class Estado {

	public Grafo grafo;

	public Random random;
	public ArrayList<int> hiddenNodes = new ArrayList<>();
	public int numHN;
	public Nodo cazador;
	public Nodo inicio;
	public int presas = 0;
	
	public Estado() {
		initHiddenNodes();

	}


//__GUILLE

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

	public Nodo busca(){
		estrategia.update(); //recalcular subestructuras
		Nodo objetivo = estrategia.getObjetivo(); //coger el nodo con mayor puntuación
		return grafo.getShortestPathNode(actual, objetivo);
		
	}
	
	/* codigo obsoleto
	private void minMax(int prof) {
		if (presa == 0 || prof < 0) return;
		ArrayList<Nodo> moves = null;
		moves = getAdyacentes();
		for (Nodo nodo : moves) {
			aux = evaluaNodo(nodo);
			if (valor < aux) {
				aux = valor;
				eleccion = nodo;
		}
	}*/
	

	//me he ajenciado esta funcion
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

	public boolean isCalcula(Sensor x, Estrategia s, Nodo n){
		return x.isVisto(n);
	}
	public boolean isEstima(Sensor x, Estrategia s, Nodo n){
		return !isCalcula(x,s);
	}


//__OTROS NO GUILLE 
	public void initGraph(){} //Recomiendo inicializar el inicio
	public void estima(){}
	//recomiendo el uso de un futuro s.evalua(n, x); para esta funcion
	public void calcula(){}
	public void guardaValoresEstado(){}

	public listaca getAdyacentes(){
		return grafo.getAdyacentes();
	}

	public void updateSensores(){
		estrategia.updateSensores();
	}

	public boolean evaluaVictoria(){
		if (presas==0) {
			return true;
		}
		return false;
	}

}