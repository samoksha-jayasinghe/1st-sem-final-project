package lk.ijse.bakerymanagment.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString


public class ItemDto {
    private String itemId;
    private String name;
    private String category;
    private int price;
    private int quantity;
    private String expirDate;


}
