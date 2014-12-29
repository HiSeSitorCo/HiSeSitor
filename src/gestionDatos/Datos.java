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

		FileWriter fichero = null;
		PrintWriter pw = null;

		int mediaVictoria = 0;
		int mediaNodos = 0;
		int mediaInfo = 0;
		int contadorVictoria = 0;
		int contadorNodos = 0;
		int contadorInfo = 0;

		try {
			fichero = new FileWriter("datosProcesados.txt");
			pw = new PrintWriter(fichero);

			/**
			 * proceso de escritura - pw.println("Linea " + i);
			 **/

			for (int i = 0; i < this.getValoresEstrategias().size(); i++) {

				pw.println("\n"
						+ this.getValoresEstrategias(i).getNombreEstrategia()
						+ ":");

				for (int j = 0; j < this.getValoresEstrategias(i)
						.getValoresIteraciones().size(); j++) {

					pw.println("\n\t- "
							+ this.getValoresEstrategias(i)
									.getValoresIteraciones(j)
									.getNombreIteracion() + ":");

					for (int k = 0; k < this.getValoresEstrategias(i)
							.getValoresIteraciones(j).getValoresLecturas()
							.size(); k++) {

						mediaVictoria += this.getValoresEstrategias(i)
								.getValoresIteraciones(j).getValoresLecturas(k)
								.getVictoria();
						mediaNodos += this.getValoresEstrategias(i)
								.getValoresIteraciones(j).getValoresLecturas(k)
								.getnNodos();
						mediaInfo += this.getValoresEstrategias(i)
								.getValoresIteraciones(j).getValoresLecturas(k)
								.getInformacion();
					}

					mediaVictoria = mediaVictoria
							/ this.getValoresEstrategias(i)
									.getValoresIteraciones(j)
									.getValoresLecturas().size();
					mediaNodos = mediaNodos
							/ this.getValoresEstrategias(i)
									.getValoresIteraciones(j)
									.getValoresLecturas().size();
					mediaInfo = mediaInfo
							/ this.getValoresEstrategias(i)
									.getValoresIteraciones(j)
									.getValoresLecturas().size();

					pw.println("\t\t- victoria media:" + mediaVictoria);
					pw.println("\t\t- media nodos recorridos:" + mediaNodos);
					pw.println("\t\t- informacion adicional:" + mediaInfo);

					contadorVictoria += mediaVictoria;
					contadorNodos += mediaNodos;
					contadorInfo += mediaInfo;

					mediaVictoria = 0;
					mediaNodos = 0;
					mediaInfo = 0;
				}

				contadorVictoria = contadorVictoria
						/ this.getValoresEstrategias(i).getValoresIteraciones()
								.size();
				contadorNodos = contadorNodos
						/ this.getValoresEstrategias(i).getValoresIteraciones()
								.size();
				contadorInfo = contadorInfo
						/ this.getValoresEstrategias(i).getValoresIteraciones()
								.size();

				pw.println("\nTOTAL "
						+ this.getValoresEstrategias(i).getNombreEstrategia()
						+ ":");
				pw.println("\t\t- victoria media:" + contadorVictoria);
				pw.println("\t\t- media nodos recorridos:" + contadorNodos);
				pw.println("\t\t- informacion adicional:" + contadorInfo);

				contadorVictoria = 0;
				contadorNodos = 0;
				contadorInfo = 0;

				pw.println("----------------------------------------------");

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

}
