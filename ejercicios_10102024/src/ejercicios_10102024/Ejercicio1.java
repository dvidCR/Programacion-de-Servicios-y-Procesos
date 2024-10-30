package ejercicios_10102024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ejercicio1 {
	
	public void echo() {
		ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "echo", "Hola Mundo");
		
		try {
			Process p = pb.start();
			BufferedReader bf = new BufferedReader(new InputStreamReader(p.getInputStream()));
			
			String line;
			
			while((line = bf.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
