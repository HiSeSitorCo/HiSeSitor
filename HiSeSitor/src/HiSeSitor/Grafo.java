package HiSeSitor;

import java.util.ArrayList;
import java.util.List;

import HiSeSitor.Nodo;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;

/**
 * 
 * @author Victor
 *
 * Gestion de grafos.
 * 
 * Incluye los metodos necesarios para crear y operar con grafos.
 */
public class Grafo {
	
	/*Nodos son enteros y las aristas tambien hasta nuevo aviso*/
	SparseMultigraph<Integer, Integer> g;
	
	public Grafo (){
		g = new SparseMultigraph<>();
	}
	public void addNode (Nodo n){
		/*Igual este metodo es un sin sentido...
		 * 
		 * Faltaria informacion de donde meter el nodo...*/
		
	}
	public void addNode (List<Integer> aristas){
		/*Añade un nodo a las aristas que se le indiquen*/
	}

	public List<Nodo> getShortestPath (Nodo n1, Nodo n2){
		List<Nodo> ln= new ArrayList<>();
		/*Devuelve una lista con nodos sucesivos*/
		return ln;
	}

}
