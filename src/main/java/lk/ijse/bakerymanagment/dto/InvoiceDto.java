package lk.ijse.bakerymanagment.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
/*@Getter
@Setter
@ToString*/
@Data

public class InvoiceDto {
    private String invoiceid;
    private String orderid;
    private String dataIssue;
    private int totalAmount;
}
