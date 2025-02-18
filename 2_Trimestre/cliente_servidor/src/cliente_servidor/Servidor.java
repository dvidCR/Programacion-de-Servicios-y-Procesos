package cliente_servidor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    private int puerto;
    private ServerSocket serverSocket;

    public Servidor(int puerto) {
        this.puerto = puerto;
    }

    public void iniciar() {
        try {
            serverSocket = new ServerSocket(puerto);
            System.out.println("Servidor iniciado en el puerto " + puerto);
            Socket socket = serverSocket.accept();

            System.out.println("Cliente conectado: " + socket.getInetAddress().getHostAddress());

            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter salida = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            while (true) {
                String mensaje = entrada.readLine();
                if (mensaje == null || mensaje.equalsIgnoreCase("salir")) {
                    System.out.println("El cliente ha terminado la conexion");
                    break;
                }
                System.out.println("Cliente: " + mensaje);

                String respuesta = "Mensaje: " + mensaje;
                salida.write(respuesta);
                salida.newLine();
                salida.flush();
            }

            cerrarConexion(socket, entrada, salida);

        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }

    private void cerrarConexion(Socket socket, BufferedReader entrada, BufferedWriter salida) throws IOException {
        entrada.close();
        salida.close();
        socket.close();
        serverSocket.close();
        System.out.println("Conexion cerrada");
    }

    public static void main(String[] args) {
        Servidor servidor = new Servidor(3000);
        servidor.iniciar();
    }
}