package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        try {
            // Carica il file FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Home.fxml"));
            Parent root = loader.load();

            // Crea la scena
            Scene scene = new Scene(root, 1000, 600);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

            // Imposta la scena nello stage
            stage.setScene(scene);
            stage.setTitle("Segreteria");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
