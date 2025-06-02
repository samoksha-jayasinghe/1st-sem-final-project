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

public class SignUpControllerPage {

    public AnchorPane ancSignup;
    public TextField txtFirstName;
    public TextField txtLastName;
    public TextField txtUsername;
    public PasswordField txtNewPassword;
    public PasswordField txtConfirmPassword;
    public Button btnSignup;

    public void btnSignupOnAction(ActionEvent actionEvent) {
        String firstName = txtFirstName.getText().trim();
        String lastName = txtLastName.getText().trim();
        String username = txtUsername.getText().trim();
        String password = txtNewPassword.getText();
        String confirmPassword = txtConfirmPassword.getText();

        if (firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() ||
                password.isEmpty() || confirmPassword.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "All fields are required!");
            return;
        }

        if (!password.equals(confirmPassword)) {
            showAlert(Alert.AlertType.ERROR, "Passwords do not match!");
            return;
        }


        showAlert(Alert.AlertType.INFORMATION, "Sign-up successful! Please log in.");
        clearFields();
        navigateToLoginPage("/view/LoginPage.fxml");
    }

    public void goToSigninPage(MouseEvent mouseEvent) {
        navigateToLoginPage("/view/LoginPage.fxml");
    }

    private void navigateToLoginPage(String path) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(path));
            Stage stage = (Stage) ancSignup.getScene().getWindow();
            stage.setScene(new Scene(parent));
            stage.setTitle("Login Page");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Unable to open the login page.");
        }
    }

    private void showAlert(Alert.AlertType alertType, String message) {
        new Alert(alertType, message).show();
    }

    private void clearFields() {
        txtFirstName.clear();
        txtLastName.clear();
        txtUsername.clear();
        txtNewPassword.clear();
        txtConfirmPassword.clear();
    }
}
