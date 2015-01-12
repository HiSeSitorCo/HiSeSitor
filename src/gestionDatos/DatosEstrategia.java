package gestionDatos;

import java.util.ArrayList;

public class DatosEstrategia {

	private String nombreEstrategia;
	private ArrayList<DatosIteracion> valoresIteraciones;

	public DatosEstrategia(String nombreEstrategia) {
		this.nombreEstrategia = nombreEstrategia;
		this.valoresIteraciones = new ArrayList<DatosIteracion>();
	}

	public String getNombreEstrategia() {
		return nombreEstrategia;
	}

	public ArrayList<DatosIteracion> getValoresIteraciones() {
		return valoresIteraciones;
	}

	public DatosIteracion getValoresIteraciones(int i) {
		return valoresIteraciones.get(i);
	}

	public int contieneIteracion(String s) {

		for (int i = 0; i < this.valoresIteraciones.size(); i++) {
			if (this.getValoresIteraciones(i).getNombreIteracion().equals(s))
				return i;
		}
		return -1;
	}

	public DatosIteracion mejorIteracion() {

		DatosIteracion d = new DatosIteracion("auxiliar", 99, 0, 0);

		for (int i = 0; i < this.getValoresIteraciones().size(); i++) {

			if (d.getCapturados() < this.getValoresIteraciones(i)
					.getCapturados()) {

				d = this.getValoresIteraciones(i);
			}

			if (d.getCapturados() == this.getValoresIteraciones(i)
					.getCapturados()) {

				if (d.getTiempo() > this.getValoresIteraciones(i).getTiempo()) {

					d = this.getValoresIteraciones(i);
				}
			}

			if (d.getCapturados() == this.getValoresIteraciones(i)
					.getCapturados()) {

				if (d.getTiempo() == this.getValoresIteraciones(i).getTiempo()) {

					if (d.getnNodos() <= this.getValoresIteraciones(i)
							.getnNodos()) {

						d = this.getValoresIteraciones(i);
					}
				}
			}
		}
		return d;
	}

}