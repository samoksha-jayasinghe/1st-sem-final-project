package lk.ijse.bakerymanagment.model;

import lk.ijse.bakerymanagment.dto.IngredientDto;
import lk.ijse.bakerymanagment.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class IngredientModel {

    public boolean saveIngredient(IngredientDto ingredientDto) throws ClassNotFoundException, SQLException {
        return CrudUtil.execute("INSERT INTO ingredient VALUES (?, ?, ?, ?, ?, ?)",
                ingredientDto.getItemId(),
                ingredientDto.getProductId(),
                ingredientDto.getBatchno(),
                ingredientDto.getDate(),
                ingredientDto.getQty(),
                ingredientDto.getIngredientName()
        );
    }

    public boolean updateIngredient(IngredientDto ingredientDto) throws ClassNotFoundException, SQLException {
        return CrudUtil.execute("UPDATE ingredient SET product_id = ?, batch_no = ?, expiry_date = ?, quantity = ?, ingredient_name = ? WHERE item_id = ?",
                ingredientDto.getProductId(),
                ingredientDto.getBatchno(),
                ingredientDto.getDate(),
                ingredientDto.getQty(),
                ingredientDto.getIngredientName(),
                ingredientDto.getItemId()
        );
    }

    public boolean deleteIngredient(String ingredientId) throws ClassNotFoundException, SQLException {
        return CrudUtil.execute("DELETE FROM ingredient WHERE item_id = ?", ingredientId);
    }

    public IngredientDto searchIngredient(String ingredientId) throws ClassNotFoundException, SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM ingredient WHERE item_id = ?", ingredientId);

        if (resultSet.next()) {
            return new IngredientDto(
                    resultSet.getString("item_id"),
                    resultSet.getString("product_id"),
                    resultSet.getString("batch_no"),
                    resultSet.getString("expiry_date"),
                    resultSet.getInt("quantity"),
                    resultSet.getString("ingredient_name")
            );
        }
        return null;
    }

    public ArrayList<IngredientDto> getAllIngredient() throws ClassNotFoundException, SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM ingredient");
        ArrayList<IngredientDto> ingredientDtoList = new ArrayList<>();

        while (resultSet.next()) {
            IngredientDto dto = new IngredientDto(
                    resultSet.getString("item_id"),
                    resultSet.getString("product_id"),
                    resultSet.getString("batch_no"),
                    resultSet.getString("expiry_date"),
                    resultSet.getInt("quantity"),
                    resultSet.getString("ingredient_name")
            );
            ingredientDtoList.add(dto);
        }

        return ingredientDtoList;
    }

    public String getNextingredientId() throws ClassNotFoundException, SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT item_id FROM ingredient ORDER BY item_id DESC LIMIT 1");
        char prefix = 'I';

        if (resultSet.next()) {
            String lastId = resultSet.getString(1); // e.g., I001
            int idNum = Integer.parseInt(lastId.substring(1));
            return String.format(prefix + "%03d", idNum + 1); // e.g., I002
        }

        return prefix + "001";
    }
}
