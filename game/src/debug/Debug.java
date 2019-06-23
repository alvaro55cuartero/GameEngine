package debug;

import java.io.IOException;
import java.util.ArrayList;

import tools.Lector;

public class Debug {

	private static String txt = "";

	private static ArrayList<Long> time = new ArrayList<Long>();

	private static long initTem = 0;

	public static void setTime(String msg) {
		long time = System.nanoTime();
		txt += msg + ": " + (initTem - time) + "\n";
		initTem = time;
	}

	public static void msg(String msg) {
		txt += msg + "\n";
	}

	public static void init() {
		initTem = System.nanoTime();
	}

	public static void end() {
		time.add(System.nanoTime() - initTem);
	}

	public static void media() {
		double sum = 0;
		for (int i = 0; i < time.size(); i++) {
			sum += time.get(i);
		}
		sum /= time.size();
		sum /= 1000000000;
		System.out.println(sum);
		System.out.println(1 / sum);
	}

	public static void save() {
		media();
		try {
			Lector.createFile("log/debug.txt", txt);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
