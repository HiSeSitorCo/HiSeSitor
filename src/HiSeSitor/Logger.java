package HiSeSitor;

/**
 * 
 * @author HiSeSiTor Co.
 * Clase Logger para activar o desactivar las trazas del programa
 */
public class Logger {

	public static boolean debug;

	public static void debug(String s) {
		if (debug == true) {
			System.out.println(s);
		}
	}

	public static void error(String s) {
		if (debug == true)
			System.err.println(s);
	}

}
