package HiSeSitor;

public class Estado {

	public Grafo grafo;

	public Random random;
	public ArrayList<int> hiddenNodes;
	public int numHN;
	public Nodo cazador;
	public Nodo inicio;
	public int presas = 0;
	public Estado() {
		initHiddenNodes();

	}


//__GUILLE

	public void addAleatOponent(){
		int rng = random.nextInt(numHN+1)
		Nodo auxN = grafo.getNodo(hiddenNodes[rng]);
		grafo.creaPresa(auxN);
		presa++;
		hiddenNodes.remove(rng);
	}

	public void busca(){
	}

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
		updateSensor();
	}

/*Este par de funciones deberén de, con un sensor y una estrategia decidir
si tiene suficiente información para el nodo dado como para poder calcularlo
 o por el contrario solo puede hacer una estimación. La información la da el 
 sensor (de forma binaria o tiene o no tiene) y la estrategia evalua esa información*/
	public boolean isCalcula(Sensor x, Estrategia s, Nodo n){
		return x.getData(n);
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

	public void getAdyacentes(){}

	public void decide(){}

	public void updateSensor(){}

	public void evaluaVictoria(){}

}