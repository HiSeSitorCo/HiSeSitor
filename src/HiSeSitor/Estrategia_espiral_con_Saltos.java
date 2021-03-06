package HiSeSitor;

import java.util.ArrayList;

/**
 * 
 * @author HiSeSiTor Co.
 * 
 */
public class Estrategia_espiral_con_Saltos extends Estrategia {
	public int periodo;
	public ArrayList<Nodo> visitados = new ArrayList<>();
	private int espiral;
	private int espiralAux = 0;
	private int pasoPasado = 0;
	private int div;
	private int itPasado = 1;
	private int jGlob = 2;
	public int transito = 1;
	public int transitado = 0;
	public int freeze = -1;
	public int numToJump;
	public int itNTJ;
	public int presas;

	protected int tam = 1;

	public Estrategia_espiral_con_Saltos(ArrayList<Sensor> sen,
			ArrayList<Integer> v) {
		super(sen, v);
		nombre = "Espiral con saltos";
		// TODO Auto-generated constructor stub
	}

	@Override
	public void asignaVariables(ArrayList<Integer> v) throws Exception {
		if (v.size() != tam) {
			new Exception("Variables no correwspondientes con la estrategua");
		}
		espiral = v.get(0);
		div = v.get(1);
		numToJump = v.get(2);
		itNTJ = numToJump;

	}

	@Override
	public double estima(Nodo n) {
		if (itNTJ == 0) {
			return estimaSalto(n);
		} else {
			return estimaEspiral(n);
		}
	};

	@Override
	public double calcula(Nodo n) {
		if (itNTJ == 0) {
			return calculaSalto(n);
		} else {
			return calculaEspiral(n);
		}
	};

	@Override
	public void update() {
		int j = jGlob;
		int paso = pasoPasado;
		int cont = 0;
		int it = itPasado;
		Nodo actual = memoria.getNode(estado.getActual().id);
		visitados.add(actual);
		int fin = 0;
		int x = (int) actual.pos.x;
		int y = (int) actual.pos.y;
		int xp = (int) actual.pos.x;
		int yp = (int) actual.pos.y;
		int notNull = 0;
		int first = 1;
		espiralAux = espiral;
		// bluce de transito

		if (presas != estado.presas) {
			itNTJ--;
			if (itNTJ < 0) {
				itNTJ = numToJump;
			}
		}
		// He concluido mi camino
		if (transito <= 1 || fin == 1) {
			transitado = 1;
			pasoPasado = (pasoPasado + 1) % 4;
			paso = pasoPasado;
			fin = 0;

		} else {// Continuo caminando
			freeze = 1;

		}

		int MAX = memoria.getListaNodos().size();
		while (espiralAux > 0 && cont < MAX) {
			Nodo bus;
			int itAux;
			if (fin == 1) {
				transitado = 1;
				if (j > 0)
					jGlob = j - 1;
				else
					jGlob = 1;
				fin = 0;
				if (j == 1) {
					itPasado = it;
				} else {
					itPasado = it + 1;

				}
			}
			if (freeze != 1) {

				if (j-- <= 0) {
					j = 1;
					it++;
				}
				itAux = it;
			} else {

				itAux = itPasado - transitado;
				if (transitado == itPasado && transitado > 1) {

					pasoPasado = (pasoPasado + 1) % 4;
					paso = pasoPasado;
					fin = 1;
				}
				transitado++;
				freeze = 0;
				transito--;
			}
			switch (paso) {

			// arriba
			case 0:
				while (itAux-- != 0) {

					if ((bus = memoria.getNodo(x, --y)) != null) {
						calculaEstima(bus);
						espiralAux--;
						notNull = 1;
						cont++;
					} else {
						y++;
						cont++;
						break;
					}
				}
				break;
			// abajo
			case 2:
				while (itAux-- != 0) {

					if ((bus = memoria.getNodo(x, ++y)) != null) {
						calculaEstima(bus);
						espiralAux--;
						notNull = 1;
						cont++;
					} else {
						y--;
						cont++;
						break;
					}
				}
				break;
			// derehca
			case 1:
				while (itAux-- != 0) {
					if ((bus = memoria.getNodo(++x, y)) != null) {
						calculaEstima(bus);
						espiralAux--;
						notNull = 1;
						cont++;
					} else {
						x--;
						cont++;
						break;
					}
				}
				break;
			// izquierda
			default:
				while (itAux-- != 0) {
					if ((bus = memoria.getNodo(--x, y)) != null) {
						calculaEstima(bus);
						espiralAux--;
						notNull = 1;
						cont++;
					} else {
						x++;
						cont++;
						break;
					}
				}
				break;
			}
			if (notNull != 0) {

				paso = (paso + 1) % 4;
				notNull = 0;
			}
			if (first == 1) {
				// if (transitado > 0) j--;

				first = 0;
				if (itAux < 1)
					itPasado = it;

				jGlob = j;
				transito = it;
			}

		}

		for (Nodo n : visitados) {
			n.score = -10;
		}
		if (xp == x && yp == y) {

			pasoPasado = (pasoPasado + 1) % 4;
			paso = pasoPasado;
		}

	}

	public double estimaSalto(Nodo n) {
		double gan = 0;
		if ((gan = dameGananciaMedia(n)) < 0) {
			gan = Math.abs(gan);
			n.score = calcula(n) * div / gan;
		} else {
			gan = Math.abs(gan);
			n.score = calcula(n) * gan / div;
		}
		return n.score;
	}

	public double estimaEspiral(Nodo n) {
		double gan = 0;
		if ((gan = dameGananciaMedia(n)) < 0) {
			gan = Math.abs(gan);
			n.score = espiralAux * div / gan;
		} else {
			gan = Math.abs(gan);
			n.score = espiralAux * gan / div;
		}
		return n.score;
	}

	public double dameGananciaMedia(Nodo n) {
		int ganancia = 0;
		int num = 0;
		for (Nodo ady : memoria.getAdjacents(n)) {
			if (!ady.isEstimacion()) {
				ganancia += ady.ganancia;
				num++;
			}
		}
		if (num != 0) {
			return ganancia / num;
		}
		return 0;

	}

	@Override
	public void reset_ext() {

		memoria = null;
		visitados = new ArrayList<>();
		espiral = -1;
		espiralAux = 0;
		pasoPasado = 0;
		div = 0;
		itPasado = 0;
	};

	public double calculaSalto(Nodo n) {
		Nodo l = estado.mapa.getNode(n.id);
		int dist = 0;
		if (n.score < 0) {
			l.score = -10;
			return -10;
		}
		if (visitados.contains(n)) {
			n.score = -5;
			l.score = -5;
			return n.score;
		}
		visitados.add(estado.getActual());
		n.score = 9999;
		dist = this.memoria.getDistancia(n, estado.inicio);
		if (dist < n.score) {
			n.score = dist;
			l.score = dist;
		}

		return n.score;
	}

	public double calculaEspiral(Nodo n) {
		if (estado.getActual() == n)
			n.score = -10;
		n.score += espiralAux;

		return n.score;
	}
}
