package cliente_servidor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    private String servidor;
    private int puerto;
    private Socket socket;
    private BufferedReader entrada;
    private BufferedWriter salida;

    public Cliente(String servidor, int puerto) {
        this.servidor = servidor;
        this.puerto = puerto;
    }

    public void conectar() {
        try {
            socket = new Socket(servidor, puerto);
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            salida = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            System.out.println("Conectado al servidor");
            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.print("Mensaje: ");
                String mensaje = sc.nextLine();
                enviarMensaje(mensaje);

                if (mensaje.equalsIgnoreCase("salir")) {
                    break;
                }

                String respuesta = recibirMensaje();
                System.out.println("Servidor: " + respuesta);
            }
            cerrarConexion();
            sc.close();

        } catch (IOException e) {
            System.err.println("Error de conexion: " + e.getMessage());
        }
    }

    private void enviarMensaje(String mensaje) throws IOException {
        salida.write(mensaje);
        salida.newLine();
        salida.flush();
    }

    private String recibirMensaje() throws IOException {
        return entrada.readLine();
    }

    private void cerrarConexion() throws IOException {
        salida.close();
        entrada.close();
        socket.close();
        System.out.println("Conexion cerrada");
    }

    public static void main(String[] args) {
        Cliente cliente = new Cliente("localhost", 8080);
        cliente.conectar();
    }
}