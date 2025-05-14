package lk.ijse.bakerymanagment.model;

import lk.ijse.bakerymanagment.dto.CustomerDto;
import lk.ijse.bakerymanagment.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerModel {
    public boolean saveCustomer(CustomerDto customerDto) throws ClassNotFoundException , SQLException {
        return CrudUtil.execute("INSERT INTO customer VALUES (?,?,?,?,?,?,?)",
                customerDto.getCustomerId(),
                customerDto.getFirstName(),
                customerDto.getLastName(),
                customerDto.getAddress(),
                customerDto.getEmail(),
                customerDto.getContact(),
                customerDto.getUserID()

        );
    }
    public boolean updateArtist(CustomerDto customerDto) throws ClassNotFoundException , SQLException {
        return CrudUtil.execute("UPDATE customer SET first_name=?, last_name=? , address=?, email=? , contact=?, user_id=? , WHERE customer_id=?",
                customerDto.getFirstName(),
                customerDto.getLastName(),
                customerDto.getAddress(),
                customerDto.getEmail(),
                customerDto.getContact(),
                customerDto.getUserID(),
                customerDto.getCustomerId()
        );
    }
    public boolean deleteCustomer(String customerId) throws ClassNotFoundException , SQLException {
        return CrudUtil.execute("DELETE FROM customer WHERE customer_d = ?",
                customerId);
    }
    public CustomerDto searchCustomer(String customerId) throws ClassNotFoundException , SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM customer WHERE customer_id = ? ",
                customerId);
        if (resultSet.next()) {
            CustomerDto dto = new CustomerDto(
                    resultSet.getString("customerId"),
                    resultSet.getString("firstName"),
                    resultSet.getString("lastName"),
                    resultSet.getString("address"),
                    resultSet.getString("email")
            );
            return dto;
        }
        return null;
    }
    public ArrayList<CustomerDto> getAllCustomers() throws ClassNotFoundException , SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM customer");
        ArrayList<CustomerDto> customerDtosArrayList = new ArrayList<>();
        while (resultSet.next()) {
            CustomerDto dto = new CustomerDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );
            customerDtosArrayList.add(dto);
        }
        return customerDtosArrayList;
    }
    public String getNextcustomerId() throws ClassNotFoundException , SQLException{
        ResultSet resultSet = CrudUtil.execute("SELECT customer_id FROM customer ORDER BY customer_id DESC LIMIT 1");
        char tableCharacter = 'C';

        if(resultSet.next()){
            String lastId = resultSet.getString(1);
            String lastIdNumberString = lastId.substring(1);
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNumber = lastIdNumber + 1;
            String nextIdString = String.format(tableCharacter + "%03d" , nextIdNumber);
            return nextIdString;
        }
        return tableCharacter +"001";
}

}
