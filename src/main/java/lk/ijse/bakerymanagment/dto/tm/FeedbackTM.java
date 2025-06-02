package lk.ijse.bakerymanagment.dto.tm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
/*@Getter
@Setter
@ToString*/
@Data

public class FeedbackTM {
    private String FeedbackId;
    private String CustomerId;
    private String OrderId;
    private int Rating;
    private String Comment;

}
