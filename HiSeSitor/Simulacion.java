package HiSeSitor
	public class Simulacion {
		public Simulacion() {
			int paus;
			public Estado estado;
			public Sensores sensores;
			public Node nodo;
			public correSimulacion() {
				if (estado == null) {
					estado = initGraph();
					addAleatOponents();
				}
				while((nodo = busca())!= null)
				nodo = busca();
				guardaValoresEstado();
				updateEstado(nodo);
			}

			public addAleatOponents() {
			}

			public loadMap(){}
			public escribeResultados() {}
			public createGrafo() {}
			public correSimulacion() {

		}
	}
}