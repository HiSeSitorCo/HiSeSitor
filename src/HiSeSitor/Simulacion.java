package HiSeSitor;

	//comentario para test
	public class Simulacion {
		public int intervalo;
		public Estado estado;
		public Sensores sensores;
		public Node nodo;
		

		public void Simulacion() {}
		public void correSimulacion() {
			if (estado == null) {
				estado = estado.initGraph();
				addAleatOponents();
			}
			while((nodo = busca())!= null) {
				nodo = estado.busca();
				estado.guardaValoresEstado();
				estado.updateEstado(nodo);
			}


		}


		public void addAleatOponents(int n) {
			while (n--) estado.addAleatOponent();
		}

		public void loadMap(){
				
		}
		public void escribeResultados() {
			
		}
		public void createGrafo() {
			
		}
}