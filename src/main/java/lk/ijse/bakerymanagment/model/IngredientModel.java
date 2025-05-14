package lk.ijse.bakerymanagment.model;

import lk.ijse.bakerymanagment.dto.FeedbackDto;
import lk.ijse.bakerymanagment.dto.IngredientDto;
import lk.ijse.bakerymanagment.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class IngredientModel {
    public boolean saveIngredient(IngredientDto ingredientDto) throws ClassNotFoundException , SQLException {
        return CrudUtil.execute("INSERT INTO ingredient VALUES (?,?,?,?,?,?)",
                ingredientDto.getItemId(),
                ingredientDto.getProductId(),
                ingredientDto.getBatchno(),
                ingredientDto.getDate(),
                ingredientDto.getQty(),
                ingredientDto.getIngredientName()

        );
    }
    public boolean updateIngredient(IngredientDto ingredientDto) throws ClassNotFoundException , SQLException {
        return CrudUtil.execute("UPDATE ingredient SET product_id=?, batch_no=? , expiry_date=?, quantity=?, ingredient_name=?  , WHERE item_id=?",
                ingredientDto.getItemId(),
                ingredientDto.getProductId(),
                ingredientDto.getBatchno(),
                ingredientDto.getDate(),
                ingredientDto.getQty(),
                ingredientDto.getIngredientName()
        );
    }
    public boolean deleteIngredient(String IngredientId) throws ClassNotFoundException , SQLException {
        return CrudUtil.execute("DELETE FROM ingredient WHERE ingredient_id = ?",
                IngredientId);
    }
    public IngredientDto searchIngredient(String IngredientId) throws ClassNotFoundException , SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM ingredient WHERE ingredient_id = ? ",
                IngredientId);
        if (resultSet.next()) {
            IngredientDto dto = new IngredientDto(
                    resultSet.getString("ItemId"),
                    resultSet.getString("ProductId"),
                    resultSet.getString("Batchno"),
                    resultSet.getString("Date"),
                    resultSet.getInt("qty"),
                    resultSet.getString("IngredientName")
            );
            return dto;
        }
        return null;
    }
    public ArrayList<IngredientDto> getAllIngredient() throws ClassNotFoundException , SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM ingredient");
        ArrayList<IngredientDto> ingredientDtoArrayList = new ArrayList<>();
        while (resultSet.next()) {
            IngredientDto dto = new IngredientDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5),
                    resultSet.getString(6)
            );
            ingredientDtoArrayList.add(dto);
        }
        return ingredientDtoArrayList;
    }
    public String getNextFeedbackId() throws ClassNotFoundException , SQLException{
        ResultSet resultSet = CrudUtil.execute("SELECT item_id FROM ingredient ORDER BY item_id DESC LIMIT 1");
        char tableCharacter = 'I';

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
