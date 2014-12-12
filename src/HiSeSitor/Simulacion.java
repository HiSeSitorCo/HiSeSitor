package HiSeSitor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

	public class Simulacion {
		public int intervalo;
		public Estado estado;
		public ArrayList<Sensor> sensores;
		public Nodo nodo;		// repito, por que hay un nodo suelto por aqui???
		public int definicionMalla = 50;
		

		public Simulacion() {
			this.intervalo = 0;
			this.estado = new Estado();
			this.sensores = new ArrayList<Sensor>();
			this.nodo = new Nodo(0, 0);
		}
		
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
			for(int i=0;i<n;i++) 
				estado.addAleatOponent();
		}

		public int[] loadMap(){
			File archivo = null;
		    FileReader fr = null;
		    BufferedReader br = null;
		    int[] tamanoMapa;
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
		
		public void escribeResultados() {
			
		}
		public void createGrafo() {
			estado.initGraph();
			int[] coordenadas = this.loadMap();
			
			int nNodos = (coordenadas[0]*coordenadas[1]*this.definicionMalla)/100;
			
			for(int i=0;i<nNodos;i++){
				Nodo aux = new Nodo(1,0);
				
			}

			
		}
}