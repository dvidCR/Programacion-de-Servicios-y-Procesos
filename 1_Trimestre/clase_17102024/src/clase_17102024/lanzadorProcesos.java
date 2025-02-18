package clase_17102024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class lanzadorProcesos {
	
	public void lanzar() {
		ProcessBuilder pb1 = new ProcessBuilder("cmd.exe", "/c", "echo hola mundo");
		ProcessBuilder pb2 = new ProcessBuilder("notepad.exe");
		ProcessBuilder pb3 = new ProcessBuilder("cmd.exe", "/c", "find /c /v \"\" C:\\Clase\\Programacion-de-Servicios-y-Procesos\\clase_17102024\\src\\clase_17102024\\lanzadorProcesos.java");

		try {
			Process p1 = pb1.start();
			
			BufferedReader br1 = new BufferedReader(new InputStreamReader(p1.getInputStream()));
			
			String line;
			
			while ((line = br1.readLine()) != null) {
				System.out.println(line);
			}
			
			pb2.start();
			
			Process p3 = pb3.start();
			
			BufferedReader br2 = new BufferedReader(new InputStreamReader(p3.getInputStream()));
			
			while ((line = br2.readLine()) != null) {
				System.out.println(line);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
