package lk.ijse.bakerymanagment.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginPageController {
    public AnchorPane ancSignin;
    public TextField txtUsername;
    public PasswordField txtPassword;

    public Button btnSignIn;

    public void goToForgetPassword(MouseEvent mouseEvent) {
    }

    public void goToSignUpPage(MouseEvent mouseEvent) {
    }



    public void btnSignInOnAction(ActionEvent actionEvent) {
            if (txtUsername.getText().equals("123") && txtPassword.getText().equals("123")) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/DashBordPage.fxml"));
                    Parent dashboard = fxmlLoader.load();

                    Stage dashboardStage = new Stage();
                    dashboardStage.setScene(new Scene(dashboard));
                    dashboardStage.setTitle("Dashboard");
                    dashboardStage.setResizable(true);
                    dashboardStage.show();

                    // Optional: Close the login window
                    ((Stage) txtUsername.getScene().getWindow()).close();

                } catch (IOException e) {
                    e.printStackTrace();
                    new Alert(Alert.AlertType.ERROR, "Failed to load Dashboard").show();
                }
            }
        }
    }

