package clase_26092024;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ejercicio3 {
	
	public void bot() {
		ProcessBuilder botT = new ProcessBuilder("powershell","-File", "bot.ps1");
		try {
			Process process = botT.start();
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			BufferedWriter writer = new BufferedWriter(new FileWriter("salida.txt"));

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
            ProcessBuilder notepad = new ProcessBuilder("notepad.exe", "salida.txt");
            notepad.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
