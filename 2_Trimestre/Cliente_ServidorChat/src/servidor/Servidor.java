package servidor;

import java.io.*;
import java.net.*;
import java.util.*;

public class Servidor {
    private static Map<String, PrintWriter> clientes = new HashMap<>();

    public static void main(String[] args) {
        final int PUERTO = 12345;

        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            System.out.println("Servidor de mensajes iniciado en el puerto " + PUERTO);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(new ManejadorCliente(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ManejadorCliente implements Runnable {
        private Socket clientSocket;
        private String nombre;

        public ManejadorCliente(Socket socket) {
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                nombre = in.readLine();
                if (clientes.containsKey(nombre)) {
                    out.println("Nombre ya en uso. Desconectando...");
                    return;
                }

                clientes.put(nombre, out);
                out.println("Bienvenido, " + nombre + ". Usa /msg <destinatario> <mensaje> para enviar mensajes.");

                String input;
                while ((input = in.readLine()) != null) {
                    if (input.startsWith("/msg ")) {
                        String[] partes = input.split(" ", 3);
                        if (partes.length < 3) {
                            out.println("Formato incorrecto. Usa /msg <destinatario> <mensaje>");
                            continue;
                        }

                        String destinatario = partes[1];
                        String mensaje = partes[2];

                        if (!clientes.containsKey(destinatario)) {
                            out.println("Destinatario no encontrado.");
                            continue;
                        }

                        clientes.get(destinatario).println("Mensaje de " + nombre + ": " + mensaje);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (nombre != null) {
                    clientes.remove(nombre);
                }
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}