package lk.ijse.bakerymanagment.model;

import lk.ijse.bakerymanagment.dto.CustomerDto;
import lk.ijse.bakerymanagment.dto.EmployeeDto;
import lk.ijse.bakerymanagment.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeModel {
    public boolean saveEmployee(EmployeeDto employeeDto) throws ClassNotFoundException , SQLException {
        return CrudUtil.execute("INSERT INTO employee VALUES (?,?,?,?,?)",
                employeeDto.getEmployeeId(),
                employeeDto.getName(),
                employeeDto.getRole(),
                employeeDto.getSalary(),
                employeeDto.getContact()

        );
    }
    public boolean updateEmployee(EmployeeDto employeeDto) throws ClassNotFoundException , SQLException {
        return CrudUtil.execute("UPDATE employee SET name=?, role=?, salary=? , contact=?, WHERE employee_id=?",
                employeeDto.getName(),
                employeeDto.getRole(),
                employeeDto.getSalary(),
                employeeDto.getContact()
        );
    }
    public boolean deleteEmployee(String EmployeeId) throws ClassNotFoundException , SQLException {
        return CrudUtil.execute("DELETE FROM employee WHERE employee_id = ?",
                EmployeeId);
    }
    public EmployeeDto searchEmployee(String EmployeeId) throws ClassNotFoundException , SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM employee WHERE employee_id = ? ",
                EmployeeId);
        if (resultSet.next()) {
            EmployeeDto dto = new EmployeeDto(
                    resultSet.getString("EmployeeId"),
                    resultSet.getString("Name"),
                    resultSet.getString("Role"),
                    resultSet.getString("Salary"),
                    resultSet.getString("contact")
            );
            return dto;
        }
        return null;
    }
    public ArrayList<EmployeeDto> getAllEmployee() throws ClassNotFoundException , SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM employee");
        ArrayList<EmployeeDto> employeeDtosArrayList = new ArrayList<>();
        while (resultSet.next()) {
            EmployeeDto dto = new EmployeeDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );
            employeeDtosArrayList.add(dto);
        }
        return employeeDtosArrayList;
    }
    public String getNextEmployeeId() throws ClassNotFoundException , SQLException{
        ResultSet resultSet = CrudUtil.execute("SELECT employee_id FROM employee ORDER BY employee_id DESC LIMIT 1");
        char tableCharacter = 'E';

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
