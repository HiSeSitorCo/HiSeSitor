package HiSeSitor;

import java.util.ArrayList;
import java.util.Collection;
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
	SparseMultigraph<Integer, Nodo> g;
	
	public Grafo (){
		g = new SparseMultigraph<>();
	}
	/*
	 * Probablemente esta funci�n carezca de sentido
	 */
	public void addNode (Nodo n){
		
		
	}
	/**
	 * 
	 * @param aristas Lista de conexiones del nodo
	 * @param n Nodo a insertar
	 * @return True si se inserta correctamente. False en caso contrario
	 */
	public boolean addNode (List<Integer> aristas, Nodo n){
		return g.addEdge(n, aristas);
	}

	public List<Nodo> getShortestPath (Nodo n1, Nodo n2){
		List<Nodo> ln= new ArrayList<>();
		/*Devuelve una lista con nodos sucesivos*/
		return ln;
	}
	
	public int getNodesCount (){
		return g.getVertexCount();
	}
}
