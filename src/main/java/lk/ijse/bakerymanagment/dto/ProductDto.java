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

public class ProductDto {
    private String productId;
    private String name;
    private int stocklevel;
    private double price;
    private String category;
}
