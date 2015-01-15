package HiSeSitor;

public class Simulacion {

	public Estado estado;
	public int MAX_ENEMIGOS;
	public int time;

	public Simulacion(int maxEnemigos) {
		this.MAX_ENEMIGOS = maxEnemigos;
	}

	public void correSimulacion(Estrategia estr) {
		Nodo nodo = null;
		time = 0;
				if (estado == null) {
				estado = new Estado(estr);
				for (Sensor x : estr.getSensores())
					x.setEstado(estado);

				estr.setEstado(estado);
				//estado.initGraph();
				addAleatOponents(10);
				estado.initEstado();
			}

			System.out.println("QUEDAN " + estado.presas
					+ " PRESAS LIBRES TODAVIA");
			try{
			while ((nodo = estado.busca()) != null && estado.presas > 0) {
				time++;
				System.out.println("ESTAS EN LA POSICION: "
						+ estado.getActual() + "\tQUEDAN " + estado.presas
						+ " PRESAS LIBRES TODAVIA");
				estado.guardaValoresEstado();
				estado.updateEstado(nodo);
			}
			System.out.println("Simulación terminada: "+nodo.toString()+" presas: "+estado.presas);
			}catch (NullPointerException e){
				System.err.println(e.getMessage());
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
