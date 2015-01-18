package gestionDatos;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

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

	public void procesaDatos(String nombre, ArrayList<DatosIteracion> datos) {

		FileWriter fichero = null;
		PrintWriter pw = null;

		try {
			fichero = new FileWriter(nombre);
			pw = new PrintWriter(fichero);

			for (DatosIteracion d : datos) {

				pw.println("nombre iteracion: " + d.getNombreIteracion());

				for (Lectura l : d.getValoresLecturas()) {
					pw.println(l.getTiempo() + "," + l.getnNodos() + ","
							+ l.getCapturados());
				}
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

	/**
	 * mejor iteracion de una estrategia
	 * 
	 * @param estrategia
	 * @return
	 */
	public DatosIteracion mejorIteracionCapturados() {

		ArrayList<DatosIteracion> array = new ArrayList<DatosIteracion>();

		for (DatosEstrategia e : this.getValoresEstrategias()) {
			array.add(this.mejorIteracionCapturados(e.getValoresIteraciones()));
		}
		return this.mejorIteracionCapturados(array);
	}

	/**
	 * mejor iteracion de una estrategia
	 * 
	 * @param estrategia
	 * @return
	 */
	public DatosIteracion mejorIteracionCapturados(String estrategia) {

		int i;
		for (i = 0; i < this.getValoresEstrategias().size(); i++) {
			if (this.getValoresEstrategias(i).getNombreEstrategia()
					.equals(estrategia))
				break;
		}
		return this.getValoresEstrategias(i).masCapturadas();
	}

	/**
	 * mejor iteracion de un array de iteraciones
	 * 
	 * @param datos
	 * @return
	 */
	public DatosIteracion mejorIteracionCapturados(
			ArrayList<DatosIteracion> datos) {

		DatosIteracion d = new DatosIteracion("auxiliar");
		Lectura l = new Lectura(999999, 0, 0);
		d.getValoresLecturas().add(l);

		for (int i = 0; i < datos.size(); i++) {

			if (d.getValoresLecturas(d.getValoresLecturas().size() - 1)
					.getCapturados() < datos
					.get(i)
					.getValoresLecturas(
							datos.get(i).getValoresLecturas().size() - 1)
					.getCapturados()) {

				d = datos.get(i);
			}

			if (d.getValoresLecturas(d.getValoresLecturas().size() - 1)
					.getCapturados() == datos
					.get(i)
					.getValoresLecturas(
							datos.get(i).getValoresLecturas().size() - 1)
					.getCapturados()) {

				if (d.getValoresLecturas(d.getValoresLecturas().size() - 1)
						.getTiempo() > datos
						.get(i)
						.getValoresLecturas(
								datos.get(i).getValoresLecturas().size() - 1)
						.getTiempo()) {

					d = datos.get(i);
				}
			}

			if (d.getValoresLecturas(d.getValoresLecturas().size() - 1)
					.getCapturados() == datos
					.get(i)
					.getValoresLecturas(
							datos.get(i).getValoresLecturas().size() - 1)
					.getCapturados()) {

				if (d.getValoresLecturas(d.getValoresLecturas().size() - 1)
						.getTiempo() == datos
						.get(i)
						.getValoresLecturas(
								datos.get(i).getValoresLecturas().size() - 1)
						.getTiempo()) {

					if (d.getValoresLecturas(d.getValoresLecturas().size() - 1)
							.getnNodos() <= datos
							.get(i)
							.getValoresLecturas(
									datos.get(i).getValoresLecturas().size() - 1)
							.getnNodos()) {

						d = datos.get(i);
					}
				}
			}
		}
		return d;
	}

	/**
	 * iteracion mas optimizada de una estrategia
	 * 
	 * @param estrategia
	 * @return
	 */
	public DatosIteracion mejorIteracionOptimizada() {

		ArrayList<DatosIteracion> array = new ArrayList<DatosIteracion>();

		for (DatosEstrategia e : this.getValoresEstrategias()) {
			array.add(this.mejorIteracionOptimizada(e.getValoresIteraciones()));
		}
		return this.mejorIteracionOptimizada(array);
	}

	/**
	 * iteracion mas optimizada de una estrategia
	 * 
	 * @param estrategia
	 * @return
	 */
	public DatosIteracion mejorIteracionOptimizada(String estrategia) {

		int i;
		for (i = 0; i < this.getValoresEstrategias().size(); i++) {
			if (this.getValoresEstrategias(i).getNombreEstrategia()
					.equals(estrategia))
				break;
		}
		return this.getValoresEstrategias(i).masEficiente();
	}

	/**
	 * iteracion mas optimizada de un array de datosIteracion
	 * 
	 * @param datos
	 * @return
	 */
	public DatosIteracion mejorIteracionOptimizada(
			ArrayList<DatosIteracion> datos) {
		DatosIteracion d = new DatosIteracion("auxiliar");
		Lectura l = new Lectura(999999, 0, 0);
		d.getValoresLecturas().add(l);

		for (int i = 0; i < datos.size(); i++) {

			if ((d.getValoresLecturas(d.getValoresLecturas().size() - 1)
					.getCapturados()
					/ d.getValoresLecturas(d.getValoresLecturas().size() - 1)
							.getTiempo() + 1) < (datos
					.get(i)
					.getValoresLecturas(
							datos.get(i).getValoresLecturas().size() - 1)
					.getCapturados()
					/ datos.get(i)
							.getValoresLecturas(
									datos.get(i).getValoresLecturas().size() - 1)
							.getTiempo() + 1)) {

				d = datos.get(i);
			}

			if ((d.getValoresLecturas(d.getValoresLecturas().size() - 1)
					.getCapturados()
					/ d.getValoresLecturas(d.getValoresLecturas().size() - 1)
							.getTiempo() + 1) == (datos
					.get(i)
					.getValoresLecturas(
							datos.get(i).getValoresLecturas().size() - 1)
					.getCapturados()
					/ datos.get(i)
							.getValoresLecturas(
									datos.get(i).getValoresLecturas().size() - 1)
							.getTiempo() + 1)) {

				if (d.getValoresLecturas(d.getValoresLecturas().size() - 1)
						.getTiempo() > datos
						.get(i)
						.getValoresLecturas(
								datos.get(i).getValoresLecturas().size() - 1)
						.getTiempo()) {

					d = datos.get(i);
				}
			}
		}
		return d;
	}

	/**
	 * iteracion mas optimizada de una estrategia
	 * 
	 * @param estrategia
	 * @return
	 */
	public DatosIteracion mejorIteracionVisibles() {

		ArrayList<DatosIteracion> array = new ArrayList<DatosIteracion>();

		for (DatosEstrategia e : this.getValoresEstrategias()) {
			array.add(this.mejorIteracionVisibles(e.getValoresIteraciones()));
		}
		return this.mejorIteracionVisibles(array);
	}

	/**
	 * iteracion mas optimizada de una estrategia
	 * 
	 * @param estrategia
	 * @return
	 */
	public DatosIteracion mejorIteracionVisibles(String estrategia) {

		int i;
		for (i = 0; i < this.getValoresEstrategias().size(); i++) {
			if (this.getValoresEstrategias(i).getNombreEstrategia()
					.equals(estrategia))
				break;
		}
		return this.getValoresEstrategias(i).masEficiente();
	}

	/**
	 * iteracion que mas nodos ve
	 * 
	 * @param datos
	 * @return
	 */
	public DatosIteracion mejorIteracionVisibles(ArrayList<DatosIteracion> datos) {

		DatosIteracion d = new DatosIteracion("auxiliar");
		Lectura l = new Lectura(999999, 0, 0);
		d.getValoresLecturas().add(l);

		for (int i = 0; i < datos.size(); i++) {

			if (d.getValoresLecturas(d.getValoresLecturas().size() - 1)
					.getnNodos() < datos
					.get(i)
					.getValoresLecturas(
							datos.get(i).getValoresLecturas().size() - 1)
					.getnNodos()) {

				d = datos.get(i);
			}

			if (d.getValoresLecturas(d.getValoresLecturas().size() - 1)
					.getnNodos() == datos
					.get(i)
					.getValoresLecturas(
							datos.get(i).getValoresLecturas().size() - 1)
					.getnNodos()) {

				if (d.getValoresLecturas(d.getValoresLecturas().size() - 1)
						.getCapturados() > datos
						.get(i)
						.getValoresLecturas(
								datos.get(i).getValoresLecturas().size() - 1)
						.getCapturados()) {

					d = datos.get(i);
				}
			}
		}
		return d;
	}
		public void muestraVentana() {
		
				JFrame frame = new JFrame("Resultados");
		        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        
				
				frame.getContentPane().setLayout((new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS)));
				
				
				ArrayList<DatosEstrategia> arrayC = new ArrayList<DatosEstrategia>();
				ArrayList<DatosEstrategia> arrayCT = new ArrayList<DatosEstrategia>();
				ArrayList<DatosEstrategia> arrayN = new ArrayList<DatosEstrategia>();
		
				for (DatosEstrategia e : this.getValoresEstrategias()) {
					DatosEstrategia d1 = new DatosEstrategia(e.getNombreEstrategia());
					DatosEstrategia d2 = new DatosEstrategia(e.getNombreEstrategia());
					DatosEstrategia d3 = new DatosEstrategia(e.getNombreEstrategia());
					d1.getValoresIteraciones().add(
							this.mejorIteracionCapturados(e.getValoresIteraciones()));
					d2.getValoresIteraciones().add(
							this.mejorIteracionOptimizada(e.getValoresIteraciones()));
					d3.getValoresIteraciones().add(
							this.mejorIteracionVisibles(e.getValoresIteraciones()));
					arrayC.add(d1);
					arrayCT.add(d2);
					arrayN.add(d3);
				}
			    
				frame.getContentPane().add(new JLabel("\n\n\n"));
				
				for (DatosEstrategia d : arrayC) {
					JLabel texto = new JLabel();
					texto.setText("\t\tLa iteracion de la estrategia "
							+ d.getNombreEstrategia()
							+ " que más enemigos ha capturado ha sido con la configuracion "
							+ d.getValoresIteraciones(0).getNombreIteracion());
					frame.getContentPane().add(texto);
				}
				frame.getContentPane().add(new JLabel("\n\n\n"));
				for (DatosEstrategia d : arrayCT) {
					JLabel texto = new JLabel();
					texto.setText("\t\tLa iteracion de la estrategia "
							+ d.getNombreEstrategia()
							+ " más optima (capturados/tiempo) ha sido con la configuracion "
							+ d.getValoresIteraciones(0).getNombreIteracion());
					frame.getContentPane().add(texto);
				}
				frame.getContentPane().add(new JLabel("\n\n\n"));
				for (DatosEstrategia d : arrayN) {
					JLabel texto = new JLabel();
					texto.setText("\t\tLa iteracion de la estrategia "
							+ d.getNombreEstrategia()
							+ " que más nodos ha visto ha sido con la configuracion "
							+ d.getValoresIteraciones(0).getNombreIteracion());
					frame.getContentPane().add(texto);;
				}frame.getContentPane().add(new JLabel("\n\n\n"));
		
			    //frame.pack();
				frame.setSize(700, 200);
			    frame.setVisible(true);
				frame.setResizable(true);
				frame.setLocationRelativeTo(null);
			}
}