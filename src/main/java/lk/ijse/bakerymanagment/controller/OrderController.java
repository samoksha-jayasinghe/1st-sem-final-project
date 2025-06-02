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
import lk.ijse.bakerymanagment.dto.OrderDto;
import lk.ijse.bakerymanagment.dto.tm.OrderDetailsTM;
import lk.ijse.bakerymanagment.dto.tm.OrderTM;
import lk.ijse.bakerymanagment.model.OrderModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class OrderController implements Initializable {

    public AnchorPane ancOrder;
    public Text lblOrderId;
    public TextField txtCustomerId;
    public TextField txtOrderDate;
    public TextField txtStatus;
    public TextField txtTotalAmmount;

    public TableView<OrderTM> tblOrder;
    public TableColumn<OrderTM , String> colOrderId;
    public TableColumn<OrderTM , String> colCustomerId;
    public TableColumn<OrderTM , String> colOrderDate;
    public TableColumn<OrderTM , String> colStatus;
    public TableColumn<OrderTM , String> colTotalAmmount;

    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnReset;
    public Button txtBack;

    private final OrderModel orderModel = new OrderModel();

    public TextField txtSearch;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("OrderId"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("CustomerId"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("Orderdate"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
        colTotalAmmount.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));

        try {
            resetPage();
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Something went wrong!",ButtonType.OK).show();
        }

    }
    public void loadTable() throws SQLException, ClassNotFoundException {
        tblOrder.setItems(FXCollections.observableArrayList(
                orderModel.getAllOrder().stream()
                        .map(orderDto -> new OrderTM(
                                orderDto.getOrderId(),
                                orderDto.getCustomerId(),
                                orderDto.getOrderdate(),
                                orderDto.getStatus(),
                                orderDto.getTotalAmount()

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

            txtCustomerId.setText(null);
            txtOrderDate.setText(null);
            txtStatus.setText(null);
            txtTotalAmmount.setText(null);
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Something went wrong!",ButtonType.OK).show();
        }
    }

    private void loadNextId() throws SQLException, ClassNotFoundException {
        String nextId = orderModel.getNextOrderId();
        lblOrderId.setText(nextId);
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String orderId = lblOrderId.getText();
        String customerId = txtCustomerId.getText();
        String orderDate = txtOrderDate.getText();
        String status = txtStatus.getText();
        String totalAmmount = txtTotalAmmount.getText();

        int preseTotal = Integer.parseInt(totalAmmount);

        OrderDto orderDto = new OrderDto (
                orderId,
                customerId,
                orderDate,
                status,
                preseTotal
        );

        try {
            boolean isSaved = orderModel.saveOrder(orderDto);
            if (isSaved) {
                resetPage();
                new Alert(Alert.AlertType.INFORMATION,"Order Saved!",ButtonType.OK).show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Something went wrong!",ButtonType.OK).show();
            }
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Something went wrong!",ButtonType.OK).show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String orderId = lblOrderId.getText();
        String customerId = txtCustomerId.getText();
        String orderDate = txtOrderDate.getText();
        String status = txtStatus.getText();
        String totalAmmount = txtTotalAmmount.getText();

        int preseTotal = Integer.parseInt(totalAmmount);

        OrderDto orderDto = new OrderDto (
                orderId,
                customerId,
                orderDate,
                status,
                preseTotal
        );

        try {
            boolean isUpdated = orderModel.updateOrder(orderDto);
            if (isUpdated) {
                resetPage();
                new Alert(Alert.AlertType.INFORMATION,"Order Updated!",ButtonType.OK).show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Something went wrong!",ButtonType.OK).show();
            }
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Something went wrong!",ButtonType.OK).show();
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
            String orderId = lblOrderId.getText();

            try {
                boolean isDeleted = orderModel.deleteOrder(orderId);
                if (isDeleted) {
                    resetPage();
                    new Alert(Alert.AlertType.INFORMATION,"Order Deleted!",ButtonType.OK).show();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Something went wrong!",ButtonType.OK).show();
                }
            }catch (Exception e){
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR,"Something went wrong!",ButtonType.OK).show();
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
            ancOrder.getChildren().clear();

            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(path));

            anchorPane.prefWidthProperty().bind(ancOrder.widthProperty());
            anchorPane.prefHeightProperty().bind(ancOrder.heightProperty());

            ancOrder.getChildren().setAll(anchorPane);
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,"Something went wrong",ButtonType.OK).show();
            e.printStackTrace();
        }
    }

    public void onClickedTable(MouseEvent mouseEvent) {
        OrderTM selectedorder = tblOrder.getSelectionModel().getSelectedItem();

        if (selectedorder != null) {
            lblOrderId.setText(selectedorder.getOrderId());
            txtCustomerId.setText(selectedorder.getCustomerId());
            txtOrderDate.setText(selectedorder.getOrderdate());
            txtStatus.setText(selectedorder.getStatus());
            txtTotalAmmount.setText(String.valueOf(selectedorder.getTotalAmount()));

            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }


}
