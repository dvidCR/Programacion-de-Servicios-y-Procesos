package JuegoAdivinanza;


import java.io.*;
import java.util.Scanner;

public class JuegoAdivinanza {
    public static void main(String[] args) {
        ProcessBuilder pb = new ProcessBuilder("python", "C:\\Clase\\Programacion-de-Servicios-y-Procesos\\clase_17102024\\src\\JuegoAdivinanza\\generador.py");
        try {
            Process proceso = pb.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            int numeroSecreto = Integer.parseInt(reader.readLine());

            Scanner sc = new Scanner(System.in);
            int intento = 0;
            System.out.println("Adivina el número entre 1 y 100:");

            while (intento != numeroSecreto) {
                intento = sc.nextInt();
                if (intento < numeroSecreto) {
                    System.out.println("El número es mayor.");
                } else if (intento > numeroSecreto) {
                    System.out.println("El número es menor.");
                } else {
                    System.out.println("¡Felicidades! Has adivinado el número.");
                }
            }
            
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
