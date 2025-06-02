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

public class InventoryTM {

    private String inventoryId;
    private String productId;
    private String supplierId;
    private String rawMaterial;
    private int qty;
}


