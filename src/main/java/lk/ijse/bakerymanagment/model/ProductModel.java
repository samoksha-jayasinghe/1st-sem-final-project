package lk.ijse.bakerymanagment.model;

import lk.ijse.bakerymanagment.dto.OrderdetailsDto;
import lk.ijse.bakerymanagment.dto.ProductDto;
import lk.ijse.bakerymanagment.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductModel {
    public static boolean reduceQty(int qty, String productId) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE product SET qty = qty - ? WHERE product_id = ?", qty, productId);
    }

    public boolean saveProduct(ProductDto productDto) throws ClassNotFoundException , SQLException {
        return CrudUtil.execute("INSERT INTO product VALUES (?,?,?,?,?,?)",
                productDto.getProductId(),
                productDto.getName(),
                productDto.getStocklevel(),
                productDto.getPrice(),
                productDto.getCategory(),
                productDto.getQty()

        );
    }
    public boolean updateproduct(ProductDto productDto) throws ClassNotFoundException , SQLException {
        return CrudUtil.execute("UPDATE product SET name=?, stock_level=? , price=?, category=?, qty=? WHERE product_id=?",
                productDto.getName(),
                productDto.getStocklevel(),
                productDto.getPrice(),
                productDto.getCategory(),
                productDto.getQty(),
                productDto.getProductId()
        );
    }
    public boolean deleteproduct(String productId) throws ClassNotFoundException , SQLException {
        return CrudUtil.execute("DELETE FROM product WHERE product_id = ?",
                productId);
    }
    public ProductDto searchproduct(String ProductId) throws ClassNotFoundException , SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM product WHERE product_id = ? ",
                ProductId);
        if (resultSet.next()) {
            ProductDto dto = new ProductDto(
                    resultSet.getString("ProductId"),
                    resultSet.getString("Name"),
                    resultSet.getInt("Stocklevel"),
                    resultSet.getInt("Price"),
                    resultSet.getString("Category"),
                    resultSet.getInt("qty")
            );
            return dto;
        }
        return null;
    }
    public ArrayList<ProductDto> getAllproduct() throws ClassNotFoundException , SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM product");
        ArrayList<ProductDto> productDtoArrayList = new ArrayList<>();
        while (resultSet.next()) {
            ProductDto dto = new ProductDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getInt(4),
                    resultSet.getString(5),
                    resultSet.getInt(6)
            );
            productDtoArrayList.add(dto);
        }
        return productDtoArrayList;
    }
    public String getNextproductId() throws ClassNotFoundException , SQLException{
        ResultSet resultSet = CrudUtil.execute("SELECT product_id FROM product ORDER BY product_id DESC LIMIT 1");
        char tableCharacter = 'P';

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

    /*public boolean addProduct(ProductDto productDto) throws SQLException, ClassNotFoundException {
        boolean isInserted = false;

        // Generate a new orderDetail ID for each record
        String newId = getNextproductId();
        productDto.setProductId(newId);

        isInserted = saveProduct(productDto);
        if (!isInserted) {
            return false;
        }

        System.out.println(productDto.getQty());

        boolean isProductUpdated = ProductModel.reduceQty(orderdetailsDto.getQty(), orderdetailsDto.getProductId());
        if (!isProductUpdated) {
            return false;
        }
        return true;
    }*/

}
