package lk.ijse.bakerymanagment.model;

import lk.ijse.bakerymanagment.dto.FeedbackDto;
import lk.ijse.bakerymanagment.dto.IngredientDto;
import lk.ijse.bakerymanagment.dto.InventoryDto;
import lk.ijse.bakerymanagment.dto.InvoiceDto;
import lk.ijse.bakerymanagment.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InvoiceModel {
    public boolean saveInvoice(InvoiceDto invoiceDto) throws ClassNotFoundException , SQLException {
        return CrudUtil.execute("INSERT INTO invoice VALUES (?,?,?,?)",
                invoiceDto.getInvoiceid(),
                invoiceDto.getOrderid(),
                invoiceDto.getDataIssue(),
                invoiceDto.getTotalAmount()

        );
    }
    public boolean updateInvoice(InvoiceDto invoiceDto) throws ClassNotFoundException , SQLException {
        return CrudUtil.execute("UPDATE invoice SET order_id=?, date_issued=? , total_amount=? WHERE invoice_id=?",
                invoiceDto.getOrderid(),
                invoiceDto.getDataIssue(),
                invoiceDto.getTotalAmount(),
                invoiceDto.getInvoiceid()
        );
    }
    public boolean deleteInvoice(String InvoiceId) throws ClassNotFoundException , SQLException {
        return CrudUtil.execute("DELETE FROM invoice WHERE invoice_id = ?",
                InvoiceId);
    }
    public InvoiceDto searchInvoice(String InvoiceId) throws ClassNotFoundException , SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM invoice WHERE invoice_id = ? ",
                InvoiceId);
        if (resultSet.next()) {
            InvoiceDto dto = new InvoiceDto(
                    resultSet.getString("InvoiceId"),
                    resultSet.getString("OrderId"),
                    resultSet.getString("DataIssue"),
                    resultSet.getInt("TotalAmount")
            );
            return dto;
        }
        return null;
    }
    public ArrayList<InvoiceDto> getAllInvoice() throws ClassNotFoundException , SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM invoice");
        ArrayList<InvoiceDto> invoiceDtoArrayList = new ArrayList<>();
        while (resultSet.next()) {
            InvoiceDto dto = new InvoiceDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4)
            );
            invoiceDtoArrayList.add(dto);
        }
        return invoiceDtoArrayList;
    }
    public String getNextInvoiceId() throws ClassNotFoundException , SQLException{
        ResultSet resultSet = CrudUtil.execute("SELECT invoice_id FROM invoice ORDER BY invoice_id DESC LIMIT 1");
        char tableCharacter = 'V';

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
