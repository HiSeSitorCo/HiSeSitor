package HiSeSitor;

import gestionDatos.Datos;

public class Simulacion {

	public Estado estado;
	public int MAX_ENEMIGOS;
	public int time;

	public Simulacion(int maxEnemigos) {
		this.MAX_ENEMIGOS = maxEnemigos;
	}

	public void InitSimulacion() {
		estado = null;
		time = 0;
	}

	public int correSimulacion(Estrategia estr, Datos dato, String id, int max_time) {
		Nodo nodo = null;
		time = 0;
		if (true) { // OJO CUIDAO EL TRUE LO HE POUESTO YO _ GUILLE CONSULTARME
					// ANTES DE TOCARLO
			estado = new Estado(estr);
			for (Sensor x : estr.getSensores())
				x.setEstado(estado);

			estr.setEstado(estado);
			// estado.initGraph();
			estado.initEstado();
			addAleatOponents(MAX_ENEMIGOS);
		}

		Logger.debug("Comenzando. Quedan:" + estado.presas + " presas libres");
		try {
			while ((nodo = estado.busca()) != null && estado.presas > 0 && max_time-->0) {
				time++;
				Logger.debug("Posicion: " + estado.getActual()
						+ "\nRestantes: " + estado.presas);
				estado.guardaValoresEstado(dato, id, MAX_ENEMIGOS);
				estado.updateEstado(nodo);
				
			}
			Logger.debug("Simulacion terminada: " + nodo.toString()
					+ " Presas capturadas: " +(MAX_ENEMIGOS - estado.salvadas)+"\n"
							+ "Presas salvadas: "+estado.salvadas);
		} catch (NullPointerException e) {
			System.err.println(e.getMessage());
		}
		return (MAX_ENEMIGOS - estado.salvadas);

	}

	public void addAleatOponents(int n) {
		for (int i = 1; i <= n; i++)
			estado.addAleatOponent();
	}

	public void escribeResultados() {

	}

	public void createGrafo() {
		estado.initGraph();
	}

}
