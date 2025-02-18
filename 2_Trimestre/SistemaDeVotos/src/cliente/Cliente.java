package cliente;

import java.io.*;
import java.net.*;

public class Cliente {
    public static void main(String[] args) {
        final String SERVIDOR = "localhost";
        final int PUERTO = 12345;

        try (Socket socket = new Socket(SERVIDOR, PUERTO);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Conectado al servidor de votación.");

            System.out.print("Introduce tu identificador: ");
            String clienteId = consoleInput.readLine();
            out.println(clienteId);

            System.out.print("Introduce tu voto (Opción A, Opción B, Opción C): ");
            String voto = consoleInput.readLine();
            out.println(voto);

            String respuesta = in.readLine();
            System.out.println(respuesta);

        } catch (UnknownHostException e) {
            System.err.println("Host desconocido: " + SERVIDOR);
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error de I/O al conectar con el servidor: " + SERVIDOR);
            e.printStackTrace();
        }
    }
}