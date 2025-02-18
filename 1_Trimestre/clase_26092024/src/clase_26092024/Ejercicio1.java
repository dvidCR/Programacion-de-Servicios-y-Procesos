package clase_26092024;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Ejercicio1 {
	
	public void ejecutar() {
		try {
            ProcessBuilder processBuilder = new ProcessBuilder("python", "C:\\Clase\\Desarrollo_de_Interfaces\\1º Trimestre\\Ejercicios\\comboBox.py");
            Process process = processBuilder.start();
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
