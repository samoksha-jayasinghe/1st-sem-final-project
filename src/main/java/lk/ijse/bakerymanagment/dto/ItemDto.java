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

public class ItemDto {
    private String itemId;
    private String name;
    private String category;
    private double price;
    private int quantity;
    private String expirDate;
}
