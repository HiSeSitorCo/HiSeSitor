package HiSeSitor;

import java.util.ArrayList;

public class Estrategia_espiral_test extends Estrategia {
	public int periodo;
	public ArrayList<Nodo> visitados = new ArrayList<>();
	
	public Estrategia_espiral_test(ArrayList<Sensor> sen) {
		super(sen);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void update() { 
        int i = 9;
        visitados.add(estado.getActual());
        //Grafo memoria = estado.memoria;
        for (Sensor s : sensores) {
            for (Nodo n : s.sensorKnoledge.getListaNodos()) {
                if (s.sensorKnoledge.getDistancia(n, estado.getActual()) < 3 && visitados.contains(n) != true ) {
                    n.score += i;
                    i--;
                    if (i < 0) break;
                } 
                if (visitados.contains(n)) n.score = -10;
            }
            i=9;
        }

    }

}
