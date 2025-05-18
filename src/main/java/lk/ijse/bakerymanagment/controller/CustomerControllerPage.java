package lk.ijse.bakerymanagment.controller;


import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bakerymanagment.dto.CustomerDto;
import lk.ijse.bakerymanagment.dto.tm.CustomerTM;
import lk.ijse.bakerymanagment.model.CustomerModel;
import lombok.SneakyThrows;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerControllerPage implements Initializable {
    public Label lblCustomerId;
    public TextField txtName;
    public TextField txtContact;
    public TextField txtAddress;
    public TextField txtEmail;

    public TableView<CustomerTM> tblCustomer;
    public TableColumn<CustomerTM, String> tblCustomerId;
    public TableColumn<CustomerTM, String> tblName;
    public TableColumn<CustomerTM, String> tblContact;
    public TableColumn<CustomerTM, String> tblAddress;
    public TableColumn<CustomerTM, String> tblEmail;

    private final CustomerModel customerModel = new CustomerModel();
    public Button btnSave;
    public Button btnDelete;
    public Button btnUpdate;
    public Button btnReset;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tblCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        tblName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        tblAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        tblEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        try {
            loadTableData();
            loadNextId();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Somthing Went Wrong", ButtonType.CLOSE).show();
        }
    }

    private void loadNextId() throws Exception {
        String nextId = customerModel.getNextcustomerId();
        lblCustomerId.setText(nextId);
    }

    public void onClickTable(MouseEvent mouseEvent) {
        CustomerTM selectedItem = tblCustomer.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            lblCustomerId.setText(selectedItem.getCustomerId());
            txtName.setText(selectedItem.getFirstName() + " " + selectedItem.getLastName());
            txtContact.setText(selectedItem.getContact());
            txtAddress.setText(selectedItem.getAddress());
            txtEmail.setText(selectedItem.getEmail());
        }

    }

    private void loadTableData() throws Exception {
        tblCustomer.setItems(FXCollections.observableArrayList(
                customerModel.getAllCustomers().stream().map(
                        customerDto -> new CustomerTM(
                                customerDto.getCustomerId(),
                                customerDto.getFirstName(),
                                customerDto.getLastName(),
                                customerDto.getAddress(),
                                customerDto.getEmail(),
                                customerDto.getContact(),
                                customerDto.getUserID()

                        )).toList()
        ));
    }

    public void resetPage() {
        btnSave.setDisable(false);
        btnDelete.setDisable(false);
        btnUpdate.setDisable(false);
        btnReset.setDisable(false);

        try {
            loadTableData();
            loadNextId();

            txtName.setText("");
            txtContact.setText("");
            txtAddress.setText("");
            txtEmail.setText("");
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Somthing Went Wrong", ButtonType.CLOSE).show();
        }
    }


    public void btnSearchOnAction(ActionEvent actionEvent) {
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
    }


    public void txtNameOnAction(ActionEvent actionEvent) {
    }

    public void txtContactOnAction(ActionEvent actionEvent) {
    }

    public void txtAdressOnAction(ActionEvent actionEvent) {
    }

    public void txtEmailOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String customerId = lblCustomerId.getText();
        String name = txtName.getText();
        String contact = txtContact.getText();
        String address = txtAddress.getText();
        String email = txtEmail.getText();

        CustomerDto customerDto = new CustomerDto(customerId, name, contact, address, email);

        try {
            boolean isSaved = customerModel.saveCustomer(customerDto);

            if (isSaved) {
                resetPage();
                new Alert(Alert.AlertType.INFORMATION, "Saved successfully", ButtonType.CLOSE).show();

            } else {
                new Alert(Alert.AlertType.ERROR, "Save failed", ButtonType.CLOSE).show();

            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Save failed", ButtonType.CLOSE).show();


        }
    }

    @SneakyThrows
    public void btnSaveOnAction(ActionEvent actionEvent) {
        String customerId = lblCustomerId.getText();
        String name = txtName.getText();
        String contact = txtContact.getText();
        String address = txtAddress.getText();
        String email = txtEmail.getText();

        CustomerDto customerDto = new CustomerDto(customerId, name, contact, address, email);

        try {
            boolean isSaved = customerModel.saveCustomer(customerDto);

            if (isSaved) {
                resetPage();
                new Alert(Alert.AlertType.INFORMATION, "Saved successfully", ButtonType.CLOSE).show();

            } else {
                new Alert(Alert.AlertType.ERROR, "Save failed", ButtonType.CLOSE).show();

            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Save failed", ButtonType.CLOSE).show();


        }
    }


    public void btnResetOnAction(ActionEvent actionEvent) {
        String customerId = lblCustomerId.getText();
        String name = txtName.getText();
        String contact = txtContact.getText();
        String address = txtAddress.getText();
        String email = txtEmail.getText();

        CustomerDto customerDto = new CustomerDto(customerId, name, contact, address, email);

        try {
            boolean isReset = customerModel.saveCustomer(customerDto);

            if (isReset) {
                resetPage();
                new Alert(Alert.AlertType.INFORMATION, "Saved successfully", ButtonType.CLOSE).show();

            } else {
                new Alert(Alert.AlertType.ERROR, "Save failed", ButtonType.CLOSE).show();

            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Save failed", ButtonType.CLOSE).show();

        }
    }


    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String customerId = lblCustomerId.getText();
        String name = txtName.getText();
        String contact = txtContact.getText();
        String address = txtAddress.getText();
        String email = txtEmail.getText();

        CustomerDto customerDto = new CustomerDto(customerId, name, contact, address, email);

        try {
            boolean isReset = customerModel.saveCustomer(customerDto);

            if (isReset) {
                resetPage();
                new Alert(Alert.AlertType.INFORMATION, "Saved successfully", ButtonType.CLOSE).show();

            } else {
                new Alert(Alert.AlertType.ERROR, "Save failed", ButtonType.CLOSE).show();

            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Save failed", ButtonType.CLOSE).show();

        }
    }
}

