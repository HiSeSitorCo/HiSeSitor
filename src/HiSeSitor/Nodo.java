package HiSeSitor;

import java.util.ArrayList;

import edu.uci.ics.jung.visualization.layout.PersistentLayout.Point;

/**
 * 
 * @author Victor
 * 
 *         Habria que definir esta clase en algun momento proximo
 * 
 */
public class Nodo {
	static final int MAS = 1;
	static final int MENOS = -1;
	static final int FIN = 0;
	int id; /* Identificador unico para cada nodo */
	double score; /* Puntuacion definida por la estrategia y/o heuristica */
	boolean cazador = false;
	boolean presa = false;
	boolean cazada = false;
	boolean obstaculo = false;
	boolean init = false;
	boolean estimacion = false;
	Point pos;
	int norte = -1, noreste = -1, este = -1, sureste = -1, sur = -1, suroeste = -1,
			oeste = -1, noroeste = -1;

	public Nodo(int id, double score, Point pos) {
		super();
		this.id = id;
		this.score = score;
		this.pos = pos;
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
		if (cazador == true)
			return pos.x+"-"+pos.y+"--ID: " + id + "| CAZADOR";
		else
			return norte+""+noreste+""+este+""+sureste+sur+suroeste+oeste+noroeste+"ss"+pos.x+"-"+pos.y+"--ID: " + id + "| Score: " + score;
	}

	int compareTo(Nodo n) {
		return Integer.compare(this.id, n.id);
	}

	@Override
	public boolean equals(Object object) {
		boolean sameSame = false;

		if (object != null && object instanceof Nodo) {
			sameSame = this.id == ((Nodo) object).id;
		}

		return sameSame;
	}
	
	public ArrayList<Integer> getListaAristas(){
		ArrayList<Integer> res = new ArrayList<>();
		res.add(norte);
		res.add(noreste);
		res.add(este);
		res.add(sureste);
		res.add(sur);
		res.add(suroeste);
		res.add(oeste);
		res.add(noroeste);
		return res;
	}
	public boolean isEstimacion() {
		return estimacion;
	}

	public void setEstimacion(boolean estimacion) {
		this.estimacion = estimacion;
	}
	
	@Override
	public int hashCode() {
		return id;
	}


	public Point getPos() {
		return pos;
	}


	public void setPos(Point pos) {
		this.pos = pos;
	}


	public int getNorte() {
		return norte;
	}


	public void setNorte(int norte) {
		this.norte = norte;
	}


	public int getNoreste() {
		return noreste;
	}


	public void setNoreste(int noreste) {
		this.noreste = noreste;
	}


	public int getSureste() {
		return sureste;
	}


	public void setSureste(int sureste) {
		this.sureste = sureste;
	}


	public int getSur() {
		return sur;
	}


	public void setSur(int sur) {
		this.sur = sur;
	}


	public int getSuroeste() {
		return suroeste;
	}


	public void setSuroeste(int suroeste) {
		this.suroeste = suroeste;
	}


	public int getOeste() {
		return oeste;
	}


	public void setOeste(int oeste) {
		this.oeste = oeste;
	}


	public int getNoroeste() {
		return noroeste;
	}


	public void setNoroeste(int noroeste) {
		this.noroeste = noroeste;
	}
	
}
