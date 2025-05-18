package lk.ijse.bakerymanagment.dto.tm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
/*@Getter
@Setter
@ToString*/
@Data

public class EmployeeTM {
    private String employeeId;
    private String name;
    private String role;
    private String salary;
    private String contact;
}
