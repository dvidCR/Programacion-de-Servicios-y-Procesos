package servidorUDP;

import java.net.*;

public class ClienteUDP {
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress servidorDireccion = InetAddress.getByName("127.0.0.1");
            int puerto = 5000;
 
            // Enviar mensaje al servidor
            String mensaje = "¡Hola, servidor!";
            byte[] mensajeBytes = mensaje.getBytes();
            DatagramPacket packet = new DatagramPacket(
                    mensajeBytes, mensajeBytes.length, servidorDireccion, puerto);
            socket.send(packet);
            System.out.println("Mensaje enviado al servidor.");
 
            // Recibir respuesta del servidor
            byte[] buffer = new byte[1024];
            DatagramPacket respuestaPacket = new DatagramPacket(buffer, buffer.length);
            socket.receive(respuestaPacket);
 
            String respuesta = new String(respuestaPacket.getData(), 0, respuestaPacket.getLength());
            System.out.println("Respuesta del servidor: " + respuesta);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}