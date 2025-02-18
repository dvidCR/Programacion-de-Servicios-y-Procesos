package cliente;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
 
public class Cliente extends Application {
    private PrintWriter out;
    private BufferedReader in;
    private Label wordLabel;
    private Label messageLabel;
 
    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
 
        wordLabel = new Label("Conectando...");
        messageLabel = new Label();
 
        GridPane buttonGrid = new GridPane();
        buttonGrid.setAlignment(Pos.CENTER);
        buttonGrid.setHgap(5);
        buttonGrid.setVgap(5);
 
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < alphabet.length(); i++) {
            String letter = String.valueOf(alphabet.charAt(i));
            Button button = new Button(letter);
            button.setOnAction(e -> sendLetter(letter));
            buttonGrid.add(button, i % 6, i / 6);
        }
 
        root.getChildren().addAll(wordLabel, messageLabel, buttonGrid);
 
        primaryStage.setScene(new Scene(root, 400, 400));
        primaryStage.setTitle("Ahorcado");
        primaryStage.show();
 
        connectToServer();
    }
 
    private void connectToServer() {
        try {
            Socket socket = new Socket("192.168.10.134", 12345); // Cambia localhost por la IP del servidor si es necesario.
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
 
            new Thread(() -> {
                try {
                    String response;
                    while ((response = in.readLine()) != null) {
                        String finalResponse = response;
                        Platform.runLater(() -> {
                            if (finalResponse.startsWith("Palabra:")) {
                                wordLabel.setText(finalResponse.substring(8).trim());
                            } else {
                                messageLabel.setText(finalResponse);
                            }
                        });
                    }
                } catch (IOException e) {
                    Platform.runLater(() -> messageLabel.setText("Conexión perdida."));
                    e.printStackTrace();
                }
            }).start();
        } catch (IOException e) {
            Platform.runLater(() -> messageLabel.setText("Error al conectar con el servidor."));
            e.printStackTrace();
        }
    }
 
    private void sendLetter(String letter) {
        if (out != null) {
            out.println(letter);
        }
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}