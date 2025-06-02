package lk.ijse.bakerymanagment.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;

public class ButtonSetController {
    public AnchorPane ancButtonSet;
    public ComboBox cmbOverView;

    public void cmbOverViewPagesOnAction(ActionEvent actionEvent) {
        navigateTo2("/view/DashBordPage.fxml");
    }

    private void navigateTo2(String path) {
        try {


            ancButtonSet.getChildren().clear();

            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(path));

            anchorPane.prefWidthProperty().bind(ancButtonSet.widthProperty());
            anchorPane.prefHeightProperty().bind(ancButtonSet.heightProperty());

            ancButtonSet.getChildren().setAll(anchorPane);
        }catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Fail to load").show();
            e.printStackTrace();
        }
    }
}
