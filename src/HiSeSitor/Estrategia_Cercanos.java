package HiSeSitor;

import java.util.ArrayList;

/**
 * 
 * @author HiSeSiTor Co.
 * 
 */
public class Estrategia_Cercanos extends Estrategia {

	public ArrayList<Nodo> visitados = new ArrayList<>();

	private int div;
	private int direccion = 0;
	private int distancia = 0;
	private int init = 0;
	private int rand = 0;

	protected int tam = 1;

	public Estrategia_Cercanos(ArrayList<Sensor> sen, ArrayList<Integer> v) {
		super(sen, v);
		nombre = "Cercanos";
	}

	@Override
	public void asignaVariables(ArrayList<Integer> v) throws Exception {
		if (v.size() != tam) {
			new Exception("Variables no correspondientes con la estrategia");
		}
		direccion = v.get(0) + 1;

	}

	@Override
	public double estima(Nodo n) {
		double gan = 0;
		if ((gan = dameGananciaMedia(n)) < 0) {
			gan = Math.abs(gan);
			n.score = (direccion + distancia) * div / gan;
		} else {
			gan = Math.abs(gan);
			n.score = (direccion + distancia) * gan / div;
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
		div = 0;
		direccion = 0;
		distancia = 0;
		init = 0;
	};

	@Override
	public double calcula(Nodo n) {
		for (int i = 0; i < visitados.size(); i++)
			if (visitados.get(i).id == estado.actual.id)
				n.score = -10;
		if (visitados.isEmpty()) {
			estado.actual = estado.inicio;
			visitados.add(memoria.getNode(estado.getActual().id));
			rand = Proceso.getPseudoRand();
		} else {
			visitados.add(memoria.getNode(estado.getActual().id));
		}
		Nodo act = memoria.getNode(estado.getActual().id);
		act.score = 0;
		if (direccion == 0) {
			if (act.pos.x == 0)
				direccion = 4;
		} else if (direccion == 1) {
			if (act.pos.y == 0) {
				direccion = 3;
			}
			if (act.pos.x == estado.mapa.x) {
				direccion = 7;
			}
			if (act.pos.x == estado.mapa.x && act.pos.y == 0) {
				direccion = 5;
			}
		} else if (direccion == 2) {
			if (act.pos.x == estado.mapa.x)
				direccion = 6;
		} else if (direccion == 3) {
			if (act.pos.x == estado.mapa.x)
				direccion = 5;
			if (act.pos.y == estado.mapa.y)
				direccion = 1;
			if (act.pos.x == estado.mapa.x && act.pos.x == estado.mapa.y)
				direccion = 7;
		} else if (direccion == 4) {
			if (act.pos.y == estado.mapa.y)
				direccion = 0;
		} else if (direccion == 5) {
			if (act.pos.x == 0)
				direccion = 3;
			if (act.pos.y == estado.mapa.y)
				direccion = 7;
			if (act.pos.y == estado.mapa.y && act.pos.x == 0)
				direccion = 1;
		} else if (direccion == 6) {
			if (act.pos.x == 0)
				direccion = 2;
		} else if (direccion == 7) {
			if (act.pos.x == 0)
				direccion = 1;
			if (act.pos.y == 0)
				direccion = 5;
			if (act.pos.x == 0 && act.pos.y == 0)
				direccion = 3;
		}
		if (init == 0) {
			int ls = rand % 8;
			if (ls == 0) {
				if (n.pos.y + 1 == estado.actual.pos.y
						&& n.pos.x == estado.actual.pos.x) {
					n.score = 10;
					init = 1;
					direccion = 0;
				}
			} else if (ls == 1) {
				if (n.pos.y + 1 == estado.actual.pos.y
						&& n.pos.x - 1 == estado.actual.pos.x) {
					n.score = 10;
					init = 1;
					direccion = 1;
				}
			} else if (ls == 2) {
				if (n.pos.y == estado.actual.pos.y
						&& n.pos.x - 1 == estado.actual.pos.x) {
					n.score = 10;
					init = 1;
					direccion = 2;
				}
			} else if (ls == 3) {
				if (n.pos.y - 1 == estado.actual.pos.y
						&& n.pos.x - 1 == estado.actual.pos.x) {
					n.score = 10;
					init = 1;
					direccion = 3;
				}
			} else if (ls == 4) {
				if (n.pos.y - 1 == estado.actual.pos.y
						&& n.pos.x == estado.actual.pos.x) {
					n.score = 10;
					init = 1;
					direccion = 4;
				}
			} else if (ls == 5) {
				if (n.pos.y - 1 == estado.actual.pos.y
						&& n.pos.x + 1 == estado.actual.pos.x) {
					n.score = 10;
					init = 1;
					direccion = 5;
				}
			} else if (ls == 6) {
				if (n.pos.y == estado.actual.pos.y
						&& n.pos.x + 1 == estado.actual.pos.x) {
					n.score = 10;
					init = 1;
					direccion = 6;
				}
			} else if (ls == 7) {
				if (n.pos.y + 1 == estado.actual.pos.y
						&& n.pos.x + 1 == estado.actual.pos.x) {
					n.score = 10;
					init = 1;
					direccion = 7;
				}
			}
		} else {
			if (direccion == 0) {
				if (n.pos.y + 1 == estado.actual.pos.y
						&& n.pos.x == estado.actual.pos.x) {
					n.score = 10;
					init = 1;
				}
			} else if (direccion == 1) {
				if (n.pos.y + 1 == estado.actual.pos.y
						&& n.pos.x - 1 == estado.actual.pos.x) {
					n.score = 10;
					init = 1;
				}
			} else if (direccion == 2) {
				if (n.pos.y == estado.actual.pos.y
						&& n.pos.x - 1 == estado.actual.pos.x) {
					n.score = 10;
					init = 1;
				}
			} else if (direccion == 3) {
				if (n.pos.y - 1 == estado.actual.pos.y
						&& n.pos.x - 1 == estado.actual.pos.x) {
					n.score = 10;
					init = 1;
				}
			} else if (direccion == 4) {
				if (n.pos.y - 1 == estado.actual.pos.y
						&& n.pos.x == estado.actual.pos.x) {
					n.score = 10;
					init = 1;
				}
			} else if (direccion == 5) {
				if (n.pos.y - 1 == estado.actual.pos.y
						&& n.pos.x + 1 == estado.actual.pos.x) {
					n.score = 10;
					init = 1;
				}
			} else if (direccion == 6) {
				if (n.pos.y == estado.actual.pos.y
						&& n.pos.x + 1 == estado.actual.pos.x) {
					n.score = 10;
					init = 1;
				}
			} else if (direccion == 7) {
				if (n.pos.y + 1 == estado.actual.pos.y
						&& n.pos.x + 1 == estado.actual.pos.x) {
					n.score = 10;
					init = 1;
				}
			} else {
				n.score = 0;
			}
			if (n.id == estado.actual.id) {
				n.score = 0;
			}
			Nodo l = estado.mapa.getNode(n.id);
			l.setScore(n.score);

		}
		return n.score;
	}
}