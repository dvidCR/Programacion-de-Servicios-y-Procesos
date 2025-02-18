package socketLog;

import java.io.*;
import java.net.*;

public class ClienteLog {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 12345);
             BufferedWriter logWriter = new BufferedWriter(new FileWriter("cliente.log", true))) {

            logWriter.write("Cliente iniciado. Conectándose al servidor...\n");
            logWriter.flush();

            socket.setSoTimeout(5000); // Timeout de 5 segundos

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String mensaje = "Hola, servidor!";
            logWriter.write("Enviando mensaje al servidor: " + mensaje + "\n");
            logWriter.flush();

            out.println(mensaje);
            System.out.println("Esperando respuesta del servidor...");

            try {
                String respuesta = in.readLine();
                logWriter.write("Respuesta recibida del servidor: " + respuesta + "\n");
                logWriter.flush();

                System.out.println("Respuesta del servidor: " + respuesta);
            } catch (SocketTimeoutException e) {
                logWriter.write("Timeout: No se recibió respuesta del servidor en el tiempo esperado.\n");
                logWriter.flush();

                System.out.println("Timeout: No se recibió respuesta del servidor en el tiempo esperado.");
            }

        } catch (IOException e) {
            try (BufferedWriter logWriter = new BufferedWriter(new FileWriter("cliente.log", true))) {
                logWriter.write("Error en el cliente: " + e.getMessage() + "\n");
                logWriter.flush();
            } catch (IOException logError) {
                System.err.println("Error escribiendo en el log: " + logError.getMessage());
            }
            System.out.println("Error en el cliente: " + e.getMessage());
        }
    }
}