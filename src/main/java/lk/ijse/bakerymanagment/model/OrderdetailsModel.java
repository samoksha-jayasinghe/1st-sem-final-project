package lk.ijse.bakerymanagment.model;

import lk.ijse.bakerymanagment.dto.OrderdetailsDto;
import lk.ijse.bakerymanagment.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderdetailsModel {
    public boolean saveOrderdetails(OrderdetailsDto orderdetailsDto) throws ClassNotFoundException , SQLException {
        return CrudUtil.execute("INSERT INTO order_details VALUES (?,?,?)",
                orderdetailsDto.getOrderid(),
                orderdetailsDto.getProductId(),
                orderdetailsDto.getQty()

        );
    }
    public boolean updateOrderdetails(OrderdetailsDto orderdetailsDto) throws ClassNotFoundException , SQLException {
        return CrudUtil.execute("UPDATE order_details SET product_id=?, quantity=?  WHERE order_id=?",
                orderdetailsDto.getProductId(),
                orderdetailsDto.getQty(),
                orderdetailsDto.getOrderid()

        );
    }
    public boolean deleteOrderdetails(String OrderId) throws ClassNotFoundException , SQLException {
        return CrudUtil.execute("DELETE FROM order_details WHERE order_id = ?",
                OrderId);
    }
    public OrderdetailsDto searchOrderdetails(String OrderId) throws ClassNotFoundException , SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM order_details WHERE order_id = ? ",
                OrderId);
        if (resultSet.next()) {
            OrderdetailsDto dto = new OrderdetailsDto(
                    resultSet.getString("OrderId"),
                    resultSet.getString("ProductId"),
                    resultSet.getInt("Qty")
            );
            return dto;
        }
        return null;
    }
    public ArrayList<OrderdetailsDto> getAllOrderdetails() throws ClassNotFoundException , SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM order_details");
        ArrayList<OrderdetailsDto> orderdetailsDtoArrayList = new ArrayList<>();
        while (resultSet.next()) {
            OrderdetailsDto dto = new OrderdetailsDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3)
            );
            orderdetailsDtoArrayList.add(dto);
        }
        return orderdetailsDtoArrayList;
    }
    public String getNextOrderdetailsId() throws ClassNotFoundException , SQLException{
        ResultSet resultSet = CrudUtil.execute("SELECT order_id FROM order_details ORDER BY order_id DESC LIMIT 1");
        char tableCharacter = 'O';

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
