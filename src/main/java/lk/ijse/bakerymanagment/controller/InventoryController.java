package lk.ijse.bakerymanagment.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import lk.ijse.bakerymanagment.dto.InventoryDto;
import lk.ijse.bakerymanagment.dto.tm.FeedbackTM;
import lk.ijse.bakerymanagment.dto.tm.IngredientTM;
import lk.ijse.bakerymanagment.dto.tm.InventoryTM;
import lk.ijse.bakerymanagment.model.InventoryModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class InventoryController implements Initializable {

    public AnchorPane ancInventory;

    public Text lblInventoryId;
    public TextField txtProductId;
    public TextField txtSupplierId;
    public TextField txtRawMaterial;
    public TextField txtQuantity;

    public TableView<InventoryTM> tblInventory;
    public TableColumn<InventoryTM ,String> colInventoryId;
    public TableColumn<InventoryTM ,String> colProductId;
    public TableColumn<InventoryTM ,String> colSupplierId;
    public TableColumn<InventoryTM ,String> colRawMaterial;
    public TableColumn<InventoryTM ,String> colQuantity;


    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnReset;

    private final InventoryModel inventoryModel = new InventoryModel();

    public Button txtBack;
    public TextField txtSearch;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colInventoryId.setCellValueFactory(new PropertyValueFactory<>("inventoryId"));
        colProductId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colRawMaterial.setCellValueFactory(new PropertyValueFactory<>("rawMaterial"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("qty"));

        try {
            resetPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong", ButtonType.OK).show();
        }
    }

    public void loadTable() throws SQLException, ClassNotFoundException {
        tblInventory.setItems(FXCollections.observableArrayList(
                inventoryModel.getAllInventory().stream()
                        .map(inventoryDto -> new InventoryTM(
                                inventoryDto.getInventoryId(),
                                inventoryDto.getProductId(),
                                inventoryDto.getSupplierId(),
                                inventoryDto.getRawMaterial(),
                                inventoryDto.getQty()
                        )).toList()
        ));
    }

    private void resetPage() {
        try {
            loadTable();
            loadNextId();

            btnSave.setDisable(false);
            btnUpdate.setDisable(true);
            btnDelete.setDisable(true);

            txtProductId.setText(null);
            txtSupplierId.setText(null);
            txtRawMaterial.setText(null);
            txtQuantity.setText(null);

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong", ButtonType.OK).show();
        }
    }

    private void loadNextId() throws SQLException, ClassNotFoundException {
        String nextId = inventoryModel.getNextInventoryId();
        lblInventoryId.setText(nextId);
    }


    public void btnSaveOnAction(ActionEvent actionEvent) {
        String inventoryId = lblInventoryId.getText();
        String productId = txtProductId.getText();
        String supplierId = txtSupplierId.getText();
        String rawMaterial = txtRawMaterial.getText();
        String quantity = txtQuantity.getText();

        int preseQty = Integer.parseInt(quantity);

        InventoryDto inventoryDto = new InventoryDto(
                inventoryId,
                productId,
                supplierId,
                rawMaterial,
                preseQty
        );

        try {
            boolean isSaved = inventoryModel.saveInventory(inventoryDto);
            if (isSaved) {
                resetPage();
                new Alert(Alert.AlertType.INFORMATION, "Inventory Saved").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
        }
    }


    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String inventoryId = lblInventoryId.getText();
        String productId = txtProductId.getText();
        String supplierId = txtSupplierId.getText();
        String rawMaterial = txtRawMaterial.getText();
        String quantity = txtQuantity.getText();

        int preseQty = Integer.parseInt(quantity);

        InventoryDto inventoryDto = new InventoryDto(
                inventoryId,
                productId,
                supplierId,
                rawMaterial,
                preseQty
        );

        try {
            boolean isUpdate = inventoryModel.updateInventory(inventoryDto);
            if (isUpdate) {
                resetPage();
                new Alert(Alert.AlertType.INFORMATION, "Inventory Saved").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are u Sure ?",
                ButtonType.YES,
                ButtonType.NO
        );
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {
            String inventoryId = lblInventoryId.getText();

            try {
                boolean isDeleted = inventoryModel.deleteInventory(inventoryId);
                if (isDeleted) {
                    resetPage();
                    new Alert(Alert.AlertType.INFORMATION, "Deleted successfully", ButtonType.OK).show();
                }else {
                    new Alert(Alert.AlertType.ERROR, "Deleting failed", ButtonType.OK).show();
                }
            }catch (Exception e){
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "something error", ButtonType.OK).show();
            }
        }
    }

    public void btnResetOnAction(ActionEvent actionEvent) {
        resetPage();
    }

    public void onClickedTable(MouseEvent mouseEvent) {
        InventoryTM selectedInventory = tblInventory.getSelectionModel().getSelectedItem();

        if (selectedInventory != null) {
            lblInventoryId.setText(selectedInventory.getInventoryId());
            txtProductId.setText(selectedInventory.getProductId());
            txtSupplierId.setText(selectedInventory.getSupplierId());
            txtRawMaterial.setText(selectedInventory.getRawMaterial());
            txtQuantity.setText(String.valueOf(selectedInventory.getQty()));

            btnSave.setDisable(true);
            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);

        }
    }


    public void btnBackOnAction(ActionEvent actionEvent) {
        navigateTo("/view/DashBordPage.fxml");
    }

    private void navigateTo(String path) {
        try{
            ancInventory.getChildren().clear();

            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(path));

            anchorPane.prefWidthProperty().bind(ancInventory.widthProperty());
            anchorPane.prefHeightProperty().bind(ancInventory.heightProperty());

            ancInventory.getChildren().setAll(anchorPane);

        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR, "something error").show();
            e.printStackTrace();

        }
    }
    }


