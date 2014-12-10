package HiSeSitor;

import java.util.ArrayList;

public class MainGrafo {

	/**
	 * Main de prueba
	 * @param args
	 */
	public static void main(String[] args) {
		Grafo g = new Grafo();
		Nodo a,b,c,d,e;
		a = new Nodo(10,0);
		b = new Nodo(20,0);
		c = new Nodo(30,0);
		d = new Nodo(40,0);
		e = new Nodo(50,0);
		
		g.addEdge(1, a, b);
		g.addEdge(2, b, c);
		g.addEdge(3, c, d);
		g.addEdge(4, a, c);
		g.addEdge(5, a, e);
		g.addEdge(6, e, b);
		g.addEdge(7, e, c);
		
		ArrayList<Nodo> lv = new ArrayList<>();
		lv.addAll(g.getShortestPath(a, d));
		for(int i = 0; i < lv.size(); i++){
			System.out.println(lv.get(i).toString());
		}
		g.plotGraph();
		
	}

}
