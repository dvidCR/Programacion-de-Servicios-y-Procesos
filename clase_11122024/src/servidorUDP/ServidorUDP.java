package servidorUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServidorUDP {
	
	public static void main(String[] args) {
		try(DatagramSocket socket = new DatagramSocket(5000)) {
			System.out.println("Servidor UDP escuchando en el puerto 5000...");
			
			byte[] buffer = new byte[1024];
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			
			while(true) {
				socket.receive(packet);
				String mensaje = new String(packet.getData(), 0, packet.getLength());
				System.out.println("Mensaje recibido: " + mensaje);
				
				// Responder al cliente
				String respuesta = "¡Hola desde el servidor!";
				byte[] respuestaBytes = respuesta.getBytes();
				InetAddress clienteDireccion = packet.getAddress();
				int clientePuerto = packet.getPort();
				
				DatagramPacket respuestaPacket = new DatagramPacket(
						respuestaBytes, respuestaBytes.length, clienteDireccion, clientePuerto);
				socket.send(respuestaPacket);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
