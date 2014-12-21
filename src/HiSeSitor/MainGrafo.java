package HiSeSitor;


public class MainGrafo {

	/**
	 * Main de prueba
	 * 
	 * @param args
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Estado e = new Estado(null);
		System.out.println(e.mapa.getShortestPath(new Nodo(3,0), new Nodo(7,0)).toString());
		
	}

}
