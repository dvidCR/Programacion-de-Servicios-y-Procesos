package servidorHTML;

import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        int port = 8080;
        
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Servidor iniciado en el puerto " + port);
            
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
                    
                    String requestLine = in.readLine();
                    System.out.println("Solicitud recibida: " + requestLine);
                    
                    // Responder solo a solicitudes GET
                    if (requestLine != null && requestLine.startsWith("GET")) {
                        sendResponse(out);
                    }
                } catch (IOException e) {
                    System.out.println("Error al manejar la conexión: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("No se pudo iniciar el servidor en el puerto " + port);
        }
    }

    private static void sendResponse(PrintWriter out) {
        String response = """
                
                
                HTTP/1.1 200 OK
                Content-Type: text/html
                
                <html>
                <head><title>Servidor Java</title></head>
                <body><h1>¡Hola desde Java!</h1></body>
                </html>
                
                        
                        """;
        out.println(response);
    }
}