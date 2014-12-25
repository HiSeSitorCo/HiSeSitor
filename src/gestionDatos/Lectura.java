package gestionDatos;

public class Lectura {

	private int victoria;
	private int nNodos;
	private int informacion;

	public Lectura(int victoria, int nNodos, int informacion) {
		this.victoria = victoria;
		this.nNodos = nNodos;
		this.informacion = informacion;
	}

	public int getVictoria() {
		return victoria;
	}

	public int getnNodos() {
		return nNodos;
	}

	public int getInformacion() {
		return informacion;
	}

}
