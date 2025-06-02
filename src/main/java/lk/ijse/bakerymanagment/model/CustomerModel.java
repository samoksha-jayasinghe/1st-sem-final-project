package lk.ijse.bakerymanagment.model;

import lk.ijse.bakerymanagment.dto.CustomerDto;
import lk.ijse.bakerymanagment.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerModel {
    public boolean saveCustomer(CustomerDto customerDto) throws ClassNotFoundException , SQLException {
        return CrudUtil.execute("INSERT INTO customer VALUES (? , ? , ? , ? , ? , ?)",
                customerDto.getCustomerId(),
                customerDto.getFirstName(),
                customerDto.getAddress(),
                customerDto.getEmail(),
                customerDto.getContact(),
                customerDto.getUserID()

        );
    }
    public boolean updateCustomer(CustomerDto customerDto) throws ClassNotFoundException , SQLException {
        return CrudUtil.execute("UPDATE customer SET first_name = ? , address = ? , email = ? , contact = ? , user_id = ? WHERE customer_id = ?",
                customerDto.getFirstName(),
                customerDto.getAddress(),
                customerDto.getEmail(),
                customerDto.getContact(),
                customerDto.getUserID(),
                customerDto.getCustomerId()
        );
    }
    public boolean deleteCustomer(String customerId) throws ClassNotFoundException , SQLException {
        return CrudUtil.execute("DELETE FROM customer WHERE customer_id = ?",
                customerId);
    }
    /*public CustomerDto searchCustomer(String customerId) throws ClassNotFoundException , SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM customer WHERE customer_id = ? ",
                customerId);
        if (resultSet.next()) {
            CustomerDto dto = new CustomerDto(
                    resultSet.getString("customerId"),
                    resultSet.getString("firstName"),
                    resultSet.getString("address"),
                    resultSet.getString("email"),
                    resultSet.getString("contact"),
                    resultSet.getString("userId")
            );
            return dto;
        }
        return null;
    }*/
    public ArrayList<CustomerDto> getAllCustomer() throws ClassNotFoundException , SQLException {
       ResultSet resultSet = CrudUtil.execute("SELECT * FROM customer");
       ArrayList<CustomerDto> customerDtoArrayList = new ArrayList<>();
       while (resultSet.next()) {
           CustomerDto customerDto = new CustomerDto(
                   resultSet.getString("customer_id"),
                   resultSet.getString("first_name"),
                   resultSet.getString("address"),
                   resultSet.getString("email"),
                   resultSet.getString("contact"),
                   resultSet.getString("user_id")

           );
           customerDtoArrayList.add(customerDto);
       }
       return customerDtoArrayList;
    }
   /* public String getNextcustomerId() throws ClassNotFoundException , SQLException{
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
*/


    public String getNextCustomerId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT customer_id FROM customer ORDER BY customer_id DESC LIMIT 1");

        char tableCharacter = 'C';  // Assuming customer IDs are like "C001"

        if (resultSet.next()) {
            String lastId = resultSet.getString(1); // e.g., "C005"
            String lastNumberString = lastId.substring(1); // "005"
            int lastIdNumber = Integer.parseInt(lastNumberString); // 5
            int nextIdNumber = lastIdNumber + 1;

            // Format: C001, C002, ..., C010
            return String.format("%s%03d", tableCharacter, nextIdNumber);
        }

        // Default for first entry
        return tableCharacter + "001";
    }


   /* public CustomerDto getCustomerId(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute(
                "SELECT * FROM customer WHERE customer_id = ?", id
        );

        if (resultSet.next()) {
            return new CustomerDto(
                    resultSet.getString(1),  // customer_id
                    resultSet.getString(2),  // first_name
                    resultSet.getString(3),  // last_name
                    resultSet.getString(4),  // address
                    resultSet.getString(5),  // email
                    resultSet.getString(6)  // contact
                    // user_id (if exists, use resultSet.getString(7))
            );
        }

        return null; // If no customer found with given ID
    }*/
}
