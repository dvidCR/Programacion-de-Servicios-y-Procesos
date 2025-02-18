package app;

import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import java.io.File;
 
public class App extends Application {
 
    private String selectedFilePath;
 
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Seleccionar Archivo");
 
        // Botón para abrir el selector de archivos
        Button selectFileButton = new Button("Seleccionar Archivo");
        selectFileButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Seleccionar Archivo para Enviar");
 
            // Abrir el diálogo de selección de archivo
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
 
            if (selectedFile != null) {
                selectedFilePath = selectedFile.getAbsolutePath();
                System.out.println("Ruta del archivo seleccionada: " + selectedFilePath);
            } else {
                System.out.println("No se seleccionó ningún archivo.");
            }
        });
 
        // Botón para ejecutar una acción personalizada
        Button actionButton = new Button("Ejecutar Acción");
        actionButton.setOnAction(e -> {
            if (selectedFilePath != null) {
                // Aquí puedes pegar tu acción personalizada usando 'selectedFilePath'
                System.out.println("Ejecutando acción con el archivo: " + selectedFilePath);
            } else {
                System.out.println("Por favor, selecciona un archivo primero.");
            }
        });
 
        // Layout principal
        VBox layout = new VBox(10, selectFileButton, actionButton);
        layout.setStyle("-fx-padding: 20px; -fx-alignment: center;");
 
        Scene scene = new Scene(layout, 300, 150);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}