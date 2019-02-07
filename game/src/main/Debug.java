package main;

public class Debug {

	public enum Grado {
		notificacion, advertencia, error
	}

	private static Grado currentGrado = Grado.error;
	private static boolean pruebas = true;

	public static void Sms(String txt, Grado grado) {
		if (Const.debug) {
			if (grado.compareTo(currentGrado) <= 0) {
				System.out.println(txt);
			}
		}
	}

	public static void prueba(String txt) {
		if (pruebas) {
			System.out.println(txt);
		}
	}
}
