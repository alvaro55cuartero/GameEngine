package debug;

import main.Const;

public class DebugWIP {

	public static final int DEBUG_CURSOR_POSITION = 0x1;
	public static final int DEBUG_KEY = 0x2;

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

	public static void enable(int code) {
		switch (code) {
		case 1:
		}
	}

	public static String debugInfo(boolean console) {
		return null;
	}
}
