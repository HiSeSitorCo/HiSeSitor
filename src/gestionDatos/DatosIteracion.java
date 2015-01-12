package gestionDatos;

public class DatosIteracion {

	private String nombreIteracion;
	private int tiempo;
	private int nNodos;
	private int capturados;

	public DatosIteracion(String nombreIteracion,int Tiempo, int nNodos, int Capturados) {
		this.tiempo = Tiempo;
		this.nNodos = nNodos;
		this.capturados = Capturados;
		this.nombreIteracion = nombreIteracion;
	}

	public String getNombreIteracion() {
		return nombreIteracion;
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
