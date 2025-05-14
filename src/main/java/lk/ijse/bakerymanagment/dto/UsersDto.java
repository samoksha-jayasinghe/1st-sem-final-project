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

public class UsersDto {
    private String userId;
    private String name;
    private String address;
    private String email;
    private double contact;
}
