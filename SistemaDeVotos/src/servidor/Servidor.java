package servidor;

import java.io.*;
import java.net.*;
import java.util.*;

public class Servidor {
    private static Map<String, Integer> votos = new HashMap<>();
    private static Set<String> clientesVotados = new HashSet<>();

    public static void main(String[] args) {
        final int PUERTO = 12345;

        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            System.out.println("Servidor de votación iniciado en el puerto " + PUERTO);

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

        public ManejadorCliente(Socket socket) {
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                String clienteId = in.readLine();
                if (clientesVotados.contains(clienteId)) {
                    out.println("Ya has votado. No puedes votar más de una vez.");
                    return;
                }

                String voto = in.readLine();
                if (voto == null || !votos.containsKey(voto)) {
                    out.println("Voto inválido. Opciones válidas: " + votos.keySet());
                    return;
                }

                votos.put(voto, votos.get(voto) + 1);
                clientesVotados.add(clienteId);
                out.println("Voto registrado. Resultados actuales: " + votos);

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static {
        votos.put("A", 0);
        votos.put("B", 0);
        votos.put("C", 0);
    }
}