package HiSeSitor;

import java.util.ArrayList;

public class Estrategia_espiral_testv2 extends Estrategia {
	public int periodo;
	public ArrayList<Nodo> visitados = new ArrayList<>();
	private int espiral = -1;
	private int espiralAux = 0;
	private int pasoPasado = 0;
	
	protected int tam = 1;
	public Estrategia_espiral_testv2(ArrayList<Sensor> sen) {
		super(sen);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void asignaVariables(ArrayList<Integer> v) throws Exception {
		if (v.size() != tam) {
			new Exception("Variables no correwspondientes con la estrategua");
		}
		espiral = v.get(0);
		
	}

	@Override
	public void update() {
		int i = espiral;
		int j = 2;
		int paso = pasoPasado; 
		pasoPasado++;
		int it = 0;
		Nodo actual = estado.getActual();
		visitados.add(actual);

		double x = actual.pos.x;
		double y = actual.pos.y;
		espiralAux = espiral;
		
		
		while (espiralAux != 0) {
			Nodo bus;
			if (j-- == 0) {
				j=2;
				it++;
			}
			switch(paso) {
			
				//arriba
				case 0:
					y+=it;
					break;
				//derehca
				case 1:
					x+=it;
					break;
				//abajo
				case 2:
					x-=it;
					break;
				//izquierda
				default:
					y-=it;
					break;
			}
			if ((bus = memoria.getNodoXY(x, y)) != null) {
				paso++;
				calculaEstima(bus);
			}
		
		}

		
	}
	
	@Override
	public double estima(Nodo n) {
		
	}

	@Override
	public double calcula(Nodo n) {
		if (estado.getActual() == n) n.score = -10;
		n.score = espiralAux;
		
		return n.score;
	}
}
