package gestionDatos;

import java.util.ArrayList;

public class DatosIteracion {

	private String nombreIteracion;
	private ArrayList<Lectura> valoresIteracion;

	public DatosIteracion(String nombreIteracion) {
		this.nombreIteracion = nombreIteracion;
		this.valoresIteracion = new ArrayList<Lectura>();
	}

	public String getNombreIteracion() {
		return nombreIteracion;
	}

	public ArrayList<Lectura> getValores() {
		return valoresIteracion;
	}

}
