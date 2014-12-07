package HiSeSitor;

/**
 * 
 * @author Victor
 * 
 * Habria que definir esta clase en algun momento proximo
 *
 */
public class Nodo {
	
	int id; /*Identificador unico para cada nodo*/
	double score; /*Puntuacion definida por la estrategia y/o heuristica*/
	
	public Nodo(int id, double score) {
		super();
		this.id = id;
		this.score = score;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}
	
	
	
	

}
