package clase_11122024;

import java.io.*;   // Importar clases para entrada y salida de datos
import java.net.*;  // Importar clases para trabajar con sockets y redes
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        try {
        	Scanner sc = new Scanner(System.in);
            // PISTA: Crear un socket para conectarse al servidor.         	
            // Usa la dirección IP del servidor ("127.0.0.1" para localhost) y el puerto (5000).
        	Socket socket = new Socket("127.0.0.1", 5000);
        	
            // PISTA: Crear un flujo de salida para enviar datos al servidor
        	OutputStream os = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(os, true);
            
            // PISTA: Crear un flujo de entrada para recibir datos del servidor
            InputStream is = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            
            // PISTA: Enviar un mensaje al servidor utilizando el flujo de salida
            System.out.print("Escribe le mensaje para el servidor: ");
            String mensaje = sc.nextLine();
            writer.println(mensaje);
            System.out.println("Mensaje enviado al servidor: " + mensaje);
            
            // PISTA: Leer la respuesta del servidor usando el flujo de entrada
            String respuesta = reader.readLine();
            System.out.println("Respuesta del servidor: " + respuesta);
            
            // PISTA: Imprimir la respuesta del servidor en la consola
            socket.close();
            sc.close();
            
        } catch (IOException e) {
            // PISTA: Manejar posibles errores de entrada/salida
            e.printStackTrace();
        }
    }
}