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

public class PaymentTM {
    private String paymentId;
    private String orderId;
    private String method;
    private String paymentDate;
    private int amount;
}
