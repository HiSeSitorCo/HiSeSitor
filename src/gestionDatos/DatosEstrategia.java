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

	public DatosIteracion masCapturadas() {

		DatosIteracion d = new DatosIteracion("auxiliar");
		Lectura l = new Lectura(999999, 0, 0);
		d.getValoresLecturas().add(l);

		for (int i = 0; i < this.getValoresIteraciones().size(); i++) {

			if (d.getValoresLecturas(d.getValoresLecturas().size() - 1)
					.getCapturados() < this
					.getValoresIteraciones(i)
					.getValoresLecturas(
							this.getValoresIteraciones(i).getValoresLecturas()
									.size() - 1).getCapturados()) {

				d = this.getValoresIteraciones(i);
			}

			if (d.getValoresLecturas(d.getValoresLecturas().size() - 1)
					.getCapturados() == this
					.getValoresIteraciones(i)
					.getValoresLecturas(
							this.getValoresIteraciones(i).getValoresLecturas()
									.size() - 1).getCapturados()) {

				if (d.getValoresLecturas(d.getValoresLecturas().size() - 1)
						.getTiempo() > this
						.getValoresIteraciones(i)
						.getValoresLecturas(
								this.getValoresIteraciones(i)
										.getValoresLecturas().size() - 1)
						.getTiempo()) {

					d = this.getValoresIteraciones(i);
				}
			}

			if (d.getValoresLecturas(d.getValoresLecturas().size() - 1)
					.getCapturados() == this
					.getValoresIteraciones(i)
					.getValoresLecturas(
							this.getValoresIteraciones(i).getValoresLecturas()
									.size() - 1).getCapturados()) {

				if (d.getValoresLecturas(d.getValoresLecturas().size() - 1)
						.getTiempo() == this
						.getValoresIteraciones(i)
						.getValoresLecturas(
								this.getValoresIteraciones(i)
										.getValoresLecturas().size() - 1)
						.getTiempo()) {

					if (d.getValoresLecturas(d.getValoresLecturas().size() - 1)
							.getnNodos() <= this
							.getValoresIteraciones(i)
							.getValoresLecturas(
									this.getValoresIteraciones(i)
											.getValoresLecturas().size() - 1)
							.getnNodos()) {

						d = this.getValoresIteraciones(i);
					}
				}
			}
		}
		return d;
	}

	public DatosIteracion masEficiente() {

		DatosIteracion d = new DatosIteracion("auxiliar");
		Lectura l = new Lectura(999999, 0, 0);
		d.getValoresLecturas().add(l);

		for (int i = 0; i < this.getValoresIteraciones().size(); i++) {

			if ((d.getValoresLecturas(d.getValoresLecturas().size() - 1)
					.getCapturados() / d.getValoresLecturas(
					d.getValoresLecturas().size() - 1).getTiempo()) < (this
					.getValoresIteraciones(i)
					.getValoresLecturas(
							this.getValoresIteraciones(i).getValoresLecturas()
									.size() - 1).getCapturados() / this
					.getValoresIteraciones(i)
					.getValoresLecturas(
							this.getValoresIteraciones(i).getValoresLecturas()
									.size() - 1).getCapturados())) {

				d = this.getValoresIteraciones(i);
			}

			if ((d.getValoresLecturas(d.getValoresLecturas().size() - 1)
					.getCapturados() / d.getValoresLecturas(
					d.getValoresLecturas().size() - 1).getTiempo()) == (this
					.getValoresIteraciones(i)
					.getValoresLecturas(
							this.getValoresIteraciones(i).getValoresLecturas()
									.size() - 1).getCapturados() / this
					.getValoresIteraciones(i)
					.getValoresLecturas(
							this.getValoresIteraciones(i).getValoresLecturas()
									.size() - 1).getCapturados())) {

				if (d.getValoresLecturas(d.getValoresLecturas().size() - 1)
						.getTiempo() > this
						.getValoresIteraciones(i)
						.getValoresLecturas(
								this.getValoresIteraciones(i)
										.getValoresLecturas().size() - 1)
						.getTiempo()) {

					d = this.getValoresIteraciones(i);
				}
			}
		}
		return d;
	}

	public DatosIteracion masVisibles() {

		DatosIteracion d = new DatosIteracion("auxiliar");
		Lectura l = new Lectura(999999, 0, 0);
		d.getValoresLecturas().add(l);

		for (int i = 0; i < this.getValoresIteraciones().size(); i++) {

			if (d.getValoresLecturas(d.getValoresLecturas().size() - 1)
					.getnNodos() < this
					.getValoresIteraciones(i)
					.getValoresLecturas(
							this.getValoresIteraciones(i).getValoresLecturas()
									.size() - 1).getnNodos()) {

				d = this.getValoresIteraciones(i);
			}

			if (d.getValoresLecturas(d.getValoresLecturas().size() - 1)
					.getnNodos() == this
					.getValoresIteraciones(i)
					.getValoresLecturas(
							this.getValoresIteraciones(i).getValoresLecturas()
									.size() - 1).getnNodos()) {

				if (d.getValoresLecturas(d.getValoresLecturas().size() - 1)
						.getCapturados() > this
						.getValoresIteraciones(i)
						.getValoresLecturas(
								this.getValoresIteraciones(i)
										.getValoresLecturas().size() - 1)
						.getCapturados()) {

					d = this.getValoresIteraciones(i);
				}
			}
		}
		return d;
	}
}