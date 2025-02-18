package clase_26092024;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.Reader;

public class Ejercicio2 {

    public void powershell() {
        try {
            // Start PowerShell script
            ProcessBuilder processBuilder = new ProcessBuilder("powershell", "-File", "procesos.ps1");
            Process process = processBuilder.start();

            // Create a BufferedReader to capture the output of the PowerShell script
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            // Write the output to a text file (salida.txt)
            BufferedWriter writer = new BufferedWriter(new FileWriter("salida.txt"));

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }

            // Close the streams
            writer.close();
            reader.close();

            // Open the text file with Notepad after writing is done
            ProcessBuilder notepad = new ProcessBuilder("notepad.exe", "salida.txt");
            notepad.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
