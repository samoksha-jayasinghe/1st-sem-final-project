package lk.ijse.bakerymanagment.model;

import lk.ijse.bakerymanagment.dto.IngredientDto;
import lk.ijse.bakerymanagment.dto.InventoryDto;
import lk.ijse.bakerymanagment.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InventoryModel {
    public boolean saveInventory(InventoryDto inventoryDto) throws ClassNotFoundException , SQLException {
        return CrudUtil.execute("INSERT INTO Inventory VALUES (?,?,?,?,?)",
                inventoryDto.getInventoryId(),
                inventoryDto.getProductId(),
                inventoryDto.getSupplierId(),
                inventoryDto.getRawMaterial(),
                inventoryDto.getQty()

        );
    }
    public boolean updateInventory(InventoryDto inventoryDto) throws ClassNotFoundException , SQLException {
        return CrudUtil.execute("UPDATE inventory SET product_id=?, supplier_id=? , raw_material=?, quantity=? WHERE inventory_id=?",
                inventoryDto.getProductId(),
                inventoryDto.getSupplierId(),
                inventoryDto.getRawMaterial(),
                inventoryDto.getQty(),
                inventoryDto.getInventoryId()
        );
    }
    public boolean deleteInventory(String InventoryId) throws ClassNotFoundException , SQLException {
        return CrudUtil.execute("DELETE FROM inventory WHERE inventory_id = ?",
                InventoryId);
    }
    public IngredientDto searchInventory(String InventoryId) throws ClassNotFoundException , SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM inventory WHERE inventory_id = ? ",
                InventoryId);
        if (resultSet.next()) {
            IngredientDto dto = new IngredientDto(
                    resultSet.getString("InventoryId"),
                    resultSet.getString("ProductId"),
                    resultSet.getString("SupplierId"),
                    resultSet.getInt("RawMaterial"),
                    resultSet.getInt("Qty")
            );
            return dto;
        }
        return null;
    }
    public ArrayList<InventoryDto> getAllInventory() throws ClassNotFoundException , SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM inventory");
        ArrayList<InventoryDto> inventoryDtoArrayList = new ArrayList<>();
        while (resultSet.next()) {
            InventoryDto dto = new InventoryDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5)
            );
            inventoryDtoArrayList.add(dto);
        }
        return inventoryDtoArrayList;
    }
    public String getNextInventoryId() throws ClassNotFoundException , SQLException{
        ResultSet resultSet = CrudUtil.execute("SELECT inventory_id FROM inventory ORDER BY inventory_id DESC LIMIT 1");
        char tableCharacter = 'T';

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
