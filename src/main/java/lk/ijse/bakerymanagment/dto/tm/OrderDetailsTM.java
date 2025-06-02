package lk.ijse.bakerymanagment.dto.tm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString



public class OrderDetailsTM {
    private String orderid;
    private String productId;
    private int qty;
    }

