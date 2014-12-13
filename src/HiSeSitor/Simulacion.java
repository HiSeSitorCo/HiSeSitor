package HiSeSitor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Simulacion {
	public int intervalo;
	public Estado estado;
	public ArrayList<Sensor> sensores;


	public Simulacion(ArrayList<Sensor> sensores) {
		this.intervalo = 0;
		this.estado = new Estado();
		this.sensores = sensores;
	}
	


	public void correSimulacion() {
		if (estado == null) {
			int enemigos = 5; //OJO ESTA PUESTO HARDCODE
			estado = new Estado();
			estado.initGraph();
			addAleatOponents(enemigos);
		}
		while((nodo = estado.busca())!= null) {
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
