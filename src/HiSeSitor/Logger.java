package HiSeSitor;

public class Logger {
	
	public static boolean debug;
	
	public static void debug (String s){
		if(debug == true){
			System.out.println(s);
		}
	}

}
