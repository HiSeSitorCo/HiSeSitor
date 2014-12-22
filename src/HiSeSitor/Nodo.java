package HiSeSitor;

/**
 * 
 * @author Victor
 * 
 *         Habria que definir esta clase en algun momento proximo
 *
 */
public class Nodo {

	int id; /* Identificador unico para cada nodo */
	double score; /* Puntuacion definida por la estrategia y/o heuristica */
	boolean cazador = false;
	boolean presa = false;
	boolean cazada = false;
	boolean obstaculo = false;
	boolean init = false;

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
	
	

	public boolean isCazador() {
		return cazador;
	}

	public void setCazador(boolean cazador) {
		this.cazador = cazador;
	}

	public boolean isPresa() {
		return presa;
	}

	public void setPresa(boolean presa) {
		this.presa = presa;
	}

	public boolean isCazada() {
		return cazada;
	}

	public void setCazada(boolean cazada) {
		this.cazada = cazada;
	}

	public boolean isObstaculo() {
		return obstaculo;
	}

	public void setObstaculo(boolean obstaculo) {
		this.obstaculo = obstaculo;
	}

	public String toString() {
		if(cazador == true)
			return "ID: " + id+"| CAZADOR";
		else
		return "ID: " + id + "| Score: " + score;
	}
	int compareTo(Nodo n){
		return Integer.compare(this.id,n.id);
	}
	@Override
    public boolean equals(Object object)
    {
        boolean sameSame = false;

        if (object != null && object instanceof Nodo)
        {
            sameSame = this.id == ((Nodo) object).id;
        }

        return sameSame;
    }
	@Override
	public int hashCode() {
		  return id;
		}
}
