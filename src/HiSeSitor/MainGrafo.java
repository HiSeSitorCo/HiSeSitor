package HiSeSitor;

import java.util.ArrayList;

import edu.uci.ics.jung.visualization.layout.PersistentLayout.Point;


public class MainGrafo {

	/**
	 * Main de prueba
	 * 
	 * @param args
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Nodo n = new Nodo(1,30,new Point(1,1));
		Nodo m = new Nodo(1,40,new Point(1,1));
		m.setEstimacion(false);
		n.setEstimacion(false);
		int lis1[]={1,1,1,1,1,1,1,1};
		int lis2[]={1,1,1,1,1,1,1,1};
		ArrayList<Integer> ai1 = new ArrayList<>();
		ArrayList<Integer> ai2 = new ArrayList<>();
		for(int i = 0; i < lis1.length; i++)
			ai1.add(lis1[i]);
		for(int i = 0; i < lis2.length; i++)
			ai2.add(lis2[i]);
		n.setListaAristas(ai1);
		n.setListaAristas(ai2);
		System.out.println(n.diffOfInfo(m));
		}
		
		
	}


