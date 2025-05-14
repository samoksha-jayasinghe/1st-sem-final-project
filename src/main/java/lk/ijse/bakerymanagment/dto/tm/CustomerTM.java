package lk.ijse.bakerymanagment.dto.tm;

import lombok.*;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CustomerTM {
    private String customerId;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String contact;
    private String userID;
}
