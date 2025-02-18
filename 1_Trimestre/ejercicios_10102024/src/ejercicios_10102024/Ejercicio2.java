package ejercicios_10102024;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Ejercicio2 {
	
	public void directory() {
		Scanner sc = new Scanner(System.in);
		
		ProcessBuilder pb = new ProcessBuilder("cmd");
		System.out.print("Escribe el directorio que quieres ver: ");
		
		
		ProcessBuilder directory = pb.directory(new File(sc.nextLine()));
		sc.close();
		
		try {
			Process p = directory.start();
			
			BufferedReader bf = new BufferedReader(new InputStreamReader(p.getInputStream()));
			
			String line;
			
			while((line = bf.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
