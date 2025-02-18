package servidor;

import java.io.*;
import java.net.*;
import java.util.Random;

public class Servidor {
    private static final int PUERTO = 12345;
    private static final Random random = new Random();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            System.out.println("Servidor de adivinanza iniciado en el puerto " + PUERTO);

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
        private int numeroSecreto;

        public ManejadorCliente(Socket socket) {
            this.clientSocket = socket;
            this.numeroSecreto = random.nextInt(100) + 1;
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                out.println("Bienvenido. Adivina un número entre 1 y 100.");

                String input;
                while ((input = in.readLine()) != null) {
                    int intento = Integer.parseInt(input);
                    if (intento < numeroSecreto) {
                        out.println("Mayor");
                    } else if (intento > numeroSecreto) {
                        out.println("Menor");
                    } else {
                        out.println("Correcto. ¡Felicidades!");
                        break;
                    }
                }

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
}