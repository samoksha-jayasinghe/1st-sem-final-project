package lk.ijse.bakerymanagment;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class AppInitializer extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("/view/LoginPage.fxml"));


        Scene scene = new Scene(parent);

        primaryStage.setScene(scene);
        //primaryStage.setResizable(false); //true
        primaryStage.setTitle("Bakery Management Application ");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}