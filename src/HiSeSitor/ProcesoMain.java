package HiSeSitor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ProcesoMain {

	static JTextField cercanos;
	static JTextField espv21;
	static JTextField espv22;
	static JTextField espv31;
	static JTextField espv32;
	static JTextField espv33;
	static JTextField espv41;
	static JTextField espv42;
	static JTextField farway1;
	static JTextField maxit;
	static JTextField maxitpsim;
	static JTextField maptext;
	static JCheckBox e1;
	static JCheckBox e2;
	static JCheckBox e3;
	static JCheckBox e4;
	static JCheckBox e5;
	static JFrame window;
	static JTextArea ta;
	static boolean errorMap;
	static boolean working = false;

	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		errorMap = true;
		window = new JFrame("HiSeSitor - Hide&seek Simulator");
		window.setMinimumSize(new Dimension(500, 400));
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(700, 550);
		window.setMaximumSize(new Dimension(700,550));
		window.setResizable(true);
		BorderLayout bl = new BorderLayout();
		window.setLayout(bl);
		JPanel n = new JPanel();
		JPanel o = new JPanel();
		JPanel s = new JPanel();
		JPanel ce = new JPanel();
		JPanel e = new JPanel();
		e.setSize(150, 150);
		o.setLayout(new BorderLayout());
		JLabel jl = new JLabel("Bienvenido a HiSeSitor!");
		JLabel mest = new JLabel("Estrategias");
		JLabel varst = new JLabel("Variable 1");
		JLabel varst2 = new JLabel("Variable 2    ");
		JLabel varst3 = new JLabel("Variable 3    ");
		JLabel conf = new JLabel("Configuracion");
		JLabel nofit = new JLabel("Simulaciones max.");
		JLabel nofsim = new JLabel("Iteraciones por simulacion max");
		JLabel map = new JLabel("Mapa ");
		jl.setFont(new Font("Cambria", Font.PLAIN, 35));
		Font labest = new Font("Cambria", Font.PLAIN, 15);
		mest.setFont(labest);
		varst.setFont(labest);
		varst2.setFont(labest);
		varst3.setFont(labest);
		conf.setFont(labest);
		nofit.setFont(labest);
		nofsim.setFont(labest);
		map.setFont(labest);
		window.add(n, BorderLayout.NORTH);
		window.add(o, BorderLayout.WEST);
		window.add(s, BorderLayout.SOUTH);
		window.add(ce, BorderLayout.CENTER);
		window.add(e, BorderLayout.EAST);
		n.add(jl);
		o.add(mest, BorderLayout.NORTH);
		GridBagLayout gl = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		e1 = new JCheckBox("Cercanos");
	    e2 = new JCheckBox("Espiral simple");
		e3 = new JCheckBox("Espiral con saltos");
		e4 = new JCheckBox("Distancias aleatorias");
		e5 = new JCheckBox("FarAway");
		e1.setFont(labest);
		e2.setFont(labest);
		e3.setFont(labest);
		e4.setFont(labest);
		e5.setFont(labest);
		JPanel listofest = new JPanel();
		cercanos = new JTextField();
		espv21 = new JTextField();
		espv22 = new JTextField();
		espv31 = new JTextField();
		espv32 = new JTextField();
		espv33 = new JTextField();
		espv41 = new JTextField();
		espv42 = new JTextField();
		farway1 = new JTextField();
		maxit = new JTextField();
		maptext = new JTextField();
		maxitpsim = new JTextField();
		maxit.setText("37");
		maxitpsim.setText("137");
		listofest.setLayout(gl);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = 40;
		c.gridx = 0;
		c.gridy = 0;
		listofest.add(new JSeparator(JSeparator.HORIZONTAL), c);
		c.gridx = 1;
		c.gridy = 0;
		listofest.add(new JSeparator(JSeparator.HORIZONTAL), c);
		c.gridx = 2;
		c.gridy = 0;
		listofest.add(new JSeparator(JSeparator.HORIZONTAL), c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		listofest.add(mest, c);
		c.gridx = 1;
		c.gridy = 1;
		listofest.add(varst, c);
		c.gridx = 2;
		c.gridy = 1;
		listofest.add(varst2, c);
		c.gridx = 3;
		c.gridy = 1;
		listofest.add(varst3, c);
		c.gridx = 0;
		c.gridy = 2;
		listofest.add(e1, c);
		c.gridx = 1;
		c.gridy = 2;
		listofest.add(cercanos, c);
		c.gridx = 0;
		c.gridy = 3;
		listofest.add(e2, c);
		c.gridx = 1;
		c.gridy = 3;
		listofest.add(espv21, c);
		c.gridx = 2;
		c.gridy = 3;
		listofest.add(espv22, c);
		c.gridx = 0;
		c.gridy = 4;
		listofest.add(e3, c);
		c.gridx = 1;
		c.gridy = 4;
		listofest.add(espv31, c);
		c.gridx = 2;
		c.gridy = 4;
		listofest.add(espv32, c);
		c.gridx = 3;
		c.gridy = 4;
		listofest.add(espv33, c);
		c.gridx = 0;
		c.gridy = 5;
		listofest.add(e4, c);
		c.gridx = 1;
		c.gridy = 5;
		listofest.add(espv41, c);
		c.gridx = 2;
		c.gridy = 5;
		listofest.add(espv42, c);
		c.gridx = 0;
		c.gridy = 6;
		listofest.add(e5, c);
		c.gridx = 1;
		c.gridy = 6;
		listofest.add(farway1, c);
		c.gridx = 0;
		c.gridy = 7;
		listofest.add(new JSeparator(JSeparator.HORIZONTAL), c);
		c.gridx = 1;
		c.gridy = 7;
		listofest.add(new JSeparator(JSeparator.HORIZONTAL), c);
		c.gridx = 2;
		c.gridy = 7;
		listofest.add(new JSeparator(JSeparator.HORIZONTAL), c);
		c.gridx = 1;
		c.gridy = 8;
		listofest.add(conf, c);
		c.gridx = 0;
		c.gridy = 9;
		listofest.add(nofit, c);
		c.gridx = 1;
		c.gridy = 9;
		listofest.add(maxit, c);
		c.gridx = 0;
		c.gridy = 10;
		listofest.add(nofsim,c);
		c.gridx = 1;
		c.gridy = 10;
		listofest.add(maxitpsim,c);
		c.gridx = 0;
		c.gridy = 11;
		listofest.add(map, c);
		c.gridx = 1;
		c.gridy = 11;
		listofest.add(maptext, c);
		c.gridx = 2;
		c.gridy = 11;
		JButton loadMap = new JButton("Seleccionar");
		listofest.add(loadMap, c);
		o.add(listofest, BorderLayout.NORTH);
		o.add(new JLabel(), BorderLayout.SOUTH);
		ta = new JTextArea();
		ta.setEditable(false);
		JScrollPane sp = new JScrollPane(ta);
		ce.setLayout(new BorderLayout());
		ce.add(sp, BorderLayout.CENTER);
		loadMap.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				final JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(new JFrame());
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					if (!file.exists()) {
						JOptionPane.showMessageDialog(window,
								"No se encuentra el fichero", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
					try {
						int[] tamanoMapa = new int[1000];
						FileReader fr = null;
						BufferedReader br = null;
						fr = new FileReader(file);
						br = new BufferedReader(fr);

						String linea;

						linea = br.readLine();
						String[] lineaTamano = linea.split(",");

						tamanoMapa[0] = Integer.parseInt(lineaTamano[0]);
						tamanoMapa[1] = Integer.parseInt(lineaTamano[1]);
						int i = 2;
						while ((linea = br.readLine()) != null) {
							lineaTamano = linea.split(",");
							tamanoMapa[i] = Integer.parseInt(lineaTamano[0]);
							tamanoMapa[i + 1] = Integer
									.parseInt(lineaTamano[1]);
							tamanoMapa[i + 2] = Integer
									.parseInt(lineaTamano[2]);
							tamanoMapa[i + 3] = Integer
									.parseInt(lineaTamano[3]);
							i += 4;
						}

					} catch (Exception d3) {
						JOptionPane
								.showMessageDialog(
										window,
										"El mapa no cumple con el formato especificado",
										"Error", JOptionPane.ERROR_MESSAGE);
						errorMap = true;
						return;
					}
					errorMap = false;
					maptext.setText(file.getAbsolutePath());
					maptext.validate();
				}
			}

		});
		JButton com = new JButton("Comenzar simulacion");
		com.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (working == true) {
					JOptionPane.showMessageDialog(window,
							"Ya hay una simulacion en proceso", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				Thread t = new Thread() {
					@Override
					public void run() {
						Proceso.randPos = null;
						String s = "";
						Sensor dummie = new Sensor();
						ArrayList<Sensor> sen = new ArrayList<>();
						ArrayList<ArrayList<Integer>> exp = new ArrayList<ArrayList<Integer>>();
						sen.add(dummie);
						ArrayList<Estrategia> es = new ArrayList<Estrategia>();
						if (!e1.isSelected() && !e2.isSelected()
								&& !e3.isSelected() && !e4.isSelected()
								&& !e5.isSelected()) {
							JOptionPane.showMessageDialog(window,
									"Debe seleccionar al menos una estrategia",
									"Error", JOptionPane.ERROR_MESSAGE);
							return;

						}
						if(errorMap){
							JOptionPane.showMessageDialog(window,
									"No se ha seleccionado un mapa valido",
									"Error", JOptionPane.ERROR_MESSAGE);
							return;

						}
						if(e1.isSelected()){
							ArrayList<Integer> e1_vars = new ArrayList<>();
							try{
							e1_vars.add(Integer.parseInt(cercanos.getText()));
							s = s+"Estrategia Cercanos con variables "+e1_vars.toString()+"\n";
							}catch (Exception e){
								JOptionPane.showMessageDialog(window, "Error al parsear la variable"
										+ "\nUtilice solo numeros", "Error", JOptionPane.ERROR_MESSAGE);
								return;
							}
							exp.add(e1_vars);
							es.add(new Estrategia_Cercanos(sen, e1_vars));
						}
						if(e2.isSelected()){
							ArrayList<Integer> e1_vars = new ArrayList<>();
							try{
							e1_vars.add(Integer.parseInt(espv21.getText()));
							e1_vars.add(Integer.parseInt(espv22.getText()));
							s = s+"Estrategia espiral simple con variables "+e1_vars.toString()+"\n";
							}catch (Exception e){
								JOptionPane.showMessageDialog(window, "Error al parsear la variable"
										+ "\nUtilice solo numeros", "Error", JOptionPane.ERROR_MESSAGE);
								return;
							}
							exp.add(e1_vars);
							es.add(new Estrategia_espiral_simple(sen, e1_vars));
						}
						if(e3.isSelected()){
							ArrayList<Integer> e1_vars = new ArrayList<>();
							try{
							e1_vars.add(Integer.parseInt(espv31.getText()));
							e1_vars.add(Integer.parseInt(espv32.getText()));
							e1_vars.add(Integer.parseInt(espv33.getText()));
							s = s+"Estrategia Espiral con saltos con variables "+e1_vars.toString()+"\n";
							}catch (Exception e){
								JOptionPane.showMessageDialog(window, "Error al parsear la variable"
										+ "\nUtilice solo numeros", "Error", JOptionPane.ERROR_MESSAGE);
								return;
							}
							exp.add(e1_vars);
							es.add(new Estrategia_espiral_con_Saltos(sen, e1_vars));
						}
						if(e4.isSelected()){
							ArrayList<Integer> e1_vars = new ArrayList<>();
							try{
							e1_vars.add(Integer.parseInt(espv41.getText()));
							e1_vars.add(Integer.parseInt(espv42.getText()));
							s = s+"Estrategia Distancias Aleatorias con variables "+e1_vars.toString()+"\n";
							}catch (Exception e){
								JOptionPane.showMessageDialog(window, "Error al parsear la variable"
										+ "\nUtilice solo numeros", "Error", JOptionPane.ERROR_MESSAGE);
								return;
							}
							exp.add(e1_vars);
							es.add(new Estrategia_de_distancias(sen, e1_vars));
						}
						if(e5.isSelected()){
							ArrayList<Integer> e1_vars = new ArrayList<>();
							try{
							e1_vars.add(Integer.parseInt(farway1.getText()));
							s = s+"Estrategia FarAway con variables "+e1_vars.toString()+"\n";
							}catch (Exception e){
								JOptionPane.showMessageDialog(window, "Error al parsear la variable"
										+ "\nUtilice solo numeros", "Error", JOptionPane.ERROR_MESSAGE);
								return;
							}
							exp.add(e1_vars);
							es.add(new Estrategia_FarAway(sen, e1_vars));
						}
						ProcesoMain.writeResult(s);
						Simulacion sim = new Simulacion(15);
						Proceso p = new Proceso(sen, sim);
						if(!maxit.getText().isEmpty()){
							try{
								p.MAX_TOP = Integer.parseInt(maxit.getText());
							}catch (Exception e){
								JOptionPane.showMessageDialog(window,
										"Error al configurar el numero maximo de simulaciones.\n"
										+ "Se usara el valor por defecto.",
										"Error", JOptionPane.ERROR_MESSAGE);
							}
						}
						if(!maxitpsim.getText().isEmpty()){
							try{
								p.max_time = Integer.parseInt(maxitpsim.getText());
							}catch (Exception e){
								JOptionPane.showMessageDialog(window,
										"Error al configurar el numero maximo de iteraciones.\n"
										+ "Se usara el valor por defecto.",
										"Error", JOptionPane.ERROR_MESSAGE);
							}
						}
						working = true;
						p.iteraEstrategias(es, exp);
						if (p.f != null)
							p.f.setVisible(false);
						working = false;
						p.imprimeResultados("fichSalida.txt");
						p.muestraVentana();
					}
				};
				t.start();

			}

		});

		s.add(com);
		window.validate();
	}

	public static void writeResult(String s) {
		ta.setText(ta.getText() + "\n" + s);
		ta.validate();
	}

}
