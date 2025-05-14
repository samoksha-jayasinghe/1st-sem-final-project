package lk.ijse.bakerymanagment.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.bakerymanagment.dto.CustomerDto;
import lk.ijse.bakerymanagment.dto.tm.CustomerTM;
import lk.ijse.bakerymanagment.model.CustomerModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class CustomerContoller implements Initializable {
    public Label lblCustomerId;
    public TextField txtName;
    public TextField txtNic;
    public TextField txtEmail;
    public TextField txtPhone;

    // TM - table model Ex: CustomerTM
    public TableView<CustomerTM> tblCustomer;
    public TableColumn<CustomerTM, String> colId;
    public TableColumn<CustomerTM, String> colName;
    public TableColumn<CustomerTM, String> colNic;
    public TableColumn<CustomerTM, String> colEmail;
    public TableColumn<CustomerTM, String> colPhone;

    // Create a CustomerModel object to access database-related methods (CustomerModel class methods)
    private final CustomerModel customerModel = new CustomerModel();
    public Button btnReset;
    public Button btnDelete;
    public Button btnUpdate;
    public Button btnSave;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // table column and tm class properties link
        colId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));

        try {
            loadTableData();
            loadNextId();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong.").show();
        }
    }

    private void loadTableData() throws SQLException, ClassNotFoundException {

        ArrayList<CustomerDto> customerDTOArrayList = null;
        try {
            customerDTOArrayList = customerModel.getAllCustomers();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        ObservableList<CustomerTM> customerTMS = FXCollections.observableArrayList();

        for (CustomerDto customerDTO : customerDTOArrayList) {
            CustomerTM customerTM = new CustomerTM(
                    customerDTO.getCustomerId(),
                    customerDTO.getFirstName(),
                    customerDTO.getLastName(),
                    customerDTO.getAddress(),
                    customerDTO.getEmail(),
                    customerDTO.getContact(),
                    customerDTO.getUserID()
            );
            customerTMS.add(customerTM);
        }
        tblCustomer.setItems(customerTMS);

//        2. Full short code (Single line)
        tblCustomer.setItems(FXCollections.observableArrayList(
                customerModel.getAllCustomers().stream()
                        .map(customerDTO -> new CustomerTM(
                                customerDTO.getCustomerId(),
                                customerDTO.getFirstName(),
                                customerDTO.getLastName(),
                                customerDTO.getAddress(),
                                customerDTO.getEmail(),
                                customerDTO.getContact(),
                                customerDTO.getUserID()
                        )).toList()
        ));
    }

    private void resetPage() {
        btnSave.setDisable(false);
        btnDelete.setDisable(false);
        btnUpdate.setDisable(false);
        btnReset.setDisable(false);
        try {
            loadTableData();
            loadNextId();

            txtName.setText("");
            txtNic.setText("");
            txtEmail.setText("");
            txtPhone.setText("");
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong.").show();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String customerId = lblCustomerId.getText();
        String name = txtName.getText();
        String nic = txtNic.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();

        // Data transfer object -dto

        // Create dto object wrap data to single unit
        CustomerDto customerDTO = new CustomerDto(
                customerId,
                name,
                nic,
                email,
                phone
        );

        // Call CustomerModel inside saveCustomer
        // method and parse

        // controller to model parse data using dto
        try {
            boolean isSaved = customerModel.saveCustomer(customerDTO);

            if (isSaved) {
                resetPage();
                new Alert(Alert.AlertType.INFORMATION, "Customer saved successfully.").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to save customer.").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to save customer.").show();
        }

    }


    private void loadNextId() throws SQLException, ClassNotFoundException {
        String nextId = customerModel.getNextcustomerId();
        lblCustomerId.setText(nextId);
    }

    public void onClickTable(MouseEvent mouseEvent) {
        CustomerTM selectedItem = tblCustomer.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            lblCustomerId.setText(selectedItem.getCustomerId());
            txtName.setText(selectedItem.getFirstName());
            txtName.setText(selectedItem.getLastName());
            txtNic.setText(selectedItem.getAddress());
            txtEmail.setText(selectedItem.getEmail());
            txtPhone.setText(selectedItem.getContact());

            // save button disable
            // update, delete button enable
        }
    }

    public void btnResetOnAction(ActionEvent actionEvent) {

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }
}


