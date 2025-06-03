package lk.ijse.bakerymanagment.model;

import lk.ijse.bakerymanagment.dto.SupplierDto;
import lk.ijse.bakerymanagment.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierModel {
    /*public static boolean reduceQty(int qty, String SupplierId) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE product SET qty = qty - ? WHERE product_id = ?", qty, productId);
    }*/

    public boolean saveSupplier(SupplierDto supplierDto) throws ClassNotFoundException , SQLException {
        return CrudUtil.execute("INSERT INTO supplier VALUES (?,?,?,?)",
                supplierDto.getSupplierId(),
                supplierDto.getName(),
                supplierDto.getContact(),
                supplierDto.getAddress()


        );
    }
    public boolean updateSupplier(SupplierDto supplierDto) throws ClassNotFoundException , SQLException {
        return CrudUtil.execute("UPDATE supplier SET name=?, contact=? , address=? WHERE supplier_id=?",
                supplierDto.getName(),
                supplierDto.getContact(),
                supplierDto.getAddress(),
                supplierDto.getSupplierId()
        );
    }
    public boolean deleteSupplier(String SupplierId) throws ClassNotFoundException , SQLException {
        return CrudUtil.execute("DELETE FROM supplier WHERE supplier_id = ?",
                SupplierId);
    }
    public SupplierDto searchSupplier(String SupplierId) throws ClassNotFoundException , SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM supplier WHERE supplier_id = ? ",
                SupplierId);
        if (resultSet.next()) {
            SupplierDto dto = new SupplierDto(
                    resultSet.getString("SupplierId"),
                    resultSet.getString("Name"),
                    resultSet.getString("Contact"),
                    resultSet.getString("Address")
            );
            return dto;
        }
        return null;
    }
    public ArrayList<SupplierDto> getAllSupplier() throws ClassNotFoundException , SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM supplier");
        ArrayList<SupplierDto> supplierDtoArrayList = new ArrayList<>();
        while (resultSet.next()) {
            SupplierDto dto = new SupplierDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
            supplierDtoArrayList.add(dto);
        }
        return supplierDtoArrayList;
    }
    public String getNextSupplierId() throws ClassNotFoundException , SQLException{
        ResultSet resultSet = CrudUtil.execute("SELECT supplier_id FROM supplier ORDER BY supplier_id DESC LIMIT 1");
        char tableCharacter = 'S';

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
