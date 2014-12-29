package gestionDatos;

import java.util.ArrayList;

public class DatosIteracion {

	private String nombreIteracion;
	private ArrayList<Lectura> valoresLecturas;

	public DatosIteracion(String nombreIteracion) {
		this.nombreIteracion = nombreIteracion;
		this.valoresLecturas = new ArrayList<Lectura>();
	}

	public String getNombreIteracion() {
		return nombreIteracion;
	}

	public ArrayList<Lectura> getValoresLecturas() {
		return valoresLecturas;
	}

	public Lectura getValoresLecturas(int i) {
		return valoresLecturas.get(i);
	}

}
