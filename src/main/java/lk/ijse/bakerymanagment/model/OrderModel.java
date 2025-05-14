package lk.ijse.bakerymanagment.model;

import lk.ijse.bakerymanagment.dto.OrderDto;
import lk.ijse.bakerymanagment.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderModel {
    public boolean saveOrder(OrderDto orderDto) throws ClassNotFoundException , SQLException {
        return CrudUtil.execute("INSERT INTO orders VALUES (?,?,?,?,?)",
                orderDto.getOrderId(),
                orderDto.getCustomerId(),
                orderDto.getOrderdate(),
                orderDto.getStatus(),
                orderDto.getTotalAmount()

        );
    }
    public boolean updateOrder(OrderDto orderDto) throws ClassNotFoundException , SQLException {
        return CrudUtil.execute("UPDATE orders SET customet_id=?, order_date=? , status=?, total_amount=?  , WHERE order_id=?",
                orderDto.getOrderId(),
                orderDto.getCustomerId(),
                orderDto.getOrderdate(),
                orderDto.getStatus(),
                orderDto.getTotalAmount()
        );
    }
    public boolean deleteOrder(String OrderId) throws ClassNotFoundException , SQLException {
        return CrudUtil.execute("DELETE FROM orders WHERE order_id = ?",
                OrderId);
    }
    public OrderDto searchOrder(String OrderId) throws ClassNotFoundException , SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM orders WHERE order_id = ? ",
                OrderId);
        if (resultSet.next()) {
            OrderDto dto = new OrderDto(
                    resultSet.getString("OrderId"),
                    resultSet.getString("CustomerId"),
                    resultSet.getString("Orderdate"),
                    resultSet.getInt("Status"),
                    resultSet.getInt("TotalAmount")
            );
            return dto;
        }
        return null;
    }
    public ArrayList<OrderDto> getAllOrder() throws ClassNotFoundException , SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM orders");
        ArrayList<OrderDto> orderDtoArrayList = new ArrayList<>();
        while (resultSet.next()) {
            OrderDto dto = new OrderDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getInt(5)
            );
            orderDtoArrayList.add(dto);
        }
        return orderDtoArrayList;
    }
    public String getNextOrderId() throws ClassNotFoundException , SQLException{
        ResultSet resultSet = CrudUtil.execute("SELECT order_id FROM orders ORDER BY order_id DESC LIMIT 1");
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
