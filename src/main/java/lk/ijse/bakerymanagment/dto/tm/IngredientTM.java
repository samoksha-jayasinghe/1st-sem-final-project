package lk.ijse.bakerymanagment.dto.tm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
/*@Getter
@Setter
@ToString*/
@Data

public class IngredientTM {
    private String itemId;
    private String productId;
    private String batchno;
    private String date;
    private int qty;
    private String ingredientName;


    public IngredientTM(String itemId, String productId, String batchno, String date, int qty) {
    }
}
