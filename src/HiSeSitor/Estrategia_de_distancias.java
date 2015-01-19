package HiSeSitor;

import java.awt.Point;
import java.awt.geom.Arc2D.Double;
import java.util.ArrayList;

public class Estrategia_de_distancias extends Estrategia {
	public int periodo;
	public ArrayList<Nodo> visitados = new ArrayList<>();
	private int espiral;
	private int a,b;
	private int div;
	private int itPasado = 1;
	private int jGlob = 2;
	public int transito=1;
	public int transitado=0;
	public int freeze = -1;
	
	protected int tam = 2;
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
		a = v.get(0);
		b = v.get(1);
		
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
		a = 0;
		b = 0;
		div = 0;
		itPasado = 0;
	};

	@Override
	public double calcula(Nodo n) {
		Nodo aux = estado.mapa.getNode(n.id);
		Nodo nact = estado.inicio;
		nact.score = -10;
		memoria.getNode(nact.id).score = -10;
		n.score = estado.mapa.getDistancia(n, estado.inicio) * a +
				estado.mapa.getDistancia(estado.actual, estado.inicio)*b;
		aux.score = n.score;
		
		return n.score;
	}
}
