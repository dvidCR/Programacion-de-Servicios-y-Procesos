package clase_11122024;


import java.io.*;   // Importar clases para la entrada y salida de datos
import java.net.*;  // Importar clases para trabajar con sockets y redes

public class Servidor {
    public static void main(String[] args) {
        try {
            // PISTA: Crea un servidor que escuche en un puerto (por ejemplo, 5000)
        	ServerSocket serverSocket = new ServerSocket(5000);
            
            System.out.println("Servidor escuchando en el puerto 5000...");
            
            while (true) {
                // PISTA: Aceptar la conexión de un cliente usando el objeto ServerSocket
                Socket socket = serverSocket.accept();
                System.out.println("Cliente conectado");

                // PISTA: Crear un flujo de entrada para recibir datos del cliente
                InputStream is = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                
                // PISTA: Crear un flujo de salida para enviar datos al cliente
                OutputStream os = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(os, true);
                
                // PISTA: Leer un mensaje del cliente utilizando el flujo de entrada
                String mensajeCliente = reader.readLine();
                System.out.println("Mensaje recibido del cliente: " + mensajeCliente);
                
                // PISTA: Imprimir el mensaje recibido en la consola
                String respuesta = "Mensaje recibido: " + mensajeCliente;
                writer.println(respuesta);
                
                // PISTA: Responder al cliente enviando un mensaje a través del flujo de salida
                socket.close();
                serverSocket.close();
            }
        } catch (IOException e) {
            // PISTA: Manejar posibles errores de entrada/salida
            e.printStackTrace();
        }
    }
}