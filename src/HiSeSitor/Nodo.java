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
	int time;
	Point pos;
	int norte = -1, noreste = -1, este = -1, sureste = -1, sur = -1, suroeste = -1,
			oeste = -1, noroeste = -1;

	public Nodo(int id, double score, Point pos) {
		super();
		this.id = id;
		this.score = score;
		this.pos = pos;
	}

	
	public void setTiempoEstimacion(int time){
		this.time = time;
	}
	
	public int getTiempoEstimacion(){
		return this.time;
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
	/**
	 * 
	 * @param n
	 * @return 
	 * 		   Devuelve 2 si ambos nodos son estimaciones y compensa unirlos.
	 *         Devuelve 1, si el nodo que ejecuta el método aporta más información
	 *         Devuelve 0, si los nodos son iguales.
	 *         Devuelve -1, si el nodo que ejecuta el método aporta menos información
	 *         Devuelve -2 en caso de error
	 */
	public int diffOfInfo(Nodo n){
		if(n.id != this.id) /*Si el ID es distinto, casque*/
			return -2;
		if(n.isEstimacion() && this.isEstimacion()!=true){ /*Si el nodo es uno calculado, da mas informacion que una estimacion*/
			return 1;
		}
		if(n.isEstimacion()==false && this.isEstimacion()){
			return -1;
		}
		if(n.estimacion == this.estimacion){
			if(n.estimacion == false){
				if(n.time<this.time)
					return -1;
				else
					return 1;
			}if(n.estimacion == true){
				return 2;
			}	
			
		}
		return 0;
	}
	public void joinNode (Nodo n){
		if(!this.estimacion || !n.estimacion){
			return;
		}
		if(n.score > this.score){
			this.score = n.score;
		}
		if(n.time > this.time){
			this.time = n.time;
		}
		if(this.norte*n.norte < 0)
			this.norte = 1;
		else
			this.norte = 0;
		if(this.noreste*n.noreste < 0)
			this.noreste = 1;
		else
			this.noreste = 0;
		if(this.este*n.este < 0)
			this.este = 1;
		else
			this.este = 0;
		if(this.sureste*n.sureste < 0)
			this.sureste = 1;
		else
			this.sureste = 0;
		if(this.sur*n.sur < 0)
			this.sur = 1;
		else
			this.sur = 0;
		if(this.suroeste*n.suroeste < 0)
			this.suroeste = 1;
		else
			this.suroeste = 0;
		if(this.oeste*n.oeste < 0)
			this.oeste = 1;
		else
			this.oeste = 0;
		if(this.noroeste*n.noroeste < 0)
			this.noroeste = 1;
		else
			this.noroeste = 0;
	}
	public void copyNode (Nodo n){
		this.id = n.id;
		this.score = n.score;
		this.cazador = n.cazador;
		this.presa = n.presa;
		this.obstaculo = n.obstaculo;
		this.init = n.init;
		this.estimacion = n.estimacion;
		this.pos = n.pos;
		this.norte = n.norte;
		this.noreste = n.noreste;
		this.este = n.este;
		this.sureste = n.sureste;
		this.suroeste = n.suroeste;
		this.oeste = n.oeste;
		this.noroeste = n.noroeste;

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
	public void setListaAristas(ArrayList<Integer> s){
		norte = s.get(0);
		noreste = s.get(1);
		este = s.get(2);
		sureste = s.get(3);
		sur = s.get(4);
		suroeste = s.get(5);
		oeste = s.get(6);
		noroeste = s.get(7);
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




	public int getEste() {
		return este;
	}




	public void setEste(int este) {
		this.este = este;
	}
	
	
}
