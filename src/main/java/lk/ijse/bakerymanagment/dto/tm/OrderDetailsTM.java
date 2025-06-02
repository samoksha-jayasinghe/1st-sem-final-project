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


public class OrderDetailsTM {
    private String orderid;
    private String productId;
    private int qty;
    }

