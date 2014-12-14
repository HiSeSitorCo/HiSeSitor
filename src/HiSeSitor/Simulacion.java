package HiSeSitor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Simulacion {
	public int intervalo;
	public Estado estado;
	public ArrayList<Sensor> sensores;


	public Simulacion(ArrayList<Sensor> sensores, Estrategia estr) {
		this.intervalo = 0;
		//this.estado = new Estado(estr);
		this.sensores = sensores;
	}
	


	public void correSimulacion(Estrategia estr) {
		Nodo nodo = null;
		if (estado == null) {
			int enemigos = 5; //OJO ESTA PUESTO HARDCODE
			estado = new Estado(estr);
			for (Sensor x : estr.sensores)
				x.setEstado(estado);
			estr.setEstado(estado);
			estado.initGraph();
			addAleatOponents(enemigos);
			estado.initEstado();
			
		}
		//estado.mapa.plotGraph();

		System.out.println("Nuestro objetivo, pillar a " + estado.presas + " Malditos malnacidos");

		System.out.println("A buscar!");
		while((nodo = estado.busca())!= null && estado.presas > 0) {

			System.out.println("A buscar! Quedan:" + estado.presas);
			estado.guardaValoresEstado();
			estado.updateEstado(nodo);
		}


	}


	public void addAleatOponents(int n) {
		for(int i=0;i<n;i++)
			estado.addAleatOponent();
	}


	public void escribeResultados() {

	}
	public void createGrafo() {
		estado.initGraph();
	}


}
