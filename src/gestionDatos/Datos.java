package gestionDatos;

import java.util.ArrayList;

public class Datos {

	private ArrayList<DatosEstrategia> valoresEstrategias;

	public Datos() {
		this.valoresEstrategias = new ArrayList<DatosEstrategia>();
	}

	public ArrayList<DatosEstrategia> getValoresEstrategias() {
		return valoresEstrategias;
	}

	public DatosEstrategia getValoresEstrategias(int i) {
		return valoresEstrategias.get(i);
	}

	public int contieneEstrategia(String s) {

		for (int i = 0; i < this.valoresEstrategias.size(); i++) {
			if (this.getValoresEstrategias(i).getNombreEstrategia().equals(s))
				return i;
		}
		return -1;
	}

	public void agregaDatos(String cadena) {

		String[] recibido = cadena.split(",");
		int auxEstr = this.contieneEstrategia(recibido[0]);
		int auxIter = 0;

		// si no existe ningna estrategia la creamos asi como la primera
		// iteracion y la lectura que esta conlleva
		if (auxEstr == -1) {
			Lectura lectura = new Lectura(Integer.parseInt(recibido[2]),
					Integer.parseInt(recibido[3]),
					Integer.parseInt(recibido[4]));

			DatosIteracion di = new DatosIteracion(recibido[1]);
			DatosEstrategia de = new DatosEstrategia(recibido[0]);

			di.getValoresLecturas().add(lectura);
			de.getValoresIteraciones().add(di);
			this.getValoresEstrategias().add(de);

			// buscamos si existe la iteracion
		} else {
			auxIter = this.getValoresEstrategias(auxEstr).contieneIteracion(
					recibido[1]);

			// si no existe, la creamos asi como la lectura que conlleva
			if (auxIter == -1) {
				Lectura lectura = new Lectura(Integer.parseInt(recibido[2]),
						Integer.parseInt(recibido[3]),
						Integer.parseInt(recibido[4]));

				DatosIteracion di = new DatosIteracion(recibido[1]);

				di.getValoresLecturas().add(lectura);
				this.getValoresEstrategias(auxEstr).getValoresIteraciones()
						.add(di);

				// creamos la lectura y la añadimos tal cual a la iteracion
				// existente
			} else {
				Lectura lectura = new Lectura(Integer.parseInt(recibido[2]),
						Integer.parseInt(recibido[3]),
						Integer.parseInt(recibido[4]));

				this.getValoresEstrategias(auxEstr)
						.getValoresIteraciones(auxIter).getValoresLecturas()
						.add(lectura);
			}
		}

	}

	public void procesaDatos() {

	}

}
