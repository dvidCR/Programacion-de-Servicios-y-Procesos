package servidor;

import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("Servidor iniciado. Esperando conexiones...");

        Socket socket = serverSocket.accept();
        System.out.println("Cliente conectado.");

        BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

        String palabraSecreta = "programacion"; // Palabra a adivinar
        StringBuilder progreso = new StringBuilder("_".repeat(palabraSecreta.length()));
        int intentosRestantes = 6;

        salida.println("Bienvenido al juego del Ahorcado!");
        salida.println("Adivina la palabra: " + progreso);

        while (intentosRestantes > 0 && progreso.indexOf("_") != -1) {
            salida.println("\nIntentos restantes: " + intentosRestantes);
            salida.println("Introduce una letra:");

            String letra = entrada.readLine();
            if (letra == null || letra.length() != 1) {
                salida.println("Entrada inválida. Por favor, introduce una sola letra.");
                continue;
            }

            letra = letra.toLowerCase();

            if (palabraSecreta.contains(letra)) {
                for (int i = 0; i < palabraSecreta.length(); i++) {
                    if (palabraSecreta.charAt(i) == letra.charAt(0)) {
                        progreso.setCharAt(i, letra.charAt(0));
                    }
                }
                salida.println("Correcto! " + progreso);
            } else {
                intentosRestantes--;
                salida.println("Incorrecto! La letra '" + letra + "' no está en la palabra.");
            }
        }

        if (progreso.indexOf("_") == -1) {
            salida.println("Felicidades! Has adivinado la palabra: " + palabraSecreta);
        } else {
            salida.println("Has perdido! La palabra era: " + palabraSecreta);
        }

        socket.close();
        serverSocket.close();
    }
}