package engine.tools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileUtils {

	public static String readTxt(String path) {
		StringBuilder result = new StringBuilder();

		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			String buffer = "";
			while ((buffer = reader.readLine()) != null) {
				result.append(buffer + "\n");
			}
			reader.close();
		} catch (IOException e) {
			System.err.println("no existe el archivo: " + path);
			e.printStackTrace();
			System.exit(-1);
		}

		return result.toString();
	}

}
