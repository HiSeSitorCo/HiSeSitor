package gestionDatos;

import java.util.ArrayList;

public class DatosEstrategia {

	private String nombreEstrategia;
	private ArrayList<DatosIteracion> valoresEstrategia;

	public DatosEstrategia(String nombreEstrategia) {
		this.nombreEstrategia = nombreEstrategia;
		this.valoresEstrategia = new ArrayList<DatosIteracion>();
	}

	public String nombreEstrategia() {
		return nombreEstrategia;
	}

	public ArrayList<DatosIteracion> getValores() {
		return valoresEstrategia;
	}

}