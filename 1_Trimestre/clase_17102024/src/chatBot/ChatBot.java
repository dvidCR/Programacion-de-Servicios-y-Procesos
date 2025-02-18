package chatBot;

import java.io.*;
import java.util.Scanner;

public class ChatBot {
    public static void main(String[] args) {
        ProcessBuilder pb = new ProcessBuilder("python", "chatbot.py");
        try {
            Process proceso = pb.start();

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(proceso.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));

            Scanner sc = new Scanner(System.in);
            String mensajeUsuario;

            System.out.println("Inicia conversación con el chat bot (escribe 'adios' para salir):");
            while (true) {
                System.out.print("Tú: ");
                mensajeUsuario = sc.nextLine();
                writer.write(mensajeUsuario + "\n");
                writer.flush();

                String respuesta = reader.readLine();
                System.out.println("Bot: " + respuesta);

                if (mensajeUsuario.equalsIgnoreCase("adios")) {
                    break;
                }
            }

            proceso.destroy();
            
            sc.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}