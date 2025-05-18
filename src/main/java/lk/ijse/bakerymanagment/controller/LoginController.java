package lk.ijse.bakerymanagment.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class LoginController {
    public AnchorPane ancLogin;
    public TextField txtUsername;
    public TextField txtPassword;

    public void nameOnAction(ActionEvent actionEvent) {
    }

    public void PasswordOnAction(ActionEvent actionEvent) {
    }

    public void LoginOnAction(ActionEvent actionEvent) throws IOException {
        if (txtUsername.getText().equals("samoksha") || txtPassword.getText().equals("samo")) {
            ancLogin.getChildren().clear();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/DashBordPage.fxml"));
            pane.prefHeightProperty().bind(ancLogin.heightProperty());
            pane.prefWidthProperty().bind(ancLogin.widthProperty());
            ancLogin.getChildren().add(pane);
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid Username or Password").showAndWait();
            return;
        }

    }

    public void SignUpOnAction(ActionEvent actionEvent) {
    }
}
