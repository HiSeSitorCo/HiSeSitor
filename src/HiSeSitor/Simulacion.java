package HiSeSitor;

public class Simulacion {

	public Estado estado;
	public int MAX_ENEMIGOS;

	public Simulacion(int maxEnemigos) {
		this.MAX_ENEMIGOS = maxEnemigos;
	}

	public void correSimulacion(Estrategia estr) {
		Nodo nodo = null;

		/*for (int enemigos = 1; enemigos < MAX_ENEMIGOS; enemigos++) {

			System.out.println("*** SIMULACION CON " + enemigos
					+ " ENEMIGOS ***");
*/
			if (estado == null) {
				estado = new Estado(estr);
				for (Sensor x : estr.getSensores())
					x.setEstado(estado);

				estr.setEstado(estado);
				estado.initGraph();
				addAleatOponents(MAX_ENEMIGOS);
				estado.initEstado();
			}

			System.out.println("QUEDAN " + estado.presas
					+ " PRESAS LIBRES TODAVIA");

			while ((nodo = estado.busca()) != null && estado.presas > 0) {

				System.out.println("ESTAS EN LA POSICION: "
						+ estado.getActual() + "\tQUEDAN " + estado.presas
						+ " PRESAS LIBRES TODAVIA");
				estado.guardaValoresEstado();
				estado.updateEstado(nodo);
			}
		//}

	}

	public void addAleatOponents(int n) {
		for (int i = 0; i < n; i++)
			estado.addAleatOponent();
	}

	public void escribeResultados() {

	}

	public void createGrafo() {
		estado.initGraph();
	}

}
