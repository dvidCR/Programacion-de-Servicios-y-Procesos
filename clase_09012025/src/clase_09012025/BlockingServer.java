package clase_09012025;

import java.io.*;
import java.net.*;

public class BlockingServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("Servidor iniciado en el puerto 8080...");
        
        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Cliente conectado.");
            
            // Crear un hilo para manejar la conexión
            new Thread(() -> handleClient(clientSocket)).start();
        }
    }

    private static void handleClient(Socket socket) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Mensaje del cliente: " + inputLine);
                out.println("Echo: " + inputLine); // Responder al cliente
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}