package gestionDatos;

import java.util.ArrayList;

public class DatosIteracion {

	private String nombreIteracion;
	private ArrayList<Lectura> valoresIteracion;
	private ArrayList<Lectura> valoresLecturas;

	public DatosIteracion(String nombreIteracion) {
		this.nombreIteracion = nombreIteracion;
		this.valoresIteracion = new ArrayList<Lectura>();
		this.valoresLecturas = new ArrayList<Lectura>();
	}

	public String getNombreIteracion() {
		return nombreIteracion;
	}

	public ArrayList<Lectura> getValores() {
		return valoresIteracion;
	}

	public ArrayList<Lectura> getValoresLecturas() {
		return valoresLecturas;
	}

	public Lectura getValoresLecturas(int i) {
		return valoresLecturas.get(i);
	}

}