package gestionDatos;

public class Lectura {

	private int tiempo;
	private int nNodos;
	private int capturados;

	public Lectura(int Tiempo, int nNodos, int Capturados) {
		this.tiempo = Tiempo;
		this.nNodos = nNodos;
		this.capturados = Capturados;
	}

	public int getTiempo() {
		return tiempo;
	}

	public int getnNodos() {
		return nNodos;
	}

	public int getCapturados() {
		return capturados;
	}

}
