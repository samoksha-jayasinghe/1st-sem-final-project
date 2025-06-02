package lk.ijse.bakerymanagment.model;

import lk.ijse.bakerymanagment.dto.PaymentDto;
import lk.ijse.bakerymanagment.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentModel {
    public boolean savePayment(PaymentDto paymentDto) throws ClassNotFoundException , SQLException {
        return CrudUtil.execute("INSERT INTO payment VALUES (?,?,?,?,?)",
                paymentDto.getPaymentId(),
                paymentDto.getOrderId(),
                paymentDto.getMethod(),
                paymentDto.getPaymentDate(),
                paymentDto.getAmount()

        );
    }
    public boolean updatePayment(PaymentDto paymentDto) throws ClassNotFoundException , SQLException {
        return CrudUtil.execute("UPDATE payment SET order_id=?, method=? , payment_date=?, amount=? WHERE payment_id=?",
                paymentDto.getOrderId(),
                paymentDto.getMethod(),
                paymentDto.getPaymentDate(),
                paymentDto.getAmount(),
                paymentDto.getPaymentId()
        );
    }
    public boolean deletePayment(String PaymentId) throws ClassNotFoundException , SQLException {
        return CrudUtil.execute("DELETE FROM payment WHERE payment_id = ?",
                PaymentId);
    }
    public PaymentDto searchPayment(String PaymentId) throws ClassNotFoundException , SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM payment WHERE payment_id = ? ",
                PaymentId);
        if (resultSet.next()) {
            PaymentDto dto = new PaymentDto(
                    resultSet.getString("PaymentId"),
                    resultSet.getString("OrderId"),
                    resultSet.getString("Method"),
                    resultSet.getString("PaymentDate"),
                    resultSet.getInt("Amount")
            );
            return dto;
        }
        return null;
    }
    public ArrayList<PaymentDto> getAllPayment() throws ClassNotFoundException , SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM payment");
        ArrayList<PaymentDto> paymentDtoArrayList = new ArrayList<>();
        while (resultSet.next()) {
            PaymentDto dto = new PaymentDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5)
            );
            paymentDtoArrayList.add(dto);
        }
        return paymentDtoArrayList;
    }
    public String getNextPaymentId() throws ClassNotFoundException , SQLException{
        ResultSet resultSet = CrudUtil.execute("SELECT payment_id FROM payment ORDER BY payment_id DESC LIMIT 1");
        String tableCharacter = "PM";

        if(resultSet.next()){
            String lastId = resultSet.getString(1);
            String lastIdNumberString = lastId.substring(tableCharacter.length());
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNumber = lastIdNumber + 1;
            String nextIdString = String.format(tableCharacter + "%03d" , nextIdNumber);
            return nextIdString;
        }
        return tableCharacter +"001";
    }

}
