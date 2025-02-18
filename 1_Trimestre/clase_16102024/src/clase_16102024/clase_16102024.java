package clase_16102024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class clase_16102024 {
    public static void main(String[] args) throws IOException {
        // Supongamos que este argumento proviene de una entrada del usuario
        String userInput = args[0];
        
        if (userInput.contains("&")) {
        	// userInput = userInput.replace("&", "");
        	userInput = null;
        }

        // Vulnerable: el input del usuario se concatena directamente en el comando
        ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", "echo ", userInput);
        Process p = processBuilder.start();
        
        BufferedReader bf = new BufferedReader(new InputStreamReader(p.getInputStream()));
		
		String line;
		
		while((line = bf.readLine()) != null) {
			System.out.println(line);
		}
    }
}