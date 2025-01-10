package socketLog;

import java.io.*;
import java.net.*;

public class ServidorLog {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345);
             BufferedWriter logWriter = new BufferedWriter(new FileWriter("servidor.log", true))) {

            logWriter.write("Servidor iniciado. Esperando conexión...\n");
            logWriter.flush();

            System.out.println("Servidor en espera de conexión...");
            Socket socket = serverSocket.accept();

            logWriter.write("Cliente conectado desde: " + socket.getInetAddress() + "\n");
            logWriter.flush();

            System.out.println("Cliente conectado.");

            socket.setSoTimeout(5000); // Timeout de 5 segundos

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String mensaje;
            while ((mensaje = in.readLine()) != null) {
                logWriter.write("Mensaje recibido: " + mensaje + "\n");
                logWriter.flush();

                System.out.println("Mensaje recibido: " + mensaje);
                out.println("Echo: " + mensaje);

                logWriter.write("Mensaje enviado al cliente: Echo: " + mensaje + "\n");
                logWriter.flush();
            }

        } catch (SocketTimeoutException e) {
            try (BufferedWriter logWriter = new BufferedWriter(new FileWriter("servidor.log", true))) {
                logWriter.write("Timeout: No se recibió respuesta del cliente en el tiempo esperado.\n");
                logWriter.flush();
            } catch (IOException logError) {
                System.err.println("Error escribiendo en el log: " + logError.getMessage());
            }
            System.out.println("Timeout: No se recibió respuesta del cliente en el tiempo esperado.");
        } catch (IOException e) {
            try (BufferedWriter logWriter = new BufferedWriter(new FileWriter("servidor.log", true))) {
                logWriter.write("Error en el servidor: " + e.getMessage() + "\n");
                logWriter.flush();
            } catch (IOException logError) {
                System.err.println("Error escribiendo en el log: " + logError.getMessage());
            }
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }
}