package lk.ijse.bakerymanagment.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.bakerymanagment.dto.tm.EmployeeTM;
import lk.ijse.bakerymanagment.model.EmployeeModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {
    public Label lblEmployeeId;
    public TextField txtName;
    public TextField txtRole;
    public TextField txtSalary;
    public TextField txtContact;

    public TableView<EmployeeTM> tblEmployee;
    public TableColumn<EmployeeTM,String > colEmployeeId;
    public TableColumn<EmployeeTM,String> colName;
    public TableColumn<EmployeeTM,String> colRole;
    public TableColumn<EmployeeTM,String> colSalary;
    public TableColumn<EmployeeTM,String> colContact;


    private final EmployeeModel employeeModel = new EmployeeModel();
    public Button btnSave;
    public Button btnDelete;
    public Button btnReset;
    public Button btnUpdate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));

        try {
            loadTableData();
            loadNextId();
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error", ButtonType.OK);
        }

    }

    private void loadNextId() {
    }

    private void loadTableData() throws SQLException, ClassNotFoundException {
        tblEmployee.setItems(FXCollections.observableArrayList(
                employeeModel.getAllEmployee().stream().map(
                        employeeDto -> new EmployeeTM(
                                employeeDto.getEmployeeId(),
                                employeeDto.getName(),
                                employeeDto.getRole(),
                                employeeDto.getSalary(),
                                employeeDto.getContact()
                        )).toList()

        ));
    }
    public void resetPage (){
        btnSave.setDisable(false);
        btnDelete.setDisable(false);
        btnReset.setDisable(false);
        btnUpdate.setDisable(false);
        try {
            loadTableData();
            loadNextId();

            txtName.setText("");
            txtRole.setText("");
            txtSalary.setText("");
            txtContact.setText("");
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error", ButtonType.OK);
        }
    }


    public void btnSearchOnAction(ActionEvent actionEvent) {
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
    }

    public void lblEmployeeIdOnAction(MouseEvent mouseEvent) {
    }

    public void txtNameOnAction(ActionEvent actionEvent) {
    }

    public void txtRoleOnAction(ActionEvent actionEvent) {
    }

    public void txtSalaryOnAction(ActionEvent actionEvent) {
    }

    public void txtContactOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
    }

    public void btnResetOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

    public void tblEmployeeOnAction(SortEvent<TableView> tableViewSortEvent) {
    }


}
