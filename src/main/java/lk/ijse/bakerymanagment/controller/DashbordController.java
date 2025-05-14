package lk.ijse.bakerymanagment.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class DashbordController implements Initializable {
    @FXML
    public AnchorPane AncDashbord;

    public void customerOnAction(ActionEvent actionEvent) {
    }

    public void employeeOnAction(ActionEvent actionEvent) {
    }

    public void feedbackOnAction(ActionEvent actionEvent) {
    }

    public void ingredientOnAction(ActionEvent actionEvent) {
    }

    public void inventoryOnAction(ActionEvent actionEvent) {
    }

    public void invoiceOnAction(ActionEvent actionEvent) {
    }

    public void itemOnAction(ActionEvent actionEvent) {
    }

    public void orderdetailsOnAction(ActionEvent actionEvent) {
    }

    public void orderOnAction(ActionEvent actionEvent) {
    }

    public void paymentOnAction(ActionEvent actionEvent) {
    }

    public void productOnAction(ActionEvent actionEvent) {
    }

    public void supplierOnAction(ActionEvent actionEvent) {
    }

    public void userOnAction(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void btnGocustomerOnAction(ActionEvent actionEvent) {
        navigateTo("/view/CustomerPage.fxml");
    }

    public void btnGoitemOnAction(ActionEvent actionEvent) {
        navigateTo("/view/ItemPage.fxml");
    }

    public void btnGoorderOnAction(ActionEvent actionEvent) {
        navigateTo("/view/OrderPage.fxml");
    }

    public void btnGoemployeeOnAction(ActionEvent actionEvent) {
        navigateTo("/view/EmplyeePage.fxml");
    }

    public void btnGofeedbackOnAction(ActionEvent actionEvent) {
        navigateTo("/view/FeedbackPage.fxml");
    }

    public void btnGoingredientOnAction(ActionEvent actionEvent) {
        navigateTo("/view/IngredientPage.fxml");
    }

    public void btnGoinventoryOnAction(ActionEvent actionEvent) {
        navigateTo("/view/InventoryPage.fxml");
    }

    public void btnGoinvoiceOnAction(ActionEvent actionEvent) {
        navigateTo("/view/InvoicePage.fxml");
    }

    public void btnGoorderdetailsOnAction(ActionEvent actionEvent) { navigateTo("/view/OrderDetailsPage.fxml");
    }

    public void btnGopaymentOnAction(ActionEvent actionEvent) {
        navigateTo("/view/PaymentPage.fxml");
    }

    public void btnGoproductOnAction(ActionEvent actionEvent) {
        navigateTo("/view/ProductPage.fxml");
    }

    public void btnGosupplierOnAction(ActionEvent actionEvent) {
        navigateTo("/view/SupplierPage.fxml");
    }

    public void btnGouserOnAction(ActionEvent actionEvent) {
        navigateTo("/view/UserPage.fxml");
    }

    private void navigateTo(String path) {
        try {
            AncDashbord.getChildren().clear();

            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(path));

            anchorPane.prefWidthProperty().bind(AncDashbord.widthProperty());
            anchorPane.prefHeightProperty().bind(AncDashbord.heightProperty());

            AncDashbord.getChildren().add(anchorPane);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Page not found..!").show();
            e.printStackTrace();
        }
    }
}
