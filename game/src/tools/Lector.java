package tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lector {

	static String[] files = new File("res").list();

	public static void createFile(String ruta, String text) throws IOException {
		File archivo = new File(ruta);
		BufferedWriter bw;
		bw = new BufferedWriter(new FileWriter(archivo));
		bw.write(text);
		bw.close();
	}

	public static String leerArchivoTexto(final String ruta) throws IOException {
		String contenido = "";
		String cadena;
		FileReader f = new FileReader(ruta);
		BufferedReader b = new BufferedReader(f);
		while ((cadena = b.readLine()) != null) {
			contenido += cadena + "\n";
		}
		b.close();
		return contenido;
	}

	public static void salto(String ruta) throws IOException {
		File archivo = new File(ruta);
		BufferedWriter bw;
		bw = new BufferedWriter(new FileWriter(archivo));
		bw.newLine();
		bw.close();
	}

	public static String[] lista(String path) {

		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		String[] files = new String[listOfFiles.length];

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				files[i] = listOfFiles[i].getName();
			}
		}

		return files;
	}

	public static String insert(String text, String insert, int period) {
		Pattern p = Pattern.compile("(.{" + period + "})", Pattern.DOTALL);
		Matcher m = p.matcher(text);
		return m.replaceAll("$1" + insert);
	}
}