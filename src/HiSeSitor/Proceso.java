package HiSeSitor;

import gestionDatos.Datos;
import gestionDatos.DatosIteracion;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.border.Border;

/**
 * 
 * @author HiSeSiTor Co.
 * 
 */
public class Proceso {

	public static boolean enableGUI;
	public int SALTO;
	public ArrayList<Sensor> sensores = new ArrayList<Sensor>();
	public Simulacion simulacion;
	public static Datos dato = new Datos();
	private int incMax = 50;
	private int fraccion = -10 / 11;
	public int itera;
	public static int MAX_TOP;
	public static String progressMsg;
	public static int maxProgress;
	public static int progress;
	static JLabel jl;
	public static JProgressBar progressBar;
	public ArrayList<DatosIteracion> datositeraciones = new ArrayList<DatosIteracion>();
	public static ArrayList<Integer> randPos;
	public static int randGet;
	private int id = 0;
	public static int max_time;
	static JFrame f;
	int calc;

	public int flagSim = 0;

	/**
	 * el salto lo podemos manejar desde aqui
	 * 
	 * @param s
	 * @param sim
	 */
	public Proceso(ArrayList<Sensor> s, Simulacion sim) {
		sensores = s;
		simulacion = sim;
		SALTO = 4;
		Logger.debug = false;
		enableGUI = false;
		MAX_TOP = 37;
		max_time = 137;
		startGUI();
		Thread initialize = new Thread() {
			@Override
			public void run() {
				progressBar = new JProgressBar();
				progressBar.validate();
			}
		};
		initialize.run();

		progressMsg = "Processing...";
	}

	/**
	 * recibe un array de estrategias o por lo menos deberia indicar el numero
	 * de estrategias que se quieren y un array de arrays de enteros. el tamao
	 * del array grande debe ser igual que el numero de estrategias.
	 * 
	 * @param es
	 * @param v
	 */
	public void iteraEstrategias(ArrayList<Estrategia> es,
			ArrayList<ArrayList<Integer>> v) {
		progress = 0;
		int dat = 0;
		progressBar.setValue(0);
		for (ArrayList<Integer> a : v) {
			dat = Math.max(dat, a.size());
		}
		calc = (int) Math.pow(MAX_TOP * es.size(), dat);
		for (int i = 0; i < es.size(); i++) {
			itera(es.get(i), v.get(i), dato);
			progress = maxProgress;
			progressBar.setValue(9999999);
		}
		datositeraciones.add(dato.mejorIteracionCapturados());
		datositeraciones.add(dato.mejorIteracionOptimizada());
		datositeraciones.add(dato.mejorIteracionVisibles());
	}

	public void preparaSimulacion(Estrategia estr, Simulacion s,
			ArrayList<Integer> var) {
		s.InitSimulacion();
		estr.reset();
		try {
			estr.asignaVariables(var);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * itera lo voy explicando entre el codigo. recibe una estrategia y el array
	 * de variables de la misma
	 * 
	 * @param e
	 * @param vars
	 * @param d
	 */
	public int itera(Estrategia e, ArrayList<Integer> vars, Datos d) {
		return iteraAux(vars.size() - 1, e, vars, d);
	}

	public int funcionDecisionParametros(int index, int inc,
			ArrayList<Integer> vars) {
		int p = vars.get(index);
		vars.remove(index);
		vars.add(index, p + inc);
		return p + inc;
	}

	public int iteraAux(int num, Estrategia e, ArrayList<Integer> vars, Datos d) {
		int inc = incMax;
		int ret1;
		int ret2;
		int it = 0;
		int top = MAX_TOP;
		if (num > 0) {
			ret1 = iteraAux(num - 1, e, vars, d);
			while (top-- != 0) {
				funcionDecisionParametros(num, inc, vars);
				ret2 = iteraAux(num - 1, e, vars, d);
				if (ret1 > ret2) {
					inc = fraccion * inc;
					it = 0;
				} else if (ret1 < ret2) {
					ret1 = ret2;
					it = 0;
				} else {
					it++;
				}

				if (it == 3) {
					return ret1;
				}
			}
		} else {
			int iteraLocal = itera;
			maxProgress = calc;
			progress = itera;
			progressBar.setValue(iteraLocal);
			progressBar.setMaximum(calc);
			System.out.println("iteracion " + iteraLocal + " de " + calc + ", "
					+ e.nombre);
			id++;
			if (e.memoria != null) {
				preparaSimulacion(e, simulacion, vars);
				flagSim = 1;
			}

			ret1 = simulacion.correSimulacion(e, d,
					id + "-" + this.toString(vars), max_time);
			iteraLocal++;
			while (top-- != 0) {
				funcionDecisionParametros(num, inc, vars);
				id++;
				progress = itera;
				progressBar.setValue(iteraLocal);
				progressBar.setMaximum(calc);
				System.out.println("iteracion " + iteraLocal + " de " + calc
						+ ", " + e.nombre);
				preparaSimulacion(e, simulacion, vars);
				ret2 = simulacion.correSimulacion(e, d,
						id + "-" + this.toString(vars), max_time);
				iteraLocal++;
				if (ret1 > ret2) {
					inc = fraccion * inc;
					it = 0;
				} else if (ret1 < ret2) {
					ret1 = ret2;
					it = 0;
				} else {
					it++;
				}

				if (it == 3) {
					itera += MAX_TOP;
					return ret1;
				}
			}
		}
		itera += MAX_TOP;
		return ret1;
	}

	public String toString(ArrayList<Integer> array) {

		String cadena = array.toString();
		cadena = cadena.replace(",", ".");
		return cadena;
	}

	public void startGUI() {
		Thread t = new Thread() {
			@Override
			public void run() {
				f = new JFrame("Hisesitor");
				f.setVisible(true);
				f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				Container content = f.getContentPane();
				progressBar = new JProgressBar();
				progressBar.setMaximum(maxProgress);
				progressBar.setStringPainted(true);
				Border border = BorderFactory.createTitledBorder(progressMsg);
				progressBar.setBorder(border);
				content.add(progressBar, BorderLayout.NORTH);
				jl = new JLabel();
				content.add(jl, BorderLayout.CENTER);
				JLabel jlb = new JLabel("This may take long time");
				content.add(jlb, BorderLayout.SOUTH);
				f.setSize(400, 100);
				f.setResizable(false);
				f.setVisible(true);
				f.setLocationRelativeTo(null);
			}
		};
		t.start();
	}

	public void imprimeResultados(String nombre) {
		dato.procesaDatos(nombre, this.datositeraciones);
	}

	public void muestraVentana() {
		dato.muestraVentana();
	}

	public static int getPseudoRand() {
		int f = randPos.get(randGet);
		randGet++;
		return f;
	}

	public static void restartPseudoRand() {
		randGet = 0;
	}

	public static void initPseudoRand(int x, int y) {
		int bounds = x * y;
		randGet = 0;
		randPos = new ArrayList<>();
		Random rd = new Random(System.currentTimeMillis());
		for (int i = 0; i < bounds * 4; i++) {
			randPos.add(rd.nextInt(bounds));
			// System.out.println("Generado: "+randPos.get(i));
		}
	}
}
