package gestionDatos;

public class PruebaMain {

	public static void main(String[] args) {

		Datos d = new Datos();

		String cadena1 = "estrategia1,iteracion1,1,1,1";
		String cadena2 = "estrategia1,iteracion1,2,2,2";
		String cadena3 = "estrategia1,iteracion2,1,1,1";
		String cadena4 = "estrategia1,iteracion2,1,1,1";

		String cadena5 = "estrategia2,iteracion1,1,1,1";
		String cadena6 = "estrategia2,iteracion1,3,3,3";
		String cadena7 = "estrategia2,iteracion2,1,1,1";

		String cadena8 = "estrategia1,iteracion1,6,6,6";
		String cadena9 = "estrategia2,iteracion2,4,4,4";

		d.agregaDatos(cadena1);
		d.agregaDatos(cadena2);
		d.agregaDatos(cadena3);
		d.agregaDatos(cadena4);
		d.agregaDatos(cadena5);
		d.agregaDatos(cadena6);
		d.agregaDatos(cadena7);
		d.agregaDatos(cadena8);
		d.agregaDatos(cadena9);

		d.procesaDatos();

		d.mejorIteracion("estrategia1");
		System.out.println();
		d.mejorIteracion("estrategia2");

		System.out.println("finiquitao");
	}

}
