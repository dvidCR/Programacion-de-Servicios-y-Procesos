package clienteHTML;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        String host = "127.0.0.1";
        int puerto = 8080;

        try (Socket socket = new Socket(host, puerto)) {
            System.out.println("Conectado al servidor.");

            // Flujos de entrada y salida
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

            // Solicitar el directorio al usuario
            Scanner scanner = new Scanner(System.in);
            System.out.print("Introduce el directorio que deseas listar: ");
            String directorio = scanner.nextLine();

            // Enviar el directorio al servidor
            salida.println(directorio);

            // Recibir y mostrar el resultado
            String resultado;
            while ((resultado = entrada.readLine()) != null) {
                System.out.println(resultado);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}