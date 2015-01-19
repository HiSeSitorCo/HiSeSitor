package HiSeSitor;

import java.awt.Point;
import java.awt.geom.Arc2D.Double;
import java.util.ArrayList;

public class Estrategia_de_distancias extends Estrategia {
	public int periodo;
	public ArrayList<Nodo> visitados = new ArrayList<>();
	private int espiral;
	private int espiralAux = 0;
	private int pasoPasado = 0;
	private int div;
	private int itPasado = 1;
	private int jGlob = 2;
	public int transito=1;
	public int transitado=0;
	public int freeze = -1;
	
	protected int tam = 1;
	public Estrategia_de_distancias(ArrayList<Sensor> sen, ArrayList<Integer> v) {
		super(sen, v);
		nombre = "Distancias";
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void asignaVariables(ArrayList<Integer> v) throws Exception {
		if (v.size() != tam) {
			new Exception("Variables no correwspondientes con la estrategua");
		}
		espiral = v.get(0);
		div = v.get(1);
		
	}
	
	
	@Override
	public double estima(Nodo n) {
		double gan = 0;
		if ((gan = dameGananciaMedia(n)) < 0) {
			gan = Math.abs(gan);
			n.score = calcula(n)*div/gan;
		} else {
			gan = Math.abs(gan);
			n.score = calcula(n)*gan/div;
		}
		return n.score;
	}
	
	public double dameGananciaMedia(Nodo n) {
		int ganancia = 0;
		int num = 0;
		for (Nodo ady : memoria.getAdjacents(n)) {
			if (!ady.isEstimacion()) {
				ganancia+=ady.ganancia;
				num++;
			}				
		}
		if (num!=0) {
			return ganancia/num;
		}
		return 0;
		
	}
	
	@Override
	public void reset_ext() {

		memoria = null;
		visitados = new ArrayList<>();
		espiral = -1;
		espiralAux = 0;
		pasoPasado = 0;
		div = 0;
		itPasado = 0;
	};

	@Override
	public double calcula(Nodo n) {
		Nodo aux = memoria.getCazador();
		
		n.score = memoria.euclideanDist(
				new Punto((int) aux.getPos().x,(int) aux.getPos().y), 
				new Punto((int) n.getPos().x,(int) n.getPos().y));
		
		return n.score;
	}
}
