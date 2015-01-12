package gestionDatos;

import java.io.FileWriter;
import java.io.PrintWriter;
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

			DatosIteracion di = new DatosIteracion(recibido[1],
					Integer.parseInt(recibido[2]),
					Integer.parseInt(recibido[3]),
					Integer.parseInt(recibido[4]));
			DatosEstrategia de = new DatosEstrategia(recibido[0]);

			de.getValoresIteraciones().add(di);
			this.getValoresEstrategias().add(de);

			// buscamos si existe la iteracion
		} else {
			auxIter = this.getValoresEstrategias(auxEstr).contieneIteracion(
					recibido[1]);

			// si no existe, la creamos asi como la lectura que conlleva
			if (auxIter == -1) {

				DatosIteracion di = new DatosIteracion(recibido[1],
						Integer.parseInt(recibido[2]),
						Integer.parseInt(recibido[3]),
						Integer.parseInt(recibido[4]));

				this.getValoresEstrategias(auxEstr).getValoresIteraciones()
						.add(di);
			}
		}

	}

	public void procesaDatos() {

		FileWriter fichero = null;
		PrintWriter pw = null;

		try {
			fichero = new FileWriter("datosProcesados.txt");
			pw = new PrintWriter(fichero);

			/**
			 * proceso de escritura - pw.println("Linea " + i);
			 **/

			for (int i = 0; i < this.getValoresEstrategias().size(); i++) {

				pw.println(this.getValoresEstrategias(i).getNombreEstrategia()
						+ ","
						+ this.getValoresEstrategias(i).mejorIteracion()
								.getNombreIteracion());

				pw.println(this.getValoresEstrategias(i).mejorIteracion()
						.getTiempo()
						+ ","
						+ this.getValoresEstrategias(i).mejorIteracion()
								.getnNodos()
						+ ","
						+ this.getValoresEstrategias(i).mejorIteracion()
								.getCapturados());

				pw.println("-----------------------------");

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fichero)
					fichero.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public DatosIteracion mejorIteracion(String estrategia) {

		int i;
		for (i = 0; i < this.getValoresEstrategias().size(); i++) {
			if (this.getValoresEstrategias(i).getNombreEstrategia()
					.equals(estrategia))
				break;
		}

		return this.getValoresEstrategias(i).mejorIteracion();
	}

}
