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

public class OrderDto {
    private String OrderId;
    private String CustomerId;
    private String Orderdate;
    private String Status;
    private int totalAmount;
}
