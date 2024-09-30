package clase_26092024;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;

public class Ejercicio2 {
	
	public void powershell() {
		try {
            ProcessBuilder processBuilder = new ProcessBuilder("powershell", "procesos.ps1");
            BufferedReader ps = new BufferedReader(null, "salida.txt");
            Process process = processBuilder.start();
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
            	 BufferedWriter rd = new BufferedWriter(ps);
            	 rd.write(line);
            	 
            	 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
