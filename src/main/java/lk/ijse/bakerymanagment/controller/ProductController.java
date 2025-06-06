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
import lk.ijse.bakerymanagment.dto.OrderdetailsDto;
import lk.ijse.bakerymanagment.dto.ProductDto;
import lk.ijse.bakerymanagment.dto.tm.OrderDetailsTM;
import lk.ijse.bakerymanagment.dto.tm.PaymentTM;
import lk.ijse.bakerymanagment.dto.tm.ProductTM;
import lk.ijse.bakerymanagment.model.ProductModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class ProductController implements Initializable {

    public AnchorPane ancProduct;
    public Text lblProductId;
    public TextField txtName;
    public TextField txtStockLevel;
    public TextField txtPrice;
    public TextField txtCategory;

    public TableView<ProductTM> tblProduct;
    public TableColumn<ProductTM , String> colProductId;
    public TableColumn<ProductTM , String> colName;
    public TableColumn<ProductTM , String> colStockLevel;
    public TableColumn<ProductTM , String> colPrice;
    public TableColumn<ProductTM , String> colCategory;

    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnReset;
    public Button txtBack;

    private final ProductModel productModel = new ProductModel();

    public TextField txtSearch;
    public TableColumn colQty;
    public TextField txtQty;
    public Button btnProduct;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colProductId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colStockLevel.setCellValueFactory(new PropertyValueFactory<>("stocklevel"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));

        try {
            resetPage();

        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong", ButtonType.OK).show();
        }

    }
    public void loadTable() throws SQLException, ClassNotFoundException {
        tblProduct.setItems(FXCollections.observableArrayList(
                productModel.getAllproduct().stream()
                        .map(productDto -> new ProductTM(
                                productDto.getProductId(),
                                productDto.getName(),
                                productDto.getStocklevel(),
                                productDto.getPrice(),
                                productDto.getCategory(),
                                productDto.getQty()
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

            txtName.setText(null);
            txtStockLevel.setText(null);
            txtPrice.setText(null);
            txtCategory.setText(null);
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong", ButtonType.OK).show();
        }
    }

    private void loadNextId() throws SQLException, ClassNotFoundException {
        String nextId = productModel.getNextproductId();
        lblProductId.setText(nextId);
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String productId = lblProductId.getText();
        String name = txtName.getText();
        String stockLevel = txtStockLevel.getText();
        String price = txtPrice.getText();
        String category = txtCategory.getText();
        String qty = txtQty.getText();

        int preseStock = Integer.parseInt(stockLevel);
        int presePrice = Integer.parseInt(price);
        int preseQty = Integer.parseInt(qty);

        ProductDto productDto = new ProductDto(
                productId,
                name,
                presePrice,
                preseStock,
                category,
                preseQty
        );

        try {
            boolean isSaved = productModel.saveProduct(productDto);
            if (isSaved) {
                resetPage();
                new Alert(Alert.AlertType.INFORMATION, "Product Saved", ButtonType.OK).show();
            }else {
                new Alert(Alert.AlertType.ERROR, "Something went wrong", ButtonType.OK).show();
            }
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong", ButtonType.OK).show();
        }

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String productId = lblProductId.getText();
        String name = txtName.getText();
        String stockLevel = txtStockLevel.getText();
        String price = txtPrice.getText();
        String category = txtCategory.getText();
        String qty = txtQty.getText();

        int preseStock = Integer.parseInt(stockLevel);
        int presePrice = Integer.parseInt(price);
        int preseQty = Integer.parseInt(qty);

        ProductDto productDto = new ProductDto(
                productId,
                name,
                presePrice,
                preseStock,
                category,
                preseQty
        );

        try {
            boolean isUpdate = productModel.updateproduct(productDto);
            if (isUpdate) {
                resetPage();
                new Alert(Alert.AlertType.INFORMATION, "Product Saved", ButtonType.OK).show();
            }else {
                new Alert(Alert.AlertType.ERROR, "Something went wrong", ButtonType.OK).show();
            }
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong", ButtonType.OK).show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are u sure ?",
                ButtonType.YES,
                ButtonType.NO
        );
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {
            String productId = lblProductId.getText();

            try {
                boolean isDeleted = productModel.deleteproduct(productId);
                if (isDeleted) {
                    resetPage();
                    new Alert(Alert.AlertType.INFORMATION, "Product Deleted", ButtonType.OK).show();
                }else {
                    new Alert(Alert.AlertType.ERROR, "Something went wrong", ButtonType.OK).show();
                }
            }catch (Exception e){
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Something went wrong", ButtonType.OK).show();
            }
        }
    }

    public void btnResetOnAction(ActionEvent actionEvent) {
        resetPage();
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        navigateTo("/view/DashBordPage.fxml");
    }

    private void navigateTo(String path) {
        try {
            ancProduct.getChildren().clear();

            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(path));

            anchorPane.prefWidthProperty().bind(ancProduct.widthProperty());
            anchorPane.prefHeightProperty().bind(ancProduct.heightProperty());

            ancProduct.getChildren().setAll(anchorPane);
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,"Something error").show();
            e.printStackTrace();
        }
    }

    public void onClickedTable(MouseEvent mouseEvent) {
        ProductTM selectedProduct = tblProduct.getSelectionModel().getSelectedItem();

        if (selectedProduct != null) {
            lblProductId.setText(selectedProduct.getProductId());
            txtName.setText(selectedProduct.getName());
            txtStockLevel.setText(String.valueOf(selectedProduct.getStocklevel()));
            txtPrice.setText(String.valueOf(selectedProduct.getPrice()));
            txtCategory.setText(selectedProduct.getCategory());

            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);

        }
    }


    /*public void btnProductOnAction(ActionEvent actionEvent) {
        System.out.println("Set Product clicked");

        if (tblProduct.getItems().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "There are no Product to set!").show();
            return;
        }

        try {
            System.out.println("Confirmed to Product");
            // Convert TableView items to a list of DTOs
            ProductDto productDto = new ProductDto();
            for (ProductTM productTM : tblProduct.getItems()) {
                productDto = new ProductDto(
                        productTM.getProductId(),
                        productTM.getName(),
                        productDto.getStocklevel(),
                        productDto.getPrice(),
                        productDto.getCategory(),
                        productDto.getQty()
                );
            }

            // Save order details via model
            boolean isPlaced = productModel.addOrderDetails(productDto);
            System.out.println("Order placement returned: " + isPlaced);

            if (isPlaced) {
                new Alert(Alert.AlertType.INFORMATION, "Order placed successfully!").show();
                resetPage(); // clear all fields and reload table
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to place order! Please try again.").show();
            }

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error occurred while placing order!").show();
        }
    }
    }*/
}
