package HiSeSitor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
	static JTextField espv41;
	static JTextField espv42;
	static JTextField farway1;
	static JCheckBox e1;
	static JCheckBox e2;
	static JCheckBox e3;
	static JCheckBox e4;
	static JCheckBox e5;
	static JFrame window;
	static JTextArea ta;
	static boolean working = false;
	public static void main(String[] args) {
		
		try {
			  UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		window = new JFrame("HiSeSitor - Hide&seek Simulator");
		window.setMinimumSize(new Dimension(500,400));
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(600, 500);
		window.setResizable(false);
		BorderLayout bl = new BorderLayout();
		window.setLayout(bl);
		JPanel n = new JPanel();
		JPanel o = new JPanel();
		JPanel s = new JPanel();
		JPanel ce = new JPanel();
		JPanel e = new JPanel();
		e.setSize(150, 150);
		o.setLayout(new BorderLayout());
		JLabel jl = new JLabel("¡Bienvenido a HiSeSitor!");
		JLabel mest = new JLabel("Estrategias");
		JLabel varst = new JLabel("Variables");
		jl.setFont(new Font("Cambria", Font.PLAIN, 35));
		Font labest = new Font("Cambria", Font.PLAIN, 15);
		mest.setFont(labest);
		varst.setFont(labest);
		window.add(n,BorderLayout.NORTH);
		window.add(o,BorderLayout.WEST);
		window.add(s,BorderLayout.SOUTH);
		window.add(ce,BorderLayout.CENTER);
		window.add(e,BorderLayout.EAST);
		n.add(jl);
		o.add(mest,BorderLayout.NORTH);
		GridBagLayout gl = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		e1 = new JCheckBox("Cercanos");
	    e2 = new JCheckBox("Espiral V2");
		e3 = new JCheckBox("Espiral V3");
		e4 = new JCheckBox("Espiral V4");
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
		espv41 = new JTextField();
		espv42 = new JTextField();
		farway1 = new JTextField();
		listofest.setLayout(gl);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		listofest.add(mest,c);
		c.gridx = 1;
		c.gridy = 0;
		listofest.add(varst,c);
		c.gridx = 0;
		c.gridy = 1;
		listofest.add(e1,c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = 40;
		c.gridx = 1;
		c.gridy = 1;
		listofest.add(cercanos,c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 2;
		listofest.add(e2,c);
		c.gridx = 1;
		c.gridy = 2;
		listofest.add(espv21,c);
		c.gridx = 2;
		c.gridy = 2;
		listofest.add(espv22,c);
		c.gridx = 0;
		c.gridy = 3;
		listofest.add(e3,c);
		c.gridx = 1;
		c.gridy = 3;
		listofest.add(espv31,c);
		c.gridx = 2;
		c.gridy = 3;
		listofest.add(espv32,c);
		c.gridx = 0;
		c.gridy = 4;
		listofest.add(e4,c);
		c.gridx = 1;
		c.gridy = 4;
		listofest.add(espv41,c);
		c.gridx = 2;
		c.gridy = 4;
		listofest.add(espv42,c);
		c.gridx = 0;
		c.gridy = 5;
		listofest.add(e5,c);
		c.gridx = 1;
		c.gridy = 5;
		listofest.add(farway1,c);
		o.add(listofest,BorderLayout.NORTH);
		o.add(new JLabel(),BorderLayout.SOUTH);
		ta = new JTextArea();
		JScrollPane sp = new JScrollPane(ta);
		ce.setLayout(new BorderLayout());
		ce.add(sp,BorderLayout.CENTER);
		JButton com = new JButton("Comenzar simulacion");
		com.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(working == true){
					JOptionPane.showMessageDialog(window, "Ya hay una simulacion en proceso", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				Thread t = new Thread(){
					@Override
					public void run(){
				String s = "";
				Sensor dummie = new Sensor();
				ArrayList<Sensor> sen = new ArrayList<>();
				ArrayList<ArrayList<Integer>> exp = new ArrayList<ArrayList<Integer>>();
				sen.add(dummie);
				ArrayList<Estrategia> es = new ArrayList<Estrategia>();
				if(!e1.isSelected() && !e2.isSelected() && !e3.isSelected() && !e4.isSelected() && !e5.isSelected()){
					JOptionPane.showMessageDialog(window, "Debe seleccionar al menos una estrategia", "Error", JOptionPane.ERROR_MESSAGE);
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
					s = s+"Estrategia espiral V2 con variables "+e1_vars.toString()+"\n";
					}catch (Exception e){
						JOptionPane.showMessageDialog(window, "Error al parsear la variable"
								+ "\nUtilice solo numeros", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					exp.add(e1_vars);
					es.add(new Estrategia_espiral_testv2(sen, e1_vars));
				}
				if(e3.isSelected()){
					ArrayList<Integer> e1_vars = new ArrayList<>();
					try{
					e1_vars.add(Integer.parseInt(espv31.getText()));
					e1_vars.add(Integer.parseInt(espv32.getText()));
					s = s+"Estrategia Espiral V3 con variables "+e1_vars.toString()+"\n";
					}catch (Exception e){
						JOptionPane.showMessageDialog(window, "Error al parsear la variable"
								+ "\nUtilice solo numeros", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					exp.add(e1_vars);
					es.add(new Estrategia_espiral_testv3(sen, e1_vars));
				}
				if(e4.isSelected()){
					ArrayList<Integer> e1_vars = new ArrayList<>();
					try{
					e1_vars.add(Integer.parseInt(espv41.getText()));
					e1_vars.add(Integer.parseInt(espv42.getText()));
					s = s+"Estrategia Espiral V4 con variables "+e1_vars.toString()+"\n";
					}catch (Exception e){
						JOptionPane.showMessageDialog(window, "Error al parsear la variable"
								+ "\nUtilice solo numeros", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					exp.add(e1_vars);
					es.add(new Estrategia_espiral_testv4(sen, e1_vars));
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
						Proceso p = new Proceso(sen,sim);
						working = true;
						p.iteraEstrategias(es, exp);
						if(p.f != null)
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
		//		Sensor dummie = new Sensor();
//		ArrayList<Sensor> sen = new ArrayList<>();
//		sen.add(dummie);
//		Simulacion sim = new Simulacion(15);
//		Proceso p = new Proceso(sen, sim);
//
//		ArrayList<Estrategia> es = new ArrayList<Estrategia>();
//		ArrayList<Integer> vars = new ArrayList<Integer>();
//
//		ArrayList<Integer> e1_vars = new ArrayList<>();
//		ArrayList<Integer> e2_vars = new ArrayList<>();
//		ArrayList<Integer> e3_vars = new ArrayList<>();
//		ArrayList<Integer> e4_vars = new ArrayList<>();
//		e1_vars.add(9);
//		e1_vars.add(2);
//		e2_vars.add(9);
//		e2_vars.add(2);
//		e3_vars.add(3); 
//		es.add(new Estrategia_espiral_testv2(sen, e1_vars));
//		es.add(new Estrategia_FarAway(sen, e3_vars));
//		ArrayList<ArrayList<Integer>> exp = new ArrayList<ArrayList<Integer>>();
//		exp.add(e1_vars);
//		//exp.add(e2_vars);
//		exp.add(e3_vars);
//
//
//		p.iteraEstrategias(es, exp);
//
//		p.imprimeResultados("fichSalida.txt");
//		p.muestraVentana();
//		
//		System.out.println("finiquitaoh");

	}
	
	public static void writeResult (String s){
		ta.setText(ta.getText()+"\n"+s);
	}

}
