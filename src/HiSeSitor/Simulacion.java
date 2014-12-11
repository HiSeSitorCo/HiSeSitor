package HiSeSitor;

import java.util.ArrayList;

	public class Simulacion {
		public int intervalo;
		public Estado estado;
		public ArrayList<Sensor> sensores;
		public Nodo nodo;			//por que hay un nodo aqui?????????????????????????
		

		public Simulacion() {
			this.intervalo = 0;
			this.estado = new Estado();
			this.sensores = new ArrayList<Sensor>();
			this.nodo = new Nodo(0, 0);
		}
		
		
		public void correSimulacion() {
			if (estado == null) {
				estado = initGraph();
				addAleatOponents();
			}
			while((nodo = busca())!= null) {
				nodo = busca();
				guardaValoresEstado();
				updateEstado(nodo);
			}


		}


		public void addAleatOponents(int n) {
			
			for(int i=0;i<n;i++)
				estado.addAleatOponent();

		}

		public void loadMap(){
				
		}
		public void escribeResultados() {
			
		}
		public void createGrafo() {
			estado.initGraph();
			
		}
}
