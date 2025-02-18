package clienteFTP;

//Cliente.java
import java.io.*;
import java.net.*;

public class Cliente {
 public static void main(String[] args) {
     String servidor = "192.168.10.134"; // Dirección del servidor
     int puerto = 12345; // Puerto del servidor
     String rutaDestino = "archivo_recibido.txt"; // Ruta donde se guardará el archivo recibido

     try (Socket socket = new Socket(servidor, puerto)) {
         System.out.println("Conectado al servidor");

         // Flujo de entrada para recibir datos del servidor
         DataInputStream entrada = new DataInputStream(socket.getInputStream());

         // Leer respuesta inicial del servidor
         String respuesta = entrada.readUTF();
         if ("OK".equals(respuesta)) {
             // Leer tamaño del archivo
             long tamanoArchivo = entrada.readLong();
             System.out.println("Recibiendo archivo de tamaño: " + tamanoArchivo + " bytes");

             // Preparar para recibir el archivo
             FileOutputStream fileOutput = new FileOutputStream(rutaDestino);
             byte[] buffer = new byte[4096];
             int bytesLeidos;
             long bytesRecibidos = 0;

             // Leer y guardar el contenido del archivo
             while (bytesRecibidos < tamanoArchivo && (bytesLeidos = entrada.read(buffer)) != -1) {
                 fileOutput.write(buffer, 0, bytesLeidos);
                 bytesRecibidos += bytesLeidos;
             }

             fileOutput.close();
             System.out.println("Archivo recibido y guardado como: " + rutaDestino);
         } else {
             System.out.println("Error desde el servidor: " + respuesta);
         }

     } catch (IOException e) {
         e.printStackTrace();
     }
 }
}