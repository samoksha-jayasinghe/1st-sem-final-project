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

public class ProductTM {
    private String productId;
    private String name;
    private int stocklevel;
    private int price;
    private String category;
    private int qty;
}
