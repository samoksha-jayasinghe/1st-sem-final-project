package lk.ijse.bakerymanagment.model;

//import lk.ijse.bakerymanagment.dto.IngredientDto;
//import lk.ijse.bakerymanagment.dto.InventoryDto;
import lk.ijse.bakerymanagment.dto.ItemDto;
import lk.ijse.bakerymanagment.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemModel {
    public boolean saveItem(ItemDto itemDto) throws ClassNotFoundException , SQLException {
        return CrudUtil.execute("INSERT INTO item VALUES (?,?,?,?,?,?)",
                itemDto.getItemId(),
                itemDto.getName(),
                itemDto.getCategory(),
                itemDto.getPrice(),
                itemDto.getQuantity(),
                itemDto.getExpirDate()

        );
    }
    public boolean updateItem(ItemDto itemDto) throws ClassNotFoundException , SQLException {
        return CrudUtil.execute("UPDATE item SET name=?, category=? , price=?, quantity_in_stock=?  ,expiry_date=? WHERE item_id=?",
                itemDto.getName(),
                itemDto.getCategory(),
                itemDto.getPrice(),
                itemDto.getQuantity(),
                itemDto.getExpirDate(),
                itemDto.getItemId()
        );
    }
    public boolean deleteItem(String ItemId) throws ClassNotFoundException , SQLException {
        return CrudUtil.execute("DELETE FROM item WHERE item_id = ?",
                ItemId);
    }
   /* public ItemDto searchItem(String ItemId) throws ClassNotFoundException , SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM item WHERE item_id = ? ",
                ItemId);
        if (resultSet.next()) {
            ItemDto dto = new ItemDto(
                    resultSet.getString("itemId"),
                    resultSet.getString("name"),
                    resultSet.getInt("price"),
                    resultSet.getInt("quantity"),
                    resultSet.getString("expirDate")
            );
            return dto;
        }
        return null;
    }*/
  /*  public ArrayList<ItemDto> getAllItem() throws ClassNotFoundException , SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM item");
        ArrayList<ItemDto> itemDtoArrayList = new ArrayList<>();
        while (resultSet.next()) {
            ItemDto dto = new ItemDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(4),
                    resultSet.getInt(5),
                    resultSet.getString(6)
            );
            itemDtoArrayList.add(dto);
        }
        return itemDtoArrayList;
    }*/

    public static ArrayList<ItemDto> getAllItem() throws ClassNotFoundException , SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM item");
        ArrayList<ItemDto> items = new ArrayList<>();
        while (resultSet.next()) {
            ItemDto itemDto = new ItemDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(4),
                    resultSet.getInt(5),
                    resultSet.getString(6)
            );
            items.add(itemDto);
        }
        return items;
    }
    public String getNextItemId() throws ClassNotFoundException , SQLException{
        ResultSet resultSet = CrudUtil.execute("SELECT item_id FROM item ORDER BY item_id DESC LIMIT 1");
        char tableCharacter = 'i';

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
