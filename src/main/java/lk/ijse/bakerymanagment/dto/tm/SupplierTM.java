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

public class SupplierTM {
    private String supplierId;
    private String name;
    private String contact;
    private String address;
}
