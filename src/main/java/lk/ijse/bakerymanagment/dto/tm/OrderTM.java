package lk.ijse.bakerymanagment.dto.tm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
/*@Getter
@Setter
@ToString*/
@Data

public class OrderTM {
    private String OrderId;
    private String CustomerId;
    private String Orderdate;
    private String Status;
    private int totalAmount;
}
