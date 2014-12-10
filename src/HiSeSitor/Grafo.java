package HiSeSitor;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;

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
	SparseMultigraph<Nodo,Integer> g;
	/*Autoincremento para el id de las aristas*/
	int edgecount;
	
	static HashMap<Integer, Integer> hm;
	static ArrayList<Nodos> nods;
	
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
		return true;
	}

	public List<Nodo> getShortestPath (Nodo n1, Nodo n2){
		List<Nodo> lv= new ArrayList<>();
		nods = new ArrayList<>();
		hm = new HashMap<>();
		ArrayList<Nodos> abiertos = new ArrayList<>();
		abiertos.add(new Nodos(n1,0,new ArrayList<Nodos>()));
		Nodos n = getNodos(n2, abiertos,this.g);
		for(int i = 0; i < n.antecesores.size(); i++){
			lv.add(n.antecesores.get(i).id);
		}
		lv.add(n.getId());
		System.out.println(lv.size());
		return lv;

	}
	
	public static Nodos getNodos (Nodo dest, ArrayList<Nodos> abiertos, SparseMultigraph<Nodo, Integer> g){
		ArrayList<Nodo> nb = new ArrayList<>();
		Nodos n = abiertos.get(0);
		if(n.id.id == dest.id){
			nods.add(n);
			return n;
		}
		nb.addAll(g.getNeighbors(n.id));
		ArrayList<Nodos> ant = new ArrayList<>();
		ant.addAll(n.antecesores);
		ant.add(n);
		for(int i = 0; i < nb.size(); i++){
			if(hm.containsKey(nb.get(i))!=true)
			abiertos.add(new Nodos(nb.get(i),n.coste+1,ant));			
		}
		hm.put(n.id.id, n.id.id);
		abiertos.remove(0);
		return getNodos(dest,abiertos,g);
		
	}
	
	public List<Nodo> getAdjacents (Nodo n){
		ArrayList<Nodo> ln = new ArrayList<>();
		ln.addAll(g.getNeighbors(n));
		return ln;
		
	}
	public int getNodesCount (){
		return g.getVertexCount();
	}
	
	public boolean addEdge (int arista, Nodo n1, Nodo n2){
		return g.addEdge(arista, n1, n2);
	}
	
	
	public void plotGraph(){
		CircleLayout layout = new CircleLayout(g);
        layout.setSize(new Dimension(300, 300));
        BasicVisualizationServer<String, Integer> vv = new BasicVisualizationServer<String, Integer>(layout);
        vv.setPreferredSize(new Dimension(350, 350));
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());

        JFrame frame = new JFrame("PrintGrapth");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(vv);
        frame.pack();
        frame.setVisible(true);
        
	}
}

/**
 * Clase auxiliar para la busqueda del camino mas corto
 * @author Victor
 *
 */
class Nodos {
	Nodo id;
	int coste;
	ArrayList<Nodos> antecesores;
	
	public Nodos(Nodo id, int coste, ArrayList<Nodos> antecesores) {
		super();
		this.id = id;
		this.coste = coste;
		this.antecesores = antecesores;
	}
	public Nodo getId() {
		return id;
	}
	public void setId(Nodo id) {
		this.id = id;
	}
	public int getCoste() {
		return coste;
	}
	public void setCoste(int coste) {
		this.coste = coste;
	}

	public String toString (){
		String s = "ID: "+id+" Coste: "+coste;
		return s;
	}
	
	
	
}